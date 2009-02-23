
package org.openiam.webadmin.maint;


import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;


import org.openiam.webadmin.busdel.base.*;
//import diamelle.common.service.*;
import diamelle.util.LoggerUtil;

import java.io.*;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.struts.*;
import org.springframework.web.context.*;

import org.openiam.idm.srvc.service.service.*;
import org.openiam.idm.srvc.service.dto.*;


/**
 * <code>ServiceListAction</code> provides a list of existing services.
 * @author Suneet Shah
 *
 */
public class ServiceAction extends DispatchActionSupport {
	
	// ---------------- Instance Variables

	   final static String className = ServiceAction.class.getName();
	   final static Logger logger = Logger.getLogger(className);
	
	   // ---------------- Methods
	   

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			
		LoggerUtil.info(logger, "entering init");
	
		ActionErrors err = new ActionErrors();
		WebApplicationContext ctx =  this.getWebApplicationContext(); 
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");

		List services = servMgr.getAllServices();
		request.setAttribute("services",services);
		
		return mapping.findForward("services");
	}
	


	/**
	 * Prepares the form to create a capture information for a new service definition.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward addService(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		LoggerUtil.info(logger, "entering add");
		
		DynaActionForm serviceForm = (DynaActionForm) form;
		serviceForm.set("mode", "add");
		init(mapping, form, request, response);
		return mapping.findForward("service");
	}
	
	
	/**
	 * Retrieves the service based on the serviceId in the request object. The service id is then transferred to 
	 * the form object.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward editService(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
	
		LoggerUtil.info(logger, "entering editService");
	
		ActionErrors errors = new ActionErrors();
		DynaActionForm serviceForm = (DynaActionForm) form;
		
		WebApplicationContext ctx =  this.getWebApplicationContext(); 
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");

			String serviceId = request.getParameter("serviceId");
			serviceForm.set("mode", "edit");
			if (serviceId != null) {
				Service service = servMgr.getService(serviceId);
				setServiceToForm(form, service);
				init(mapping, form, request, response);
			}
		return mapping.findForward("service");
	}
	/* Check if the form is valid before trying to save it. 	*/
	private boolean isFormValid(ActionMessages errors, DynaActionForm serviceForm) {
		boolean retval = true;
		
		
		if (serviceForm.get("serviceName") == null || 
				((String)serviceForm.get("serviceName")).length()==0) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.serviceName"));
				retval = false;
			}
		if (serviceForm.get("locationIpAddress") == null || 
				((String)serviceForm.get("locationIpAddress")).length()==0) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.sericeLocation"));
				retval = false;
		}	
		// check to see if we have a valid ip address.
		// are there 3 .
		// is each segment between 0 and 255
