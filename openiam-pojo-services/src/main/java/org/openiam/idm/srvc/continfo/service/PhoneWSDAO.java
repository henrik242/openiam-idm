package org.openiam.idm.srvc.continfo.service;

import java.util.List;
import java.util.Map;


import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.util.ws.collection.*;

/**
 * Data access interface for Phone that is web service friendly. Phone usually exists with a parent entity
 * such as a user, organization, account, etc.  Client components should use 
 * the service objects such as <code>UserMgr</code> instead of using the DAO
 * directly.
 * @author Suneet Shah
 *
 */
public interface PhoneWSDAO { 
		//extends BaseDAO<EmailAddress, String> {

	/**
	 * Return an object for the id.
	 * @param id
	 */
	Phone findById(String id) ;
	/**
	 * Adds a new instance
	 * @param instance
	 */
	void add(Phone instance);
	/**
	 * Updates an existing instance
	 * @param instace
	 */
	void update(Phone instace);
	/**
	 * Removes an existing instance
	 * @param instance
	 */
	void remove(Phone instance);
	
	/**
	 * Persist a map of Phone objects at one time. Handles add, update, delete.
	 * @param parentId
	 * @param parentType
	 * @param adrMap
	 */
	void savePhoneArray(String parentId, String parentType, Phone[] adrMap);
	
	/**
	 * Returns a List of Phone objects for the parentId and parentType combination.
	 * @param parentId
	 * @param parentType
	 * @return
	 */	
	public Phone[] findByParentAsArray(String parentId,String parentType);

	
	/**
	 * Removes all EmailAddresses for a parent
	 * @param parentId
	 * @param parentType
	 */
	void removeByParent(String parentId,String parentType);
	/**
	 * Returns a default address for the parentId and parentType combination. 
	 * Returns null if a match is not found.
	 * @return
	 */
	Phone findDefault(String parentId,String parentType);
	/**
	 * Return an address object that matches the description. Returns null if a match
	 * is not found.
	 * @param name
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	Phone findByName(String name, String parentId,String parentType);

	
}
