/*
* ------------------------------------------------------------------------------
* Title: org.openiam.webadmin.access.IndexAction
* Author: OpenIAM,LLC 
* Overview: Sets up static data required to run the Admin App
* ------------------------------------------------------------------------------
* Copyright (c) 2000-2008 OpenIAM, LLC. All Rights Reserved.
*
* This SOURCE CODE FILE, which has been provided by OpenIAM as part
* of a OpenIAM Software product for use ONLY by licensed users of the product,
* includes CONFIDENTIAL and PROPRIETARY information of OpenIAM.
*
* This code or parts or derivatives of it cannot be used for any commercial
* products without written permission from OpenIAM.
*
* USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
*
* IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD OpenIAM, ITS
* RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
* CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
* DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
* ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
* DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
* ------------------------------------------------------------------------------
* CHANGE CONTROL:
* Last modified by : 
* on : 
* ------------------------------------------------------------------------------
*/
package org.openiam.selfserv.da.user;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.ServletContext;
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
import org.apache.struts.util.LabelValueBean;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;

import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.service.dto.*;
import org.openiam.idm.srvc.user.service.UserMgr;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.web.context.WebApplicationContext;


import diamelle.common.status.StatusCodeValue;
import diamelle.ebc.user.CompanyData;
import diamelle.ebc.user.CompanySearch;
import diamelle.security.auth.GroupValue;


/**
 * @version 	1.0
 * @author
 */
public class IndexAction extends NavigationAction {
	LoginAccess loginAccess = new LoginAccess();
	SecurityAccess secAccess = new SecurityAccess();
	MetadataAccess metaAccess = new MetadataAccess();
	CompanyAccess compAccess =  new CompanyAccess();
	ServiceAccess serviceAccess = null;


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		ActionErrors err = new ActionErrors();
		serviceAccess = new ServiceAccess(getWebApplicationContext());
		Locale locale = getLocale(request);
		String langCd = locale.getLanguage();

		HttpSession session = request.getSession();
		//String userId = (String) session.getAttribute("userId");
		String userId = request.getParameter("userId");
		String login = request.getParameter("lg");
		String menuId = request.getParameter("menuid");
		session.setAttribute("userId", userId);
		session.setAttribute("login", login);
		session.setAttribute("menuId", menuId);
	
		WebApplicationContext webContext =  getWebApplicationContext();
		UserDataService userMgr = (UserDataService)webContext.getBean("userManager");
		org.openiam.idm.srvc.user.dto.User usr = userMgr.getUserWithDependent(userId,false);
		String orgId = usr.getCompanyId();
		System.out.println("Organization id = " + orgId);
		session.setAttribute("orgid", orgId);
		
		try {

			if (menuId != null) {
				List menus = loginAccess.getPermissions(userId, menuId, langCd);
				session.setAttribute("subMenus", menus);
			}
			
			ServletContext servletContext =  getServlet().getServletConfig().getServletContext();

			loadStaticData(session, servletContext, orgId);
			session.removeAttribute("sideMenus");
			session.removeAttribute("categories");
			session.removeAttribute("menus");

		} catch (Exception e) {
			e.printStackTrace();
	        err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			return (mapping.findForward("failure"));

		}
		request.setAttribute("bodyjsp", "/blank.jsp");
		return (mapping.findForward("welcome"));

	}
	
	private void loadStaticData(HttpSession session, ServletContext servletCtx, String orgId) throws RemoteException {
		session.setAttribute("operationList", getOperationStatusList());
		//session.setAttribute("groupList", getGroupList());
		session.setAttribute("countryList", getCountryList());
		session.setAttribute("userTypes", this.getUserMetadataTypes());
		session.setAttribute("companyList", getCompanyList(orgId));
		session.setAttribute("services", getAllServices());
		
	}
	private List getOperationStatusList() throws RemoteException {
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
	private List getCountryList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
        CodeAccess cdAccess = new CodeAccess();
        List codeList = cdAccess.getCodesByService("100","IDM","COUNTRY_CD","en");
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
	

	/**
	 * Get a list of metadata types for users.
	 * @return
	 * @throws RemoteException
	 */
	private List getUserMetadataTypes() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
    	Map typeMap =  metaAccess.getMetadataTypes("USER_TYPE");
    	if (typeMap != null && typeMap.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	Collection col = typeMap.keySet();
        	Iterator it = col.iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();
        		String value = (String)typeMap.get(key);
        		LabelValueBean label = new LabelValueBean(value,key);
        		newCodeList.add(label);
        	}
         }
        return newCodeList;
    }
	private List getCompanyList(String orgId) {
       	WebApplicationContext webContext =  getWebApplicationContext();
    	OrganizationDataService orgMgr = (OrganizationDataService)webContext.getBean("orgManager");

		
	   	ArrayList newCodeList = new ArrayList();
		//try {
			//CompanySearch search = new CompanySearch();
			//List companyList = compAccess.searchCompany(search);
	        //if (companyList != null && companyList.size() > 0) {
        	//newCodeList.add(new LabelValueBean("",""));
        	//for (int i=0; i< companyList.size(); i++) {       	
		Organization org = orgMgr.getOrganization(orgId);
        		//CompanyData val = (CompanyData)companyList.get(i);
       	LabelValueBean label = new LabelValueBean(org.getOrganizationName(),orgId);
       	newCodeList.add(label);
        	//}
      

//		}catch(RemoteException re) {
//			re.printStackTrace();
//		}
	    return newCodeList;	
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
}
