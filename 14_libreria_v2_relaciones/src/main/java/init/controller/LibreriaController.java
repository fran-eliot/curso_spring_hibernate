package init.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.model.Libro;
import init.service.ClientesService;
import init.service.LibreriaService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LibreriaController {
	
	@Autowired
	private ClientesService clientesService;
	
	@Autowired
	private LibreriaService libreriaService;
	
	@PostMapping("/login")
	public String login(HttpSession sesion,Model model, @RequestParam("usuario") String usuario, @RequestParam("password") String password) {

		if (clientesService.login(usuario, password)) {
			//sesion.setAttribute("usuario", usuario);
			model.addAttribute("temas", libreriaService.getTemas());
			return "libros";
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
	
	
	@GetMapping("/librosTema")
	public String librosTema(HttpSession sesion, Model model, @RequestParam("tema") String tema) {
	    List<Libro> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	                         ? libreriaService.getLibros() 
	                         : libreriaService.getLibrosByTema(tema);
	    
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemas());
	    model.addAttribute("temaSeleccionado", tema);
	    model.addAttribute("carrito", sesion.getAttribute("carrito")); // Añadir esta línea
	    return "libros";
	}
	
	@GetMapping("/goLibros")
	public String goLibros(Model model) {
		List<String> temas = libreriaService.getTemas();
	    model.addAttribute("temas", temas);
		model.addAttribute("temas", libreriaService.getTemas());
		return "libros";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(Model model, @RequestParam("isbn") int isbn, @RequestParam("tema") String tema) {
	    libreriaService.removeByIsbn(isbn);
	    
	    // Recuperar solo los libros del tema que teníamos
	    List<Libro> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	                         ? libreriaService.getLibros() 
	                         : libreriaService.getLibrosByTema(tema);
	    
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemas());
	    model.addAttribute("temaSeleccionado", tema);
	    return "libros";
	}

	@GetMapping("/editar")
	public String editar(Model model, @RequestParam("isbn") int isbn, @RequestParam("tema") String tema) {
	    Libro libro = libreriaService.getLibroByIsbn(isbn);
	    model.addAttribute("libro", libro);
	    model.addAttribute("temaAnterior", tema); // Pasamos el tema a la vista de edición
	    return "editar";
	}

	@PostMapping("/update")
	public String update(Model model, @ModelAttribute Libro libro, @RequestParam("temaAnterior") String tema) {
	    libreriaService.updateLibro(libro);
	    
	    // Al volver, filtramos por el tema que guardamos antes de editar
	    List<Libro> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	                         ? libreriaService.getLibros() 
	                         : libreriaService.getLibrosByTema(tema);
	    
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemas());
	    model.addAttribute("temaSeleccionado", tema);
	    return "libros";
	}
	
	@PostMapping("/agregarCarrito")
	public String agregarCarrito(HttpSession sesion, Model model, 
	                            @RequestParam("isbn") int isbn, 
	                            @RequestParam("tema") String tema) {
	    
	    // 1. Obtener y actualizar carrito en la sesión
	    List<Libro> carrito = (List<Libro>) sesion.getAttribute("carrito");
	    if (carrito == null) {
	        carrito = new ArrayList<>();
	    }
	    carrito.add(libreriaService.getLibroByIsbn(isbn));
	    sesion.setAttribute("carrito", carrito);

	    // 2. Filtrar libros para no perder la vista actual
	    List<Libro> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	                         ? libreriaService.getLibros() 
	                         : libreriaService.getLibrosByTema(tema);

	    // 3. ENVIAR TODO AL MODELO (Esto es lo que te faltaba)
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemas()); // Imprescindible para el select
	    model.addAttribute("temaSeleccionado", tema);
	    model.addAttribute("carrito", carrito); // Enviamos el carrito para que se vea
	    
	    return "libros";
	}
	
	@PostMapping("/eliminarCarrito")
	public String eliminarCarrito(Model model, @RequestParam("posicion") int posicion, @RequestParam("tema") String tema, HttpSession sesion) {
		List<Libro> carrito = (List<Libro>) sesion.getAttribute("carrito");
	    if (carrito != null) {
	        carrito.remove(posicion);
	    }
	    List<Libro> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
                ? libreriaService.getLibros() 
                : libreriaService.getLibrosByTema(tema);

		// 3. ENVIAR TODO AL MODELO (Esto es lo que te faltaba)
		model.addAttribute("libros", libros);
		model.addAttribute("temas", libreriaService.getTemas()); // Imprescindible para el select
		model.addAttribute("temaSeleccionado", tema);
		model.addAttribute("carrito", carrito); // Enviamos el carrito para que se vea
	    return "libros"; // Redirige a la página página de libros después de eliminar del carrito
	}

}
