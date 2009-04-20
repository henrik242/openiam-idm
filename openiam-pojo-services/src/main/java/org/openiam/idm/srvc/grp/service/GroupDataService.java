package org.openiam.idm.srvc.grp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;

/**
 * <code>GroupDataService</code> provides a service to manage groups as well
 * as related objects such as Users. Groups are stored in an hierarchical
 * relationship. A user belongs to one or more groups.<br>
 * Groups are often modeled after an organizations structure.
 * 
 * @author Suneet Shah
 * @version 2.0
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/grp/service", name = "GroupDataService")
/*
@XmlSeeAlso( { org.openiam.idm.srvc.user.dto.ObjectFactory.class,
		org.openiam.idm.srvc.continfo.dto.ObjectFactory.class,
		org.openiam.idm.srvc.org.dto.ObjectFactory.class,
		org.openiam.idm.srvc.grp.dto.ObjectFactory.class,
		org.openiam.idm.srvc.role.dto.ObjectFactory.class,
		org.openiam.idm.srvc.meta.dto.ObjectFactory.class,
		org.openiam.idm.srvc.grp.types.ObjectFactory.class })
*/		
public interface GroupDataService {

	/**
	 * This method gets all the groups from the database.<br>
	 * For example:
	 * <p>
	 * <code>
	 *  GroupDataService grpManager = new GroupDataServiceImpl();<br>
	 *  List allGrp = grpManager.getAllGroups(true,true);<br>
	 * </code>
	 * 
	 * @param subgroups -
	 *            True will force the system to traverse the tree of child
	 *            groups
	 * @param dependants -
	 *            True tells the system to retrieve dependent objects as well.
	 * @return List of Group objects.
	 */
	public List<Group> getAllGroupsWithDependents(boolean subgroups);

	/**
	 * Returns a list of Group objects that is flat in structure.
	 * 
	 * @return
	 */
	public List<Group> getAllGroups();

	/**
	 * This method creates a new group For example:
	 * <p>
	 * <code>
	 * Group grp = new Group();
	 * grp.setGrpId(groupId);
	 * grp.setGrpName("Test Group");
	 * 
	 * grpManager.addGroup(grpValue);<br>
	 * </code>
	 * 
	 * @param Group
	 */

	public void addGroup(Group grp);

	/**
	 * This method retrieves an existing group object. Dependent objects such as
	 * users are not retrieved. Null is returned if the groupId is not found.
	 * 
	 * @param grpId
	 */
	public Group getGroup(String grpId);

	/**
	 * This method retrieves an existing group along with an dependant objects
	 * that it may contain. null is returned if the groupId is not found.
	 * 
	 * @param grpId
	 * @return
	 */
	public Group getGroupWithDependants(String grpId);

	/**
	 * This method updates and existing group in database. For example:
	 * <p>
	 * <code>
	 *   grpManager.updateGroup(grpValue);<br>
	 * </code>
	 * 
	 * @param grpValue
	 *            The bulk accessor for Group.
	 */
	public void updateGroup(Group grp);

	/**
	 * This method removes group for a particular grpId. If the group has sub
	 * groups they will be deleted as well. For example:
	 * <p>
	 * <code>
	 *   grpManager.removeGroup(grpId);<br>
	 * </code>
	 * 
	 * @param grpId
	 *            The grpId to be removed.
	 */
	public void removeGroup(String grpId);

	/**
	 * Returns all the groups that are the immediate children of the parent
	 * group. For example:
	 * <p>
	 * <code>
	 *  List allGrp = grpManager.getChildGroups(parentGroupId, true);<br>
	 * </code>
	 * 
	 * @param parentGroupId
	 * @param subGroups -
	 *            true to retrieve the group hierarchy. false, to retrieve just
	 *            the current level of groups
	 * @return List of Group objects. Returns null if no groups are found.
	 */
	public List<Group> getChildGroups(String parentGroupId, boolean subgroups);

	/**
	 * Removes all the children of a group regardless of how deep the hierarchy
	 * is. This method does not remove the groupId specified by the
	 * parentGroupId
	 * 
	 * @param parentGroupId
	 * @return The number of entities that were deleted.
	 */
	public int removeChildGroups(String parentGroupId);

	/**
	 * Returns the parent Group object for the groupId that is passed in. If no
	 * parent group is found, the system return null.
	 * 
	 * @param parentGroupId
	 * @param dependants -
	 *            True indicates that dependant objects will be loaded as well.
	 * @return
	 */
	public Group getParentGroup(String groupId, boolean dependants);

	/**
	 * --------------------- Group Methods Related to Users --------------
	 */

	/**
	 * Returns true or false depending on whether a user belongs to a particular
	 * group or not. If a group has been marked as "Inherits from Parent", then
	 * the system will check to see if the user belongs to one of the parent
	 * group objects.
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 */
	public boolean isUserInGroup(String groupId, String userId);

	/**
	 * Returns List of Group objects that represent the groups this user belongs
	 * to. For example:
	 * <p>
	 * <code>
	 *   grpManager.getUserInGroups(userId);<br>
	 * </code>
	 * 
	 * @param userId
	 * @return
	 */
	public List<Group> getUserInGroups(String userId);
	
