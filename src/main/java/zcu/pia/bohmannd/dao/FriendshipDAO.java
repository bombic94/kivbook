package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.sql.DataSource;

public interface FriendshipDAO {
	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
 	public void setDataSource(DataSource ds);
  
 	/** 
     * This is the method to be used to create
     * a record in the Friendship table.
     */
 	public void create(int user1_id, int user2_id, boolean accepted);
 
 	/** 
     * This is the method to be used to list down
     * a record from the Friendship table corresponding
     * to a passed Friendship id.
	*/
  	public Friendship getFriendship(Integer id);

	/** 
	   * This is the method to be used to list down
	   * all the records from the Friendship table.
	   */
	public List<Friendship> listFriendships();
	
	/** 
	   * This is the method to be used to list down
	   * users records from the Friendship table.
	   */
	public List<Friendship> listFriendships(int user_id);
	
	/** 
	   * This is the method to be used to 
	   * accept friendship.
	   */
	public void acceptFriendship(int id);
}
