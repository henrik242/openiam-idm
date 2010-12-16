package org.openiam.webadmin.admin.data;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;


/**
 * Displays a list of locations.
 * @author suneet
 *
 */
public class ChallengeQuestionListController extends AbstractController {


	protected static final Log log = LogFactory.getLog(ChallengeQuestionListController.class);
	protected String successView;
	 protected ChallengeResponseService challengeResponse;
	
	

	
	public ChallengeQuestionListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<IdentityQuestion> questList =  challengeResponse.allQuestions();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("questList", questList);
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

	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}

	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
	}


}
