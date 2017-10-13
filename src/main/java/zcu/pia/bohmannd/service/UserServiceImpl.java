package zcu.pia.bohmannd.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.UserDAO;
import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.utils.EmailService;
import zcu.pia.bohmannd.utils.Encoder;

@Service
public class UserServiceImpl implements UserService {

	final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ChatService chatService;
	@Autowired
	private FriendshipService friendshipService;
	@Autowired
	private Encoder encoder;
	@Autowired
	private EmailService emailService;

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

		User u = userDAO.getByUsername(user.getUsername());

		if (u != null) {
			success = false;
		} else {
			emailService.sendMail(user);

			user.setPassword(encoder.encode(user.getPassword()));
			userDAO.save(user);

			success = true;
		}

		return success;
	}

	@Transactional
	@Override
	public User getUserByUsername(String username) {

		User u = userDAO.getByUsername(username);

		return u;
	}

	@Transactional
	@Override
	public List<User> listUsersToFriend(User user) {
		List<User> listU = userDAO.list();
		List<Friendship> listF = friendshipService.listPossibleFriendshipByUser(user);

		for (Iterator<User> iterator = listU.iterator(); iterator.hasNext();) {
			User u = iterator.next();
			if (u.getId() == user.getId()) {
				iterator.remove();
			}
		}

		for (Friendship f : listF) {
			for (Iterator<User> iterator = listU.iterator(); iterator.hasNext();) {
				User u = iterator.next();
				if (u.getId() == f.getUser1().getId() || u.getId() == f.getUser2().getId()) {
					iterator.remove();
				}
			}
		}
		return listU;
	}

	@Transactional
	@Override
	public List<User> listUsersToChat(User user) {
		List<User> listU = userDAO.list();
		List<Chat> listCh = chatService.listChatByUser(user);

		for (Iterator<User> iterator = listU.iterator(); iterator.hasNext();) {
			User u = iterator.next();
			if (u.getId() == user.getId()) {
				iterator.remove();
			}
		}

		for (Chat ch : listCh) {
			for (Iterator<User> iterator = listU.iterator(); iterator.hasNext();) {
				User u = iterator.next();
				if (u.getId() == ch.getUser1().getId() || u.getId() == ch.getUser2().getId()) {
					iterator.remove();
				}
			}
		}
		return listU;
	}

	@Transactional
	@Override
	public void changeSettings(User user) {
		userDAO.updateSettings(user);
	}

	@Transactional
	@Override
	public void changePhoto(User user) {
		userDAO.updatePhoto(user);
	}

	@Transactional
	@Override
	public void changePassword(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDAO.updatePassword(user);
	}

}
