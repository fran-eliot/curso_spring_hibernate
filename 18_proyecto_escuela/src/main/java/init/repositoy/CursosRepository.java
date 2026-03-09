package init.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.model.Curso;

public interface CursosRepository extends JpaRepository<Curso, Integer> {
	
	//Métodos que se van a utilizar: findAll(), save()
	
	@Transactional
	@Modifying
	@Query(value= "insert into matriculas values(?,?)",nativeQuery=true)
	void matricular(int codCurso, String dni);

}
