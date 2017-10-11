package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.ChatDAO;
import zcu.pia.bohmannd.dao.Chat_LineDAO;
import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	
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
	public void acceptChat(Chat chat) {
		chatDAO.accept(chat);
	}

}
