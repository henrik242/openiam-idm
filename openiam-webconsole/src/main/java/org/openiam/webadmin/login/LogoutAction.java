
package org.openiam.webadmin.login;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.webadmin.util.AuditHelper;
import org.springframework.web.struts.DispatchActionSupport;
import org.apache.struts.action.ActionMessages;





public class LogoutAction extends DispatchActionSupport  {

	protected AuthenticationService authenticate;
    protected AuditHelper auditHelper;


  public ActionForward execute(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {
	  
	  System.out.println("logout called");
  	
      ActionErrors errors = new ActionErrors();
      Locale locale = getLocale(request);
      HttpSession session = request.getSession();

      String userId = request.getParameter("userId");
      if (userId == null) {
        userId = (String)session.getAttribute("userId");
      }

     String login = (String)request.getSession().getAttribute("login");
     String domain = (String) request.getSession().getAttribute("domainId");


      try {
    	  if (userId != null) {
    		  authenticate.globalLogout(userId);
    	  }

         auditHelper.addLog("LOGOUT", domain,	login,
					"WEBCONSOLE", userId, "0", "AUTHENTICATION",
                    userId, null,
                    "SUCCESS", null,  null,null,
                 null,null, null, null,
                 request.getRemoteHost(), login, domain);

        //session.removeAttribute("userId");
        //session.removeAttribute("token");
        session.invalidate(); // removes everything

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

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }
}