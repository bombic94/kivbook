package zcu.pia.bohmannd.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class CommentJdbcTemplate implements CommentDAO {

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
	public void create(int status_id, int user_id, String comment_text) {
		String SQL = "insert into bohmannd_comment ( `status_id`, `user_id`, `comment_text`)" + 
	    		" values (?, ?, ?)";
	    jdbcTemplateObject.update( SQL, status_id, user_id, comment_text);
	}

	@Override
	public Comment getComment(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);
		
		Comment comment = new Comment();
	    comment.setCreated_at((Timestamp) out.get("out_created_at"));
		comment.setId((Integer) out.get("out_id"));
		comment.setStatus_id((Integer) out.get("out_status_id"));
		comment.setUser_id((Integer) out.get("out_user_id"));
		comment.setComment_text(out.get("out_comment_text").toString());
		
		return comment;
	}

	@Override
	public List<Comment> listComments() {
		String SQL = "select * from bohmannd_comment";
	    List <Comment> comments = jdbcTemplateObject.query(SQL, new CommentMapper());
	    
	    return comments;
	}

	@Override
	public List<Comment> listComments(int status_id) {
		String SQL = "select * from bohmannd_comment where status_id = ?";
		Object[] args = {status_id};
	    List <Comment> comments = jdbcTemplateObject.query(SQL, args, new CommentMapper());
	    
	    return comments;
	}

}
