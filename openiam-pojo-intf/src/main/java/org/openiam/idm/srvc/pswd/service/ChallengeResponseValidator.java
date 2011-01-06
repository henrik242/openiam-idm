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

import org.openiam.idm.srvc.pswd.dto.ChallengeResponseUser;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;

/**
 * Challenge response interface that allows extension of the challenge response model to be outside the OpenIAM repository
 * @author suneet
 *
 */
public interface ChallengeResponseValidator {

	/**
	 * Returns the list of questions based on the parameters specified in the ChallengeResponseUser object.
	 * @param req
	 * @return
	 */
	List<IdentityQuestion> getQuestions(ChallengeResponseUser req);
	
	/**
	 * Retrives a specific question that is identified by the question id.
	 * @param questionId
	 * @return
	 */
	IdentityQuestion getQuestion(String questionId);
	
	/*
	 * Checks if the user response is valid
	 */
	boolean isResponseValid(ChallengeResponseUser req, List<UserIdentityAnswer> newAnswerList);
	
	boolean isResponseValid(ChallengeResponseUser req, List<UserIdentityAnswer> newAnswerList, int requiredCorrectAns);
	
	/**
	 * Saves the answers that a user submits for a set of questions.
	 * @param ansList
	 */
	void saveAnswers(List<UserIdentityAnswer> ansList );
	
	/**
	 * returns the answers to questions that a user previously provided.
	 * @param userId
	 * @return
	 */
	List<UserIdentityAnswer> answersByUser(String userId);
	
}
