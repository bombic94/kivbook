package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Status;

public interface CommentDAO extends AbstractDAO<Comment>{	
	
	/**
	 * list comments for given status
	 * @param status
	 * @return comments for status
	 */
	List<Comment> listByStatus(Status status);
	
}
