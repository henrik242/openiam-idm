package org.openiam.selfsrvc.profile;

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
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class ProfileCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3001967685870249543L;
	String status = new String("APPROVED");
	String firstName;
	String lastName;
	String middleName;
	String companyName;
	String department;
	String title;
	String managedBy;
	String nickname;
	String maiden;
	String suffix;
	String division;
	String jobCode;
	String gender;
	java.util.Date birthDate;
	String employeeId;
	String employeeType;
	
	String userId;
	String secdomain;
	String managedSysId;
	
	/* Store as user attributes */
	String passwordTheme;
	String fullPart;
	String fullPartId;
	String languagesSpoken;
	String languagesSpokenId;
	String volunteerInterpreter;
	String volunteerInterpreterId;
	List<String> selectedLanguages;
	
	/* List of Managers */
	List<User> managerList;
	List<ReferenceData> jobCodeList;
	List<ReferenceData> userTypeList;
	
	/* Access control */
	String role;
	String group;
	List<Group> selectedGroups;
	List<Group> unselectedGroups;
	

	/* Address fields */
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMaiden() {
		return maiden;
	}

	public void setMaiden(String maiden) {
		this.maiden = maiden;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getPasswordTheme() {
		return passwordTheme;
	}

	public void setPasswordTheme(String passwordTheme) {
		this.passwordTheme = passwordTheme;
	}

	public String getFullPart() {
		return fullPart;
	}

	public void setFullPart(String fullPart) {
		this.fullPart = fullPart;
	}

	public String getLanguagesSpoken() {
		return languagesSpoken;
	}

	public void setLanguagesSpoken(String languagesSpoken) {
		this.languagesSpoken = languagesSpoken;
	}

	public String getVolunteerInterpreter() {
		return volunteerInterpreter;
	}

	public void setVolunteerInterpreter(String volunteerInterpreter) {
		this.volunteerInterpreter = volunteerInterpreter;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getFullPartId() {
		return fullPartId;
	}

	public void setFullPartId(String fullPartId) {
		this.fullPartId = fullPartId;
	}

	public String getLanguagesSpokenId() {
		return languagesSpokenId;
	}

	public void setLanguagesSpokenId(String languagesSpokenId) {
		this.languagesSpokenId = languagesSpokenId;
	}

	public String getVolunteerInterpreterId() {
		return volunteerInterpreterId;
	}

	public void setVolunteerInterpreterId(String volunteerInterpreterId) {
		this.volunteerInterpreterId = volunteerInterpreterId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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



	public java.util.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
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

	public List<String> getSelectedLanguages() {
		return selectedLanguages;
	}

	public void setSelectedLanguages(List<String> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
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




}
