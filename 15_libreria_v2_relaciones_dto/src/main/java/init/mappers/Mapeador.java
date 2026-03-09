package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.ClienteDto;
import init.dtos.LibroDto;
import init.dtos.TemaDto;
import init.model.Cliente;
import init.model.Libro;
import init.model.Tema;

@Component
public class Mapeador {
	
	public Cliente clienteDtoToEntity(ClienteDto clienteDto) {
		return new Cliente(clienteDto.getUsuario(), clienteDto.getPassword(), clienteDto.getEmail(), clienteDto.getTelefono());
	}

	
	public ClienteDto clienteEntityToDto(Cliente cliente) {
		return new ClienteDto(cliente.getUsuario(), cliente.getPassword(), cliente.getEmail(), cliente.getTelefono());
	}
	
	public TemaDto temaEntityToDto(Tema tema) {
		return new TemaDto(tema.getIdTema(), tema.getTema());
	}
	
	public Tema temaDtoToEntity(TemaDto temaDto) {
		return new Tema(temaDto.getTema(), null);
	}
	
	public Libro libroDtoToEntity(init.dtos.LibroDto libroDto) {
		return new Libro(libroDto.getIsbn(), libroDto.getTitulo(), libroDto.getAutor(), libroDto.getPrecio(), libroDto.getPaginas());
	}
	
	public LibroDto libroEntityToDto(Libro libro) {
		return new LibroDto(libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getPaginas(), libro.getPrecio(), libro.getTemaRelacionado().getTema());
	}
}
