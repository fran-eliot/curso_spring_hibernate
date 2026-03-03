package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		    model.addAttribute("mensaje", "Alumno añadido correctamente");
		    model.addAttribute("clase", "success"); // Clase para éxito
		} else {
		    model.addAttribute("mensaje", "No añadido. ¡Alumno duplicado!");
		    model.addAttribute("clase", "error");   // Clase para error
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
//		if (alumnosService.eliminarAlumno(nombre, curso)) {
//			model.addAttribute("mensaje","Alumno eliminado correctamente");
//			
//		}else {
//			model.addAttribute("mensaje","No eliminado, Alumno no encontrado!!");
//		}
//		return "mensaje";
		alumnosService.eliminarAlumno(nombre, curso);
		model.addAttribute("cursos", alumnosService.getCursos());
		List<Alumno> alumnos = alumnosService.alumnosCurso(curso);
	    model.addAttribute("alumnos", alumnos);
		model.addAttribute("cursoSeleccionado", curso);
		return "consulta";
	}
	
	@GetMapping("/editar")
	public String editarAlumno(Model model, @RequestParam("idAlumno") int id) {
	    Alumno alumno = alumnosService.findById(id);
	    model.addAttribute("alumno", alumno);
	    // Cargamos los cursos por si quieres un desplegable en la edición
	    model.addAttribute("cursos", alumnosService.getCursos());
	    return "editar";
	}

	@PostMapping("/update")
	public String updateAlumno(Model model, @ModelAttribute Alumno alumno) {
	    // 1. Persistimos los cambios
	    alumnosService.update(alumno);
	    
	    // 2. Preparamos los datos para volver a la pantalla de consulta filtrada
	    model.addAttribute("cursos", alumnosService.getCursos());
	    model.addAttribute("alumnos", alumnosService.alumnosCurso(alumno.getCurso()));
	    model.addAttribute("cursoSeleccionado", alumno.getCurso());
	    
	    // Retornamos la vista de consulta con la lista actualizada
	    return "consulta"; 
	}
		

}
