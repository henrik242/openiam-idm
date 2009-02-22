package org.openiam.idm.srvc.continfo.service;

import java.util.List;
import java.util.Map;


import org.openiam.idm.srvc.continfo.dto.Address;

/**
 * Data access object for address. Address usually exists with a parent entity
 * such as a user, organization, account, etc. Client components should use 
 * the service objects such as <code>UserMgr</code> instead of using the DAO
 * directly.
 * @author Suneet Shah
 *
 */
public interface AddressDAO  {

	/**
	 * Return an address object for the id.
	 * @param id
	 */
	Address findById(java.lang.String id) ;
	/**
	 * Creates a new address
	 * @param instance
	 */
	void add(Address instance);
	/**
	 * Updates an existing address
	 * @param instace
	 */
	void update(Address instace);
	/**
	 * Removes an address
	 * @param instance
	 */
	void remove(Address instance);
	
	/**
	 * Persist a map of address objects at one time. Handles add, update, delete.
	 * @param parentId
	 * @param parentType
	 * @param adrMap
	 */
	void saveAddressMap(String parentId, String parentType, Map<String,Address> adrMap);
	
	/**
	 * Returns a Map of Address objects for the parentId and parentType combination.
	 * The map is keyed on the address.description. Address.description indicates
	 * the type of address that we have; ie. Shipping, Billing, etc.
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	Map<String, Address> findByParent(String parentId,String parentType);
	/**
	 * Returns a List of Address objects for the parentId and parentType combination.
	 * @param parentId
	 * @param parentType
	 * @return
	 */	
	public List<Address> findByParentAsList(String parentId,String parentType);

	
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
	 * Return an address object that matches the Name field. Returns null if a match
	 * is not found.
	 * @param description
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	Address findByName(String name, String parentId,String parentType);

	


	
}
