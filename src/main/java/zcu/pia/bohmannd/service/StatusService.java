package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface StatusService {

	/**
	 * Insert new status
	 * @param status object to insert
	 */
	public void insertStatus(Status status);

	/**
	 * List all statuses
	 * @return list of all statuses
	 */
	public List<Status> listStatuses();

	/**
	 * Retrieve status by given id
	 * @param id ID of status
	 * @return Status object
	 */
	public Status getStatus(Integer id);

	/**
	 * Delete given status
	 * @param status Object to delete
	 */
	public void deleteStatus(Status status);

	/**
	 * List statuses for given user. Finds out all statuses by user
	 * and statuses by his friends.
	 * @param user Object
	 * @return list of statuses for user
	 */
	List<Status> listStatusesForUser(User user);

	/**
	 * Returns given number of statuses from all statuses. Start on given
	 * index and end after n iterations
	 * @param allStatuses list of All statuses
	 * @param id id of page
	 * @param n number of statuses for one page
	 * @return list of statuses for given page
	 */
	public List<Status> getNstatuses(List<Status> allStatuses, Integer id, Integer n);
}
