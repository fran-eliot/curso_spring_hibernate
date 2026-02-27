package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Item;
import service.ItemsService;

@Controller
public class ItemsController {
	@Autowired
	ItemsService itemsService;
	@GetMapping("buscar")
	public String buscarItems(Model model,@RequestParam("tematica") String tematica) {
		List<Item> items=itemsService.buscarPorTematica(tematica);
		//guardar la lista en algún lugar donde pueda recogerlo
		//la página que va a generar la respuesta
		model.addAttribute("listado", items);
		return "resultados";
		
	}
}
