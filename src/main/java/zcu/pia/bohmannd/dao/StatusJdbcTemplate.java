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

public class StatusJdbcTemplate implements StatusDAO {

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
	public void create(int user_id, String status_text, String photo) {
		String SQL = "insert into bohmannd_status ( `user_id`, `status_text`, `photo`)" + 
	    		" values (?, ?, ?)";
	    jdbcTemplateObject.update( SQL, user_id, status_text, photo);
	}

	@Override
	public Status getStatus(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
	    Map<String, Object> out = jdbcCall.execute(in);

	    Status status = new Status();
	    status.setCreated_at((Timestamp) out.get("out_created_at"));
		status.setId((Integer) out.get("out_id"));
		status.setStatus_text(out.get("out_status_text").toString());
		status.setUser_id((Integer) out.get("out_user_id"));
		status.setPhoto(out.get("out_photo").toString());
		
		return status;
	}

	@Override
	public List<Status> listStatuses() {
		String SQL = "select * from bohmannd_status";
	    List <Status> statuses = jdbcTemplateObject.query(SQL, new StatusMapper());
	    
	    return statuses;
	}
	
}
