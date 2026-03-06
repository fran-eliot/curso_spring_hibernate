package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Tema;

public interface TemasRepository extends JpaRepository<Tema, Integer> {
	
	List<Tema> findByTema(String tema);
	
	@Query("SELECT DISTINCT(t.tema) FROM Tema t")
	List<String> findAllTemas();
	
	//método que devuelva el objeto Tema al que pertenece un determinado libro, a través del isbn del libro
	@Query("SELECT t FROM Tema t JOIN t.libros l WHERE l.isbn = ?1")
	Tema findTemaByIsbnLibro(int isbn);

}
