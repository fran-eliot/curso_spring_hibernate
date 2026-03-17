package init.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
	private int idPedido;
	private int codigoProducto;
	private int unidades;
	private double total;
	private LocalDateTime fechaPedido;

}
