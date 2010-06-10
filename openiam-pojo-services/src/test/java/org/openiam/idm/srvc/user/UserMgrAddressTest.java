package org.openiam.idm.srvc.user;


import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.continfo.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserMgrAddressTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user2;
	Address adr1,adr2, adr3, adr4;
	
	static String adrId1 = null, adrId2=null, adrId3=null, adrId4=null;
	static String userId = null;
	
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
		
		adr1 = (Address)ctx.getBean("address1");
		adr2 = (Address)ctx.getBean("address2");
		adr3 = (Address)ctx.getBean("address3");
		adr4 = (Address)ctx.getBean("address4");
		
		
		
	} 

	@Test
	public void testAddUser() {
		userMgr.addUser(user);
		
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
	}

	public void testAddUserWithAddress() {

		user.getAddresses().add(adr1);
		User newuser = userMgr.addUser(user);
		userId = newuser.getUserId();

		assertNotNull(newuser);
		assertNotNull(newuser.getUserId());
		
		User u = userMgr.getUserWithDependent(newuser.getUserId(),false);

		
		System.out.println("New User = " + u.getUserId());

		
	}
	

	/* Test address methods through the user dto */

	public void testRemoveUserWithAddress() {
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);	
	}	

	
	/* Test direct address methods */
	
	@Test
	public void testAddAddress() {
		
		userMgr.addAddress(adr1); 		
		
		adrId1 = adr1.getAddressId();
		
		Address tempAdr = userMgr.getAddressById(adr1.getAddressId());
		assertEquals(adr1.getAddress1(), tempAdr.getAddress1());
		assertEquals(adr1.getCity(), tempAdr.getCity());
		assertEquals(adr1.getAddressId(), tempAdr.getAddressId());				
	}
	

	@Test
	public void testAdd2ndAddress() {
		userMgr.addAddress(adr2);
		userMgr.addAddress(adr3);
		
		adrId2 = adr2.getAddressId();
		adrId3 = adr3.getAddressId();
		
		List<Address> adrList = userMgr.getAddressList(user.getUserId());
		
		assertEquals(3, adrList.size());

		
	}
	@Test
	public void testGetAllAddressess() {
		List<Address> adrList = userMgr.getAddressList(user.getUserId());
		assertNotNull(adrList);
	}
	
	
	// get the address if we are going through the user object
	@Test
	public void testGetAllAddressFromUser() {
		User tempUser =  userMgr.getUserWithDependent(user.getUserId(),true);
		
		Set<Address> adrMap = tempUser.getAddresses();
		assertNotNull(adrMap);
		assertEquals(3, adrMap.size());
	}	
	
	
	@Test
	public void testGetDefaultAddress() {
		Address adrTemp = userMgr.getDefaultAddress(user.getUserId());
		assertNotNull(adrTemp);
		assertEquals(adrId2, adrTemp.getAddressId());
	}



	@Test
	public void testUpdateAddress() {
		
		adr2.setAddressId(adrId2);
		adr2.setAddress2("Added Address here");
		adr2.setAddress1("Updated Adr- 123 Some St");
		
		userMgr.updateAddress(adr2);
		
		Address adrTemp = userMgr.getAddressById(adrId2);
		assertEquals(adr2.getAddress1(), adrTemp.getAddress1());
		assertEquals(adr2.getAddress2(), adrTemp.getAddress2());
	
	}
	@Test
	public void testRemoveAddress1() {
		adr1.setAddressId(adrId1);
		userMgr.removeAddress(adr1);
		
		assertNull(userMgr.getAddressById(adrId1));
	
	}
	
	
	@Test
	public void testUpdateAddressThruUser() {
		
		// cannot change the address description through this method since the 
		// map is keyed on the address.
		
		
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<Address> adrSet = tempUser.getAddresses();

		Iterator<Address> it = adrSet.iterator();
		while (it.hasNext()) {
			Address adr = it.next();
			if (adr.getAddressId().equals(adrId2)) {
				adr.setAddress2("changed address");
				adr.setCity("Updated city");
			}
		}
		
		//adrMap.add(adr1);
		tempUser.setAddresses(adrSet);
		
		userMgr.updateUserWithDependent(tempUser, true);
		
		Address adrTemp = userMgr.getAddressById(adrId2);
		assertEquals("changed address", adrTemp.getAddress2());
		assertEquals("Updated city", adrTemp.getCity());
	
	}	
	


	
	
	public void testRemoveAddressThroughUser() {
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<Address> adrSet = tempUser.getAddresses();

		Iterator<Address> it = adrSet.iterator();
		while (it.hasNext()) {
			Address adr = it.next();
			if (adr.getAddressId().equals(adrId1)) {
				it.remove();
			}
		}
		

		tempUser.setAddresses(adrSet);		
		userMgr.updateUserWithDependent(tempUser, true);
		
		Address adrTemp = userMgr.getAddressById(adrId1);
		assertNull(adrTemp);
		
	}
	
	public void testAddAddressThroughUser() {
		User tempUser = userMgr.getUserWithDependent(user.getUserId(),true);
		Set<Address> adrSet = tempUser.getAddresses();
		
		Address adr = new Address();
		adr.setAddress1("changed address");
		adr.setCity("Updated city");
		adr.setName("Test");
		adr.setParentId(user.getUserId());
		adr.setParentType("USER");
		
		//adrMap.put(adr1.getDescription(), adr1);
		adrSet.add( adr);
		tempUser.setAddresses(adrSet);	
		userMgr.updateUserWithDependent(tempUser, true);
		
		Address adrTemp = userMgr.getAddressByName(user.getUserId(), "Test");
		assertNotNull(adrTemp);
		assertEquals("changed address", adrTemp.getAddress1());
		assertEquals("Updated city", adrTemp.getCity());
		
	}

	@Test
	public void testRemoveAddress() {
		adr2.setAddressId(adrId2);
		userMgr.removeAddress(adr2);
		
		assertNull(userMgr.getAddressById(adrId2));
		
		List<Address> adrList = userMgr.getAddressList(user.getUserId());
		assertEquals(2, adrList.size());		

	}

	
	
	@Test
	public void testRemoveAllAddresses() {
		userMgr.removeAllAddresses(user.getUserId());
		
		assertNull(userMgr.getAddressById(adrId2));
		
		List<Address> adrList = userMgr.getAddressList(user.getUserId());
		assertNull(adrList);		

	}
	
	public void testRemoveUser() {
		
		userMgr.removeUser(user.getUserId());
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNull(u);	
	}
	


		
}








