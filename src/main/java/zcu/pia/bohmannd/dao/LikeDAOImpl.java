package zcu.pia.bohmannd.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

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
			list = Collections.emptyList();
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
		this.entityManager.remove(entityManager.contains(l) ? l : entityManager.merge(l));
	}

	@Override
	public List<Like> listByStatus(Status status) {
		List<Like> list;
		try {
			list = this.entityManager
					.createQuery("SELECT l FROM Like l WHERE l.status = :status ORDER BY l.created_at", Like.class)
					.setParameter("status", status)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

	@Override
	public Like getExists(Like like) {
		Like l;
		try {
			l = this.entityManager
					.createQuery("SELECT l FROM Like l WHERE l.status = :status AND l.user = :user", Like.class)
					.setParameter("status", like.getStatus())
					.setParameter("user", like.getUser())
					.getSingleResult();
		} catch (NoResultException nre) {
			l = null;
		}
		return l;
	}

	@Override
	public List<Like> listByUser(User user) {
		List<Like> list;
		try {
			list = this.entityManager
					.createQuery("SELECT l FROM Like l WHERE l.user = :user ORDER BY l.created_at", Like.class)
					.setParameter("user", user)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

}
