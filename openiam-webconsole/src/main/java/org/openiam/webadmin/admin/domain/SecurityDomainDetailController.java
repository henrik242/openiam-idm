package org.openiam.webadmin.admin.domain;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.service.LocationDataService;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyConstants;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;


public class SecurityDomainDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(SecurityDomainDetailController.class);
	protected SecurityDomainDataService secDomainService;
	protected PolicyDataService policyDataService;
 	protected String redirectView;
	
	
	public SecurityDomainDetailController() {
		super();
	}
	
	
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		List<Policy> passwordPolicyList = policyDataService.getAllPolicies(PolicyConstants.PASSWORD_POLICY);
		List<Policy> authPolicyList = policyDataService.getAllPolicies(PolicyConstants.AUTHENTICATION_POLICY);
		List<Policy> auditPolicyList = policyDataService.getAllPolicies(PolicyConstants.AUDIT_POLICY);
		
		Map model = new HashMap();
		model.put("passwordPolicyList", passwordPolicyList);
		model.put("authnPolicyList", authPolicyList);
		model.put("auditPolicyList", auditPolicyList);
		
		return model;
	}



	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {

		
		String domainId = request.getParameter("domainId");
		if (domainId == null) {
			return (new SecurityDomainCommand());
		}
		
		SecurityDomain domain = secDomainService.getSecurityDomain(domainId);		
		SecurityDomainCommand domainCmd = new SecurityDomainCommand();
		domainCmd.setDomain(domain);
	

		return domainCmd;


}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		SecurityDomainCommand domainCmd = (SecurityDomainCommand)command;
		SecurityDomain domain = domainCmd.getDomain();

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			secDomainService.removeSecurityDomainById(domainCmd.getDomain().getDomainId());
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Location has been successfully deleted.");
	        return mav;
		}else {	
			if (domain.getDomainId() == null || domain.getDomainId().length() == 0) {
				domain.setDomainId(null); 
				secDomainService.addSecurityDomain(domain);
			}else {
				secDomainService.updateSecurityDomain(domain);
			}
		}
		
		ModelAndView mav = new ModelAndView(new RedirectView(redirectView, true));
		mav.addObject("secDomainCmd", domainCmd);
		
		return mav;
	}


	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}
	


	



	

}
