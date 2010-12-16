package org.openiam.base;


/**
 * Base interface for all DAOs.
 * @author Suneet Shah
 * @version 2
 */
public interface BaseDAO<T, ID> {

	/**
	 * Return an object for the id.
	 * @param id
	 */
	T findById(ID id) ;
	/**
	 * Adds a new instance
	 * @param instance
	 */
	void add(T instance);
	/**
	 * Updates an existing instance
	 * @param instace
	 */
	void update(T instace);
	/**
	 * Removes an existing instance
	 * @param instance
	 */
	void remove(T instance);

}
