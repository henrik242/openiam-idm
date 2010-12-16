package org.openiam.webadmin.sync;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;


/**
 * Displays a list of locations.
 * @author suneet
 *
 */
public class SynchConfigListController extends AbstractController {


	protected static final Log log = LogFactory.getLog(SynchConfigListController.class);
	protected String successView;
	
	protected IdentitySynchWebService synchConfig;


	
	public SynchConfigListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<SynchConfig> configList = synchConfig.getAllConfig().getConfigList();
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("configList", configList);
		
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public IdentitySynchWebService getSynchConfig() {
		return synchConfig;
	}

	public void setSynchConfig(IdentitySynchWebService synchConfig) {
		this.synchConfig = synchConfig;
	}

	

}
