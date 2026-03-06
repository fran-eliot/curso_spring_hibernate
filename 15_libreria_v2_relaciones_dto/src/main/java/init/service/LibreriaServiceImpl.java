package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.LibroDto;
import init.dtos.TemaDto;
import init.mappers.Mapeador;
import init.model.Libro;
import init.model.Tema;
import init.repository.LibrosRepository;
import init.repository.TemasRepository;

@Service
public class LibreriaServiceImpl implements LibreriaService {
	
	@Autowired
	LibrosRepository librosRepository;
	@Autowired
	TemasRepository temasRepository;
	@Autowired
	Mapeador mapeador;

	@Override
	public List<Libro> getLibros() {
		return librosRepository.findAll();
	}

	@Override
	public List<Libro> getLibrosByTema(String tema) {
		if (temasRepository.findByTema(tema).isEmpty()) {
			return null;
		} else {
			int idTema = temasRepository.findByTema(tema).get(0).getIdTema();
			return librosRepository.findByTemaRelacionado_IdTema(idTema);
		}
		
	}
	
	@Override
	public List<String> getTemas() {
		return temasRepository.findAllTemas();
	}

	@Override
	public void removeByIsbn(int isbn) {
		librosRepository.deleteByIsbn(isbn);
		
	}

	@Override
	public void updateLibro(Libro libro) {
		librosRepository.save(libro);
		
	}
	
	@Override
	public Libro getLibroByIsbn(int isbn) {
		return librosRepository.findByIsbn(isbn);
	}

	@Override
	public List<TemaDto> getTemasConLibros() {
		return temasRepository.findAll().
				stream().
				map(tema -> mapeador.temaEntityToDto(tema))
				.toList();
	}

	@Override
	public void actualizaLibro(LibroDto libroDto) {
		Tema tema = temasRepository.findTemaByIsbnLibro(libroDto.getIsbn());
	    Libro libro = mapeador.libroDtoToEntity(libroDto);
	    libro.setTemaRelacionado(tema); 
	    librosRepository.save(libro);
		
	}

	@Override
	public LibroDto getLibroDtoByIsbn(int isbn) {
		return mapeador.libroEntityToDto(librosRepository.findByIsbn(isbn));
	}	

}
