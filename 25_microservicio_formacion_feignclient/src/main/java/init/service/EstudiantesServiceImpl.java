package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;
import init.clients.AlumnosFeign;
import init.dtos.EstudianteDto;
import init.mappers.Mapeador;


@Service
public class EstudiantesServiceImpl implements EstudiantesService {
	

	@Autowired
	Mapeador mapeador;
	
	@Autowired
	AlumnosFeign alumnosFeign;

	@Override
	public List<EstudianteDto> estudiantesRangoCalificacion(double min, double max) {

		return alumnosFeign.alumnos()
				.stream()
				.map(mapeador::alumnoToEstudiante)
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(EstudianteDto estudiante) {
	
		try {
			alumnosFeign.nuevoAlumno(mapeador.estudianteToAlumno(estudiante));
			return true;
		}catch(FeignException ex) {
			if (ex.status()==409) {
				return false;
			}
			throw ex;
		}
		
	}

}
