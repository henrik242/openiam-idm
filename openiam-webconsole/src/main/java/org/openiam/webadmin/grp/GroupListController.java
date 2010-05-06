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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupSearch;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;;


public class GroupListController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(GroupListController.class);

	protected GroupDataWebService groupManager;


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map model = new HashMap();
		
		request.getSession().removeAttribute("sideMenus");
		
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			model.put("msg", "Your information has been sucessfully saved.");
		}
		
		List<Group> groupList = groupManager.getAllGroups().getGroupList();
		model.put("groupList", groupList);
		return model;
	}


	public GroupListController() {
		super();
	}

	
	private void loadReferenceDataMAV(ModelAndView model) {
		List<Group> groupList = groupManager.getAllGroups().getGroupList();
		model.addObject("groupList", groupList);
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		GroupListCommand groupListCommand = (GroupListCommand)command;
		
		// get the list of Groups
		String searchValue = groupListCommand.getNameFilter();
		GroupSearch search = new GroupSearch();
		search.setGrpName(searchValue + "%");
		List<Group> groupList = groupManager.search(search).getGroupList();
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		int count = 0;
		mav.addObject("groupListCmd", groupListCommand);
		mav.addObject("searchResult", groupList);
		if (groupList != null) {
			count = groupList.size();
		}
		mav.addObject("resultsize", count);
		
		return mav;

		
	}


	public GroupDataWebService getGroupManager() {
		return groupManager;
	}


	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}



	

}
