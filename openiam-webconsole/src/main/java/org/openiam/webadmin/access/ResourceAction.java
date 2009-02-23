/**
 * ------------------------------------------------------------------------------
 * Title: SampleAction Author: APS 04-09-2004 Overview: gemonique starting
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

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.ACLAccess;
import diamelle.base.prop.Property;
import diamelle.base.prop.PropertyImpl;
import diamelle.security.resource.ResourceTypeValue;

public class ResourceAction extends NavigationDispatchAction {
	private ACLAccess acl = new ACLAccess();

	private MetadataAccess meta = new MetadataAccess();

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - init...");

		DynaActionForm f = (DynaActionForm) form;
		String resourceId = (String) f.get("resourceId");

		System.out.println("resourceId is " + resourceId);

		if ((resourceId != null)&&(resourceId.length()>0)) {
			diamelle.security.resource.ResourceValue val = acl
					.getResource(resourceId);
			this.setResourceValue(form, val);
			Collection resourceProp = acl.getResourceProp(resourceId);
			if (resourceProp != null)
				request.setAttribute("resourceProp", resourceProp);
		}
		List resourceTypes = acl.getAllResourceTypes();
		List resourceTypeLabels = resourceTypesToLabels(resourceTypes);
		request.setAttribute("resourceTypes", resourceTypeLabels);

		return mapping.findForward("resource");
	}

	// to build drop down in jsp page
	private List resourceTypesToLabels(List resourceTypes) {
		List labels = new java.util.ArrayList();
		LabelValueBean emptybean = new LabelValueBean("<Select a value>", "-1");
		labels.add(emptybean);

		if (resourceTypes != null) {
			for (int i = 0; i < resourceTypes.size(); i++) {
				ResourceTypeValue val = (ResourceTypeValue) resourceTypes
						.get(i);
				LabelValueBean bean = new LabelValueBean(val.getDescription(),
						val.getResourceTypeId());
				labels.add(bean);
			}
		}
		return labels;
	}

	public ActionForward newResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - newResource...");
		HttpSession session = request.getSession();

		DynaActionForm resourceForm = (DynaActionForm) form;

		// NOTE!! these are PARENT RESOURCE values
		String resourceId = (String) resourceForm.get("resourceId");
		diamelle.security.resource.ResourceValue val = acl
				.getResource(resourceId);

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
			resourceForm.set("mode", "addRoot");
			resourceForm.set("categoryId", categoryId);
		}

		List resourceTypes = acl.getAllResourceTypes();
		List resourceTypeLabels = resourceTypesToLabels(resourceTypes);
		request.setAttribute("resourceTypes", resourceTypeLabels);

		return mapping.findForward("resource");
	}

	public ActionForward addResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - addResource...");
		ActionErrors err = new ActionErrors();

		try {
			diamelle.security.resource.ResourceValue val = getResourceValue(form);

			// if no parent, it must be a root node and branchId must equal
			// resourceId
			if (val.getResourceParent() == null)
				acl.addRoot(val);
			else
				acl.addResource(val);

			String resourceId = val.getResourceId();
			DynaActionForm resourceForm = (DynaActionForm) form;
			resourceForm.set("resourceId", resourceId);
			resourceForm.set("mode", "addResource");

			// add property template values from metadata
			String resourceType = val.getResourceTypeId();

			if (resourceType != null) {
				List resourceProp = meta.getPropByType(resourceType,
						resourceId, "RESOURCE_PROP_ID");
				Iterator i = resourceProp.iterator();
				while (i.hasNext()) {
					Property p = (Property) i.next();
					acl.addResourceProp(resourceId, p);
				}
				request.setAttribute("resourceProp", resourceProp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			// request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("index");
		}

		return mapping.findForward("resource");
	}

	public ActionForward saveResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - saveResource...");
		ActionErrors err = new ActionErrors();

		try {
			diamelle.security.resource.ResourceValue val = getResourceValue(form);
			DynaActionForm f = (DynaActionForm) form;

			String mode = (String) f.get("mode");
			if ((mode != null) && (mode.equalsIgnoreCase("addResource"))) {
				if (val != null) {
					if (val.getResourceTypeId().equals("-1")) {
						err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.missingResourceType"));
						//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.missingResourceType"));
						saveErrors(request, err);
						init(mapping, form, request, response);
						return mapping.findForward("resource");
					} else 
						acl.addResource(val);
				}

			} else if ((mode != null) && (mode.equalsIgnoreCase("addRoot"))) {
				try {
				acl.addRoot(val);
				} catch (Exception e) {
					// ResourceTypeId finder fails
					err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.missingResourceType"));
					//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.missingResourceType"));					
					saveErrors(request, err);
					init(mapping, form, request, response);
					return mapping.findForward("resource");
				}

			} else {
				acl.saveResource(val);
			}

			// add metadata values if a new record
			String resourceType = val.getResourceTypeId();
			if ((resourceType != null) && (mode != null)) {
				if ((mode.equalsIgnoreCase("addResource"))
						|| (mode.equalsIgnoreCase("addRoot"))) {
					List resourceProp = meta.getPropByType(resourceType, val
							.getResourceId(), "RESOURCE_PROP_ID");
					Iterator i = resourceProp.iterator();
					while (i.hasNext()) {
						Property p = (Property) i.next();
						acl.addResourceProp(val.getResourceId(), p);
					}
					request.setAttribute("resourceProp", resourceProp);
				}
			}
			// do not want to add a new record if user presses Save
			f.set("mode", "editResource");

			// must set new resourceId generated
			f.set("resourceId", val.getResourceId());
			init(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
			// request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("resource");
		}
		return mapping.findForward("resource");

	}

	public ActionForward addProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - addProperty...");
		ActionErrors err = new ActionErrors();

		DynaActionForm resourceForm = (DynaActionForm) form;
		resourceForm.set("mode", "addprop");
		init(mapping, form, request, response);

		return mapping.findForward("resource");
	}

	public ActionForward editProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - editProperty...");

		DynaActionForm f = (DynaActionForm) form;

		f.set("mode", "editprop");

		String resourcePropId = request.getParameter("resourcePropId");
		Property prop = acl.getResourcePropById(resourcePropId);
		// set prop values in f
		if (prop != null) {
			f.set("resourcePropId", resourcePropId);
			f.set("metadataId", prop.getMetaDataId());
			f.set("propValue", prop.getValueAsString());
		}
		init(mapping, form, request, response);

		return mapping.findForward("resource");
	}

	public ActionForward saveProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - saveProperty....");

		DynaActionForm resourceForm = (DynaActionForm) form;
		String resourceId = (String) resourceForm.get("resourceId");

		String mode = (String) resourceForm.get("mode");
		System.out.println("mode: " + mode);

		Property prop = new PropertyImpl();
		prop.setParentKey(resourceId);

		// let system auto generate. Uncomment if we want user to be able to key
		// in

		prop.setMetaDataId((String) resourceForm.get("metadataId"));
		prop.setValue((String) resourceForm.get("propValue"));

		if ((mode != null) && (mode.equalsIgnoreCase("editprop"))) {

			// only for edit we need the propId since we do not want user
			// entered property
			prop.setId((String) resourceForm.get("resourcePropId"));
			acl.saveResourceProp(resourceId, prop);
		} else
			acl.addResourceProp(resourceId, prop);

		resourceForm.set("mode", "");

		init(mapping, form, request, response);

		return mapping.findForward("resource");
	}

	public ActionForward handleProperties(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String submit = (String) request.getParameter("submit");

		if ((submit != null) && (submit.equalsIgnoreCase("Delete")))
			return removeProperties(mapping, form, request, response);

		return saveProperties(mapping, form, request, response);

	}

	public ActionForward saveProperties(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("ResourceAction - saveProperties....");
		DynaActionForm resourceForm = (DynaActionForm) form;
		String resourceId = (String) resourceForm.get("resourceId");

		String[] attrib = request.getParameterValues("attributeValue");
		String[] propId = request.getParameterValues("propId");
		String[] metadata = request.getParameterValues("metadataId");

		int propLen = 0;
		if (propId != null)
			propLen = propId.length;
		for (int i = 0; i < propLen; i++) {
			// note that we actually should use ResourcePropValue instead of Property
			Property prop = new PropertyImpl();
			prop.setId(propId[i]);
			prop.setMetaDataId(metadata[i]);
			prop.setValue(attrib[i]);
			acl.saveResourceProp(resourceId, prop);
		}
		init(mapping, form, request, response);

		return mapping.findForward("resource");
	}

	public ActionForward removeProperties(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		System.out.println("ResourceAction - removeProperties....");
		DynaActionForm resourceForm = (DynaActionForm) form;
		String resourceId = (String) resourceForm.get("resourceId");

		String[] arr = request.getParameterValues("listResourcePropId");
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.println("removing resourcePropId " + arr[i]);
				acl.removeResourceProp(resourceId, arr[i]);
			}
		}
		init(mapping, form, request, response);

		return mapping.findForward("resource");
	}

	/**
	 * Fetches input data from the form and sets into value class
	 * 
	 * @param form -
	 *            ActionForm return void
	 */

	private diamelle.security.resource.ResourceValue getResourceValue(
			ActionForm form) {
		DynaActionForm f = (DynaActionForm) form;

		diamelle.security.resource.ResourceValue val = new diamelle.security.resource.ResourceValue();
		val.setResourceId((String) f.get("resourceId"));
		val.setCategoryId((String) f.get("categoryId"));
		val.setDescription((String) f.get("description"));
		val.setResourceTypeId((String) f.get("resourceTypeId"));
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
	 * 
	 * @param form -
	 *            ActionForm
	 * @param form -
	 *            ResourceValue return void
	 */
	private void setResourceValue(ActionForm form,
			diamelle.security.resource.ResourceValue val) {

		DynaActionForm resourceForm = (DynaActionForm) form;
		if (val != null) {
			resourceForm.set("resourceId", val.getResourceId());
			resourceForm.set("branchId", val.getBranchId());
			resourceForm.set("categoryId", val.getCategoryId());
			resourceForm.set("resourceParent", val.getResourceParent());
			resourceForm.set("description", val.getDescription());
			resourceForm.set("resourceTypeId", val.getResourceTypeId());
		}
	}

}