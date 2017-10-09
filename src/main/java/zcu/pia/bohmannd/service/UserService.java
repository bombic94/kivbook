package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.User;

public interface UserService {

    public void insertUser(User user);

    public List<User> listUsers();

    public User getUser(Integer id);
    
    public User getUserByUsername(String username);
    
    public void deleteUser(User user);

	public boolean validateUser(User user);

	public boolean register(User user);
}
