package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface LikeService {

	/**
	 * Insert new like
	 * @param like Object to insert
	 */
	public void insertLike(Like like);

	/**
	 * List all likes
	 * @return list of all likes
	 */
	public List<Like> listLikes();

	/**
	 * Retrieve like by given id
	 * @param id ID of like
	 * @return Like object
	 */
	public Like getLike(Integer id);

	/**
	 * Delete given like
	 * @param like Object to delete
	 */
	public void deleteLike(Like like);

	/**
	 * List all likes for given status
	 * @param status Status object
	 * @return list of likes for status
	 */
	public List<Like> listLikesByStatus(Status status);

	/**
	 * Return like with information if like was already given
	 * @param like Object to find out
	 * @return like Object with information about like
	 */
	public Like isLiked(Like like);

	/**
	 * List all likes for given user
	 * @param user User object
	 * @return list of likes for user
	 */
	public List<Like> listLikesByUser(User user);
}
