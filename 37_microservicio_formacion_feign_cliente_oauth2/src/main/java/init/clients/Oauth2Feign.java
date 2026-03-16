package init.clients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import init.dtos.TokenDto;

@FeignClient(name = "oauth2Feign", url = "${oauth2.url}")
public interface Oauth2Feign {
	
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenDto autenticar(@RequestBody MultiValueMap<String, String> data);

}