	/**
	 * Return an array of Groups that a user does not belong to
	 * @param userId
	 * @param nested - True, traverse the group hierarchy.  False, search the current hierarchy
	 * @param parentGroupId - Group where the traversing will start
	 * @return
	 */
	public List<Group> getGroupsNotLinkedToUser(String userId, String parentGroupId, boolean nested);

	/**
	 * This method gets all users assigned to a particular group .<br>
	 * For example:
	 * <p>
	 * <code>
	 *     Set userList = grpManager.getUsersByGroup(groupId);<br>
	 * </code>
	 * 
	 * 
	 * @param grpId
	 *            The group to which users belong .
	 * 
	 * @return List of User object .
	 */
	@WebMethod
	@XmlJavaTypeAdapter(org.openiam.idm.srvc.user.dto.UserSetAdapter.class)
	public Set<org.openiam.idm.srvc.user.dto.User> getUsersByGroup(String grpId);

	/**
	 * This method adds the user to a group .<br>
	 * For example:
	 * <p>
	 * <code>
	 *     grpManager.addUserToGroup(groupId,userId);<br>
	 * </code>
	 * 
	 * 
	 * @param userId
	 *            User to be added to group.
	 * 
	 * @param grpId
	 *            Group to which user will be added .
	 */
	public void addUserToGroup(String grpId, String userId);

	/**
	 * This method removes user from a group .<br>
	 * For example:
	 * <p>
	 * <code>
	 *     grpManager.removeUserGroup(groupId,userId);<br>
	 * </code>
	 * 
	 * @param grpId
	 *            Group from where user would be removed .
	 * @param userId
	 *            User which is to be removed from group .
	 * 
	 */
	public void removeUserFromGroup(String groupId, String userId);

	/* --------------------- Group Methods Related to Roles -------------- */

	/**
	 * Returns a list of Groups associated with a role.
	 * 
	 * @param parentGroupId
	 * @return
	 */

	// note: next four methods were skipped due to issues with web service generation re GroupRole object
	
/*	
	@WebMethod
	List<Group> getGroupsInRole(
			@WebParam(name = "serviceId", targetNamespace = "")
			java.lang.String serviceId,
			@WebParam(name = "roleId", targetNamespace = "")
			java.lang.String roleId);
*/
	/**
	 * Associates a group to a role
	 * 
	 * @param groupId
	 * @param serviceId
	 * @param roleId
	 */
/*	
	@WebMethod
	public void addGroupToRole(
			@WebParam(name = "groupId", targetNamespace = "")
			java.lang.String groupId,
			@WebParam(name = "serviceId", targetNamespace = "")
			java.lang.String serviceId,
			@WebParam(name = "roleId", targetNamespace = "")
			java.lang.String roleId);
*/
	/**
	 * Removes a group from a role
	 * 
	 * @param groupId
	 * @param serviceId
	 * @param roleId
	 */
/*	@WebMethod
	public void removeGroupFromRole(
			@WebParam(name = "groupId", targetNamespace = "")
			java.lang.String groupId,
			@WebParam(name = "serviceId", targetNamespace = "")
			java.lang.String serviceId,
			@WebParam(name = "roleId", targetNamespace = "")
			java.lang.String roleId);
*/
	/**
	 * Removes all groups from a role that are currently associated with a role.
	 * 
	 * @param serviceId
	 * @param roleId
	 */
/*	
	@WebMethod
	public void removeAllGroupsFromRole(
			@WebParam(name = "serviceId", targetNamespace = "")
			java.lang.String serviceId,
			@WebParam(name = "roleId", targetNamespace = "")
			java.lang.String roleId);
*/
	/**
	 * Adds an attribute to the Group object.
	 * 
	 * @param attribute
	 */
	public void addAttribute(GroupAttribute attribute);

	/**
	 * Update an attribute to the Group object.
	 * 
	 * @param attribute
	 */
	public void updateAttribute(GroupAttribute attribute);
	
	/**
	 * Saves a collection of attributes. The method will determine if the add or update mehtods should be 
	 * called internally.
	 */
	public void saveAttributes(GroupAttribute[] groupAttr);

	/**
	 * Returns a Map of GroupAttribute for the Group. The map is keyed on the
	 * "NAME' element of this object.
	 * 
	 * @param groupId
	 * @return java.util.HashMap of GroupAttribute objects
	 */
	@WebMethod
	@XmlJavaTypeAdapter(org.openiam.idm.srvc.grp.dto.GroupAttributeMapAdapter.class)
	public Map<String, org.openiam.idm.srvc.grp.dto.GroupAttribute> getAllAttributes(
			String groupId);

	/**
	 * Returns a single GroupAttributes object based on the attributeId.
	 * 
	 * @param attrId
	 * @return
	 */
	public GroupAttribute getAttribute(String attrId);

	/**
	 * Removes a GroupAttribute specified by the attribute.
	 * 
	 * @param userId
	 * @param attributeId
	 */
	public void removeAttribute(GroupAttribute attr);

	/**
	 * Removes all the attributes associated with a groupId.
	 * 
	 * @param userId
	 */
	public void removeAllAttributes(String groupId);

}