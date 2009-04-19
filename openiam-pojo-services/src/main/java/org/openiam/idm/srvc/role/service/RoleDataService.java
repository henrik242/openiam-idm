package org.openiam.idm.srvc.role.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openiam.idm.srvc.role.dto.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.grp.dto.*;

/**
 * Interface permitting the management of Roles and related objects such as
 * groups and users.
 * 
 * @author Suneet Shah
 * @version 1
 * 
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/role/service", name = "RoleDataService")
//@XmlSeeAlso({org.openiam.idm.srvc.user.dto.ObjectFactory.class,org.openiam.idm.srvc.org.dto.ObjectFactory.class,org.openiam.idm.srvc.continfo.dto.ObjectFactory.class,org.openiam.idm.srvc.grp.dto.ObjectFactory.class,org.openiam.idm.srvc.role.types.ObjectFactory.class,org.openiam.idm.srvc.role.dto.ObjectFactory.class,org.openiam.idm.srvc.meta.dto.ObjectFactory.class})

public interface RoleDataService {

	/**
	 * Retrieves a role object based on the roleId and the serviceId.
	 * Dependent objects include Group and Users collections that are associated with this Role.
	 * 
	 * @param serviceId
	 * @param roleId
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role getRole(String serviceId, String roleId);
	

	/**
	 * Adds a new role to the system
	 * 
	 * @param role
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role addRole(org.openiam.idm.srvc.role.dto.Role role);

	/**
	 * Updates an existing role
	 * 
	 * @param role
	 */
	void updateRole(org.openiam.idm.srvc.role.dto.Role role);

	/**
	 * Removes a role.
	 * 
	 * @param serviceId
	 * @param roleId
	 */
	void removeRole(String serviceId, String roleId);

	/**
	 * Returns an array of roles that are in a service.
	 * 
	 * @param serviceId
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role[] getRolesInService(String serviceId);

	/**
	 * Returns a list of all Roles regardless of service The list is sorted by
	 * ServiceId, Role
	 * 
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role[] getAllRoles();

	/** * Attribute Methods ****** */

	/**
	 * Adds an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	public void addAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Update an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	public void updateAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Returns an array of RoleAttributes for the Role.
	 * 
	 * @param serviceId
	 * @param roleId
	 * @return
	 */
	public org.openiam.idm.srvc.role.dto.RoleAttribute[] getAllAttributes(String serviceId, String roleId);

	/**
	 * Returns a single RoleAttributes object based on the attributeId.
	 * 
	 * @param attrId
	 * @return
	 */
	public org.openiam.idm.srvc.role.dto.RoleAttribute getAttribute(String attrId);

	/**
	 * Removes a RoleAttribute specified by the attribute.
	 * 
	 * @param attr
	 */
	public void removeAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute attr);

	/**
	 * Removes all the attributes associated with a role.
	 * 
	 * @param serviceId
	 * @param roleId
	 */
	public void removeAllAttributes(String serviceId, String roleId);

	/** * Role-Group Methods ****** */

	/**
	 * Returns an array of Role objects that are linked to a Group Returns null
	 * if no roles are found.
	 * 
	 * @param groupId
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role[] getRolesInGroup(String groupId);



	/**
	 * This method returns true if particular group is associated with a role.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   boolean check = roleService.isGroupInRole(serviceId, roleId, groupId);<br>
	 * </code>
	 * 
	 * @return boolean Returns True if group belongs to that roleId.
	 */
	boolean isGroupInRole(String serviceId, String roleId, String groupId);



	/**
	 * This method adds particular roleId to a particular group.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   roleService.addRoleToGroup(serviceId, roleId, groupId);<br>
	 * </code>
	 * 
	 * @param grpId
	 *            The group for which the roleId is to be added .
	 * @param roleId
	 *            The roleId which is to be added to the group.
	 */
	void addGroupToRole(String serviceId, String roleId, String groupId);

	/**
	 * Removes the association between a single group and role.
	 * 
	 * @param serviceId
	 * @param roleId
	 * @param groupId
	 */
	void removeGroupFromRole(String serviceId, String roleId, String groupId);

	/**
	 * Removes the association between a role and all the groups linked to it.
	 * 
	 * @param serviceId
	 * @param roleId
	 */
	void removeAllGroupsFromRole(String serviceId, String roleId);

	/**
	 * This method retrieves all groups for a particular role. Returns null if
	 * no groups were found.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   roleService.getGroupsInRole(serviceId, roleId);<br>
	 * </code>
	 * 
	 * @param serviceId
	 * @param roleId
	 *            The roleId for which groups has to be retrieved .
	 * 
	 */
	// problem generating wsdl with this method
	org.openiam.idm.srvc.grp.dto.Group[] getGroupsInRole(String serviceId, String roleId);

	/* ------------- User to Role Methods --------------------------------- */

	
	/**
	 * This method adds particular user directly to a role.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   roleService.addUserToRole(serviceId, roleId, userId);<br>
	 * </code>
	 * 
	 * @param serviceId
	 * @param roleId
	 *            The roleId to which the user will be associated. 
	 * @param userId
	 *            The userId to which the roleId is to be added .
	 */
	void addUserToRole(String serviceId, String roleId, String userId);

	/**
	 * This method removes a particular user directly to a role.
	 * 
	 * @param serviceId
	 * @param roleId
	 * @param userId
	 */
	void removeUserFromRole(String serviceId, String roleId, String userId);

	/**
	 * This method returns true if user belongs to that roleId.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   boolean check = roleService.isUserInRole(serviceId, roleId, userId);<br>
	 * </code>
	 * 
	 * @return boolean Returns True if user belongs to that roleId. False if it does not belong to this role.
	 */

	boolean isUserInRole(String serviceId, String roleId, String userId);
	
	/**
	 * Return an array of users that are in a particular role
	 * 
	 * @param serviceId
	 * @param roleId
	 * @return
	 */
	// problem generating wsdl with this method
	org.openiam.idm.srvc.user.dto.User[] getUsersInRole(String serviceId, String roleId);

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to.
	 * 
	 * @param userId
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role[] getUserRoles(String userId);
	
	/**
	 * Returns the roles that are directly associated with a user; ie. Does not take into
	 * account roles that may be associated with a user becuase of a group relationship.
	 * @param userId
	 * @return
	 */
	public Role[] getUserRolesDirect(String userId);

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to within a given service.
	 * 
	 * @param userId
	 * @return
	 */
	
	org.openiam.idm.srvc.role.dto.Role[] getUserRolesByService(String serviceId,  String userId);



}
