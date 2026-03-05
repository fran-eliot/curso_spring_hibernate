package init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import init.model.Cliente;
import init.repository.ClientesRepository;

@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;

	@Override
	public boolean login(String usuario, String password) {
		
		if (clientesRepository.findFistByUsuarioAndPassword(usuario, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean registro(String usuario, String password, String email, int telefono) {
		if (clientesRepository.existsByUsuario(usuario)) {
			return false;
		} else {
			Cliente nuevoCliente = new Cliente(usuario, password, email, telefono);
			clientesRepository.save(nuevoCliente);
			return true;
		}
	}

}
