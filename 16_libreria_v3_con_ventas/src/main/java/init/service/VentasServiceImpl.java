package init.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import init.dtos.LibroDto;
import init.mappers.Mapeador;
import init.model.Cliente;
import init.model.Venta;
import init.repository.ClientesRepository;
import init.repository.VentasRepository;

public class VentasServiceImpl implements VentasService {
	
	@Autowired
	private VentasRepository ventasRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	Mapeador mapeador;

	@Override
	public void nuevaVenta(int idCliente, List<LibroDto> libros) {
		Cliente cliente = clientesRepository.findById(idCliente).orElse(null);
		if (cliente!=null) {
			for (LibroDto libro : libros) {
				Venta venta = new Venta();
				venta.setCliente(cliente);
				venta.setLibro(mapeador.libroDtoToEntity(libro));
				venta.setFecha(LocalDateTime.now());
				ventasRepository.save(venta);
			}
		}

	}

}
