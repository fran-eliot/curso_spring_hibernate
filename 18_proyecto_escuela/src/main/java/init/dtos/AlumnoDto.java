package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoDto {
	private String dni;
	private String nombre;
	private String email;
	private int edad;

}
