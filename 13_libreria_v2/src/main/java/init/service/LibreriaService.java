package init.service;

import java.util.List;

import init.model.Libro;

public interface LibreriaService {
	
	List<Libro> getLibros();
	
	List<Libro> getLibrosByTema(String tema);
	
	List<String> getTemas();
	
	void removeByIsbn(int isbn);
	
	void updateLibro(Libro libro);
	
	Libro getLibroByIsbn(int isbn);

}
