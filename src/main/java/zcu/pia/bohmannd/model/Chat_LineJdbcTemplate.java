package zcu.pia.bohmannd.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class Chat_LineJdbcTemplate implements Chat_LineDAO {

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
	public void create(int chat_id, int sender_id, String line_text) {
		String SQL = "insert into bohmannd_chat_line ( `chat_id`, `sender_id`, `line_text`)" + 
	    		" values (?, ?, ?)";
	    jdbcTemplateObject.update( SQL, chat_id, sender_id, line_text);
	}

	@Override
	public Chat_Line getChat_Line(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);

		Chat_Line chat_line = new Chat_Line();
	    chat_line.setCreated_at((Timestamp) out.get("out_created_at"));
		chat_line.setId((Integer) out.get("out_id"));
		chat_line.setChat_id((Integer) out.get("out_chat_id"));
		chat_line.setSender_id((Integer) out.get("out_sender_id"));
		chat_line.setLine_text(out.get("out_line_text").toString());
		
		return chat_line;
	}

	@Override
	public List<Chat_Line> listChat_Lines() {
		String SQL = "select * from bohmannd_chat_line";
	    List <Chat_Line> chat_lines = jdbcTemplateObject.query(SQL, new Chat_LineMapper());
	    
	    return chat_lines;
	}

	@Override
	public List<Chat_Line> listChat_Lines(int chat_id) {
		String SQL = "select * from bohmannd_chat_line where chat_id = ?";
		Object[] args = {chat_id};
	    List <Chat_Line> chat_lines = jdbcTemplateObject.query(SQL, args, new Chat_LineMapper());
	    
	    return chat_lines;
	}

}
