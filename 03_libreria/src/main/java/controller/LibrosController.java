package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Libro;
import service.LibrosService;

@Controller
public class LibrosController {
	@Autowired
	LibrosService librosService;
	
	@GetMapping("buscar")
	public String buscarLibros(Model model,
			@RequestParam("isbn") String isbn) {
		Libro libro=librosService.buscarLibro(isbn);
		//guardar la lista en algún lugar donde pueda recogerlo
		//la página que va a generar la respuesta
		if (libro==null) {
			model.addAttribute("mensaje","No encontrado, ISBN no encontrado!!");
			return "mensaje";
		}
		model.addAttribute("libro", libro);
		return "datos";
		
	}
	
	@GetMapping("eliminar")
	public String eliminarLibro(Model model,
			@RequestParam("isbn") String isbn) {
		if (librosService.eliminarLibro(isbn)) {
			model.addAttribute("mensaje","Libro eliminado correctamente");
			
		}else {
			model.addAttribute("mensaje","No eliminado, ISBN no encontrado!!");
		}
		return "mensaje";
	}
	
	@PostMapping("alta")
	public String nuevoLibro(Model model,
			@RequestParam("isbn") String isbn, 
			@RequestParam("titulo") String titulo, 
			@RequestParam("autor") String autor,
			@RequestParam("precio") Long precio) {
	    Libro libro = new Libro(isbn,titulo,autor,precio);
		if (librosService.nuevoLibro(libro)) {
			model.addAttribute("mensaje","Libro añadido correctamente");
			
		}else {
			model.addAttribute("mensaje","No añadido, ISBN duplicado!!");
		}
		return "mensaje";
	}
	
	@GetMapping("irNuevo")
	public String nuevoLibro() {
		return "nuevo";
	}
	
	@GetMapping("irEliminar")
	public String eliminarLibro() {
		return "eliminar";
	}
	

	@GetMapping({"/","irMenu"})
	public String menu() {
		return "menu";
	}
	
	@GetMapping("irBuscar")
	public String irABuscar() {
	    return "buscar"; 
	}

}
