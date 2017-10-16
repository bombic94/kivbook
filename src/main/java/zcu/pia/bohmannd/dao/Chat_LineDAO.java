package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;

public interface Chat_LineDAO extends AbstractDAO<Chat_Line> {

	/**
	 * List messages from chat
	 * 
	 * @param chat
	 *            Chat thread
	 * @return List of messages
	 */
	List<Chat_Line> listByChat(Chat chat);

}
