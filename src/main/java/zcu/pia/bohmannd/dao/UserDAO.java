package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.User;

public interface UserDAO extends AbstractDAO<User>{

	User getByUsername(String username);
   
	void updateSettings(User user);
	
	void updatePhoto(User user);
	
	void updatePassword(User user);
}
