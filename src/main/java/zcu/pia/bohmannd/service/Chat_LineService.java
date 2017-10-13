package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Chat_Line;
import zcu.pia.bohmannd.model.Chat;

public interface Chat_LineService {

	/**
	 * Insert new chat line
	 * @param chat_Line Chat line to insert
	 */
	public void insertChat_Line(Chat_Line chat_Line);

	/**
	 * List all chat lines
	 * @return list of all chat lines
	 */
	public List<Chat_Line> listChat_Lines();

	/**
	 * Retrieve chat line by given id
	 * @param id ID of chat line
	 * @return Chat_Line object
	 */
	public Chat_Line getChat_Line(Integer id);

	/**
	 * Delete given chat line
	 * @param chat_Line Object to delete
	 */
	public void deleteChat_Line(Chat_Line chat_Line);

	/**
	 * List all chat lines for given chat
	 * @param chat Chat object
	 * @return list of chat lines for chat
	 */
	public List<Chat_Line> listChat_LinesByChat(Chat chat);
}
