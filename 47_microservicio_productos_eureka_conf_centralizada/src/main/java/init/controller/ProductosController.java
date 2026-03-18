package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import init.dtos.ProductoDto;
import init.service.ProductosService;

@RestController
public class ProductosController {
	
	@Autowired
	ProductosService productosService;
	
	@GetMapping("productos")
	ResponseEntity<List<ProductoDto>> getProductos(){
		List<ProductoDto> productos= productosService.productos();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("productos/{codigoProducto}")
	ResponseEntity<ProductoDto> buscaProducto(@PathVariable int codigoProducto){
		ProductoDto producto = productosService.getProducto(codigoProducto);
		if (producto!=null) {
			return new ResponseEntity<>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("productos")
	ResponseEntity<ProductoDto> nuevoProducto(@RequestBody ProductoDto nuevoproducto){
		ProductoDto producto = productosService.altaProducto(nuevoproducto);
		if (producto!=null) {
			return new ResponseEntity<>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("productos/{codigoProducto}/{unidades}")
	ResponseEntity<ProductoDto> actualizaStock(@PathVariable int codigoProducto, @PathVariable int unidades){
		ProductoDto producto = productosService.actualizaStock(codigoProducto, unidades);
		if (producto!=null) {
			return new ResponseEntity<>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	

}
