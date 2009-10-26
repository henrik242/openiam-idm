package org.openiam.selfsrvc.pswd;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.BaseObject;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.service.UserDataService;

/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class IdentityQuestionController extends SimpleFormController {


	//protected UserDataService userMgr;
	protected PasswordConfiguration configuration;
	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService;
	protected ChallengeResponseService challengeResponse;
		
	private static final Log log = LogFactory.getLog(IdentityQuestionController.class);
	
	public IdentityQuestionController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return super.referenceData(request);
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		
		IdentityQuestionCommand cmd = new IdentityQuestionCommand();
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		SecurityDomain domain = secDomainService.getSecurityDomain(configuration.getDefaultSecurityDomain());
		Policy passwordPolicy = policyDataService.getPolicy(domain.getPasswordPolicyId());
		
		// get the policies in place
		PolicyAttribute countAttr = passwordPolicy.getAttribute("QUEST_COUNT");
		PolicyAttribute questSrcAttr = passwordPolicy.getAttribute("QUEST_SRC");
		PolicyAttribute questListAttr = passwordPolicy.getAttribute("QUEST_LIST");
		
		log.debug("Question count=" + countAttr.getValue1());
		
		cmd.setQuestionCount(Integer.valueOf(countAttr.getValue1()));
		
		// check if answers to questions already exist for this user
		
		List<UserIdentityAnswer> answerList = challengeResponse.answersByUser(userId);
		if (answerList == null) {
			// needed for the UI
			answerList = prefillAnswerList(cmd.getQuestionCount());
		}else {
			setUpdateFlag(answerList);
		}
		log.debug("Size of answerlist=" + answerList.size());
		
		List<IdentityQuestion> questionList = challengeResponse.questionsByGroup(configuration.getDefaultChallengeResponseGroup());
		
		log.debug("question list size =" + questionList.size());
		
		cmd.setAnswerList( answerList);
		cmd.setQuestionList(questionList);
		
		return cmd;
	}
	
	private void setUpdateFlag(List<UserIdentityAnswer> answerList) {
		for (UserIdentityAnswer ans : answerList) {
			ans.setObjectState(BaseObject.UPDATE);
		}
	}
	
	private List<UserIdentityAnswer> prefillAnswerList(int questionCount) {
		List<UserIdentityAnswer> answerList = new ArrayList<UserIdentityAnswer>();
		for (int i=0; i < questionCount; i++) {
			UserIdentityAnswer ans = new UserIdentityAnswer();
			ans.setIdentityAnsId(String.valueOf(i));
			ans.setObjectState(BaseObject.NEW);
			answerList.add(ans);
		}
		return answerList;
	}

	

	


	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		log.info("onSubmit called.");
		
		System.out.println("submit called.");
		
		HttpSession session = request.getSession();
		IdentityQuestionCommand cmd = (IdentityQuestionCommand) command;
		List<UserIdentityAnswer> answerList = cmd.getAnswerList();

		cleanupAnswerList(answerList, (String)session.getAttribute("userId"));
		this.challengeResponse.saveAnswers(answerList);
		

				
		ModelAndView mav = new ModelAndView(getSuccessView());
		
		
		return mav;
	}


	private void cleanupAnswerList(List<UserIdentityAnswer> answerList, String userId) {
		
		
		  for (UserIdentityAnswer ans : answerList) {
			 ans.setUserId(userId);
			 if (ans.getObjectState().equalsIgnoreCase(BaseObject.NEW)) {
				 // let hibernate generate the UUID.
				 ans.setIdentityAnsId(null);
			 }
			 // set the question text with the answer. Will need it for the challenge response 
			 IdentityQuestion question = challengeResponse.getQuestion(ans.getIdentityQuestionId());
			 if (question != null) {
				 ans.setQuestionText(question.getQuestionText());
			 }
			 
		}
		
	}


	public PasswordConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}


	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}


	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}


	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}


	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}


	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
	}


}
