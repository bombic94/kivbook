package zcu.pia.bohmannd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FriendshipMapper implements RowMapper<Friendship> {

	@Override
	public Friendship mapRow(ResultSet rs, int rowNum) throws SQLException {
		Friendship friendship = new Friendship();
		friendship.setCreated_at(rs.getTimestamp("created_at"));
		friendship.setId(rs.getInt("id"));
		friendship.setAccepted(rs.getBoolean("accepted"));
		friendship.setUser1_id(rs.getInt("user1_id"));
		friendship.setUser2_id(rs.getInt("user2_id"));
		
		return friendship;
	}
}
