package org.openiam.selfsrvc.hire;

import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class NewHireCommand implements Serializable {
	 
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
	/* Store as user attributes */
	String passwordTheme;
	String fullPart;
	String languagesSpoken;
	String volunteerInterpreter;
	/* Access control */
	String role;
	String group;
	

	/* Address fields */
	String locationBldg;
	String locationRoom;
	
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
	String email1;
	String email2;
	String email3;
	
	Menu[] managedSystems;

	
	
	public Menu[] getManagedSystems() {
		return managedSystems;
	}

	public void setManagedSystems(Menu[] managedSystems) {
		this.managedSystems = managedSystems;
	}

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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	

}
