package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.CountryDto;
import init.model.Pais;

@Component
public class Mapeador {
	
	public Pais countryDtoToPais(CountryDto country) {
		return new Pais(country.getName(), country.getRegion(), country.getPopulation(), country.getCapital(), country.getFlag());
	}
	
	public CountryDto paisToCountryDto(Pais pais) {
		return new CountryDto(pais.getNombre(), pais.getContinente(), pais.getHabitantes(), pais.getCapital(), pais.getBandera());
	}

}
