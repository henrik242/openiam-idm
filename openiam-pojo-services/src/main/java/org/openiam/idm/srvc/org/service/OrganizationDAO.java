package org.openiam.idm.srvc.org.service;

import java.util.List;
import java.util.Map;

import org.openiam.idm.srvc.org.dto.*;


/**
 * Data access object interface for Organization. 
 * 
 * @author Suneet Shah
 *
 */
public interface OrganizationDAO  {

	/**
	 * Return an Organization object for the id.
	 * @param id
	 */
	Organization findById(java.lang.String id) ;
	void add(Organization instance);
	void update(Organization instace);
	void remove(Organization instance);
	
	
	/**
	 * Returns an Organization object that is the parent of the orgId specified.
	 * Return null is no parent organizations are found.
	 * @param orgId
	 * @return
	 */
	Organization findParent(String orgId);
	
	/**
	 * Returns a list of Organization objects that are root level entities; ie. they 
	 * don't have a parent. 
	 * @return
	 */
	List<Organization> findRootOrganizations();
	
	/**
	 * Returns a List of Organization objects that are sub-organizations of the specified
	 * orgId. Returns null if no children are found. 
	 * @param orgId
	 * @return
	 */	
	List<Organization> findChildOrganization(String orgId) ;
	
	List<Organization> findAllOrganization() ;
	
	List<Organization> findOrganizationByType(String type, String parentId) ;
	
	List<Organization> search(String name, String type);

	

	


	
}
