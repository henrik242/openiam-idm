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




import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.menu.dto.Menu;

/**
 * Command object for the ManagedSystemList 
 * @author suneet
 *
 */
public class MenuDetailCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 513253291470702152L;
	
	private List<Menu> childMenuList = null ;
	private Menu parentMenu;
	private Menu menu;
	public List<Menu> getChildMenuList() {
		return childMenuList;
	}
	public void setChildMenuList(List<Menu> childMenuList) {
		this.childMenuList = childMenuList;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	

	
 
    
    

	

}
