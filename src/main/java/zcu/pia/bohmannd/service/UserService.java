package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.User;

public interface UserService {

    public void insertUser(User user);

    public List<User> list();

}
