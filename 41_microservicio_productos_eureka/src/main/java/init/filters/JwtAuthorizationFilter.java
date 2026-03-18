package init.filters;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	String clave; 
	private final String ENCABEZADO="Authorization";
	private final String PREFIJO_TOKEN="Bearer";
	public JwtAuthorizationFilter(AuthenticationManager authManager,String clave) {
		super(authManager);	
		this.clave=clave;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException, java.io.IOException {
		String header = req.getHeader(ENCABEZADO);
		//si no hay token JWT
		if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
			chain.doFilter(req, res);
			return;
		}
		//obtenemos los datos del usuario a partir del token
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		//la información del usuario se almacena en el contexto de seguridad
		//para que pueda ser utilizada por Spring security durante el 
		//proceso de autorización
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		//el token viene en la cabecera de la petición
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.
			Claims claims=Jwts.parserBuilder()
					.setSigningKey(clave.getBytes())
					.build()
					.parseClaimsJws(token.replace(PREFIJO_TOKEN, "").trim())
					.getBody();
			String user = claims.getSubject();
			List<String> authorities=(List<String>) claims.get("authorities");
			if (user != null) {
				//creamos el objeto con la información del usuario
				return new UsernamePasswordAuthenticationToken(user, null, authorities.stream()
													.map(SimpleGrantedAuthority::new)
													.toList());
			}
			return null;
		}
		return null;
	}
}

