package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="productos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {
	
	@Id
	private int codigoProducto;
	private String producto;
	private double precioUnitario;
	private int stock;
	
	

}
