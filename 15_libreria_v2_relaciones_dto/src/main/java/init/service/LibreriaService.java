package init.service;

import java.util.List;

import init.dtos.LibroDto;
import init.dtos.TemaDto;
import init.model.Libro;

public interface LibreriaService {
	
	List<Libro> getLibros();
	
	List<Libro> getLibrosByTema(String tema);
	
	List<String> getTemas();
	
	List<TemaDto> getTemasConLibros();
	
	
	
	void removeByIsbn(int isbn);
	
	void updateLibro(Libro libro);
	
	void actualizaLibro(LibroDto libroDto);
	
	Libro getLibroByIsbn(int isbn);
	
	LibroDto getLibroDtoByIsbn(int isbn);

}
