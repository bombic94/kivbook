package zcu.pia.bohmannd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.UserDAO;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.utils.Encoder;

@Service
public class UserServiceImpl implements UserService {

	final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserDAO userDAO;
	@Autowired
	private Encoder encoder;
	
	@Transactional
	@Override
	public void insertUser(User user) {
		userDAO.save(user);		
	}

	@Transactional
	@Override
	public List<User> listUsers() {
		return userDAO.list();
	}

	@Transactional
	@Override
	public User getUser(Integer id) {
		return userDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	@Transactional
	@Override
	public boolean validateUser(User user) {
		
		User u = userDAO.getByUsername(user.getUsername());
        
        return (u != null && encoder.validate(user.getPassword(), u.getPassword()));
	}

	@Transactional
	@Override
	public boolean register(User user) {
		boolean success;
		
//		if(!user.isNew()) {
//            throw new RuntimeException("User already exists, use save method for updates!");
//        }
		User u = userDAO.getByUsername(user.getUsername());
        
        if(u != null) {
        	success = false;
        } else {
	        user.setPassword(encoder.encode(user.getPassword()));
	        userDAO.save(user);
	        
	        success = true;
        }
        
        return success;
	}

	@Override
	public User getUserByUsername(String username) {
		
		User u = userDAO.getByUsername(username);
		
		return u;
	}

}
