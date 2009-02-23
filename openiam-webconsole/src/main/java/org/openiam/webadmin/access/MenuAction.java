/**
 * ------------------------------------------------------------------------------
 * Title: MenuAction Author: APS 04-09-2004 Overview:Handles all functions like
 * adding , updating, deleting and viewing Menus.
 * ------------------------------------------------------------------------------
 * Copyright (c) 2000-2004 Diamelle Inc. All Rights Reserved.
 * 
 * This SOURCE CODE FILE, which has been provided by Diamelle Technologies as
 * part of a Diamelle Software product for use ONLY by licensed users of the
 * product, includes CONFIDENTIAL and PROPRIETARY information of Diamelle
 * Technologies.
 * 
 * This code or parts or derivatives of it cannot be used for any commercial
 * products without written permission from Diamelle Technologies.
 * 
 * USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE
 * STATEMENT FURNISHED WITH THE PRODUCT.
 * 
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS RELATED
 * COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY CLAIMS OR
 * LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR DISTRIBUTION OF YOUR
 * PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES ARISING OUT OF OR RESULTING
 * FROM THE USE, MODIFICATION, OR DISTRIBUTION OF PROGRAMS OR FILES CREATED
 * FROM, BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
 * ------------------------------------------------------------------------------
 * CHANGE CONTROL:
 * 
 * ------------------------------------------------------------------------------
 */

package org.openiam.webadmin.access;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;

import org.openiam.webadmin.busdel.base.*;
import diamelle.ebc.navigator.*;
import org.openiam.webadmin.busdel.security.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.DynaValidatorForm;

//This class allows us to
// 1. View List of Menus
// 2. View Menu Detials
// 3. Add new Menu
// 4. Update details of existing Menu
// 5. Deletes a Menu

public class MenuAction extends NavigationDispatchAction {

	//creating an instance of the access class
	private NavigationAccess navAccess = new NavigationAccess();

	// private PermissionAccess permissionAccess = new PermissionAccess();

	//Extracts nav, categoryId, menuId and nextAction parameters from a request
	// object and sets
	//nextAction, categoryId,categoryValue, categories, navBar, menuId, menus,
	// menuBar in session
	//Also extracts List of Menus.
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		// Extracts nav, categoryId, menuId and nextAction parameters from a
		// request object and sets
		// nextAction, categoryId,categoryValue, categories, navBar, menuId,
		// menus, menuBar in session
		// categoryId left unchanged if null or showlist=1
		// nav=resetMenu, resetCat removes those attributes only, reset removes
		// both
		super.setNavigation(mapping, form, request, response);

		try {

			String langCd = (String)session.getAttribute("languageCd");
			if ((langCd == null)||(langCd.length() == 0)){
				langCd = "en";
				session.setAttribute("languageCd", langCd);
			}
			
			String menuGroup = request.getParameter("parent");
			
			if ((menuGroup == null)||(menuGroup.length()== 0)) {
				//lookup for Root Menu
				Context ctx = new InitialContext();
				menuGroup = (String) ctx.lookup("java:comp/env/rootAppMenu");
				if (menuGroup != null) {
					//appends menuRoot to navigationMenuBar
					super.setNavBar("navigationMenuBar", session, request,
							"Root Menus");
				}
			}
			Collection menuList = navAccess.getMenuList(menuGroup, langCd);

			request.setAttribute("menuList", menuList);
			request.setAttribute("menuGroup", menuGroup);

		} catch (Exception e) {
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("menulist");
	}

	//Sets mode to add, for adding new Menu
	public ActionForward addMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		DynaValidatorForm menuForm = (DynaValidatorForm) form;

		//retriving language code from locale and setting it into form
		Locale locale = getLocale(request);
		String languageCd = locale.getLanguage();
		if (languageCd != null)
			menuForm.set("languageCd", languageCd);

		//setting Name of Menu in request for which new menu has to be created
		request.setAttribute("menuGroup", request.getParameter("menuGroup"));

