package org.openiam.idm.srvc.pswd;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.pswd.service.PasswordService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class ChallengeResponseServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	ChallengeResponseService challengeService;

	static String id = null;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml"});
		challengeService = (ChallengeResponseService) ctx.getBean("challengeResponse");


	}

	@Test
	public void testGetQuestionList() {
		
	
		List<IdentityQuestion> questionList = challengeService.allQuestions();
		assertNotNull(questionList);
		
	}
	public void testGetGlobalQuestionList() {
		
		
		List<IdentityQuestion> questionList = challengeService.questionsByGroup("GLOBAL");
		assertNotNull(questionList);
		
	}
	
	}
