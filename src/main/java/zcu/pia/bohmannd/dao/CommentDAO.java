package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface CommentDAO extends AbstractDAO<Comment> {

	/**
	 * list comments for given status
	 * 
	 * @param status Status object
	 * @return comments for status
	 */
	List<Comment> listByStatus(Status status);

	/**
	 * list comments for given user
	 * 
	 * @param user User object
	 * @return comments for user
	 */
	List<Comment> listByUser(User user);

}
