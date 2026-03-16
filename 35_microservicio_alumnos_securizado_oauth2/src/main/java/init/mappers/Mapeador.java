package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.AlumnoDto;
import init.model.Alumno;

@Component
public class Mapeador {
	
	public Alumno alumnoDtoToEntity(AlumnoDto alumno) {
		return new Alumno(alumno.getNombre(),alumno.getCurso(), alumno.getEmail(), alumno.getNota());
	}
	
	public AlumnoDto alumnoEntityToDto(Alumno alumno) {
		return new AlumnoDto(alumno.getNombre(),alumno.getCurso(), alumno.getEmail(), alumno.getNota());
	}
	
	

}
