package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDto {
	
	private int codigoProducto;
	private String producto;
	private double precioUnitario;
	private int stock;

}
