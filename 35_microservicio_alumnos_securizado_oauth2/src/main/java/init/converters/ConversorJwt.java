package init.converters;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;


@Component
public class ConversorJwt implements Converter<Jwt, AbstractAuthenticationToken> {

	@Value("${keycloak.clientId}")
	String clientId;
	
	@Override
	public AbstractAuthenticationToken convert(Jwt source) {
		
		return new JwtAuthenticationToken(source, getAuthorities(source), source.getClaim("preferred_username"));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Jwt jwt){
		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        //si no hay información sobre ese cliente, se devuelve colección vacía
        if (resourceAccess == null
                || (resource = (Map<String, Object>) resourceAccess.get(clientId)) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
            return Set.of();
        }
        //a cada rol indicado se le debe añadir el prefijo "ROLE_"
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
	}

}
