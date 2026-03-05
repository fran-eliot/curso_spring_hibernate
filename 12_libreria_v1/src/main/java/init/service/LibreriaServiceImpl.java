package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Libro;
import init.repository.LibrosRepository;
import init.repository.TemasRepository;

@Service
public class LibreriaServiceImpl implements LibreriaService {
	
	@Autowired
	LibrosRepository librosRepository;
	@Autowired
	TemasRepository temasRepository;

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
			return librosRepository.findByIdTema(idTema);
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

}
