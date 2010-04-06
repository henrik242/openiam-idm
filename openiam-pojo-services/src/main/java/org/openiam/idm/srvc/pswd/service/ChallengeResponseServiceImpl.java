/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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

/**
 * 
 */
package org.openiam.idm.srvc.pswd.service;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.BaseObject;
import org.openiam.exception.data.IdentityAnswerNotFoundException;
import org.openiam.exception.data.PrincipalNotFoundException;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.util.encrypt.Cryptor;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.pswd.service.ChallengeResponseService", 
		targetNamespace = "urn:idm.openiam.org/srvc/pswd/service", 
		portName = "ChallengeResponseWebServicePort",
		serviceName = "ChallengeResponseWebService")
public class ChallengeResponseServiceImpl implements ChallengeResponseService {

	protected Cryptor cryptor;
	protected PolicyDataService policyDataService;
	protected IdentityQuestionDAO identityQuestDao;
	protected UserIdentityAnswerDAO identityAnswerDao;
	protected IdentityQuestGroupDAO identityQuestGroupDao;
	protected LoginDataService loginManager;
	
	private static final Log log = LogFactory.getLog(ChallengeResponseServiceImpl.class);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseService#getAllQuestions()
	 */
	public List<IdentityQuestion> allQuestions() {
		return identityQuestDao.findAllQuestions();
	}


	public List<IdentityQuestion> questionsByUser(String userId) {
		if (userId == null) {
			throw new NullPointerException("UserId is null");
		}
		return identityQuestDao.findAllQuestionsByUser(userId);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseService#getQuestionsByGroup(java.lang.String)
	 */
	public List<IdentityQuestion> questionsByGroup(String group) {
		if (group == null) {
			throw new NullPointerException("group is null");
		}
		return identityQuestDao.findAllQuestionsByQuestionGroup(group);
	}
	
 	public IdentityQuestion getQuestion(String questionId) {
 		if (questionId == null) {
			throw new NullPointerException("questionId is null");
		}
		return identityQuestDao.findById(questionId);
 	}
 	
 	public IdentityQuestion addQuestion(IdentityQuestion question) {
 		if (question == null) {
			throw new NullPointerException("question is null");
		}
		return identityQuestDao.add(question);
 	}
 	
 	public IdentityQuestion updateQuestion(IdentityQuestion question) {
 		if (question == null) {
			throw new NullPointerException("question is null");
		}
		return identityQuestDao.update(question);
 	}
 	
 	public void removeQuestion(String questionId) {
 		if (questionId == null) {
			throw new NullPointerException("question is null");
		}
 		IdentityQuestion question = new IdentityQuestion();
 		question.setIdentityQuestionId(questionId);
		identityQuestDao.remove(question);
 	}
 	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseService#AnswersByUser(java.lang.String)
	 */
	public List<UserIdentityAnswer> answersByUser(String userId) {
		if (userId == null) {
			throw new NullPointerException("UserId is null");
		}
		return this.identityAnswerDao.findAnswersByUser(userId);
	}
	
	public boolean userAnserExists(String userId) {
		List<UserIdentityAnswer> answerList = answersByUser(userId);
		if (answerList == null || answerList.isEmpty()) {
			return false;
		}
		return true;
	}
	
 	public UserIdentityAnswer addAnswer(UserIdentityAnswer answer) {
 		if (answer == null) {
 			throw new NullPointerException("Answer object is null");
 		}
 		return identityAnswerDao.add(answer);
 		
 	}
 	public UserIdentityAnswer updateAnswer(UserIdentityAnswer answer) {
 		if (answer == null) {
 			throw new NullPointerException("Answer object is null");
 		}
 		return identityAnswerDao.update(answer);		
 	}
 	public UserIdentityAnswer getAnswer(String answerId) {
 		if (answerId == null) {
 			throw new NullPointerException("answerId object is null");
 		}
 		return identityAnswerDao.findById(answerId);	 		
 	}
 	public void removeAnswer(String answerId) {
 		if (answerId == null) {
 			throw new NullPointerException("answerId object is null");
 		}
 		UserIdentityAnswer answer = new UserIdentityAnswer();
 		answer.setIdentityAnsId(answerId);
 		identityAnswerDao.delete(answer);		
 	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseService#addAnswers(java.util.List)
	 */
	public void addAnswers(List<UserIdentityAnswer> ansList) {
		if (ansList == null) {
			throw new NullPointerException("anslist is null");
		}
		for (UserIdentityAnswer ans : ansList) {
			addAnswer(ans);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseService#isResponseValid(java.lang.String, java.lang.String, java.lang.String, java.util.List)
	 */
	public boolean isResponseValid(String domainId, String login, String managedSysId,
			String questGrpId, List<UserIdentityAnswer> newAnswerList) {
		
		/*Validate that there are no null responses
		 
		 */
		
		Login lg =loginManager.getLoginByManagedSys(domainId, login, managedSysId);
		if (lg == null) {
			throw new PrincipalNotFoundException("Login object not found for login=" + login);
		}
		// get the answers in the system to validate the response.
		List<UserIdentityAnswer> savedAnsList = answersByUser(lg.getUserId());
		if (savedAnsList == null || savedAnsList.isEmpty()) {
			throw new IdentityAnswerNotFoundException();
		}
		log.debug("Vaidating responses");
		for (UserIdentityAnswer savedAns : savedAnsList) {
			for (UserIdentityAnswer newAns : newAnswerList) {
				if (newAns.getIdentityAnsId().equals(savedAns.getIdentityAnsId())) {
					log.debug("User answer for:" + newAns.getIdentityAnsId());
					if (newAns.getQuestionAnswer() == null || newAns.getQuestionAnswer().length() == 0) {
						return false;
					}
					if (!newAns.getQuestionAnswer().equalsIgnoreCase(savedAns.getQuestionAnswer())) {
						return false;
					}
				}
			}
		}
		return true;

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseService#saveAnswers(java.util.List)
	 */
	public void saveAnswers(List<UserIdentityAnswer> ansList) {
		if (ansList == null) {
			throw new NullPointerException("anslist is null");
		}
		for (UserIdentityAnswer ans : ansList) {
			// check if the answer exists
			if (ans.getObjectState().equalsIgnoreCase(BaseObject.NEW)) {
				addAnswer(ans);
			}else {
				updateAnswer(ans);
			}
		}
		
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}

	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public IdentityQuestionDAO getIdentityQuestDao() {
		return identityQuestDao;
	}


	public void setIdentityQuestDao(IdentityQuestionDAO identityQuestDao) {
		this.identityQuestDao = identityQuestDao;
	}


	public UserIdentityAnswerDAO getIdentityAnswerDao() {
		return identityAnswerDao;
	}


	public void setIdentityAnswerDao(UserIdentityAnswerDAO identityAnswerDao) {
		this.identityAnswerDao = identityAnswerDao;
	}


	public IdentityQuestGroupDAO getIdentityQuestGroupDao() {
		return identityQuestGroupDao;
	}


	public void setIdentityQuestGroupDao(IdentityQuestGroupDAO identityQuestGroupDao) {
		this.identityQuestGroupDao = identityQuestGroupDao;
	}


	public LoginDataService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}



}
