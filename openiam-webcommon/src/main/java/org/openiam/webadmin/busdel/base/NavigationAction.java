/**
 * ------------------------------------------------------------------------------ 
 * Title: NavigationAction
 * Author: APS
 * Overview: base class for struts Action classes
 * ------------------------------------------------------------------------------
 * Copyright (c) 2000-2004 Diamelle Inc. All Rights Reserved.
 * 
 * This SOURCE CODE FILE, which has been provided by Diamelle Technologies as part
 * of a Diamelle Software product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of Diamelle Technologies. 
 *
 * This code or parts or derivatives of it cannot be used for any commercial
 * products without written permission from Diamelle Technologies.
 *
 * USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS 
 * OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
 * RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
 * CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
 * DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
 * ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
 * DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR
 * DERIVED FROM THIS SOURCE CODE FILE.
 * ------------------------------------------------------------------------------
 * CHANGE CONTROL:
 * aaa - APS 041504 revised and added lang support for category value  
 * ------------------------------------------------------------------------------           
 */
package org.openiam.webadmin.busdel.base;


import diamelle.common.cat.*;
import diamelle.ebc.navigator.*;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

/**
 * <p><code>NavigationAction</code><font face="arial"> is used to set
 * categories and menus in the session object
 * </font ></p>
 */

public class NavigationAction extends DiamelleBaseAction {
/**
  * This is a base class which can either be used as it is, or is a super
  * class to other Action classes which need navigation functionality.
  *
  * Extracts categoryId, menuId and nextAction parameters from a request object
  * first from getParameters, and if null, from the request attribute. Then
  * nextAction and categoryId are stored in session, menuId is stored in request.
  * navBar and menuBar are generated and stored in session
  * categories and menus lists are generated and stored in session
  * categories and navBar are dropped from session if categoryId is null
  * categoryId is kept unchanged in session if new categoryId is null
  * If showlist parameter in category is 1 then categories are left unchanged in
  * the session, so that the same categories can continue to be displayed
  * If navigation = reset, all menu and category attributes are removed from session
  * If resetMenu or resetCat only menu or category attributes are removed.
  *
  * Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded, or <code>null</code> if the response has
  * already been completed.
  *
  * @param mapping The ActionMapping used to select this instance
  * @param form The ActionForm bean for this request
  * @param request The HTTP request we are processing
  * @param response The HTTP response we are creating
  *
  * @return Return an <code>ActionForward</code> instance describing where and how
  * control should be forwarded.
  * @exception IOException if an input/output error occurs
  * @exception ServletException if a servlet exception occurs
  */

  protected NavigationAccess navAccess = new NavigationAccess();

