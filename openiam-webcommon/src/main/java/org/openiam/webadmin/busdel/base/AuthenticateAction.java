package org.openiam.webadmin.busdel.base;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

import diamelle.security.auth.*;

/**
 * <p>
 * <code>AuthenticateAction</code> <font face="arial">
 * The main authentication class, whether user is authenticated or not
 * Checks request object for appId, userId, token. If not there
 * checks session object for userId, token. If not there
 * checks cookies for userId, token. If userId is still null
 * forwards to login page. Otherwise does authentication.
 * If authenticated, forwards to PermissionAction.
 * </font>
 * </p>
 */

public class AuthenticateAction extends DiamelleBaseAction {

  protected LoginAccess loginAccess = new LoginAccess();

  public ActionForward execute(ActionMapping mapping,
         ActionForm form,
         HttpServletRequest request,
         HttpServletResponse response)
  throws IOException, ServletException {


      ActionErrors errors = new ActionErrors();
      HttpSession session = request.getSession();

      Locale locale = getLocale(request);
      String langCd = locale.getLanguage();
 
	  String errMsg = request.getParameter("logError");
      if (errMsg != null) {
          errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(errMsg));

		// errors.add("authentication", new ActionError(errMsg));
		 saveErrors(request,errors);
		 return (mapping.findForward("login"));
	  }

      // get application id and store
      String appId = request.getParameter("appId");
      if ((appId != null) && (appId.length()>0))
         session.setAttribute("appId", appId);
      else
         session.removeAttribute("appId"); // want to show ALL permissions in PermissionAction

      // get user id and store
      String userId = request.getParameter("userId");
      if (userId == null)
         userId = (String)session.getAttribute("userId");

      String urlEncodedToken = request.getParameter("token");
      if (urlEncodedToken == null)
         urlEncodedToken = (String)session.getAttribute("token");

      // if no values, then does the value exist in cookies?
      if (userId == null && urlEncodedToken == null) {
        System.out.println("User not given, checking cookies");
        Cookie [] cookies = request.getCookies();
        if (cookies!= null) {
          for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equalsIgnoreCase("userId"))
               userId = cookies[i].getValue();
            if (cookies[i].getName().equalsIgnoreCase("token"))
               urlEncodedToken = cookies[i].getValue();
          }
        }
      }

      // If User is not give then display login.jsp,
      if (userId == null) {
         return (mapping.findForward("login"));
      }

      String token = null;
      if (urlEncodedToken != null)
          token = java.net.URLDecoder.decode(urlEncodedToken);

      try {
         SSOSubject sub = loginAccess.authenticate(userId, token);
         if (sub == null) {
        	 errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.failed"));
           // errors.add("authentication", new ActionError("error.login.failed"));
            saveErrors(request,errors);
            return (mapping.findForward("login"));
         }

         String serviceId = (String) session.getAttribute("serviceId");

         // Save permissions in session xx already done in AuthFilter
         //String rootMenu = (String)session.getAttribute("rootPermissions");
         //List menuList = loginAccess.getPermissions(userId, rootMenu, langCd);
         //session.setAttribute("permissions", menuList);

       } catch (Exception e) {
        //e.printStackTrace();
    	   errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.problem"));
        //errors.add("authentication", new ActionError("error.login.problem"));
        saveErrors(request,errors);
        return (mapping.findForward("login"));
      }
      return (mapping.findForward("permissionfwd") );
    }
}