package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstudianteDto {
	
	private String nombre;
	private String curso;
	private String email;
	private double calificacion;

}
