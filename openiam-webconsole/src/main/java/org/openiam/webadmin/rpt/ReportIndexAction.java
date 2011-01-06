//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.webadmin.rpt;

import javax.servlet.http.*;


import java.util.*;
import org.apache.struts.action.*;
import org.springframework.web.struts.DispatchActionSupport;


import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;

/** 
 * Shows the list of reports that are available.
 */
public class ReportIndexAction extends DispatchActionSupport {
	 protected NavigatorDataWebService navigationDataService;
	 
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
		
		List<Menu> userRptMenu = navigationDataService.menuGroup("USERREPORT", "en").getMenuList();
		List<Menu> auditRptMenu = navigationDataService.menuGroup("AUDITREPORT", "en").getMenuList();
		List<Menu> passwordRptMenu = navigationDataService.menuGroup("PASSWORDREPORT", "en").getMenuList();
		List<Menu> provRptMenu = navigationDataService.menuGroup("PROVREPORT", "en").getMenuList();
		List<Menu> accessRptMenu = navigationDataService.menuGroup("ACCESSREPORT", "en").getMenuList();
		List<Menu> otherRptMenu = navigationDataService.menuGroup("OTHERREPORT", "en").getMenuList();
		
		request.setAttribute("userRptMenu", userRptMenu);
		request.setAttribute("auditRptMenu", auditRptMenu);
		request.setAttribute("passwordRptMenu", passwordRptMenu);
		request.setAttribute("provRptMenu", provRptMenu);
		request.setAttribute("accessRptMenu", accessRptMenu);
		request.setAttribute("otherRptMenu", otherRptMenu);

		
		request.setAttribute("hidemenu","true");

		return mapping.findForward("reportlist");


	}

	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


}


