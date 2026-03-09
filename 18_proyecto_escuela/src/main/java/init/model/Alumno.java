package init.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alumnos")
public class Alumno {
	@Id
	private String dni;
	private String nombre;
	private String email;
	private int edad;
	
	@ManyToMany(mappedBy = "alumnos")
	private List<Curso> cursos;

	public Alumno(String dni, String nombre, String email, int edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}
	
	

}
