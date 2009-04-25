package org.openiam.webadmin.conn.mngsys;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.user.service.UserDataService;


/**
 * Controller to manage the attribute mappings that are necessary for a managed system.
 *  
 * @author suneet
 *
 */
public class ManagedSysApproverController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ManagedSysApproverController.class);



	private ManagedSystemDataService managedSysService; 
	private UserDataService userMgr;

	


	public ManagedSysApproverController() {
		super();
	}



	
	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {

		String connectorId = request.getParameter("connectorId");
		String menuGroup = request.getParameter("menuGroup");
		
		// used by the UI for to show the side menu
		request.setAttribute("menuGroup", menuGroup);
		request.setAttribute("connectorId", connectorId);

		
		
		SysApproverCommand approverCommand  = new SysApproverCommand();

		
		return approverCommand;


}


	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		SysApproverCommand approverCommand = (SysApproverCommand)command;
		
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("sysApproverCmd", approverCommand);
	
		/*mav.addObject("managedSysAry", managedSysAry);
		if (managedSysAry != null) {
			mav.addObject("searchResults", managedSysAry.length);
		}else {
			mav.addObject("searchResults", 0);
		}
		*/
		
		return mav;

		
	}



	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	
	public UserDataService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}


	

}
