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
			sesion.setAttribute("usuario", cliente);
			model.addAttribute("temas", libreriaService.getTemasConLibros());
			return "libros";
		} else {
			model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
			return "mensaje";
		}
		
	}
	
	@PostMapping("/registro")
	public String registro(Model model, @RequestParam("cliente") ClienteDto nuevoCliente) {
		
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
	    model.addAttribute("temaAnterior", tema); // Pasamos el tema a la vista de edición
	    return "editar";
	}

	@PostMapping("/update")
	public String update(Model model, @ModelAttribute LibroDto libro, @RequestParam("temaAnterior") String tema) {
	    libreriaService.actualizaLibro(libreriaService.getLibroDtoByIsbn(libro.getIsbn()));
	    
	    // Al volver, filtramos por el tema que guardamos antes de editar
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
                ? libreriaService.getLibros().stream().map(lib -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList()
               		 	                         : libreriaService.getLibrosByTema(tema).stream().map(lib -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList();
	    
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
	    List<LibroDto> carrito = (List<LibroDto>) sesion.getAttribute("carrito");
	    if (carrito == null) {
	        carrito = new ArrayList<>();
	    }
	    carrito.add(libreriaService.getLibroDtoByIsbn(isbn));
	    sesion.setAttribute("carrito", carrito);

	    // 2. Filtrar libros para no perder la vista actual
	    List<LibroDto> libros = (tema == null || tema.isEmpty() || tema.equals("Todos")) 
                ? libreriaService.getLibros().stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList()
               		 	                         : libreriaService.getLibrosByTema(tema).stream().map(libro -> libreriaService.getLibroDtoByIsbn(libro.getIsbn())).toList();

	    // 3. ENVIAR TODO AL MODELO (Esto es lo que te faltaba)
	    model.addAttribute("libros", libros);
	    model.addAttribute("temas", libreriaService.getTemas()); // Imprescindible para el select
	    model.addAttribute("temaSeleccionado", tema);
	    model.addAttribute("carrito", carrito); // Enviamos el carrito para que se vea
	    
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
		model.addAttribute("temas", libreriaService.getTemas()); // Imprescindible para el select
		model.addAttribute("temaSeleccionado", tema);
		model.addAttribute("carrito", carrito); // Enviamos el carrito para que se vea
	    return "libros"; // Redirige a la página página de libros después de eliminar del carrito
	}
	
	@PostMapping("/comprar")
	public String comprar(Model model, HttpSession sesion, @RequestParam("tema") String tema) {
	    List<LibroDto> carrito = (List<LibroDto>) sesion.getAttribute("carrito");
	    if (carrito != null && !carrito.isEmpty()) {
	        ClienteDto usuario = (ClienteDto) sesion.getAttribute("usuario");
	        int idCliente = usuario.getIdCliente();
	        ventasService.nuevaVenta(idCliente, carrito);
	        sesion.removeAttribute("carrito"); // Vaciar el carrito después de la compra
	        sesion.invalidate(); // Cerrar sesión después de la compra
	        model.addAttribute("mensaje", "Compra realizada con éxito");
	    } else {
	        model.addAttribute("mensaje", "El carrito está vacío. Agrega libros antes de comprar.");
	    }
	    return "mensaje"; // Redirige a una página de mensaje después de la compra
	}
	
//	@GetMapping("/informe")
//	public String informe(@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin, Model model) {
//		System.out.println("Fecha Inicio: " + fechaInicio);
//		System.out.println("Fecha Fin: " + fechaFin);
//	    List<VentaDto> ventas = ventasService.getVentasEntreFechas(fechaInicio, fechaFin);
//	    model.addAttribute("ventas", ventas);
//	    return "informe";
//	}

}
