package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Alumno;
import service.AlumnosService;

@Controller
public class AlumnosController {
	
	@Autowired
	AlumnosService alumnosService;
	
		
	@GetMapping("alumnosCurso")
	public String alumnosCurso(Model model,@RequestParam("curso") String curso) {
		model.addAttribute("cursos", alumnosService.getCursos());
		List<Alumno> alumnos = alumnosService.alumnosCurso(curso);
	    model.addAttribute("alumnos", alumnos);
		model.addAttribute("cursoSeleccionado", curso);
		return "consulta";
	}
	
	@PostMapping("alta")
	public String nuevoAlumno(Model model,Alumno alumno) {
		if (alumnosService.nuevoAlumno(alumno)) {
			model.addAttribute("mensaje","Alumno añadido correctamente");
			
		}else {
			model.addAttribute("mensaje","No añadido, Alumno duplicado: No se puede repetir nombre/curso!!");
		}
		return "mensaje";
	}
	
	@GetMapping("goNuevo")
	public String nuevo(Model model) {
		model.addAttribute("cursos", alumnosService.getCursos());
		return "nuevo";
	}
	
	@GetMapping({"/","goMenu"})
	public String menu() {
		return "menu";
	}
	
	@GetMapping("goConsulta")
	public String consulta(Model model) {
	    // Obtenemos la lista de cursos únicos desde el repositorio
	    List<String> cursos = alumnosService.getCursos();
	    // Los pasamos a la vista con el nombre "cursos"
	    model.addAttribute("cursos", cursos);
	    return "consulta";
	}
	
	@PostMapping("eliminar")
	public String eliminarAlumno(Model model,@RequestParam("nombre") String nombre,@RequestParam("curso") String curso) {
		if (alumnosService.eliminarAlumno(nombre, curso)) {
			model.addAttribute("mensaje","Alumno eliminado correctamente");
			
		}else {
			model.addAttribute("mensaje","No eliminado, Alumno no encontrado!!");
		}
		return "mensaje";
	}
		

}
