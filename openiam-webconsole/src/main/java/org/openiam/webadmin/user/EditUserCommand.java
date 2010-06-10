package org.openiam.webadmin.user;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Command object for the EditUserController
 * @author suneet
 *
 */
public class EditUserCommand implements Serializable {
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 254263741352814079L;

	String status = new String("APPROVED");
	
	User user = new User();
	
	String supervisorId;
	String supervisorName;
	
	String alternateContactId;
	String alternateContactName;
	
	String secdomain;
	String managedSysId;
	

	

	List<ReferenceData> jobCodeList;
	List<ReferenceData> userTypeList;
	
	/* Access control */
	String role;
	String group;
	List<Group> selectedGroups;
	List<Group> unselectedGroups;
	

	/* Address fields */
	String addressId;
	String locationBldg;
	String locationRoom;
	
	String bldgNbr;
	String adrSign;
	String address1;
	String address2;
	String city;
	String state;
	String postalCode;
	String country;
	
	/* phone fields */
	String workAreaCode;
	String workPhone;
	String cellAreaCode;
	String cellPhone;
	String faxAreaCode;
	String faxPhone;

	String workPhoneId;
	String cellPhoneId;
	String faxPhoneId;
	String homePhoneIdr;
	String altCellNbrId;
	String altPhoneNbrId;
	String personalNbrId;
	
	String homePhoneAreaCode;
	String homePhoneNbr;
	String altCellAreaCode;
	String altCellNbr;
	String altPhoneAreaCode;
	String altPhoneNbr;
	String personalAreaCode;
	String personalNbr;

	
	
	/* misc fields */
	String userPrincipalName;
	String email1Id;
	String email1;
	String email2Id;
	String email2;
	String email3Id;
	String email3;
	
	List<Organization> orgList;
	List<Organization> departmentList;
	List<Organization> divisionList;
	
	List<Role> roleAry; // list of roles a user belongs to
	Location[] locationAry;
	

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}






	public String getLocationBldg() {
		return locationBldg;
	}

	public void setLocationBldg(String locationBldg) {
		this.locationBldg = locationBldg;
	}

	public String getLocationRoom() {
		return locationRoom;
	}

	public void setLocationRoom(String locationRoom) {
		this.locationRoom = locationRoom;
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

	public void setHomePhoneIdr(String homePhoneNbIdr) {
		this.homePhoneIdr = homePhoneNbIdr;
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



	public String getSecdomain() {
		return secdomain;
	}

	public void setSecdomain(String secdomain) {
		this.secdomain = secdomain;
	}

	public String getManagedSysId() {
		return managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}


	public List<Role> getRoleAry() {
		return roleAry;
	}

	public void setRoleAry(List<Role> roleAry) {
		this.roleAry = roleAry;
	}




	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}


	public List<Group> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(List<Group> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	
	public void setLangStr(String langStr) {
		
	}

	public List<Group> getUnselectedGroups() {
		return unselectedGroups;
	}

	public void setUnselectedGroups(List<Group> unselectedGroups) {
		this.unselectedGroups = unselectedGroups;
	}

	public String getAdrSign() {
		return adrSign;
	}

	public void setAdrSign(String adrSign) {
		this.adrSign = adrSign;
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

	public Location[] getLocationAry() {
		return locationAry;
	}

	public void setLocationAry(Location[] locationAry) {
		this.locationAry = locationAry;
	}

	public String getBldgNbr() {
		return bldgNbr;
	}

	public void setBldgNbr(String bldgNbr) {
		this.bldgNbr = bldgNbr;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getAlternateContactId() {
		return alternateContactId;
	}

	public void setAlternateContactId(String alternateContactId) {
		this.alternateContactId = alternateContactId;
	}

	public String getAlternateContactName() {
		return alternateContactName;
	}

	public void setAlternateContactName(String alternateContactName) {
		this.alternateContactName = alternateContactName;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}




}
