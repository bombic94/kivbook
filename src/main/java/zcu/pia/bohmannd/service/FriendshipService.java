package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;

public interface FriendshipService {

	/**
	 * Insert new friendship
	 * @param friendship Object to insert
	 */
	public void insertFriendship(Friendship friendship);

	/**
	 * List all friendships
	 * @return list of all friendships
	 */
	public List<Friendship> listFriendships();

	/**
	 * Retrieve friendship by given id
	 * @param id ID of friendship
	 * @return
	 */
	public Friendship getFriendship(Integer id);

	/**
	 * Delete given friendship
	 * @param friendship Object to delete
	 */
	public void deleteFriendship(Friendship friendship);

	/**
	 * List friendships for given user
	 * @param user User object
	 * @return list of friendships for user
	 */
	public List<Friendship> listFriendshipByUser(User user);

	/**
	 * List pending friendships for given user
	 * @param user User object
	 * @return list of pending friendships for user
	 */
	public List<Friendship> listPendingFriendshipByUser(User user);

	/**
	 * List accepted and pending friendships for given user
	 * @param user User object
	 * @return list of accepted and pending friendships for user
	 */
	public List<Friendship> listPossibleFriendshipByUser(User user);

	/**
	 * Accept pending friendship
	 * @param friendship Friendship object to accept
	 */
	public void acceptFriendship(Friendship friendship);
}
