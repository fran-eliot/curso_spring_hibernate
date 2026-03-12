package init.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import init.dtos.CountryDto;

@FeignClient(name="countriesFeign",url="${remote.url}")
public interface CountryFeign {
	
	@GetMapping()
    List<CountryDto> paises();

}
