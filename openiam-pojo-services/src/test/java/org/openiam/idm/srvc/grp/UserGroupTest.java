package org.openiam.idm.srvc.grp;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.service.*;
import org.openiam.idm.srvc.grp.dto.*;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UserGroupTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	GroupDataService grpMgr;

	Group grp;

	GroupAttribute grpAttr, grpAttr2, grpAttr3;
	UserDataService userMgr;
	User user, user10;
	static String userId;
	static String groupId, groupId2;
	


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/groupTest-applicationContext.xml",
				"/userTest-applicationContext.xml" });
		grpMgr = (GroupDataService) ctx.getBean("groupManager");
		grp = (Group) ctx.getBean("grpBean");

		
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBean");
		

	}



	/*--------- User to Group    -------- */
	
	@Test
	public void testAddUser() {
		user = userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		userId = user.getUserId();
		
	}
	
	/**
	 *  add a user to new group
	 */
	@Test
	public void testAddUserToGroup1() {

		
		User u = userMgr.getUserWithDependent(userId,false);
		
		groupId = String.valueOf( System.currentTimeMillis() ); 
		
		Group grp = new Group();
		grp.setGrpId(groupId);
		grp.setGrpName("Test Group");
		
		grp.getUsers().add(u);
		grpMgr.addGroup(grp);
		
		//grpMgr.addUserToGroup(this.grp.getGrpId(), userId);  
	//	assertNotNull(grpMgr.getUserGroups(userId));
	}	

	/**
	 * Add a user to an existing group
	 */
	public void testAddUserToGroup2() {

		User u2 = new User();
		u2.setUserId(String.valueOf( System.currentTimeMillis() ));
		u2.setFirstName("FirstName");
		u2.setLastName("LastName");
		
		userMgr.addUser(u2);
		
		groupId2 =String.valueOf(System.currentTimeMillis());

		Group grp = new Group();
		grp.setGrpId(groupId2);
		grp.setGrpName("Existing Group");
		grpMgr.addGroup(grp);


		grpMgr.addUserToGroup(grp.getGrpId(), u2.getUserId());

	

	}	
	
	public void testIsUserInGroup() {
		boolean inGroup = grpMgr.isUserInGroup(groupId, userId);
		assertEquals(true, inGroup);
		
	}

	public void testIsUserInGroup2() {
		boolean inGroup = grpMgr.isUserInGroup(groupId2, userId);
		assertEquals(false, inGroup);
		
	}
	
	public void test(){
		
		List<Group> grpList = grpMgr.getGroupsNotLinkedToUser("3006", null, false);
		assertNotNull(grpList);
		
		
	}
	
	
	public void testUserInGroups() {
		List<Group> grpList = grpMgr.getUserInGroups(userId);
		assertNotNull(grpList);
		assertEquals(1,grpList.size());

	}
	
	public void testAddUserToGroup3() {

		User u3 = new User();
		u3.setUserId(String.valueOf( System.currentTimeMillis() ));
		u3.setFirstName("FirstName3");
		u3.setLastName("LastName3");
		
		userMgr.addUser(u3);

		Group grp = grpMgr.getGroupWithDependants(groupId);
		grp.getUsers().add(u3);
		
		grpMgr.updateGroup(grp);

	}

	public void testUsersInGroup() {
		Set<User> userSet = grpMgr.getUsersByGroup(groupId);
		assertNotNull(userSet);
		assertEquals(2,userSet.size());
	}
	
	public void testRemoveUserFromGroup() {
		grpMgr.removeUserFromGroup(groupId, userId);
		boolean inGroup = grpMgr.isUserInGroup(groupId, userId);
		assertEquals(false, inGroup);
		
	}
	
	
	public void testRemoveGroupWithUser() {
		grpMgr.removeGroup(groupId);
	}

	public void testRemoveGroupWithUser2() {
		grpMgr.removeGroup(groupId2);
	}
	
 
	
/*	
	@Test
	public void testRemoveUser() {
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUser(userId);
		assertNull(u);	
	}
	

	@Test
	public void testRemoveGroup() {

		grpMgr.removeGroup(grp.getGrpId());

		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNull(u);
	}
	
*/	
}
