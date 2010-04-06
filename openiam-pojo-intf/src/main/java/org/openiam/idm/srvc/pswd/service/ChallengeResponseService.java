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
 *   GNU General Public License for more details.
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

import org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;


/**
 * ChallengeResponseService provides operations to manage the challenge response functionality that is needed by the self service application
 * 
 * @author Suneet Shah
 *
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/pswd/service", name = "ChallengeResponseWebService")
public interface ChallengeResponseService {
	/**
 	 * Returns a list of questions that are linked to a user. Questions are linked to a user 
 	 * when the policy allows the user define their own challenge response questions.
 	 * for the companyId.
 	 * 
 	 * @param userId
 	 * @return
 	 */
	List<IdentityQuestion> questionsByUser(String userId);
 	/**
 	 * Returns a list of questions that are linked to a question group. If the system
 	 * is configured for just 1 set of questions, then enter the word "GLOBAL" 
 	 * for the companyId.
 	 * 
 	 * @param groupId
 	 * @return
 	 * @throws RemoteException
 	 */
	List<IdentityQuestion> questionsByGroup(String groupId);

	/**
	 * Gets all the answers that a users has provided for their identity questions.
	 * @param userId
	 * @return
	 */
 	List<UserIdentityAnswer> answersByUser(String userId);
 	
 	/**
 	 * Determines if a user has answered their questions.
 	 * @param userId
 	 * @return
 	 */
 	boolean userAnserExists(String userId);
 	/**
 	 * Gets all the questions in the question bank
 	 * @return
 	 */
 	List<IdentityQuestion> allQuestions();
 	/**
 	 * Returns a questions specified by the questionId
 	 * @param questionId
 	 * @return
 	 */
 	IdentityQuestion getQuestion(String questionId);
 	
 	/**
 	 * Adds a new question to the question bank
 	 * @param question
 	 * @return
 	 */
 	IdentityQuestion addQuestion(IdentityQuestion question);
 	
 	/**
 	 * Updates an existing question
 	 * @param question
 	 * @return
 	 */
 	IdentityQuestion updateQuestion(IdentityQuestion question);
 	
 	/**
 	 * Removes a question from the question bank
 	 * @param questionId
 	 * @return
 	 */
 	void removeQuestion(String questionId);
 	
 	/**
 	 * Adds an answer to the list of answers for the challenge response questions for a user
 	 * @param answer
 	 * @return
 	 */
 	UserIdentityAnswer addAnswer(UserIdentityAnswer answer);
 	/**
 	 * Update an answer to the list of answers for the challenge response questions for a user
 	 * @param answer
 	 * @return
 	 */
 	UserIdentityAnswer updateAnswer(UserIdentityAnswer answer);
 	/**
 	 * Returns a UserIdentityAnswer object for the specified answerId. The UserIdentityAnswer object contains the users
 	 * answer for a challenge response question
 	 * @param answerId
 	 * @return
 	 */
 	UserIdentityAnswer getAnswer(String answerId);
 	/**
 	 * Removes an answer from the list of answers that are used for challenge response functioanlity
 	 * @param answerId
 	 */
 	void removeAnswer(String answerId);
 	
 	/**
 	 * Adds the answers in the ansList. The list contains QuestionValue objects.
 	 * @param ansList
 	 * @throws RemoteException
 	 */
 	void addAnswers(List<UserIdentityAnswer> ansList);
 	/**
 	 * Updates the answers contains in the ansList. The list contains QuestionValue objects.
 	 * @param ansList
 	 * @throws RemoteException
 	 */
 	void saveAnswers(List<UserIdentityAnswer> ansList);
 	
 	/**
 	 * Determines is the answers that are provided by the user are the same as those stored in
 	 * the system.
 	 * @param login - login of which we want to validate the questions.
 	 * @param answerList - List of QuestionValue objects
 	 * @return
 	 * @throws RemoteException
 	 */
 	boolean isResponseValid(String domainId, String login, String managedSysId, String questGrpId,  List<UserIdentityAnswer> answerList);
	
}
