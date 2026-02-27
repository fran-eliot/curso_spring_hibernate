package repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Item;

@Repository
public class ItemsRepositoryImpl implements ItemsRepository {
	@PersistenceContext
	EntityManager eManager;

	@Transactional
	@Override
	public void save(Item item) {
		// TODO Auto-generated method stub
		eManager.persist(item);
	}

	@Override
	public List<Item> findByTematica(String tematica) {
		String jpql = "select i from Item i where i.tematica=?1";
		TypedQuery<Item> query=eManager.createQuery(jpql,Item.class);
		query.setParameter(1, tematica);
		return query.getResultList();
	}

	@Override
	public Item findFirstByUrl(String url) {
		String jpql = "select i from Item i where i.url=?1";
		TypedQuery<Item> query=eManager.createQuery(jpql,Item.class);
		query.setParameter(1, url);
		return query.getResultList().get(0);
	}

}
