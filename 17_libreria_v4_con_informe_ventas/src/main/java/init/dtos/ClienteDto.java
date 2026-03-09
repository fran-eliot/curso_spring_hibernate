package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
	
	private int idCliente;
	private String usuario;
	private String password;
	private String email;
	private int telefono;
	
	public ClienteDto(String usuario, String password, String email, int telefono) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
	}
	
	
	

}
