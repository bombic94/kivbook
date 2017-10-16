package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

public interface ChatService {

	/**
	 * Insert new chat
	 * 
	 * @param chat
	 *            Object to insert
	 */
	public void insertChat(Chat chat);

	/**
	 * List all chats
	 * 
	 * @return list of all chats
	 */
	public List<Chat> listChats();

	/**
	 * Retrieve chat by given id
	 * 
	 * @param id
	 *            ID of chat
	 * @return Chat Object
	 */
	public Chat getChat(Integer id);

	/**
	 * Delete given chat
	 * 
	 * @param chat
	 *            Object to delete
	 */
	public void deleteChat(Chat chat);

	/**
	 * List all chats for given user
	 * 
	 * @param user
	 *            User Object
	 * @return list of chats for user
	 */
	public List<Chat> listChatByUser(User user);

	/**
	 * List all unread chats for given user
	 * 
	 * @param user
	 *            User Object
	 * @return list of unread chats for user
	 */
	public List<Chat> listUnreadChatByUser(User user);

	/**
	 * Read chat by receiver user. If user is sender, do nothing
	 * 
	 * @param chat
	 *            Chat to read
	 * @param user
	 *            User which is reading
	 */
	public void readChat(Chat chat, User user);

	/**
	 * Set chat unread, when adding new chat_line
	 * 
	 * @param chat
	 *            object
	 */
	void setUnread(Chat chat);
}
