package org.openiam.idm.srvc.continfo.service;




import org.openiam.idm.srvc.continfo.dto.Address;

/**
 * Data access interface for address that is webservices safe. Address usually exists with a parent entity
 * such as a user, organization, account, etc. Client components should use 
 * the service objects such as <code>UserMgr</code> instead of using the DAO
 * directly.
 * @author Suneet Shah
 * @version 2
 *
 */
public interface AddressWSDAO  {

	/**
	 * Return an address object for the id.
	 * @param id
	 */
	Address findById(java.lang.String id) ;
	/**
	 * Creates a new address
	 * @return True indicates success. False indicates failure.
	 * @param instance
	 */
	void add(Address instance);
	/**
	 * Updates an existing address
	 * @return True indicates success. False indicates failure.
	 * @param instace
	 */
	void update(Address instace);
	/**
	 * Removes an address
	 * @return True indicates success. False indicates failure.
	 * @param instance
	 */
	void remove(Address instance);
	
	/**
	 * Persist an array of address objects at one time. Handles add, update, delete.
	 * @param parentId
	 * @param parentType
	 * @param adrMap
	 * @return True indicates success. False indicates failure.
	 */
	void saveAddressArray(String parentId, String parentType, Address[] adrMap);
	
	/**
	 * Returns a List of Address objects for the parentId and parentType combination.
	 * @param parentId
	 * @param parentType
	 * @return  Null if no records found.
	 */	
	public Address[] findByParentAsArray(String parentId,String parentType);

	
	/**
	 * Removes all address for a parent
	 * @param parentId
	 * @param parentType
	 */
	void removeByParent(String parentId,String parentType);
	/**
	 * Returns a default address for the parentId and parentType combination. 
	 * Returns null if a match is not found.
	 * @return
	 */
	Address findDefault(String parentId,String parentType);
	/**
	 * Return an address object that matches the description. Returns null if a match
	 * is not found.
	 * @param description
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	Address findByName(String description, String parentId,String parentType);

	


	
}
