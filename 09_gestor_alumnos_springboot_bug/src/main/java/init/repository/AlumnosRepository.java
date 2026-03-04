package init.repository;

import java.util.List;

import init.model.Alumno;

public interface AlumnosRepository {
	void save(Alumno alumno);
	List<Alumno> findByCurso(String curso);
	Alumno findByNombreAndCurso(String nombre, String curso);
	List<String> findAllCursos();
	void removeById(int idAlumno);
	
	double averageByCurso(String curso);
}
