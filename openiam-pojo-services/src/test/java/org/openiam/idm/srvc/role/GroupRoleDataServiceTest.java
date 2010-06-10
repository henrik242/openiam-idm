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

public class GroupRoleDataServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	RoleDataService roleMgr;
	Role role, role3;

	UserDataService userMgr;
	User user;
	static String userId;

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


		// urole = (UserRole)ctx.getBean("userRoleBean");
		userMgr = (UserDataService) ctx.getBean("userManager");
		user = (User) ctx.getBean("userBean");
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

	public void testAddGroup() {
		grpMgr.addGroup(grp);
		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNotNull(u);

	}

	@Test
	public void testAddRole() {
		roleMgr.addRole(role);
	}

	public void testAddRole3() {
		roleMgr.addRole(role3);
	}

	public void testAddGroupToRole() {
		Group g = grpMgr.getGroup(grp.getGrpId());

		Role r = new Role(new RoleId("IDM", "NewRole1"));
		r.setRoleName("New Role 1");
		r.getGroups().add(g);
		roleMgr.addRole(r);

	}

	/**
	 * Alternative way to add a group to a role
	 */
	public void testAddGroupToRole3() {

		Group g = grpMgr.getGroup(grp.getGrpId());
		roleMgr.addGroupToRole(role3.getId().getServiceId(), role3.getId()
				.getRoleId(), grp.getGrpId());

	}

	public void testGetGroupsInRole() {
		Group[] grpAry = roleMgr.getGroupsInRole("IDM", "NewRole1");
		int size = grpAry.length;
		assertEquals(1, size);

	}

	public void testIsGroupInRole() {
		boolean inRole = roleMgr.isGroupInRole("IDM", "NewRole1", grp
				.getGrpId());
		assertEquals(true, inRole);

	}

	public void testGetRolesInGroup() {

		List<Role> roleAry = roleMgr.getRolesInGroup(grp.getGrpId());
		assertNotNull(roleAry);
		assertEquals(2, roleAry.size());

	}

	public void testRemoveGroupFromRole() {

		roleMgr.removeGroupFromRole("IDM", "NewRole1", grp.getGrpId());
		boolean inRole = roleMgr.isGroupInRole("IDM", "NewRole1", grp
				.getGrpId());
		assertEquals(false, inRole);
	}

	public void testRemoveGroupFromRole3() {

		roleMgr.removeGroupFromRole(role3.getId().getServiceId(), role3.getId()
				.getRoleId(), grp.getGrpId());
		boolean inRole = roleMgr.isGroupInRole(role3.getId().getServiceId(),
				role3.getId().getRoleId(), grp.getGrpId());
		assertEquals(false, inRole);
	}

	public void testRemoveAllGroupsFromRole() {
		roleMgr.removeAllGroupsFromRole("IDM", "NewRole1");
	}


	public void testRemoveGroup() {

		grpMgr.removeGroup(grp.getGrpId());
		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNull(u);
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

	@Test
	public void testRemoveUser() {

		userMgr.removeUser(userId);

		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);
	}

}
