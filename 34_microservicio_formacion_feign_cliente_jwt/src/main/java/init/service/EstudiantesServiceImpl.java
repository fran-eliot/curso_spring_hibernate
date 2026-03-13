package init.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import feign.FeignException;
import init.clients.AlumnosFeign;
import init.dtos.CredentialsDto;
import init.dtos.EstudianteDto;
import init.mappers.Mapeador;


@Service
public class EstudiantesServiceImpl implements EstudiantesService , InitializingBean {
	

	@Autowired
	Mapeador mapeador;
	
	@Autowired
	AlumnosFeign alumnosFeign;
	
	@Value("${remote.url}")
	private String urlBase;
	@Value("${remote.user}")
	private String user;
	@Value("${remote.password}")
	private String pass;
	
	private String tokenRecibido;

	@Override
	public List<EstudianteDto> estudiantesRangoCalificacion(double min, double max) {

		return alumnosFeign.alumnos("Bearer "+tokenRecibido)
				.stream()
				.map(mapeador::alumnoToEstudiante)
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
	
		try {
			alumnosFeign.nuevoAlumno(mapeador.estudianteToAlumno(estudiante),"Bearer "+tokenRecibido);
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
		CredentialsDto credential = new CredentialsDto(user,pass);
		tokenRecibido = alumnosFeign.autenticar(credential);
	}

}
