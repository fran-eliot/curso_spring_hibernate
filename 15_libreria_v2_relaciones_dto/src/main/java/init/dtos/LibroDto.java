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
	//no incluimos el tema relacionado como un objeto, sino solo su nombre para evitar bucles infinitos al convertir a DTO
	private String nombreTema;

}
