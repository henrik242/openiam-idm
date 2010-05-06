package org.openiam.idm.srvc.role;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.role.service.*;
import org.openiam.idm.srvc.role.dto.*;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserRoleDataServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	RoleDataService roleMgr;
	Role role, role3;

	UserDataService userMgr;
	User user,user2, user3;
	static String userId, userId2, userId3;
	static String groupId;

	GroupDataService grpMgr;
	Group grp;


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml",
				"/roleDataServiceTest-applicationContext.xml",
				"/userTest-applicationContext.xml" });
		roleMgr = (RoleDataService) ctx.getBean("roleDataService");
		role = (Role) ctx.getBean("roleBean");
		role3 = (Role) ctx.getBean("roleBean3");

		userMgr = (UserDataService) ctx.getBean("userManager");
		user = (User) ctx.getBean("userBean");
		user2 = (User) ctx.getBean("userBean2");
		user3 = (User) ctx.getBean("userBeanAdr");
		
		grpMgr = (GroupDataService) ctx.getBean("groupManager");
		grp = (Group) ctx.getBean("grpBean");

	}

	@Test
	public void testAddUser() {
		user = userMgr.addUser(user);

		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);

		userId = user.getUserId();

	}

	public void testAddUser2() {
		user2 = userMgr.addUser(user2);

		User u = userMgr.getUserWithDependent(user2.getUserId(),false);
		assertNotNull(u);

		userId2 = user2.getUserId();

		// add the 3rd user which is used in the user role testing.
		user3 = userMgr.addUser(user3);
		userId3 = user3.getUserId();

		
	}

	@Test
	public void testAddRole() {
		roleMgr.addRole(role);
	}

	public void testAddRole3() {
		roleMgr.addRole(role3);
	}

	public void testAddUserToRole() {
		User u = userMgr.getUserWithDependent(userId,false);
		
		roleMgr.addUserToRole(role.getId().getServiceId(), role.getId().getRoleId(), u.getUserId());



	}

	public void testAddUser2ToRole() {
		User u = userMgr.getUserWithDependent(userId2,false);

		roleMgr.addUserToRole(role.getId().getServiceId(), role.getId().getRoleId(), u.getUserId());
		

	}
	
	/**
	 * Alternative way to add a group to a role
	 */
	public void testAddUserToRole3() {

		User u = userMgr.getUserWithDependent(userId,false);
		roleMgr.addUserToRole(role3.getId().getServiceId(), role3.getId()
				.getRoleId(), u.getUserId());
		
	}
	
	
	/**
	 * Add a user to group. Add the group to role.
	 * This is used to prove that when we call getUsersInRole, that we 
	 * get users that are both directly related and indirectly related 
	 * through a group.
	 */
	public void testAddUserToGroup() {

		
		User u = userMgr.getUserWithDependent(userId3,false);
		
		groupId = String.valueOf( System.currentTimeMillis() ); 
		
		Group grp = new Group();
		grp.setGrpId(groupId);
		grp.setGrpName("Test Group");
		grpMgr.addGroup(grp);
		grpMgr.addUserToGroup(grp.getGrpId(), userId);
		
		// add the group to a role
		
		roleMgr.addGroupToRole(role.getId().getServiceId(), role.getId().getRoleId(), grp.getGrpId());


	}
	public void testGetUsersInRole() {
		User[] userAry = roleMgr.getUsersInRole(role.getId().getServiceId(), role.getId().getRoleId());
		int size = userAry.length;
		assertEquals(3, size);

	}



/*	public void testGetAllRole() {
		Role[] roleAry = roleMgr.getAllRoles();
		assertNotNull(roleAry);
		for (Role rl : roleAry) {
			System.out.println("Role Id = " + rl.getId().getRoleId()  + " role users:" + rl.getUsers());
			System.out.println("**role groups:" + rl.getGroups());
		}
	}
*/	
	
	/*
	 * For getUserInRole and IsUserInRole test the following three cases:
	 * - check if the user is linked to a role directly
	 * - check if the user is linked to a role through a group
	 * - check if the user is linked to both - direct and through a group
	 */
	
	
/*	public void testGetRolesForUserDirect() {
		Role[] roleAry = roleMgr.getUserRoles(userId);
		assertNotNull(roleAry);
		assertEquals(2, roleAry.length);

		
	}

	public void testGetRolesForUserDirect2() {
		Role[] roleAry = roleMgr.getUserRoles("3100");
		assertNotNull(roleAry);
		assertEquals(2, roleAry.length);

		
	}
	
	public void testGetRolesForUserBoth() {
		// add a user to a group before carrying out this test.
		
		grpMgr.addUserToGroup(groupId, userId);
		Role[] roleAry = roleMgr.getUserRoles(userId);
		assertNotNull(roleAry);
		// duplicate roles are taken into 
		assertEquals(2, roleAry.length);
		
		grpMgr.removeUserFromGroup(groupId, userId);
		
	}
	
	

	public void testIsUserInRoleDirect() {
		boolean inRole = roleMgr.isUserInRole("IDM", "NewRole1", userId);
		assertEquals(true, inRole);

	}

	public void testIsUserInRoleThroughGroup() {
		roleMgr.removeUserFromRole("IDM", "NewRole1", userId);
		grpMgr.addUserToGroup(groupId, userId);
		
		boolean inRole = roleMgr.isUserInRole("IDM", "NewRole1", userId);
		assertEquals(true, inRole);
		grpMgr.removeUserFromGroup(groupId, userId);
		
	}


*/
	public void testRemoveUserFromRole() {

		roleMgr.removeUserFromRole(role.getId().getServiceId(), role.getId().getRoleId(), userId);
		//boolean inRole = roleMgr.isUserInRole("IDM", "NewRole1", grp
		//		.getGrpId());
		//assertEquals(false, inRole);
	}

	public void testRemoveRole() {

		Role r = new Role();

		roleMgr.removeRole(role.getId().getServiceId(), role.getId()
				.getRoleId());

		assertNull(roleMgr.getRole(role.getId().getServiceId(), role.getId()
				.getRoleId()));
	}

	public void testRemoveRole2() {

		roleMgr.removeRole("IDM", "NewRole1");
	}

	public void testRemoveRole3() {

		roleMgr.removeRole(role3.getId().getServiceId(), role3.getId()
				.getRoleId());
	}

	public void testRemoveGroup() {
		grpMgr.removeGroup(groupId);
	}
	
	@Test
	public void testRemoveUser() {

		userMgr.removeUser(userId);

		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);
		
		userMgr.removeUser(userId2);
		userMgr.removeUser(userId3);
	}
	
	

}
