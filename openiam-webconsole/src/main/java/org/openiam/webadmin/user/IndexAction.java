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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.springframework.web.struts.DispatchActionSupport;


/**
 * @version 	1.0
 * @author
 */
public class IndexAction extends DispatchActionSupport  {
	
	
    NavigatorDataWebService navigationDataService;
    SecurityDomainDataService secDomainService;
    OrganizationDataService orgDataService;

    MetadataWebService metadataService;
	ReferenceDataService refDataService;
	
	private static final Log log = LogFactory.getLog(IndexAction.class);
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		log.info("Idman IndexAction");
		
		ActionErrors err = new ActionErrors();
		
		Locale locale = getLocale(request);
		//String langCd = locale.getLanguage();

		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String login = request.getParameter("lg");
		String menuId = request.getParameter("menuid");
		session.setAttribute("userId", userId);
		session.setAttribute("login", login);
		session.setAttribute("menuId", menuId);
		
		log.info("Menu id = " + menuId);
		
		try {

			if (menuId != null) {
				List<Menu> menus = navigationDataService.menuGroupByUser(menuId, userId, "en").getMenuList(); 
				session.setAttribute("topLevelMenus", menus);
				
				log.info("Menu List = " + menus);
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
		log.info(" Forwarding to welcome...");
		request.setAttribute("bodyjsp", "/blank.jsp");
		return (mapping.findForward("welcome"));

	}
	
	private void loadStaticData(HttpSession session, ServletContext servletCtx) throws RemoteException {
		session.setAttribute("operationList", getOperationStatusList());
		session.setAttribute("countryList", getCountryList());
		session.setAttribute("userTypes", this.getUserMetadataTypes());
		session.setAttribute("companyList", getCompanyList());
		session.setAttribute("services", getAllDomains());
		session.setAttribute("elementList", getComleteMetadataElementList());
		
	}
	private List getOperationStatusList() throws RemoteException {
		log.info("getOperationStatusList called");
		
    	ArrayList<LabelValueBean> newCodeList = new ArrayList();
    	List<ReferenceData> codeList = refDataService.getRefByGroup("OPERATION","en");

        if (codeList != null && codeList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i<codeList.size(); i++) {       		
        		ReferenceData val = (ReferenceData)codeList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getId().getStatusCd());
        	 	newCodeList.add(label);
        	}
        }
        return newCodeList;
    }
	private List getCountryList() throws RemoteException {
		log.info("getCountryList() called");
		
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
		List<ReferenceData> codeList = refDataService.getRefByGroup("COUNTRY_CD","en");
        if (codeList != null && codeList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i<codeList.size(); i++) {       		
        		ReferenceData val = (ReferenceData)codeList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getId().getStatusCd());
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
		log.info("getUserMetadataTypes called.");
		
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
    	MetadataType[] typeAry = metadataService.getTypesInCategory("USER_TYPE").getMetadataTypeAry();
    	if (typeAry != null && typeAry.length > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for ( MetadataType type: typeAry) {
        		LabelValueBean label = new LabelValueBean(type.getDescription(),type.getMetadataTypeId());
        		newCodeList.add(label);
        	}
         }
        return newCodeList;
    }

	private List getComleteMetadataElementList()  {
		log.info("getUserMetadataTypes called.");
		
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
    	MetadataElement[] elementAry = metadataService.getAllElementsForCategoryType("USER_TYPE").getMetadataElementAry();
    	if (elementAry != null && elementAry.length > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for ( MetadataElement elm: elementAry) {
        		LabelValueBean label = new LabelValueBean(elm.getMetadataTypeId() + "->" 
        				+ elm.getAttributeName(), elm.getAttributeName());
        		newCodeList.add(label);
        	}
         }
        return newCodeList;
    }
	
	private List getCompanyList() {
		log.info("getCompanyList called.");
		ArrayList<LabelValueBean> newCodeList = new ArrayList();
	   	
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

		log.info("getAllDomains called.");

		SecurityDomainDataService secDomainDS = 
			(SecurityDomainDataService)getWebApplicationContext().getBean("secDomainServiceClient");       		
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


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public OrganizationDataService getOrgDataService() {
		return orgDataService;
	}

	public void setOrgDataService(OrganizationDataService orgDataService) {
		this.orgDataService = orgDataService;
	}

	public ReferenceDataService getRefDataService() {
		return refDataService;
	}

	public void setRefDataService(ReferenceDataService refDataService) {
		this.refDataService = refDataService;
	}

	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}

	public MetadataWebService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}
}
