package org.openiam.webadmin.busdel.base;

import java.util.*;
import javax.servlet.http.*;

import diamelle.common.cat.*;
import diamelle.ebc.navigator.*;


public class CategoryAccess extends NavigationAccess{

  CategoryService cat = null;

  public CategoryAccess() {
    try {
      CategoryServiceHome home = (CategoryServiceHome) this.getHome("CategoryService");
      cat = home.create();
    } catch (Exception e) {
        e.printStackTrace();
    }
 }

  public void addCategory(CategoryValue cv) throws java.rmi.RemoteException {
	   if (cv.getCategoryId() == null || cv.getCategoryId().length() ==0) {
		   cv.setCategoryId(this.getNextId("CATEGORY_ID"));
	   }
	   cat.addCategory(cv);
}
  
  public void addCategoryLanguage(String catId,String langCd,String categoryName) throws java.rmi.RemoteException {
  	cat.addLanguageToCategory(catId,langCd,categoryName);
   
}

  public void removeCategory(String catId) throws Exception {
      cat.removeCategory(catId);
  }

  public void updateCategory(CategoryValue cv) throws java.rmi.RemoteException {
      cat.updateCategory(cv);
  }

   public String getNext(String catId) throws Exception{
      return this.getNextId(catId);
   }

   public List getAllSubCategories(String catId) throws java.rmi.RemoteException {
      return cat.getAllSubCategories(catId);
   }

   public List getAllCategories(String catId) throws Exception {
      return cat.getCategoryList(catId);
   }

  /**
  * This method is used to display the navigator bar.
  *
  * @param req the request in which the menu has to be set.
  * @param catId the catId for which all the sub category list has to be displayed.
  * @param path the path of the servlet which is going to take of the Category clicked.
  * @throws ServletException
  */
   public void setNavBar(HttpServletRequest req, String catId,String path ) throws Exception {
    HttpSession session = req.getSession(true);
    CategoryValue cv  = null;

    NavigatorBar navBar = (NavigatorBar)session.getAttribute("navbar");
    if (navBar == null)
      navBar = new NavigatorBar();

    if (catId != null)
      cv = getCategory(catId);

     if(cv != null)
        navBar.add(cv.getCategoryName(), (path + "?" + req.getQueryString()) );

    session.setAttribute("navbar", navBar);

   }


   public void setMenuCategoryList(HttpServletRequest req, String catId, String path) throws Exception{
      HttpSession session = req.getSession(true);
      NavigatorBar navBar = new NavigatorBar();

      List categoryList = this.getAllCategories(catId);

      if ( categoryList != null && (!categoryList.isEmpty())) {
          session.setAttribute("categoryList" ,categoryList);

      } else {
          session.removeAttribute("categoryList");

      }
      this.setNavBar(req, catId, path);
      session.setAttribute("path", path);
   }


}