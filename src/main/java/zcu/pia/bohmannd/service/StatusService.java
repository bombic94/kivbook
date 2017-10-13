package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface StatusService {

	public void insertStatus(Status status);

	public List<Status> listStatuss();

	public Status getStatus(Integer id);

	public void deleteStatus(Status status);

	List<Status> listStatusesForUser(User user);

	public List<Status> getNstatuses(List<Status> allStatuses, Integer id);
}
