package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.persistence.EntityManager;
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
		List<Comment> list = this.entityManager
				.createQuery("SELECT c FROM Comment c", Comment.class)
				.getResultList();
		return list;
	}

	@Override
	public Comment getById(Integer id) {
		Comment c = this.entityManager
				.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class)
				.setParameter("id", id)
				.getSingleResult();
		return c;
	}

	@Override
	public void delete(Comment c) {
		this.entityManager.remove(c);
	}

	@Override
	public List<Comment> listByStatus(Status status) {
		List<Comment> list = this.entityManager
				.createQuery("SELECT l FROM Comment c WHERE c.status_id = :status_id ORDER BY c.created_at", Comment.class)
				.setParameter("status_id", status.getId())
				.getResultList();
		return list;
	}

}
