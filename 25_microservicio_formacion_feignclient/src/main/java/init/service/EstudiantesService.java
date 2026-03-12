package init.service;

import java.util.List;

import org.springframework.stereotype.Service;

import init.dtos.EstudianteDto;


public interface EstudiantesService {
	List<EstudianteDto> estudiantesRangoCalificacion(double min, double max);
	boolean altaEstudiante(EstudianteDto estudiante);

}
