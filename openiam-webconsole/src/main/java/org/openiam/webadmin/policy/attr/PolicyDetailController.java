package org.openiam.webadmin.policy.attr;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.webadmin.util.AuditHelper;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.dto.PolicyDef;
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;
import org.springframework.web.servlet.view.RedirectView;


/**
 * Controller for the attribute policy form
 * @author suneet
 *
 */
public class PolicyDetailController extends CancellableFormController {




	private static final Log log = LogFactory.getLog(PolicyDetailController.class);

	protected PolicyDataService policyDataService;
    protected ResourceDataService resourceDataService;
	protected String redirectView;
    protected AuditHelper auditHelper;


	public PolicyDetailController() {
		super();
	}

        @Override
       protected ModelAndView onCancel(Object command) throws Exception {
           return new ModelAndView(new RedirectView(getCancelView(),true));
       }


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		PolicyDetailCommand policyCommand = new PolicyDetailCommand();
		
		
		// load the policy in the event of a view operation
		String policyId = request.getParameter("policyId");
		if (policyId != null) {
			Policy policy = policyDataService.getPolicy(policyId);
			Set<PolicyAttribute> policyAttrSet = policy.getPolicyAttributes();
			
			policyCommand.setPolicy(policy);
			policyCommand.setPolicyAttr(policyAttrSet);
			
			String policyDefId = policy.getPolicyDefId();
			if (policyDefId.equalsIgnoreCase("103")) {
				policyCommand.setPolicyDefName("AUTHENTICATION");
			}else if (policyDefId.equalsIgnoreCase("100")) {
				policyCommand.setPolicyDefName("PASSWORD");
			}
			request.setAttribute("policyDefId", policyDefId);

		}else {
			policyCommand = new PolicyDetailCommand();
			String policyDefId = request.getParameter("policyDefId");
			PolicyDef policyDef = policyDataService.getPolicyDefinition(policyDefId);
			Set<PolicyDefParam> paramSet = policyDef.getPolicyDefParams();
			if (paramSet != null) {
				Policy policy = new Policy();
				policy.setPolicyDefId(policyDefId);
				Set<PolicyAttribute> policyAttrSet = new HashSet<PolicyAttribute>();
				for ( PolicyDefParam defParam : paramSet) {
					PolicyAttribute attr = new PolicyAttribute();
					attr.setDefParamId(defParam.getDefParamId());
					attr.setName(defParam.getName());
					policyAttrSet.add(attr);
				
				}
				policyCommand.setPolicy(policy);
				policyCommand.setPolicyAttr(policyAttrSet);
				if (policyDefId.equalsIgnoreCase("103")) {
					policyCommand.setPolicyDefName("AUTHENTICATION");
				}else if (policyDefId.equalsIgnoreCase("100")) {
					policyCommand.setPolicyDefName("PASSWORD");
				}				
			}
			request.setAttribute("policyDefId", policyDefId);
			
		}
            // load the list of authentication respository
            List<Resource> resourceList = resourceDataService.getResourcesByType("AUTH_REPO");
            System.out.println("List of authentication repositories:" + resourceList);
            policyCommand.setResourceList(resourceList);


		return policyCommand;
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

        String userId = (String)request.getSession().getAttribute("userId");
		String domainId = (String)request.getSession().getAttribute("domainid");
		String login = (String)request.getSession().getAttribute("login");

	
		PolicyDetailCommand policyCommand = (PolicyDetailCommand)command;

		Policy policy = policyCommand.getPolicy();
		Set<PolicyAttribute> attrSet = policyCommand.getPolicyAttr();
		
		// Clean up the policy attribute ids
		if (attrSet != null) {
			for (PolicyAttribute a  :attrSet) {
				if (a.getPolicyAttrId() == null || a.getPolicyAttrId().length() ==0 ) {
					a.setPolicyAttrId(null);
					a.setPolicyId(null);
				}
					
			}
		}
		
		policy.setPolicyAttributes(attrSet);
		
		

		
		// check which button was clicked
		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {

			policyDataService.removePolicy(policy.getPolicyId());
		}else {		
			if (policy.getPolicyId() == null || policy.getPolicyId().length() ==0) {
				// new
				policy.setPolicyId(null);
				policyDataService.addPolicy(policy);


                auditHelper.addLog("CREATE", domainId,	login,
                        "WEBCONSOLE", userId, "0", "POLICY", policy.getName(),
                        null,   "SUCCESS", null,  null,
                        null, null, null,
                       policy.getName(), request.getRemoteHost());

			}else {
				// update
				policyDataService.updatePolicy(policy);

                 auditHelper.addLog("MODIFY", domainId,	login,
                        "WEBCONSOLE", userId, "0", "POLICY", policy.getName(),
                        null,   "SUCCESS", null,  null,
                        null, null, null,
                         policy.getName(), request.getRemoteHost());

			}
		}
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;

		
	}
	




	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }
}
