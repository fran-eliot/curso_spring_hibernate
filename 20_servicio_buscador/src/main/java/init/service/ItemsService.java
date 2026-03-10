package init.service;

import java.util.List;

import init.dtos.ItemDto;


public interface ItemsService {
	List<ItemDto> buscarPorTematica(String tematica);
	boolean nuevoItem(ItemDto item);
	ItemDto eliminarItem(String url);
}
