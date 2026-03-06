package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
	
	private String usuario;
	private String password;
	private String email;
	private int telefono;
	

}
