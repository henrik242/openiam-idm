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
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;

/**
 * @author suneet
 *
 */
public class LDAPLoginTest {
	static protected ResourceBundle res = ResourceBundle.getBundle("datasource");
	static protected ResourceBundle secres = ResourceBundle.getBundle("securityconf");
	
	static String host = res.getString("login.ldap.host");
	static String baseDn = res.getString("login.ldap.basedn");
	static String adminUserName = res.getString("login.ldap.basedn");
	static String adminPassword = res.getString("login.ldap.password");
	static String protocol = res.getString("login.ldap.protocol");
	
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
		envDC.put(Context.SECURITY_PRINCIPAL,"CN=Directory Manager");  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS,"sasny257");	
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
	String returnedAtts[]={"uid","cn","sn", "DN" };
	searchCtls.setReturningAttributes(returnedAtts);
	
	//Specify the search scope
	try {
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);


		String searchFilter = "(&(objectClass=person)(uid=" + searchValue +"))";
		String baseDN = "DC=openiam,DC=org";
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
	        System.out.println("LdapLoginTest!"); // Display the string.
	        String cn = null, uid = null, dn=null;
	        
	        LDAPLoginTest test = new LDAPLoginTest();
	        LdapContext ctx = test.connect();
	        
	        // find the user
	        NamingEnumeration nameEnum = test.search(ctx, "xxxx");
	        
		 	try {
				while (nameEnum.hasMoreElements()) {
					SearchResult sr = (SearchResult)nameEnum.next();
					Attributes attrs = sr.getAttributes();
					if (attrs != null) {
						cn = (String)attrs.get("cn").get();
						uid = (String)attrs.get("uid").get();
					//	dn = (String)attrs.get("dn").get();
					}
					System.out.println("cn=" + cn);
					System.out.println("uid=" + uid);
				}

			 	
			 	
		 	//	ModificationItem[] mods = new ModificationItem[1];
		 	//	mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", "passwd00"));
		 	//	ctx.modifyAttributes(uid, mods);
		 	}catch(NamingException ne) {
		 		ne.printStackTrace();
		 	}
		 	
	        // authenticate as the user
			 	Hashtable<String, String> envDC = new Hashtable();
			 	
				envDC.put(Context.PROVIDER_URL,host);
				envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
				envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
				envDC.put(Context.SECURITY_PRINCIPAL,uid);  //"administrator@diamelle.local"
				envDC.put(Context.SECURITY_CREDENTIALS,"passwd00");	
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
