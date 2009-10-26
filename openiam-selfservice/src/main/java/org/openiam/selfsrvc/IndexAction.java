/*
* ------------------------------------------------------------------------------
* Title: diamelle.admin.security.IndexAction
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
package org.openiam.selfsrvc;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.webadmin.busdel.base.*;

/**
 * @version 	1.0
 * @author
 */
public class IndexAction extends NavigationAction {

	AppConfiguration appConfiguration;
	SecurityDomainDataService secDomainService;
	
	NavigatorDataService navigationDataService;
	private String publicLeftMenuGroup;
	private String publicRightMenuGroup1;
	private String publicRightMenuGroup2;
	
	

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		System.out.println("IndexAction:execute()..");
		
		ActionErrors err = new ActionErrors();

		String expire = request.getParameter("expire");
		if (expire != null && expire.equals("1")) {
			request.setAttribute("expmsg", "Session has expired. Please login again");
		}
		

		HttpSession session = request.getSession();
		ServletContext servletContext =  getServlet().getServletConfig().getServletContext();
				
		//appConfiguration = (AppConfiguration)getWebApplicationContext().getBean("appConfiguration");

	
		// put the configuration object in session
		session.setAttribute("logoUrl", appConfiguration.getLogoUrl());
		session.setAttribute("title", appConfiguration.getTitle());
		session.setAttribute("defaultLang", appConfiguration.getDefaultLang());
		session.setAttribute("welcomePageUrl", appConfiguration.getWelcomePageUrl());
		

		String userId = request.getParameter("userId");
		String login = request.getParameter("lg");
		session.setAttribute("userId", userId);
		session.setAttribute("login", login);
		

		try {
			String appId = (String) session.getAttribute("appId");
			if (appId != null) {
				List<Menu> menus = navigationDataService.menuGroupByUser(appId, userId, appConfiguration.getDefaultLang());
				//List menus = loginAccess.getPermissions(userId, appId, appConfiguration.getDefaultLang());
				session.setAttribute("topLevelMenus", menus);
			}
			loadStaticData(session, servletContext);

		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			return (mapping.findForward("failure"));
		}
		
		// show the login page first
		return mapping.findForward("login");


	}
	
	private void loadStaticData(HttpSession session, ServletContext servletContext) throws RemoteException {
		//session.setAttribute("operationList", getOperationStatusList());
		session.setAttribute("domainList", this.secDomainService.getAllDomainsWithExclude("IDM"));
		// load public menus
		session.setAttribute("publicLeftMenuGroup",
				navigationDataService.menuGroup(publicLeftMenuGroup, appConfiguration.getDefaultLang()));
		session.setAttribute("publicRightMenuGroup1",
				navigationDataService.menuGroup(publicRightMenuGroup1, appConfiguration.getDefaultLang()));
		session.setAttribute("publicRightMenuGroup2",
				navigationDataService.menuGroup(publicRightMenuGroup2, appConfiguration.getDefaultLang()));
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

	public String getPublicLeftMenuGroup() {
		return publicLeftMenuGroup;
	}

	public void setPublicLeftMenuGroup(String publicLeftMenuGroup) {
		this.publicLeftMenuGroup = publicLeftMenuGroup;
	}


	public String getPublicRightMenuGroup1() {
		return publicRightMenuGroup1;
	}

	public void setPublicRightMenuGroup1(String publicRightMenuGroup1) {
		this.publicRightMenuGroup1 = publicRightMenuGroup1;
	}

	public String getPublicRightMenuGroup2() {
		return publicRightMenuGroup2;
	}

	public void setPublicRightMenuGroup2(String publicRightMenuGroup2) {
		this.publicRightMenuGroup2 = publicRightMenuGroup2;
	}

	public NavigatorDataService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(NavigatorDataService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}

	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}

	public void setAppConfiguration(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}



}
