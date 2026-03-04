package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno, Integer> {
	
	List<Alumno> findByCurso(String curso);
	Alumno findFirstByNombreAndCurso(String nombre,String curso);
	@Query("SELECT DISTINCT curso FROM Alumno")
	List<String> findAllByCurso();
	@Query("DELETE FROM Alumno WHERE nombre = ?1 AND curso = ?2")
	boolean deleteByNombreAndCurso(String nombre, String curso);
	
	void deleteByIdAlumno(int idAlumno);
	Alumno findByIdAlumno(int idAlumno);
	@Query("SELECT AVG(nota) FROM Alumno WHERE curso = ?1")
	double averageByCurso(String curso);
	
}
