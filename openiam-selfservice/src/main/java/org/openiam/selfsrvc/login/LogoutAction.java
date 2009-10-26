
package org.openiam.selfsrvc.login;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.selfsrvc.AppConfiguration;
import org.openiam.webadmin.busdel.base.*;
import org.apache.struts.action.ActionMessages;


public class LogoutAction extends NavigationAction {

 
  protected AppConfiguration appConfiguration;
  protected AuthenticationService authenticate;

  public ActionForward execute(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {
  	
      ActionErrors errors = new ActionErrors();
      Locale locale = getLocale(request);
      HttpSession session = request.getSession();

      String userId = request.getParameter("userId");
      if (userId == null) {
        userId = (String)session.getAttribute("userId");
      }
      
      try {
    	  if (userId != null) {
    		  authenticate.globalLogout(userId);
    	  }
    	  
        session.invalidate(); // removes everything
        
		appConfiguration = (AppConfiguration)getWebApplicationContext().getBean("appConfiguration");

		request.setAttribute("logoUrl", appConfiguration.getLogoUrl());
		request.setAttribute("title", appConfiguration.getTitle());


        // remove time-sensitive token
        Cookie [] cookies = request.getCookies();
        if (cookies!= null) {
          for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equalsIgnoreCase("token")) {
               cookies[i].setMaxAge(0);
               response.addCookie(cookies[i]);
            }
          }
        }

      } catch (Exception e) {
          e.printStackTrace();
          errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.logout.failed"));

          saveErrors(request,errors);
          return (mapping.findForward("logoutfailed"));
       }
      
       return (mapping.findForward("logout"));
    }

public AuthenticationService getAuthenticate() {
	return authenticate;
}

public void setAuthenticate(AuthenticationService authenticate) {
	this.authenticate = authenticate;
}
}