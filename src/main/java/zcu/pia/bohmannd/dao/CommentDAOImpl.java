package zcu.pia.bohmannd.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Status;

@Repository("commentDAO")
public class CommentDAOImpl implements CommentDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Comment save(Comment c) {
		if(c.isNew()) {
            this.entityManager.persist(c);
            return c;
        } else {
            this.entityManager.merge(c);
            return c;
        }
	}

	@Override
	public List<Comment> list() {
		List<Comment> list;
		try {
			list = this.entityManager
					.createQuery("SELECT c FROM Comment c", Comment.class)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

	@Override
	public Comment getById(Integer id) {
		Comment c;
		try {
			c = this.entityManager
					.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			c = null;
		}
		return c;
	}

	@Override
	public void delete(Comment c) {
		this.entityManager.remove(entityManager.contains(c) ? c : entityManager.merge(c));
	}

	@Override
	public List<Comment> listByStatus(Status status) {
		List<Comment> list;
		try {
			list = this.entityManager
					.createQuery("SELECT l FROM Comment c WHERE c.status = :status ORDER BY c.created_at", Comment.class)
					.setParameter("status", status)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

}
