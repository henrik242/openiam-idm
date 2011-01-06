package org.openiam.idm.srvc.user.dto;

import java.util.Date;
import java.io.Serializable;

/**
 * UserSearchField defines a set of constants that used during search operations.
 * @author Suneet Shah
 *
 */
public class UserSearchField implements Serializable{
	public static final String FirstName = "firstName";
	public static final String LastName = "lastName";
	public static final String MiddleInitial = "middleInit";
	public static final String Status = "status";
	public static final String PhoneNumber= "phone.phoneNbr";
	public static final String PhoneAreaCd= "phone.areaCd";
	public static final String Organization = "companyId";
	public static final String DepartmentCd = "deptCd";
	public static final String Location = "locationId";
	public static final String EmployeeId = "employeeId";
	public static final String EmailAddress = "emailAddress.emailAddress";
	public static final String Role = "role.roleId";
}


