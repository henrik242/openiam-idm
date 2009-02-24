/**
 * ------------------------------------------------------------------------------
 * Title: UserAction
 * Author: APS 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * list of users.
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

package org.openiam.selfserv.da.user;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;

import diamelle.ebc.user.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

public class UserEmployeeAction extends NavigationDispatchAction {


  public ActionForward addEmployeeForm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

      try {

        String personId = (String) request.getParameter("personId");
        request.setAttribute("personId", personId);

     } catch(Exception ex) {
           return (mapping.findForward("failure"));
    }
      return (mapping.findForward("success"));
  }

  public ActionForward saveEmployee ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      HttpSession session = req.getSession( true );

      DynaValidatorForm employeeForm = (DynaValidatorForm) form;
      String personId = (String) employeeForm.get("personId");
      String errorString = "";

      try {

        UserAccess userAccess = new UserAccess();
        OrgRelationship org = getOrgRelationship(employeeForm);

        UserData employeeData = userAccess.getUser(org.getEmployeeId());
        if (employeeData == null) {
          errorString = "Invalid user id.";
          req.setAttribute("errormessage", errorString);
          return (mapping.findForward("fail"));
        }
        try {
          List empList = userAccess.getEmployees(personId);
          if (empList != null) {
            int size = empList.size();
            for (int x=0; x < size; x++) {
              employeeData = (UserData) empList.get(x);
              if (employeeData.getId().equals(org.getEmployeeId())) {
                errorString = "Employee already exists in the org tree.";
                req.setAttribute("errormessage", errorString);
                return (mapping.findForward("fail"));
              }
            }
          }
        } catch(Exception e) {
            e.printStackTrace();
        }

     } catch(Exception ex) {
           ex.printStackTrace();
    }
    return (mapping.findForward("success"));
  }



  private OrgRelationship getOrgRelationship(DynaValidatorForm empForm) {
   OrgRelationship org = new OrgRelationship();
   org.setEmployeeId((String) empForm.get("employeeId"));
   org.setManagerId((String) empForm.get("personId"));
   return org;
 }



}