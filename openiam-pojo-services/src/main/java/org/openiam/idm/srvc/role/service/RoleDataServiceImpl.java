package org.openiam.idm.srvc.role.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.UserGroupDAO;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.service.ResourceRoleDAO;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleAttribute;
import org.openiam.idm.srvc.role.dto.RoleConstant;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.dto.RolePolicy;
import org.openiam.idm.srvc.role.dto.RoleSearch;
import org.openiam.idm.srvc.role.dto.UserRole;
import org.openiam.idm.srvc.user.dto.User;

import org.openiam.idm.srvc.user.dto.UserConstant;
import org.openiam.idm.srvc.user.service.UserDataService;

import java.util.*;

import javax.jws.WebService;

//Note: as per spec serviceName goes in impl class and name goes in interface
		

public class RoleDataServiceImpl implements RoleDataService {

	private RoleDAO roleDao;
	private RoleAttributeDAO roleAttributeDAO;
	private UserDataService userManager;
	private UserRoleDAO userRoleDao;
	private UserGroupDAO userGroupDao;
	private RolePolicyDAO rolePolicyDao;
    private ResourceRoleDAO resRoleDao;
	

	private static final Log log = LogFactory.getLog(RoleDataServiceImpl.class);

	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public Role addRole(Role role) {
		if (role == null)
			throw new IllegalArgumentException("role object is null");

		roleDao.add(role);
		return role;
	}

	public Role getRole(String serviceId, String roleId) {
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");

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
			throw new IllegalArgumentException("role object is null");

		roleDao.update(role);

	}

	/**
	 * Returns a list of all Roles regardless of service The list is sorted by
	 * ServiceId, Role
	 * 
	 * @return
	 */
	public List<Role> getAllRoles() {

		return roleDao.findAllRoles();

	}

	public int removeRole(String domainId, String roleId) {
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (domainId == null)
			throw new IllegalArgumentException("serviceId object is null");

		Role rl = new Role(new RoleId(domainId, roleId));

        try {
            this.roleAttributeDAO.deleteRoleAttributes(domainId, roleId);
            this.userRoleDao.removeAllUsersInRole(domainId, roleId);
            this.resRoleDao.removeResourceRole(domainId,roleId);
            this.roleDao.remove(rl);
        }catch (Exception e) {
            log.error(e.toString());
            return 0;
        }
        return 1;
	}

	public List<Role> getRolesInDomain(String domainId) {
		long start = System.currentTimeMillis();
		
		List<Role> rlList = roleDao.findRolesInService(domainId);
		
		long end = System.currentTimeMillis();
		log.debug("findRolesInService: " + (end-start));
		
		if (rlList == null || rlList.size() == 0)
			return null;
		return rlList;
		

	}

	/* ---------------------- RoleAttribute Methods --------------- */

	public RoleAttribute addAttribute(RoleAttribute attribute) {
		if (attribute == null)
			throw new IllegalArgumentException("Attribute can not be null");

		if (attribute.getRoleId() == null) {
			throw new IllegalArgumentException(
					"Role has not been associated with this attribute.");
		}

		roleAttributeDAO.add(attribute);
		return attribute;
	}

	public RoleAttribute[] getAllAttributes(String serviceId, String roleId) {

		if (roleId == null) {
			throw new IllegalArgumentException("groupId is null");
		}

		Role role = roleDao.findById(new RoleId(serviceId, roleId));
		Set attrSet = role.getRoleAttributes();
		if (attrSet != null && attrSet.isEmpty())
			return null;
		return this.roleAttrSetToArray(attrSet);
	}

	public RoleAttribute getAttribute(String attrId) {
		if (attrId == null) {
			throw new IllegalArgumentException("attrId is null");
		}
		return roleAttributeDAO.findById(attrId);
	}

	public void removeAllAttributes(String serviceId, String roleId) {
		if (roleId == null) {
			throw new IllegalArgumentException("roleId is null");
		}
		this.roleAttributeDAO.deleteRoleAttributes(serviceId, roleId);

	}

	public void removeAttribute(RoleAttribute attr) {
		if (attr == null) {
			throw new IllegalArgumentException("attr is null");
		}
		if (attr.getRoleAttrId() == null) {
			throw new IllegalArgumentException("attrId is null");
		}

		roleAttributeDAO.remove(attr);

	}

	public void updateAttribute(RoleAttribute attribute) {
		if (attribute == null)
			throw new IllegalArgumentException("Attribute can not be null");
		if (attribute.getRoleAttrId() == null) {
			throw new IllegalArgumentException("Attribute id is null");
		}
		if (attribute.getRoleId() == null) {
			throw new IllegalArgumentException(
					"Role has not been associated with this attribute.");
		}

		roleAttributeDAO.update(attribute);
	}




