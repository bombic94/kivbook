package zcu.pia.bohmannd.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.ChatDAO;
import zcu.pia.bohmannd.dao.Chat_LineDAO;
import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;
import zcu.pia.bohmannd.model.User;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	
	private Chat activeChat;

	@Autowired
	private Chat_LineDAO chat_LineDAO;
	
	@Transactional
	@Override
	public void insertChat(Chat chat) {
		chatDAO.save(chat);
	}

	@Transactional
	@Override
	public List<Chat> listChats() {
		return chatDAO.list();
	}

	@Transactional
	@Override
	public Chat getChat(Integer id) {
		return chatDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteChat(Chat chat) {
		chatDAO.delete(chat);
	}

	@Transactional
	@Override
	public List<Chat> listChatByUser(User user) {
		List<Chat> listCh = chatDAO.listByUser(user);
		for (Chat ch : listCh) { 
			ch.setChat_Lines(chat_LineDAO.listByChat(ch));
		}
		
		return listCh;
	}

	@Transactional
	@Override
	public void readChat(Chat chat, User user) {
		List<Chat_Line> chL = chat.getChat_Lines();
		if (chL.get(chL.size() - 1).getSender().getId() != user.getId()) {
			chatDAO.accept(chat);
		}
	}
	
	@Override
	public Chat getActiveChat() {
		return activeChat;
	}

	@Override
	public void setActiveChat(Chat activeChat) {
		this.activeChat = activeChat;
	}

	@Override
	public List<Chat> listUnreadChatByUser(User user) {
		
		List<Chat> listCh = chatDAO.listByUser(user);
		for (Chat ch : listCh) { 
			ch.setChat_Lines(chat_LineDAO.listByChat(ch));
		}		
		
		for (Iterator<Chat> iterator = listCh.iterator(); iterator.hasNext();) {
			Chat ch = iterator.next();
			List<Chat_Line> chL = ch.getChat_Lines();
			if (chL.size() > 0) {
				if (ch.isSeen()) {
					iterator.remove();
					continue;
				}
				if (chL.get(chL.size() - 1).getSender().getId() == user.getId()) {
					iterator.remove();
				}		
			}
		}
		
		return listCh;
	}

}
