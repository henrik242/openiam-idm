package org.openiam.idm.srvc.role.service;

import java.util.List;

import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Data access interface for domain model class Role.
 * @see org.openiam.idm.srvc.role.dto.Role
 */
public interface RoleDAO {

	public void add(Role transientInstance);

	public void remove(Role persistentInstance);
	
	

	public void update(Role detachedInstance);

    public Role findById( RoleId id) ;

	public List<Role> findByExample(Role instance);
	
	/**
	 * Adds a group to the Role. DAO takes care of the persistence part of this task.
	 * @param serviceId
	 * @param roleId
	 * @param groupId
	 */
	public void addGroupToRole(String serviceId, String roleId, String groupId);
	
	/**
	 * Removes all the group associations with this Role.
	 * @param serviceId
	 * @param roleId
	 */
	public void removeAllGroupsFromRole(String serviceId, String roleId);
	
	/**
	 * Removes a groups association with a role.
	 * @param serviceId
	 * @param roleId
	 * @param groupId
	 */
	public void removeGroupFromRole(String serviceId, String roleId, String groupId);
	
	/**
	 * Adds a user to a Role. DAO takes care of the persistence part of this task.
	 * @param serviceId
	 * @param roleId
	 * @param userId
	 */
	public void addUserToRole(String serviceId, String roleId, String userId);
	
	/**
	 * Removes a users association with a role
	 * @param serviceId
	 * @param roleId
	 * @param userId
	 */
	public void removeUserFromRole(String serviceId, String roleId, String userId);
	
	
	/**
	 * Get the roles for a user
	 * @param userId
	 * @return
	 */
	public List<Role> findUserRoles(String userId);
	
	/**
	 * Gets the roles a user belongs based on the serviceId that is defined.
	 * @param service
	 * @param userId
	 * @return
	 */
	public List<Role> findUserRolesByService(String service, String userId);
	
	/**
	 * Get the roles for a user that associated to the user through a group.
	 * @param userId
	 * @return
	 */
	public List<Role>findIndirectUserRoles(String userId);
	
	/**
	 * Get the users that are in a role
	 * @param roleId
	 * @return
	 */
	public List<User> findUsersInRole(String serviceId, String roleId);
	
    /**
     * Returns a list of all Roles regardless of service
     * The list is sorted by ServiceId, Role
     * @return
     */
    public List<Role>findAllRoles();
    
    /**
     * Find all the roles in a service
     * @param serviceId
     * @return
     */
    public List<Role>findRolesInService(String serviceId);
    
    /**
     * Find all the roles associated with a Group
     * @return
     */
    public List<Role> findRolesInGroup(String groupId);

    /**
     * Returns a role object if a direct relationship between a user and role exists.
     * @return
     */
    Role findDirectRoleForUser(String serviceId, String roleId, String userId);

    
}