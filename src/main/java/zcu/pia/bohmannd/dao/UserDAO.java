package zcu.pia.bohmannd.dao;

import zcu.pia.bohmannd.model.Chat;
import zcu.pia.bohmannd.model.User;

public interface UserDAO extends AbstractDAO<User> {

	/**
	 * Get user by given username
	 * @param username String value of username
	 * @return User
	 */
	User getByUsername(String username);

	/**
	 * Update user settings (name, dateofbirth, gender)
	 * @param user User to be updated
	 */
	void updateSettings(User user);

	/**
	 * Update user profile picture
	 * @param user User to be updated
	 */
	void updatePhoto(User user);

	/**
	 * Update user password
	 * @param user User to be updated
	 */
	void updatePassword(User user);

	/**
	 * Set active chat for given user
	 * @param user User whose chat setting active
	 * @param chat Chat to set active
	 */
	void setActiveChat(User user, Chat chat);

	/**
	 * Get active chat for given user
	 * @param user User whose chat getting
	 * @return Chat
	 */
	Chat getActiveChat(User user);
}
