package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Status;

@Repository("statusDAO")
public class StatusDAOImpl implements StatusDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Status save(Status s) {
		if(s.isNew()) {
            this.entityManager.persist(s);
            return s;
        } else {
            this.entityManager.merge(s);
            return s;
        }
	}

	@Override
	public List<Status> list() {
		List<Status> list = this.entityManager
				.createQuery("SELECT s FROM Status s", Status.class)
				.getResultList();
		return list;
	}

	@Override
	public Status getById(Integer id) {
		Status s = this.entityManager
				.createQuery("SELECT s FROM Status s WHERE s.id = :id", Status.class)
				.setParameter("id", id)
				.getSingleResult();
		return s;
	}

	@Override
	public void delete(Status s) {
		this.entityManager.remove(s);
	}

}
