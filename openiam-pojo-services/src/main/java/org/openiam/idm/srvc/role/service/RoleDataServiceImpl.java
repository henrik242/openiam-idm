package org.openiam.idm.srvc.role.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleAttribute;
import org.openiam.idm.srvc.role.dto.RoleConstant;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.user.dto.User;

import org.openiam.idm.srvc.user.dto.UserConstant;
import org.openiam.idm.srvc.user.service.UserDataService;

import java.util.*;

import javax.jws.WebService;

//Note: as per spec serviceName goes in impl class and name goes in interface
		
@WebService(endpointInterface = "org.openiam.idm.srvc.role.service.RoleDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/role/service", 
		serviceName = "RoleDataWebService")
public class RoleDataServiceImpl implements RoleDataService {

	RoleDAO roleDao;
	RoleAttributeDAO roleAttributeDAO;
	UserDataService userManager;

	private static final Log log = LogFactory.getLog(RoleDataServiceImpl.class);

	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public Role addRole(Role role) {
		if (role == null)
			throw new NullPointerException("role object is null");

		roleDao.add(role);
		return role;
	}

	public Role getRole(String serviceId, String roleId) {
		if (roleId == null)
			throw new NullPointerException("roleId is null");

		Role rl = roleDao.findById(new RoleId(serviceId, roleId));
		
		//if (!org.hibernate.Hibernate.isInitialized(rl.getUsers())) {
//		if (rl != null) {
//			org.hibernate.Hibernate.initialize(rl.getUsers());
//			org.hibernate.Hibernate.initialize(rl.getGroups());	
//		}
		return rl;

	}
	

	public void updateRole(Role role) {
		if (role == null)
			throw new NullPointerException("role object is null");

		roleDao.update(role);

	}

	/**
	 * Returns a list of all Roles regardless of service The list is sorted by
	 * ServiceId, Role
	 * 
	 * @return
	 */
	public Role[] getAllRoles() {

		List<Role> roleList = roleDao.findAllRoles();
//		if (roleList != null) {
//			org.hibernate.Hibernate.initialize(roleList);
//		}
		return roleListToArray(roleList);
	}

	public void removeRole(String serviceId, String roleId) {
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId object is null");

		Role rl = new Role(new RoleId(serviceId, roleId));

		this.roleAttributeDAO.deleteRoleAttributes(serviceId, roleId);
		this.roleDao.remove(rl);
	}

	public Role[] getRolesInService(String serviceId) {
		long start = System.currentTimeMillis();
		
		List<Role> rlList = roleDao.findRolesInService(serviceId);
		
		long end = System.currentTimeMillis();
		System.out.println("findRolesInService: " + (end-start));
		
		if (rlList == null || rlList.size() == 0)
			return null;
		int size = rlList.size();
		Role[] rlAry = new Role[size];
		rlAry = rlList.toArray(rlAry);
		return rlAry;

	}

	/* ---------------------- RoleAttribute Methods --------------- */

	public void addAttribute(RoleAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getRoleAttrId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getRoleId() == null) {
			throw new NullPointerException(
					"Role has not been associated with this attribute.");
		}

