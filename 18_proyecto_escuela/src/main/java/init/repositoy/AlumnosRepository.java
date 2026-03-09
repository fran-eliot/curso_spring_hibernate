package init.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno, String> {
	
	@Query("SELECT a FROM Alumno a JOIN a.cursos c WHERE c.codCurso = ?1")
	List<Alumno> findByCodigoCurso(int codigo);

}
