package zcu.pia.bohmannd.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChatMapper implements RowMapper<Chat> {

	@Override
	public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Chat chat = new Chat();
		chat.setCreated_at(rs.getTimestamp("created_at"));
		chat.setId(rs.getInt("id"));
		chat.setSeen(rs.getBoolean("seen"));
		chat.setUser1_id(rs.getInt("user1_id"));
		chat.setUser2_id(rs.getInt("user2_id"));
		
		return chat;
	}
}
