package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.ProductoDto;
import init.model.Producto;

@Component
public class Mapeador {
	
	public ProductoDto productoEntityToDto(Producto producto) {
		return new ProductoDto(producto.getCodigoProducto(),producto.getProducto(),producto.getPrecioUnitario(),producto.getStock());
	}
	
	public Producto productoDtoToEntity(ProductoDto producto) {
		return new Producto(producto.getCodigoProducto(),producto.getProducto(),producto.getPrecioUnitario(),producto.getStock());
	}
	

}
