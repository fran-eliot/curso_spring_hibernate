package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Libro {
	private String	 isbn;
	private String titulo;
	private String autor;
	private long precio;
	
	
	
}
