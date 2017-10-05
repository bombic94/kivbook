package zcu.pia.bohmannd.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setCreated_at(rs.getTimestamp("created_at"));
		user.setDateofbirth(rs.getDate("dateofbirth"));
		user.setEmail(rs.getString("email"));
		user.setFirstname(rs.getString("firstname"));
		user.setGender(rs.getString("gender"));
		user.setId(rs.getInt("id"));
		user.setLastname(rs.getString("lastname"));
		user.setPassword(rs.getString("password"));
		user.setPhoto(rs.getString("photo"));
		user.setUsername(rs.getString("username"));
		
		return user;
	}

}
