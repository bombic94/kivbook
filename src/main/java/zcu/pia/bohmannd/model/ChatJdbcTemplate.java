package zcu.pia.bohmannd.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ChatJdbcTemplate implements ChatDAO {

	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource dataSource) {	
		this.dataSource = dataSource;
		this.jdbcCall =  new SimpleJdbcCall(dataSource).withProcedureName("getRecord");	
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(int user1_id, int user2_id, boolean seen) {
		String SQL = "insert into bohmannd_chat ( `user1_id`, `user2_id`, `seen`)" + 
	    		" values (?, ?, ?)";
	    jdbcTemplateObject.update( SQL, user1_id, user2_id, seen);
	}

	@Override
	public Chat getChat(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);
	
		Chat chat = new Chat();
	    chat.setCreated_at((Timestamp) out.get("out_created_at"));
		chat.setId((Integer) out.get("out_id"));
		chat.setUser2_id((Integer) out.get("out_user2_id"));
		chat.setUser1_id((Integer) out.get("out_user1_id"));
		chat.setSeen((Boolean) out.get("out_seen"));
		
		return chat;
	}

	@Override
	public List<Chat> listChats() {
		String SQL = "select * from bohmannd_chat";
	    List <Chat> chats = jdbcTemplateObject.query(SQL, new ChatMapper());
	    
	    return chats;
	}

	@Override
	public List<Chat> listChats(int user_id) {
		String SQL = "select * from bohmannd_chat where user1_id = ? or user2_id = ?";
		Object[] args = {user_id, user_id};
	    List <Chat> chats = jdbcTemplateObject.query(SQL, args, new ChatMapper());
	    
	    return chats;
	}

	@Override
	public void seeChat(int id) {
		String SQL = "update bohmannd_chat set seen=1 where id = ?";
		Object[] args = {id};
		jdbcTemplateObject.update(SQL, args);		
	}

}
