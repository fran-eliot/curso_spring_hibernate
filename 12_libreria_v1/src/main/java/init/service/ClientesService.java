package init.service;

public interface ClientesService {
	
	//Login
	boolean login(String usuario, String password);
	
	//Registro
	boolean registro(String usuario, String password, String email, int telefono);

}
