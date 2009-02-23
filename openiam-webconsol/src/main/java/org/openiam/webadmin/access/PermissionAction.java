/**
 * ------------------------------------------------------------------------------
 * Title: PermissionAction Author: APS 04-09-2004 Overview:Handles all functions
 * like adding , updating, deleting and viewing Permissions.
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

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;

import java.io.*;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.DynaValidatorForm;

public class PermissionAction extends NavigationAction {

	//creating an instance of the access class
	private SecurityAccess securityAccess = new SecurityAccess();

	private NavigationAccess navAccess = new NavigationAccess();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("PermissionAction - viewPermissions...");

		Locale locale = getLocale(request);
		HttpSession session = request.getSession();
		String langCd = locale.getLanguage();
		ActionErrors errors = new ActionErrors();

		DynaValidatorForm roleForm = (DynaValidatorForm) form;
		String submit = (String) roleForm.get("submit");

		String menu = (String) roleForm.get("menu");
		String roleId = (String) roleForm.get("roleId");

		try {
			Context ctx = new InitialContext();

			if (submit != null && submit.equals("<")) {
				// removes permission from role
				if (request.getParameterValues("perId") != null) {
					String[] perId = request.getParameterValues("perId");
					for (int i = 0; i < perId.length; i++) {
						securityAccess.removePermission(roleId, perId[i]);
					}
				}
			} else if (submit.equals(">")) {
				// adds permission in role
				if (request.getParameterValues("menId") != null) {
					String[] menId = request.getParameterValues("menId");
					for (int i = 0; i < menId.length; i++) {
						securityAccess.addPermission(roleId, menId[i]);
					}
				}
			}

			List permissions = (List) securityAccess.getPermissionsInRole(
					roleId, langCd);
			// no permissions set in desired language, so try default lang
			if (permissions == null) {
				String defaultLanguage = (String) ctx
						.lookup("java:comp/env/defaultLanguage");
				permissions = (List) securityAccess.getPermissionsInRole(
						roleId, defaultLanguage);
			}
			List menus = (List) navAccess.getSubMenuData(menu);

			request.setAttribute("permissions", permissions);
			request.setAttribute("menus", menus);
			request.setAttribute("menu", menu);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

}