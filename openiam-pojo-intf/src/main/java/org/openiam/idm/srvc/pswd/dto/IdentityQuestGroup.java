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


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * IdentityQuestGroup is a domain object that allows you to group questions together. These questions can be
 * associate with an organization or security domain.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityQuestGroup", propOrder = { 
		"identityQuestGrpId",
		"name",
		"status",
		"companyOwnerId",
		"createDate",
		"createdBy",
		"lastUpdate",
		"lastUpdatedBy",
		"identityQuestions"
})
public class IdentityQuestGroup extends org.openiam.base.BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1531681049802666090L;
	protected String identityQuestGrpId;
	protected String name;
	protected String status;
	protected String companyOwnerId;
	@XmlSchemaType(name = "dateTime")
	protected Date createDate;
	protected String createdBy;
	@XmlSchemaType(name = "dateTime")
	protected Date lastUpdate;
	protected String lastUpdatedBy;
	protected Set<IdentityQuestion> identityQuestions = new HashSet<IdentityQuestion>(
			0);

	public IdentityQuestGroup() {
	}

	public IdentityQuestGroup(String identityQuestGrpId) {
		this.identityQuestGrpId = identityQuestGrpId;
	}

	public IdentityQuestGroup(String identityQuestGrpId, String name,
			String status, String companyOwnerId, Date createDate,
			String createdBy, Date lastUpdate, String lastUpdatedBy,
			Set<IdentityQuestion> identityQuestions) {
		this.identityQuestGrpId = identityQuestGrpId;
		this.name = name;
		this.status = status;
		this.companyOwnerId = companyOwnerId;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.identityQuestions = identityQuestions;
	}

	public String getIdentityQuestGrpId() {
		return this.identityQuestGrpId;
	}

	public void setIdentityQuestGrpId(String identityQuestGrpId) {
		this.identityQuestGrpId = identityQuestGrpId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyOwnerId() {
		return this.companyOwnerId;
	}

	public void setCompanyOwnerId(String companyOwnerId) {
		this.companyOwnerId = companyOwnerId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Set<IdentityQuestion> getIdentityQuestions() {
		return this.identityQuestions;
	}

	public void setIdentityQuestions(Set<IdentityQuestion> identityQuestions) {
		this.identityQuestions = identityQuestions;
	}

}
