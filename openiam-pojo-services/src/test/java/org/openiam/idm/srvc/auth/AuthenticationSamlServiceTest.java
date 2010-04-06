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
import org.openiam.base.ws.BooleanResponse;

public class AuthenticationSamlServiceTest extends AbstractOpenIAMTestCase  {

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
	public void testValidateToken() {
		
		String token="<saml2:Assertion xmlns:saml2=\"urn:oasis:names:tc:SAML:2.0:assertion\" ID=\"123\"  IssueInstant=\"2010-03-01T05:30:49.730Z\" Version=\"2.0\"> " +
		" <saml2:Subject>" +
		"  <saml2:NameID Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:X509SubjectName\">mbrendish</saml2:NameID> " +
		" </saml2:Subject> " + 
		" </saml2:Assertion>";
		
		String token2 ="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		//token2.trim();
	//	try{
		
		//Subject sub = authService.passwordAuth("USR_SEC_DOMAIN", "snelson", "passwd00");
		
		//String tk = sub.getSsoToken().getToken();
		
		//System.out.println("saml = " + tk);
		
		BooleanResponse resp = authService.validateToken("snelson", token2.trim(), "SAML2_TOKEN" );
		
			System.out.println(" return= " + resp.getValue());
			//Subject sub = authService.passwordAuth("IDM", "sysadmin", "passwd00");

	//	}catch(AuthenticationException ae) {
	///		System.out.println("testGoodPassworAuth()");
	//	}
		
	}
	
/*	public void testGoodPasswordAuth() {
		try{
			Subject sub = authService.passwordAuth("USR_SEC_DOMAIN", "snelson", "passwd00");
			System.out.println("saml assertion = "+ sub.getSsoToken().getToken());
			assertNotNull(sub);
		}catch(AuthenticationException ae) {
			System.out.println("testGoodPassworAuth()");
			ae.printStackTrace();
		}
		
	}
*/	

}








