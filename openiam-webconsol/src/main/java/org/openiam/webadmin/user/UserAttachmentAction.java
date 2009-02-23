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
 
 
package org.openiam.webadmin.user;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;

import diamelle.ebc.user.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

public class UserAttachmentAction extends NavigationDispatchAction {


  public ActionForward attachmentForm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

      try {

        String userId = (String) request.getParameter("userId");
        String attachmentId = (String) request.getParameter("attachmentId");

        if (attachmentId != null && attachmentId.length() > 0) {
          UserAccess userAccess = new UserAccess();
          AttachmentProperty attachment = userAccess.getAttachment(userId, attachmentId);

          request.setAttribute("attachment", attachment);
        }

        request.setAttribute("userId", userId);

     } catch(Exception ex) {
           return (mapping.findForward("failure"));
    }
      return (mapping.findForward("attachment"));
  }



  public ActionForward saveAttachment ( ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      HttpSession session = req.getSession( true );

      DynaValidatorForm attachForm = (DynaValidatorForm) form;
      String userId = (String) attachForm.get("userId");

      try {

        UserAccess userAccess = new UserAccess();
        userAccess.saveAttachment(userId, getAttachment(attachForm));

     } catch(Exception ex) {
           ex.printStackTrace();
    }
    return (mapping.findForward("success"));
  }


  public ActionForward deleteAttachment ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res) throws IOException, ServletException {
      String userId = request.getParameter("userId");
      String attachmentId = request.getParameter("attachmentId");

      try {

        UserAccess userAccess = new UserAccess();
        userAccess.removeAttachmentProperty(userId, attachmentId);

     } catch(Exception ex) {
           ex.printStackTrace();
    }
    return (mapping.findForward("success"));
  }



  private AttachmentProperty getAttachment(DynaValidatorForm attachForm) {
     AttachmentProperty prop = new AttachmentProperty();

     try {
         prop.setId((String) attachForm.get("id"));
	       prop.setValue((String) attachForm.get("description"));
	       prop.setName((String) attachForm.get("name"));
	       prop.setUrl((String) attachForm.get("url"));
         prop.setParentKey((String) attachForm.get("userId"));

     } catch (Exception e ) {
	           e.printStackTrace();
     }
     return prop;
 }



}