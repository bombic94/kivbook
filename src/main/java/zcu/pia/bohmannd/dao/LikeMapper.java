package zcu.pia.bohmannd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LikeMapper implements RowMapper<Like> {

	@Override
	public Like mapRow(ResultSet rs, int rowNum) throws SQLException {
		Like like = new Like();
		like.setCreated_at(rs.getTimestamp("created_at"));
		like.setId(rs.getInt("id"));
		like.setStatus_id(rs.getInt("status_id"));
		like.setUser_id(rs.getInt("user_id"));
		
		return like;
	}
}
