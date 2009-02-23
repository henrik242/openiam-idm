/**
 * ------------------------------------------------------------------------------
 * Title: UserGroupAction
 * Author: LD 04-09-2004
 * Overview:Handles all functions like adding a user to a Group and removing a user/s from a Group.
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
 *
 * ------------------------------------------------------------------------------
 */

package org.openiam.webadmin.user;

import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.springframework.web.context.WebApplicationContext;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

import java.util.*;

public class UserRoleAction extends NavigationDispatchAction {

	
 /**
   * Adds a User To a Role and Removes a User From a Role
   */
  public ActionForward edit ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      HttpSession session = req.getSession( true );

      RoleDataServiceAccess roleDataAcc = null;
      
 	  WebApplicationContext webContext =  getWebApplicationContext();
 	  roleDataAcc = new RoleDataServiceAccess(webContext);
      
 
      DynaValidatorForm userForm = (DynaValidatorForm) form;
      String personId = (String) userForm.get("personId");
      String submit = (String) userForm.get("submit");

      Role[] roleAry  = null;
      Role[] userRoleAry = null;

      try {

         if ( submit != null && submit.equals(">")) {
             //adding a user to a Group/s
        	 System.out.println(" in user role action..submit > ");
        	 
             if (req.getParameterValues("roleId") != null) {
                 String[] roleParamAry = req.getParameterValues("roleId");
                 
                 for(int i = 0; i < roleParamAry.length ; i++) {
                	 String roleParamValue = roleParamAry[i];
                	               	 
                	 int indx = roleParamValue.indexOf("*");
                	 String serviceId = roleParamValue.substring(0, indx);
                	 String rId = roleParamValue.substring(++indx);
                	 
                	 roleDataAcc.addUserToRole(serviceId, rId, personId);
                 }
             }
             roleAry = roleDataAcc.getAllRoles();
             userRoleAry = roleDataAcc.getUserRoles(personId);       
         } else if (submit != null &&  submit.equals("<")) {
               // removing a user from the Group/s
               
               if (req.getParameterValues("userroleId") != null) {
                  String[] roleParamAryId = req.getParameterValues("userroleId");
                  for(int i = 0; i < roleParamAryId.length ; i++) {
                 	 String roleParamValue = roleParamAryId[i];
                	 int indx = roleParamValue.indexOf("*");
                	 String serviceId = roleParamValue.substring(0, indx);
                	 String rId = roleParamValue.substring(++indx);
                	 roleDataAcc.removeUserFromRole(serviceId,rId, personId);
                  }
               }
               roleAry = roleDataAcc.getAllRoles();
               userRoleAry = roleDataAcc.getUserRoles(personId);      

         }

         
         req.setAttribute("personId",personId);
         req.setAttribute("roleList",roleAry);
         req.setAttribute("userRoleAry",userRoleAry);


      } catch(Exception ex) {
           ex.printStackTrace();
      }
      return (mapping.findForward("success"));
  }
}