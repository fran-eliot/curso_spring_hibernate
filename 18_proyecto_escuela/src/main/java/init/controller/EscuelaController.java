package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "matriculas";
	}
	
	

}
