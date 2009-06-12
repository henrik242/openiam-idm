package org.openiam.idm.srvc.user;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserMgrAttributeTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user10;
	UserAttribute userAttribute, userAttribute2,userAttribute3;

	static String userId;
	static String attrId, attrId2, attrId3;

	
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
		userAttribute = (UserAttribute)ctx.getBean("userAttribute");
		userAttribute2 = (UserAttribute)ctx.getBean("userAttribute2");
		userAttribute3 = (UserAttribute)ctx.getBean("userAttribute3");

		
	} 

	
	
	@Test
	public void testAddUser() {
		user = userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		userId = user.getUserId();
		
	}
	


	/*--------- Test Attributes    -------- */ 
	
	public void testAddAttribute() {
		// create a user 
		
		this.testAddUser();
		
		User usr = userMgr.getUserWithDependent(userId,false);
		Map<String, UserAttribute> attrMap =  usr.getUserAttributes();
		
		attrMap.put(userAttribute.getName(),userAttribute);
		
		userMgr.updateUser(usr);

		Map<String, UserAttribute> atMap = userMgr.getAllAttributes(user.getUserId());
		assertNotNull(atMap);
		assertEquals(atMap.size(), 1);
	
	}

	public void testAddAttributeWithoutUser() {
		User usr = userMgr.getUserWithDependent(userId,false);
		userAttribute2.setUserId(userId);
		userAttribute3.setUserId(userId);
		
		userAttribute2 = userMgr.addAttribute(userAttribute2);
		userAttribute3 = userMgr.addAttribute(userAttribute3);
		
		attrId2 = userAttribute2.getId();
		attrId3 = userAttribute3.getId();
		attrId = String.valueOf( Integer.parseInt(attrId2)-1 );
		
		Map<String, UserAttribute> attrMap = userMgr.getAllAttributes(userId);
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);
		
	}	
	
	public void testGetAllAttributes() {
		User usr = userMgr.getUserWithDependent(userId, true);
		Map<String, UserAttribute> attrMap = usr.getUserAttributes();
		System.out.println("map size=" + attrMap.size());
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);
	}
	
	public void testSaveAttribute() {
		User usr = userMgr.getUserWithDependent(userId, true);
		Map<String,UserAttribute> attrMap = usr.getUserAttributes();
		UserAttribute tempAt = attrMap.get(this.userAttribute.getName());
		tempAt.setValue("updated value");
		attrMap.put(tempAt.getName(), tempAt);
		
		userMgr.updateUser(usr);
		
		UserAttribute checkAttr = userMgr.getAttribute(tempAt.getId());
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value" );
		
	}
	
	public void testSaveAttributeWithoutUser() {

		userAttribute2 = userMgr.getAttribute(attrId2);
		userAttribute2.setValue("updated value test");
		this.userMgr.updateAttribute(userAttribute2);

		UserAttribute checkAttr = userMgr.getAttribute(attrId2);
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value test");
	
	}	
	
	public void testRemoveAttribute() {
		User usr = userMgr.getUserWithDependent(userId, true);
		Map<String,UserAttribute> attrMap = usr.getUserAttributes();
		attrMap.remove(userAttribute.getName());
		
		userMgr.updateUser(usr);

		UserAttribute checkAttr = userMgr.getAttribute(attrId);
		assertNull(checkAttr);		
	}
	
	public void testRemoveAttributeDirect() {
		userAttribute2 = userMgr.getAttribute(attrId2);
		
		userMgr.removeAttribute(userAttribute2);	

		UserAttribute checkAttr = userMgr.getAttribute(attrId2);
		assertNull(checkAttr);
	
	}
	
	public void testGetUserAsAttributes() {
		Map<String, UserAttribute> attrMap = userMgr.getUserAsMap(userId);
	
		assertNotNull(attrMap);
	
	}
	
	public void testRemoveAllAttributes() {
		userMgr.removeAllAttributes(userId);

		Map<String, UserAttribute> attrMap = userMgr.getAllAttributes(userId);
		assertNull(attrMap);
	}

	public void testRemoveUserWithChildren() {
		this.testAddAttribute();
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUserWithDependent(userId, false);
		assertNull(u);	
		
	}
	


	

		
}








