package org.openiam.idm.srvc.auth;


import org.junit.Test;
import java.util.*;


import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.auth.dto.*;
import org.openiam.idm.srvc.auth.login.*;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.exception.AuthenticationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class AuthenticationServiceTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	AuthenticationService authService;
	
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml"} ) ;
		authService = (AuthenticationService)ctx.getBean("authenticate");
		
		
	} 

	@Test
	public void testGoodPasswordAuth() {
		try{
			Subject sub = authService.passwordAuth("IDM", "sysadmin", "passwd00");
			assertNotNull(sub);
		}catch(AuthenticationException ae) {
			ae.printStackTrace();
		}
		
	}
	
/*	public void testBadPasswordAuth() {
		try {
		Subject sub = authService.passwordAuth("IDM", "sysadmin", "passwd01");
		}catch(AuthenticationException ae) {
			ae.printStackTrace();
		}
	
		
	}
	
 */
	public void testGoodToken() {

			boolean retval = authService.validateToken("Donald.Forhane@JWA.ocgov.com", "c2f89db451637ce3f3886e03c1e1d0d9def094e0029ffd76", AuthenticationConstants.OPENIAM_TOKEN);
			this.assertTrue(retval);

		
	}
	public void testBadToken() {
		boolean retval = authService.validateToken("Donald.Forhane@JWA.ocgov.com", "c2f89db451637ce3f3886e03c1e1d0d9defxxx0029ffd76", AuthenticationConstants.OPENIAM_TOKEN);
		this.assertFalse(retval);
	
		
	}
	public void testDisabledToken() {

		boolean retval = authService.validateToken("Donald.Forhane@JWA.ocgov.com", "c2f89db451637ce3f3886e03c1e1d0d9def094e0029ffd76", AuthenticationConstants.OPENIAM_TOKEN);
		this.assertFalse(retval);

	
}	
	
}








