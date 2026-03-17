package init.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.dtos.PedidoDto;
import init.dtos.ProductoDto;
import init.mappers.Mapeador;
import init.model.Pedido;
import init.repositoy.PedidosRepository;

@Service
public class PedidosServiceImpl implements PedidosService {
    
    @Autowired
    PedidosRepository pedidosRepository;
    
    @Autowired
    Mapeador mapeador;

    @Autowired
    private RestClient.Builder restClientBuilder; // Inyectamos el Builder con LoadBalancer
    
    private final String urlProductos = "http://servicio_productos/productos";

    @Override
    public List<PedidoDto> pedidos() {
        return pedidosRepository.findAll()
                .stream()
                .map(p -> mapeador.pedidoEntityToDto(p))
                .toList();
    }

    @Override
    public void altaPedido(PedidoDto pedidoDto, String token) {
        // Creamos el cliente a partir del builder (esto aplica la magia de Eureka)
        RestClient restClient = restClientBuilder.build();

        // 1. Obtener datos del producto (GET)
        ProductoDto prod = restClient.get()
            .uri(urlProductos + "/" + pedidoDto.getCodigoProducto())
            .header("Authorization", token)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (request, response) -> {
                throw new RuntimeException("Error al consultar producto: " + response.getStatusCode());
            })
            .body(ProductoDto.class);

        // 2. Lógica de negocio y persistencia local
        if (prod != null && prod.getStock() >= pedidoDto.getUnidades()) {
            double totalPedido = prod.getPrecioUnitario() * pedidoDto.getUnidades();
            
            Pedido pedido = mapeador.pedidoDtoToEntity(pedidoDto);
            pedido.setTotal(totalPedido);
            pedido.setFechaPedido(LocalDateTime.now());
            
            pedidosRepository.save(pedido);

            // 3. Actualizar el stock en el microservicio de productos (PUT)
            restClient.put()
                .uri(urlProductos + "/" + pedidoDto.getCodigoProducto() + "/" + pedidoDto.getUnidades())
                .header("Authorization", token)
                .retrieve()
                .toBodilessEntity(); // No esperamos cuerpo de respuesta
                
        } else {
            throw new RuntimeException("Stock insuficiente o producto inexistente");
        }
    }
}