package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;

@Repository("friendshipDAO")
public class FriendshipDAOImpl implements FriendshipDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Friendship save(Friendship f) {
		if(f.isNew()) {
            this.entityManager.persist(f);
            return f;
        } else {
            this.entityManager.merge(f);
            return f;
        }
	}

	@Override
	public List<Friendship> list() {
		List<Friendship> list = this.entityManager
				.createQuery("SELECT f FROM Friendship f", Friendship.class)
				.getResultList();
		return list;
	}

	@Override
	public Friendship getById(Integer id) {
		Friendship f = this.entityManager
				.createQuery("SELECT f FROM Friendship f WHERE f.id = :id", Friendship.class)
				.setParameter("id", id)
				.getSingleResult();
		return f;
	}

	@Override
	public void delete(Friendship f) {
		this.entityManager.remove(f);	
	}

	@Override
	public List<Friendship> listByUser(User user) {
		List<Friendship> list = this.entityManager
				.createQuery("SELECT f FROM Friendship f WHERE f.user1_id = :id OR f.user2_id = :id", Friendship.class)
				.setParameter("id", user.getId())
				.getResultList();
		return list;
	}

	@Override
	public void accept(Friendship friendship) {
		this.entityManager
				.createQuery("UPDATE Friendship f SET f.accepted=1 WHERE f.id = :id")
				.setParameter("id", friendship.getId())
				.executeUpdate();
	}

}
