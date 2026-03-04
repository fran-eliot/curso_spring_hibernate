package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import model.Item;

public interface ItemsRepository extends JpaRepository<Item, Integer> {

	List<Item> findByTematica(String tematica);
	Item findFirstByUrl(String url);
	
	@Transactional
	@Modifying
	void deleteByUrl(String url);
	
	@Query("SELECT i FROM Item i WHERE i.descripcion LIKE %?1%")
	List<Item> findAllLikeDescripcion(String texto);
	//List<Item> findByDescripcionLike(String texto);
	
	//metodo que devuelve el total de itms de una determinada tematica
	@Query(value = "SELECT COUNT(*) FROM items WHERE tematica = ?1", nativeQuery = true)
	int countByTematica(String tematica);

}
