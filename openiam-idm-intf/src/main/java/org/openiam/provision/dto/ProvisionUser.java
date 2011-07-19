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
package org.openiam.provision.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;

/**
 * ProvisionUser is the user object used by the provisioning service.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvisionUser", propOrder = {
    "principalList",
    "memberOfGroups",
    "appAccess",
    "requestId",
    "sessionId",
    "memberOfRoles",
    "srcSystemId",
    "provisionModel",
    "securityDomain" ,
    "notifyTargetSystems"
})

public class ProvisionUser extends org.openiam.idm.srvc.user.dto.User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6441635701870724194L;
	protected List<Login> principalList ;
	protected List<Group> memberOfGroups;
	protected List<Role> memberOfRoles;
	protected List<Application> appAccess;
	public ProvisionModelEnum  provisionModel;
	public String securityDomain;
	
		
	protected String requestId;
	protected String sessionId;
	
	/* ID of the system where this request came from */
	protected String srcSystemId;
    /* Flag that indicates if target systems should be updated or not
     */
    protected boolean notifyTargetSystems = true;

	public ProvisionUser() {
		
	}
	public ProvisionUser(User user) {
		    birthdate = user.getBirthdate();
		    companyId = user.getCompanyId();
		    companyOwnerId = user.getCompanyOwnerId();
		    createDate = user.getCreateDate();
		    createdBy = user.getCreatedBy();
		    deptCd = user.getDeptCd();
		    deptName = user.getDeptName();
		    employeeId = user.getEmployeeId();
		    employeeType = user.getEmployeeType();

		    firstName = user.getFirstName();
		    jobCode = user.getJobCode();
		    lastName = user.getLastName();
		    lastUpdate = user.getLastUpdate();
		    this.lastUpdatedBy = user.getLastUpdatedBy();
		    this.locationCd = user.getLocationCd();
		    this.locationName = user.getLocationName();
		    this.managerId = user.getManagerId();
		    this.metadataTypeId = user.getMetadataTypeId();
		    this.classification = user.getClassification();
		    this.middleInit = user.getMiddleInit();
		    this.prefix = user.getPrefix();
		    this.sex = user.getSex();
		    this.status = user.getStatus();
		    this.secondaryStatus = user.getSecondaryStatus();
		    this.suffix = user.getSuffix();
		    this.title = user.getTitle();
		    this.userId = user.getUserId();
		    this.userTypeInd = user.getUserTypeInd();
		    this.division = user.getDivision();
		    this.mailCode = user.getMailCode(); 
			this.costCenter = user.getCostCenter();
			this.startDate = user.getStartDate();
			this.lastDate = user.getLastDate();		    
			this.nickname = user.getNickname();
			this.maidenName = user.getMaidenName();
			this.passwordTheme = user.getPasswordTheme();
		    this.country = user.getCountry();            
		    this.bldgNum = user.getBldgNum(); 
		    this.streetDirection = user.getStreetDirection();
			this.address1 = user.getAddress1();
			this.address2 = user.getAddress2();
			this.address3 = user.getAddress3();            
			this.address4 = user.getAddress4();            
			this.address5 = user.getAddress5();     
			this.address6 = user.getAddress6();  
			this.address7 = user.getAddress7();
			this.city = user.getCity();
			this.state = user.getState();
			this.postalCd = user.getPostalCd();
			this.email = user.getEmail();
			this.areaCd = user.getAreaCd();       
			this.countryCd = user.getCountryCd();
			this.phoneNbr = user.getPhoneNbr();
			this.phoneExt = user.getPhoneExt();
			this.showInSearch = user.getShowInSearch();
			this.delAdmin = user.getDelAdmin();
			this.alternateContactId = user.getAlternateContactId();
			
			this.createdBy = user.getCreatedBy();
			this.startDate = user.getStartDate();
			this.lastDate = user.getLastDate();
    
			this.userOwnerId = user.getUserOwnerId();
			this.dateChallengeRespChanged = user.getDateChallengeRespChanged();
			this.datePasswordChanged = user.getDatePasswordChanged();
			
			userNotes = user.getUserNotes();
			userAttributes = user.getUserAttributes();
			phones = user.getPhone();
			addresses = user.getAddresses();
			// set the email address in a hibernate friendly manner
		
		
	}
	
	public User getUser() {
		User user = new User();
		
		user.setBirthdate(birthdate);
	    user.setCompanyId(companyId);
	    user.setCompanyOwnerId(companyOwnerId);
	    user.setCreateDate(createDate);
	    user.setCreatedBy(createdBy);
	    user.setDeptCd(deptCd);
	    user.setDeptName(deptName);
	    user.setEmployeeId(employeeId);
	    user.setEmployeeType(employeeType);

	    user.setFirstName(firstName);
	    user.setJobCode(jobCode);
	    user.setLastName(lastName);
	    user.setLastUpdate(lastUpdate);
	    user.setLastUpdatedBy(lastUpdatedBy);
	    user.setLocationCd(locationCd);
	    user.setLocationName(locationName);
	    user.setManagerId(managerId);
	    user.setMetadataTypeId(metadataTypeId);
	    user.setClassification(classification);
	    user.setMiddleInit(middleInit);
	    user.setPrefix(prefix);
	    user.setSex(sex);
	    user.setStatus(status);
	    user.setSecondaryStatus(secondaryStatus);
	    user.setSuffix(suffix);
	    user.setTitle(title);
	    user.setUserId(userId);
	    user.setUserTypeInd(userTypeInd);
	    user.setDivision(division);
	    user.setMailCode(mailCode); 
		user.setCostCenter(costCenter);
		user.setStartDate(startDate);
		user.setLastDate(lastDate);		    
		user.setNickname(nickname);
		user.setMaidenName(maidenName);
		user.setPasswordTheme(passwordTheme);
	    user.setCountry(country);            
	    user.setBldgNum(bldgNum); 
	    user.setStreetDirection(streetDirection);
		user.setAddress1(address1);
		user.setAddress2(address2);
		user.setAddress3(address3);            
		user.setAddress4(address4);            
		user.setAddress5(address5);     
		user.setAddress6(address6);  
		user.setAddress7(address3);
		user.setCity(city);
		user.setState(state);
		user.setPostalCd(postalCd);
		user.setEmail(email);
		user.setAreaCd(areaCd);       
		user.setCountryCd(countryCd);
		user.setPhoneNbr(phoneNbr);
		user.setPhoneExt(phoneExt);

		user.setUserNotes(userNotes);
		user.setUserAttributes(userAttributes);
		user.setPhone(phones);
		user.setAddresses(addresses);
		user.setEmailAddress(emailAddresses);
		user.setAlternateContactId(alternateContactId);
		user.setShowInSearch(showInSearch);
		user.setDelAdmin(delAdmin);
		
		user.setUserOwnerId(userOwnerId);
		user.setDateChallengeRespChanged(dateChallengeRespChanged);
		user.setDatePasswordChanged(datePasswordChanged);

		return user;
	}
	

	public Login getPrimaryPrincipal(String managedSysId) {
		if (principalList == null) {
			return null;
		}
		for (Login l  : principalList) {
			if (l.getId().getManagedSysId().equals(managedSysId)) {
				return l;
			}
		}
		return null;
	}

	public List<Login> getPrincipalList() {
		return principalList;
	}

	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}


	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSrcSystemId() {
		return srcSystemId;
	}

	public void setSrcSystemId(String srcSystemId) {
		this.srcSystemId = srcSystemId;
	}

	public List<Group> getMemberOfGroups() {
		return memberOfGroups;
	}

	public void setMemberOfGroups(List<Group> memberOfGroups) {
		this.memberOfGroups = memberOfGroups;
	}

	public List<Application> getAppAccess() {
		return appAccess;
	}

	public void setAppAccess(List<Application> appAccess) {
		this.appAccess = appAccess;
	}
	public List<Role> getMemberOfRoles() {
		return memberOfRoles;
	}
	public List<Role> getActiveMemberOfRoles() {
		List<Role> activeRoleList = new ArrayList<Role>();
		if (memberOfRoles != null) {
			for (Role r  : memberOfRoles) {
				if (r.getOperation() != AttributeOperationEnum.DELETE) {
					activeRoleList.add(r);
				}
			}
			return activeRoleList;
		}
		return null;
	}
	public void setMemberOfRoles(List<Role> memberOfRoles) {
		this.memberOfRoles = memberOfRoles;
	}
	public ProvisionModelEnum getProvisionModel() {
		return provisionModel;
	}
	public void setProvisionModel(ProvisionModelEnum provisionModel) {
		this.provisionModel = provisionModel;
	}
	public String getSecurityDomain() {
		return securityDomain;
	}
	public void setSecurityDomain(String securityDomain) {
		this.securityDomain = securityDomain;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

    public boolean isNotifyTargetSystems() {
        return notifyTargetSystems;
    }

    public void setNotifyTargetSystems(boolean notifyTargetSystems) {
        this.notifyTargetSystems = notifyTargetSystems;
    }

    @Override
    public String toString() {
        return "ProvisionUser{" +
                "principalList=" + principalList +
                ", memberOfGroups=" + memberOfGroups +
                ", memberOfRoles=" + memberOfRoles +
                ", appAccess=" + appAccess +
                ", provisionModel=" + provisionModel +
                ", securityDomain='" + securityDomain + '\'' +
                ", requestId='" + requestId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", srcSystemId='" + srcSystemId + '\'' +
                '}';
    }
}
