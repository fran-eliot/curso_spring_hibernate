package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.PedidoDto;
import init.model.Pedido;

@Component
public class Mapeador {
	
	public PedidoDto pedidoEntityToDto(Pedido pedido) {
		return new PedidoDto(pedido.getIdPedido(), pedido.getCodigoProducto(), pedido.getUnidades(), pedido.getTotal(), pedido.getFechaPedido());
	}
	
	public Pedido pedidoDtoToEntity(PedidoDto pedido) {
		return new Pedido(pedido.getIdPedido(), pedido.getCodigoProducto(), pedido.getUnidades(), pedido.getTotal(), pedido.getFechaPedido());
	}

}
