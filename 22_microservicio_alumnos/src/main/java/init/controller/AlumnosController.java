package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.AlumnoDto;
import init.service.AlumnosService;

@RestController
public class AlumnosController {
	
	@Autowired
	AlumnosService alumnosService;
	
			
	@GetMapping(value="alumnos/{curso}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> alumnosCurso(@PathVariable("curso") String curso) {
		List<AlumnoDto> alumnos = alumnosService.alumnosCurso(curso);

		return new ResponseEntity<>(alumnos, HttpStatus.OK);
	}
	
	@GetMapping(value="alumnos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> getAlumnos(){
		List<AlumnoDto> alumnos = alumnosService.todosAlumnos();

		return new ResponseEntity<>(alumnos, HttpStatus.OK);
	}
	
	@PostMapping(value="alumnos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> nuevoAlumno(@RequestBody AlumnoDto alumno) {
		if (alumnosService.nuevoAlumno(alumno)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getCursos(){
		List<String> cursos = alumnosService.getCursos();
		return new ResponseEntity<>(cursos,HttpStatus.OK);
	}
	
	@DeleteMapping(value="alumnos/{email}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> eliminaPrimeroPorEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(alumnosService.eliminarPorEmail(email)) ;
		
	}
	

	
	
}