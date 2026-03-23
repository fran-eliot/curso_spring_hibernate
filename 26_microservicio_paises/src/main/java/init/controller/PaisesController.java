package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Pais;
import init.service.PaisesService;

@CrossOrigin("*")
@RestController
public class PaisesController {

	@Autowired
	PaisesService service;

	// Ruta final: /restcountries/continentes
	@GetMapping(value = "continentes")
	public ResponseEntity<List<String>> getContinentes() {
		System.out.println("Enviando contienntes...");
		return ResponseEntity.ok(service.getContinentes());
	}

	// Ruta final: /restcountries/paises/{continente}
	@GetMapping(value = "paises/{continente}", produces = "application/json")
	public ResponseEntity<List<Pais>> getPaises(@PathVariable("continente") String continente) {
		System.out.println("Enviando paisess...");
		return ResponseEntity.ok(service.getPaisesPorContinente(continente));
	}
}