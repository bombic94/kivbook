package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface LikeDAO extends AbstractDAO<Like> {

	/**
	 * list likes for given status
	 * 
	 * @param status
	 * @return likes for status
	 */
	List<Like> listByStatus(Status status);

	/**
	 * Checks if like already exists
	 * 
	 * @param like
	 * @return existing like or null
	 */
	Like getExists(Like like);

	/**
	 * list likes for given user
	 * 
	 * @param user
	 * @return likes for user
	 */
	List<Like> listByUser(User user);

}
