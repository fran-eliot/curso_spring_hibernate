package init.service;

import java.util.List;

import init.dtos.LibroDto;

public interface VentasService{
	
	public void nuevaVenta(int idCliente, List<LibroDto> libros);

}
