package org.openiam.webadmin.admin;


import java.util.List;

import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.springframework.web.struts.DispatchActionSupport;

/**
 * @author suneet
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminIndexAction extends DispatchActionSupport {

	// --------------------------------------------------------- Instance Variables
	NavigatorDataWebService navDS;
	// --------------------------------------------------------- Methods

	/** 
	 * Shows the maintenance menu.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		HttpSession session = request.getSession();
		List<Menu> maintMenu = navDS.menuGroup("ADMIN","en").getMenuList();
		session.setAttribute("topLevelMenus", maintMenu);
	
		return mapping.findForward("success");


	}

	public NavigatorDataWebService getNavDS() {
		return navDS;
	}

	public void setNavDS(NavigatorDataWebService navDS) {
		this.navDS = navDS;
	}


}
