
package org.openiam.webadmin.maint;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.identity.*;
import diamelle.common.status.StatusCodeValue;
//import diamelle.common.service.*;

import diamelle.ebc.user.CompanyData;
import diamelle.ebc.user.CompanySearch;
import diamelle.util.LoggerUtil;

import java.rmi.RemoteException;
import java.util.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;

import org.springframework.web.struts.*;
import org.springframework.web.context.*;

import org.openiam.idm.srvc.service.service.*;
import org.openiam.idm.srvc.service.dto.*;

/**
 * <code>ServiceListAction</code> provides a list of existing services.
 * @author Suneet Shah
 *
 */
public class ServiceListAction extends ActionSupport {
	// ---------------- Instance Variables
	
	private CompanyAccess compAccess = new CompanyAccess();

	   final static String className = ServiceListAction.class.getName();
	   final static Logger logger = Logger.getLogger(className);
	
	// ---------------- Methods
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		LoggerUtil.info(logger, "begin execute");

	  			
		/* add code to get a list of services here  */
  		ActionErrors errors = new ActionErrors();
  		
		WebApplicationContext ctx =  this.getWebApplicationContext(); 
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");
		
		List services = servMgr.getAllServices();
		request.setAttribute("services",services);
		
  		try {
  		    loadStaticData(request.getSession());  		    
  		} catch(Exception e){
			LoggerUtil.error(logger,e);
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.databaseselect"));
  		}  		
  		return mapping.findForward("services");
	}	
	
	private void loadStaticData(HttpSession session) throws RemoteException {
      
		// load 
         ArrayList newCodeList = new ArrayList();
         CodeAccess cdAccess = new CodeAccess();
         List codeList = cdAccess.getCodesByService("100","IDM","SERVICE_TYPE","en");
        if (codeList != null && codeList.size() > 0) {
        	newCodeList.add(new LabelValueBean("Please select a value","-1"));
        	for (int i=0; i<codeList.size(); i++) {       		
        		StatusCodeValue val = (StatusCodeValue)codeList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getStatusCd());
        	 	newCodeList.add(label);
        	}
        }
        session.setAttribute("serviceType", newCodeList);

        // get the companyList
        newCodeList = new ArrayList();
		CompanySearch search = new CompanySearch();
		List companyList = compAccess.searchCompany(search);
        if (companyList != null && companyList.size() > 0) {
    	newCodeList.add(new LabelValueBean("Please select a value","-1"));
    	for (int i=0; i< companyList.size(); i++) {       		
    		CompanyData val = (CompanyData)companyList.get(i);
    	 	LabelValueBean label = new LabelValueBean(val.getCompanyName(),val.getCompanyId());
    	 	newCodeList.add(label);
    	}
    	session.setAttribute("companyList", newCodeList);

        // get the communication type list
        List communicationType = new ArrayList();
        communicationType.add(new LabelValueBean("Please select a value","-1"));
        communicationType.add(new LabelValueBean("CLEAR","simple"));
        communicationType.add(new LabelValueBean("SECURE","SECURE"));
        
    	session.setAttribute("communicationOptions", communicationType);
   	
    	
        }
	}


}
