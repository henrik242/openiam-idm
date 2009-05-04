package org.openiam.webadmin.login;


import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.AuthenticatorAccess;


import diamelle.security.auth.*;
import diamelle.util.Log;
import diamelle.ebc.user.*;


/**
 * <p>
 * <code>LoginAction</code> <font face="arial">
 * Forwarded from AuthenticateAction or AuthFilter
 * Gets login, service and password from login.jsp
 * If service not given, reads from env variable serviceId in the web.xml file
 * If user is authenticated, service, user and token are saved in session
 * for extraction in PermissionAction. User and token are also saved in cookies.
 *
 * Control is passed to PermissionAction for extracting applications the
 * authenticated user is allowed to go to.
 * </font>
 * </p>
 */

public class LoginAction extends DiamelleBaseAction {

	protected LoginAccess loginAccess = new LoginAccess();
	//protected UserAccess userAccess = null;
	protected AuthenticatorAccess authAccess = null;
	//protected TokenAccess tknAccess = null;
	
	public LoginAction() {
		try {
		//	userAccess = new UserAccess();
			authAccess = new AuthenticatorAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		// local variables
	UserData ud = null;
	LoginValue loginValue = null;
	LoginValue lv = null;
	SSOSubject sub = null;	
	ActionErrors errors = new ActionErrors();

	String reqSessionId = request.getRequestedSessionId();
	HttpSession session = request.getSession();
	
	if (reqSessionId != null) {
		session.invalidate();
		session = request.getSession(true);
	}
	
	Locale locale = getLocale(request);
	String langCd = locale.getLanguage();
	
	if (langCd.length() > 3) {
		langCd = langCd.substring(0, 2);	
	}

	// save appId for permission action
	String appId = request.getParameter("appId");
	
	// rootMenu to get permissions
	String rootMenu = request.getParameter("rootPermissionMenu");

	// get Parameters
	String serviceId = ((LoginForm) form).getServiceId();
	String actionCommand = ((LoginForm) form).getSubmit();
	String login = ((LoginForm) form).getLogin();
	String password = ((LoginForm) form).getPassword();

	String userId = null;

	try {

		//if service not provided, get default service from environment entry
		if (serviceId == null || serviceId.length() < 1) {
			Context ctx = new InitialContext();
			serviceId = (String) ctx.lookup("java:comp/env/serviceId");
		}
		session.setAttribute("serviceId", serviceId);

		//if rootMenu not provided, get default rootMenu from environment entry
		if (rootMenu == null) {
			Context ctx = new InitialContext();
			rootMenu = (String) ctx.lookup("java:comp/env/rootPermissionMenu");
		}
		//session.setAttribute("rootMenu", rootMenu);

		// When the action command is 'Login'
		if (actionCommand.equalsIgnoreCase("login")) { 
			loginValue = authAccess.getLogin(serviceId,login);
			if (loginValue == null) {
		       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.id"));
		       return (mapping.findForward("login"));				
			}

			// creating an instance of LoginValue and setting the details
			lv = new LoginValue();
			lv.setLogin(login);
			lv.setPassword(password);
			lv.setService(serviceId);
			sub = loginAccess.authenticate(lv);
			
			session.setAttribute("token", sub.getToken());
			session.setAttribute("userId", sub.getUserId());
			session.setAttribute("login", login);
			request.setAttribute("subject", sub);
			
			// save user and token in cookie
			Cookie uidCookie = new javax.servlet.http.Cookie("userId", sub.getUserId());
			Cookie tokenCookie = new javax.servlet.http.Cookie("token", sub.getToken());
		}

		List menuList = loginAccess.getPermissions(sub.getUserId(), rootMenu, langCd);
		session.setAttribute("permissions", menuList);
		
	}catch(diamelle.security.auth.AuthenticationException ae) {

		//ae.printStackTrace();
		String errorCd = ae.getErrorCode();
		if (errorCd.equals( AuthenticationResultCode.INVALID_LOGIN )) {
	       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.id"));
		}
		if (errorCd.equals( AuthenticationResultCode.INVALID_PASSWORD )) {
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.pw"));
		}
		if (errorCd.equals( AuthenticationResultCode.INVALID_USER_STATUS )) {
	       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.user.status"));
		}
		if (errorCd.equals( AuthenticationResultCode.LOGIN_LOCKED )) {
	       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.locked"));
		}
		if (errorCd.equals( AuthenticationResultCode.PASSWORD_EXPIRED )) {
		       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.pswdexp"));
		}
		if (errorCd.equals( AuthenticationResultCode.SERVICE_NOT_FOUND )) {
		       errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.service"));
		}
		saveErrors(request, errors);
		return (mapping.findForward("login"));		
	}catch (Exception e) {
		Log.error(e.getMessage(),e);
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.problem"));
		saveErrors(request, errors);
		return (mapping.findForward("login"));
	}

	// if its a first time login, then force the user to change the password.
	if (loginValue.isResetPassword()) {
		return (mapping.findForward("changepswd"));
	}
	return (mapping.findForward("permissionfwd"));
	}

}