package service;

import java.util.List;

import model.Alumno;

public interface AlumnosService {
	
	List<String> getCursos();
	List<Alumno> alumnosCurso (String curso);
	boolean nuevoAlumno(Alumno alumno);
	boolean eliminarAlumno(String nombre, String curso);
	Alumno findById(int idAlumno);
	void removeById(int idAlumno);
	void update(Alumno alumno);

}
