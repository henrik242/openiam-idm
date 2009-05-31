package org.openiam.idm.connector.ldap;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.connector.ldap.*;
import org.openiam.idm.connector.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ConnectionMgrTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	ConnectionMgr conMgr;


	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/LdapBeansContext.xml",
								  "/LdapTestContext.xml"} ) ;

		conMgr = (ConnectionMgr)ctx.getBean("ldapConnectionMgr");

		
	} 

	public void testConnect() {
		Object conObj = conMgr.connect() ;
		
		assertNotNull(conObj);
	}

	public void testDisconnect() {
		
		conMgr.close() ;
		
		
	}



	
		
}








