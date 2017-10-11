package zcu.pia.bohmannd.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;

@Repository("chat_lineDAO")
public class Chat_LineDAOImpl implements Chat_LineDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Chat_Line save(Chat_Line ch) {
		if(ch.isNew()) {
            this.entityManager.persist(ch);
            return ch;
        } else {
            this.entityManager.merge(ch);
            return ch;
        }
	}

	@Override
	public List<Chat_Line> list() {
		List<Chat_Line> list;
		try {
			list = this.entityManager
					.createQuery("SELECT ch FROM Chat_Line ch", Chat_Line.class)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

	@Override
	public Chat_Line getById(Integer id) {
		Chat_Line ch;
		try {
			ch = this.entityManager
					.createQuery("SELECT ch FROM Chat_Line ch WHERE ch.id = :id", Chat_Line.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			ch = null;
		}
		return ch;
	}

	@Override
	public void delete(Chat_Line ch) {
		this.entityManager.remove(entityManager.contains(ch) ? ch : entityManager.merge(ch));
	}

	@Override
	public List<Chat_Line> listByChat(Chat chat) {
		List<Chat_Line> list;
		try {
			list = this.entityManager
					.createQuery("SELECT ch FROM Chat_Line ch WHERE ch.chat = :id ORDER BY ch.created_at", Chat_Line.class)
					.setParameter("id", chat)
					.getResultList();
		} catch (NoResultException nre) {
			list = Collections.emptyList();
		}
		return list;
	}

}
