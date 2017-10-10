package zcu.pia.bohmannd.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		List<User> list;
		try {
			list = this.entityManager
					.createQuery("SELECT u FROM User u", User.class)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

	@Override
	public User getById(Integer id) {
		User u;
		try {
			u = this.entityManager
					.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			u = null;
		}
		return u;
	}

	@Override
	public void delete(User u) {
		this.entityManager.remove(u);		
	}

	@Override
	public User getByUsername(String username) {
		User u;
		try {
			u = this.entityManager
					.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
					.setParameter("username", username)
					.getSingleResult();
		} catch (NoResultException nre) {
			u = null;
		}
		return u;
	}

	@Override
	public void updateSettings(User user) {
		this.entityManager
					.createQuery("UPDATE User u SET u.firstname = :firstname, u.lastname = :lastname, u.dateofbirth = :dateofbirth, u.gender = :gender WHERE u.id = :id")
					.setParameter("firstname", user.getFirstname())
					.setParameter("lastname", user.getLastname())
					.setParameter("dateofbirth", user.getDateofbirth())
					.setParameter("gender", user.getGender())
					.setParameter("id", user.getId())
					.executeUpdate();	
	}

	@Override
	public void updatePhoto(User user) {
		this.entityManager
					.createQuery("UPDATE User u SET u.photo = :photo WHERE u.id = :id")
					.setParameter("photo", user.getPhoto())
					.setParameter("id", user.getId())
					.executeUpdate();		
	}

	@Override
	public void updatePassword(User user) {
		this.entityManager
					.createQuery("UPDATE User u SET u.password = :password WHERE u.id = :id")
					.setParameter("password", user.getPassword())
					.setParameter("id", user.getId())
					.executeUpdate();	
	}	

}
