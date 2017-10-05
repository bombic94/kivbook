package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.persistence.EntityManager;
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
		List<Like> list = this.entityManager
				.createQuery("SELECT l FROM Like l", Like.class)
				.getResultList();
		return list;
	}

	@Override
	public Like getById(Integer id) {
		Like l = this.entityManager
				.createQuery("SELECT l FROM Like l WHERE l.id = :id", Like.class)
				.setParameter("id", id)
				.getSingleResult();
		return l;
	}

	@Override
	public void delete(Like l) {
		this.entityManager.remove(l);
	}

	@Override
	public List<Like> listByStatus(Status status) {
		List<Like> list = this.entityManager
				.createQuery("SELECT l FROM Like l WHERE l.status_id = :status_id ORDER BY l.created_at", Like.class)
				.setParameter("status_id", status.getId())
				.getResultList();
		return list;
	}

}
