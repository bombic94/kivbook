package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;

public interface FriendshipDAO extends AbstractDAO<Friendship>{
	
	/**
	 * List friendships for given user
	 * @param user
	 * @return list of friendships
	 */
	List<Friendship> listFriendshipsByUser(User user);
	
	/**
	 * List pending friendships for given user
	 * @param user
	 * @return list of friendships
	 */
	List<Friendship> listPendingFriendshipsByUser(User user);
	
	/**
	 * List possible friendships for given user
	 * @param user
	 * @return list of friendships
	 */
	List<Friendship> listAllFriendshipsByUser(User user);
	
	/**
	 * accept friendship
	 * @param friendship
	 */
	void accept(Friendship friendship);	
	
}
