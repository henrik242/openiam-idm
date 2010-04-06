package org.openiam.idm.srvc.continfo.service;

import java.util.List;
import java.util.Map;

import org.openiam.base.BaseDAO;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;

/**
 * Data access object for EmailAddress. EmailAddress usually exists with a parent entity
 * such as a user, organization, account, etc. Client components should use 
 * the service objects such as <code>UserMgr</code> instead of using the DAO
 * directly.
 * @author Suneet Shah
 *
 */
public interface EmailAddressDAO { 
		//extends BaseDAO<EmailAddress, String> {

	/**
	 * Return an object for the id.
	 * @param id
	 */
	EmailAddress findById(String id) ;
	/**
	 * Adds a new instance
	 * @param instance
	 */
	EmailAddress add(EmailAddress instance);
	/**
	 * Updates an existing instance
	 * @param instace
	 */
	void update(EmailAddress instace);
	/**
	 * Removes an existing instance
	 * @param instance
	 */
	void remove(EmailAddress instance);
	
	/**
	 * Persist a map of EmailAddress objects at one time. Handles add, update, delete.
	 * @param parentId
	 * @param parentType
	 * @param adrMap
	 */
	void saveEmailAddressMap(String parentId, String parentType, Map<String,EmailAddress> adrMap);
	
	/**
	 * Returns a Map of EmailAddress objects for the parentId and parentType combination.
	 * The map is keyed on the address.description. Address.description indicates
	 * the type of address that we have; ie. Shipping, Billing, etc.
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	Map<String, EmailAddress> findByParent(String parentId,String parentType);
	/**
	 * Returns a List of EmailAddress objects for the parentId and parentType combination.
	 * @param parentId
	 * @param parentType
	 * @return
	 */	
	public List<EmailAddress> findByParentAsList(String parentId,String parentType);

	
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
	EmailAddress findDefault(String parentId,String parentType);
	/**
	 * Return an address object that matches the Name field. Returns null if a match
	 * is not found.
	 * @param description
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	EmailAddress findByName(String name, String parentId,String parentType);

	
}
