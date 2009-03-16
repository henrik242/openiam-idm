/**
 * ------------------------------------------------------------------------------
 * Title: PolicyAction Author: APS 04-09-2004 Overview:Handles all functions
 * like adding , updating, deleting and viewing Policies for available Services.
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

import java.io.*;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;

import org.openiam.webadmin.busdel.base.*;
//import diamelle.security.auth.GroupValue;
//import diamelle.security.auth.RoleValue;
import diamelle.security.policy.*;
//import diamelle.security.auth.*;
import diamelle.security.resource.*;
import org.openiam.webadmin.busdel.security.*;
import org.springframework.web.context.WebApplicationContext;

import diamelle.common.meta.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.meta.service.MetadataService;
import org.openiam.idm.srvc.policy.dto.PolicyConstants;
import org.openiam.idm.srvc.policy.dto.PolicyDef;
import org.openiam.idm.srvc.policy.service.PolicyDataService;

import org.apache.struts.action.*;
import org.apache.struts.util.*;
import org.apache.struts.validator.*;

public class PolicyAction extends NavigationDispatchAction {

	private PolicyAccess policyAccess = new PolicyAccess();

	private MetadataAccess meta = new MetadataAccess();

	//private ServiceAccess serviceAccess = new ServiceAccess();

	private SecurityAccess securityAccess = new SecurityAccess();
	SecurityDomainAccess secDomainAccess = null;
	PolicyDataService policyService = null;

	//private GroupAccess groupAccess = new GroupAccess();

	private static final String GROUP = "Group";

	/*
	 * Description: Populate the intial screen so that the user can select a
	 * policy type to view a list of policies.
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

System.out.println("In Policy init...");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		
		WebApplicationContext webCtx = getWebApplicationContext();
		policyService = (PolicyDataService)webCtx.getBean("policyDataService");

		try {

			List policyTypes = this.policyTypesToLabels();
			request.setAttribute("policyTypes", policyTypes);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		return mapping.findForward("policies");
	}

	public ActionForward searchDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		DynaActionForm f = (DynaActionForm) form;
		String submit = (String) f.get("submit");

		if ((submit != null) && (submit.length() > 0)) {
			if (submit.equalsIgnoreCase("Delete"))
				return removePolicies(mapping, form, request, response);
		}
		return search(mapping, form, request, response);
	}

	/*
	 * Description: Returns a list of polices for the selected service or group
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("Policy search is called.. ");
		List policyList = null;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		DynaActionForm dynForm = (DynaActionForm) form;
		String policyDefId = (String) dynForm.get("policyDefId");
		
		System.out.println("Policy DefId = " + policyDefId);
		session.setAttribute("policyDefId", policyDefId);

		try {
			policyList = policyAccess.getPolicyByType(policyDefId);
			request.setAttribute("policies", policyList);

			init(mapping, form, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		//if (!errors.isEmpty()) {
		//	saveErrors(request, errors);
		//}

		return mapping.findForward("policies");
	}

	public ActionForward removePolicies(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		String[] arr = request.getParameterValues("policyId");
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				policyAccess.removePolicy(arr[i]);
			}
		}
		search(mapping, form, request, response);

		return mapping.findForward("policies");
	}

	public ActionForward newPolicy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors err = new ActionErrors();

		try {
			
			HttpSession session = request.getSession(true);
			String policyDefId = (String)session.getAttribute("policyDefId");
			if (policyDefId.equals( PolicyConstants.ATTRIBUTE_POLICY )) {
				return mapping.findForward("attrPolicy");
			}

			request.setAttribute("tabOptions", initTabOptions("Policy", null));
			init(mapping, form, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
		}

		if (!err.isEmpty()) {
			saveErrors(request, err);
		}

		return mapping.findForward("policy");
	}

	public ActionForward savePolicy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors err = new ActionErrors();

		Locale locale = getLocale(request);
		HttpSession session = request.getSession();

		try {

			DynaActionForm f = (DynaActionForm) form;
			PolicyValue val = getPolicyValue(f);

			// from auth filter
			String uid = (String) session.getAttribute("userId");
			if (uid != null) {
				val.setLastUpdatedBy(uid);
			}
			String policyId = (String) f.get("policyId");

			if ((policyId != null) && (policyId.length() > 0)) {
				
				// leave policyDefId, create fields unchanged from old value
				PolicyValue modVal = policyAccess.getPolicy(policyId);
				modVal.setDescription(val.getDescription());
				modVal.setEnabled(val.isEnabled());
				modVal.setName(val.getName());
				modVal.setLastUpdatedBy(val.getLastUpdatedBy());
				modVal.setLastUpdate(val.getLastUpdate());
				policyAccess.savePolicy(modVal);

			} else {

				String policyDefId = (String) f.get("policyDefId");
				if ((policyDefId == null) || (policyDefId.equals("-1")))
					err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.missingPolicyDefId"));
					//err.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					//		"error.missingPolicyDefId"));
				else {


					if (uid != null) {
						val.setCreatedBy(uid);
					}
					policyId = policyAccess.addPolicy(val);
					f.set("policyId", policyId); // rules method will need this

					// insert policy definition as attributes for this policy:
					Collection defParams = policyAccess.getPolicyDefParams(val
							.getPolicyDefId());
					Iterator it = defParams.iterator();
					while (it.hasNext()) {
						PolicyDefParamValue defParam = (PolicyDefParamValue) it
								.next();
						PolicyAttrValue attr = new PolicyAttrValue();
						attr.setName(defParam.getName());
						attr.setOperation(defParam.getOperation());
						attr.setPolicyDefParamId(defParam.getDefParamId());
						attr.setPolicyId(policyId);
						attr.setValue1(defParam.getValue1());
						attr.setValue2(defParam.getValue2());
						policyAccess.addPolicyAttribute(attr);
					}
				}
			}
			policy(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.databaseselect"));
			//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
		}

	//	if (!err.isEmpty()) {
	//		saveErrors(request, err);
	//		return mapping.findForward("newPolicy");
	//	}

		return mapping.findForward("policy");
	}

	public ActionForward policy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		try {
			
			secDomainAccess = new SecurityDomainAccess(getWebApplicationContext());	
			session.setAttribute("domainList", secDomainAccess.getAllDomainsWithExclude("IDM"));

			
			DynaActionForm f = (DynaActionForm) form;

			String policyId = (String) f.get("policyId");
			PolicyValue policyVal = policyAccess.getPolicy(policyId);
			request.setAttribute("policy", policyVal);
			populatePolicyForm(policyVal, f);

			String policyDefId = policyVal.getPolicyDefId();
			request.setAttribute("tabOptions", initTabOptions("Policy",
					policyDefId));

			List policyTypes = this.policyTypesToLabels();
			request.setAttribute("policyTypes", policyTypes);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		return mapping.findForward("policy");
	}

	public ActionForward rules(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		try {
			DynaActionForm f = (DynaActionForm) form;

			String policyId = (String) f.get("policyId");
			PolicyValue policyVal = policyAccess.getPolicy(policyId);
			request.setAttribute("policy", policyVal);

			String policyDefId = policyVal.getPolicyDefId();
			request.setAttribute("tabOptions", initTabOptions("Rules",
					policyDefId));

			//passwordPolicy(mapping, form, request, response);
			String submit = (String) f.get("submit");

			if ((submit != null) && (submit.length() > 0)) {
				if (submit.equalsIgnoreCase("Save"))
					saveRules(mapping, form, request, response);
			}

			//this.viewPasswordPolicy(mapping, form, request, response);
			List policyAttr = policyAccess.getPolicyAttributes(policyId);

			if (policyAttr != null) {
				Iterator it = policyAttr.iterator();
				while (it.hasNext()) {
					PolicyAttrValue val = (PolicyAttrValue) it.next();
					f.set(val.getName(), val.getValue1());
	
					if ((val.getOperation() != null)
							&& (val.getOperation().equalsIgnoreCase("RANGE"))) {
						f.set(val.getName() + "_MAX", val.getValue2());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		return mapping.findForward("policy");
	}

	/*
	 * public ActionForward viewPasswordPolicy(ActionMapping mapping, ActionForm
	 * form, HttpServletRequest request, HttpServletResponse response) throws
	 * IOException, ServletException {
	 * 
	 * ActionErrors errors = new ActionErrors();
	 * 
	 * try { DynaActionForm f = (DynaActionForm) form; String policyId =
	 * (String) f.get("policyId"); List policyAttr =
	 * policyAccess.getPolicyAttributes(policyId);
	 * 
	 * Iterator it = policyAttr.iterator(); while (it.hasNext()) {
	 * PolicyAttrValue val = (PolicyAttrValue) it.next(); f.set(val.getName(),
	 * val.getValue1());
	 * 
	 * if ((val.getOperation() != null) &&
	 * (val.getOperation().equalsIgnoreCase("RANGE"))) { f.set(val.getName() +
	 * "_MAX", val.getValue2()); } } } catch (Exception e) {
	 * e.printStackTrace(); errors.add(ActionErrors.GLOBAL_ERROR, new
	 * ActionError("error.ejb")); } if (!errors.isEmpty()) { saveErrors(request,
	 * errors); return mapping.findForward("failure"); } return
	 * mapping.findForward("policy"); }
	 */

	public ActionForward saveRules(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();

		try {
			DynaActionForm f = (DynaActionForm) form;
			String policyId = (String) f.get("policyId");
			List policyAttr = policyAccess.getPolicyAttributes(policyId);

			Iterator it = policyAttr.iterator();
			while (it.hasNext()) {
				PolicyAttrValue val = (PolicyAttrValue) it.next();

				String attrName = val.getName();
				String attrValue1 = request.getParameter(attrName);
				val.setValue1(attrValue1);

				if (val.getOperation() != null) {
					if (val.getOperation().equalsIgnoreCase("RANGE")) {
						String attrValue2 = request.getParameter(attrName
								+ "_MAX");
						val.setValue2(attrValue2);
					}
					if ((attrValue1 != null)
							&& (val.getOperation().equalsIgnoreCase("boolean"))) {
						if ((attrValue1.equalsIgnoreCase("on"))
								|| (attrValue1.equalsIgnoreCase("true")))
							val.setValue1("true");
						else
							val.setValue1("false");
					}
				}
				policyAccess.savePolicyAttributes(val);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		return null;
	}

	public ActionForward groups(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();

		try {

			DynaActionForm f = (DynaActionForm) form;
			String policyId = (String) f.get("policyId");
			PolicyValue policyVal = policyAccess.getPolicy(policyId);
			request.setAttribute("policy", policyVal);

			List members = policyAccess.getPolicyMembers(policyId);

			String submit = (String) f.get("submit");
			if (submit != null) {
				if (submit.equalsIgnoreCase("Add")) {
					String groupId = (String) f.get("groupId");
					this.addGroupMember(policyId, groupId, members, errors);
				}
				if (submit.equalsIgnoreCase("Delete")) {
					String[] arr = request.getParameterValues("policyMemberId");
					if (arr != null) {
						for (int i = 0; i < arr.length; i++) {
							policyAccess.removePolicyMember(arr[i]);
						}
					}
				}
				// refresh list:
				members = policyAccess.getPolicyMembers(policyId);
			}

			request.setAttribute("members", members);

			String policyDefId = policyVal.getPolicyDefId();
			request.setAttribute("tabOptions", initTabOptions("Groups",
					policyDefId));

			List groups = this.groupsToLabels();
			request.setAttribute("groups", groups);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		return mapping.findForward("policy");
	}

	private void addGroupMember(String policyId, String groupId, List members,
			ActionErrors errors) throws RemoteException, Exception {
		
	      GroupDataServiceAccess groupDataAcc = null;
      
	 	  WebApplicationContext webContext =  getWebApplicationContext();
		  groupDataAcc = new GroupDataServiceAccess(webContext);
		
		if ((groupId == null) || (groupId.equals("-1")))
			return;
		// check if this group is already a member
		if (members != null) {
			Iterator it = members.iterator();
			while (it.hasNext()) {
				PolicyMemberValue pmv = (PolicyMemberValue) it.next();
				if ((pmv.getObjectId() != null)
						&& (pmv.getObjectId().equals(groupId))) {
					if ((pmv.getObjectType() != null)
							&& (pmv.getObjectType().equals(GROUP))) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.duplicatePolicyMember"));
					//	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					//			"error.duplicatePolicyMember"));
						return;
					}
				}
			}
		}

		if (groupId != null) {
			//sets values for fields in group.jsp for editing
			Group groupValue = groupDataAcc.getGroup(groupId);

			PolicyMemberValue val = new PolicyMemberValue();
			val.setPolicyId(policyId);
			val.setObjectId(groupId);
			val.setObjectType(GROUP);
			if (groupValue != null)
				val.setServiceId(groupValue.getGrpName()); // rename this method
			// to
			// desc in the bean
			policyAccess.addPolicyMember(val);
		}

	}

	/*
	 * public ActionForward passwordPolicy(ActionMapping mapping, ActionForm
	 * form, HttpServletRequest request, HttpServletResponse response) throws
	 * IOException, ServletException {
	 * 
	 * DynaActionForm f = (DynaActionForm) form; String submit = (String)
	 * f.get("submit");
	 * 
	 * if ((submit != null) && (submit.length() > 0)) { if
	 * (submit.equalsIgnoreCase("Save")) savePasswordPolicy(mapping, form,
	 * request, response); } return viewPasswordPolicy(mapping, form, request,
	 * response); }
	 */

	// ---------- helper methods --------------
	private void populatePolicyForm(PolicyValue val, DynaActionForm form) {
		DateFormat df = new SimpleDateFormat("MM-DD-yyyy");

		if (val == null) {
			System.out.println("Val was null. ");
		}
		
		if (val.getCreateDate() != null) {
			form.set("createDate", df.format(val.getCreateDate()));
		} else
			form.set("createDate", df.format(Calendar.getInstance().getTime()));

		form.set("createdBy", val.getCreatedBy());
		form.set("description", val.getDescription());
		if (val.getLastUpdate() != null) {
			form.set("lstUpdate", df.format(val.getLastUpdate()));
		}
		form.set("lstUpdateBy", val.getLastUpdatedBy());
		form.set("policyName", val.getName());
		form.set("policyDefId", val.getPolicyDefId());
		form.set("policyId", val.getPolicyId());
		boolean enabled = val.isEnabled();
		if (enabled)
			form.set("enabled", "true");
		else
			form.set("enabled", "false");

	}

	private PolicyValue getPolicyValue(DynaActionForm f) {
		PolicyValue val = new PolicyValue();

		if (f != null) {
			Calendar calendar = new GregorianCalendar();
			java.sql.Date date = new java.sql.Date(calendar.getTimeInMillis());
			
			// policy bean will set default date for create
			//String s = (String) f.get("createDate");
			//if ((s != null) && (s.length() > 0)) {
			//	val.setCreateDate(Date.valueOf(s));
			//}

			val.setCreatedBy((String) f.get("createdBy"));
			val.setDescription((String) f.get("description"));
			val.setEnabled(false);
			String enabled = (String) f.get("enabled");
			if (enabled != null) {
				if ((enabled.equalsIgnoreCase("true"))
						|| (enabled.equalsIgnoreCase("on")))
					val.setEnabled(true);
			}

			val.setLastUpdate(date);

			val.setLastUpdatedBy((String) f.get("lstUpdateBy"));
			val.setName((String) f.get("policyName"));
			val.setPolicyDefId((String) f.get("policyDefId"));
			val.setPolicyId((String) f.get("policyId"));
		}
		return val;
	}

	// to build drop down in jsp page
	private List policyTypesToLabels() {
		List labels = new java.util.ArrayList();
		LabelValueBean emptybean = new LabelValueBean("<Select a value>", "-1");
		labels.add(emptybean);

		PolicyDef[] defAry = policyService.getAllPolicyDef();
		if (defAry == null) {
			return labels;
		}
		int size = defAry.length;
		for (int i=0; i<size; i++) {
			labels.add(new LabelValueBean(defAry[i].getName(), defAry[i].getPolicyDefId()));
		}


		return labels;
	}

	// to build drop down in jsp page
	private List groupsToLabels() {
		GroupDataServiceAccess groupDataAcc = null;		
		List labels = new java.util.ArrayList();
		
		WebApplicationContext webContext =  getWebApplicationContext();
		groupDataAcc = new GroupDataServiceAccess(webContext);
		
		LabelValueBean emptybean = new LabelValueBean("<Select a value>", "-1");
		labels.add(emptybean);
			//List groups = securityAccess.getAllGroups();
		List<Group> groups = groupDataAcc.getAllGroups(false);
		if (groups != null) {
			for (int i = 0; i < groups.size(); i++) {
				Group val = (Group) groups.get(i);
				LabelValueBean bean = new LabelValueBean(val.getGrpName(),val.getGrpId());
				labels.add(bean);
			}
		}
		return labels;
	}

	private List initTabOptions(String activeTab, String policyDefId) {

		List l = new ArrayList();

		TabOption option = new TabOption("Policy", false,
				"security/policy.do?method=policy", "/policy/policyhdr.jsp");
		if (activeTab.equalsIgnoreCase("Policy")) {
			option.setActive(true);
		}
		l.add(option);

		if (policyDefId != null) {

			option = new TabOption("Rules", false,
					"security/rulesPolicy.do?method=rules", getPolicyPage(policyDefId));
			if (activeTab.equalsIgnoreCase("Rules")) {
				option.setActive(true);
			}
			l.add(option);

			option = new TabOption("Groups", false,
					"security/groupPolicy.do?method=groups", "/policy/groupmembers.jsp");
			if (activeTab.equalsIgnoreCase("Groups")) {
				option.setActive(true);
			}
			l.add(option);

		}
		return l;
	}

	private String getPolicyPage(String policyDefId) {

		String activeJsp = "/policy/pswdpolicy.jsp";

		if (policyDefId.equalsIgnoreCase("100")) {
			activeJsp = "/policy/pswdpolicy.jsp";
		}
		if (policyDefId.equalsIgnoreCase("101")) {
			activeJsp = "";
		}
		if (policyDefId.equalsIgnoreCase("102")) {
			activeJsp = "/policy/auditpolicy.jsp";
		}
		if (policyDefId.equalsIgnoreCase("103")) {
			activeJsp = "/policy/accountpolicy.jsp";
		}

		return activeJsp;

	}

}