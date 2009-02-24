/**
 * ------------------------------------------------------------------------------
 * Title: UserIdentityAction
 * Author: LD 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * logins for a user.
 * ------------------------------------------------------------------------------
 * Copyright (c) 2000-2004 Diamelle Inc. All Rights Reserved.
 *
 * This SOURCE CODE FILE, which has been provided by Diamelle Technologies as part
 * of a Diamelle Software product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of Diamelle Technologies.
 *
 * This code or parts or derivatives of it cannot be used for any commercial
 * products without written permission from Diamelle Technologies.
 *
 * USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS
 * OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
 * RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
 * CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
 * DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
 * ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
 * DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR
 * DERIVED FROM THIS SOURCE CODE FILE.
 * ------------------------------------------------------------------------------
 * CHANGE CONTROL:
 *
 * ------------------------------------------------------------------------------
 */


package org.openiam.selfserv.da.user;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;


import diamelle.security.auth.*;
import diamelle.ebc.user.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.validator.*;

public class UserIdentityAction extends NavigationDispatchAction {
	
	UserAccess userAccess = null;

	public UserIdentityAction() {
		try {
			userAccess = new UserAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}


 /**
   * Forwards it to Add/Edit Form
   * if loginId is present , sets the login details in request
   */
  public ActionForward identityForm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

      try {
        SecurityAccess securityAccess = new SecurityAccess();

        Map serviceList =  securityAccess.getAllServices();
        request.setAttribute("serviceList", serviceList);

        String personId = request.getParameter("personId");
        String loginId =  request.getParameter("loginId");
        String serviceId = request.getParameter("serviceId");

        if (loginId != null && loginId.length() > 0) {
           AuthenticatorAccess authAccess = new AuthenticatorAccess();
           // LoginValue loginValue = authAccess.getLogin(loginId, serviceId);
           // sas - temp hack as the parameters are reversed and 
           // no one bothered to fix it yet
			LoginValue loginValue = authAccess.getLogin(serviceId, loginId);
			request.setAttribute("loginValue", loginValue);
        }

        request.setAttribute("personId", personId);

     } catch(Exception ex) {
           return (mapping.findForward("failure"));
    }
      return (mapping.findForward("identity"));
  }



  /**
    * Updates the login details for a User
    */
  public ActionForward saveIdentity ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      HttpSession session = req.getSession( true );
      DynaValidatorForm userForm = (DynaValidatorForm) form;
      String operation = null;
      try {

        AuthenticatorAccess authAccess = new AuthenticatorAccess();
        LoginValue val = getLoginValue(userForm);
   		String userId = (String)session.getAttribute("userId");
   		
        if (authAccess.getLogin(val.getService(),val.getLogin()) == null) {
        	authAccess.addLogin(val);
        	operation = "CREATED";
            String msg = "Password has been auto set to: " + val.getPassword();
            req.setAttribute("msg",msg);
        }else {
        	authAccess.updateLogin(val);
        	operation = "UPDATED";
        }
        AuditLogAccess.logEvent("Identity " + val.getLogin() + " for userId =" + val.getUserId() + " has been " + operation, 
        		req.getRemoteHost(), 
           		(String)session.getAttribute("userId"),
				(String)session.getAttribute("login"), "IDM");
       

     } catch(Exception ex) {
           String errorString = "Login exists";
           req.setAttribute("errormessage", errorString);
           req.setAttribute("updatedetail","1");
           return (mapping.findForward("success"));
    }
    return (mapping.findForward("success"));
  }

  /**
    * Deletes a login
    */
  public ActionForward deleteIdentity ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res) throws IOException, ServletException {
      String serviceId = request.getParameter("serviceId");
      String loginId = request.getParameter("login");
      HttpSession session = request.getSession();
      try {

        AuthenticatorAccess authAccess = new AuthenticatorAccess();
        LoginValue loginVal = authAccess.getLogin(serviceId, loginId);
        loginVal.setStatus("DELETED");
        authAccess.updateLogin(loginVal);
        AuditLogAccess.logEvent("Identity " + loginId + " has been deleted", 
        		request.getRemoteHost(), 
           		(String)session.getAttribute("userId"),
				(String)session.getAttribute("login"), "IDM");
     } catch(Exception e) {
           String errorString = "Login exists";
           request.setAttribute("errormessage", errorString);
           request.setAttribute("updatedetail","1");
           return (mapping.findForward("success"));
     }
    return (mapping.findForward("success"));
  }

  
  public ActionForward resetPswd ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    
    String login = req.getParameter("loginId");
    String service = req.getParameter("serviceId");

    try {
      AuthenticatorAccess authAccess = new AuthenticatorAccess();
      String newPassword = authAccess.autoResetPassword(service,login);
      String msg = "Password has been reset to: " + newPassword;
      req.setAttribute("msg",msg);

   } catch(Exception ex) {
         return (mapping.findForward("success"));
  }
  return (mapping.findForward("success"));
  }

  
  public ActionForward unlockPswd ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    
    String login = req.getParameter("loginId");
    String service = req.getParameter("serviceId");

    try {
      AuthenticatorAccess authAccess = new AuthenticatorAccess();
      LoginValue loginVal = authAccess.getLogin(service, login);
      loginVal.setLocked(false);
      loginVal.setAuthFailureCount(0);
      authAccess.updateLogin(loginVal);
      String msg = "Idenity " + login + " has been unlocked: ";

   } catch(Exception ex) {
         return (mapping.findForward("success"));
  }
  return (mapping.findForward("success"));
 }  
  /**
   * Retrieves the Login details from Form and sets it in LoginValue object
   */
  private LoginValue getLoginValue(DynaValidatorForm userForm) {
     LoginValue loginValue = new LoginValue();

     loginValue.setLogin((String) userForm.get("id"));
     loginValue.setUserId((String) userForm.get("personId"));
     loginValue.setService((String) userForm.get("serviceId"));
     loginValue.setPassword((String) userForm.get("password"));
     loginValue.setPasswordEquivalentToken((String) userForm.get("pwdToken"));

     if (((String) userForm.get("pwdExp")).length() > 0) {
       Timestamp passwordexp = Timestamp.valueOf((String) userForm.get("pwdExp"));
       loginValue.setPasswordExp(passwordexp);
     }
     return loginValue;
  }

 

}