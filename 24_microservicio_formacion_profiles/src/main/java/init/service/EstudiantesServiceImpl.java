package init.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.dtos.EstudianteDto;
import init.mappers.Mapeador;
import init.model.Alumno;

@Service
public class EstudiantesServiceImpl implements EstudiantesService {
	
	@Autowired
	RestClient restClient;
	
	@Autowired
	Mapeador mapeador;
	
	@Value("${remote.url}")
	private String urlBase;

	@Override
	public List<EstudianteDto> estudiantesRangoCalificacion(double min, double max) {
		return Arrays.stream(restClient.get()
				.uri(urlBase+"/alumnos")
				.retrieve()
				.body(Alumno[].class))
				.map(mapeador::alumnoToEstudiante)
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
		try {
			restClient.post()
			.uri(urlBase+"/alumnos")
			.contentType(MediaType.APPLICATION_JSON)
			.body(mapeador.estudianteToAlumno(estudiante))
			.retrieve()
			.toBodilessEntity();
			return true;
		}catch(HttpClientErrorException ex) {
			if (ex.getStatusCode()==HttpStatus.CONFLICT) {
				return false;
			}
			throw ex;
		}
		
	}

}
