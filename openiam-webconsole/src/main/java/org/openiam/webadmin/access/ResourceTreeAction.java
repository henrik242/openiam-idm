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
import diamelle.common.cat.CategoryValue;
import java.io.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import diamelle.security.resource.*;

public class ResourceTreeAction extends NavigationDispatchAction {
	private ACLAccess acl = new ACLAccess();

	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		System.out.println("ResourceTreeAction - init...");
		ActionErrors err = new ActionErrors();

		Locale locale = getLocale(request);
		//MessageResources messages = getResources();
		HttpSession session = request.getSession();
		String langCd = locale.getLanguage();

		String resourceId = request.getParameter("resourceId");
		List resources = new ArrayList();

		try {
			// Extracts nav, categoryId, menuId and nextAction parameters from a request object and sets
			// nextAction, categoryId,categoryValue, categories, navBar, menuId, menus, menuBar in session
			// categoryId left unchanged if null or showlist=1
			// nav=resetMenu, resetCat removes those attributes only, reset removes both
			super.setNavigation(mapping, form, request, response);
			String categoryId = (String) session.getAttribute("categoryId");

			System.out.println("--categoryId=" + categoryId);
			
			if (categoryId == null || categoryId.length() == 0) {
				Context ctx = new InitialContext();
				categoryId = (String) ctx.lookup("java:comp/env/RootCategory");
				
				System.out.println("categoryId=" + categoryId);
				
				super.setCategories(
					categoryId,
					langCd,
					request,
					session,
					"categories",
					"navBar",
					"categoryValue");
			}

			// get all resource descendents
			if (resourceId != null) {
				System.out.println("resources for ResourceId " + resourceId);
				diamelle.security.resource.ResourceValue rv =
					acl.getResource(resourceId);
				request.setAttribute("resourceValue", rv);
				resources = acl.getNodeResources(resourceId);

				List privileges = acl.getResourcePrivileges(resourceId);
				if (privileges != null)
					request.setAttribute("privileges", privileges);

			} else {
				System.out.println("resources for categoryId " + categoryId);
				// if no resourceId, get root branches
				resources = acl.getCategoryBranches(categoryId);
				
				System.out.println("Resource=" + resources);
			}
			request.setAttribute("resources", resources);

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
		session.setAttribute("nextAction", "security/resourceTree.do?method=init");
		session.removeAttribute("sideMenus");

		return mapping.findForward("resources");
	}

