package init.service;

import java.util.List;

import init.model.Pais;

public interface PaisesService {
	
	List<String> getContinentes();
	
	List<Pais> getPaisesPorContinente(String continente);

}
