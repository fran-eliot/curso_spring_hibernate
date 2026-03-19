package init.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import init.model.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno, Integer> {
    
    List<Alumno> findByCurso(String curso);
    
    Optional<Alumno> findByNombreAndCurso(String nombre, String curso);

    @Query("SELECT DISTINCT a.curso FROM Alumno a")
    List<String> findAllCursos();

    @Transactional
    void deleteByNombreAndCurso(String nombre, String curso);

    @Query("SELECT AVG(a.nota) FROM Alumno a WHERE a.curso = ?1")
    double averageByCurso(String curso);
    
    Optional<Alumno>findFirstByEmail(String email);
}



