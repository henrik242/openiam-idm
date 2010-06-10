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
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserGroupTest extends  AbstractOpenIAMTestCase {

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
	 * Add a user to an existing group
	 */
	public void testAddUserToGroup() {

		User u2 = new User();
		u2.setUserId(String.valueOf( System.currentTimeMillis() ));
		u2.setFirstName("FirstName");
		u2.setLastName("LastName");
		
		userMgr.addUser(u2);
		userId = u2.getUserId();
		groupId =String.valueOf(System.currentTimeMillis());

		Group grp = new Group();
		grp.setGrpId(groupId);
		grp.setGrpName("Existing Group");
		grpMgr.addGroup(grp);


		grpMgr.addUserToGroup(grp.getGrpId(), u2.getUserId());

	

	}	
	
	public void testIsUserInGroup() {
		boolean inGroup = grpMgr.isUserInGroup(groupId, userId);
		assertEquals(true, inGroup);
		
	}


	
	public void testUserInGroups() {
		List<Group> grpList = grpMgr.getUserInGroups(userId);
		assertNotNull(grpList);
		assertEquals(1,grpList.size());

	}


	public void testUsersInGroup() {
		List<User> userSet = grpMgr.getUsersByGroup(groupId);
		assertNotNull(userSet);
		assertEquals(1,userSet.size());
	}

public void testRemoveUserFromGroup() {
		grpMgr.removeUserFromGroup(groupId, userId);
		
	}
	

}
