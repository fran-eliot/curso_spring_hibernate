package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.dtos.CursoDto;
import init.service.EscuelaService;

@Controller
public class EscuelaController {
	
	@Autowired
	EscuelaService escuelaService;
	
	@GetMapping("/")
	public String incial(Model model) {
		model.addAttribute("cursos",escuelaService.getCursos());
		return "matriculas";
		
	}
	
	@GetMapping("matriculas")
	public String verMatriculas(Model model, @RequestParam int codCurso) {
		model.addAttribute("matriculas", escuelaService.getMatriculasByCurso(codCurso));
		model.addAttribute("cursos",escuelaService.getCursos());
		model.addAttribute("cursoSeleccionado",codCurso);
		return "matriculas";
	}
	
	@GetMapping("prepararMatricula")
	public String prepararMatricula(Model model) {
	    model.addAttribute("cursos", escuelaService.getCursos());
	    return "nueva_matricula";
	}

	@PostMapping("matricular")
	public String realizarMatricula(@RequestParam int codCurso, @RequestParam String dni, Model model) {
		System.out.println("DEBUG: Matriculando DNI: " + dni + " en Curso ID: " + codCurso);
	    boolean exito = escuelaService.matricular(codCurso,dni);
	    if (exito) {
	        return "redirect:/"; // Redirige a la página principal tras el éxito
	    } else {
	        model.addAttribute("error", "No se pudo realizar la matrícula. Verifique el DNI y el Curso.");
	        model.addAttribute("cursos", escuelaService.getCursos());
	        return "nueva_matricula";
	    }
	}
	
	

}
