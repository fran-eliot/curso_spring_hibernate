package init.service;

import java.util.List;

import init.dtos.AlumnoDto;


public interface AlumnosService {
	
	List<String> getCursos();
	List<AlumnoDto> todosAlumnos();
	List<AlumnoDto> alumnosCurso (String curso);
	boolean nuevoAlumno(AlumnoDto alumno);
	AlumnoDto eliminarAlumno(String nombre, String curso);
	AlumnoDto findById(int idAlumno);
	void removeById(int idAlumno);
	void update(AlumnoDto alumno);
	AlumnoDto eliminarPorEmail(String email);

}
