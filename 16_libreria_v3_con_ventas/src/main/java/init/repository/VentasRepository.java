package init.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Venta;

public interface VentasRepository extends JpaRepository<Venta, Integer> {
	
	//Devuelva la lista de ventas asociadas a un rango de fechas
	List<Venta> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	//Devuelva la lista de ventas asociadas a un determinado cliente
	List<Venta> findByClienteIdCliente(int idCliente);

}
