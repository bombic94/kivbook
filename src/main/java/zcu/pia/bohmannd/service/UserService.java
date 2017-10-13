package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.User;

public interface UserService {

	/**
	 * Insert new user
	 * @param user Object to insert
	 */
	public void insertUser(User user);

	/**
	 * List all users
	 * @return list of all users
	 */
	public List<User> listUsers();

	/**
	 * Retrieve user by given id
	 * @param id ID of user
	 * @return User Object
	 */
	public User getUser(Integer id);

	/**
	 * Retrieve user by given username
	 * @param username Username of user
	 * @return User object
	 */
	public User getUserByUsername(String username);

	/**
	 * Delete given user
	 * @param user Object to delete
	 */
	public void deleteUser(User user);

	/**
	 * Check if username and password matches DB
	 * @param user object to match
	 * @return User matches DB
	 */
	public boolean validateUser(User user);

	/**
	 * Create new user, send email and encode password
	 * @param user Object to save
	 * @return registered successfully
	 */
	public boolean register(User user);

	/**
	 * Change user settings - name, age, email
	 * @param user Object to change
	 */
	public void changeSettings(User user);

	/**
	 * Change user photo
	 * @param user Object to change
	 */
	public void changePhoto(User user);

	/**
	 * Change user passwrod
	 * @param user Object to change
	 */
	public void changePassword(User user);

	/**
	 * List all users that given user can add as friends
	 * @param user Object for to find new friends
	 * @return list of possible friends
	 */
	List<User> listUsersToFriend(User user);

	/**
	 * List all users that given user can add in chat
	 * @param user Object for to find new friends to chat
	 * @return list of possible friends to chat
	 */
	List<User> listUsersToChat(User user);
}
