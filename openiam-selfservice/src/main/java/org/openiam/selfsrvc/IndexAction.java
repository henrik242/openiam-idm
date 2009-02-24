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
import org.apache.struts.util.*;
import org.openiam.webadmin.busdel.base.*;
import diamelle.common.status.*;

/**
 * @version 	1.0
 * @author
 */
public class IndexAction extends NavigationAction {
	LoginAccess loginAccess = new LoginAccess();
	SecurityDomainAccess secDomainAccess = null;
	AppConfiguration appConfiguration;


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		ActionErrors err = new ActionErrors();

		Locale locale = getLocale(request);
		String langCd = locale.getLanguage();

		HttpSession session = request.getSession();
		ServletContext servletContext =  getServlet().getServletConfig().getServletContext();
			
		secDomainAccess = new SecurityDomainAccess(getWebApplicationContext());		
		appConfiguration = (AppConfiguration)getWebApplicationContext().getBean("appConfiguration");
		
	
		// put the configuration object in session
		session.setAttribute("logoUrl", appConfiguration.getLogoUrl());
		session.setAttribute("title", appConfiguration.getTitle());
		
		
		//String userId = (String) session.getAttribute("userId");
		String userId = request.getParameter("userId");
		String login = request.getParameter("lg");
		session.setAttribute("userId", userId);
		session.setAttribute("login", login);
		

		try {
			String appId = (String) session.getAttribute("appId");
			if (appId != null) {
				List menus = loginAccess.getPermissions(userId, appId, langCd);
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
		//return (mapping.findForward("welcome"));

	}
	
	private void loadStaticData(HttpSession session, ServletContext servletContext) throws RemoteException {
		session.setAttribute("operationList", getOperationStatusList());
		session.setAttribute("domainList", secDomainAccess.getAllDomainsWithExclude("IDM"));
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

}
