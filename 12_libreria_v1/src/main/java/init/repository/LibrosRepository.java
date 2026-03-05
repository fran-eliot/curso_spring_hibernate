package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import init.model.Libro;

public interface LibrosRepository extends JpaRepository<Libro, Integer> {
	
	List<Libro> findByTitulo(String titulo);
	
	List<Libro> findByAutor(String autor);
	
	List<Libro> findByIdTema(int idTema);
	
	@Transactional
	@Modifying
	void deleteByIsbn(int isbn);
	
	Libro findByIsbn(int isbn);

}
