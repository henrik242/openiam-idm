package org.openiam.webadmin.admin;


import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.openiam.webadmin.busdel.base.NavigationAction;

/**
 * @author suneet
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminIndexAction extends NavigationAction {

	// --------------------------------------------------------- Instance Variables

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
		try {
			List maintMenu = navAccess.getMenuList("ADMIN");
			session.setAttribute("topLevelMenus", maintMenu);
		}catch(RemoteException re) {
			re.printStackTrace();
		}		
		return mapping.findForward("success");


	}

}
