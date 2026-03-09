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

import init.dtos.ClienteDto;
import init.dtos.LibroDto;
import init.dtos.TemaDto;
import init.model.Cliente;
import init.service.ClientesService;
import init.service.LibreriaService;
import init.service.VentasService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LibreriaController {
	
	@Autowired
	private ClientesService clientesService;
	
	@Autowired
	private LibreriaService libreriaService;
	
	@Autowired
	private VentasService ventasService;
	
	@PostMapping("/login")
	public String login(HttpSession sesion,Model model, @RequestParam("usuario") String usuario, @RequestParam("password") String password) {

		ClienteDto cliente = clientesService.autenticarUsuario(usuario, password);
		if (cliente != null) {
			sesion.setAttribute("cliente", cliente);
			model.addAttribute("temas", libreriaService.getTemasConLibros());
			return "libros";
		} else {
			model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
			return "mensaje";
		}
		
	}
	
	@PostMapping("/registro")
	public String registro(Model model, @RequestParam("usuario") String usuario, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("telefono") int telefono) {
		ClienteDto nuevoCliente = new ClienteDto(usuario, password, email, telefono);
		if (clientesService.guardarCliente(nuevoCliente)) {
			
			return "login";
		} else {
			model.addAttribute("mensaje", "El usuario ya existe. Por favor, elige otro nombre de usuario.");
			return "mensaje";
		}
	}
	
	
	@GetMapping("/librosTema")
	public String librosTema(HttpSession sesion, Model model, @RequestParam("tema") String tema) {
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	                         ? libreriaService.getLibros().stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList()
	                        		 	                         : libreriaService.getLibrosByTema(tema).stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList();
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemasConLibros());
	    model.addAttribute("temaSeleccionado", tema);
	    model.addAttribute("carrito", sesion.getAttribute("carrito")); // Añadir esta línea
	    return "libros";
	}
	
	@GetMapping("/goLibros")
	public String goLibros(Model model) {
		List<TemaDto> temas = libreriaService.getTemasConLibros();
	    model.addAttribute("temas", temas);
		return "libros";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(Model model, @RequestParam("isbn") int isbn, @RequestParam("tema") String tema) {
	    libreriaService.removeByIsbn(isbn);
	    
	    // Recuperar solo los libros del tema que teníamos
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
                ? libreriaService.getLibros().stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList()
               		 	                         : libreriaService.getLibrosByTema(tema).stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList();
	    
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemasConLibros());
	    model.addAttribute("temaSeleccionado", tema);
	    return "libros";
	}

	@GetMapping("/editar")
	public String editar(Model model, @RequestParam("isbn") int isbn, @RequestParam("tema") String tema) {
		LibroDto libro = libreriaService.getLibroDtoByIsbn(isbn);
	    model.addAttribute("libro", libro);
	    model.addAttribute("temaAnterior", tema); 
	    // Añadimos esto para llenar el desplegable en la vista de edición
	    model.addAttribute("temas", libreriaService.getTemasConLibros()); 
	    return "editar";
	}

	@PostMapping("/update")
	public String update(Model model, @ModelAttribute LibroDto libroDto, @RequestParam("temaAnterior") String tema, HttpSession sesion) {
	    // 1. IMPORTANTE: Usamos el DTO que viene del formulario, no volvemos a consultar el viejo
		
	    libreriaService.actualizaLibro(libroDto); 
	    
	    // 2. Corregir el Stream: usar 'lib' (la variable de la lambda) no 'libro'
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	            ? libreriaService.getLibros().stream().map(lib -> libreriaService.getLibroDtoByIsbn(lib.getIsbn())).toList()
	            : libreriaService.getLibrosByTema(tema).stream().map(lib -> libreriaService.getLibroDtoByIsbn(lib.getIsbn())).toList();
	    
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemasConLibros()); // Usa temas con libros para ser consistente
	    model.addAttribute("temaSeleccionado", tema);
	    model.addAttribute("carrito", sesion.getAttribute("carrito"));
	    return "libros";
	}
	
	@PostMapping("/agregarCarrito")
	public String agregarCarrito(HttpSession sesion, Model model, 
	                            @RequestParam("isbn") int isbn, 
	                            @RequestParam("tema") String tema) {
	    
	    // 1. Obtener y actualizar carrito en la sesión
	    List<LibroDto> carrito = (List<LibroDto>) sesion.getAttribute("carrito");
	    if (carrito == null) {
	        carrito = new ArrayList<>();
	    }
	    carrito.add(libreriaService.getLibroDtoByIsbn(isbn));
	    sesion.setAttribute("carrito", carrito);

	    // 2. Filtrar libros para mantener la vista actual (Lógica idéntica a librosTema)
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
	            ? libreriaService.getLibros().stream().map(l -> libreriaService.getLibroDtoByIsbn(l.getIsbn())).toList()
	            : libreriaService.getLibrosByTema(tema).stream().map(l -> libreriaService.getLibroDtoByIsbn(l.getIsbn())).toList();

	    // 3. ENVIAR AL MODELO
	    model.addAttribute("libros", libros);
	    // CAMBIO AQUÍ: Usar getTemasConLibros() para que el objeto tenga la propiedad .tema
	    model.addAttribute("temas", libreriaService.getTemasConLibros()); 
	    model.addAttribute("temaSeleccionado", tema);
	    model.addAttribute("carrito", carrito);
	    
	    return "libros";
	}
	
	@PostMapping("/eliminarCarrito")
	public String eliminarCarrito(Model model, @RequestParam("posicion") int posicion, @RequestParam("tema") String tema, HttpSession sesion) {
		List<LibroDto> carrito = (List<LibroDto>) sesion.getAttribute("carrito");
	    if (carrito != null) {
	        carrito.remove(posicion);
	    }
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
                ? libreriaService.getLibros().stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList()
               		 	                         : libreriaService.getLibrosByTema(tema).stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList();

		// 3. ENVIAR TODO AL MODELO (Esto es lo que te faltaba)
		model.addAttribute("libros", libros);
		model.addAttribute("temas", libreriaService.getTemasConLibros()); // Imprescindible para el select
		model.addAttribute("temaSeleccionado", tema);
		model.addAttribute("carrito", carrito); // Enviamos el carrito para que se vea
	    return "libros"; // Redirige a la página página de libros después de eliminar del carrito
	}
	
	@PostMapping("/comprar")
	public String comprar(HttpSession sesion, Model model) {
	    ClienteDto cliente = (ClienteDto) sesion.getAttribute("cliente");
	    List<LibroDto> carrito = (List<LibroDto>) sesion.getAttribute("carrito");
	   
	    if (cliente != null && carrito != null && !carrito.isEmpty()) {
	    	
	    	
	    	// Ejecutamos la lógica de persistencia
	    	ventasService.nuevaVenta(cliente.getIdCliente(), carrito);
	        
	        // Vaciamos el carrito tras la compra exitosa
	        sesion.setAttribute("carrito", new ArrayList<LibroDto>());
	        
	        model.addAttribute("mensaje", "¡Compra realizada con éxito! Gracias por su confianza.");
	    } else {
	        model.addAttribute("mensaje", "Error al procesar la compra. El carrito está vacío o no se ha identificado el usuario.");
	    }
	    
	    return "mensaje";
	}
	

}
