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
package org.openiam.webadmin.user;

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

import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.service.MetadataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.springframework.web.context.WebApplicationContext;
//import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;


import diamelle.common.status.StatusCodeValue;
//import diamelle.ebc.user.CompanyData;
//import diamelle.ebc.user.CompanySearch;
import diamelle.security.auth.GroupValue;


/**
 * @version 	1.0
 * @author
 */
public class IndexAction extends NavigationAction {
	LoginAccess loginAccess = new LoginAccess();
	SecurityAccess secAccess = new SecurityAccess();

	ServiceAccess serviceAccess = null;
	OrganizationDataService orgDataService;

	MetadataService metadataService;
	
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		ActionErrors err = new ActionErrors();
		WebApplicationContext webCtx =  getWebApplicationContext();
		orgDataService =  (OrganizationDataService)webCtx.getBean("orgManager");
		serviceAccess = new ServiceAccess(webCtx);
		
		Locale locale = getLocale(request);
		String langCd = locale.getLanguage();

		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String login = request.getParameter("lg");
		String menuId = request.getParameter("menuid");
		session.setAttribute("userId", userId);
		session.setAttribute("login", login);
		session.setAttribute("menuId", menuId);
		
		try {

			if (menuId != null) {
				List menus = loginAccess.getPermissions(userId, menuId, langCd);
				session.setAttribute("topLevelMenus", menus);
			}
			
			ServletContext servletContext =  getServlet().getServletConfig().getServletContext();

			loadStaticData(session, servletContext);
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
	
	private void loadStaticData(HttpSession session, ServletContext servletCtx) throws RemoteException {
		session.setAttribute("operationList", getOperationStatusList());
		session.setAttribute("countryList", getCountryList());
		session.setAttribute("userTypes", this.getUserMetadataTypes());
		session.setAttribute("companyList", getCompanyList());
		session.setAttribute("services", getAllDomains());
		
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
	 */
	private List getUserMetadataTypes()  {
    	ArrayList newCodeList = new ArrayList();
    	MetadataType[] typeAry = metadataService.getTypesInCategory("USER_TYPE");
    	if (typeAry != null && typeAry.length > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for ( MetadataType type: typeAry) {
        		LabelValueBean label = new LabelValueBean(type.getDescription(),type.getMetadataTypeId());
        		newCodeList.add(label);
        	}
         }
        return newCodeList;
    }
	
	
	private List getCompanyList() {
	   	ArrayList newCodeList = new ArrayList();
	   	
	   	List<Organization> companyList = orgDataService.getAllOrganizations();   	
        if (companyList != null && companyList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i< companyList.size(); i++) {       		
        		Organization val = (Organization)companyList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getOrganizationName(),val.getOrgId());
        	 	newCodeList.add(label);
        	}
        }
	    return newCodeList;	
	}
	private List getAllDomains() {


		SecurityDomainDataService secDomainDS = 
			(SecurityDomainDataService)getWebApplicationContext().getBean("secDomainService");       		
		SecurityDomain domainAry[] = secDomainDS.getAllSecurityDomains();	

		ArrayList<LabelValueBean> newServiceList = new ArrayList<LabelValueBean>();

      
        if (domainAry != null && domainAry.length > 0) {
        	newServiceList.add(new LabelValueBean("",""));
        	for (int i=0; i < domainAry.length; i++ ) {
          	 	LabelValueBean label = new LabelValueBean(domainAry[i].getName(), domainAry[i].getDomainId());
           	 	newServiceList.add(label);
        	}        		
        }
        return newServiceList;
    }

	public MetadataService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataService metadataService) {
		this.metadataService = metadataService;
	}
}
