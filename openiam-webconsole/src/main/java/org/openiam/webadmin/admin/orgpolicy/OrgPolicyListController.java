package org.openiam.webadmin.admin.orgpolicy;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.ws.OrgPolicyWebService;



public class OrgPolicyListController extends AbstractController {


	private static final Log log = LogFactory.getLog(OrgPolicyListController.class);
	private String successView;
	private OrgPolicyWebService orgPolicyService; 

	
	
	public OrgPolicyListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		List<OrgPolicy> orgPolicyList = orgPolicyService.getAllPolicyMessages().getPolicyAcceptanceList();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("policyList", orgPolicyList);
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public OrgPolicyWebService getOrgPolicyService() {
		return orgPolicyService;
	}

	public void setOrgPolicyService(OrgPolicyWebService orgPolicyService) {
		this.orgPolicyService = orgPolicyService;
	}

	

}