  public ActionForward perform(ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws IOException, ServletException {

    return execute(mapping, form, request, response);
  }


  public ActionForward execute(ActionMapping mapping,
                  ActionForm form,
                  HttpServletRequest request,
                  HttpServletResponse response)
              throws IOException, ServletException {

      HttpSession session = request.getSession();

      // getParameters
      String navigation = request.getParameter("nav");
      String menuId = request.getParameter("menuId");
      String categoryId = request.getParameter("categoryId");
      String nextAction = request.getParameter("nextAction");

      // if variables are not in get parameters, check request attributes
      if (navigation == null)
        navigation = (String) request.getAttribute("nav");
      if (menuId == null)
        menuId = (String) request.getAttribute("menuId");
      if (categoryId == null)
        categoryId = (String) request.getAttribute("categoryId");
      if (nextAction == null)
        nextAction = (String) request.getAttribute("nextAction");

      if (navigation != null) {

        if (navigation.equalsIgnoreCase("reset")) {
          removeMenus(session);
          removeCategories(session);
        }
        if (navigation.equalsIgnoreCase("resetMenu"))
          removeMenus(session);

        if (navigation.equalsIgnoreCase("resetCat"))
          removeCategories(session);
      }

      // if nextAction is defined, save it in session
      if (nextAction != null)
          session.setAttribute("nextAction", nextAction);

      Locale locale = getLocale(request);
      String langCd = locale.getLanguage();


      try {

        //Set new menu. If null do not process, continue to show last menu
        if (menuId != null) {
          if (!menuId.equalsIgnoreCase("null")) {
            request.setAttribute("menuId",menuId); // for backwards compatiblity only
            session.setAttribute("menuId", menuId);
            setMenus(menuId, langCd, request, session, "menus", "menuBar", "menu");
          }  
        }

        if (categoryId != null) {
          if (!categoryId.equalsIgnoreCase("null")) {
            session.setAttribute("categoryId", categoryId);
            setCategories(categoryId, langCd, request, session, "categories", "navBar", "categoryValue");
          }
        } 

        
      } catch (Exception e){
        e.printStackTrace();
	      
        ActionErrors errs = new ActionErrors();
        errs.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
        saveErrors(request, errs);
      }
      return (mapping.findForward("success"));
  }
   
  public void setCategories(String categoryId, String langCd, HttpServletRequest request, 
              HttpSession session, String categoriesName, String navBarName, String categoryValueName) throws Exception {
              
      CategoryValue cv = null;
      Collection optionList = null;
     
    try {  
      if (langCd != null) {
        optionList = navAccess.getCategoryList(categoryId, langCd);    
        cv = navAccess.getCategory(categoryId, langCd);
      } 
    
      if ((optionList == null)||(optionList.isEmpty())) {
        optionList = navAccess.getCategoryList(categoryId);    
      }
      
	  if ((cv == null)||(cv.getCategoryId() == null)) {
		cv = navAccess.getCategory(categoryId);
	  }

	  } catch (Exception e){
		  System.out.println("***** DIAMELLE ERROR MSG ******");
		  System.out.println("Unable to getCategory or getCategoryList for " + categoryId);
		  System.out.println("Are you missing data??? Check CATEGORY for " + categoryId);
		  System.out.println("***********");
		  ActionErrors errs = new ActionErrors();
	      errs.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		  //errs.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
		  saveErrors(request, errs);    	
	  }
			  
      if (cv != null) {
        session.setAttribute(categoryValueName, cv);
        setNavBar(navBarName, session, request, cv.getCategoryName());
      }  

      // if showList = 1, do not drill down
      String showList = request.getParameter("showList");
      if (showList != null) {
        if (showList.equals("1")) {
          return;
        }   
      }

      // do not replace categories if there are no more submenus
      if (optionList != null) {
        if (!optionList.isEmpty()) {
          session.setAttribute(categoriesName, optionList);
        }
      }
  
  }



  public void setMenus(String menuId, String langCd, HttpServletRequest request, 
        HttpSession session, String menusName, String menuBarName, String menuDataName)
        throws Exception {
      MenuData menu = null;
      Collection optionList = null;

      if (langCd != null) {
        optionList = navAccess.getMenuList(menuId, langCd);
        menu = navAccess.getMenu(menuId, langCd);
      }
    
      // if menu has not been defined for a langauge
      if ((optionList == null)||(optionList.isEmpty())) {
        optionList = navAccess.getMenuList(menuId);
        menu = navAccess.getMenu(menuId);
      }
    
      if (menu != null) {
        session.setAttribute(menuDataName, menu);
        setNavBar(menuBarName, session, request, menu.getMenuName());
      }  

      // do not replace menu if there are no more submenus
      if (optionList != null) {
        if (!optionList.isEmpty()) {
          session.setAttribute(menusName, optionList);
        }
      }

  }

  public ActionErrors setSecurityBar(HttpServletRequest request ) {
	ActionErrors err = new ActionErrors();
  	LoginAccess loginAccess = new LoginAccess();
	Locale locale = getLocale(request);
	String langCd = locale.getLanguage();

	HttpSession session = request.getSession();
	String userId = (String) session.getAttribute("userId");

	try {
		String appId = (String) session.getAttribute("appId");
		if (appId != null) {
			List menus = loginAccess.getPermissions(userId, appId, langCd);
			session.setAttribute("topLevelMenus", menus);
		}

	} catch (Exception e) {
        err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
		return err;
	}
	return null;
  	
  }
  

  /**
   * Set navigation bar in session object
   * Using this method, navigation paths for menus and categories are set
   * in the session object.
   *
   * @param name Menu or Category name to be displayed in the navigation path
   * @param bar  Name of the session attribute
   * @param request
   * @param session
  */
  public void setNavBar(String bar, HttpSession session, HttpServletRequest request, String name)
        throws ServletException {

    try {
      // NavBarItem gets a NullPointer in its equals method. Try this:
      if (name == null) return;

      NavigatorBar navBar = (NavigatorBar) session.getAttribute(bar);
      if (navBar == null)
        navBar = new NavigatorBar();

      // to override the url for the new item set navPath
      // navPath as "" will not add any item at all to the navBar
      String navPath = (String) request.getAttribute("navPath");

      if (navPath != null) {
        if (! (navPath.equals("")) || ! (navPath.equalsIgnoreCase("null"))) {
          navBar.add(name, navPath);
        }
      } else {
        String queryString = request.getQueryString();

        // if we remove getContextPath then we need to remove the leading slash to get default context
        // so just leave the contextPath as it is
        String url = request.getContextPath() + request.getServletPath();

        if (queryString == null)
          navBar.add(name, url );
        else
          navBar.add(name, (url + "?" + queryString) );
      }
      session.setAttribute(bar, navBar);

    } catch (Exception e) {
        e.printStackTrace();
        ActionErrors errs = new ActionErrors();
        errs.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
       // errs.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
        saveErrors(request, errs);
    }
  }


  /**
  * Removes categoryId, categories, navBar, categoryValue from the Session object
  */
  public void removeCategories(HttpSession session) {

    session.removeAttribute("categoryId");
    session.removeAttribute("categories");
    session.removeAttribute("navBar");
    session.removeAttribute("categoryValue");
  }
  
 /**
 * Removes menuId, menus, menuBar from the Session object
 */
  public void removeMenus(HttpSession session) {

    session.removeAttribute("menuId");
    session.removeAttribute("menus");
    session.removeAttribute("menuBar");
    
  }


  /**
   * gets category list
   *
   * @param categoryId
   * @param langCd
   * @deprecated
   */
  public Collection getCategoryList(String categoryId, String langCd)  throws Exception {

    Collection optionList = null;

    if (langCd != null) {
      optionList = navAccess.getCategoryList(categoryId, langCd);    
    }
    
    if ((optionList == null)||(optionList.isEmpty())) {
      optionList = navAccess.getCategoryList(categoryId);    
    }

    return optionList;
  }

}
