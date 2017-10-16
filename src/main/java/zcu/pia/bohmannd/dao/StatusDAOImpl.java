package zcu.pia.bohmannd.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

@Repository("statusDAO")
public class StatusDAOImpl implements StatusDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Status save(Status s) {
		if (s.isNew()) {
			this.entityManager.persist(s);
			return s;
		} else {
			this.entityManager.merge(s);
			return s;
		}
	}

	@Override
	public List<Status> list() {
		List<Status> list;
		try {
			list = this.entityManager.createQuery("SELECT s FROM Status s", Status.class).getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

	@Override
	public Status getById(Integer id) {
		Status s;
		try {
			s = this.entityManager.createQuery("SELECT s FROM Status s WHERE s.id = :id", Status.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException nre) {
			s = null;
		}
		return s;
	}

	@Override
	public void delete(Status s) {
		this.entityManager.remove(entityManager.contains(s) ? s : entityManager.merge(s));
	}

	@Override
	public List<Status> listByUser(User user) {
		List<Status> list;
		try {
			list = this.entityManager.createQuery("SELECT s FROM Status s where s.user = :user", Status.class)
					.setParameter("user", user).getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

}
