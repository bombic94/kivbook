package zcu.pia.bohmannd.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class FriendshipJdbcTemplate implements FriendshipDAO {

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
	public void create(int user1_id, int user2_id, boolean accepted) {		
		String SQL = "insert into bohmannd_friendship ( `user1_id`, `user2_id`, `accepted`)" + 
	    		" values (?, ?, ?)";
	    jdbcTemplateObject.update( SQL, user1_id, user2_id, accepted);
	}

	@Override
	public Friendship getFriendship(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);
		
		Friendship friendship = new Friendship();
	    friendship.setCreated_at((Timestamp) out.get("out_created_at"));
		friendship.setId((Integer) out.get("out_id"));
		friendship.setUser2_id((Integer) out.get("out_user2_id"));
		friendship.setUser1_id((Integer) out.get("out_user1_id"));
		friendship.setAccepted((Boolean) out.get("out_accepted"));
		
		return friendship;
	}

	@Override
	public List<Friendship> listFriendships() {
		String SQL = "select * from bohmannd_friendship";
	    List <Friendship> friendships = jdbcTemplateObject.query(SQL, new FriendshipMapper());
	    
	    return friendships;
	}

	@Override
	public List<Friendship> listFriendships(int user_id) {
		String SQL = "select * from bohmannd_friendship where user1_id = ? or user2_id = ?";
		Object[] args = {user_id, user_id};
	    List <Friendship> friendships = jdbcTemplateObject.query(SQL, args, new FriendshipMapper());
	    
	    return friendships;
	}

	@Override
	public void acceptFriendship(int id) {
		String SQL = "update bohmannd_friendship set accepted=1 where id = ?";
		Object[] args = {id};
		jdbcTemplateObject.update(SQL, args);
	}

}