		roleAttributeDAO.add(attribute);
	}

	public RoleAttribute[] getAllAttributes(String serviceId, String roleId) {

		if (roleId == null) {
			throw new NullPointerException("groupId is null");
		}

		Role role = roleDao.findById(new RoleId(serviceId, roleId));
		Set attrSet = role.getRoleAttributes();
		if (attrSet != null && attrSet.isEmpty())
			return null;
		return this.roleAttrSetToArray(attrSet);
	}

	public RoleAttribute getAttribute(String attrId) {
		if (attrId == null) {
			throw new NullPointerException("attrId is null");
		}
		return roleAttributeDAO.findById(attrId);
	}

	public void removeAllAttributes(String serviceId, String roleId) {
		if (roleId == null) {
			throw new NullPointerException("roleId is null");
		}
		this.roleAttributeDAO.deleteRoleAttributes(serviceId, roleId);

	}

	public void removeAttribute(RoleAttribute attr) {
		if (attr == null) {
			throw new NullPointerException("attr is null");
		}
		if (attr.getRoleAttrId() == null) {
			throw new NullPointerException("attrId is null");
		}

		roleAttributeDAO.remove(attr);

	}

	public void updateAttribute(RoleAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getRoleAttrId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getRoleId() == null) {
			throw new NullPointerException(
					"Role has not been associated with this attribute.");
		}

		roleAttributeDAO.update(attribute);
	}




	/* ------------- Group to Role Methods --------------------------------- */

	public void addGroupToRole(String serviceId, String roleId, String groupId) {
		// TODO Auto-generated method stub
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId is null");
		if (groupId == null)
			throw new NullPointerException("groupId is null");

		roleDao.addGroupToRole(serviceId, roleId, groupId);

	}

	public Group[] getGroupsInRole(String serviceId, String roleId) {
		Role rl = roleDao.findById(new RoleId(serviceId, roleId));
		if (rl == null) {
			log.error("Role not found for roleId =" + roleId);
			throw new ObjectNotFoundException();
		}
		//org.hibernate.Hibernate.initialize(rl.getGroups());
		Set<Group> grpSet = rl.getGroups();
		if (grpSet == null || grpSet.isEmpty()) {
			return null;
		}
		return this.groupSetToArray(grpSet);

	}

	public boolean isGroupInRole(String serviceId, String roleId, String groupId) {

		Role rl = roleDao.findById(new RoleId(serviceId, roleId));
		if (rl == null) {
			log.error("Role not found for roleId =" + roleId);
			throw new ObjectNotFoundException();
		}
		//org.hibernate.Hibernate.initialize(rl.getGroups());
		Set<Group> grpSet = rl.getGroups();
		if (grpSet == null || grpSet.isEmpty()) {
			return false;
		}
		Iterator<Group> it = grpSet.iterator();
		while (it.hasNext()) {
			Group g = it.next();
			if (g.getGrpId().equalsIgnoreCase(groupId)) {
				return true;
			}
		}
		return false;
	}

	public void removeGroupFromRole(String serviceId, String roleId,
			String groupId) {
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId object is null");
		if (groupId == null)
			throw new NullPointerException("groupId object is null");

		this.roleDao.removeGroupFromRole(serviceId, roleId, groupId);

	}

	public void removeAllGroupsFromRole(String serviceId, String roleId) {
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId object is null");

		roleDao.removeAllGroupsFromRole(serviceId, roleId);
	}

	public Role[] getRolesInGroup(String groupId) {
		// TODO Auto-generated method stub

		if (groupId == null)
			throw new NullPointerException("groupid is null");

		List<Role> roleList = roleDao.findRolesInGroup(groupId);
		if (roleList == null || roleList.isEmpty())
			return null;

		int size = roleList.size();
		Role[] roleAry = new Role[size];
		roleAry = roleList.toArray(roleAry);
		return roleAry;

	}


	
	/* ------------- User to Role Methods --------------------------------- */
	
	public void addUserToRole(String serviceId, String roleId, String userId) {
		// TODO Auto-generated method stub
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId object is null");
		if (userId == null)
			throw new NullPointerException("userId object is null");

		roleDao.addUserToRole(serviceId, roleId, userId);
	}
	
	public boolean isUserInRole(String serviceId, String roleId, String userId) {
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId object is null");
		if (userId == null)
			throw new NullPointerException("userIdId object is null");
		
		// check if the user is directly linked to a role
		Role rl = roleDao.findDirectRoleForUser(serviceId, roleId, userId);
		if (rl != null)
			return true;
		// check if the user is linked to a role through a group.
		List<Role> roleList =  roleDao.findIndirectUserRoles(userId);

		if (roleList != null)
			return true;		
		return false;
	}

	public void removeUserFromRole(String serviceId, String roleId,
			String userId) {
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		if (serviceId == null)
			throw new NullPointerException("serviceId object is null");
		if (userId == null)
			throw new NullPointerException("userIdId object is null");

		roleDao.removeUserFromRole(serviceId, roleId, userId);

	}

	/**
	 * Returns the roles that are directly associated with a user; ie. Does not take into
	 * account roles that may be associated with a user becuase of a group relationship.
	 * @param userId
	 * @return
	 */
	public Role[] getUserRolesDirect(String userId) {
		if (userId == null)
			throw new NullPointerException("userIdId is null");

		List<Role> roleList = roleDao.findUserRoles(userId);
		if (roleList == null || roleList.size() == 0)
			return null;
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		
		int size = newRoleSet.size();
		Role[] roleAry = new Role[size];
		return newRoleSet.toArray(roleAry);

	}
	
	
	/**
	 * Returns an array of roles that a user belongs to.
	 */
	public Role[] getUserRoles(String userId) {
		if (userId == null)
			throw new NullPointerException("userIdId is null");

		List<Role> roleList = roleDao.findUserRoles(userId);
		if (roleList == null || roleList.size() == 0)
			return null;
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		
		roleList =  roleDao.findIndirectUserRoles(userId);
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.INDIRECT, newRoleSet);
		}
		int size = newRoleSet.size();
		Role[] roleAry = new Role[size];
		return newRoleSet.toArray(roleAry);

	}

	
	public Role[] getUserRolesByService(String serviceId,  String userId) {
		if (userId == null)
			throw new NullPointerException("userIdId is null");

		List<Role> roleList = roleDao.findUserRolesByService(serviceId,userId);
		if (roleList == null || roleList.size() == 0)
			return null;
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		
		roleList =  roleDao.findIndirectUserRoles(userId);
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.INDIRECT, newRoleSet);
		}
		int size = newRoleSet.size();
		Role[] roleAry = new Role[size];
		return newRoleSet.toArray(roleAry);

	}
	


	public User[] getUsersInRole(String serviceId, String roleId) {
		if (serviceId == null)
			throw new NullPointerException("serviceId is null");
		if (roleId == null)
			throw new NullPointerException("roleId is null");
		
		/* Get the users that are directly associated */
		Role rl = getRole(serviceId, roleId);
		
		//Role rl = getRole(serviceId, roleId);
		
		System.out.println("in getUsersInRole: rl=" + rl);
		System.out.println("in getUsersInRole: users =" + rl.getUsers());
		
		Set<User> userSet = rl.getUsers();

		if (userSet == null || userSet.isEmpty())
			return null;

		Set<User> newUserSet = updateUserRoleAssociation(userSet, UserConstant.DIRECT);

		/* Get the users that are linked through a group */
	 	Set<Group> groupSet = rl.getGroups();
	 	// ensure that we have a unique set of users.
	 	// iterate through the groups
	 	if (groupSet != null && !groupSet.isEmpty()) {
	 		Iterator<Group> it = groupSet.iterator();
	 		while (it.hasNext()) {
	 			Group grp = it.next();
	 			Set<User> grpUsers = grp.getUsers();
	 			userSetToNewUserSet(grpUsers, UserConstant.INDIRECT, newUserSet);
	 		}
	 	}
	 	int size = newUserSet.size();
	 	User[] userAry = new User[size];
	 	return newUserSet.toArray(userAry);
	 	
		
	}
	
	
	
	/** **************** Helper Methods ***************************** */

	/**
	 * Converts a list of Role objects into an Array
	 * 
	 * @param roleList
	 * @return
	 */
	private Role[] roleListToArray(List<Role> roleList) {

		if (roleList == null || roleList.size() == 0)
			return null;

		int size = roleList.size();
		Role[] roleAry = new Role[size];
		for (int ctr = 0; ctr < size; ctr++) {
			Role rl = roleList.get(ctr);
			roleAry[ctr] = rl;
		}
		return roleAry;

	}

	private RoleAttribute[] roleAttrSetToArray(Set<RoleAttribute> attrSet) {

		int size = attrSet.size();
		RoleAttribute[] roleAttrAry = new RoleAttribute[size];
		Iterator<RoleAttribute> it = attrSet.iterator();
		int ctr = 0;
		while (it.hasNext()) {
			RoleAttribute ra = it.next();
			roleAttrAry[ctr] = ra;
		}
		return roleAttrAry;

	}

	private Group[] groupSetToArray(Set<Group> groupSet) {

		int size = groupSet.size();
		Group[] groupAry = new Group[size];
		Iterator<Group> it = groupSet.iterator();
		int ctr = 0;
		while (it.hasNext()) {
			Group ra = it.next();
			groupAry[ctr] = ra;
		}
		return groupAry;

	}

	private User[] userSetToArray(Set<User> userSet) {

		int size = userSet.size();
		User[] userAry = new User[size];
		Iterator<User> it = userSet.iterator();
		int ctr = 0;
		while (it.hasNext()) {
			User u = it.next();
			userAry[ctr] = u;
		}
		return userAry;

	}
	private User[] userCollectionToArray(Collection<User> userCol) {

		int size = userCol.size();
		User[] userAry = new User[size];
		return  userCol.toArray(userAry);
		


	}
	
	private Set<User> updateUserRoleAssociation(Set<User> userSet, int roleAssociationMethod) {

		Set<User> newUserSet = new HashSet();

		Iterator<User> it = userSet.iterator();
		while (it.hasNext()) {
			User u = it.next();
			// @TODO u.setRoleAssociationMethod(roleAssociationMethod);
			newUserSet.add(u);
		}
		return newUserSet;


	}
	
	private void updateRoleAssociation(List<Role> roleList, int associationMethod, Set<Role> newRoleSet) {
		int size = roleList.size();
		for (int i=0; i<size; i++) {
			Role rl = roleList.get(i);
			rl.setUserAssociationMethod(RoleConstant.DIRECT);
			newRoleSet.add(rl);
		}
		//return newRoleSet;		
	}

	private void userSetToNewUserSet(Set<User> userSet, int roleAssociationMethod, Set<User> newUserSet) {

		Iterator<User> it = userSet.iterator();
		while (it.hasNext()) {
			User u = it.next();
			// @TODO u.setRoleAssociationMethod(roleAssociationMethod);
			newUserSet.add( u);

		}
	}	
	
	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}

	public RoleAttributeDAO getRoleAttributeDAO() {
		return roleAttributeDAO;
	}

	public void setRoleAttributeDAO(RoleAttributeDAO roleAttributeDAO) {
		this.roleAttributeDAO = roleAttributeDAO;
	}
}
