
package org.openiam.selfserv.da.user;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import org.apache.struts.validator.*;
import org.springframework.web.context.WebApplicationContext;

import diamelle.security.auth.*;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;

import diamelle.common.service.ServiceMgr;

import java.util.*;



public class ResetAction extends NavigationDispatchAction {
	

	 private AuthenticatorAccess auth = new AuthenticatorAccess();
	// private ServiceAccess serviceAccess = new ServiceAccess();
	
	 
	public ActionForward services(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws IOException, ServletException {
		
		WebApplicationContext ctx =  this.getWebApplicationContext(); 
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");
		
		setService(request,servMgr.getServicesMap());

		return mapping.findForward("login");
     }

	 
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("ResetAction - login...*********************************");
  		
  		try {
  			System.out.println("ResetAction - login...*********************************in try............");
  			String login = request.getParameter("login");
  			String service = request.getParameter("service");
  			
  			LoginValue loginValue = auth.getLogin("Ameet.Shah","DIAMELLE");
  			System.out.println("ResetAction - login...*******************login val is **************"+loginValue);
  			
  		} catch(Exception e){
  			e.printStackTrace();
  		}  		
  		return mapping.findForward("login1");
	}

	private void setService(HttpServletRequest request,Map serviceMap) {
		
		System.out.println("ResetAction - setService****************************..");	
		
		try {
			
			System.out.println("ResetAction - setService****************************..in try");
	        List services =new ArrayList();
	        services.add(new LabelValueBean("Select a Service", "0"));
	        
	        
	         if (serviceMap != null) {
	         	
	         	System.out.println("ResetAction - setService****************************..in if"); 	
	          	Iterator serviceItr = serviceMap.keySet().iterator();
	            Iterator Iter = serviceMap.values().iterator();
	            
	            while( serviceItr.hasNext()) {
	            	
	            	System.out.println("ResetAction - setService****************************..in while");
	            	String servId = (String) serviceItr.next();
	                String serviceName = (String) Iter.next();
	   	            services.add(new LabelValueBean(serviceName,servId));
	            }
	         }
	         System.out.println("ResetAction - setService****************************..after if");
	         request.setAttribute("services", services);
	        } catch (Exception e) {
	          e.printStackTrace();
	     }
	 }
	
	
	private LoginValue getLogin(ActionForm form) {
		
  		DynaValidatorForm LoginForm = (DynaValidatorForm)form;
  	   
  		LoginValue loginValue = new LoginValue();
  		
  		loginValue.setLogin((String)LoginForm.get("login"));
  		loginValue.setService((String)LoginForm.get("service"));
  		 
  		return loginValue;
  	}
  	
	
}	
	


