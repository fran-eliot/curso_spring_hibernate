package init.service;

import java.util.List;

import init.dtos.PedidoDto;

public interface PedidosService {
	
	List<PedidoDto> pedidos();
	
	void altaPedido(PedidoDto pedido, String token);

}
