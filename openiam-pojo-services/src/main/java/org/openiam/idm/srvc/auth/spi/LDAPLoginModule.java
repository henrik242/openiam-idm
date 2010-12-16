/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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
package org.openiam.idm.srvc.auth.spi;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.sso.SSOTokenFactory;
import org.openiam.idm.srvc.auth.sso.SSOTokenModule;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;

/**
 * LDAPLoginModule provides basic password based authentication using an LDAP directory.
 * @author suneet
 *
 */
public class LDAPLoginModule extends AbstractLoginModule {

	static protected ResourceBundle res = ResourceBundle.getBundle("datasource");
	private static final Log log = LogFactory.getLog(LDAPLoginModule.class);
	
	static protected ResourceBundle secres = ResourceBundle.getBundle("securityconf");
	
	static String host = res.getString("login.ldap.host");
	static String baseDn = res.getString("login.ldap.basedn");
	static String adminUserName = res.getString("login.ldap.username");
	static String adminPassword = res.getString("login.ldap.password");
	static String protocol = res.getString("login.ldap.protocol");
	static String objectclass = res.getString("login.ldap.objectclass");
	static String pkAttribute = res.getString("login.ldap.pkAttribute");
	static String managedSysId = res.getString("login.ldap.managedSysId");
	
	static String keystore;
	LdapContext ctxLdap = null;
	
	
	public LDAPLoginModule() {
		keystore = secres.getString("KEYSTORE");
	}
	
