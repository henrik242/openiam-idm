package org.openiam.idm.srvc.role.ws;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.role.dto.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.user.ws.UserArrayResponse;
import org.openiam.idm.srvc.grp.dto.*;
import org.openiam.idm.srvc.grp.ws.GroupArrayResponse;

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
public interface RoleDataWebService {

	/**
	 * Retrieves a role object based on the roleId and the domainId.
	 * Dependent objects include Group and Users collections that are associated with this Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	@WebMethod
	RoleResponse getRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);
	

	/**
	 * Adds a new role to the system
	 * 
	 * @param role
	 * @return
	 */
	
	@WebMethod
	RoleResponse addRole(
			@WebParam(name = "role", targetNamespace = "")
			org.openiam.idm.srvc.role.dto.Role role);

	/**
	 * Updates an existing role
	 * 
	 * @param role
	 */
	@WebMethod
	Response updateRole(
			@WebParam(name = "role", targetNamespace = "")
			org.openiam.idm.srvc.role.dto.Role role);

	/**
	 * Removes a role.
	 * 
	 * @param domainId
	 * @param roleId
	 */
	@WebMethod
	Response removeRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);

	/**
	 * Returns an array of roles that are in a security domain.
	 * 
	 * @param domainId
	 * @return
	 */
	@WebMethod
	RoleListResponse getRolesInDomain(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId);

	/**
	 * Returns a list of all Roles regardless of service The list is sorted by
	 * domainId, Role
	 * 
	 * @return
	 */
	@WebMethod
	RoleListResponse getAllRoles();

	/** * Attribute Methods ****** */

	/**
	 * Adds an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	@WebMethod
	RoleAttributeResponse addAttribute(
			@WebParam(name = "attribute", targetNamespace = "")
			org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Update an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	@WebMethod
	Response updateAttribute(
			@WebParam(name = "attribute", targetNamespace = "")
			org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Returns an array of RoleAttributes for the Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	@WebMethod
	RoleAttributeArrayResponse getAllAttributes(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);

	/**
	 * Returns a single RoleAttributes object based on the attributeId.
	 * 
	 * @param attrId
	 * @return
	 */
	@WebMethod
	RoleAttributeResponse getAttribute(
			@WebParam(name = "attrId", targetNamespace = "")
			String attrId);

	/**
	 * Removes a RoleAttribute specified by the attribute.
	 * 
	 * @param attr
	 */
	@WebMethod
	Response removeAttribute(
			@WebParam(name = "attribute", targetNamespace = "")
			org.openiam.idm.srvc.role.dto.RoleAttribute attribute);

	/**
	 * Removes all the attributes associated with a role.
	 * 
	 * @param domainId
	 * @param roleId
	 */
	@WebMethod
	Response removeAllAttributes(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);

	/** * Role-Group Methods ****** */

	/**
	 * Returns an array of Role objects that are linked to a Group Returns null
	 * if no roles are found.
	 * 
	 * @param groupId
	 * @return
	 */
	@WebMethod
	RoleListResponse getRolesInGroup(
			@WebParam(name = "groupId", targetNamespace = "")
			String groupId);



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
	@WebMethod
	Response isGroupInRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId, 
			@WebParam(name = "groupId", targetNamespace = "")
			String groupId);



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
	@WebMethod
	Response addGroupToRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId, 
			@WebParam(name = "groupId", targetNamespace = "")
			String groupId);

	/**
	 * Removes the association between a single group and role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @param groupId
	 */
	@WebMethod
	Response removeGroupFromRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId, 
			@WebParam(name = "groupId", targetNamespace = "")
			String groupId);

	/**
	 * Removes the association between a role and all the groups linked to it.
	 * 
	 * @param domainId
	 * @param roleId
	 */
	@WebMethod
	Response removeAllGroupsFromRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);

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
	@WebMethod
	GroupArrayResponse getGroupsInRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);

	/* ------------- User to Role Methods --------------------------------- */

	/**
	 * Adds a User to Role. This operation allows you to set additional attributes in the UserRole objects.
	 */
	@WebMethod
	Response assocUserToRole(
			@WebParam(name = "userRole", targetNamespace = "")
			UserRole userRole
			) ;
	
	/**
	 * Updates the attributes in the user role object.
	 * @param ur
	 */
	@WebMethod
	Response updateUserRoleAssoc(
			@WebParam(name = "userRole", targetNamespace = "")
			UserRole userRole) ;
	
	/**
	 * Gets a UserRole object based on the record identifier
	 * @param userRoleId
	 * @return
	 */
	@WebMethod
	UserRoleResponse getUserRoleById(
			@WebParam(name = "userRoleId", targetNamespace = "")
			String userRoleId ) ;
	
	/**
	 * Returns a list of UserRole objects
	 * @param userId
	 * @return
	 */
	@WebMethod
	UserRoleListResponse getUserRolesForUser(
			@WebParam(name = "userId", targetNamespace = "")
			String userId) ;
	
	
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
	@WebMethod
	Response addUserToRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId, 
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/**
	 * This method removes a particular user directly to a role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @param userId
	 */
	@WebMethod
	Response removeUserFromRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId, 
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

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

	@WebMethod
	Response isUserInRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId, 
			@WebParam(name = "userId", targetNamespace = "")
			String userId);
	
	/**
	 * Return an array of users that are in a particular role
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	// problem generating wsdl with this method
	@WebMethod
	UserArrayResponse getUsersInRole(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);
	
	/**
	 * Returns a list of roles that a user belongs to. Roles can be hierarchical and this operation traverses the tree to roles that are in the 
	 * hierarchy.
	 * @param userId
	 * @return
	 */
	@WebMethod
	RoleListResponse getUserRolesAsFlatList(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to.
	 * 
	 * @param userId
	 * @return
	 */
	@WebMethod
	RoleListResponse getUserRoles(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);
	
	/**
	 * Returns the roles that are directly associated with a user; ie. Does not take into
	 * account roles that may be associated with a user becuase of a group relationship.
	 * @param userId
	 * @return
	 */
	@WebMethod
	RoleListResponse getUserRolesDirect(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to within a given security domain.
	 * 
	 * @param userId
	 * @return
	 */
	
	@WebMethod
	RoleListResponse getUserRolesByDomain(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId,  
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	@WebMethod
	RoleListResponse search(
			@WebParam(name = "search", targetNamespace = "")
			RoleSearch search);
	
	/** * Role Policy Methods ****** */

	@WebMethod
	public RolePolicyResponse addRolePolicy(
			@WebParam(name = "rPolicy", targetNamespace = "")
			RolePolicy rPolicy);
	

	/**
	 * Update an attribute to the Role object.
	 * 
	 * @param attribute
	 */
	@WebMethod
	public RolePolicyResponse updateRolePolicy(
			@WebParam(name = "rolePolicy", targetNamespace = "")
			RolePolicy rolePolicy);

	/**
	 * Returns List of RolePolicy for the Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	@WebMethod
	public RolePolicyListResponse getAllRolePolicies(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "roleId", targetNamespace = "")
			String roleId);

	/**
	 * Returns a single RolePolicy object based on the attributeId.
	 * 
	 * @param attrId
	 * @return
	 */
	@WebMethod
	public RolePolicyResponse getRolePolicy(
			@WebParam(name = "rolePolicyId", targetNamespace = "")
			String rolePolicyId);

	/**
	 * Removes a RolePolicy specified by the rPolicy parameter.
	 * 
	 * @param attr
	 */
	@WebMethod
	public Response removeRolePolicy(
			@WebParam(name = "rolePolicy", targetNamespace = "")
			RolePolicy rolePolicy);


}
