package init.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	private String urlBase="http://localhost:8005/escuela";

	@Override
	public List<EstudianteDto> estudiantesRangoCalificacion(double min, double max) {
		return Arrays.stream(restClient.get()
				.uri(urlBase+"alumnos")
				.retrieve()
				.body(Alumno[].class))
				.map(mapeador::alumnoToEstudiante)
				.toList();
	}

	@Override
	public boolean alta(EstudianteDto estudiante) {
		// TODO Auto-generated method stub
		return false;
	}

}
