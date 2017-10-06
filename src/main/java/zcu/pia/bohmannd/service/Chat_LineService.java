package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Chat_Line;
import zcu.pia.bohmannd.model.Chat;

public interface Chat_LineService {

	public void insertChat_Line(Chat_Line chat_Line);

    public List<Chat_Line> listChat_Lines();

    public Chat_Line getChat_Line(Integer id);
    
    public void deleteChat_Line(Chat_Line chat_Line);
    
    public List<Chat_Line> listChat_LinesByChat(Chat chat);
}
