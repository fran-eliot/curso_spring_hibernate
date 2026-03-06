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
    public ClienteDto autenticarUsuario(String usuario, String password) {
        Cliente cliente = clientesRepository.findFistByUsuarioAndPassword(usuario, password);
        return (cliente != null) ? mapeador.clienteEntityToDto(cliente) : null;
    }

    @Override
    public boolean guardarCliente(ClienteDto clienteDto) {
        if (clientesRepository.existsByUsuario(clienteDto.getUsuario())) {
            return false;
        }
        clientesRepository.save(mapeador.clienteDtoToEntity(clienteDto));
        return true;
    }

}
