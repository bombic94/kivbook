package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface StatusDAO extends AbstractDAO<Status>{

	List<Status> listByUser(User user);

}
