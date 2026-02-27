package service;

import java.util.List;

import model.Libro;

public interface LibrosService {
	
	Libro buscarLibro(String isbn);
	boolean nuevoLibro(Libro libro);
	boolean eliminarLibro(String isbn);

}
