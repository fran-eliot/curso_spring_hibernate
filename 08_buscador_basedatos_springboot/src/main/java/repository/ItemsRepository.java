package repository;

import java.util.List;

import model.Item;

public interface ItemsRepository {
	
	void save(Item item);
	List<Item> findByTematica(String tematica);
	Item findFirstByUrl(String url);

}
