package zcu.pia.bohmannd.dao;

import java.util.List;

import zcu.pia.bohmannd.model.AbstractObject;

public interface AbstractDAO<T extends AbstractObject> {

	/**
	 * Create new or update row in db
	 * @param t
	 * @return created or updated row
	 */
	T save (T t);
	
	/**
	 * List all rows in table
	 * @return list of rows
	 */
	List<T> list();
	
	/**
	 * Get row by given id
	 * @param id
	 * @return row with id
	 */
	T getById(Integer id);
	
	/**
	 * Delete given row
	 * @param t
	 */
	void delete(T t);	
	
}
