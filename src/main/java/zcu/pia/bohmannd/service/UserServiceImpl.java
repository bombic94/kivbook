package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.UserDAO;
import zcu.pia.bohmannd.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDAO userDAO;

	@Transactional
	@Override
	public void insertUser(User user) {
		userDAO.save(user);		
	}

	@Transactional
	@Override
	public List<User> list() {
		return userDAO.list();
	}

}
