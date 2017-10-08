package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Chat save(Chat ch) {
		if(ch.isNew()) {
            this.entityManager.persist(ch);
            return ch;
        } else {
            this.entityManager.merge(ch);
            return ch;
        }
	}

	@Override
	public List<Chat> list() {
		List<Chat> list;
		try {
			list = this.entityManager
					.createQuery("SELECT ch FROM Chat ch", Chat.class)
					.getResultList();
		} catch (NoResultException nre) {
			list = null;
		}
		return list;
	}

	@Override
	public Chat getById(Integer id) {
		Chat ch;
		try {
			ch = this.entityManager
					.createQuery("SELECT ch FROM Chat ch WHERE ch.id = :id", Chat.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			ch = null;
		}
		return ch;
	}

	@Override
	public void delete(Chat ch) {
		this.entityManager.remove(ch);
	}

	@Override
	public List<Chat> listByUser(User user) {
		List<Chat> list;
		try {
			list = this.entityManager
					.createQuery("SELECT ch FROM Chat ch WHERE ch.user1_id = :id OR ch.user2_id = :id", Chat.class)
					.setParameter("id", user.getId())
					.getResultList();
		} catch (NoResultException nre) {
			list = null;
		}
		return list;
	}

	@Override
	public void accept(Chat chat) {
		this.entityManager
				.createQuery("UPDATE Chat ch SET ch.accepted=1 WHERE ch.id = :id")
				.setParameter("id", chat.getId())
				.executeUpdate();
	}

}
