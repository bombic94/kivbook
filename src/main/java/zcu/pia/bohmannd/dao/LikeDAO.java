package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.sql.DataSource;

public interface LikeDAO {
	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
	public void setDataSource(DataSource ds);
  
  	/** 
     * This is the method to be used to create
     * a record in the Like table.
     */
  	public void create(int like_id, int user_id);
 
  	/** 
     * This is the method to be used to list down
     * a record from the Like table corresponding
     * to a passed Like id.
	*/
  	public Like getLike(Integer id);

	/** 
	   * This is the method to be used to list down
	   * all the records from the Like table.
	   */
	public List<Like> listLikes();
	
	/** 
	   * This is the method to be used to list down
	   * all the records from the Like table.
	   */
	public List<Like> listLikes(int status_id);
}
