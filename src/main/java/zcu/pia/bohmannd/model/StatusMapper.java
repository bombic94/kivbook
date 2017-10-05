package zcu.pia.bohmannd.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StatusMapper implements RowMapper<Status> {

	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		Status status = new Status();
		status.setCreated_at(rs.getTimestamp("created_at"));
		status.setId(rs.getInt("id"));
		status.setPhoto(rs.getString("photo"));
		status.setStatus_text(rs.getString("status_text"));
		status.setUser_id(rs.getInt("user_id"));
		
		return status;
	}

}
