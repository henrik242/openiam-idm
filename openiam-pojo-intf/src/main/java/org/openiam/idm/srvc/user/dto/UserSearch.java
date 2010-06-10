/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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
package org.openiam.idm.srvc.user.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * UserSearch is used to capture the parameters for an ad-hoc search on the user object.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserSearch", propOrder = {

    "firstName",
    "lastName",
    "status",
    "secondaryStatus",
	"nickName",
	"deptCd",
	"division",
	"phoneAreaCd",
	"phoneNbr",
	"employeeId",
	"groupIdList",
	"roleIdList",
	"emailAddress",
	"orgId",
	"orgName",
	"principal",
	"attributeName",
	"attributeValue",
	"attributeElementId",
	"userId",
	"domainId",
	"showInSearch",  
	"locationCd",
	"classification",
    "userTypeInd",
    "loggedIn",
    "createDate",
	"maxResultSize",
	"startDate",
	"lastDate"
})
public class UserSearch implements Serializable {

	protected String firstName = null;
	protected String lastName = null;
	protected String nickName = null;
	protected String status = null;
	protected String secondaryStatus = null;
	protected String deptCd = null;
	protected String division = null;
	protected String phoneAreaCd = null;
	protected String phoneNbr = null;
	protected String employeeId = null;
	protected List<String> groupIdList = new ArrayList<String>();
	protected List<String> roleIdList = new ArrayList<String>();
	protected String emailAddress = null;
	protected String orgId = null;	
	protected String orgName;
	protected String principal;
	protected String attributeName;
	protected String attributeValue;
	protected String attributeElementId;
	protected String userId;
	protected String domainId;
	protected String locationCd;
	protected Integer showInSearch;
	protected Integer maxResultSize;
	
    protected String userTypeInd;
    protected String classification;
    @XmlSchemaType(name = "dateTime")
    protected Date createDate;
    @XmlSchemaType(name = "dateTime")
	protected Date startDate;
    @XmlSchemaType(name = "dateTime")
	protected Date lastDate;
    
    protected String loggedIn = null;
    
	
	public boolean isEmpty() {
		boolean retval = false;
		
		if ( (firstName == null || firstName.length() == 0) && 
			 (lastName == null || lastName.length() == 0)   &&
			 (status == null || status.length() == 0)  &&
			 (secondaryStatus == null || secondaryStatus.length() == 0)  &&
			 (deptCd == null || deptCd.length() == 0)  && 
			 (phoneAreaCd == null || phoneAreaCd.length() == 0) &&
			 (phoneNbr == null || phoneNbr.length() == 0)  && 
			 (employeeId == null || employeeId.length() == 0) && 
			 groupIdList.isEmpty() && 
			 roleIdList.isEmpty() && 
			 (emailAddress == null || emailAddress.length() == 0)  &&
			 (nickName == null || nickName.length() == 0) &&
			 (orgId == null || orgId.length() == 0 )  && 
			 (principal == null || principal.length() == 0) && 
			 (attributeName == null || attributeName.length() == 0) &&
			 (attributeValue == null || attributeValue.length() == 0) && 
			 (attributeElementId == null || attributeElementId.length() == 0) &&
			 showInSearch == null  &&
			 (userId == null || userId.length() == 0) && 
			 (startDate == null) && 
			 (lastDate == null)) {
			retval = true;
		}
		
		return retval;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getPhoneAreaCd() {
		return phoneAreaCd;
	}
	public void setPhoneAreaCd(String phoneAreaCd) {
		this.phoneAreaCd = phoneAreaCd;
	}
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<String> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeElementId() {
		return attributeElementId;
	}

	public void setAttributeElementId(String attributeElementId) {
		this.attributeElementId = attributeElementId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public Integer getMaxResultSize() {
		return maxResultSize;
	}

	public void setMaxResultSize(Integer maxResultSize) {
		this.maxResultSize = maxResultSize;
	}

	public Integer getShowInSearch() {
		return showInSearch;
	}

	public void setShowInSearch(Integer showInSearch) {
		this.showInSearch = showInSearch;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getUserTypeInd() {
		return userTypeInd;
	}

	public void setUserTypeInd(String userTypeInd) {
		this.userTypeInd = userTypeInd;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLocationCd() {
		return locationCd;
	}

	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getSecondaryStatus() {
		return secondaryStatus;
	}

	public void setSecondaryStatus(String secondaryStatus) {
		this.secondaryStatus = secondaryStatus;
	}




}
