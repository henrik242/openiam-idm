//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.webadmin.rpt;

import javax.servlet.http.*;

import java.rmi.RemoteException;
import java.util.*;
import org.apache.struts.action.*;


import org.openiam.webadmin.busdel.base.NavigationAction;

/** 
 * MyEclipse Struts
 * Creation date: 02-22-2005
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 */
public class ReportIndexAction extends NavigationAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
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

	
		// don't show the main menu when looking at reports.
		HttpSession session = request.getSession();
		session.removeAttribute("topLevelMenus");
		session.setAttribute("MENU_MODE","REPORT_MENU");
		
		try {
		List userRptMenu = navAccess.getMenuList("USERREPORT");
		List auditRptMenu = navAccess.getMenuList("AUDITREPORT");
		List passwordRptMenu = navAccess.getMenuList("PASSWORDREPORT");
		List provRptMenu = navAccess.getMenuList("PROVREPORT");
		List accessRptMenu = navAccess.getMenuList("ACCESSREPORT");
		List otherRptMenu = navAccess.getMenuList("OTHERREPORT");
		
		request.setAttribute("userRptMenu", userRptMenu);
		request.setAttribute("auditRptMenu", auditRptMenu);
		request.setAttribute("passwordRptMenu", passwordRptMenu);
		request.setAttribute("provRptMenu", provRptMenu);
		request.setAttribute("accessRptMenu", accessRptMenu);
		request.setAttribute("otherRptMenu", otherRptMenu);

		
		request.setAttribute("hidemenu","true");
		}catch(RemoteException re) {
			re.printStackTrace();
		}
		return mapping.findForward("reportlist");


	}

}


