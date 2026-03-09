package init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import init.dtos.ClienteDto;
import init.mappers.Mapeador;
import init.model.Cliente;
import init.repository.ClientesRepository;

@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private Mapeador mapeador;

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



	@Override
	public ClienteDto autenticarUsuario(String usuario, String password) {
		return mapeador.clienteEntityToDto(clientesRepository.findFistByUsuarioAndPassword(usuario, password));
	}



	@Override
	public boolean guardarCliente(ClienteDto clienteDto) {
		if (clientesRepository.existsByUsuario(clienteDto.getUsuario())) {
			return false;
		} else {
			clientesRepository.save(mapeador.clienteDtoToEntity(clienteDto));
			return true;
		}
	}

}
