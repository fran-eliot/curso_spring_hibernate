package init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
	
	boolean existsByUsuario(String usuario);
	boolean existsByUsuarioAndPassword(String usuario, String password);
	
	Cliente findFistByUsuarioAndPassword(String usuario, String password);
	
}
