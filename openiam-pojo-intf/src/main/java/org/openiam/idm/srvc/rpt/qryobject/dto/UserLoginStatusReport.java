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
package org.openiam.idm.srvc.rpt.qryobject.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;



/**
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserLoginStatusReport", propOrder = {
    "userId",
    "firstName",
    "lastName",
    "nickName",
    "middleInit",
    "emailAddress",
    "deptCd",
    "division",
    "timeStamp",
    "response",
    "login",
    "lastLogin",
    "employeeType",
    "areaCode",
    "phoneNbr",
    "unit",
    "company",
    "title",
    "supervisorName",
    "supervisorLastName"
})
@XmlRootElement(name="UserLoginStatusReport")
public class UserLoginStatusReport {

	private String userId;
	private String firstName;
	private String lastName;
	private String nickName;
	private String middleInit;
	private String emailAddress;
	private String deptCd;
	private String division;
    @XmlSchemaType(name = "dateTime")
	private Date timeStamp;
	private String login;
    @XmlSchemaType(name = "dateTime")
	private Date lastLogin;
	private String employeeType;
	private String areaCode;
	private String phoneNbr;
	
	private String unit;
	private String company;
	private String title;
	private String supervisorName;
	private String supervisorLastName;
	private String response;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMiddleInit() {
		return middleInit;
	}
	public void setMiddleInit(String middleInit) {
		this.middleInit = middleInit;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupervisorLastName() {
		return supervisorLastName;
	}
	public void setSupervisorLastName(String supervisorLastName) {
		this.supervisorLastName = supervisorLastName;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
