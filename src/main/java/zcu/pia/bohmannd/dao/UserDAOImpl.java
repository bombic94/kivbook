package zcu.pia.bohmannd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import zcu.pia.bohmannd.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public User save(User u) {
		if(u.isNew()) {
            this.entityManager.persist(u);
            return u;
        } else {
            this.entityManager.merge(u);
            return u;
        }
	}

	@Override
	public List<User> list() {
		List<User> list = this.entityManager
				.createQuery("SELECT u FROM User u", User.class)
				.getResultList();
		return list;
	}

	@Override
	public User getById(Integer id) {
		User u = this.entityManager
				.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
				.setParameter("id", id)
				.getSingleResult();
		return u;
	}

	@Override
	public void delete(User u) {
		this.entityManager.remove(u);		
	}	

}
