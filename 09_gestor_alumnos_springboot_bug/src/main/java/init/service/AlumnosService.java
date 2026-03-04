package init.service;

import java.util.List;

import init.model.Alumno;

public interface AlumnosService {
	boolean guardar(Alumno alumno);
	List<Alumno> alumnosCurso(String curso);
	List<String> cursos();
	void eliminar(int idAlumno);
}