	/* ------------- Group to Role Methods --------------------------------- */

	public void addGroupToRole(String serviceId, String roleId, String groupId) {
		// TODO Auto-generated method stub
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (serviceId == null)
			throw new IllegalArgumentException("serviceId is null");
		if (groupId == null)
			throw new IllegalArgumentException("groupId is null");

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
			throw new IllegalArgumentException("roleId is null");
		if (serviceId == null)
			throw new IllegalArgumentException("serviceId object is null");
		if (groupId == null)
			throw new IllegalArgumentException("groupId object is null");

		this.roleDao.removeGroupFromRole(serviceId, roleId, groupId);

	}

	public void removeAllGroupsFromRole(String serviceId, String roleId) {
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (serviceId == null)
			throw new IllegalArgumentException("serviceId object is null");

		roleDao.removeAllGroupsFromRole(serviceId, roleId);
	}

	public List<Role> getRolesInGroup(String groupId) {
		// TODO Auto-generated method stub

		if (groupId == null)
			throw new IllegalArgumentException("groupid is null");

		List<Role> roleList = roleDao.findRolesInGroup(groupId);
		if (roleList == null || roleList.isEmpty())
			return null;
		return roleList;


	}


	
	/* ------------- User to Role Methods --------------------------------- */
	
	/**
	 * Adds a user to a role using the UserRole object. Similar to addUserToRole, but allows you to update attributes likes start and end date.
	 */
	public void assocUserToRole(UserRole ur) {
		if (ur.getRoleId() == null)
			throw new IllegalArgumentException("roleId is null");
		if (ur.getServiceId() == null)
			throw new IllegalArgumentException("domainId object is null");
		if (ur.getUserId() == null)
			throw new IllegalArgumentException("userId object is null");	
		
		ur.setUserRoleId(null);
		userRoleDao.add(ur);
	}
	
	/**
	 * Updates the attributes in the user role object.
	 * @param ur
	 */
	public void updateUserRoleAssoc(UserRole ur) {
		if (ur.getRoleId() == null)
			throw new IllegalArgumentException("roleId is null");
		if (ur.getServiceId() == null)
			throw new IllegalArgumentException("domainId object is null");
		if (ur.getUserId() == null)
			throw new IllegalArgumentException("userId object is null");		
		userRoleDao.update(ur);
	}
	
	public UserRole getUserRoleById(String userRoleId ) {
		if (userRoleId == null) {
			throw new IllegalArgumentException("userRoleId is null");
		}
		return userRoleDao.findById(userRoleId);
		
	}
	
	public List<UserRole> getUserRolesForUser(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId is null");
		}
		return userRoleDao.findUserRoleByUser(userId);		
	}
	
	
	public void addUserToRole(String domainId, String roleId, String userId) {

		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (domainId == null)
			throw new IllegalArgumentException("domainId object is null");
		if (userId == null)
			throw new IllegalArgumentException("userId object is null");
		
		UserRole ur = new UserRole(userId, domainId, roleId);
		
		this.userRoleDao.add(ur);

	}
	
	public boolean isUserInRole(String serviceId, String roleId, String userId) {
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (serviceId == null)
			throw new IllegalArgumentException("serviceId object is null");
		if (userId == null)
			throw new IllegalArgumentException("userIdId object is null");
		
		// check if the user is directly linked to a role
/*		Role rl = roleDao.findDirectRoleForUser(serviceId, roleId, userId);
		log.info("findDirectRoleForUser = " + rl);
		if (rl != null) {
			return true;
		}
		// check if the user is linked to a role through a group.
		List<Role> roleList =  roleDao.findIndirectUserRoles(userId);
		log.info("findInDirectUserRoles = " + roleList);
		if (roleList != null)
			return true;		
		return false;
*/
		List<Role> userRoleList = this.getUserRolesAsFlatList(userId);
		if (userRoleList == null) {
			return false;
		}
		for (Role rl : userRoleList) {
			if (rl.getId().getRoleId().equalsIgnoreCase(roleId) &&
				rl.getId().getServiceId().equalsIgnoreCase(serviceId)) {
				return true;
			}
		}
		return false;
	}

	public void removeUserFromRole(String domainId, String roleId,	String userId) {
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (domainId == null)
			throw new IllegalArgumentException("domainId object is null");
		if (userId == null)
			throw new IllegalArgumentException("userId object is null");

		this.userRoleDao.removeUserFromRole(domainId, roleId, userId);
		
		

	}

	/**
	 * Returns the roles that are directly associated with a user; ie. Does not take into
	 * account roles that may be associated with a user becuase of a group relationship.
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRolesDirect(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userIdId is null");

		List<Role> roleList = roleDao.findUserRoles(userId);
		if (roleList == null || roleList.size() == 0)
			return null;
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		if (roleList == null || roleList.size() == 0) {
			return null;
		}
		return roleList;
		
	}
	
	
	/**
	 * Returns an array of roles that a user belongs to.
	 */
