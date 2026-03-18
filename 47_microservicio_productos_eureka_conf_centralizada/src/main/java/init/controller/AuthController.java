package init.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.CredentialsDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authManager;
	
	//propiedades utilizadas durante la generación del token
	@Value("${jwt.properties.timeout}")
	private long timeOut;
	
	@Value("${jwt.properties.key}")
	private String key;

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody CredentialsDto credentials){
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUser(),credentials.getPassword()));
			return ResponseEntity.ok(getToken(auth));
		}catch(AuthenticationException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	private String getToken(Authentication auth) {
		//en el body del token se incluye el usuario 
		//y los roles a los que pertenece, además
		//de la fecha de caducidad y los datos de la firma
		return Jwts.builder()
				.setIssuedAt(new Date()) //fecha creación
				.setSubject(auth.getName()) //usuario
				.claim("authorities",auth.getAuthorities().stream() //roles
								.map(GrantedAuthority::getAuthority)
								.toList())
				.setExpiration(new Date(System.currentTimeMillis() + timeOut)) //fecha caducidad
				.signWith(Keys.hmacShaKeyFor(key.getBytes()))//clave y algoritmo para firma				
				.compact(); //generación del token

	}
	

}
