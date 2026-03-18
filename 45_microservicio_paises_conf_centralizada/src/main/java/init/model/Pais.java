package init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pais {
	
	private String nombre;
	private String continente;
	private long habitantes;
	private String capital;
	private String bandera;
	

}
