package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Libro;

@Service
public class LibrosServiceImpl implements LibrosService {
	
	static List<Libro> libros = new ArrayList<>(Arrays.asList(new Libro("1234567890123","El Quijote","Miguel de Cervantes",20),
			new Libro("2345678901234","Cien años de soledad","Gabriel García Márquez",25),
			new Libro("3456789012345","La sombra del viento","Carlos Ruiz Zafón",15),
			new Libro("4567890123456","El nombre de la rosa","Umberto Eco",18),
			new Libro("5678901234567","El código Da Vinci","Dan Brown",22)
			));

	@Override
	public boolean eliminarLibro(String isbn) {
		if (buscarLibro(isbn)==null) {
			return false;
		}
		libros.removeIf(libro->libro.getIsbn().equals(isbn));
		return true;
	}

	@Override
	public Libro buscarLibro(String isbn) {
		return libros.stream()
				.filter(libro->libro.getIsbn().equals(isbn))
				.findFirst()
				.orElse(null);
				
				
	}

	@Override
	public boolean nuevoLibro(Libro libro) {
		for (Libro l:libros) {
			if(l.getIsbn()==libro.getIsbn()) {
				return false;
			}
		}
		return libros.add(libro);
	}



}
