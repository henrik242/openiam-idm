package org.openiam.idm.srvc.auth;


import org.junit.Test;
import java.util.*;


import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.auth.context.AuthContextFactory;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.*;
import org.openiam.idm.srvc.auth.login.*;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.exception.AuthenticationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class AuthenticationServiceTest extends AbstractOpenIAMTestCase  {

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
			System.out.println("testGoodPassworAuth()");
			ae.printStackTrace();
		}
		
	}
	
/*	public void testBadPasswordAuth() {
		try {
			Subject sub = authService.passwordAuth("IDM", "sysadmin", "passwd01");
		}catch(AuthenticationException ae) {
			System.out.println("testBadPasswordAuth()");
			System.out.println("Authentication exception caugth. error code=" + ae.getErrorCode());
		}
	}
*/
/*	public void testWithAuthContext() {
		try{
			AuthenticationContext ctx = AuthContextFactory.createContext("org.openiam.idm.srvc.auth.context.AuthenticationContextImpl");
			ctx.setDomainId("IDM");
			
			PasswordCredential cred = (PasswordCredential) ctx.createCredentialObject( AuthenticationConstants.AUTHN_TYPE_PASSWORD );
			cred.setCredentials("IDM", "sysadmin", "passwd00");
			ctx.setCredential(AuthenticationConstants.AUTHN_TYPE_PASSWORD , cred);
			
			Subject sub = authService.authenticate(ctx);
			assertNotNull(sub);
		}catch(AuthenticationException ae) {
			System.out.println("testGoodPassworAuth() failed");
			ae.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
*/
	
/*	public void testPendingInitialLogin() {
		try{
				Subject sub = authService.passwordAuth("IDM", "test1", "passwd00");
				this.assertEquals(AuthenticationConstants.RESULT_SUCCESS, sub.getResultCode()); 
		}catch(AuthenticationException ae) {
			ae.printStackTrace();
		}
		
	}

	public void testInactiveStatus() {
		try{
				Subject sub = authService.passwordAuth("IDM", "test2", "passwd00");
				
		}catch(AuthenticationException ae) {
			System.out.println("error code=" + ae.getErrorCode());
			this.assertEquals(AuthenticationConstants.RESULT_INVALID_USER_STATUS, ae.getErrorCode() );
		}
		
	}

	public void testLockedUser() {
		System.out.println("Test locked user..");
		try{
				Subject sub = authService.passwordAuth("IDM", "test3", "passwd00");
				
		}catch(AuthenticationException ae) {
			System.out.println("error code=" + ae.getErrorCode());
			this.assertEquals(AuthenticationConstants.RESULT_LOGIN_LOCKED, ae.getErrorCode() );
		}
		
	}

	
	public void testPendingStartDate() {
		System.out.println("Test Pending start date user..");
		try{
				Subject sub = authService.passwordAuth("IDM", "test5", "passwd00");
				
		}catch(AuthenticationException ae) {
			System.out.println("error code=" + ae.getErrorCode());
			this.assertEquals(AuthenticationConstants.RESULT_INVALID_USER_STATUS, ae.getErrorCode() );
		}
		
	}

*/

	
	/*	public void testGoodToken() {

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
*/	
}








