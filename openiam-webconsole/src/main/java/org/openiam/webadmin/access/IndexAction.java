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
package org.openiam.webadmin.access;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import org.openiam.webadmin.busdel.base.*;

/**
 * @version 	1.0
 * @author
 */
public class IndexAction extends NavigationAction {
	LoginAccess loginAccess = new LoginAccess();

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {


		ActionErrors err = new ActionErrors();

		String expire = request.getParameter("expire");
		if (expire != null && expire.equals("1")) {
			request.setAttribute("expmsg", "Session has expired. Please login again");
		}
		
		Locale locale = getLocale(request);
		String langCd = locale.getLanguage();

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			userId = request.getParameter("userId");
		}
		String login = request.getParameter("lg");
		String menuId = request.getParameter("menuid");		
		
		session.setAttribute("login", login);
		session.setAttribute("menuId", menuId);
		
		try {
			//String appId = (String) session.getAttribute("appId");
			
		
			//if (appId != null) {
			//	List menus = loginAccess.getPermissions(userId, appId, langCd);
			//	session.setAttribute("topLevelMenus", menus);
			//}
			if (menuId != null) {
				List menus = loginAccess.getPermissions(userId, menuId, langCd);
				session.setAttribute("topLevelMenus", menus);
			}

			session.removeAttribute("sideMenus");
			session.removeAttribute("menus");
			
		} catch (Exception e) {
			e.printStackTrace();
	        err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));

		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			return (mapping.findForward("failure"));

		}
		// used to render the menu correctly.
		session.setAttribute("MENU_MODE","SECURITY_MAIN");
		request.setAttribute("bodyjsp", "/blank.jsp");

		return (mapping.findForward("welcome"));

	}
}
