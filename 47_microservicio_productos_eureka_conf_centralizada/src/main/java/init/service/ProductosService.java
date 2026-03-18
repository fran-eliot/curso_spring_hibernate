package init.service;

import java.util.List;

import init.dtos.ProductoDto;

public interface ProductosService {
	
	List<ProductoDto> productos();
	
	ProductoDto getProducto(int codigoProducto);
	
	ProductoDto altaProducto(ProductoDto producto);
	
	ProductoDto actualizaStock(int codigoProducto, int unidades);
	
	
}
