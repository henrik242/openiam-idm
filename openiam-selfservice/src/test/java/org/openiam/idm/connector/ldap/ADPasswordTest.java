package org.openiam.idm.connector.ldap;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

import org.openiam.selfsrvc.util.ConnectionParam;
import org.openiam.selfsrvc.util.Password;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;


public class ADPasswordTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;


	Password password;
	ConnectionParam param;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/selfServeBeans.xml"} ) ;
		password = (Password)ctx.getBean("adPassword");
		//param = (ConnectionParam)ctx.getBean("adConOCGOVParam");
		param = (ConnectionParam)ctx.getBean("adConICJISParam");
		//param = (ConnectionParam)ctx.getBean("adConUTParam");
		
	} 


	
	
	public void testSetPassword() {
		System.out.println("in set password...");
	
		System.out.println("param=" + param);
		
		password.setPassword(param,"openiamtest1","sasny257");
		//password.setPassword(param,"sghost","sasny257");
	}
	





	
		
}








