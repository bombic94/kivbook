package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;

@Repository("likeDAO")
public class LikeDAOImpl implements LikeDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Like save(Like l) {
		if(l.isNew()) {
            this.entityManager.persist(l);
            return l;
        } else {
            this.entityManager.merge(l);
            return l;
        }
	}

	@Override
	public List<Like> list() {
		List<Like> list;
		try {
			list = this.entityManager
					.createQuery("SELECT l FROM Like l", Like.class)
					.getResultList();
		} catch (NoResultException nre) {
			list = null;
		}
		return list;
	}

	@Override
	public Like getById(Integer id) {
		Like l;
		try {
			l = this.entityManager
					.createQuery("SELECT l FROM Like l WHERE l.id = :id", Like.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			l = null;
		}
		return l;
	}

	@Override
	public void delete(Like l) {
		this.entityManager.remove(l);
	}

	@Override
	public List<Like> listByStatus(Status status) {
		List<Like> list;
		try {
			list = this.entityManager
					.createQuery("SELECT l FROM Like l WHERE l.status_id = :status_id ORDER BY l.created_at", Like.class)
					.setParameter("status_id", status.getId())
					.getResultList();
		} catch (NoResultException nre) {
			list = null;
		}
		return list;
	}

}
