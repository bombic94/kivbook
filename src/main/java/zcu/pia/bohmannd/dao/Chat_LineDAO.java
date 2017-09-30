package zcu.pia.bohmannd.dao;

import java.util.List;

import javax.sql.DataSource;

public interface Chat_LineDAO {

	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
 	public void setDataSource(DataSource ds);
  
 	/** 
     * This is the method to be used to create
     * a record in the Chat_Line table.
     */
 	public void create(int chat_id, int sender_id, String line_text);
 
 	/** 
     * This is the method to be used to list down
     * a record from the Chat_Line table corresponding
     * to a passed Chat_Line id.
	*/
  	public Chat_Line getChat_Line(Integer id);

	/** 
	   * This is the method to be used to list down
	   * all the records from the Chat_Line table.
	   */
	public List<Chat_Line> listChat_Lines();
	
	/** 
	   * This is the method to be used to list down
	   * users records from the Chat_Line table.
	   */
	public List<Chat_Line> listChat_Lines(int chat_id);
}
