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
package org.openiam.idm.srvc.pswd.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Domain object representing an answer by the user for a challenge response question.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserIdentityAnswer", propOrder = { 
		"identityAnsId",
		"identityQuestionId",
		"questionText",
		"userId",
		"questionAnswer"
	})
public class UserIdentityAnswer extends org.openiam.base.BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841064146448209034L;
	protected String identityAnsId;
	protected String identityQuestionId;
	protected String questionText;
	protected String userId;
	protected String questionAnswer;

	public UserIdentityAnswer() {
	}

	public UserIdentityAnswer(String identityAnsId) {
		this.identityAnsId = identityAnsId;
	}

	public UserIdentityAnswer(String identityAnsId,
			String questionText,
			String userId, String questionAnswer) {
		this.identityAnsId = identityAnsId;
		this.questionText = questionText;
		this.userId = userId;
		this.questionAnswer = questionAnswer;
	}

	public String getIdentityAnsId() {
		return this.identityAnsId;
	}

	public void setIdentityAnsId(String identityAnsId) {
		this.identityAnsId = identityAnsId;
	}


	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestionAnswer() {
		return this.questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getIdentityQuestionId() {
		return identityQuestionId;
	}

	public void setIdentityQuestionId(String identityQuestionId) {
		this.identityQuestionId = identityQuestionId;
	}

}
