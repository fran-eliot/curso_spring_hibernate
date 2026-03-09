package init.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping({"/","irLogin"})
	public String irLogin() {
		return "login";
	}
	
	@GetMapping("/irRegistro")
	public String irRegistro() {
		return "registro";
	}

}
