package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.ChatDAO;
import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	
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
		return chatDAO.listByUser(user);
	}

	@Transactional
	@Override
	public void acceptChat(Chat chat) {
		chatDAO.accept(chat);
	}

}
