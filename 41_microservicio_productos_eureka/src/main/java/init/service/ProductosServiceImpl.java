package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.ProductoDto;
import init.mappers.Mapeador;
import init.model.Producto;
import init.repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService {
	
	@Autowired
	ProductosRepository productosRepository;
	
	@Autowired
	Mapeador mapeador;

	@Override
	public List<ProductoDto> productos() {
		return productosRepository.findAll()
				.stream()
				.map(p -> mapeador.productoEntityToDto(p))
				.toList();
	}

	@Override
	public ProductoDto getProducto(int codigoProducto) {
		return mapeador.productoEntityToDto(productosRepository.findById(codigoProducto).orElse(null));
	}

	@Override
	public ProductoDto altaProducto(ProductoDto producto) {
	    // findById devuelve Optional, no null. Usamos isEmpty() para verificar disponibilidad.
	    if (productosRepository.findById(producto.getCodigoProducto()).isEmpty()) {
	        Producto nuevoprod = productosRepository.save(mapeador.productoDtoToEntity(producto));
	        return mapeador.productoEntityToDto(nuevoprod);
	    }
	    return null; // Ya existe el código
	}

	@Override
	public ProductoDto actualizaStock(int codigoProducto, int unidades) {
	    Producto prod = productosRepository.findById(codigoProducto).orElse(null);
	    if (prod != null) {
	        int nuevoStock = prod.getStock() - unidades;
	        // Se corrige a >= 0 para permitir agotar el stock exactamente a cero
	        if (nuevoStock >= 0) {
	            prod.setStock(nuevoStock);
	            return mapeador.productoEntityToDto(productosRepository.save(prod));
	        }
	    }
	    return null; // Producto no existe o stock insuficiente
	}

}
