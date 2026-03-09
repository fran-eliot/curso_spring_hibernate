package init.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoDto {
	
	private String nombreCurso;
	private int codCurso;
	private int duracion;
	private String horario;

	
	

}
