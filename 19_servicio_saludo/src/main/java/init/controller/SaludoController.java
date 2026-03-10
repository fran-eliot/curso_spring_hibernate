package init.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Ficha;

@RestController
public class SaludoController {
	
	@GetMapping(value="saludar",produces="text/plain")
	public String saludar() {
		return "Bienvenido a mi servicio REST";
	}
	
	@GetMapping(value="saludar/{name}",produces=MediaType.TEXT_PLAIN_VALUE)
	public String saludar(@PathVariable String name) {
		return "Bienvenido a Rest. Sr. "+name;
	}
	
	@GetMapping(value="saludar-completo",produces=MediaType.TEXT_PLAIN_VALUE)
	public String saudar(@RequestParam String name, @RequestParam int edad) {
		return "Bienvenido a Rest. Sr "+name+" tienes "+edad+" años";
	}
	
	@GetMapping(value="ficha",produces=MediaType.APPLICATION_JSON_VALUE)
	public Ficha ficha (@RequestParam String name) {
		return new Ficha(name,"test@gmail.com",22);
	}
	
	@PostMapping(value="ficha",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void nuevaFicha(@RequestBody Ficha ficha) {
		System.out.println(ficha.getNombre()+"-"+ficha.getEmail()+"-"+ficha.getEdad());
	}

}
