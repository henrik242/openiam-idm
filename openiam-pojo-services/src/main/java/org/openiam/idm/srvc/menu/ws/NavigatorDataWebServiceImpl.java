/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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


package org.openiam.idm.srvc.menu.ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.menu.ws.NavigatorDataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/menu/service",
		portName = "NavigationWebServicePort",
		serviceName = "NavigationWebService")
public class NavigatorDataWebServiceImpl implements NavigatorDataWebService {

	NavigatorDataService navigatorDataService;
	private static final Log log = LogFactory.getLog(NavigatorDataWebServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#addMenu(org.openiam.idm.srvc.menu.dto.Menu)
	 */
	public MenuResponse addMenu(Menu data) {
		MenuResponse resp = new MenuResponse(ResponseStatus.SUCCESS);
		navigatorDataService.addMenu(data); 
		if (data.getId().getMenuId() != null) {
			resp.setMenu(data);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#getAllMenuOptionIDs(java.lang.String, java.lang.String)
	 */
	public Response getAllMenuOptionIDs(String parentMenuGroupId, String languageCd) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		String str = navigatorDataService.getAllMenuOptionIDs(parentMenuGroupId, languageCd);
		if (str != null && str.length() > 0) {
			resp.setResponseValue(str);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#getMenu(java.lang.String, java.lang.String)
	 */
	public MenuResponse getMenu(String menuId, String languageCd) {
		MenuResponse resp = new MenuResponse(ResponseStatus.SUCCESS);
		Menu menu = navigatorDataService.getMenu(menuId, languageCd); 
		if (menu != null) {
			resp.setMenu(menu);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#menuGroup(java.lang.String, java.lang.String)
	 */
	public MenuListResponse menuGroup(String menuGroupId, String langCd) {
		MenuListResponse resp = new MenuListResponse(ResponseStatus.SUCCESS);
		List<Menu> menuList = navigatorDataService.menuGroup(menuGroupId, langCd); 
		if (menuList != null) {
			resp.setMenuList(menuList);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#menuGroupByUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public MenuListResponse menuGroupByUser(String menuGroupId, String userId,	String languageCd) {
		MenuListResponse resp = new MenuListResponse(ResponseStatus.SUCCESS);
		List<Menu> menuList = navigatorDataService.menuGroupByUser(menuGroupId, userId, languageCd); 
		if (menuList != null) {
			resp.setMenuList(menuList);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#menuGroupSelectedByUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public MenuListResponse menuGroupSelectedByUser(String menuGroupId, String userId,String languageCd) {
		MenuListResponse resp = new MenuListResponse(ResponseStatus.SUCCESS);
		List<Menu> menuList = navigatorDataService.menuGroupSelectedByUser(menuGroupId, userId, languageCd); 
		log.info("menuGroupSelectedByUser: menuList=" + menuList);
		if (menuList != null) {
			resp.setMenuList(menuList);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#removeMenu(java.lang.String, boolean)
	 */
	public Response removeMenu(String menuId, boolean deleteChildren) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		int retval = navigatorDataService.removeMenu(menuId, deleteChildren);
		if (retval < 1) {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.menu.ws.NavigatorDataWebService#updateMenu(org.openiam.idm.srvc.menu.dto.Menu)
	 */
	public Response updateMenu(Menu data) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		navigatorDataService.updateMenu(data);
		return resp;
	}

	public NavigatorDataService getNavigatorDataService() {
		return navigatorDataService;
	}

	public void setNavigatorDataService(NavigatorDataService navigatorDataService) {
		this.navigatorDataService = navigatorDataService;
	}

}
