package zcu.pia.bohmannd.model;

import java.util.List;

import javax.sql.DataSource;

public interface CommentDAO {
	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
	public void setDataSource(DataSource ds);
  
  	/** 
     * This is the method to be used to create
     * a record in the Comment table.
     */
  	public void create(int status_id, int user_id, String comment_text);
 
  	/** 
     * This is the method to be used to list down
     * a record from the Comment table corresponding
     * to a passed Comment id.
	*/
  	public Comment getComment(Integer id);

	/** 
	   * This is the method to be used to list down
	   * all the records from the Comment table.
	   */
	public List<Comment> listComments();
	
	/** 
	   * This is the method to be used to list down
	   * all the records from the Comment table.
	   */
	public List<Comment> listComments(int status_id);
	
	/**
	 * Delete comment with given id
	 */
	public void deleteComment(Integer id);
	
	/**
	 * Get count of comments to given status
	 */
	public int getCommentsCount(int status_id);
}
