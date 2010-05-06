package org.openiam.idm.srvc.user;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.util.db.*;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.util.db.Search;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;
 
/**
 * Tests that a user object is being initialized correctly when loading dependants
 * @author suneet
 *
 */
public class UserMgrInitTest extends AbstractOpenIAMTestCase  {

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

	

	
	@Test
	public void testAddUser() {

		user = userMgr.addUser(user);

		assertNotNull(user.getUserId());
		
		Phone ph = new Phone();
		ph.setAreaCd("123");
		ph.setPhoneNbr("12345");
		ph.setParentId(user.getUserId());
		ph.setParentType("USER");
		ph.setName("test");
		user.getPhone().add(ph);
		userMgr.updateUser(user);
		

		
		
		userId = user.getUserId();
		
	}
	
	@Test
	public void testGetUser() {
		User u = userMgr.getUserWithDependent(userId,true);
		assertNotNull(u);
		// test if phone has been initialized
		Set<Phone> phSet = u.getPhone();
		Iterator<Phone> it = phSet.iterator();
		while (it.hasNext()) {
			Phone p = it.next();
			System.out.println("Phone = " + p.getName());
		}
		
	}

	
	@Test
	public void testRemoveUser() {
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);	
	}
	



	
		
}