		//set mode to add
		menuForm.set("mode", "add");
		menuForm.set("active", "on");
		return mapping.findForward("menu");
	}

	//Sets mode to Edit, for updating the Menu Details
	public ActionForward editMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();

		try {
			String menu = request.getParameter("menu");
			DynaValidatorForm menuForm = (DynaValidatorForm) form;

			//sets mode to edit
			menuForm.set("mode", "edit");

			if (menu != null) {

				//sets values for fields in menu.jsp for editing
				MenuData menuData = navAccess.getMenu(menu);
				setMenuData(form, menuData);

				//intializing the menus.jsp
				init(mapping, form, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
				
		return mapping.findForward("menu");
	}

	//Saves/Update Menu Details
	public ActionForward saveMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();

		DynaValidatorForm menuForm = (DynaValidatorForm) form;

		String mode = (String) menuForm.get("mode");
		String parent = request.getParameter("parent");

		try {
			//reads the data from the form
			MenuData menuData = getMenuData(form);

			if (mode.equalsIgnoreCase("add")) {

				//if the user had added a EntitlementId in the Form
				if (menuData.getMenuId() != null
						&& menuData.getMenuId().length() > 0) {

					// checking if such a menuId is there in the database
					MenuData md = navAccess.getMenu(menuData.getMenuId());
					if (md.getMenuId() == null) {
						// if the menu does not exist add the menu
						navAccess.addMenu(menuData);
						menuForm.set("mode", "view");
					} else {
						// if the menu exists throw an error
						menuForm.set("mode", "add");
				        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.exception.menuexists"));
					}
				} else {
					//adding the new menu
					navAccess.addMenu(menuData);
					menuForm.set("mode", "view");
				}
			} else if (mode.equalsIgnoreCase("edit")) {
				//updating the group
				//permissionAccess.updateMenu(menuData);
				navAccess.updateMenu(menuData);
			}

			//sets mode to view
			menuForm.set("mode", "view");


			//checks if its parent menu or submenu and accordingly intitalizes
			// menus.jsp
			if (parent.length() > 0) {
				request.setAttribute("menuList", navAccess.getMenuList(parent));
			    request.setAttribute("menuGroup", (String) menuForm.get("menuGroup"));
			} else {
				init(mapping, form, request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		// hack so that the jsp popup knows that it has to close
		request.setAttribute("saved","1");
		
		return mapping.findForward("menu");
	}

	//Deletes selected Menus
	public ActionForward removeMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		String menu = request.getParameter("menu");

		try {
			String[] arrMenu = request.getParameterValues("menu");
			if (arrMenu != null) {
				// deleting selected menus
				for (int i = 0; i < arrMenu.length; i++)
					//permissionAccess.deleteMenu(arrMenu[i]);
					navAccess.deleteMenu(arrMenu[i]);
			}

			//intializes menus.jsp
			init(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		return mapping.findForward("menulist");
	}

	//Displays List of subMenus for particular menu
	public ActionForward subMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		Collection menuList = null;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		String menu = request.getParameter("menu");
		DynaValidatorForm menuForm = (DynaValidatorForm) form;

		try {
			if (menu != null) {

				MenuData menuData = (MenuData) navAccess.getMenu(menu);

				//appends Menu Name to navigationMenuBar
				if (menuData != null)
					super.setNavBar("navigationMenuBar", session, request,
							menuData.getMenuName());

				menuList = navAccess.getMenuList(menu);
				menuForm.set("parent", menu);
			}
			request.setAttribute("menuGroup", menu);
			request.setAttribute("menuList", menuList);
			request.setAttribute("menu", menu);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("menulist");
	}	

	
	//Fetches input data from the form and sets into value class
	private MenuData getMenuData(ActionForm form) {
		DynaValidatorForm menuForm = (DynaValidatorForm) form;

		MenuData menuData = new MenuData();

		menuData.setMenuId((String) menuForm.get("menu"));
		menuData.setLanguageCd((String) menuForm.get("languageCd"));
		menuData.setMenuGroupId((String) menuForm.get("menuGroup"));
		menuData.setMenuName((String) menuForm.get("menuName"));
		menuData.setMenuDesc((String) menuForm.get("menuDesc"));
		menuData.setMainUrl((String) menuForm.get("mainUrl"));

		//set active if check box is checked
		String active = (String) menuForm.get("active");
		if (active.equals("on"))
			menuData.setActive(new Boolean("true"));

		return menuData;
	}

	//Takes values from value class object and sets it into form
	private void setMenuData(ActionForm form, MenuData menuData)
			throws Exception {
		DynaValidatorForm menuForm = (DynaValidatorForm) form;

		menuForm.set("menu", menuData.getMenuId());
		menuForm.set("languageCd", menuData.getLanguageCd());
		menuForm.set("menuGroup", menuData.getMenuGroupId());
		menuForm.set("menuName", menuData.getMenuName());
		menuForm.set("menuDesc", menuData.getMenuDesc());
		menuForm.set("mainUrl", menuData.getMainUrl());

		//sets checkbox to on if active is true
		if (menuData.isActive() != null) {
			if (menuData.isActive().booleanValue())
				menuForm.set("active", "on");
		}
	}

}