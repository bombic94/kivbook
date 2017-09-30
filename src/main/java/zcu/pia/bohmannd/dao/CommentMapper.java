package zcu.pia.bohmannd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentMapper implements RowMapper<Comment> {

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setCreated_at(rs.getTimestamp("created_at"));
		comment.setId(rs.getInt("id"));
		comment.setStatus_id(rs.getInt("status_id"));
		comment.setComment_text(rs.getString("comment_text"));
		comment.setUser_id(rs.getInt("user_id"));
		
		return comment;
	}
}
