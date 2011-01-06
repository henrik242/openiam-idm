package org.openiam.selfsrvc.prov;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.role.dto.Role;

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
	
	protected List<Group> groupList;
	protected List<Role> roleList;
	
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
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	


	
	

	

}
