package zcu.pia.bohmannd.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.ChatDAO;
import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Chat_Line;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.utils.MessageComparator;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;
	@Autowired
	private Chat_LineService chat_LineService;

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
			ch.setChat_Lines(chat_LineService.listChat_LinesByChat(ch));
		}
		Collections.sort(listCh, new MessageComparator());
		return listCh;
	}

	@Transactional
	@Override
	public void readChat(Chat chat, User user) {
		List<Chat_Line> chL = chat.getChat_Lines();
		if (chL.size() == 0) {
			chatDAO.accept(chat);
		} else if (chL.get(chL.size() - 1).getSender().getId() != user.getId()) {
			chatDAO.accept(chat);
		}
	}

	@Override
	public List<Chat> listUnreadChatByUser(User user) {

		List<Chat> listCh = chatDAO.listByUser(user);
		for (Chat ch : listCh) {
			ch.setChat_Lines(chat_LineService.listChat_LinesByChat(ch));
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

	@Transactional
	@Override
	public void setUnread(Chat chat) {
		chatDAO.setUnread(chat);
	}

}
