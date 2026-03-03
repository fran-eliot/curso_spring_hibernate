package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Item;
@Service
public class ItemsServiceImpl implements ItemsService {
	static List<Item> direcciones=new ArrayList<>(Arrays.asList(new Item("http://www.amazon.es","libros","web de libros y más cosas"),
			new Item("http://www.fnac.es","libros","libreria completa"),
			new Item("http://www.travel.es","viajes","viajes por el mundo"),
			new Item("http://www.game.es","juegos","el mundo del juego"),
			new Item("http://www.fly.com","viajes","vuelos a todos los destinos"),
			new Item("http://www.casadellibro.es","libros","libros de todos los temas")
			));
	@Override
	public List<Item> buscarPorTematica(String tematica) {
		/*solucion 1: programación convencional
		//lista vacía donde guardaremos los items que coincidan
		List<Item> resultados=new ArrayList<>();
		//recorremos la lista y preguntamos uno por uno si el item
		//coincide con esa temática
		for(Item it:direcciones) {
			if(it.getTematica().equals(tematica)) {
				resultados.add(it);
			}
		}
		return resultados; 
		*/
		
		//solución 2: programación funcional
		return direcciones.stream()
				.filter(it->it.getTematica().equals(tematica))
				.toList();
	}
	@Override
	public boolean nuevoItem(Item item) {
		//añade el nuevo Item a lista. No se admiten urls duplicadas. Antes de añadir
		//hay que comprobar que no hay ningún otro Item con esa url. En caso de que lo haya
		//no lo añade y devuelve false.
		//si se consigue añadir, devolverá true
		for(Item it:direcciones) {
			if(it.getUrl().equals(item.getUrl())) {
				return false;
			}
		}
		return direcciones.add(item);
	}

}
