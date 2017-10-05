package zcu.pia.bohmannd.model;

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
   
   /**
    * Update user settings
    */
   public void updateUser(Integer id, String email, String dateofbirth, String gender, String firstname, String lastname);
   
   /**
    * Update user profile picture
    */
   public void updateUserPicture(Integer id, String photo);
   
   /**
    * Update user password
    */
   public void updateUserPassword(Integer id, String password);
   
   /**
    * Delete user with given id
    */
   public void deleteUser(Integer id);
   
}
