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
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.webadmin.busdel.base.*;



/**
 * @version 	1.0
 * @author
 */
public class MenuNavigationAction extends NavigationAction {
	//LoginAccess loginAccess = new LoginAccess();
	//ServiceAccess serviceAccess = new ServiceAccess();
	
	NavigatorDataService navigationDataService;

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

		// clear up any menus that are visible but don't belong on the screen.
		String level = request.getParameter("l");
		if (level.equalsIgnoreCase("p")) {
			session.removeAttribute("topLevelMenus");
			session.removeAttribute("subMenus");
			session.removeAttribute("sideMenus");
		}
		if (level.equalsIgnoreCase("t")) {
			session.removeAttribute("sideMenus");
		}

		
		String userId = (String)session.getAttribute("userId");
		String menuId = request.getParameter("menuid");
		try {
			List<Menu> menus = navigationDataService.menuGroupByUser(menuId, userId, langCd);
			//List menus = loginAccess.getPermissions(userId, menuId,langCd);
			if (menus != null && menus.size() > 0 ) {
				session.setAttribute("sideMenus", menus);
			}else {
				session.removeAttribute("sideMenus");
			}

		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));

		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			return (mapping.findForward("failure"));
		}
		// show the login page first
		return mapping.findForward("success");

	}

	public NavigatorDataService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(NavigatorDataService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}
	
	
}