/*	public List<Role> getUserRoles(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userIdId is null");

		List<Role> roleList = roleDao.findUserRoles(userId);
		
		log.debug("getUserRoles for userId=" + userId);
		log.debug(" - findUserRoles = " + roleList);
		
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		
		roleList =  roleDao.findIndirectUserRoles(userId);
		
		log.debug(" - findIndirectUserRoles = " + roleList);
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.INDIRECT, newRoleSet);
		}
		if (newRoleSet == null || newRoleSet.size() == 0) {
			return null;
		}
		List<Role> newRoles = new ArrayList<Role>(newRoleSet);
		return newRoles;
		

	}
*/
	
	private Role getParentRole(Role rl) {
		RoleId id = new RoleId(rl.getId().getServiceId(), rl.getParentRoleId());
		Role pRole =  this.roleDao.findById(id);
		log.info("Got parent role = " + pRole);
		if (pRole != null) {
			// add the child role to the parentRole
			pRole.getChildRoles().add(rl);
		}
		if (pRole.getParentRoleId() != null) {
			log.info("Found another parent role - make a recursive call. parentId =" + pRole.getParentRoleId());
			return getParentRole(pRole);
		}
		return pRole;
	}

	/**
	 * Returns an array of Role objects that indicate the Roles a user is
	 * associated to.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRoles(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userIdId is null");

		List<Role> roleList = roleDao.findUserRoles(userId);
		
		log.debug("getUserRoles for userId=" + userId);
		log.debug(" - findUserRoles = " + roleList);
		
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		
		roleList =  roleDao.findIndirectUserRoles(userId);
		
		log.debug(" - findIndirectUserRoles = " + roleList);
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.INDIRECT, newRoleSet);
		}
		if (newRoleSet == null || newRoleSet.size() == 0) {
			return null;
		}
		List<Role> newRoles = new ArrayList<Role>(newRoleSet);
		// for each of these roles, figure out if there are roles above it in the hierarchy
		
		List<Role> newRoleList = new ArrayList<Role>();
		for (Role rl : newRoles ) {
			log.info("Role id=" + rl.getId() + " parentId=" + rl.getParentRoleId() );
			if (rl.getParentRoleId() == null) {
				newRoleList.add(rl);
			}else {
				log.info("Get the parent role for parentId=" + rl.getParentRoleId());
				newRoleList.add(getParentRole(rl));
			}
		}
		
		
		return newRoleList;

		
		//return newRoles;

	}
	private List<Role> getParentRoleFlat(Role rl) {
		List<Role> roleList = new ArrayList<Role>();
		RoleId id = new RoleId(rl.getId().getServiceId(), rl.getParentRoleId());
		Role pRole = roleDao.findById(id);
		log.info("Got parent role = " + pRole);
		if (pRole != null) {
			// add the child role to the list of  role
			roleList.add(pRole);
		}
		if (pRole.getParentRoleId() != null) {
			log.info("Found another parent role - make a recursive call. parentId =" + pRole.getParentRoleId());
			roleList.addAll( getParentRoleFlat(pRole) );
			return roleList;
		}
		return roleList;
	}
	
	/**
	 * Returns a list of roles that a user belongs to. Roles can be hierarchical and this operation traverses the tree to roles that are in the 
	 * hierarchy.
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRolesAsFlatList(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userIdId is null");

		List<Role> roleList = roleDao.findUserRoles(userId);
		
		log.debug("getUserRoles for userId=" + userId);
		log.debug(" - findUserRoles = " + roleList);
		
		
		Set<Role> newRoleSet = new HashSet();
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.DIRECT, newRoleSet);
		}
		
		roleList =  roleDao.findIndirectUserRoles(userId);
		
		log.debug(" - findIndirectUserRoles = " + roleList);
		
		if (roleList != null && !roleList.isEmpty()) {
			updateRoleAssociation(roleList, RoleConstant.INDIRECT, newRoleSet);
		}
		if (newRoleSet == null || newRoleSet.size() == 0) {
			return null;
		}
		List<Role> newRoles = new ArrayList<Role>(newRoleSet);
		// for each of these roles, figure out if there are roles above it in the hierarchy
		
		List<Role> newRoleList = new ArrayList<Role>();
		for (Role rl : newRoles ) {
			log.info("Role id=" + rl.getId() + " parentId=" + rl.getParentRoleId() );
			if (rl.getParentRoleId() == null) {
				newRoleList.add(rl);
			}else {
				log.info("Get the parent role for parentId=" + rl.getParentRoleId());
				newRoleList.add(rl);
				newRoleList.addAll(getParentRoleFlat(rl));
			}
		}
		
		
		return newRoleList;		
	}
	
	
	public List<Role> getUserRolesByDomain(String domainId,  String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userIdId is null");

		List<Role> roleList = roleDao.findUserRolesByService(domainId,userId);
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
		if (roleList == null || roleList.size() == 0) {
			return null;
		}
		return roleList;

	}
	


	public User[] getUsersInRole(String domainId, String roleId) {
		if (domainId == null)
			throw new IllegalArgumentException("domainId is null");
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		
		/* Get the users that are directly associated */
		Role rl = getRole(domainId, roleId);
		
		
		//System.out.println("in getUsersInRole: rl=" + rl);
		//System.out.println("in getUsersInRole: users =" + rl.getUsers());
		
		List<User> userList = userRoleDao.findUserByRole(domainId, roleId);

		if (userList == null || userList.isEmpty())
			return null;

		Set<User> newUserSet = updateUserRoleAssociation(userList, UserConstant.DIRECT);

		/* Get the users that are linked through a group */
	 	Set<Group> groupSet = rl.getGroups();
	 	// ensure that we have a unique set of users.
	 	// iterate through the groups
	 	if (groupSet != null && !groupSet.isEmpty()) {
	 		Iterator<Group> it = groupSet.iterator();
	 		while (it.hasNext()) {
	 			Group grp = it.next();
	 			List<User> userLst = userGroupDao.findUserByGroup(grp.getGrpId());
	 			//Set<User> grpUsers = grp.getUsers();
	 			userSetToNewUserSet(userLst, UserConstant.INDIRECT, newUserSet);
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
			roleAttrAry[ctr++] = ra;
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
			groupAry[ctr++] = ra;
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
			userAry[ctr++] = u;
		}
		return userAry;

	}
	private User[] userCollectionToArray(Collection<User> userCol) {

		int size = userCol.size();
		User[] userAry = new User[size];
		return  userCol.toArray(userAry);
		


	}
	
	private Set<User> updateUserRoleAssociation(List<User> userList, int roleAssociationMethod) {

		Set<User> newUserSet = new HashSet();
		
		for ( User u :userList) {
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

	private void userSetToNewUserSet(List<User> userList, int roleAssociationMethod, Set<User> newUserSet) {

		for ( User u : userList) {
			newUserSet.add(u);
		}

	}	
	
	public List<Role> search(RoleSearch search) {
		if (search == null) {
			throw new IllegalArgumentException("Search parameter is null");
		}
		List<Role> roleList = roleDao.search(search);
		if (roleList == null || roleList.isEmpty()) {
			return null;
		}
		if (roleList == null || roleList.size() == 0) {
			return null;
		}
		return roleList;
		
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

	public UserRoleDAO getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDAO userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public UserGroupDAO getUserGroupDao() {
		return userGroupDao;
	}

	public void setUserGroupDao(UserGroupDAO userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

	/* Role Policies */
	public RolePolicy addRolePolicy(RolePolicy rPolicy) {
		if (rPolicy == null) {
			throw new NullPointerException("rPolicy is null");
		}
		rolePolicyDao.add(rPolicy);
		return rPolicy;
	}

	public RolePolicy updateRolePolicy(RolePolicy rPolicy) {
		if (rPolicy == null) {
			throw new NullPointerException("rPolicy is null");
		}
		return rolePolicyDao.update(rPolicy);

	}
	
	public List<RolePolicy> getAllRolePolicies(String domainId, String roleId) {
		if (domainId == null) {
			throw new NullPointerException("domainId is null");
		}
		if (roleId == null) {
			throw new NullPointerException("roleId is null");
		}
		return rolePolicyDao.findRolePolicies(domainId, roleId);
	}

	public RolePolicy getRolePolicy(String rolePolicyId) {
		if (rolePolicyId == null) {
			throw new NullPointerException("rolePolicyId is null");
		}
		return rolePolicyDao.findById(rolePolicyId);
	}

	public void removeRolePolicy(RolePolicy rPolicy) {
		if (rPolicy == null) {
			throw new NullPointerException("rPolicy is null");
		}
		rolePolicyDao.remove(rPolicy);
		
	}

	public RolePolicyDAO getRolePolicyDao() {
		return rolePolicyDao;
	}

	public void setRolePolicyDao(RolePolicyDAO rolePolicyDao) {
		this.rolePolicyDao = rolePolicyDao;
	}

    public ResourceRoleDAO getResRoleDao() {
        return resRoleDao;
    }

    public void setResRoleDao(ResourceRoleDAO resRoleDao) {
        this.resRoleDao = resRoleDao;
    }
}
