package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libros")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Libro {
	
	@Id
	private int isbn;
	private String titulo;
	private String autor;
	private double precio;
	private int paginas;
	@ManyToOne()
	@JoinColumn(name = "idTema", referencedColumnName = "idTema")
	private Tema temaRelacionado;

}
