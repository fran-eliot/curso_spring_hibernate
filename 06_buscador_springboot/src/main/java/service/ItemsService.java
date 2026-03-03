package service;

import java.util.List;

import model.Item;

public interface ItemsService {
	List<Item> buscarPorTematica(String tematica);
	boolean nuevoItem(Item item);
}
