package zcu.pia.bohmannd.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class LikeJdbcTemplate implements LikeDAO {

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
	public void create(int status_id, int user_id) {
		String SQL = "insert into bohmannd_like ( `status_id`, `user_id`)" + 
	    		" values (?, ?)";
	    jdbcTemplateObject.update( SQL, status_id, user_id);
	}

	@Override
	public Like getLike(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);
		
		Like like = new Like();
	    like.setCreated_at((Timestamp) out.get("out_created_at"));
		like.setId((Integer) out.get("out_id"));
		like.setStatus_id((Integer) out.get("out_status_id"));
		like.setUser_id((Integer) out.get("out_user_id"));
		
		return like;
	}

	@Override
	public List<Like> listLikes() {
		String SQL = "select * from bohmannd_like";
	    List <Like> like = jdbcTemplateObject.query(SQL, new LikeMapper());
	    
	    return like;
	}

	@Override
	public List<Like> listLikes(int status_id) {
		String SQL = "select * from bohmannd_like where status_id = ?";
		Object[] args = {status_id};
	    List <Like> like = jdbcTemplateObject.query(SQL, args, new LikeMapper());
	    
	    return like;
	}

}
