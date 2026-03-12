package init.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import init.model.Alumno;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name="alumnosFeign",url="${remote.url}")
public interface AlumnosFeign {
	
	@GetMapping("alumnos")
	List<Alumno> alumnos();
	
	@PostMapping("alumnos")
	void nuevoAlumno(@RequestBody Alumno alumno);
	
	

}
