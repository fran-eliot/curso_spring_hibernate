package init.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

}
