package org.openiam.idm.srvc.cat.service;

import java.util.List;

import org.openiam.idm.srvc.cat.dto.Category;

/**
 * DAO Interface for Category
 * @author suneet shah
 *
 */
public interface CategoryDAO {

	void add(Category transientInstance);

	void remove(Category persistentInstance);

	Category update(Category detachedInstance);

	Category findById(java.lang.String id);

	List<Category> findByExample(Category instance);
	
	/**
	 * Return a list of Categories where the parentId is null.
	 * @return
	 */
	List<Category> findRootCategories();
	/**
	 * Return a list of Categories for the specified parentId.
	 * @param parentId
	 * @return
	 */
	List<Category> findChildCategories(String parentId);
	
	/**
	 * Removes a list of categories
	 * @param catIdList
	 * @return
	 */
	public int removeGroupList(String catIdList);

}