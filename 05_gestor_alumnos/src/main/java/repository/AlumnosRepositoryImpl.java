package repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.Alumno;


@Repository
public class AlumnosRepositoryImpl implements AlumnosRepository {
	
	@PersistenceContext
	EntityManager eManager;

	@Transactional
	@Override
	public void save(Alumno alumno) {
		eManager.persist(alumno);

	}

	@Override
	public List<Alumno> findByCurso(String curso) {
		String jpql = "select a from Alumno a where a.curso=?1";
		TypedQuery<Alumno> query=eManager.createQuery(jpql,Alumno.class);
		query.setParameter(1, curso);
		return query.getResultList();
	}

	@Override
	public Alumno findFirstByNombreAndCurso(String nombre, String curso) {
		String jpql = "select a from Alumno a where a.nombre=?1 and a.curso=?2";
		TypedQuery<Alumno> query=eManager.createQuery(jpql,Alumno.class);
		query.setParameter(1, nombre);
		query.setParameter(2, curso);
		List<Alumno> resultado = query.getResultList();
		return resultado.size()>0?resultado.get(0):null;
	}

	@Override
	public List<String> findAllCursos() {
		String jpql = "select a.curso from Alumno a";
		TypedQuery<String> query=eManager.createQuery(jpql,String.class);
		List<String> resultado= query.getResultList();
		return resultado.stream().distinct().toList();
	}

	@Transactional
	@Override
	public boolean eliminarAlumno(String nombre, String curso) {
		String jpql = "delete from Alumno a where a.nombre=?1 and a.curso=?2";
		int filasEliminadas = eManager.createQuery(jpql)
				.setParameter(1, nombre)
				.setParameter(2, curso)
				.executeUpdate();
		return filasEliminadas>0;
	}

}
