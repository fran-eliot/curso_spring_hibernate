package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.service.ClientesService;

@Controller
public class ClientesController {
	
	@Autowired
	private ClientesService clientesService;
	
	@PostMapping("/login")
	public String login(Model model, @RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		if (clientesService.login(usuario, password)) {
			model.addAttribute("mensaje", "Bienvenido, " + usuario + "!");
			return "mensaje";
		} else {
			model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
			return "mensaje";
		}
	}
	
	@PostMapping("/registro")
	public String registro(Model model, @RequestParam("usuario") String usuario, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("telefono") int telefono) {
		if (clientesService.registro(usuario, password, email, telefono)) {
			
			return "login";
		} else {
			model.addAttribute("mensaje", "El usuario ya existe. Por favor, elige otro nombre de usuario.");
			return "mensaje";
		}
	}
	
	@GetMapping({"/","irLogin"})
	public String irLogin() {
		return "login";
	}
	
	@GetMapping("/irRegistro")
	public String irRegistro() {
		return "registro";
	}
	

}
