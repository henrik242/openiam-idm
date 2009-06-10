package org.openiam.idm.srvc.auth;


import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.auth.dto.*;
import org.openiam.idm.srvc.auth.login.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class LoginrMgrTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user;
	Login login, login2;
	LoginDataService loginService;
	Supervisor supr1, supr2, supr3;
	static String suprObj1, suprObj2, suprObj3;
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/loginTest-applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		loginService = (LoginDataService)ctx.getBean("loginManager");
		user = (User)ctx.getBean("userBeanAddress");
		login = (Login)ctx.getBean("loginBean");
		login2 = (Login)ctx.getBean("loginBean2");
		
	} 

	@Test
	public void testAddUsers() {
		userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		
	}
	@Test
	public void testAddLogin() {	
		Login lg = loginService.addLogin(login);
		assertNotNull(lg);
		
		
	}	

	public void testAdd2ndLogin() {	
		Login lg = loginService.addLogin(login2);
		assertNotNull(lg);		
	}	

	
	public void testGetLogin() {	
	//	Login lg = loginService.getLogin(login.getId().getServiceId(), login.getId().getLogin(), "0");

	//	assertNotNull(lg);
				
	}

	public void testUpdateLogin() {
		login2.setIsLocked(1);
		login2.setPassword("updpasswd");
		loginService.updateLogin(login2);

	//	Login lg2 = loginService.getLogin(login2.getId().getServiceId(), login2.getId().getLogin(), "0"));
		
	//	assertNotNull(lg2);
				
	}
	
	
	public void testLoginsForUser() {	
		Login[] lgAry = loginService.getLoginByUser(login2.getUser().getUserId());
		assertEquals(2, lgAry.length);		
	}
	
	
	public void testRemoveLogin() {	
		loginService.removeLogin(login.getId().getServiceId(), login.getId().getLogin());
	//	assertNull(loginService.getLogin(login.getId().getServiceId(), login.getId().getLogin()), "0");
		
		loginService.removeLogin(login2.getId().getServiceId(), login2.getId().getLogin());
		
	}	

	public void testCleanupUsers() {
		userMgr.removeUser(user.getUserId());
		
	}
	
}








