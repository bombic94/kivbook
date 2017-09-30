package zcu.pia.bohmannd.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class UserJdbcTemplate implements UserDAO {

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
	public void create(String email, String username, String password, String dateofbirth, String string,
			String firstname, String lastname, String photo) {

	    String SQL = "insert into bohmannd_user ( `email`, `username`, `password`, `dateofbirth`, `gender`, `firstname`, `lastname`, `photo`)" + 
	    		" values (?, ?, ?, ?, ?, ?, ?, ?)";
	    jdbcTemplateObject.update( SQL, email, username, password, dateofbirth, string, firstname, lastname, photo);
	}
	
	@Override
	public User getUser(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);

	    User user = new User();
		user.setCreated_at((Timestamp) out.get("out_created_at"));
		user.setDateofbirth((Date) out.get("out_dateofbirth"));
		user.setEmail(out.get("out_email").toString());
		user.setFirstname(out.get("out_firstname").toString());
		user.setGender(out.get("out_gender").toString());
		user.setId((Integer) out.get("out_id"));
		user.setLastname(out.get("out_lastname").toString());
		user.setPassword(out.get("out_password").toString());
		user.setPhoto(out.get("out_photo").toString());
		user.setUsername(out.get("out_username").toString());
		
		return user;
	}
	
	@Override
	public List<User> listUsers() {
		String SQL = "select * from bohmannd_user";
	    List <User> users = jdbcTemplateObject.query(SQL, new UserMapper());
	    
	    return users;
	}
	
	@Override
	public int getUserCount() {
		String SQL = "select count(*) from bohmannd_user";
	    int usersCount = jdbcTemplateObject.queryForInt(SQL);
	    return usersCount;
	}

}
