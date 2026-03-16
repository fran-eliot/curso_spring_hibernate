package init.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import feign.FeignException;
import init.clients.AlumnosFeign;
import init.clients.Oauth2Feign;
import init.dtos.EstudianteDto;
import init.dtos.TokenDto;
import init.mappers.Mapeador;


@Service
public class EstudiantesServiceImpl implements EstudiantesService , InitializingBean {
	

	@Autowired
	Mapeador mapeador;
	
	@Autowired
	AlumnosFeign alumnosFeign;
	
	@Autowired
	Oauth2Feign oauth2Feign;
	
	@Value("${remote.url}")
	private String urlBase;
	@Value("${remote.user}")
	private String user;
	@Value("${remote.password}")
	private String pass;
	
	@Value("${oauth2.url}")
	private String urlOauth2;
	@Value("${oauth2.clientId}")
	private String clienteId;
	@Value("${oauth2.grantType}")
	private String grantType;
	
	private TokenDto tokenRecibido;

	@Override
	public List<EstudianteDto> estudiantesRangoCalificacion(double min, double max) {

		return alumnosFeign.alumnos("Bearer "+tokenRecibido.getAccess_token())
				.stream()
				.map(mapeador::alumnoToEstudiante)
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
	
		try {
			alumnosFeign.nuevoAlumno(mapeador.estudianteToAlumno(estudiante),"Bearer "+tokenRecibido.getAccess_token());
			return true;
		}catch(FeignException ex) {
			if (ex.status()==409) {
				return false;
			}
			throw ex;
		}
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		cargarToken();
		
	}
	
	private void cargarToken() {
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("client_id", clienteId);		
		params.add("username", user);
		params.add("password", pass);
		params.add("grant_type", grantType);
	    
	    try {
	        // La magia de Feign: una sola línea
	        tokenRecibido = oauth2Feign.autenticar(params);
	        
	        if (tokenRecibido != null) {
	            System.out.println("Token Recibido: " + tokenRecibido.getAccess_token());
	        }
	    } catch (FeignException ex) {
	        // Feign lanza FeignException en lugar de HttpClientErrorException
	        System.err.println("Error al obtener el token: " + ex.getMessage());
	        if (ex.status() == HttpStatus.CONFLICT.value()) { // 409
	            return;
	        }
	        throw ex; 
	    }
	}

}
