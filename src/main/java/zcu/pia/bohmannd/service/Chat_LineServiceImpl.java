package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.Chat_LineDAO;
import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;

@Service
public class Chat_LineServiceImpl implements Chat_LineService {

	@Autowired
	private Chat_LineDAO chat_LineDAO;
	
	@Transactional
	@Override
	public void insertChat_Line(Chat_Line chat_Line) {
		chat_LineDAO.save(chat_Line);
	}

	@Transactional
	@Override
	public List<Chat_Line> listChat_Lines() {
		return chat_LineDAO.list();
	}

	@Transactional
	@Override
	public Chat_Line getChat_Line(Integer id) {
		return chat_LineDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteChat_Line(Chat_Line chat_Line) {
		chat_LineDAO.delete(chat_Line);
	}

	@Transactional
	@Override
	public List<Chat_Line> listChat_LinesByChat(Chat chat) {
		return chat_LineDAO.listByChat(chat);
	}

}
