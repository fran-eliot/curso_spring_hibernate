package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.EstudianteDto;
import init.service.EstudiantesService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class EstudiantesController {
	@Autowired
	EstudiantesService estudiantesService;
	
	@GetMapping("estudiantes")
	public ResponseEntity<List<EstudianteDto>> estudiantesCualifacion(
	    @RequestParam("min") double min, 
	    @RequestParam("max") double max) {
	    
	    return ResponseEntity.ok(estudiantesService.estudiantesRangoCalificacion(min, max));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode="201", description="El estudiante se ha añadido correctamente"),
		@ApiResponse(responseCode="409", description="No se ha creado el estudiante, porque no se puede "),
	})
	
	@PostMapping("estudiantes")
	public ResponseEntity<Void> altaEstudiante(@RequestBody EstudianteDto estudiante){
		if (estudiantesService.altaEstudiante(estudiante)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

}
