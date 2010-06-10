package org.openiam.webadmin.sync;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 * Displays a list of locations.
 * @author suneet
 *
 */
public class SynchConfigListController extends AbstractController {


	protected static final Log log = LogFactory.getLog(SynchConfigListController.class);
	protected String successView;


	
	public SynchConfigListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
/*		mav.addObject("domainAry", domainAry);
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			mav.addObject("msg", "Your information has been sucessfully saved.");
		}
*/		
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	

}
