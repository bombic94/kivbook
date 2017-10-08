package zcu.pia.bohmannd.dao;

import zcu.pia.bohmannd.model.User;

public interface UserDAO extends AbstractDAO<User>{

	User getByUsername(String username);
   
}
