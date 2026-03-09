package init.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	private int codCurso;
	private String denominacion;
	private int duracion;
	private String horario;
	
	@ManyToMany()
	@JoinTable(name = "matriculas",
				joinColumns = @JoinColumn(name = "idCurso", 
				referencedColumnName = "codCurso"),
				inverseJoinColumns = @JoinColumn(name = "idAlumno", 
				referencedColumnName = "dni"))
	private List<Alumno> alumnos;

	public Curso(int codCurso, String denominacion, int duracion, String horario) {
		super();
		this.codCurso = codCurso;
		this.denominacion = denominacion;
		this.duracion = duracion;
		this.horario = horario;
	}

	
}
