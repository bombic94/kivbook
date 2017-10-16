package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface StatusDAO extends AbstractDAO<Status> {

	/**
	 * List statuses for given user. Finds out all statuses by user and statuses by
	 * his friends.
	 * 
	 * @param user
	 *            Object
	 * @return list of statuses for user
	 */
	List<Status> listByUser(User user);

}