	public ActionForward removeResource(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		System.out.println("ResourceTreeAction - removeResource...");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		try {
			String[] arr = request.getParameterValues("resourceId");
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					//System.out.println("removing descendents of resourceId " + arr[i]);

					// get resource descendents
					List descendents = acl.getNodeResources(arr[i]);

					// check for null since descendents may already have been deleted
					if (descendents != null) {

						Iterator it = descendents.iterator();
						while (it.hasNext()) {
							ResourceValue resVal = (ResourceValue) it.next();
							String resId = resVal.getResourceId();
							System.out.println("removing resourceId " + resId);
							acl.removeResource(resId);
						}
					}
					// remove resource
					try {
						acl.removeResource(arr[i]);
					} catch (Exception e) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.removeResource"));
					
					}
				}
			}

			// from init method, to get resource list
			String resourceId = request.getParameter("resourceParent");
			String categoryId = (String) session.getAttribute("categoryId");
			List resources = new ArrayList();

			// get all resource descendents
			if ((resourceId == null) || (resourceId.length() == 0)) {
				resources = acl.getCategoryBranches(categoryId);
			} else {
				diamelle.security.resource.ResourceValue rv =
					acl.getResource(resourceId);
				request.setAttribute("resourceValue", rv);
				resources = acl.getNodeResources(resourceId);

				List privileges = acl.getResourcePrivileges(resourceId);
				if (privileges != null)
					request.setAttribute("privileges", privileges);

			}
			request.setAttribute("resources", resources);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
	    // if errors, return to welcome page via index.jsp
	    if (!errors.isEmpty()) {
	      saveErrors(request, errors);
	    }
		return mapping.findForward("resources");
	}

	public ActionForward removePrivilege(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		System.out.println("ResourceTreeAction - removePrivilege...");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		String submit = request.getParameter("submit");
		String resourceId = request.getParameter("resourceParent");

		try {
			if (submit.equalsIgnoreCase("DeletePrivilege")) {
				String[] roleResourcePrivileges =
					request.getParameterValues("roleResourcePrivileges");
				if (roleResourcePrivileges != null) {
					for (int r = 0; r < roleResourcePrivileges.length; r++) {
						String s = roleResourcePrivileges[r];
						StringTokenizer t = new StringTokenizer(s, "-");
						String roleId = (String) t.nextElement();
						String privId = (String) t.nextElement();
						acl.removePrivilege(resourceId, roleId, privId);
					}
				}

				// mark all resource checkboxes for this node      
			} else if (submit.equalsIgnoreCase("SelectAll")) {
				request.setAttribute("selectAll", "Y");
			}

			// from init method, to get resource list
			String categoryId = (String) session.getAttribute("categoryId");
			List resources = new ArrayList();

			// get all resource descendents
			if ((resourceId == null) || (resourceId.length() == 0)) {
				resources = acl.getCategoryBranches(categoryId);
			} else {
				diamelle.security.resource.ResourceValue rv =
					acl.getResource(resourceId);
				request.setAttribute("resourceValue", rv);
				resources = acl.getNodeResources(resourceId);

				List privileges = acl.getResourcePrivileges(resourceId);
				if (privileges != null)
					request.setAttribute("privileges", privileges);

			}
			request.setAttribute("resources", resources);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		// if errors, return to welcome page via index.jsp
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			// request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("index");
		}

		// category.jsp will need this to provide link to action class
		session.setAttribute("nextAction", "resourceTree.do?method=init");

		return mapping.findForward("resources");
	}

	public ActionForward addResource(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		System.out.println("ResourceAction - addResource...");
		HttpSession session = request.getSession();

		DynaActionForm resourceForm = (DynaActionForm) form;

		String resourceId = (String) resourceForm.get("resourceId");
		diamelle.security.resource.ResourceValue val = acl.getResource(resourceId);

		// set form values for display in jsp
		if (val != null) {
			resourceForm.set("mode", "addResource");
			resourceForm.set("resourceId", null);
			resourceForm.set("branchId", val.getBranchId());
			resourceForm.set("categoryId", val.getCategoryId());
			resourceForm.set("resourceParent", val.getResourceId());
			resourceForm.set("resourceTypeId", val.getResourceTypeId());
		} else {
			String categoryId = (String) session.getAttribute("categoryId");
			resourceForm.set("mode", "addResource");
			resourceForm.set("categoryId", categoryId);
		}

		return mapping.findForward("resourcedetail");
	}

	/**
	 * Fetches input data from the form and sets into value class
	 * @param form - ActionForm
	 * return void
	 */

	private diamelle.security.resource.ResourceValue getResourceValue(
		ActionForm form) {
		DynaActionForm f = (DynaActionForm) form;

		diamelle.security.resource.ResourceValue val =
			new diamelle.security.resource.ResourceValue();
		val.setResourceId((String) f.get("resourceId"));
		val.setCategoryId((String) f.get("categoryId"));
		val.setDescription((String) f.get("description"));
		//val.setResourceTypeId((String)f.get("resourceTypeId"));
		val.setBranchId((String) f.get("branchId"));
		val.setNodeLevel(0);
		val.setDisplayOrder(0);
		// set to null if resourceParent is ""
		String resourceParent = (String) f.get("resourceParent");
		if ((resourceParent == null) || (resourceParent.equals("")))
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
	private void setResourceValue(
		ActionForm form,
		diamelle.security.resource.ResourceValue val) {
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