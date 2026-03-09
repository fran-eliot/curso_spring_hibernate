package init.service;

import init.dtos.ClienteDto;

public interface ClientesService {
	
	//Login
	boolean login(String usuario, String password);
	
	ClienteDto autenticarUsuario(String usuario, String password);
	
	boolean guardarCliente(ClienteDto clienteDto);
	
	//Registro
	boolean registro(String usuario, String password, String email, int telefono);

}
