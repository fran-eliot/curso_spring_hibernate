package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.ItemDto;
import init.service.ItemsService;

@RestController
public class ItemsController {
	@Autowired
	ItemsService itemsService;
	
	@GetMapping(value="items/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemDto>> buscarItems(@PathVariable("tematica") String tematica) {
		List<ItemDto> items=itemsService.buscarPorTematica(tematica);
		return new ResponseEntity<>(items, HttpStatus.OK);
		
	}
	
	@PostMapping(value="items",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaItem(@RequestBody ItemDto item) {
		
		if (itemsService.nuevoItem(item)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	   	
	}
	
	@DeleteMapping(value="items/{url}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemDto> borraItem(@PathVariable("url") String url) {
		return ResponseEntity.ok(itemsService.eliminarItem(url));
	}
	
	
}
