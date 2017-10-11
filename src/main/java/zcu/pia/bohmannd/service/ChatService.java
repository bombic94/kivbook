package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

public interface ChatService {

	public void insertChat(Chat chat);

    public List<Chat> listChats();

    public Chat getChat(Integer id);
    
    public void deleteChat(Chat chat);
    
    public List<Chat> listChatByUser(User user);
    
    public List<Chat> listUnreadChatByUser(User user);
    
    public void readChat(Chat chat, User user);

	Chat getActiveChat();

	void setActiveChat(Chat activeChat);
}
