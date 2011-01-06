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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;


public class MenuListController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(MenuListController.class);


	protected NavigatorDataWebService navigationDataService;
	protected String redirectView;


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map model = new HashMap();
		
		request.getSession().removeAttribute("sideMenus");
		
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			model.put("msg", "Your information has been sucessfully saved.");
		}
		
		List<Menu> menuList = navigationDataService.menuGroup("ROOT", "en").getMenuList();

		model.put("menuList", menuList);
		return model;
	}


	public MenuListController() {
		super();
	}

	
	private void loadReferenceDataMAV(ModelAndView model) {
		List<Menu> menuList = navigationDataService.menuGroup(null, "en").getMenuList();
		model.addObject("menuList", menuList);
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		MenuListCommand menuListCommand = (MenuListCommand)command;
		
		String menuGroup = menuListCommand.getMenuGroup();
		if (menuGroup != null && menuGroup.length() == 0 ) {
			menuGroup = null;
		}
		
		List<Menu> menuList = navigationDataService.menuGroup(menuGroup,menuListCommand.getLanguageCd()).getMenuList();
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		int count = 0;
		mav.addObject("menuListCmd", menuListCommand);
		mav.addObject("searchResult", menuList);
		if (menuList != null) {
			count = menuList.size();
		}
		mav.addObject("resultsize", count);
		
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
