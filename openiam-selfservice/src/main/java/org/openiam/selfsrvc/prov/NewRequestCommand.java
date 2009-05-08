package org.openiam.selfsrvc.prov;

import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class NewRequestCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3001967685870249543L;
	String status = new String("PENDING");
	String firstName;
	String lastName;
	String middleName;
	String employeeId;
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	


	
	

	

}
