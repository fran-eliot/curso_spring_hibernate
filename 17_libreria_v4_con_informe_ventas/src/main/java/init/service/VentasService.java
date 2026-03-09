package init.service;

import java.time.LocalDateTime;
import java.util.List;

import init.dtos.LibroDto;
import init.dtos.VentaDto;
import init.model.Venta;

public interface VentasService{
	
	public void nuevaVenta(int idCliente, List<LibroDto> libros);
	
	public List<VentaDto> consultarVentas(LocalDateTime f1, LocalDateTime f2);

}
