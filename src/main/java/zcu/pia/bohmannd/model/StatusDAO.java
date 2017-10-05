package zcu.pia.bohmannd.model;

import java.util.List;

import javax.sql.DataSource;

public interface StatusDAO {

	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
  public void setDataSource(DataSource ds);
  
  /** 
     * This is the method to be used to create
     * a record in the Status table.
     */
  public void create(int user_id, String status_text, String photo);
 
  /** 
     * This is the method to be used to list down
     * a record from the Status table corresponding
     * to a passed Status id.
	*/
  	public Status getStatus(Integer id);

	/** 
	   * This is the method to be used to list down
	   * all the records from the Status table.
	   */
	public List<Status> listStatuses();
	
	/**
	 * Delete status with given id
	 */
	public void deleteStatus(Integer id);
}
