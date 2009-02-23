package org.openiam.webadmin.login;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.openiam.webadmin.busdel.base.DiamelleBaseAction;
import org.openiam.webadmin.busdel.base.LoginAccess;

import diamelle.ebc.navigator.*;

/**
 * <p>
 * <code>PermissionAction</code> <font face="arial">
 * Forwarded here from AuthenticateAction or LoginAction
 * userId, token and appId are extracted from session
 * If appId is null, extracts ALL permissions for this user
 * Else, checks if user has rights for the application represented by appId
 * </font>
 * </p>
 */


public class PermissionAction extends DiamelleBaseAction {

  protected LoginAccess loginAccess = new LoginAccess();

  /**
   * This is the main action called from the Struts framework.
   * @param mapping The ActionMapping used to select this instance.
   * @param form The optional ActionForm bean for this request.
   * @param request The HTTP Request we are processing.
   * @param response The HTTP Response we are processing.
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException  {


      ActionErrors errors = new ActionErrors();
      HttpSession session = request.getSession();

      Locale locale = getLocale(request);
      String langCd = locale.getLanguage();
     
      // get attributes from session
      String serviceId = (String)session.getAttribute("serviceId");
      String userId = (String)session.getAttribute("userId");
      String appId = (String) session.getAttribute("appId");
      List menuList = (List) session.getAttribute("permissions");
      
/*
      System.out.println("** PermissionAction - Language cd = " + langCd);
      System.out.println("** PermissionAction - serviceId = " + serviceId);
      System.out.println("** PermissionAction - appId = " + appId);
      System.out.println("** PermissionAction - userid = " + userId);
      System.out.println("** PermissionAction - permissions = " + menuList);
*/      
      if (langCd != null && langCd.length() > 2) {
    	  langCd = langCd.substring(3,5);
      }
      
      try {
        // appId is null indicates we are looking for ALL user permissions
    
    	  
        if (appId == null) {
          System.out.println("**Checking all permissions for " + userId + " for " + serviceId + " in " + langCd);
          if (menuList.isEmpty()) {
            System.out.println("**No permissions are present for this user.");
            errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.permission.noaccess"));
            saveErrors(request,errors);
            return (mapping.findForward("login"));
          }
          return (mapping.findForward("permissionlist"));

        } else {

        // if appId is not null, we are checking if user has permission for this application
          System.out.println("**Checking permission for userId " + userId + " to access appId " + appId);
          boolean hasPermission = loginAccess.hasPermission(userId, appId);
          if (hasPermission) {
            System.out.println("**Permission granted.");
            MenuData md = loginAccess.getMenu(appId);
            request.setAttribute("menu", md);
            return (mapping.findForward("dispatcher"));
          }

          System.out.println("User: " + userId + " has no permission for appId: " + appId);
          errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.permission.noaccess"));
          //errors.add("permission", new ActionError("error.permission.noaccess"));
          saveErrors(request,errors);
          return (mapping.findForward("login"));
        }

       } catch (Exception e) {
        System.out.println("Problem in checking permissions");
        e.printStackTrace();
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.permission.problem"));
      //  errors.add("permissions", new ActionError("error.permission.problem"));
        saveErrors(request,errors);
        return (mapping.findForward("login"));
      }
  }

}