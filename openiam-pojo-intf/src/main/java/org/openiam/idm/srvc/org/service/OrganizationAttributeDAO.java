package org.openiam.idm.srvc.org.service;

import java.util.List;
import java.util.Map;

import org.openiam.idm.srvc.org.dto.*;
import org.openiam.idm.srvc.user.dto.UserAttribute;


/**
 * Data access object interface for OrganizationAttribute. 
 * 
 * @author Suneet Shah
 *
 */
public interface OrganizationAttributeDAO  {

	/**
	 * Return an OrganizationAttribute object for the id.
	 * @param id
	 */
	OrganizationAttribute findById(java.lang.String id) ;
	void add(OrganizationAttribute instance);
	void update(OrganizationAttribute instace);
	void remove(OrganizationAttribute instance);
	
	/**
	 * Return a list of OrganizationAttribute objects for the organization that is specified by the parentId
	 * @param parentId
	 * @return
	 */
	List<OrganizationAttribute> findAttributesByParent(String parentId);
	/**
	 * Removes all the OrganizationAttributes that are associated with the Organization specified by the parentId.
	 * @param parentId
	 * @return The number of entities deleted.
	 */
	int removeAttributesByParent(String parentId);
		


	
}
