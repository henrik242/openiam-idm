package org.openiam.idm.connector.ldap;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

import org.openiam.idm.connector.ldap.*;
import org.openiam.idm.connector.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;


public class LdapPasswordTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	ConnectionMgr conMgr;

	Password password;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/LdapBeansContext.xml",
								  "/LdapTestContext.xml"} ) ;
		conMgr = (ConnectionMgr)ctx.getBean("ldapConnectionMgr");
		password = (LdapPassword)ctx.getBean("ldapPassword");
	
	} 

	public void testAuthenticate() {
		Hashtable envDC = new Hashtable();
		String urlLdap =  "ldap://ocesam1.ocfl.net:636";					

		String keystore = "C:\\devtool\\Java\\jdk1.6.0_06\\jre\\lib\\security\\cacerts";
		System.setProperty("javax.net.ssl.trustStore",keystore);
			
		String adminName = "uid=openiam,ou=people,dc=iss-esu_accounts,dc=net";
		
//		String userName = "uid=openiamtest1,ou=people,dc=iss-esu_accounts,dc=net";
		
			
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,adminName);  //"administrator@diamelle.local"
		//envDC.put(Context.SECURITY_CREDENTIALS,"testing123");
		envDC.put(Context.SECURITY_CREDENTIALS,"ss0126");
		
		//password123
		
		envDC.put(Context.PROVIDER_URL,urlLdap);
		envDC.put(Context.SECURITY_PROTOCOL,"ssl");
		try {
			LdapContext ctxLdap = new InitialLdapContext(envDC,null);
			System.out.println("Auth successful. Directory context = " + ctxLdap);
		}catch(NamingException ne) {
			ne.printStackTrace();
		}

	}

	/*
	public void testSetPassword() {
		System.out.println("in set password...");
		
		password.setPassword("openiamtest1","sasny257");
	}
	*/





	
		
}








