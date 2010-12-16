package org.openiam.webadmin.admin.domain;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;


/**
 * Displays a list of locations.
 * @author suneet
 *
 */
public class SecurityDomainListController extends AbstractController {


	protected static final Log log = LogFactory.getLog(SecurityDomainListController.class);
	protected String successView;
	protected SecurityDomainDataService secDomainService;
	
	

	
	public SecurityDomainListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SecurityDomain[] domainAry = secDomainService.getAllSecurityDomains();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("domainAry", domainAry);
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			mav.addObject("msg", "Your information has been sucessfully saved.");
		}
		
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	

}
