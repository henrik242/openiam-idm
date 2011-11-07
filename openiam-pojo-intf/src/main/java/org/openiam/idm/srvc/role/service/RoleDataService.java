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
//@XmlSeeAlso({org.openiam.idm.srvc.user.dto.ObjectFactory.class,org.openiam.idm.srvc.org.dto.ObjectFactory.class,org.openiam.idm.srvc.continfo.dto.ObjectFactory.class,org.openiam.idm.srvc.grp.dto.ObjectFactory.class,org.openiam.idm.srvc.role.types.ObjectFactory.class,org.openiam.idm.srvc.role.dto.ObjectFactory.class,org.openiam.idm.srvc.meta.dto.ObjectFactory.class})
public interface RoleDataService {

	/**
	 * Retrieves a role object based on the roleId and the domainId.
	 * Dependent objects include Group and Users collections that are associated with this Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	org.openiam.idm.srvc.role.dto.Role getRole(String domainId, String roleId);
	

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
	 * @param domainId
	 * @param roleId
	 */
	int removeRole(String domainId, String roleId);

	/**
	 * Returns an array of roles that are in a security domain.
	 * 
	 * @param domainId
	 * @return
	 */
	List<Role> getRolesInDomain(String domainId);

	/**
	 * Returns a list of all Roles regardless of service The list is sorted by
	 * domainId, Role
	 * 
	 * @return
	 */
	List<Role> getAllRoles();

	/** * Role Policy Methods ****** */

	public RolePolicy addRolePolicy(RolePolicy rPolicy);

	/**
	 * Update an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	public RolePolicy updateRolePolicy(RolePolicy rPolicy);

	/**
	 * Returns List of RolePolicy for the Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	public List<RolePolicy> getAllRolePolicies(String domainId, String roleId);

	/**
	 * Returns a single RolePolicy object based on the attributeId.
	 * 
	 * @param attrId
	 * @return
	 */
	public RolePolicy getRolePolicy(String rolePolicyId);

	/**
	 * Removes a RolePolicy specified by the rPolicy parameter.
	 * 
	 * @param attr
	 */
	public void removeRolePolicy(RolePolicy rPolicy);


	
	
	/** * Attribute Methods ****** */

	/**
	 * Adds an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	public RoleAttribute addAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Update an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	public void updateAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Returns an array of RoleAttributes for the Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	public org.openiam.idm.srvc.role.dto.RoleAttribute[] getAllAttributes(String domainId, String roleId);

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
	 * @param domainId
	 * @param roleId
	 */
	public void removeAllAttributes(String domainId, String roleId);

	/** * Role-Group Methods ****** */

	/**
	 * Returns an array of Role objects that are linked to a Group Returns null
	 * if no roles are found.
	 * 
	 * @param groupId
	 * @return
	 */
	List<Role> getRolesInGroup(String groupId);



	/**
	 * This method returns true if particular group is associated with a role.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   boolean check = roleService.isGroupInRole(domainId, roleId, groupId);<br>
	 * </code>
	 * 
	 * @return boolean Returns True if group belongs to that roleId.
	 */
	boolean isGroupInRole(String domainId, String roleId, String groupId);



	/**
	 * This method adds particular roleId to a particular group.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   roleService.addRoleToGroup(domainId, roleId, groupId);<br>
	 * </code>
	 * 
	 * @param grpId
	 *            The group for which the roleId is to be added .
	 * @param roleId
	 *            The roleId which is to be added to the group.
	 */
	void addGroupToRole(String domainId, String roleId, String groupId);

	/**
	 * Removes the association between a single group and role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @param groupId
	 */
	void removeGroupFromRole(String domainId, String roleId, String groupId);

	/**
	 * Removes the association between a role and all the groups linked to it.
	 * 
	 * @param domainId
	 * @param roleId
	 */
	void removeAllGroupsFromRole(String domainId, String roleId);

	/**
	 * This method retrieves all groups for a particular role. Returns null if
	 * no groups were found.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   roleService.getGroupsInRole(domainId, roleId);<br>
	 * </code>
	 * 
	 * @param domainId
	 * @param roleId
	 *            The roleId for which groups has to be retrieved .
	 * 
	 */
	// problem generating wsdl with this method
	org.openiam.idm.srvc.grp.dto.Group[] getGroupsInRole(String domainId, String roleId);

	/* ------------- User to Role Methods --------------------------------- */

	/**
	 * Adds a User to Role. This operation allows you to set additional attributes in the UserRole objects.
	 */
	void assocUserToRole(UserRole ur) ;
	
	/**
	 * Updates the attributes in the user role object.
	 * @param ur
	 */
	void updateUserRoleAssoc(UserRole ur) ;
	
	/**
	 * Gets a UserRole object based on the record identifier
	 * @param userRoleId
	 * @return
	 */
	UserRole getUserRoleById(String userRoleId ) ;
	
	/**
	 * Returns a list of UserRole objects
	 * @param userId
	 * @return
	 */
	List<UserRole> getUserRolesForUser(String userId) ;
	
	
	
	/**
	 * This method adds particular user directly to a role.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   roleService.addUserToRole(domainId, roleId, userId);<br>
	 * </code>
	 * 
	 * @param domainId
	 * @param roleId
	 *            The roleId to which the user will be associated. 
	 * @param userId
	 *            The userId to which the roleId is to be added .
	 */
	void addUserToRole(String domainId, String roleId, String userId);

	/**
	 * This method removes a particular user directly to a role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @param userId
	 */
	void removeUserFromRole(String domainId, String roleId, String userId);

	/**
	 * This method returns true if user belongs to that roleId.<br>
	 * For example:
	 * <p>
	 * <code>
	 *   boolean check = roleService.isUserInRole(domainId, roleId, userId);<br>
	 * </code>
	 * 
	 * @return boolean Returns True if user belongs to that roleId. False if it does not belong to this role.
	 */

	boolean isUserInRole(String domainId, String roleId, String userId);
	
	/**
	 * Return an array of users that are in a particular role
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	// problem generating wsdl with this method
	User[] getUsersInRole(String domainId, String roleId);

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to.
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getUserRoles(String userId);
	
	/**
	 * Returns a list of roles that a user belongs to. Roles can be hierarchical and this operation traverses the tree to roles that are in the 
	 * hierarchy.
	 * @param userId
	 * @return
	 */
	List<Role> getUserRolesAsFlatList(String userId);
	
	/**
	 * Returns the roles that are directly associated with a user; ie. Does not take into
	 * account roles that may be associated with a user becuase of a group relationship.
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRolesDirect(String userId);

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to within a given security domain.
	 * 
	 * @param userId
	 * @return
	 */
	
	List<Role> getUserRolesByDomain(String domainId,  String userId);

	List<Role> search(RoleSearch search);
	

}
