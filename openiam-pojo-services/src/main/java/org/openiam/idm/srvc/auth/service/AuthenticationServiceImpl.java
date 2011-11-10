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
package org.openiam.idm.srvc.auth.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.SysConfiguration;
import org.openiam.base.ws.BooleanResponse;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.base.ws.StringResponse;
import org.openiam.exception.AuthenticationException;
import org.openiam.exception.LogoutException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditLogUtil;
import org.openiam.idm.srvc.auth.context.AuthContextFactory;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.AuthState;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginModuleSelector;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.AuthStateDAO;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.spi.AbstractLoginModule;
import org.openiam.idm.srvc.auth.spi.LoginModule;
import org.openiam.idm.srvc.auth.spi.LoginModuleFactory;
import org.openiam.idm.srvc.auth.sso.SSOTokenFactory;
import org.openiam.idm.srvc.auth.sso.SSOTokenModule;
import org.openiam.idm.srvc.auth.ws.AuthenticationResponse;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.util.encrypt.Cryptor;

//import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author suneet
 *
 */

@WebService(endpointInterface = "org.openiam.idm.srvc.auth.service.AuthenticationService", 
		targetNamespace = "urn:idm.openiam.org/srvc/auth/service",
		portName = "AuthenticationServicePort",
		serviceName = "AuthenticationService")
