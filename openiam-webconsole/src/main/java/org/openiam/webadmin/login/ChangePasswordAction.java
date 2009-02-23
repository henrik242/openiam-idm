
package org.openiam.webadmin.login;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.AuthenticatorAccess;
import diamelle.security.auth.LoginValue;




public class ChangePasswordAction extends DispatchAction {

  protected LoginAccess loginAccess = new LoginAccess();
 // protected ServiceAccess serviceAccess = new ServiceAccess();
	protected AuthenticatorAccess authAccess = new AuthenticatorAccess();
  
  public ActionForward view(ActionMapping mapping,
  				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response)
  					throws IOException, ServletException  {
	
  	HttpSession session = request.getSession();
	DynaActionForm passwordChangeForm = (DynaActionForm) form;
	//serviceAccess = new ServiceAccess();
	
	try {
		if (session.getAttribute("serviceList") == null) {
			session.setAttribute("serviceList", serviceLabel());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return mapping.findForward("view");

  }
  
  private List serviceLabel() throws java.rmi.RemoteException {
    List newCodeList = new ArrayList();
    //List serviceList = serviceAccess.getServiceList();
   // if (serviceList != null) {
   // 	newCodeList.add(new LabelValueBean("Please select a value","-1"));
   // 	for (int i=0; i< serviceList.size(); i++) {
    	//	ServiceValue val = (ServiceValue)serviceList.get(i);
    	//	LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
		// 	newCodeList.add(label);    		
   // 	}
  //  }
    return newCodeList;

  }
  
  public ActionForward save(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {
 
    ActionErrors errors = new ActionErrors();
    Locale locale = getLocale(request);
    HttpSession session = request.getSession();

    
  	DynaActionForm dynForm = (DynaActionForm)form;
  	String login = (String)dynForm.get("login");
  	String service = (String)dynForm.get("service");
  	String oldPassword = (String)dynForm.get("oldPassword");
  	String newPassword = (String)dynForm.get("newPassword");
  	String confNewPassword = (String)dynForm.get("confNewPassword");
  	
  	
  	// validate the data
  	try {
  		if((login == null) || (login.length() < 1)) 	{
  	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.idrequired"));

  			//errors.add("login",new ActionError("error.login.idrequired"));
  			saveErrors(request, errors);
  			return mapping.findForward("failure");		 
  		}
  		if( (service == null) || (service.equals("-1")) ) {
  	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.service.required"));

  			//errors.add("login",new ActionError("error.service.required"));
  			saveErrors(request, errors);
  			return mapping.findForward("failure");		 
  		}  		  		

  	if((oldPassword == null) || (oldPassword.length() < 1)) 	{
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.password.required"));

  		//errors.add("password",new ActionError("error.password.required"));
  			saveErrors(request, errors);
  			return mapping.findForward("failure");		 
  	}
  		
	if((newPassword == null) || (newPassword.length() < 1)) 	{
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.password.required"));

	//	errors.add("password",new ActionError("error.password.required"));
		saveErrors(request, errors);
		return mapping.findForward("failure");		 
	}
  		
	if((confNewPassword == null) || (confNewPassword.length() < 1))	{
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.confPassword.required"));

		//errors.add("confPassword",new ActionError("error.confPassword.required"));
		saveErrors(request, errors);
		return mapping.findForward("failure");
	 }	
				
	
	if (!newPassword.equals(confNewPassword)) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.password.mismatch"));

		//errors.add("confPassword",new ActionError("error.password.mismatch"));
	    saveErrors(request, errors);
	    return mapping.findForward("failure"); 
	}

	if (newPassword.length() < 5) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.pwdlen"));

		//errors.add("confPassword",new ActionError("error.login.pwdlen"));
	    saveErrors(request, errors);
	    return mapping.findForward("failure"); 
	}
	
	LoginValue val = authAccess.getLogin(service,login);
	if (val == null) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.incorrect"));

		//errors.add("login",new ActionError("error.login.incorrect"));
		saveErrors(request, errors);
		return mapping.findForward("failure");		  					
	}
	if (!val.getPassword().equals(oldPassword)) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.oldpw"));

		//errors.add("login",new ActionError("error.login.oldpw"));
		saveErrors(request, errors);
		return mapping.findForward("failure");		  			
	}
	
	// data is valid - save the password.
	
	val.setPassword(newPassword);
	val.setAuthFailureCount(0);
	val.setResetPassword(false);
	authAccess.updateLogin(val);
	
  	}catch(Exception e) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.incorrect"));

 		//errors.add("login",new ActionError("error.login.incorrect"));
		saveErrors(request, errors);
		return mapping.findForward("failure");		  			

  	}
       
   
       return (mapping.findForward("success"));
    }
}

	


