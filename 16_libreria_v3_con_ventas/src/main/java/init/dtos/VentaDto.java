package init.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaDto {
	
	private ClienteDto cliente;
	private LibroDto libro;
	private LocalDateTime fecha;

}
