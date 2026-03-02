package repository;

import java.util.List;

import model.Alumno;

public interface AlumnosRepository {
	
	void save(Alumno alumno);
	List<Alumno> findByCurso(String curso);
	Alumno findFirstByNombreAndCurso(String nombre,String curso);
	List<String> findAllCursos();
	boolean eliminarAlumno(String nombre, String curso);

}
