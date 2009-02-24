package org.openiam.idm.connector.ad;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

import org.openiam.idm.connector.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;


public class ADServiceTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	ConnectionMgr conMgr;

	Spml2Service provisionService;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/activeDirBeansContext.xml"} ) ;
		conMgr = (ConnectionMgr)ctx.getBean("adConnectionMgr");
		provisionService = (Spml2Service)ctx.getBean("adService");
	
	} 

	
	public void testAdd() {
		System.out.println("in add user..");
		
		
		provisionService.add("will.smith","Will Smith", "Smith", "Will", "will@openiam.com", "sasny$257", "Dummy", "CT", "123 Main", "12345");
	}

/*	public void testDelete() {
		System.out.println("in delete user..");
		
		
		provisionService.delete("will.smith");
	}
*/



	
		
}








