package org.openiam.webadmin.admin;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.springframework.web.struts.DispatchActionSupport;


/**
 * @version 	1.0
 * @author
 */
public class MenuNavigationAction extends DispatchActionSupport {
	protected NavigatorDataWebService navDS;
	
	

	public ActionForward execute(
		ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		ActionErrors err = new ActionErrors();


		String langCd = "en";

		HttpSession session = request.getSession();

		// remove the menu that is underneath the calling level.
		String level = request.getParameter("l");
		if (level.equalsIgnoreCase("p")) {
			session.removeAttribute("topLevelMenus");
			session.removeAttribute("sideMenus");
		}
		if (level.equalsIgnoreCase("t")) {
			session.removeAttribute("sideMenus");
		}
		
		
		String userId = (String)session.getAttribute("userId");
		String menuId = request.getParameter("menuid");
		
		request.setAttribute("menuGroup", menuId);
		
		try {
			List<Menu> menus = navDS.menuGroupByUser(menuId, userId, langCd).getMenuList();
			if (menus != null && menus.size() > 0 ) {
				session.setAttribute("sideMenus", menus);
			}else {
				session.removeAttribute("sideMenus");
			}


		} catch (Exception e) {
		//	e.printStackTrace();
	        err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
			//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			return (mapping.findForward("failure"));
		}
		// show the login page first
		return mapping.findForward("success");

	}



	public NavigatorDataWebService getNavDS() {
		return navDS;
	}



	public void setNavDS(NavigatorDataWebService navDS) {
		this.navDS = navDS;
	}










	
	
}
