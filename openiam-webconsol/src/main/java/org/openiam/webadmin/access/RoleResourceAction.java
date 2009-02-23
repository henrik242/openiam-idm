/**
 * ------------------------------------------------------------------------------ 
 * Title: SampleAction
 * Author: APS 04-09-2004
 * Overview: gemonique starting action
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
 * aaa - APS   
 * ------------------------------------------------------------------------------           
 */

package org.openiam.webadmin.access;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class RoleResourceAction extends NavigationDispatchAction  {
  private ACLAccess acl = new ACLAccess();

  
  
  public ActionForward getTree(ActionMapping mapping, ActionForm form, 
      HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {  

    System.out.println("RoleResourceAction - getTree...");
    ActionErrors err = new ActionErrors();  

    // Extracts nav, categoryId, menuId and nextAction parameters from a request object and sets
    // nextAction, categoryId,categoryValue, categories, navBar, menuId, menus, menuBar in session
    // categoryId left unchanged if null or showlist=1
    // nav=resetMenu, resetCat removes those attributes only, reset removes both
    super.setNavigation(mapping,form,request,response);

    Locale locale = getLocale(request);
    //MessageResources messages = getResources();
    HttpSession session = request.getSession();
    String langCd = locale.getLanguage();
    
    String resourceId = request.getParameter("resourceId");
    List resources = new ArrayList();
        
    try {
      // get all resource descendents
      if (resourceId != null) {
        diamelle.security.resource.ResourceValue rv = acl.getResource(resourceId);
        request.setAttribute("resourceValue", rv);
        resources = acl.getNodeResources(resourceId);       
        request.setAttribute("resources", resources);
      }
        
    } catch(Exception e) {
      e.printStackTrace();
      err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
      //err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
    }
   
    // if errors, return to welcome page via index.jsp
    if (!err.isEmpty()) {
      saveErrors(request, err);
      // request.setAttribute("nextAction", "xxx.do?method=yyy");
      return mapping.findForward("failure");
    }

    // category.jsp will need this to provide link to action class
    session.setAttribute("nextAction", "role.do?method=acl");

    return mapping.findForward("success");
  }


  public ActionForward setResources(ActionMapping mapping, ActionForm form, 
      HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {  

    System.out.println("RoleResourceAction - setResources...");
    ActionErrors err = new ActionErrors();  
    HttpSession session = request.getSession();


    try {
           
      DynaActionForm roleResourceForm = (DynaActionForm) form;
      String submit = (String) roleResourceForm.get("submit");
      String roleId = (String) roleResourceForm.get("roleId"); 
      
      // RoleAction - acl needs resourceId
      String resourceId = (String) roleResourceForm.get("resourceParent");
      
      request.setAttribute("resourceId", resourceId); 
      
      String[] privilegeArr = request.getParameterValues("privileges");
      List privileges = new ArrayList();
      if (privilegeArr != null) {
        for (int p=0; p < privilegeArr.length; p++) {
          //System.out.println("privileges list " + privilegeArr[p]);
          privileges.add(privilegeArr[p]);
        }   
      }  

      if (submit.equals("AssignPrivileges")) {   
      	// add privileges to the node (parent)
      	// aps 120704 - hmmm, maybe node privileges should not be added
      	//acl.addResourcePrivileges(resourceId, roleId, privileges);
        String[] resourceIds = request.getParameterValues("resourceId");
        
        
        if (resourceIds != null) {
          for (int r = 0; r < resourceIds.length; r++) {
        	//System.out.println("adding privilege for resource " + resourceIds[r] + "-" + roleId);
            acl.addResourcePrivileges(resourceIds[r], roleId, privileges);
          }
        }   

     } else if (submit.equals("RemovePrivileges")) {
        String[] roleResourcePrivileges = request.getParameterValues("roleResourcePrivileges");
        if (roleResourcePrivileges !=null) {
          for (int r = 0; r < roleResourcePrivileges.length; r++) {
            String s = roleResourcePrivileges[r];
            StringTokenizer t = new StringTokenizer(s, "-");
            String resId = (String) t.nextElement();
            String privId = (String) t.nextElement();
            acl.removePrivilege(resId, roleId, privId);
          }
        }        
        
        // mark all resource checkboxes for this node      
     } else if (submit.equalsIgnoreCase("SelectAll")) {
     	 request.setAttribute("selectAll", "Y");               
     }

   } catch (Exception e) {
     e.printStackTrace();
     return (mapping.findForward("failure"));
   } 
   return (mapping.findForward("success"));
 }


/**
 * Fetches input data from the form and sets into value class
 * @param form - ActionForm
 * return void
 */
  
  private diamelle.security.resource.ResourceValue getResourceValue(ActionForm form) {
    DynaActionForm f = (DynaActionForm)form;
  
    diamelle.security.resource.ResourceValue val = new diamelle.security.resource.ResourceValue();
    val.setResourceId((String)f.get("resourceId"));
    val.setCategoryId((String)f.get("categoryId"));
    val.setDescription((String)f.get("description"));
    //val.setResourceTypeId((String)f.get("resourceTypeId"));
    val.setBranchId((String)f.get("branchId"));
    val.setDisplayOrder(0);    
    // set to null if resourceParent is ""
    String resourceParent = (String)f.get("resourceParent") ;
    if ((resourceParent == null)||(resourceParent.equals("")))
      resourceParent = null;
    val.setResourceParent(resourceParent);
  
    return val;
  }
  

/**
 * Takes values from value class object and sets it into form
 * @param form - ActionForm
 * @param form - ResourceValue
 * return void
 */ 
  private void setResourceValue(ActionForm form, diamelle.security.resource.ResourceValue val) {
    DynaActionForm resourceForm = (DynaActionForm) form;
    if (val != null) {
      resourceForm.set("resourceId", val.getResourceId());
      resourceForm.set("branchId", val.getBranchId());      
      resourceForm.set("categoryId", val.getCategoryId());      
      resourceForm.set("resourceParent", val.getResourceId());  
      resourceForm.set("description", val.getDescription());
      //resourceForm.set("resourceTypeId", val.getResourceTypeId());
    }
  }


}