@ManagedResource(objectName="openiam:name=authenticationService", description="Authentication Service")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	protected LoginModule defaultLoginModule;
	protected AuthStateDAO authStateDao;
	protected LoginDataService loginManager;
	protected SecurityDomainDataService secDomainService; 
	protected String authContextClass;
    protected ResourceDataService resourceService;
	
	protected SSOTokenModule defaultToken;
	protected UserDataService userManager;
	protected PolicyDAO policyDao;
	protected Cryptor cryptor;
	protected AuditLogUtil auditUtil;
		
	GroupDataService groupManager; 
	RoleDataService roleManager;
	protected String scriptEngine;
	protected SysConfiguration sysConfiguration;
    protected PasswordService passwordManager;
	
	private static final Log log = LogFactory.getLog(AuthenticationServiceImpl.class);

	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#authenticate(org.openiam.idm.srvc.auth.context.AuthenticationContext)
	 */
	//public Subject authenticate(AuthenticationContext ctx)	throws AuthenticationException {
	public AuthenticationResponse authenticate(AuthenticationContext ctx) {
		AuthenticationResponse authResp = new AuthenticationResponse(ResponseStatus.FAILURE);
		
		AbstractLoginModule loginModule = null;
		
		if (ctx == null) {
			throw new NullPointerException("AuthenticationContext parameter is null");
		}
		
		String secDomainId = ctx.getDomainId();
		
		SecurityDomain secDomain = secDomainService.getSecurityDomain(secDomainId);
		if (secDomain == null) {
			authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_DOMAIN);
			return authResp;
			
			//throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_DOMAIN);
		}
		try {
			log.debug("loginModule=" + secDomain.getDefaultLoginModule());
			
			// create the authentication module class
			// if the authenticationcontext has a class, that will over-ride the one that is with the domain
			// later add the abilty to define a login module at the resource level
			if (ctx.getLoginModule() != null) {
				loginModule = (AbstractLoginModule)LoginModuleFactory.createModule(ctx.getLoginModule());
			}else {
				loginModule = (AbstractLoginModule)LoginModuleFactory.createModule(secDomain.getDefaultLoginModule());
			}
			/* Dependency injection fails when we use our own factory. 
			 * Set the necessary beans directly 
			 */
			loginModule.setLoginService(loginManager);
			loginModule.setTokenModule(defaultToken);
			loginModule.setUserService(userManager);
			loginModule.setPolicyDAO(policyDao);
			loginModule.setCryptor(cryptor);
			loginModule.setSecurityDomain(secDomain);
			loginModule.setAuditUtil(auditUtil);

		}catch(Exception ie) {
			log.error(ie.getMessage(),ie);
			authResp.setAuthErrorCode(AuthenticationConstants.INTERNAL_ERROR);
			return authResp;
		}
		
		Map<String,Object> authParamMap = new HashMap<String,Object>();
		authParamMap.put("SEC_DOMAIN_ID",secDomainId);
		authParamMap.put("AUTH_SYS_ID", secDomain.getAuthSysId());
		ctx.setAuthParam(authParamMap);
		
		try {
			Subject sub = loginModule.login(ctx);
			// add the sso token to the authstate
			
			updateAuthState(sub);
				
			populateSubject(sub.getUserId(), sub);
			
			authResp.setSubject(sub);
			authResp.setStatus(ResponseStatus.SUCCESS);
			return authResp;

		}catch(AuthenticationException ae) {
			int errCode = ae.getErrorCode();
			switch (errCode) {
			case AuthenticationConstants.RESULT_INVALID_DOMAIN:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_DOMAIN);
				break;
			case AuthenticationConstants.RESULT_INVALID_LOGIN:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
				break;
			case AuthenticationConstants.RESULT_INVALID_PASSWORD:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_PASSWORD);
				break;
			case AuthenticationConstants.RESULT_INVALID_USER_STATUS:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
				break;
			case AuthenticationConstants.RESULT_LOGIN_DISABLED:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_LOGIN_DISABLED);
				break;
			case AuthenticationConstants.RESULT_LOGIN_LOCKED:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_LOGIN_LOCKED);
				break;
			case AuthenticationConstants.RESULT_PASSWORD_EXPIRED:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_PASSWORD_EXPIRED);
				break;
			case AuthenticationConstants.RESULT_SERVICE_NOT_FOUND:
				authResp.setAuthErrorCode(AuthenticationConstants.RESULT_SERVICE_NOT_FOUND);
				break;
			default:
				authResp.setAuthErrorCode(AuthenticationConstants.INTERNAL_ERROR);
			}
			return authResp;
		}

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#authenticateByToken(java.lang.String, java.lang.String, java.lang.String)
	 */
    @ManagedAttribute
	public Subject authenticateByToken(String userId, String token,	String tokenType) throws AuthenticationException {
		// TODO Auto-generated method stub
		BooleanResponse resp = validateTokenByUser(userId, token, tokenType);
		if (!resp.getValue()) {
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_TOKEN);
		}
		

		AuthState authSt = authStateDao.findById(userId);
		Subject sub = new Subject(userId);
		sub.setExpirationTime(authSt.getExpiration());
		sub.setResultCode(AuthenticationConstants.RESULT_SUCCESS);
				
		populateSubject(userId, sub);
		
		return sub;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#globalLogout(java.lang.String)
	 */
    @ManagedAttribute
	public void globalLogout(String userId) throws LogoutException {
		if (userId == null) {
			throw new NullPointerException("UserId is null");
		}
		
		AuthState authSt = authStateDao.findById(userId);
		if (authSt == null) {
			log.error("AuthState not found for userId=" + userId);
			throw new LogoutException();
		}

		authSt.setAuthState(new BigDecimal(0));
		authSt.setToken(null);
		this.authStateDao.saveAuthState(authSt);
		
	
		//IdmAuditLog log = new IdmAuditLog( "AUTHENTICATION", "LOGOUT", "SUCCESS", null,null,userId,  null, null, null);
		//auditUtil.log(log);
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#passwordAuth(java.lang.String, java.lang.String, java.lang.String)
	 */
	//public AuthenticationResponse passwordAuth(String secDomainId, String principal, String password) throws AuthenticationException {
	@ManagedAttribute
    public AuthenticationResponse passwordAuth(String secDomainId, String principal, String password) {

        log.debug("*** PasswordAuth called...");

		 AuthenticationResponse authResp = new AuthenticationResponse(ResponseStatus.FAILURE);
		 
		 AuthenticationContext ctx = null;
		 AbstractLoginModule loginModule = null;
		 Policy authPolicy = null;
		 String loginModName = null;
		 LoginModuleSelector modSel = new LoginModuleSelector();

        Login lg = null;
        String userId = null;
        User user = null;


		
		SecurityDomain secDomain = secDomainService.getSecurityDomain(secDomainId);
		if (secDomain == null) {
			//throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_DOMAIN);
			authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_DOMAIN);
			return authResp;
			
		}

        // Determine which login module to use
        // - get the Authentication policy for the domain
        String authPolicyId =  secDomain.getAuthnPolicyId();

        log.debug("Authn policyId=" + authPolicyId);

        authPolicy = policyDao.findById(authPolicyId);

        log.debug("Auth Policy object=" + authPolicy);

        PolicyAttribute modType = authPolicy.getAttribute("LOGIN_MOD_TYPE");
        PolicyAttribute defaultModule = authPolicy.getAttribute("DEFAULT_LOGIN_MOD");
        loginModName = defaultModule.getValue1();
        if (modType !=null) {
            //modSel.setModuleType( Integer.parseInt(modType.getValue1()));
            modSel.setModuleName(loginModName);
        }

		//	log.debug("loginModule=" + secDomain.getDefaultLoginModule());
			


            if (loginModName.equalsIgnoreCase("org.openiam.idm.srvc.auth.spi.DefaultLoginModule"))  {
                /* Few basic checks must be met before calling the login module. */
                /* Simplifies the login module */
                if (principal == null || principal.length()==0) {

                    log.debug("Invalid login:" + principal);

                    log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", secDomainId, null, principal, null, null);
                    //throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);

                    authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
                    return authResp;

                }

                if (password == null 	|| password.equals("")) {

                    log.debug("Invalid password");

                    log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID PASSWORD", secDomainId, null, principal, null, null);
                    //throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_PASSWORD);

                    authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_PASSWORD);
                    return authResp;

                }

                lg = loginManager.getLoginByManagedSys(secDomainId,principal, secDomain.getAuthSysId());

                log.debug("login object after looking up the login:" + lg);

                if (lg == null) {
                    log.debug("Login not found. Throw authentication exception");
                    log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", secDomainId, null, principal, null, null);
                    //throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);

                    authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
                    return authResp;

                }else {
                    log.debug("Login found. No exception thrown");
                }


                			// check the user status - move to the abstract class for reuse
		    userId = lg.getUserId();

			log.debug("UserId=" + userId);
			user = userManager.getUserWithDependent(userId, false);
			log.debug("User object for " + userId + "=" + user);
            }

		try {

            log.debug("Creating authentication context");

			ctx = AuthContextFactory.createContext(authContextClass);
			

			
			
			PolicyAttribute selPolicy =  authPolicy.getAttribute("LOGIN_MODULE_SEL_POLCY");
			if (selPolicy != null &&  selPolicy.getValue1() != null && selPolicy.getValue1().length() > 0 ) {

                log.debug("Calling policy selection rule");
			
				Map<String, Object> bindingMap = new HashMap<String, Object>();
				bindingMap.put("secDomainId", secDomainId);
				bindingMap.put("principal", principal);
				bindingMap.put("sysId", secDomain.getAuthSysId());
				// also bind the user and login objects to avoid re-initialization of spring the scripting engine
				bindingMap.put("login",lg);
				bindingMap.put("user", user);
						
				ScriptIntegration se = ScriptFactory.createModule(this.scriptEngine); 
				loginModName = (String)se.execute(bindingMap, selPolicy.getValue1());
				
				log.debug("LoginModName from script =" + loginModName);
				
			}else {
				log.debug("retrieving default login module for policy");
				
				// test code
				Set<PolicyAttribute> attSet = authPolicy.getPolicyAttributes();
				Iterator<PolicyAttribute> it = attSet.iterator();
				/*while( it.hasNext() ) {
					PolicyAttribute attr = it.next();
					System.out.println("Name=" + attr.getName());
				}
				*/

			}
			log.debug("login module name=" + loginModName);
			
			if ( modSel.getModuleType() == LoginModuleSelector.MODULE_TYPE_LOGIN_MODULE ) {
				loginModule = (AbstractLoginModule)LoginModuleFactory.createModule(loginModName);
				/* Dependency injection fails when we use our own factory. 
				 * Set the necessary beans directly 
				 */
				loginModule.setLoginService(loginManager);
				loginModule.setTokenModule(defaultToken);
				loginModule.setUserService(userManager);
				loginModule.setPolicyDAO(policyDao);
				loginModule.setCryptor(cryptor);
				loginModule.setSecurityDomain(secDomain);
				loginModule.setAuditUtil(auditUtil);
				loginModule.setUser(user);
				loginModule.setLg(lg);
                loginModule.setAuthPolicyId(authPolicyId);
                loginModule.setResourceService(resourceService);
                loginModule.setPasswordManager(passwordManager);
			}
			
		
		}catch(Exception ie) {
			log.error(ie.getMessage(),ie);
			//throw (new AuthenticationException(AuthenticationConstants.INTERNAL_ERROR,ie.getMessage(),ie));
			authResp.setAuthErrorCode(AuthenticationConstants.INTERNAL_ERROR);
			return authResp;
		}
		PasswordCredential cred = (PasswordCredential) ctx.createCredentialObject( AuthenticationConstants.AUTHN_TYPE_PASSWORD );
		cred.setCredentials(secDomainId, principal,password);
		ctx.setCredential(AuthenticationConstants.AUTHN_TYPE_PASSWORD , cred);
		
		Map<String,Object> authParamMap = new HashMap<String,Object>();
		authParamMap.put("SEC_DOMAIN_ID",secDomainId);
		authParamMap.put("AUTH_SYS_ID", secDomain.getAuthSysId());
		ctx.setAuthParam(authParamMap);
		
		Subject sub = null;
		if ( modSel.getModuleType() == LoginModuleSelector.MODULE_TYPE_LOGIN_MODULE) {
			try {
				sub = loginModule.login(ctx);
				
			}catch(AuthenticationException ae) {

                log.debug("Authentication error " + ae.toString());

				int errCode = ae.getErrorCode();
				switch (errCode) {
				case AuthenticationConstants.RESULT_INVALID_DOMAIN:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_DOMAIN);
					break;
				case AuthenticationConstants.RESULT_INVALID_LOGIN:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
					break;
				case AuthenticationConstants.RESULT_INVALID_PASSWORD:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_PASSWORD);
					break;
				case AuthenticationConstants.RESULT_INVALID_USER_STATUS:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
					break;
				case AuthenticationConstants.RESULT_LOGIN_DISABLED:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_LOGIN_DISABLED);
					break;
				case AuthenticationConstants.RESULT_LOGIN_LOCKED:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_LOGIN_LOCKED);
					break;
				case AuthenticationConstants.RESULT_PASSWORD_EXPIRED:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_PASSWORD_EXPIRED);
					break;
				case AuthenticationConstants.RESULT_SERVICE_NOT_FOUND:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_SERVICE_NOT_FOUND);
					break;
                case AuthenticationConstants.RESULT_INVALID_CONFIGURATION:
					authResp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_CONFIGURATION);
					break;
				default:
					authResp.setAuthErrorCode(AuthenticationConstants.INTERNAL_ERROR);
				}
				return authResp;
			}

		}else {
			
		}
		// add the sso token to the authstate
		
		updateAuthState(sub);
		populateSubject(sub.getUserId(), sub);

        log.debug("*** PasswordAuth complete...Returning response object");
		
		authResp.setSubject(sub);
		authResp.setStatus(ResponseStatus.SUCCESS);
		return authResp;

	}
	
	private void populateSubject(String userId, Subject sub) {
		log.debug("populateSubject: userId=" + userId);
		
		List<Group> groupList = groupManager.getUserInGroups(userId);
		List<Role> roleAry = roleManager.getUserRoles(userId);
		
		if (groupList != null && !groupList.isEmpty()) {
			sub.setGroups(groupList);
		}
		if (roleAry != null && !roleAry.isEmpty()) {
			sub.setRoles(roleAry);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#validateToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	public BooleanResponse validateToken(String loginId, String token, String tokenType) {
			
		if (loginId == null) {
			throw new IllegalArgumentException("loginId is null");
		}
		if (token == null)  {
			throw new IllegalArgumentException("token is null");
		}
		if (tokenType == null) {
			throw new IllegalArgumentException("tokenType is null");
		}
		
		log.debug("validateToken token=" + token);
		
		// check if this is a valid user
		Login lg = loginManager.getLoginByManagedSys(this.sysConfiguration.getDefaultSecurityDomain(),
				loginId, this.sysConfiguration.getDefaultManagedSysId());
		if (lg == null) {
			log.debug("login object is null.");
			BooleanResponse resp = new BooleanResponse(false);	
			return resp;
		}
		
		// get handler for the token type
		SSOTokenModule tkModule = SSOTokenFactory.createModule(tokenType);
		tkModule.setCryptor(cryptor);
		boolean tokenStatus = tkModule.isTokenValid(lg.getUserId(), loginId, token);
		
		BooleanResponse resp = new BooleanResponse(tokenStatus);
		return resp;
	
		
	}
	
	@WebMethod
	public Response renewToken(
			String principal,  String token, String tokenType) {
		
		Response resp = new Response(ResponseStatus.SUCCESS);
		
		// validateToken first
		
		SecurityDomain secDomain = secDomainService.getSecurityDomain(getSysConfiguration().getDefaultSecurityDomain());
		
		Policy plcy = policyDao.findById(secDomain.getAuthnPolicyId());
		String attrValue = getPolicyAttribute( plcy.getPolicyAttributes(), "FAILED_AUTH_COUNT");
		String tokenLife = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_LIFE");
		String tokenIssuer = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_ISSUER");
		
		// get the userId of this token
		Login lg = loginManager.getLoginByManagedSys(sysConfiguration.getDefaultSecurityDomain(),
				principal, sysConfiguration.getDefaultManagedSysId());
		
		if (lg == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		
		Map tokenParam = new HashMap();
		tokenParam.put("TOKEN_TYPE", tokenType);
		tokenParam.put("TOKEN_LIFE", tokenLife);
		tokenParam.put("TOKEN_ISSUER", tokenIssuer);
		
		tokenParam.put("USER_ID",lg.getUserId());
		tokenParam.put("PRINCIPAL",principal);
		
		SSOTokenModule tkModule = SSOTokenFactory.createModule((String)tokenParam.get("TOKEN_TYPE"));
        tkModule.setCryptor(this.cryptor);
        tkModule.setTokenLife(Integer.parseInt( tokenLife));
		
		if (!tkModule.isTokenValid(lg.getUserId(), principal, token))  {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		
		SSOToken ssoToken = tkModule.createToken(tokenParam);
		resp.setResponseValue(ssoToken);
		return resp;
		
		
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
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#validateTokenByUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public BooleanResponse validateTokenByUser(String userId, String token, 	String tokenType) {
		if (userId == null) {
			throw new IllegalArgumentException("userId is null");
		}
		if (token == null)  {
			throw new IllegalArgumentException("token is null");
		}
		if (tokenType == null) {
			throw new IllegalArgumentException("tokenType is null");
		}
		
		// get the user
		
		User user = userManager.getUserWithDependent(userId, false);
		if (user == null) {
			// invalid user
			BooleanResponse resp = new BooleanResponse(false);	
			return resp;
		}
		// get the password policy
		SecurityDomain secDomain = secDomainService.getSecurityDomain(getSysConfiguration().getDefaultSecurityDomain());
		
		Policy plcy = policyDao.findById(secDomain.getAuthnPolicyId());
		String tokenLife = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_LIFE");
		String tokenIssuer = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_ISSUER");

		
		
		// get the primary identity
		Login lg = this.loginManager.getPrimaryIdentity(userId);
		SSOTokenModule tkModule = SSOTokenFactory.createModule(tokenType);
		tkModule.setCryptor(cryptor);
		tkModule.setTokenLife(Integer.parseInt(tokenLife));
		
		boolean tokenStatus = tkModule.isTokenValid(lg.getUserId(), lg.getId().getLogin(), token);

		BooleanResponse resp = new BooleanResponse(tokenStatus);
		return resp;

	}
	
	private void updateAuthState(Subject sub) {
		
		AuthState state = new AuthState(sub.getDomainId(), new BigDecimal(1),  sub.getSsoToken().getExpirationTime().getTime(),
				sub.getSsoToken().getToken(), sub.getUserId());
	
		authStateDao.saveAuthState(state);
	}
	
	public void updateAppStatus (String managedSysId, String loginId, String status, String sessionId, String token) {
		
	}

	public LoginModule getDefaultLoginModule() {
		return defaultLoginModule;
	}

	public void setDefaultLoginModule(LoginModule defaultLoginModule) {
		this.defaultLoginModule = defaultLoginModule;
	}

	public AuthStateDAO getAuthStateDao() {
		return authStateDao;
	}

	public void setAuthStateDao(AuthStateDAO authStateDao) {
		this.authStateDao = authStateDao;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public String getAuthContextClass() {
		return authContextClass;
	}

	public void setAuthContextClass(String authContextClass) {
		this.authContextClass = authContextClass;
	}

	public SSOTokenModule getDefaultToken() {
		return defaultToken;
	}

	public void setDefaultToken(SSOTokenModule defaultToken) {
		this.defaultToken = defaultToken;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}

	public PolicyDAO getPolicyDao() {
		return policyDao;
	}

	public void setPolicyDao(PolicyDAO policyDao) {
		this.policyDao = policyDao;
	}

	public GroupDataService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}

	public RoleDataService getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleDataService roleManager) {
		this.roleManager = roleManager;
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}

	public AuditLogUtil getAuditUtil() {
		return auditUtil;
	}

	public void setAuditUtil(AuditLogUtil auditUtil) {
		this.auditUtil = auditUtil;
	}
	
	private SSOToken token(String userId, Map tokenParam) {

		tokenParam.put("USER_ID",userId);
		
		SSOTokenModule tkModule = SSOTokenFactory.createModule((String)tokenParam.get("TOKEN_TYPE"));
		return tkModule.createToken(tokenParam);
	}

	
	
	public void log(String objectTypeId, String actionId, String actionStatus, String reason, 
			String domainId, String userId, String principal, 
			String linkedLogId, String clientId) {
		IdmAuditLog log = new IdmAuditLog( objectTypeId, actionId, actionStatus,
				reason, domainId, userId, principal,
				linkedLogId, clientId);
		auditUtil.log(log);
	}

	public String getScriptEngine() {
		return scriptEngine;
	}

	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}

	public SysConfiguration getSysConfiguration() {
		return sysConfiguration;
	}

	public void setSysConfiguration(SysConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}

    public ResourceDataService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceDataService resourceService) {
        this.resourceService = resourceService;
    }

    public PasswordService getPasswordManager() {
        return passwordManager;
    }

    public void setPasswordManager(PasswordService passwordManager) {
        this.passwordManager = passwordManager;
    }
}
