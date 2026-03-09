package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatriculaInfoDto {
	
	private AlumnoDto alumno;
	private CursoDto curso;
	
	
}
