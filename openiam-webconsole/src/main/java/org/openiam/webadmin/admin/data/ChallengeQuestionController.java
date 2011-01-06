package org.openiam.webadmin.admin.data;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;



public class ChallengeQuestionController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ChallengeQuestionController.class);
 	protected String redirectView;
	 protected ChallengeResponseService challengeResponse;
	
	
	public ChallengeQuestionController() {
		super();
	}
	
	
	

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {

		
		String questId = request.getParameter("questId");
		if (questId == null) {
			return (new ChallengeQuestionCommand());
		}
		
		IdentityQuestion idQuest = challengeResponse.getQuestion(questId);
			
		ChallengeQuestionCommand questionCmd = new ChallengeQuestionCommand();
		questionCmd.setIdQuest(idQuest);
	

		return questionCmd;


}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		IdentityQuestGroup idQuestGroup = new IdentityQuestGroup("GLOBAL");
		
		ChallengeQuestionCommand questionCmd = (ChallengeQuestionCommand)command;
		IdentityQuestion idQuest = questionCmd.getIdQuest();

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			challengeResponse.removeQuestion(questionCmd.getIdQuest().getIdentityQuestionId());
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Question has been successfully deleted.");
	        return mav;
		}else {	
			if ( idQuest.getIdentityQuestionId() == null || idQuest.getIdentityQuestionId().length() == 0) {
				idQuest.setIdentityQuestionId(null);
				idQuest.setIdentityQuestGrp(idQuestGroup);
				challengeResponse.addQuestion(idQuest);
			} else {
				idQuest.setIdentityQuestGrp(idQuestGroup);
				challengeResponse.updateQuestion(idQuest);
			}
		
		}
		
		ModelAndView mav = new ModelAndView(new RedirectView(redirectView, true));
		mav.addObject("questionCmd", questionCmd);
		
		return mav;
	}


	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}



	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}



	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
	}



	



	

}
