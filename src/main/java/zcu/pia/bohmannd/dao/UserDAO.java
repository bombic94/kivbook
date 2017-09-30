package zcu.pia.bohmannd.dao;

import java.util.List;
import javax.sql.DataSource;

public interface UserDAO {
   /** 
      * This is the method to be used to initialize
      * database resources ie. connection.
      */
   public void setDataSource(DataSource ds);
   
   /** 
      * This is the method to be used to create
      * a record in the User table.
      */
   public void create(String email, String username,String password,String dateofbirth,String gender,String firstname,String lastname,String photo);
  
   /** 
      * This is the method to be used to list down
      * a record from the User table corresponding
      * to a passed User id.
      */
   public User getUser(Integer id);
   
   /** 
      * This is the method to be used to list down
      * all the records from the User table.
      */
   public List<User> listUsers();
   
   /** 
    * This is the method to be used to obtain
    * number of all the records from the User table.
    */
   public int getUserCount();
   
}
