package init.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import init.converters.ConversorJwt;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	ConversorJwt conversorJwt;
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/alumnos","/alumnos/**").authenticated()
				.requestMatchers(HttpMethod.POST, "/alumnos").hasRole("ADMINS")
				.requestMatchers(HttpMethod.DELETE, "/alumnos").hasAnyRole("ADMINS","OPERATORS")
				.anyRequest().permitAll()
				)
		.oauth2ResourceServer(oauth2ResourceServer-> oauth2ResourceServer
				.jwt(jwt->jwt .jwtAuthenticationConverter(conversorJwt))) 
        .sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
		
	}

}
