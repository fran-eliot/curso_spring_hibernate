package init.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "temas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTema;
	private String tema;
	
	@OneToMany(mappedBy = "temaRelacionado")
	private List<Libro> libros;

	public Tema(int idTema, String tema) {
		this.idTema = idTema;
		this.tema = tema;
	}
	
	
	
	

}
