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

import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
import org.springframework.web.context.WebApplicationContext;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

import java.util.*;

public class UserGroupAction extends NavigationDispatchAction {

	
 /**
   * Adds a User To a Group and Removes a User From a Group
   */
  public ActionForward edit ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      HttpSession session = req.getSession( true );

      GroupDataServiceAccess groupDataAcc = null;
      
 	  WebApplicationContext webContext =  getWebApplicationContext();
	  groupDataAcc = new GroupDataServiceAccess(webContext);
      
 
      DynaValidatorForm userForm = (DynaValidatorForm) form;
      String personId = (String) userForm.get("personId");
      String submit = (String) userForm.get("submit");

      List<Group> groupList = new ArrayList<Group>();
      List<Group> userList = new ArrayList<Group>();

      try {


         if ( submit != null && submit.equals(">")) {
             //adding a user to a Group/s

             if (req.getParameterValues("groupId") != null) {
                 String[] grId = req.getParameterValues("groupId");
                 for(int i = 0; i < grId.length ; i++) {
                   //  userAccess.addUserGroup(personId,grId[i]);
                	 System.out.println("UserGroupAction : adding user to a group ");
                	 groupDataAcc.addUserToGroup(grId[i], personId);
                 }
             }
                groupList = groupDataAcc.getAllGroups(true);
                userList = groupDataAcc.getUserGroups(personId);

             
         } else if (submit != null &&  submit.equals("<")) {
               // removing a user from the Group/s
               
               if (req.getParameterValues("grouproleId") != null) {
                  String[] grId = req.getParameterValues("grouproleId");
                  for(int i = 0; i < grId.length ; i++) {
                      //userAccess.deleteUserGroup(personId,grId[i]);
                	  System.out.println("in UserGroupAction - removeUserFromGroup: grpId=" + grId[i] + " userId=" + personId);
                	  groupDataAcc.removeUserFromGroup(grId[i], personId);
                  }
               }
               groupList = groupDataAcc.getAllGroups(true);
               userList = groupDataAcc.getUserGroups(personId);

         }

         
         req.setAttribute("personId",personId);
         req.setAttribute("groupList",groupList);
         req.setAttribute("userList",userList);


      } catch(Exception ex) {
           ex.printStackTrace();
      }
      return (mapping.findForward("success"));
  }
}