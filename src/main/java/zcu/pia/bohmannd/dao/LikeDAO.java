package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;

public interface LikeDAO extends AbstractDAO<Like>{

	/**
	 * list likes for given status
	 * @param status
	 * @return likes for status
	 */
	List<Like> listByStatus(Status status);
	
}
