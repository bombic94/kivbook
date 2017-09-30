package zcu.pia.bohmannd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Chat_LineMapper implements RowMapper<Chat_Line> {

	@Override
	public Chat_Line mapRow(ResultSet rs, int rowNum) throws SQLException {
		Chat_Line chat_line = new Chat_Line();
		chat_line.setCreated_at(rs.getTimestamp("created_at"));
		chat_line.setId(rs.getInt("id"));
		chat_line.setChat_id(rs.getInt("chat_id"));
		chat_line.setLine_text(rs.getString("line_text"));
		chat_line.setSender_id(rs.getInt("sender_id"));
		
		return chat_line;
	}

}
