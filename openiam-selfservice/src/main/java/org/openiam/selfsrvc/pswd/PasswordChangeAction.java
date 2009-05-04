//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.selfsrvc.pswd;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;


import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.service.dto.*;


import diamelle.common.status.StatusCodeValue;
import diamelle.ebc.user.UserData;
import diamelle.security.auth.*;
import diamelle.security.policy.PolicyAttrValue;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.*;
import javax.servlet.ServletContext;

import org.openiam.selfsrvc.pswd.*;



public class PasswordChangeAction extends DispatchActionSupport {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	  protected LoginAccess loginAccess = new LoginAccess();
	 // protected ServiceAccess serviceAccess = new ServiceAccess();

	  protected SecurityDomainAccess secDomainAccess = null;

	  protected ServiceAccess serviceAccess = null;
	  protected AuthenticatorAccess authAccess = new AuthenticatorAccess();
	  protected SecurityAccess securityAccess = new SecurityAccess();
	 
	  // new - 06-08-2006
	  protected PolicyAccess policyAccess = new PolicyAccess();
	  static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	  
	  // SAS - 8/31/08 - Integration with spring objects
	  protected PasswordConfiguration passwordConfig= null;
	  
 

	  
	  public PasswordConfiguration getPasswordConfig() {
		return passwordConfig;
	}

	public void setPasswordConfig(PasswordConfiguration passwordConfig) {
		this.passwordConfig = passwordConfig;
	}

