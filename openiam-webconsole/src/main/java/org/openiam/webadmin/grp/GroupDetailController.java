package org.openiam.webadmin.grp;

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
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.grp.ws.GroupResponse;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;


public class GroupDetailController extends SimpleFormController {



	protected GroupDataWebService groupManager;
	protected MetadataWebService metadataService;
	protected OrganizationDataService orgDataService;
	protected String groupTypeCategory;
	protected String redirectView;
	protected AuditHelper auditHelper;
	
	
	private static final Log log = LogFactory.getLog(GroupDetailController.class);

	public GroupDetailController() {
		super();
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Group group = null;
		GroupDetailCommand groupCommand = new GroupDetailCommand();
		
		groupCommand.setOrgList( orgDataService.getTopLevelOrganizations());
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
		
		return groupCommand;
	}
	

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		String userId = (String)request.getSession().getAttribute("userId");
		String domainId = (String)request.getSession().getAttribute("domainId");
		String login = (String)request.getSession().getAttribute("login");
		
		GroupDetailCommand groupCommand = (GroupDetailCommand)command;
		
		Group group = groupCommand.getGroup();
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
	
		return new ModelAndView(new RedirectView(redirectView, true));
		
		//ModelAndView mav = new ModelAndView(getSuccessView());
		//return mav;

		
	}

	private void prepareObject(Group group) {
		if (group.getMetadataTypeId().equals("")) {
			group.setMetadataTypeId(null);
		}
		if (group.getCompanyId().equals("")) {
			group.setCompanyId(null);
		}
		if (group.getParentGrpId().equals("")) {
			group.setParentGrpId(null);
		}
	}







	public OrganizationDataService getOrgDataService() {
		return orgDataService;
	}

	public void setOrgDataService(OrganizationDataService orgDataService) {
		this.orgDataService = orgDataService;
	}

	public String getGroupTypeCategory() {
		return groupTypeCategory;
	}

	public void setGroupTypeCategory(String groupTypeCategory) {
		this.groupTypeCategory = groupTypeCategory;
	}

	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public GroupDataWebService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}

	public AuditHelper getAuditHelper() {
		return auditHelper;
	}

	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}

	public MetadataWebService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}


	

}
