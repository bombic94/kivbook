package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.sql.DataSource;

public interface ChatDAO {

	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
 	public void setDataSource(DataSource ds);
  
 	/** 
     * This is the method to be used to create
     * a record in the Chat table.
     */
 	public void create(int user1_id, int user2_id, boolean read);
 
 	/** 
     * This is the method to be used to list down
     * a record from the Chat table corresponding
     * to a passed Chat id.
	*/
  	public Chat getChat(Integer id);

	/** 
	   * This is the method to be used to list down
	   * all the records from the Chat table.
	   */
	public List<Chat> listChats();
	
	/** 
	   * This is the method to be used to list down
	   * users records from the Chat table.
	   */
	public List<Chat> listChats(int user_id);
	
	/** 
	   * This is the method to be used to list down
	   * users records from the Chat table.
	   */
	public void seeChat(int id);
}
