package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LibroDto {
	
	private int isbn;
	private String titulo;
	private String autor;
	private int paginas;
	private double precio;
	private TemaDto temaDto;

}
