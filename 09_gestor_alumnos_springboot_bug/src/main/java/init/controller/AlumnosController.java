package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.model.Alumno;
import init.service.AlumnosService;

@Controller
public class AlumnosController {
	
	@Autowired
	AlumnosService alumnosService;
	
	@PostMapping("alta")
	/*public String alta(Model model,
			@RequestParam("nombre") String nombre,
			@RequestParam("curso") String curso,
			@RequestParam("email") String email,
			@RequestParam("nota") double nota) {
		Alumno alumno=new Alumno(nombre, curso, email, nota);*/
	  public String alta(Model model,@ModelAttribute Alumno alumno) {
		if(alumnosService.guardar(alumno)) {
			model.addAttribute("mensaje", "Alumno creado correctamente!");
		}else {
			model.addAttribute("mensaje", "No se puede repetir nombre/curso!!");
		}
		return "mensaje";
	}
	@GetMapping("consulta")
	public String consultar(Model model) {
		model.addAttribute("cursos", alumnosService.cursos());
		return "consulta";
	}
	@GetMapping("alumnos")
	public String verAlumnos(Model model,@RequestParam("curso") String curso) {
		model.addAttribute("cursos", alumnosService.cursos());
		model.addAttribute("alumnos", alumnosService.alumnosCurso(curso));
		return "consulta";
	}
	@GetMapping("nuevo")
	public String nuevo(Model model) {
		model.addAttribute("cursos", alumnosService.cursos());
		return "nuevo";
	}
	@GetMapping({"/","goMenu"})
	public String menu() {
		return "menu";
	}
	
	@GetMapping("eliminar")
	public String eliminar(@RequestParam("idAlumno") int idAlumno) {
		alumnosService.eliminar(idAlumno);
		return "menu";
	}
}
