package org.openiam.idm.srvc.grp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openiam.idm.srvc.grp.dto.*;
import org.openiam.idm.srvc.user.dto.User;


/**
 * Data access object interface for Group. 
 * 
 * @author Suneet Shah
 *
 */
public interface GroupDAO  {

	/**
	 * Return an Group object for the id.
	 * @param id
	 */
	Group findById(java.lang.String id) ;
	Group findById(java.lang.String id, boolean dependants); 
	void add(Group instance);
	void update(Group instace);
	void remove(Group instance);
	
	void addUserToGroup(String groupId, String user);
	void removeUserFromGroup(String groupId, String user);
	
	
	/**
	 * Returns an Group object that is the parent of the orgId specified.
	 * Return null is no parent organizations are found.
	 * @param groupId
	 * @return - null if no parent group is found.
	 */
	Group findParent(String groupId, boolean dependants);
	
	
	/**
	 * Returns a List of Group objects that are sub-groups of the specified
	 * parentGroupId. Returns null if no children are found. 
	 * @param parentGroupId
	 * @return
	 */	
	List<Group> findChildGroup(String parentGroupId) ;
	
	/**
	 * Return a list of root level group object. Root level group object do not have parent groups.
	 * @return
	 */
	List<Group> findRootGroups();
	
	/**
	 * Returns a list of Groups that a user is associated with
	 * @return
	 */
	List<Group> findGroupsForUser(String userId);
	
	

	/**
	 * Removes the groups specified by the groupIdList. groupIdList is a string containing a concatenated
	 * list of groupIds.
	 * @param groupIdList
	 * @return The number of entities deleted.
	 */
	public int removeGroupList(String groupIdList);
	
	public List<Group> findAllGroups();
	
	public List<Group> findGroupsInRole(String serviceId, String roleId);
	
	public User findUserInGroup(String groupId,String userId);

	

	


	
}
