package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.clients.CountryFeign;
import init.mappers.Mapeador;
import init.model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {
	
	
	@Autowired
	Mapeador mapeador;
	
	@Autowired
	CountryFeign countryFeign;

	@Override
	public List<String> getContinentes() {
		return countryFeign.paises() // 1. Traemos todos los países
                .stream()
                .map(p->p.getRegion())  // 2. Extraemos solo la región (continente)
                .distinct()                  // 3. Evitamos que "Europe" salga 20 veces
                .toList();
	}

	@Override
    public List<Pais> getPaisesPorContinente(String continente) {
        return countryFeign.paises() // 1. Traemos la lista completa
                .stream()
                .map(mapeador::countryDtoToPais) // 2. Convertimos a entidad Pais
                .filter(c -> c.getContinente().equalsIgnoreCase(continente)) // 3. Filtramos por continente
                .toList();
    }

}