	public void globalLogout(String securityDomain, String principal) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#login(org.openiam.idm.srvc.auth.context.AuthenticationContext)
	 */
	public Subject login(AuthenticationContext authContext) throws AuthenticationException {


		Subject sub = new Subject();
		
		log.debug("login() in LDAPLoginModule called");
		
		// current date
		Date curDate = new Date(System.currentTimeMillis());
		PasswordCredential cred =  (PasswordCredential)authContext.getCredential();
		
		String principal = cred.getPrincipal();
		String domainId = cred.getDomainId();
		String password = cred.getPassword();
			
		
		if (user != null && user.getStatus() != null ) {
			log.debug("User Status=" + user.getStatus());
			if (user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {
				if (!pendingInitialStartDateCheck(user, curDate)) {
					log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID USER STATUS", domainId, null, principal, null, null);
					throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
				}
			}				
			if (!user.getStatus().equals(UserStatusEnum.ACTIVE) && !user.getStatus().equals(UserStatusEnum.PENDING_INITIAL_LOGIN)) {
				// invalid status
				log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID USER STATUS", domainId, null, principal, null, null);
				throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
			}
			// check the secondary status
			log.debug("Secondary status=" + user.getSecondaryStatus());
			checkSecondaryStatus(user);
		
		}	
		// get the id of the user from the openiam repository
		List<Login> principalList = loginManager.getLoginByUser(user.getUserId());
		if (principalList == null) {
			log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", domainId, null, principal, null, null);
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);
		}
		Login ldapLogin = null;
		for ( Login l : principalList) {
			if (l.getId().getManagedSysId().equalsIgnoreCase(managedSysId)) {
				ldapLogin = l;
			}
		}
		if (ldapLogin == null) {
			log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", domainId, null, principal, null, null);
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);
			
		}
		if (!ldapLogin.getId().getLogin().contains(principal)) {
			log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", domainId, null, principal, null, null);
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);

		}
		
		
		 
		 // try to login to AD with this user
		 LdapContext tempCtx = connect(ldapLogin.getId().getLogin(), password);
		 if (tempCtx == null) {
			 log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "RESULT_INVALID_PASSWORD", domainId, null, principal, null, null);
				throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_PASSWORD);
			 
		 }
		 
		
		log.debug("Authentication policyid=" + securityDomain.getAuthnPolicyId());
		// get the authentication lock out policy
		Policy plcy = policyDao.findById(securityDomain.getAuthnPolicyId());
		String attrValue = getPolicyAttribute( plcy.getPolicyAttributes(), "FAILED_AUTH_COUNT");
		
		String tokenType = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_TYPE");
		String tokenLife = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_LIFE");
		String tokenIssuer = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_ISSUER");
		
		
		Map tokenParam = new HashMap();
		tokenParam.put("TOKEN_TYPE", tokenType);
		tokenParam.put("TOKEN_LIFE", tokenLife);
		tokenParam.put("TOKEN_ISSUER", tokenIssuer);
		tokenParam.put("PRINCIPAL", principal);
		
		// update the login and user records to show this authentication
		lg.setLastAuthAttempt(new Date(System.currentTimeMillis()));
		lg.setLastLogin(new Date(System.currentTimeMillis()));
		lg.setAuthFailCount(0);
		lg.setFirstTimeLogin(0);
		log.info("Good Authn: Login object updated.");
		loginManager.updateLogin(lg);
		
		// check the user status
		if (user.getStatus() != null) {
			if (user.getStatus().equals(UserStatusEnum.PENDING_INITIAL_LOGIN) ||
				// after the start date
				user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {
				
				user.setStatus(UserStatusEnum.ACTIVE);
				userManager.updateUser(user);
			}
		}	
		
		// Successful login
		sub.setUserId(lg.getUserId());
		sub.setPrincipal(principal);
		sub.setSsoToken(token(lg.getUserId(), tokenParam));
		sub.setDomainId(domainId);
		setResultCode(lg,sub, curDate);
		
		// send message into to audit log
		
		log("AUTHENTICATION", "AUTHENTICATION", "SUCCESS", null, domainId, user.getUserId(), principal, null, null);
		
		
		return sub;
}

	
	public LdapContext connect(String userName, String password) {

		//LdapContext ctxLdap = null;
		Hashtable<String, String> envDC = new Hashtable();
	
		keystore = secres.getString("KEYSTORE");
		System.setProperty("javax.net.ssl.trustStore",keystore);

		log.info("Connecting to ldap using principal=" + userName);
		
		envDC.put(Context.PROVIDER_URL,host);
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,userName);  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS,password);	
		if (protocol != null && protocol.equalsIgnoreCase("SSL")) {
			envDC.put(Context.SECURITY_PROTOCOL, protocol);
		}
		
		try {
			return (new InitialLdapContext(envDC,null));
		}catch(NamingException ne) {
			log.info(ne.getMessage());

		}
		return null;
	}


	
	private NamingEnumeration search(LdapContext ctx,String searchValue) {
		SearchControls searchCtls = new SearchControls();
		
		//Specify the attributes to returned
		String returnedAtts[]={"uid","cn","sn" };
		searchCtls.setReturningAttributes(returnedAtts);
		
		//Specify the search scope
		try {
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			String searchFilter = "(&(objectClass=" + objectclass + ")(" + pkAttribute + "=" + searchValue +"))";

			
			System.out.println("Search Filter=" + searchFilter);
			System.out.println("BaseDN=" + this.baseDn);
			
			return ctx.search(baseDn, searchFilter, searchCtls);
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		return null;
		
	}
	
	private String getDN(NamingEnumeration nameEnum) {
		String uid = null;
		
		try {
			while (nameEnum.hasMoreElements()) {
				SearchResult sr = (SearchResult)nameEnum.next();
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					uid = (String)attrs.get("uid").get();
					log.info("getDN: uid=" + uid);
					if (uid != null) {
						return uid;
					}
				}
			}
	 	}catch(NamingException ne) {
	 		ne.printStackTrace();
	 	}
		return null;		
	}
	
	/**
	 * If the password has expired, but its before the grace period then its a good login
	 * If the password has expired and after the grace period, then its an exception.
	 * You should also set the days to expiration
	 * @param lg
	 * @return
	 */
	private int passwordExpired(Login lg, Date curDate) {
		if ( lg.getGracePeriod() == null) {
			// set an early date
			lg.setGracePeriod(new Date(0));
		}
		if (lg.getPwdExp() != null) {
			if ( curDate.after(lg.getPwdExp()) && curDate.after(lg.getGracePeriod()) )  {
				// check for password expiration, but successful login
				return AuthenticationConstants.RESULT_PASSWORD_EXPIRED;
			}				
			if ((curDate.after(lg.getPwdExp()) && curDate.before( lg.getGracePeriod()))) {
				// check for password expiration, but successful login
				return AuthenticationConstants.RESULT_SUCCESS_PASSWORD_EXP;
			}	
		}
		return AuthenticationConstants.RESULT_SUCCESS_PASSWORD_EXP;
	}

	
	private String getPolicyAttribute(Set<PolicyAttribute> attr, String name) {
		assert name != null : "Name parameter is null";
		
		for ( PolicyAttribute policyAtr :attr) {
			if (policyAtr.getName().equalsIgnoreCase(name)) {
				return policyAtr.getValue1();
			}
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#logout(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void logout(String securityDomain, String principal,
			String managedSysId) {


		log("AUTHENTICATION", "LOGOUT", "SUCCESS", null, securityDomain, null, principal, null, null);

	}


	/* supporting methods */
	
	private SSOToken token(String userId, Map tokenParam) {

		tokenParam.put("USER_ID",userId);
		
		SSOTokenModule tkModule = SSOTokenFactory.createModule((String)tokenParam.get("TOKEN_TYPE"));
		return tkModule.createToken(tokenParam);
	}

}
