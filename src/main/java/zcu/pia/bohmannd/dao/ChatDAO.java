package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

public interface ChatDAO extends AbstractDAO<Chat> {

	/**
	 * List chats for given user
	 * 
	 * @param user User object
	 * @return list of chats
	 */
	List<Chat> listByUser(User user);

	/**
	 * accept chat (read)
	 * 
	 * @param chat Chat object
	 */
	void accept(Chat chat);

	/**
	 * set chat unread
	 * 
	 * @param chat Chat object
	 */
	void setUnread(Chat chat);
}