/*		String ip = (String)serviceForm.get("locationIpAddress");
	
	  	StringTokenizer tokenizer = new StringTokenizer(ip,".");
		if (tokenizer != null) {
			int count = tokenizer.countTokens();
			if (count != 4) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.sericeIP"));
				retval = false;
			}
			while (tokenizer.hasMoreElements()) {
				String tkn = tokenizer.nextToken();
				try {
					int iTkn = Integer.parseInt(tkn);
					if (iTkn < 0 || iTkn > 255) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.sericeIPNumber"));
						retval = false;
					}
				}catch(NumberFormatException pe) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.sericeIP"));
					retval = false;
				}
			}
		}
*/
		return retval;
	}
	
	public ActionForward testConnection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Hashtable envDC = new Hashtable();

		String serviceId = request.getParameter("serviceId");
		WebApplicationContext ctx =  this.getWebApplicationContext(); 
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");

		Service service = servMgr.getService(serviceId);
		
		try {
			
			//ServiceValue servVal = serviceAccess.getService(serviceId);
			//String urlAD = "ldap://win2003srv.diamelle.local:389";
			String urlAD =  service.getLocationIpAddress(); // servVal.getParam1();// "ldap://win2003srv.diamelle.local:389";
			
			//System.out.println("AD url = " + urlAD);
			
			String baseContainer = service.getParam2();
			//String adminName = "CN=" + servVal.getParam3() + "," + baseContainer;
			//String adminName = service.getParam3() + "," + baseContainer;
			String adminName = service.getParam3();
			System.out.println("AdminName = " + adminName);
			
			
			envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
			envDC.put(Context.SECURITY_AUTHENTICATION, service.getParam5() ); // simple
			envDC.put(Context.SECURITY_PRINCIPAL,adminName);  //"administrator@diamelle.local"
			envDC.put(Context.SECURITY_CREDENTIALS,service.getParam4());
			envDC.put(Context.PROVIDER_URL,urlAD);
			envDC.put(Context.REFERRAL,"follow");
			LdapContext ctxDC = new InitialLdapContext(envDC,null);
			System.out.println("Directory context = " + ctxDC);
			//System.out.println("Connected to = " + urlAD);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("test");
	}
	
	/**
	 * Saves (adds and updates) a service definition. A service may be a connected system.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward saveService(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Service service = new Service();

		ActionMessages errors = new ActionMessages();
		
		DynaActionForm serviceForm = (DynaActionForm) form;
		WebApplicationContext ctx =  this.getWebApplicationContext(); 
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");
		
		String mode = (String) serviceForm.get("mode");
		
		// check to see if the service id is a duplicate.
		if (mode.equalsIgnoreCase("add")) {
			// if the user added a service id, then we need to check that its not a duplicate
			String serviceId = (String)serviceForm.get("serviceId");
			if (serviceId != null) {
				service = servMgr.getService(serviceId);
				if (service != null) {
					 errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.serviceIdExists"));
				     this.saveErrors(request,errors);
					 return mapping.findForward("service");
				}
			}
		}
		
		// validate
		if (!isFormValid(errors, serviceForm)) {
			this.saveErrors(request,errors);
			return mapping.findForward("service");
		}
		
		// move the data from the form to the service object
		
		service = this.getService(serviceForm);
		//
	//	init(mapping, form, request, response);
		
		System.out.println("Service =" + service);
		
		// if we use clear communication, then we don't need to generate a cert.
		if ( service != null) {
			if (service.getParam5().equalsIgnoreCase("CLEAR")) {
				service.setLicenseKey("-NO CERT-");
			}
		}
		
		//try {
			
			if (mode.equalsIgnoreCase("add")) {
				servMgr.addService(service);
				serviceForm.set("mode","view");
			} else if (mode.equalsIgnoreCase("edit")) {
				servMgr.updateService(getService(form));
				serviceForm.set("mode", "view");
			}	
		//} catch (Exception e) {
		//	e.printStackTrace();
		//	org.openiam.webadmin.busdel.security
		//}
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("index");
		}

		return mapping.findForward("services");
	}
	
	
	public ActionForward removeService(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

	ActionErrors errors = new ActionErrors();
	String[] serviceId = request.getParameterValues("serviceId");

	WebApplicationContext ctx =  this.getWebApplicationContext(); 
	ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");
	
	try {
		if (request.getParameterValues("serviceId") != null) {
			for (int i = 0; i < serviceId.length; i++) {
				servMgr.removeService(serviceId[i]);
			}
		}		
		init(mapping, form, request, response);
	} catch (Exception e) {
		LoggerUtil.error(logger,e);
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.databaseupdate"));
	}
	return mapping.findForward("services");
	}
	
	
	
	private Service getService(ActionForm form) {
		
		DynaActionForm serviceForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		
		Service service = new Service();
		try {
			service.setServiceId((String) serviceForm.get("serviceId"));
			service.setServiceName((String) serviceForm.get("serviceName"));
			service.setStatus((String) serviceForm.get("status"));
			service.setLocationIpAddress((String) serviceForm.get("locationIpAddress"));
			service.setCompanyOwnerId((String) serviceForm.get("companyOwnerId"));		
			service.setLicenseKey((String) serviceForm.get("licenseKey"));
			service.setServiceType((String) serviceForm.get("serviceType"));
			service.setParentServiceId((String) serviceForm.get("parentServiceId"));

			service.setParam1((String) serviceForm.get("directoryDomain"));
			service.setParam2((String) serviceForm.get("baseContainer"));
			service.setParam3((String) serviceForm.get("dirAuthUserId"));
			service.setParam4((String) serviceForm.get("dirAuthUserPassword"));
			service.setParam5((String) serviceForm.get("commType"));

			
			if (serviceForm.get("startDate") != null && 
					((String)serviceForm.get("startDate")).length() > 1 ) {
				service.setStartDate(df.parse((String) serviceForm.get("startDate")));
			}
			if (serviceForm.get("endDate") != null && 
					((String)serviceForm.get("endDate")).length() > 1) {
				service.setEndDate( df.parse((String)serviceForm.get("endDate")));
			}
			// if we use clear communication, then we don't need to generate a cert.
			if (service.getParam5().equalsIgnoreCase("CLEAR")) {
				service.setLicenseKey("-NO CERT-");
			}			
			if (service.getParam5().equalsIgnoreCase("SECURE") && 
					service.getLicenseKey().equalsIgnoreCase("-NO CERT-")) {
				service.setLicenseKey("");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}	
		
		return service;
	}

	/**
	 * Transfers the information in the service object to the struts form object.
	 * @param form
	 * @param serviceVal
	 * @throws Exception
	 */
	private void setServiceToForm(ActionForm form, Service serviceVal)  {
		
		DynaActionForm serviceForm = (DynaActionForm) form;	
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		serviceForm.set("serviceId", serviceVal.getServiceId());
		serviceForm.set("serviceName", serviceVal.getServiceName());
		serviceForm.set("status", serviceVal.getStatus());
		serviceForm.set("locationIpAddress", serviceVal.getLocationIpAddress());
		serviceForm.set("companyOwnerId", serviceVal.getCompanyOwnerId());
		if (serviceVal.getStartDate() != null) {
			serviceForm.set("startDate", df.format(serviceVal.getStartDate()));
		}
		if (serviceVal.getEndDate() != null) {
			serviceForm.set("endDate", df.format(serviceVal.getEndDate()));
		}
		serviceForm.set("licenseKey", serviceVal.getLicenseKey());
		serviceForm.set("serviceType", serviceVal.getServiceType());
		serviceForm.set("parentServiceId", serviceVal.getParentServiceId());   
		
		serviceForm.set("directoryDomain", serviceVal.getParam1());   
		serviceForm.set("baseContainer", serviceVal.getParam2());   
		serviceForm.set("dirAuthUserId", serviceVal.getParam3());   
		serviceForm.set("dirAuthUserPassword", serviceVal.getParam4());  
		serviceForm.set("commType", serviceVal.getParam5());
		
		
		
	}
	
	private Service getServiceValue(String serviceId){
		try {
		//	return serviceAccess.getService(serviceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}