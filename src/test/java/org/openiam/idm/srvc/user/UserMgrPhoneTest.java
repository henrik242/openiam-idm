package org.openiam.idm.srvc.user;


import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.continfo.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UserMgrPhoneTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user2;
	Phone phone1,phone2,phone3;
	
	static String phId1 = null, phId2=null, phId3=null;
	

	
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
		
		phone1= (Phone)ctx.getBean("phone1");
		 phone2 = (Phone)ctx.getBean("phone2");
		 phone3 = (Phone)ctx.getBean("phone3");
		
	} 

	@Test
	public void testAddUser() {
		userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
	}
	
	/* Test direct address methods */
	
	@Test
	public void testAddAddress() {
		
		userMgr.addPhone(phone1); 	
		
		phId1 = phone1.getPhoneId();
		
		Phone tempAdr = userMgr.getPhoneById(phone1.getPhoneId());
		assertEquals(phone1.getPhoneNbr(), tempAdr.getPhoneNbr());
		assertEquals(phone1.getPhoneExt(), tempAdr.getPhoneExt());
	}
	

	@Test
	public void testAdd2ndPhone() {
		userMgr.addPhone(phone2);
		userMgr.addPhone(phone3);

		phId2 = phone2.getPhoneId();
		phId3 = phone3.getPhoneId();

		
		List<Phone> adrList = userMgr.getPhoneList(user.getUserId());
		
		assertEquals(3, adrList.size());

		
	}
	@Test
	public void testGetAllPhoneess() {
		List<Phone> adrList = userMgr.getPhoneList(user.getUserId());
		assertNotNull(adrList);
	}
	
	
	// get the address if we are going through the user object
	@Test
	public void testGetAllPhoneFromUser() {
		User tempUser =  userMgr.getUserWithDependent(user.getUserId(),true);
		
		Set<Phone> phoneSet = tempUser.getPhone();
		assertNotNull(phoneSet);
		assertEquals(3, phoneSet.size());
	}	
	
	
	@Test
	public void testGetDefaultPhone() {
		Phone adrTemp = userMgr.getDefaultPhone(user.getUserId());
		assertNotNull(adrTemp);
		assertEquals(phId3, adrTemp.getPhoneId());
	}



	@Test
	public void testUpdatePhone() {
		
		Phone ph = userMgr.getPhoneById(phId2);
		ph.setPhoneNbr("123-5512");
		ph.setPhoneExt("2345");
		
		userMgr.updatePhone(ph);
		
		Phone adrTemp = userMgr.getPhoneById(phId2);
		assertEquals(ph.getPhoneNbr(), adrTemp.getPhoneNbr());
		assertEquals(ph.getPhoneExt(), adrTemp.getPhoneExt());
	
	}
	
	@Test
	public void testUpdatePhoneThruUser() {
		
	
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<Phone> phoneSet = tempUser.getPhone();

		Iterator<Phone> it = phoneSet.iterator();
		while (it.hasNext()) {
			Phone ph = it.next();
			if (ph.getPhoneId().equals(phId1)) {
				ph.setPhoneNbr("123-5512");
				ph.setPhoneExt("2345");
			}
		}
		
		tempUser.setPhone(phoneSet);
		
		userMgr.updateUserWithDependent(tempUser, true);
		
		Phone adrTemp = userMgr.getPhoneById(phId1);
		assertEquals("123-5512", adrTemp.getPhoneNbr());
		assertEquals("2345", adrTemp.getPhoneExt());
	
	}	
	

	
	public void testRemovePhoneThroughUser() {
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<Phone> phoneSet = tempUser.getPhone();

		Iterator<Phone> it = phoneSet.iterator();
		while (it.hasNext()) {
			Phone ph = it.next();
			if (ph.getPhoneId().equals(phId1)) {
				it.remove();
			}
		}
		
		userMgr.updateUserWithDependent(tempUser, true);
		
		Phone adrTemp = userMgr.getPhoneById(phId1);
		assertNull(adrTemp);
		
	}

	
	public void testAddPhoneThroughUser() {
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<Phone> phoneSet = tempUser.getPhone();
		
		Phone ph = new Phone();
		ph.setParentId(tempUser.getUserId());
		ph.setParentType("USER");
		ph.setName("Test");
		ph.setPhoneNbr("456-5512");
		ph.setPhoneExt("x45");		
		
		phoneSet.add(ph);
		tempUser.setPhone(phoneSet);	
		userMgr.updateUserWithDependent(tempUser, true);
		
		Phone phoneTemp = userMgr.getPhoneByName(tempUser.getUserId(),"Test");
		assertNotNull(phoneTemp);
		assertEquals(ph.getPhoneNbr(), phoneTemp.getPhoneNbr());
		assertEquals(ph.getPhoneExt(), phoneTemp.getPhoneExt());
		
	}

	@Test
	public void testRemovePhone() {
		phone2.setPhoneId(phId2);
		userMgr.removePhone(phone2);
		
		assertNull(userMgr.getPhoneById(phId2));
		
		List<Phone> adrList = userMgr.getPhoneList(user.getUserId());
		assertEquals(2, adrList.size());		

	}

	
	
	@Test
	public void testRemoveAllPhonees() {
		userMgr.removeAllPhones(user.getUserId());
		
		assertNull(userMgr.getPhoneById(phId2));
		
		List<Phone> adrList = userMgr.getPhoneList(user.getUserId());
		assertNull(adrList);		

	}
	
	public void testRemoveUser() {
		
		userMgr.removeUser(user.getUserId());
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNull(u);	
	}
	
	/* Test address methods through the user dto */
	


		
}








