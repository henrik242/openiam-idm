package org.openiam.selfsrvc.prov;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.res.dto.Resource;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class ChangeAccessCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3001967685870249543L;
	String status = new String("PENDING_APPROVAL");
	String firstName;
	String lastName;
	String middleName;
	String companyName;
	String department;
	String title;
	String managedBy;
	String nickname;
	String maiden;

	String requestType;
	Date startDate;
	Date endDate;
	String changeAccessBy;

	String suffix;
	String division;
	String jobCode;
	String gender;
	
	String phoneNbr;


	String phoneAreaCd;
	String email;
	String requestorUserId;
	

	/* Access control */
	String role;
	String group;
	
	/* requestee */
	String userId;
	String userName;

	
	protected List<Resource> resourceList;

	



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

	public String getPhoneAreaCd() {
		return phoneAreaCd;
	}

	public void setPhoneAreaCd(String phoneAreaCd) {
		this.phoneAreaCd = phoneAreaCd;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRequestorUserId() {
		return requestorUserId;
	}

	public void setRequestorUserId(String requestorUserId) {
		this.requestorUserId = requestorUserId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getChangeAccessBy() {
		return changeAccessBy;
	}

	public void setChangeAccessBy(String changeAccessBy) {
		this.changeAccessBy = changeAccessBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
