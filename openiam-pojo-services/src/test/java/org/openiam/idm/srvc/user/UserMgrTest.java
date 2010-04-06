package org.openiam.idm.srvc.user;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserMgrTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user10;

	static String userId;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBean");
		user10 = (User)ctx.getBean("userBean10");
	
		
	} 

	
	public void testGetNonExistantUser() {
		User newUser = userMgr.getUserWithDependent("123",false);
		
		assertNull(newUser);
	}
	
	public void testGetNonExistantUser2() {
		User newUser = userMgr.getUserWithDependent("123",true);
		
		assertNull(newUser);
	
	}	
	
	@Test
	public void testAddUser() {
		user = userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		userId = user.getUserId();
		
	}
	
	@Test
	public void testGetUser() {
		User u = userMgr.getUserWithDependent(userId,false);
		assertNotNull(u);
	}


	@Test
	public void testUpdateUser() {
	
		
		user = userMgr.getUserWithDependent(userId,false);
		
		user.setLastName("Shah_test");
		user.setFirstName("Ameet_up");
		
		//type = user.getMetadataType();
		//type.setDescription("updated type");
		user.setMetadataTypeId("InetOrgPerson");
		
		
		userMgr.updateUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		assertEquals(u.getFirstName(),"Ameet_up");
		assertEquals(u.getLastName(), "Shah_test");
	
	}

	@Test
	public void testRemoveUser() {
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);	
	}
	
	public void testFindUserByLastUpdate() {
		java.util.Date endDate = new java.util.Date(System.currentTimeMillis());
		java.util.Date startDate = new java.util.Date("01/10/2008");
		
		List userList = userMgr.findUsersByLastUpdateRange(startDate, new Date());
		//List userList = userMgr.
		System.out.println("****userlist =" + userList.size());
	
	}


	
		
}








