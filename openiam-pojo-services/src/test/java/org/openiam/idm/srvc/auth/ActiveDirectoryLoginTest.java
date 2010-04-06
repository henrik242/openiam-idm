/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.auth;

import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;

/**
 * @author suneet
 *
 */
public class ActiveDirectoryLoginTest {
	static protected ResourceBundle res = ResourceBundle.getBundle("datasource");
	static protected ResourceBundle secres = ResourceBundle.getBundle("securityconf");
	
	static String host = res.getString("login.ad.host");
	static String baseDn = res.getString("login.ad.basedn");
	static String adminUserName = res.getString("login.ad.basedn");
	static String adminPassword = res.getString("login.ad.password");
	static String protocol = res.getString("login.ad.protocol");
	
	static String keystore;
	LdapContext ctxLdap = null;

	
	public LdapContext connect() {

		//LdapContext ctxLdap = null;
		Hashtable<String, String> envDC = new Hashtable();
	
		//keystore = secres.getString("KEYSTORE");
		//System.setProperty("javax.net.ssl.trustStore",keystore);

		
		
		envDC.put(Context.PROVIDER_URL,host);
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,"CN=Administrator,CN=Users,DC=build,DC=openiam,DC=local");  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS,"sasny$257");	
		if (protocol != null && protocol.equalsIgnoreCase("SSL")) {
			envDC.put(Context.SECURITY_PROTOCOL, protocol);
		}
		
		try {
			return (new InitialLdapContext(envDC,null));
		}catch(NamingException ne) {
			ne.printStackTrace();
			return null;
		}

	}

	private NamingEnumeration search(LdapContext ctx,String searchValue) {
	SearchControls searchCtls = new SearchControls();
	
	//Specify the attributes to returned
	String returnedAtts[]={"distinguishedName","sAMAccountName","cn","sn" };
	searchCtls.setReturningAttributes(returnedAtts);
	
	//Specify the search scope
	try {
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);


		String searchFilter = "(&(objectClass=person)(sAMAccountName=tlucich))";
		String baseDN = "CN=Users,DC=build,DC=openiam,DC=local";
		// replace the place holder in the search filter
		//searchFilter = searchFilter.replace("?", searchValue);

		
		System.out.println("Search Filter=" + searchFilter);
		System.out.println("BaseDN=" +baseDN);
		
		return ctx.search(baseDN, searchFilter, searchCtls);
	}catch(NamingException ne) {
		ne.printStackTrace();
	}
	return null;
	
}
	
	
	 public static void main(String[] args) {
	        System.out.println("ActiveDirectoryLoginTest!"); // Display the string.
	        String cn = null, distinguishedName = null;
	        
	        ActiveDirectoryLoginTest test = new ActiveDirectoryLoginTest();
	        LdapContext ctx = test.connect();
	        
	        // find the user
	        NamingEnumeration nameEnum = test.search(ctx, "tlucich");
	        
		 	try {
				while (nameEnum.hasMoreElements()) {
					SearchResult sr = (SearchResult)nameEnum.next();
					Attributes attrs = sr.getAttributes();
					if (attrs != null) {
						cn = (String)attrs.get("cn").get();
						distinguishedName = (String)attrs.get("distinguishedName").get();
					}
					System.out.println("cn=" + cn);
					System.out.println("distinguishedName=" + distinguishedName);
				}
			 	}catch(NamingException ne) {
			 		ne.printStackTrace();
			 	}
			 	
	        
	        // authenticate as the user
			 	Hashtable<String, String> envDC = new Hashtable();
			 	
				envDC.put(Context.PROVIDER_URL,host);
				envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
				envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
				envDC.put(Context.SECURITY_PRINCIPAL,distinguishedName);  //"administrator@diamelle.local"
				envDC.put(Context.SECURITY_CREDENTIALS,"sasny$257");	
				if (protocol != null && protocol.equalsIgnoreCase("SSL")) {
					envDC.put(Context.SECURITY_PROTOCOL, protocol);
				}
				
				try {
					LdapContext userCtx = new InitialLdapContext(envDC,null);
					System.out.println("userCtx=" + userCtx);
				}catch(NamingException ne) {
					ne.printStackTrace();

				}
				
	 }

}
