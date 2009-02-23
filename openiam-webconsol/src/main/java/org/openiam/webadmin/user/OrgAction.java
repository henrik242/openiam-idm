/**
 * ------------------------------------------------------------------------------
 * Title: UserAction
 * Author: LD 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * list of users.
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

package org.openiam.webadmin.user;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.identity.UserAccess;
import org.openiam.webadmin.busdel.security.GroupDataServiceAccess;

import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.org.dto.Organization;





import diamelle.ebc.user.Address;
import diamelle.ebc.user.Email;
import diamelle.ebc.user.Phone;
import diamelle.ebc.user.UserData;
import diamelle.util.Log;
import java.io.*;


import java.util.*;
import java.rmi.RemoteException;


import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;
import org.springframework.web.context.WebApplicationContext;



public class OrgAction extends NavigationDispatchAction  {

	MetadataAccess metaAccess = null;

	public OrgAction() {
		try {

		metaAccess = new MetadataAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ActionForward addOrg ( ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse res ) throws IOException, ServletException {

		HttpSession session = request.getSession();
		this.loadStaticData(session);

        return mapping.findForward("success");
    }
	

	public ActionForward view ( ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse res ) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
        try {
            WebApplicationContext webContext =  getWebApplicationContext();
            OrganizationDataService orgMgr = (OrganizationDataService)webContext.getBean("orgManager");
            
        	Organization org = new Organization();
            String orgId = null;
        	
        	
           UserAccess userAccess = new UserAccess();
           
           orgId = request.getParameter("orgId");
           if (orgId == null) {
           	orgId = (String)session.getAttribute("orgId");
           }

           org = orgMgr.getOrganization(orgId);
           if (org != null) {
        	   this.populateFormBean(org, (DynaValidatorForm)form);                       
           }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
        
    }

	
	
	
   /** 
     * Creates a new User or Saves a Users details if its an existing user.
     */
     public ActionForward saveOrg ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
        
        WebApplicationContext webContext =  getWebApplicationContext();
        OrganizationDataService orgMgr = (OrganizationDataService)webContext.getBean("orgManager");
        
    	Organization org = new Organization();
        String orgId = null;
        String logMsg = null;

        try {
           ActionErrors errors = null;
           
           HttpSession session = request.getSession();          
           DynaValidatorForm orgForm = (DynaValidatorForm)form;

           // is actually the personId
           orgId = (String) orgForm.get("orgId");
           if (orgId != null)
            request.setAttribute("orgId", orgId);


 		  org = getOrgDetails(org, orgForm);
          if (orgId != null && orgId.length() > 0){
     		   //userData.setId(personId);
     		   orgMgr.updateOrganization(org);
              
           } else {
           	  
           	  // new user
        	   org.setOrgId(String.valueOf(System.currentTimeMillis()));
        	   orgMgr.addOrganization(org);
    
           }

	              
        } catch(Exception e) {
        	e.printStackTrace();
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));

        }
        return (mapping.findForward("confirm"));
    }
     
  



    /**
     * Deletes a User and all its dependencies
     */
    public ActionForward deleteUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {

 /*       try {
           HttpSession session = request.getSession();
           String personId = request.getParameter("personId");
           UserAccess userAccess = new UserAccess();
           
           UserData ud = userAccess.getUser(personId);
           if (ud != null) {
           	ud.setStatusId("DELETED");
           	userAccess.saveUser(ud);
            AuditLogAccess.logEvent("User id=" + personId + " has been deleted.",
              		request.getRemoteHost(),(String)session.getAttribute("userId"),  
               		(String)session.getAttribute("login"),"IDM");
           }
 
        } catch(Exception e) {
            e.printStackTrace();
        }
    	//return (mapping.findForward("success"));
*/        return (mapping.findForward("search"));
    }






    /**
     * Retrieves the data from Form and sets in the UserData object
     */
    private Organization getOrgDetails(Organization org, DynaValidatorForm form) {
         CalendarUtil calUtil = new CalendarUtil();
         org.setOrgId((String) form.get("orgId"));
         org.setOrganizationName((String) form.get("orgName"));
         org.setStatus((String) form.get("status"));
         org.setAlias((String) form.get("alias"));
         return org;
    }

 
    /**
     * Retrieves information from UserData and sets it in Form
     */
    private void populateFormBean(Organization org, DynaValidatorForm uform) {
        CalendarUtil calUtil = new CalendarUtil();

      uform.set("orgId", org.getOrgId());

      uform.set("orgName",org.getOrganizationName());
      uform.set("status", org.getStatus());
      uform.set("alias", org.getAlias());

       
    }
  
  
    private List getTypeList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
    	metaAccess = new MetadataAccess();
    	Map typeMap = metaAccess.getMetadataTypes("ORG_TYPE");
      
        if (typeMap != null && typeMap.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	Set set = typeMap.keySet();
        	Iterator it = set.iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();
        		String value = (String)typeMap.get(key);
        		LabelValueBean label = new LabelValueBean(value,key);
        	 	newCodeList.add(label);
        	}

        }
        return newCodeList;
   	
    }
	
	
	private void loadStaticData(HttpSession session) throws RemoteException {
        if (session.getAttribute("typeList") == null) {
           	// we dont have the data for the drop downs.
           	session.setAttribute("services", getTypeList());
           	
        } 		
	}

}

