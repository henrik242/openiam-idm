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
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import org.springframework.web.struts.DispatchActionSupport;

import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.dto.PolicyConstants;
import org.openiam.idm.srvc.policy.dto.PolicyDef;
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

public class PolicyAction extends DispatchActionSupport {

	protected SecurityDomainDataService secDomainService;
	protected PolicyDataService policyDS;
	

	private static final String GROUP = "Group";

	private static final Log log = LogFactory.getLog(PolicyAction.class);
	
	/*
	 * Description: Populate the intial screen so that the user can select a
	 * policy type to view a list of policies.
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.info("In Policy init...");
		ActionErrors errors = new ActionErrors();
		
		HttpSession session = request.getSession(); 
		session.removeAttribute("sideMenus");
		session.removeAttribute("categories");
		session.removeAttribute("menus");
		session.removeAttribute("topLevelMenus");
		
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

		log.info("Policy search is called.. ");
		//List policyList = null;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		DynaActionForm dynForm = (DynaActionForm) form;
		String policyDefId = (String) dynForm.get("policyDefId");
		
		log.info("Policy DefId = " + policyDefId);
		log.info("PolicyDS=" + policyDS);
		session.setAttribute("policyDefId", policyDefId);

		try {
		 	List<Policy> policyList = policyDS.getAllPolicies(policyDefId); 
			request.setAttribute("policies", policyList);

			init(mapping, form, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}


		return mapping.findForward("policies");
	}

	public ActionForward removePolicies(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		String[] arr = request.getParameterValues("policyId");
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				policyDS.removePolicy(arr[i]);
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
			if (policyDefId == null) {
				// policy def was not selected. notify the user
				String msg = "Please select a policy type";
				request.setAttribute("msg", msg);
				init(mapping, form, request, response);
				return mapping.findForward("policies");
			}
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
			Policy val = getPolicyValue(f);

			// from auth filter
			String uid = (String) session.getAttribute("userId");
			if (uid != null) {
				val.setLastUpdatedBy(uid);
			}
			String policyId = (String) f.get("policyId");

			if ((policyId != null) && (policyId.length() > 0)) {
				
				// leave policyDefId, create fields unchanged from old value
				Policy modVal = policyDS.getPolicy(policyId);
				modVal.setDescription(val.getDescription());
				modVal.setName(val.getName());
				modVal.setLastUpdatedBy(val.getLastUpdatedBy());
				modVal.setLastUpdate(val.getLastUpdate());
				policyDS.updatePolicy(modVal);

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
					policyDS.addPolicy(val);
					policyId = val.getPolicyId();
					f.set("policyId", policyId); // rules method will need this

					// insert policy definition as attributes for this policy:
					PolicyDef policyDef = policyDS.getPolicyDefinition(val.getPolicyDefId());
					Set<PolicyDefParam> defParams = policyDef.getPolicyDefParams();
					Set<PolicyAttribute> attrSet = val.getPolicyAttributes();
					
					Iterator<PolicyDefParam> it = defParams.iterator();
					while (it.hasNext()) {
						PolicyDefParam defParam = it.next();
						PolicyAttribute attr = new PolicyAttribute();
						attr.setName(defParam.getName());
						attr.setOperation(defParam.getOperation());
	
	//					attr.setPolicyDefParam(defParam);
	//					attr.setPolicy(val);

						attr.setValue1(defParam.getValue1());
						attr.setValue2(defParam.getValue2());
						
						attrSet.add(attr);
					}
					val.setPolicyAttributes(attrSet);
					policyDS.updatePolicy(val);
				}
			}
			policy(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.databaseselect"));
			//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ejb"));
		}

		return mapping.findForward("policy");
	}

	public ActionForward policy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		try {
			
			session.setAttribute("domainList", secDomainService.getAllDomainsWithExclude("IDM"));		
			DynaActionForm f = (DynaActionForm) form;

			String policyId = (String) f.get("policyId");
			Policy policyVal = policyDS.getPolicy(policyId);
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
		session.setAttribute("domainList", secDomainService.getAllDomainsWithExclude("IDM"));	

		try {
			DynaActionForm f = (DynaActionForm) form;

			String policyId = (String) f.get("policyId");
			Policy policyVal = policyDS.getPolicy(policyId);
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
			Set<PolicyAttribute> attrSet = policyVal.getPolicyAttributes();
			//List policyAttr = policyAccess.getPolicyAttributes(policyId);

			if (attrSet != null) {
				Iterator<PolicyAttribute> it = attrSet.iterator();
				while (it.hasNext()) {
					PolicyAttribute val = it.next();
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
			Policy policy = policyDS.getPolicy(policyId);
			Set<PolicyAttribute> attrSet = policy.getPolicyAttributes();
			//List policyAttr = policyAccess.getPolicyAttributes(policyId);

			Iterator<PolicyAttribute> it = attrSet.iterator();
			while (it.hasNext()) {
				PolicyAttribute val = it.next();

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
				attrSet.add(val);
			}
			policyDS.updatePolicy(policy);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		return null;
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
	private void populatePolicyForm(Policy val, DynaActionForm form) {
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
	}

	private Policy getPolicyValue(DynaActionForm f) {
		Policy val = new Policy();

		if (f != null) {
			Calendar calendar = new GregorianCalendar();
			java.sql.Date date = new java.sql.Date(calendar.getTimeInMillis());
			
			val.setCreatedBy((String) f.get("createdBy"));
			val.setDescription((String) f.get("description"));

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

		PolicyDef[] defAry = policyDS.getAllPolicyDef();
		if (defAry == null) {
			return labels;
		}
		int size = defAry.length;
		for (int i=0; i<size; i++) {
			labels.add(new LabelValueBean(defAry[i].getName(), defAry[i].getPolicyDefId()));
		}


		return labels;
	}


	private List initTabOptions(String activeTab, String policyDefId) {

		List l = new ArrayList();

	/*	TabOption option = new TabOption("Policy", false,
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
		}
	*/
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

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public PolicyDataService getPolicyDS() {
		return policyDS;
	}

	public void setPolicyDS(PolicyDataService policyDS) {
		this.policyDS = policyDS;
	}



}