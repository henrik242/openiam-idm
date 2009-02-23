package org.openiam.webadmin.access;

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
  */
public class HelpDeskMenuAction extends NavigationAction {

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
			List maintMenu = navAccess.getMenuList("HELPDESK");
			session.setAttribute("topLevelMenus", maintMenu);
			session.setAttribute("MENU_MODE","HELP_DESK");
		
		}catch(RemoteException re) {
			re.printStackTrace();
		}		
		return mapping.findForward("auditLog");


	}

}
