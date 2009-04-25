/*
* ------------------------------------------------------------------------------
* Title: org.openiam.webadmin.access.IndexAction
* Author: Diamelle Technologies 
* Overview: Sets up static data required to run the Admin App
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
* USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
*
* IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
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
package org.openiam.webadmin.feder;

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

import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.service.dto.*;
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
		
		try {
			//String appId = (String) session.getAttribute("appId");
			//System.out.print("appId.." + appId);
			if (menuId != null) {
				List menus = loginAccess.getPermissions(userId, menuId, langCd);
				System.out.println("***menus = " + menus);
				session.setAttribute("topLevelMenus", menus);
			}
			
			ServletContext servletContext =  getServlet().getServletConfig().getServletContext();

			
			loadStaticData(session, servletContext);
			session.removeAttribute("sideMenus");

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
	
	private void loadStaticData(HttpSession session, ServletContext servletCtx) throws RemoteException {
		session.setAttribute("operationList", getOperationStatusList());
		session.setAttribute("groupList", getGroupList());
		session.setAttribute("countryList", getCountryList());
		session.setAttribute("userTypes", this.getUserMetadataTypes());
		session.setAttribute("companyList", getCompanyList());
		//session.setAttribute("services", getAllServices());
		
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
	
	private List getGroupList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
    	List grpList = secAccess.getAllGroups();
         if (grpList != null && grpList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i = 0; i < grpList.size(); i++) {       		
        		GroupValue val = (GroupValue)grpList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
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
	private List getCompanyList() {
        WebApplicationContext webContext =  getWebApplicationContext();
        OrganizationDataService orgMgr = (OrganizationDataService)webContext.getBean("orgManager");
        List<Organization> orgList = orgMgr.getAllOrganizations();
        
		
	   	ArrayList newCodeList = new ArrayList();

			//CompanySearch search = new CompanySearch();
			//List companyList = compAccess.searchCompany(search);
	        if (orgList != null && orgList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i< orgList.size(); i++) {       		
        		Organization val = (Organization)orgList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getOrganizationName(),val.getOrgId());
        	 	newCodeList.add(label);
        	}
        }


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
