/**
 * ------------------------------------------------------------------------------ 
 * Title: NavigationAccess
 * Author: 
 * Overview: Access class for Navigation Action classes
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
 * aaa - APS 041504 added lang support for category value. 
 *        moved CategoryService creation into constructor  
 * ------------------------------------------------------------------------------           
 */
 
package org.openiam.webadmin.busdel.base;

import diamelle.common.cat.*;
import diamelle.ebc.navigator.*;
import java.util.*;

/**
 * Provided commonly needed functionalty for all action classes.
 */

public class NavigationAccess extends DiamelleBaseAccess {
  Navigator nav = null;
  //CategoryService cat = null;
  String categoryId = null;

  public NavigationAccess() {
    try {
      NavigatorHome home = (NavigatorHome)getHome("Navigator");
      nav = home.create();
      //CategoryServiceHome cathome = (CategoryServiceHome) getHome("CategoryService");
      //cat = cathome.create();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the List of menu
   * @param menuGroup
   * @returns Collection
   */
  public Collection getSubMenuData (String menuGroup) throws Exception {
    return nav.getAllSubMenuData(menuGroup);
  }


 /**
  * Returns a list of menus for the menu group
  * @param menuGroup - Group Id of the menus
  * @return List containing menuData objects.
  */
  public List getMenuList(String menuGroup) throws java.rmi.RemoteException {
      return nav.getMenuGroup(menuGroup);
  }

  public List getMenuList(String menuGroup, String langCd) throws java.rmi.RemoteException {
      return nav.getMenuGroup(menuGroup, langCd);
  }

   public MenuData getMenu(String menuId) throws java.rmi.RemoteException {
      return nav.getMenu(menuId);
  }

   public MenuData getMenu(String menuId, String langCd) throws java.rmi.RemoteException {
      return nav.getMenu(menuId, langCd);
  }


/**
 * Creates an instance of CategoryService ejb if not yet created and stores it
 * as a variable.
 * @deprecated
 */

  private void setCategoryService() throws java.rmi.RemoteException {
 /*   if (cat == null) {
      try {
          CategoryServiceHome home = (CategoryServiceHome)getHome("CategoryService");
          cat = home.create();
        } catch (javax.ejb.CreateException ce) {
          ce.printStackTrace();
          throw new java.rmi.RemoteException();
        }
    }
*/  }

  /*
      if ( (categoryId == null) || (categoryId.length() < 1)  ){
        categoryId = (String) jndiContext.lookup("java:comp/env/CategoryRoot");
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  */

 /**
  * Returns the list of immediate sub categories of this category.
  * @param categoryId the categoryId of the parent.
  * @return the list of immediate sub categories of this category.
  */
  public Collection getCategoryList(String categoryId) throws java.rmi.RemoteException {
    //  return cat.getCategoryList(categoryId);
	  return null;
  }

 /**
  * Returns the list of immediate sub categories of this category.
  * @param categoryId the categoryId of the parent.
  * @param languageCd the languageCd
  * @return the list of immediate sub categories of this category.
  */
  public Collection getCategoryList(String categoryId,String languageCd) throws java.rmi.RemoteException {
    //  return cat.getCategoryList(categoryId, languageCd);
	  return null;
  }

 /**
  * Gets the category information.
  * @param categoryId the categoryId of the category.
  * @return the CategoryValue object which holds information about the category.
  */
  public CategoryValue getCategory(String categoryId)throws java.rmi.RemoteException {
     // return cat.getCategory(categoryId);
      return null;
  }

  /**
   * Gets all the information for a particular combination of CategoryId and
   * Language Code .
   *
   * @param categoryId is the category Id for which the Infomation is Required.
   * @param languageCd is the Language Code for the category
   * @return the Bulk Accessor object of Category.
   */
  public CategoryValue getCategory(String categoryId, String languageCd) 
        throws java.rmi.RemoteException {
      //return cat.getCategory(categoryId, languageCd);    
	  return null;
  }

 /**
  * Gets the category name.
  * @param categoryId the categoryId of the category.
  * @return String category name
  */
  public String getCategoryName(String categoryId)throws java.rmi.RemoteException {
      CategoryValue cv = getCategory(categoryId);
      if (cv == null) return null;
      return cv.getCategoryName();
  }
  
 /**
  * Gets the category name.
  * @param categoryId the categoryId of the category.
  * @param languageCd is the Language Code for the category
  * @return String category name
  */
  public String getCategoryName(String categoryId,String languageCd) throws java.rmi.RemoteException {
      CategoryValue cv = getCategory(categoryId, languageCd);
      if (cv == null) return null;
      return cv.getCategoryName();
  }
  
//Menu methods
  
  /**
   * Adds Menu.
   * @param menuData - MenuData
   */  
  public void addMenu (MenuData menuData) throws Exception {
    if (menuData.getMenuId() == null || menuData.getMenuId().length() <1)
        menuData.setMenuId(getNextId("MENU_ID"));
        
    nav.addMenu(menuData);


  }

  /**
   * Deletes a Menu.
   * @param menuId - String
   */  
  public void deleteMenu(String menuId) throws Exception {
    nav.removeMenu(menuId);
  }

  /**
   * Updates Menu.
   * @param menuData - MenuData
   */  
  public void updateMenu(MenuData menuData) throws Exception {
    nav.updateMenu(menuData);
  }
}

