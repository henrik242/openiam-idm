package org.openiam.webadmin.menu;

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


import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;



public class MenuDetailController extends SimpleFormController {



	protected NavigatorDataWebService navigationDataService;
	protected String redirectView;
	
	private static final Log log = LogFactory.getLog(MenuDetailController.class);

	public MenuDetailController() {
		super();
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Menu menu = null;
		MenuDetailCommand groupCommand = new MenuDetailCommand();
		
	/*	groupCommand.setOrgList( orgDataService.getTopLevelOrganizations());
	  	groupCommand.setTypeList( metadataService.getTypesInCategory(groupTypeCategory).getMetadataTypeAry() );
			
		String groupId = request.getParameter("groupId");
		String parentGroupId = request.getParameter("parentGroupId");
		
		
		if ( groupId != null) {
			group = groupManager.getGroup(groupId).getGroup();
		}else {
			group = new Group();
			group.setParentGrpId(null);
		}
		if (parentGroupId != null && parentGroupId.length() > 0) {
			group.setParentGrpId(parentGroupId);
		}
		
		groupCommand.setGroup(group);
		// get the list of child groups if any
		List<Group> childGroupList = groupManager.getChildGroups(group.getGrpId(),false).getGroupList();
		groupCommand.setChildGroup(childGroupList);
	*/	
		return groupCommand;
	}
	

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		String userId = (String)request.getSession().getAttribute("userId");
		String domainId = (String)request.getSession().getAttribute("domainId");
		String login = (String)request.getSession().getAttribute("login");
		
		MenuDetailCommand groupCommand = (MenuDetailCommand)command;
		
/*		Group group = groupCommand.getGroup();
		prepareObject(group);
		
		String btn = request.getParameter("btn");

		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			groupManager.removeGroup(group.getGrpId());

			auditHelper.addLog("DELETE", domainId,	login,
					"WEBCONSOLE", userId, "0", "GROUP", group.getGrpId(), 
					null,   "SUCCESS", null,  null, 
					null, null, null);
			
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Group has been successfully deleted.");
	        return mav;

		}

		if (group.getGrpId() != null && group.getGrpId().length() > 0) {
			group.setLastUpdate(new Date(System.currentTimeMillis()));
			group.setLastUpdatedBy(userId);
			groupManager.updateGroup(group);
			
			auditHelper.addLog("UPDATE", domainId,	login,
					"WEBCONSOLE", userId, "0", "GROUP", group.getGrpId(), 
					null,   "SUCCESS", null,  null, 
					null, null, null);
		
			
		}else {
			group.setGrpId(null);
			group.setCreatedBy(userId);
			group.setCreateDate(new Date(System.currentTimeMillis()));
			GroupResponse resp = groupManager.addGroup(group);
			String grpId = null;
			if (resp.getStatus() == ResponseStatus.SUCCESS) {
				grpId = resp.getGroup().getGrpId();
			}

			auditHelper.addLog("CREATE", domainId,	login,
					"WEBCONSOLE", userId, "0", "GROUP", grpId, 
					null,   "SUCCESS", null,  null, 
					null, null, null);
			
		}
	*/
	//	return new ModelAndView(new RedirectView(redirectView, true));
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;

		
	}


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}

	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}






	

}
