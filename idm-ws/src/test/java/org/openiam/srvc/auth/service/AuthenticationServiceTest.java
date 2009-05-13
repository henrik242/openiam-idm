package org.openiam.srvc.auth.service;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;


import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.exception.AuthenticationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class AuthenticationServiceTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext  ctx = null;

	AuthenticationService authService;


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		
	
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"client-beans.xml"});
		authService = (AuthenticationService) ctx.getBean("authenticateService");

		
	}



	@Test
	public void testAuthenticate() {
		
		try {
			Subject sub = authService.passwordAuth("USR_SEC_DOMAIN", "sshah", "passwd00");
		}catch(AuthenticationException ae) {
			ae.printStackTrace();
		}
		
	}
	
	
	
}
