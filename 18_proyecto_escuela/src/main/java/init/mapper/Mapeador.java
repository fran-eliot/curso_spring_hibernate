package init.mapper;

import org.springframework.stereotype.Component;

import init.dtos.AlumnoDto;
import init.dtos.CursoDto;
import init.model.Alumno;
import init.model.Curso;

@Component
public class Mapeador {
	
	public AlumnoDto alumnoEntitiyToDto(Alumno alumno) {
		return new AlumnoDto(alumno.getDni(), alumno.getNombre(), alumno.getEmail(), alumno.getEdad());
	}
	
	public Alumno alumnoDtoToEntity(AlumnoDto alumno) {
		return new Alumno(alumno.getDni(), alumno.getNombre(), alumno.getEmail(), alumno.getEdad());
	}
	
	public CursoDto cursoEntityToDto(Curso curso) {
		return new CursoDto(curso.getDenominacion(), curso.getCodCurso(), curso.getDuracion(), curso.getHorario());
	}
	
	public Curso cursoDtoToEntity(CursoDto curso) {
		return new Curso(curso.getCodCurso(), curso.getNombreCurso(), curso.getDuracion(), curso.getHorario());
	}

}