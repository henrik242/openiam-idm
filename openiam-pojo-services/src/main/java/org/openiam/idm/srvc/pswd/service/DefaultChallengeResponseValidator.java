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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.BaseObject;
import org.openiam.exception.data.IdentityAnswerNotFoundException;
import org.openiam.exception.data.PrincipalNotFoundException;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.pswd.dto.ChallengeResponseUser;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.util.encrypt.Cryptor;

/**
 * Default implementation of the challenge response validator. This implementation uses the information stored in the OpenIAM repository
 * @author suneet
 *
 */
public class DefaultChallengeResponseValidator implements
		ChallengeResponseValidator {

	protected IdentityQuestionDAO identityQuestDao;
	protected UserIdentityAnswerDAO identityAnswerDao;
	protected Cryptor cryptor;
	protected LoginDataService loginManager;
	
	private static final Log log = LogFactory.getLog(DefaultChallengeResponseValidator.class);
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseValidator#getQuestion(org.openiam.idm.srvc.pswd.dto.ChallengeResponseUser)
	 */
	public List<IdentityQuestion> getQuestions(ChallengeResponseUser req) {
		if (req == null) {
			return identityQuestDao.findAllQuestions();
		}
		if (req.getQuestionGroup() != null) {
			return identityQuestDao.findAllQuestionsByQuestionGroup(req.getQuestionGroup());
		}
		return null;
	}
	
	public  IdentityQuestion getQuestion(String questionId) {
		return identityQuestDao.findById(questionId);
	}
	
	public boolean isResponseValid(ChallengeResponseUser req, List<UserIdentityAnswer> newAnswerList, int requiredCorrectAns) {
		
		int correctAns = 0;
		
		Login lg =loginManager.getLoginByManagedSys(req.getDomain(),
				req.getPrincipal(),
				req.getManagedSysId());
		
		if (lg == null) {
			throw new PrincipalNotFoundException("Login object not found for login=" + req.getPrincipal());
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
						//return false;
					}else if (!newAns.getQuestionAnswer().equalsIgnoreCase(savedAns.getQuestionAnswer())) {
						// false;
					}else {
						correctAns++;
					}
				}
			}
		}
		if (correctAns == requiredCorrectAns) {
			return true;
		}
		return false;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseValidator#isResponseValid(org.openiam.idm.srvc.pswd.dto.ChallengeResponseUser, java.util.List)
	 */
	public boolean isResponseValid(ChallengeResponseUser req,
			List<UserIdentityAnswer> newAnswerList) {
		
		Login lg =loginManager.getLoginByManagedSys(req.getDomain(),
				req.getPrincipal(),
				req.getManagedSysId());
		
		if (lg == null) {
			throw new PrincipalNotFoundException("Login object not found for login=" + req.getPrincipal());
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
	 * @see org.openiam.idm.srvc.pswd.service.ChallengeResponseValidator#saveAnswers(java.util.List)
	 */
	public void saveAnswers(List<UserIdentityAnswer> ansList) {
		for (UserIdentityAnswer ans : ansList) {
			// check if the answer exists
			if (ans.getObjectState().equalsIgnoreCase(BaseObject.NEW)) {
				addAnswer(ans);
			}else {
				updateAnswer(ans);
			}
		}

	}

	public List<UserIdentityAnswer> answersByUser(String userId) {
		if (userId == null) {
			throw new NullPointerException("UserId is null");
		}
		return this.identityAnswerDao.findAnswersByUser(userId);
	}
	
 	private UserIdentityAnswer addAnswer(UserIdentityAnswer answer) {
 		if (answer == null) {
 			throw new NullPointerException("Answer object is null");
 		}
 		return identityAnswerDao.add(answer);
 		
 	}
 	private UserIdentityAnswer updateAnswer(UserIdentityAnswer answer) {
 		if (answer == null) {
 			throw new NullPointerException("Answer object is null");
 		}
 		return identityAnswerDao.update(answer);		
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

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

}
