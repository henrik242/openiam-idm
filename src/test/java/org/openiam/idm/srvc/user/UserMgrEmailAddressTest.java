package org.openiam.idm.srvc.user;


import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.continfo.service.EmailAddressDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UserMgrEmailAddressTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user2;
	EmailAddress eml1,eml2,eml3;
	
	static String emId1 = null, emId2=null, emId3=null;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBeanAddress");
		user2 = (User)ctx.getBean("userBeanAddress2");
		
		 eml1= (EmailAddress)ctx.getBean("email1");
		 eml2 = (EmailAddress)ctx.getBean("email2");
		 eml3 = (EmailAddress)ctx.getBean("email3");
		
	} 

	@Test
	public void testAddUser() {
		userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
	}
	
	/* Test direct address methods */
	
	@Test
	public void testAddEmailAddress() {
		
		userMgr.addEmailAddress(eml1); 		
		
		emId1 = eml1.getEmailId();
		
		EmailAddress tempAdr = userMgr.getEmailAddressById(emId1);
		assertEquals(eml1.getEmailAddress(), tempAdr.getEmailAddress());
				
	}
	

	@Test
	public void testAdd2ndEmailAddress() {
		userMgr.addEmailAddress(eml2);
		userMgr.addEmailAddress(eml3);
		
		emId2 = eml2.getEmailId();
		emId3 = eml3.getEmailId();
		
		List<EmailAddress> adrList = userMgr.getEmailAddressList(user.getUserId());
		
		assertEquals(3, adrList.size());

		
	}
	@Test
	public void testGetAllEmailAddressess() {
		List<EmailAddress> adrList = userMgr.getEmailAddressList(user.getUserId());
		assertNotNull(adrList);
	}
	
	
	// get the address if we are going through the user object
	@Test
	public void testGetAllEmailAddressFromUser() {
		User tempUser =  userMgr.getUserWithDependent(user.getUserId(),true);
		
		Set<EmailAddress> adrSet = tempUser.getEmailAddress();
		assertNotNull(adrSet);
		assertEquals(3, adrSet.size());
	}	
	
	
	@Test
	public void testGetDefaultEmailAddress() {
		EmailAddress adrTemp = userMgr.getDefaultEmailAddress(user.getUserId());
		assertNotNull(adrTemp);
		assertEquals(emId3, adrTemp.getEmailId());
	}



	@Test
	public void testUpdateEmailAddress() {
		
		eml2.setEmailId(emId2);
		eml2.setEmailAddress("sam@somestore.com");

		
		userMgr.updateEmailAddress(eml2);
		
		EmailAddress adrTemp = userMgr.getEmailAddressById(emId2);
		assertEquals(eml2.getEmailAddress(), adrTemp.getEmailAddress());
	
	}
	
	@Test
	public void testUpdateEmailAddressThruUser() {
		
		
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<EmailAddress> adrMap = tempUser.getEmailAddress();
		// get email 1
		
		Iterator<EmailAddress> it = adrMap.iterator();
		while (it.hasNext()) {
			EmailAddress emAdr = it.next();
			if (emAdr.getEmailId().equals(emId1)) {
				emAdr.setEmailAddress("bob@somestore.com");
			}
		}
		
		
		//adrMap.add(eml1);
		tempUser.setEmailAddress(adrMap);
		
		userMgr.updateUserWithDependent(tempUser, true);
		
		EmailAddress adrTemp = userMgr.getEmailAddressById(emId1);
		assertEquals("bob@somestore.com", adrTemp.getEmailAddress());

	
	}	
	
	public void testRemoveEmailAddressThroughUser() {
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<EmailAddress> adrMap = tempUser.getEmailAddress();

		Iterator<EmailAddress> it = adrMap.iterator();
		while (it.hasNext()) {
			EmailAddress emAdr = it.next();
			if (emAdr.getEmailId().equals(emId1)) {
				it.remove();
			}
		}
		
		//tempUser.setEmailAddress(adrMap);	
		userMgr.updateUserWithDependent(tempUser,true);
 		
		EmailAddress adrTemp = userMgr.getEmailAddressById(emId1);
		assertNull(adrTemp);
		
	}
	
	public void testAddEmailAddressThroughUser() {
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<EmailAddress> adrMap = tempUser.getEmailAddress();
		
		EmailAddress eml = new EmailAddress();
		eml.setParentId(tempUser.getUserId());
		eml.setParentType("USER");
		eml.setName("Test");
		eml.setEmailAddress("email@test.com");
		
		adrMap.add(eml);
		tempUser.setEmailAddress(adrMap);	
		userMgr.updateUserWithDependent(tempUser, true);
		
		EmailAddress adrTemp = userMgr.getEmailAddressByName(tempUser.getUserId(),"Test");
		assertNotNull(adrTemp);
		assertEquals("email@test.com", adrTemp.getEmailAddress());

		
	}

	@Test
	public void testRemoveEmailAddress() {
		eml2.setEmailId(emId2);
		userMgr.removeEmailAddress(eml2);
		
		assertNull(userMgr.getEmailAddressById(emId2));
		
		List<EmailAddress> adrList = userMgr.getEmailAddressList(user.getUserId());
		assertEquals(2, adrList.size());		

	}

	
	
	@Test
	public void testRemoveAllEmailAddresses() {
		userMgr.removeAllEmailAddresses(user.getUserId());
		
		assertNull(userMgr.getEmailAddressById(emId2));
		
		List<EmailAddress> adrList = userMgr.getEmailAddressList(user.getUserId());
		assertNull(adrList);		

	}
	
	public void testRemoveUser() {
		
		userMgr.removeUser(user.getUserId());
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNull(u);	
	}
	
	/* Test address methods through the user dto */
	


		
}








