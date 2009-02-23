/**
 * ------------------------------------------------------------------------------
 * Title: PrivilegeAction Author: APS 04-09-2004 Overview: gemonique starting
 * action
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
 * CHANGE CONTROL: aaa - APS
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
import org.apache.struts.util.*;
import diamelle.security.resource.*;
import org.openiam.webadmin.busdel.security.ACLAccess;
import org.apache.struts.validator.DynaValidatorForm;

public class PrivilegeAction extends NavigationDispatchAction {
	private SecurityAccess security = new SecurityAccess();

	private ACLAccess ac = new ACLAccess();

	/**
	 * Display list of Privileges
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward viewPrivilege(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("PrivilegeAction - viewPrivilege...");
		HttpSession session = request.getSession();
		ActionErrors errors = new ActionErrors();
		try {
			//intializing the policylist.jsp
			init(mapping, form, request, response);

			//removing mode from session
			session.removeAttribute("mode");
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("privileges");
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("PrivilegeAction - init...");
		ActionErrors err = new ActionErrors();

		// Extracts nav, categoryId, menuId and nextAction parameters from a
		// request object and sets
		// nextAction, categoryId,categoryValue, categories, navBar, menuId,
		// menus, menuBar in session
		// categoryId left unchanged if null or showlist=1
		// nav=resetMenu, resetCat removes those attributes only, reset removes
		// both
		super.setNavigation(mapping, form, request, response);

		Locale locale = getLocale(request);
		//MessageResources messages = getResources();
		HttpSession session = request.getSession();
		String langCd = locale.getLanguage();

		// String categoryId = (String)session.getAttribute("categoryId");

		try {

			// do something
			List privilege = ac.getAllPrivileges();
			request.setAttribute("privilege", privilege);

		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		// if errors, return to welcome page via index.jsp
		if (!err.isEmpty()) {
			saveErrors(request, err);
			// request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("index");
		}

		// category.jsp will need this to provide link to action class
		// request.setAttribute("nextAction", "xxx.do?method=yyy");

		return mapping.findForward("privileges");
	}

	/**
	 * Sets mode to Add, for adding new Privileges
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward addPrivilege(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("PrivilegeAction - addPrivileges.....");

		//set mode. if mode is add/edit, privilegesList jsp will include
		// resource.jsp
		DynaValidatorForm privilegeForm = (DynaValidatorForm) form;
		privilegeForm.set("mode", "add");

		//intializing the privileges.jsp
		init(mapping, form, request, response);
		return mapping.findForward("privileges");
	}

	/**
	 * Sets mode to Edit, for updating the Privilege Details
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward editPrivilege(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("PrivilegesAction - editPrivileges...");
		ActionErrors errors = new ActionErrors();
		DynaValidatorForm privilegeForm = (DynaValidatorForm) form;

		try {
			String privilegeId = request.getParameter("privilegeId");

			//set mode. if mode is add/edit, privileges jsp will include
			// privilege.jsp
			privilegeForm.set("mode", "edit");

			if (privilegeId != null) {

				PrivilegeValue privilegeVal = ac.getPrivilege(privilegeId);
				setPrivilege(form, privilegeVal);

				//intializing the resourcelist.jsp
				init(mapping, form, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("privileges");
	}

	/**
	 * Saves/Update Privilege Details
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward savePrivilege(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("PrivilegeAction - savePrivileges...");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaValidatorForm privilegeForm = (DynaValidatorForm) form;

		String mode = (String) privilegeForm.get("mode");

		PrivilegeValue privilegeValue = getPrivilege(form);
		try {
			if (mode.equalsIgnoreCase("add")) {
				try {
					ac.addPrivilege(privilegeValue);
					privilegeForm.set("mode", "view");
				} catch (Exception e) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.addRecord"));

					e.printStackTrace();
				}

			} else if (mode.equalsIgnoreCase("edit")) {
				//updating the privileges
				ac.savePrivilege(getPrivilege(form));
				privilegeForm.set("mode", "view");
			}
			init(mapping, form, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.operation"));
		}

		// if errors, return to welcome page via index.jsp
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			//request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("privileges");
		}

		// category.jsp will need this to provide link to action class
		// request.setAttribute("nextAction", "xxx.do?method=yyy");
		return mapping.findForward("privileges");
	}

	/**
	 * Removes selected Privilege/Privilege from the list of Privileges
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward removePrivilege(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		System.out.println("PrivilegeAction - removePrivileges...");
		ActionErrors errors = new ActionErrors();
		String[] privilegeId = request.getParameterValues("privilegeId");

		try {
			if (request.getParameterValues("privilegeId") != null) {
				// deleting a privilege from the service
				for (int i = 0; i < privilegeId.length; i++) {
					ac.removePrivilege(privilegeId[i]);
				}
			}
			init(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("privileges");
	}

	/**
	 * Fetches input data from the form and sets into value class
	 * 
	 * @param form -
	 *            ActionForm return void
	 */

	private PrivilegeValue getPrivilege(ActionForm form) {
		DynaValidatorForm privilegeForm = (DynaValidatorForm) form;

		PrivilegeValue privilegeVal = new PrivilegeValue();
		privilegeVal.setPrivilegeId((String) privilegeForm.get("privilegeId"));
		privilegeVal.setDescription((String) privilegeForm.get("description"));

		return privilegeVal;
	}

	/**
	 * Takes values from value class object and sets it into form
	 * 
	 * @param form -
	 *            ActionForm
	 * @param form -
	 *            RoleValue return void
	 */
	private void setPrivilege(ActionForm form, PrivilegeValue privilegeVal)
			throws Exception {
		DynaValidatorForm privilegesForm = (DynaValidatorForm) form;

		privilegesForm.set("privilegeId", privilegeVal.getPrivilegeId());
		privilegesForm.set("description", privilegeVal.getDescription());
	}

}