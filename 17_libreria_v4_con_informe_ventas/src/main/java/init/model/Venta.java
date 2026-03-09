package init.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	@ManyToOne
	@JoinColumn(name="idCliente",referencedColumnName="idCliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="idLibro",referencedColumnName="isbn")
	private Libro libro;
	private LocalDateTime fecha;
	
	public Venta(Cliente cliente, Libro libro, LocalDateTime fecha) {
		super();
		this.cliente = cliente;
		this.libro = libro;
		this.fecha = fecha;
	}

	
}
