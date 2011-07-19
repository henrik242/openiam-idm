package org.openiam.webadmin.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;


/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class NewUserCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3001967685870249543L;
	String status = new String("PENDING_APPROVAL");

    UserStatusEnum newUserStatus;

	User user = new User();
	List<Application> applicationList = new ArrayList<Application>();
	
	String supervisorId;
	String supervisorName;
	
	//String alternateContactId;
	String alternateContactName;

	
	/* phone fields */
	protected String workAreaCode;
	protected String workPhone;
	protected String cellAreaCode;
	protected String cellPhone;
	protected String faxAreaCode;
	protected String faxPhone;

	protected String workPhoneId;
	protected String cellPhoneId;
	protected String faxPhoneId;
	protected String homePhoneIdr;
	protected String altCellNbrId;
	protected String altPhoneNbrId;
	protected String personalNbrId;
	
	protected String homePhoneAreaCode;
	protected String homePhoneNbr;
	protected String altCellAreaCode;
	protected String altCellNbr;
	protected String altPhoneAreaCode;
	protected String altPhoneNbr;
	protected String personalAreaCode;
	protected String personalNbr;

	
	
	/* misc fields */
	protected String userPrincipalName;
	protected String email1Id;
	protected String email2Id;
	protected String email3Id;
	protected String email1;
	protected String email2;
	protected String email3;
	
	/* List of Managers */
	protected List<User> managerList;
	protected List<ReferenceData> jobCodeList;
	protected List<ReferenceData> userTypeList;
	
	/* Access control */
	protected String role;
	protected String group;
	protected List<Group> selectedGroups;
	protected List<Group> unselectedGroups;
	
	protected List<Organization> orgList;
	protected List<Organization> departmentList;
	protected List<Organization> divisionList;
	
	protected List<Role> roleAry; // list of roles a user belongs to
	protected Location[] locationAry;
	
	protected List<UserAttribute> attributeList;
	
	//protected List<Resource> resourceList;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	} 


	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}



	public List<User> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<User> managerList) {
		this.managerList = managerList;
	}

	public List<ReferenceData> getJobCodeList() {
		return jobCodeList;
	}

	public void setJobCodeList(List<ReferenceData> jobCodeList) {
		this.jobCodeList = jobCodeList;
	}

	public List<ReferenceData> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<ReferenceData> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public List<Group> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(List<Group> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public List<Group> getUnselectedGroups() {
		return unselectedGroups;
	}

	public void setUnselectedGroups(List<Group> unselectedGroups) {
		this.unselectedGroups = unselectedGroups;
	}



	public List<Organization> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Organization> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Organization> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<Organization> divisionList) {
		this.divisionList = divisionList;
	}

	public List<Role> getRoleAry() {
		return roleAry;
	}

	public void setRoleAry(List<Role> roleAry) {
		this.roleAry = roleAry;
	}

	public Location[] getLocationAry() {
		return locationAry;
	}

	public void setLocationAry(Location[] locationAry) {
		this.locationAry = locationAry;
	}


	public void setResourceList(List<Resource> resourceList) {
		// copy to the application list
		if (resourceList != null) {
			for (Resource res  : resourceList) {
				applicationList.add(new Application(res));
			}
		}

	}

	public User getUser() {
	
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public String getWorkAreaCode() {
		return workAreaCode;
	}

	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCellAreaCode() {
		return cellAreaCode;
	}

	public void setCellAreaCode(String cellAreaCode) {
		this.cellAreaCode = cellAreaCode;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFaxAreaCode() {
		return faxAreaCode;
	}

	public void setFaxAreaCode(String faxAreaCode) {
		this.faxAreaCode = faxAreaCode;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public String getWorkPhoneId() {
		return workPhoneId;
	}

	public void setWorkPhoneId(String workPhoneId) {
		this.workPhoneId = workPhoneId;
	}

	public String getCellPhoneId() {
		return cellPhoneId;
	}

	public void setCellPhoneId(String cellPhoneId) {
		this.cellPhoneId = cellPhoneId;
	}

	public String getFaxPhoneId() {
		return faxPhoneId;
	}

	public void setFaxPhoneId(String faxPhoneId) {
		this.faxPhoneId = faxPhoneId;
	}

	public String getHomePhoneIdr() {
		return homePhoneIdr;
	}

	public void setHomePhoneIdr(String homePhoneIdr) {
		this.homePhoneIdr = homePhoneIdr;
	}

	public String getAltCellNbrId() {
		return altCellNbrId;
	}

	public void setAltCellNbrId(String altCellNbrId) {
		this.altCellNbrId = altCellNbrId;
	}

	public String getAltPhoneNbrId() {
		return altPhoneNbrId;
	}

	public void setAltPhoneNbrId(String altPhoneNbrId) {
		this.altPhoneNbrId = altPhoneNbrId;
	}

	public String getPersonalNbrId() {
		return personalNbrId;
	}

	public void setPersonalNbrId(String personalNbrId) {
		this.personalNbrId = personalNbrId;
	}

	public String getHomePhoneAreaCode() {
		return homePhoneAreaCode;
	}

	public void setHomePhoneAreaCode(String homePhoneAreaCode) {
		this.homePhoneAreaCode = homePhoneAreaCode;
	}

	public String getHomePhoneNbr() {
		return homePhoneNbr;
	}

	public void setHomePhoneNbr(String homePhoneNbr) {
		this.homePhoneNbr = homePhoneNbr;
	}

	public String getAltCellAreaCode() {
		return altCellAreaCode;
	}

	public void setAltCellAreaCode(String altCellAreaCode) {
		this.altCellAreaCode = altCellAreaCode;
	}

	public String getAltCellNbr() {
		return altCellNbr;
	}

	public void setAltCellNbr(String altCellNbr) {
		this.altCellNbr = altCellNbr;
	}

	public String getAltPhoneAreaCode() {
		return altPhoneAreaCode;
	}

	public void setAltPhoneAreaCode(String altPhoneAreaCode) {
		this.altPhoneAreaCode = altPhoneAreaCode;
	}

	public String getAltPhoneNbr() {
		return altPhoneNbr;
	}

	public void setAltPhoneNbr(String altPhoneNbr) {
		this.altPhoneNbr = altPhoneNbr;
	}

	public String getPersonalAreaCode() {
		return personalAreaCode;
	}

	public void setPersonalAreaCode(String personalAreaCode) {
		this.personalAreaCode = personalAreaCode;
	}

	public String getPersonalNbr() {
		return personalNbr;
	}

	public void setPersonalNbr(String personalNbr) {
		this.personalNbr = personalNbr;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getEmail1Id() {
		return email1Id;
	}

	public void setEmail1Id(String email1Id) {
		this.email1Id = email1Id;
	}

	public String getEmail2Id() {
		return email2Id;
	}

	public void setEmail2Id(String email2Id) {
		this.email2Id = email2Id;
	}

	public String getEmail3Id() {
		return email3Id;
	}

	public void setEmail3Id(String email3Id) {
		this.email3Id = email3Id;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}



	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}


	public String getAlternateContactName() {
		return alternateContactName;
	}

	public void setAlternateContactName(String alternateContactName) {
		this.alternateContactName = alternateContactName;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public List<Application> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}

	public List<UserAttribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<UserAttribute> attributeList) {
		this.attributeList = attributeList;
	}

    public UserStatusEnum getNewUserStatus() {
        return newUserStatus;
    }

    public void setNewUserStatus(UserStatusEnum newUserStatus) {
        this.newUserStatus = newUserStatus;
    }
}
