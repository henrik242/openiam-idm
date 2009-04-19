package org.openiam.idm.srvc.continfo.service;



import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.util.ws.collection.*;

/**
 * Data access interface for EmailAddress that is web services friendly. EmailAddress usually exists with a parent entity
 * such as a user, organization, account, etc. Client components should use 
 * the service objects such as <code>UserMgr</code> instead of using the DAO
 * directly.
 * @author Suneet Shah
 *
 */
public interface EmailAddressWSDAO { 
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
	void add(EmailAddress instance);
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
	void saveEmailAddressArray(String parentId, String parentType, EmailAddress[] adrMap);
	

	/**
	 * Returns a List of EmailAddress objects for the parentId and parentType combination.
	 * @param parentId
	 * @param parentType
	 * @return
	 */	
	public EmailAddress[] findByParentAsArray(String parentId,String parentType);

	
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
	 * @param name
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	EmailAddress findByName(String name, String parentId,String parentType);

	
}
