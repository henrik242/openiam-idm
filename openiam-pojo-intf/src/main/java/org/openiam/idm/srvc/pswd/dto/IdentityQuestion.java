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


import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Domain object that represents a question for use in the challenge response functionality
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityQuestion", propOrder = { 
		"identityQuestionId",
		"identityQuestGrp",
		"questionText",
		"required",
		"active",
		"userId"
	})
public class IdentityQuestion extends org.openiam.base.BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1802758764731284709L;
	protected String identityQuestionId;
	protected IdentityQuestGroup identityQuestGrp;
	protected String questionText;
	protected Integer required = new Integer(0);
	protected Integer active = new Integer(1);
	protected String userId;

	public IdentityQuestion() {
	}

	public IdentityQuestion(String identityQuestionId) {
		this.identityQuestionId = identityQuestionId;
	}

	public IdentityQuestion(String identityQuestionId,
			IdentityQuestGroup identityQuestGrp, String questionText,
			Integer required, String userId) {
		this.identityQuestionId = identityQuestionId;
		this.identityQuestGrp = identityQuestGrp;
		this.questionText = questionText;
		this.required = required;
		this.userId = userId;
	}

	public String getIdentityQuestionId() {
		return this.identityQuestionId;
	}

	public void setIdentityQuestionId(String identityQuestionId) {
		this.identityQuestionId = identityQuestionId;
	}

	public IdentityQuestGroup getIdentityQuestGrp() {
		return this.identityQuestGrp;
	}

	public void setIdentityQuestGrp(IdentityQuestGroup identityQuestGrp) {
		this.identityQuestGrp = identityQuestGrp;
	}

	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Integer getRequired() {
		return this.required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}



}