	public ActionForward view(ActionMapping mapping,
	  				ActionForm form,
					HttpServletRequest request,
					HttpServletResponse response)
	  					throws IOException, ServletException  {

  	
	  	HttpSession session = request.getSession();
		DynaActionForm passwordChangeForm = (DynaActionForm) form;
		
		serviceAccess = new ServiceAccess(getWebApplicationContext());
		secDomainAccess = new SecurityDomainAccess(getWebApplicationContext());
		
		WebApplicationContext webCtx = getWebApplicationContext();

	    IdmAuditLogDataService auditService = 
    		 (IdmAuditLogDataService)webCtx.getBean("auditDataService");
	
		
		
		String userId = (String)session.getAttribute("userId");
		if (userId == null   || userId.length() == 0)  {
			userId = request.getParameter("userId");
			// will be used in the password change action 
			session.setAttribute("userId", userId);
		}
		
		request.setAttribute("displaySyncServices", String.valueOf( passwordConfig.isDisplaySyncServices() ) ) ;
		request.setAttribute("displayDomainList", String.valueOf( passwordConfig.isDisplayDomainList() ) ) ;
		request.setAttribute("secDomain", String.valueOf( passwordConfig.getDefaultSecurityDoamin() ) ) ;
		
		String login = (String)session.getAttribute("login");
		if (login == null   || login.length() == 0) {
			login = request.getParameter("lg");
			session.setAttribute("login", login);
			passwordChangeForm.set("login",login);
		}
		String token = request.getParameter("token");
		session.setAttribute("token", token);
	
		// Return Target Url is the url that we want to return to
		String retTargetUrl = request.getParameter("rettargeturl");
		if (retTargetUrl == null || retTargetUrl.length() == 0) {
			// make sure we are not carrying a value from an earlier session
			session.removeAttribute("retTargetUrl");
		}else {
			// set the target url
			session.setAttribute("retTargetUrl", retTargetUrl);
		}
		String pwdExpFlag = request.getParameter("pwdexp");
		if (pwdExpFlag != null && pwdExpFlag.equals("1")) {
			request.setAttribute("pwdexp", pwdExpFlag);
		}
		// get a list of service that this user has access to 
		
		
		try {
			if (session.getAttribute("domainList") == null) {
				//session.setAttribute("serviceList", getAllServices());
				//session.setAttribute("serviceList", securityAccess.getUserServices(userId));
				//session.setAttribute("operationList", getOperationStatusList());
				session.setAttribute("domainList", secDomainAccess.getAllDomainsWithExclude("IDM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("view");

	  }
	  
		private List getAllServices() {

			
	    	ArrayList<LabelValueBean> newServiceList = new ArrayList<LabelValueBean>();
	    	//List serviceList = servMgr.getAllServices();
	    	List<Service> serviceList = serviceAccess.getAllServices();
	      
	        if (serviceList != null && serviceList.size() > 0) {
	        	newServiceList.add(new LabelValueBean("",""));
	        	for (int i=0; i < serviceList.size(); i++ ) {
	        		Service val = (Service)serviceList.get(i);
	          	 	LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
	           	 	newServiceList.add(label);
	        	}        		
	        }
	        return newServiceList;
	    }
	  
 private List serviceLabel(String userId) throws java.rmi.RemoteException {
	    List newCodeList = new ArrayList();
	    
	    java.util.Map serviceMap = null; //securityAccess.getUserServices(userId);
	    List<Service> serviceList = serviceAccess.getAllServices();
	    if (serviceMap != null) {
	    	newCodeList.add(new LabelValueBean("Please select a value","-1"));
	    	for (int i=0; i< serviceList.size(); i++) {
	    		Service val = (Service)serviceList.get(i);
	    		LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
				newCodeList.add(label);    		
	    	}
	    	Set keySet = serviceMap.keySet();
	    	java.util.Iterator it = keySet.iterator();
	    	while (it.hasNext()) {
	    		String key = (String)it.next();
	    		String value = (String)serviceMap.get(key);
		    	LabelValueBean label = new LabelValueBean(value,key);
	    		newCodeList.add(label);
	    	}
	    }
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
	    String userId = (String)session.getAttribute("userId");
   
	  	DynaActionForm dynForm = (DynaActionForm)form;
	  	String login = (String)dynForm.get("login");
	  	String service = (String)dynForm.get("service");
	  	String domainId = (String)dynForm.get("domainId");
	  	//String oldPassword = (String)dynForm.get("oldPassword");
	  	String newPassword = (String)dynForm.get("newPassword");
	  	String confNewPassword = (String)dynForm.get("confNewPassword");
	  	
	  	
	  	// validate the data
	  	try {
	  		if((login == null) || (login.length() < 1)) 	{
	  			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.idrequired"));
	  			
	  		//	errors.add("login",new ActionError("error.login.idrequired"));
	  			saveErrors(request, errors);
	  			return mapping.findForward("failure");		 
	  		}
	  		if( (service == null) || (service.equals("-1")) ) {
	  			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.service.required"));
	  			
	  			//errors.add("login",new ActionError("error.service.required"));
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

		//if (newPassword.length() < 5) {
		//	errors.add("confPassword",new ActionError("error.login.pwdlen"));
		 //   saveErrors(request, errors);
		 //   return mapping.findForward("failure"); 
		//}

		LoginValue val = authAccess.getLogin(domainId,login);
		if (val == null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.incorrect"));
			//errors.add("login",new ActionError("error.login.incorrect"));
			saveErrors(request, errors);
			return mapping.findForward("failure");		  					
		}
		// temp hack on the policy validation
		List policyAttrList = policyAccess.getPolicyAttributes( res.getString("SYS_DEFAULT_PASSWORD_POLICY"));
		
		// length
		PolicyAttrValue attrVal = getPolicyAttr(policyAttrList,"PWD_LEN_RANGE");
		if (attrVal != null) {
			if (newPassword.length() < Integer.parseInt(attrVal.getValue1())  || 
				newPassword.length() > Integer.parseInt(attrVal.getValue2())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				
				//errors.add("confPassword",new ActionError("error.passwordpolicy"));
				saveErrors(request, errors);
				return mapping.findForward("failure");		  							
			}
		}

		// alpha numeric 
		attrVal = getPolicyAttr(policyAttrList,"HAS_ALPHA_NUM_AT");
		if (attrVal != null) {
			try {
				byte firstChar = (byte)newPassword.charAt(Integer.parseInt(attrVal.getValue1()));
				if ((firstChar >= 48 && firstChar <= 90) || (firstChar >= 97 && firstChar <= 122 )) {
		
				}else {
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
					//errors.add("confPassword",new ActionError("error.passwordpolicy"));
					saveErrors(request, errors);
					return mapping.findForward("failure");		  							
					
				}
			}catch(NumberFormatException ne) {
				
			}
		}
		// UPPERCASE
		attrVal = getPolicyAttr(policyAttrList,"UPPERCASE_CHARS");
		if (attrVal != null) {
			int min = -1;
			int max = -1;
			
			if (attrVal.getValue1() != null && attrVal.getValue1().length() > 0) {
				min = Integer.valueOf( attrVal.getValue1()).intValue();
			}
			if (attrVal.getValue2() != null && attrVal.getValue2().length() > 0 ) {
				max = Integer.valueOf(attrVal.getValue2()).intValue();
			}
			int len = newPassword.length();
			int ctr = 0;
			for (int x=0; x < len; x++ ) {
				byte ch = (byte)newPassword.charAt(x);
				if (ch >=65 && ch <= 90) {
					// its upper case
					ctr++;
				}
			}
			if (min > 0 && ctr < min) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				saveErrors(request, errors);
				return mapping.findForward("failure");		  										
			}
			if (max > 0 && ctr > max) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}

		
		
		
		// LOWERCASE
		attrVal = getPolicyAttr(policyAttrList,"LOWERCASE_CHARS");
		if (attrVal != null) {
			int min = -1;
			int max = -1;
			
			if (attrVal.getValue1() != null && attrVal.getValue1().length() > 0) {
				min = Integer.valueOf( attrVal.getValue1()).intValue();
			}
			if (attrVal.getValue2() != null && attrVal.getValue2().length() > 0 ) {
				max = Integer.valueOf(attrVal.getValue2()).intValue();
			}
			int len = newPassword.length();
			int ctr = 0;
			for (int x=0; x < len; x++ ) {
				byte ch = (byte)newPassword.charAt(x);
				if (ch >=97 && ch <= 122) {
					// its lower case
					ctr++;
				}
			}
			if (min > 0 && ctr < min) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				saveErrors(request, errors);
				return mapping.findForward("failure");		  										
			}
			if (max > 0 && ctr > max) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		
		
		// if a number exists
		attrVal = getPolicyAttr(policyAttrList,"NUMERIC_CHARS");
		boolean match = false;
		try {
			int numericCount = Integer.parseInt(attrVal.getValue1());
			if (numericCount > 0 ) {
				for (int i=1; i < newPassword.length(); i++) {
					byte ch = (byte)newPassword.charAt(i);
					if ( ch >= 48 && ch <= 57) {
						match = true;
						break;
					}	
				}
				if (match == false) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				//	errors.add("confPassword",new ActionError("error.passwordpolicy"));
					saveErrors(request, errors);
					return mapping.findForward("failure");		  							
					
				}
			}
		}catch(NumberFormatException nf) {
			
		}
		
		attrVal = getPolicyAttr(policyAttrList,"NON_ALPHA_CHARS");
		
		log.info("attrval = " + attrVal.getValue1());
		
		if (attrVal.getValue1() != null) {
			// if a non-alpha exists
			match = false;
			int numericCount = Integer.parseInt(attrVal.getValue1());
			if (numericCount > 0 ) {
				for (int i=1; i < newPassword.length(); i++) {
					byte ch = (byte)newPassword.charAt(i);
		
					if (( ch >= 33 && ch <= 47) || (ch >= 58 && ch <= 64) || (ch >= 123 && ch <= 126) || ch == 95) {
						match = true;
						
						log.info("attrval = match found " + ch );
						break;
					}	
				}
			}
			if (match == false) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.passwordpolicy"));
				//errors.add("confPassword",new ActionError("error.passwordpolicy"));
				saveErrors(request, errors);
				return mapping.findForward("failure");		  							
				
			}
		}
		
		// temp hack
		Date curDt = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDt);
		cal.add(Calendar.DATE, 90);
			
		// data is valid - save the password.
		val.setPasswordExp(new Timestamp(cal.getTime().getTime()));
		val.setPassword(newPassword);
		val.setAuthFailureCount(0);
		val.setResetPassword(false);
		val.setLocked(false);
		authAccess.updateLogin(val);

	
		String token = (String)session.getAttribute("token");
		if (token == null) {
			SSOSubject sub = loginAccess.authenticate(val);
			if (sub != null) {

				session.setAttribute("token", sub.getToken());
				session.setAttribute("login", val.getLogin());
				session.setAttribute("userId", sub.getUserId());
			}
		}
		
	// get the user object
		UserAccess userAccess = new UserAccess();
		UserData ud = userAccess.getUser(userId);
		// sync with connected directories
		


		WebApplicationContext webCtx = getWebApplicationContext();
	    IdmAuditLogDataService auditService = 
   		 (IdmAuditLogDataService)webCtx.getBean("auditDataService");

		
   			String logMsg = "User id=" + val.getUserId() + " has changed the password";
            
  			IdmAuditLog log = new IdmAuditLog();
  			log.setActionId("PASSWORD CHANGE");
  			log.setActionStatus("SUCCESS");
  			log.setActionDatetime(new Date(System.currentTimeMillis()));
  			log.setHost(request.getRemoteHost());
  			log.setLogHash("00");
  			log.setObjectName(Class.forName("org.openiam.idm.srvc.user.dto.User").getName());
  			log.setObjectTypeId(val.getUserId());
  			log.setLoginId(val.getLogin());
  			log.setReason("PASSWORD_CHANGE");
  			log.setReasonDetail(logMsg);
  			log.setReqUrl(request.getRequestURI());
  			log.setServiceId(val.getService());
  			log.setUserId((String)session.getAttribute("userId"));
  			auditService.addLog(log);
		
		
		
		String[] serviceAry = request.getParameterValues("syncService");
		if (serviceAry != null && serviceAry.length > 0) {
			List loginList = authAccess.getAllLogins(userId);
			// iterate the login list and if a service matches on of the selected serices, then update the password.
			//int size = loginList.size();
			int size = serviceAry.length;
			
			for (int i=0; i < size; i++) {
				String selectedServ = serviceAry[i];	
				LoginValue loginVal = getLogin(selectedServ, loginList);
								
				if (loginVal != null) {
					curDt = new Date(System.currentTimeMillis());
					cal = Calendar.getInstance();
					cal.setTime(curDt);
					cal.add(Calendar.DATE, 90);

					loginVal.setAuthFailureCount(0);
					loginVal.setResetPassword(false);			
					loginVal.setPassword(newPassword);
					loginVal.setLocked(false);
					loginVal.setPasswordExp(new Timestamp(cal.getTime().getTime()));

					authAccess.updateLogin(loginVal);
				}
			}
		}

		
	
		// if the person used this screent to change the password because it had expired
		// and came in from another application, then we need to create a token to log
		// back in to the 
		
	  	}catch(Exception e) {
	  		e.printStackTrace();
	  		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login.incorrect"));
	 	//	errors.add("login",new ActionError("error.login.incorrect"));
			saveErrors(request, errors);
			return mapping.findForward("failure");		  			

	  	}
	       
	   
	       return (mapping.findForward("success"));
	    }
	
	  
	private PolicyAttrValue getPolicyAttr(List attrList, String key) {
		int size = attrList.size();
		for (int i = 0; i < size; i++) {
			PolicyAttrValue val = (PolicyAttrValue) attrList.get(i);
			if (val.getName().equals(key)) {
				return val;
			}
		}
		
		return null;
	}
	private LoginValue getLogin(String selectedServ, List loginList) {
		for (int i=0; i < loginList.size(); i++) {
			LoginValue val = (LoginValue)loginList.get(i);
			if (val.getService().equals(selectedServ)) {
				return val;
			}
		}
		return null;
	}
	private	boolean serviceSelected(String serviceName, String[] serviceAry) {
			int arySize = serviceAry.length;
			
			for (int i=0; i<=arySize; i++) {
				log.info("Serv = " + serviceAry[i]);
				if (serviceAry[i].equals( serviceName))
					return true;
			}
			return false;
	}
	  
/*	private List getOperationStatusList() throws RemoteException {
		ArrayList newCodeList = new ArrayList();
	    CodeAccess cdAccess = new CodeAccess();
	    List codeList = cdAccess.getCodesByService("100","IDM","OPERATION","en");
	    if (codeList != null && codeList.size() > 0) {
	    	newCodeList.add(new LabelValueBean("",""));
	        	for (int i=0; i<codeList.size(); i++) {       		
	        		StatusCodeValue val = (StatusCodeValue)codeList.get(i);
	        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getStatusCd());
	        	 	newCodeList.add(label);
	        	}
	        }
	        return newCodeList;
	 }
*/


}
