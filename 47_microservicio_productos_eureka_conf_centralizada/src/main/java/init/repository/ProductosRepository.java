package init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer> {
	

}
