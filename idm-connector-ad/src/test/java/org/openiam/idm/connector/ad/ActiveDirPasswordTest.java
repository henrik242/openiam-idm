package org.openiam.idm.connector.ad;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

import org.openiam.idm.connector.ad.*;
import org.openiam.idm.connector.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;


public class ActiveDirPasswordTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	ConnectionMgr conMgr;

	Password password;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/activeDirBeansContext.xml",
								  "/activeDirTestContext.xml"} ) ;
		conMgr = (ConnectionMgr)ctx.getBean("adConnectionMgr");
		password = (ActiveDirPassword)ctx.getBean("password");
	
	} 


	public void testSetPassword() {
		System.out.println("in set password...");
		
		// try to do a search
		
			
		String userName ="107529";
		String userName2 = "openiamtest1";
		password.setPassword(userName,"testing456");
		password.setPassword(userName2,"testing456");
	}





	
		
}








