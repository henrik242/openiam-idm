package org.openiam.idm.srvc.auth.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 *  A Subject represents a grouping of related information for a single entity, such as a person. 
 *  Such information includes the Subject's identities as well as its security-related attributes 
 *  (passwords and cryptographic keys, for example).<br>
 *  
 *  Subjects may potentially have multiple identities. Each identity is represented as a 
 *  Principal within the Subject. Principals simply bind names to a Subject. 
 *  For example, a Subject that happens to be a person, Alice, might have two Principals:<br>
 *  one which binds "Alice Bar", the name on her driver license, to the Subject, and another 
 *  which binds, "999-99-9999", the number on her student identification card, to the Subject. 
 *  Both Principals refer to the same Subject even though each has a different name.
 * 
 * @author Suneet Shah
 * @version 1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subject", propOrder = {
    "readOnly",
    "expirationTime",
    "resultCode",
    "saml",
    "samlType",
    "daysToPwdExp",
    "domainId",
    "ssoToken",
    "groups",
    "roles",
    "userId",
    "principal",
    "principals"
})
public class Subject implements Serializable {

	public boolean readOnly = false;

	
	private long expirationTime;
	private int resultCode;
	//private  String token;
	private String saml;
	private String samlType;
	private int daysToPwdExp = 0;
	private String domainId;
	
	private SSOToken ssoToken;	/* Object containing the sso token and related information */
	
	private List groups;
	private List roles;
	private List principals;	/* list of identities for this user */
	
	private String userId;
	private String principal; /* Login */
	

	/** 
	 * Returns the number of days in which the current password will expire.
	 * @return
	 */
	public int getDaysToPwdExp() {
		return daysToPwdExp;
	}

	/**
	 * Sets the number of days in which the current password will expire
	 * @param daysToPwdExp
	 */
	public void setDaysToPwdExp(int daysToPwdExp) {
		this.daysToPwdExp = daysToPwdExp;
	}

	/**
	 * Create an instance of a Subject
	 */
	public Subject() {
	}

	/**
	 * Create an instance of a Subject The specified Sets must check whether
	 * this Subject has not been set to read-only before permitting subsequent
	 * modifications.
	 * 
	 * @param userId
	 * 
	 */
	public Subject(String userId) {
		this.userId = userId;
	}

	/**
	 * This method sets the user id.
	 * 
	 * @param userId
	 *            Sets the user id.
	 */
	public void setUserId(String userId) {
		if (!readOnly)
			this.userId = userId;
	}

	/**
	 * This method gets the user id.
	 * 
	 * @return String Gets the user id.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Setting readOnly to true will ensure that no changes are allowed to
	 * critical security data
	 */

	public void setReadOnly() {
		this.readOnly = true;
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

	/**
	 * This method sets the expitarion time for the user authentication token.
	 * 
	 * @param expTime
	 *            The expiration time is set.
	 */
	public void setExpirationTime(long expTime) {
		if (!readOnly)
			this.expirationTime = expTime;
	}

	/**
	 * This method gets the expiration time for the user authentication.
	 * 
	 * @return long Gets the expitarion time.
	 */
	public long getExpirationTime() {
		return expirationTime;
	}

	/**
	 * Returns the String value of this Subject.
	 * 
	 * @return String Displays the value of each field in the object.
	 */
	public String toString() {
		String subjectString = "User Id: " + userId + "Expiration Time: "
				+ expirationTime + "Result code: " + resultCode + "Read Only: "
				+ readOnly;
		return subjectString;
	}

	/**
	 * @return Returns the resultCode.
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            The resultCode to set.
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}



	/**
	 * Returns hashcode for the user id.
	 * 
	 * @return int Gets the hash code.
	 */
	public int hashCode() {
		return userId.hashCode();
	}

	/**
	 * This method compares objects of userId.
	 * 
	 * @param object
	 *            The object to be compared .
	 * 
	 * @return boolean Returns value as per equality of objects.
	 */
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof Subject) {
			Subject sub = (Subject) object;
			return (sub.userId.equals(this.userId));
		} else {
			return false;
		}
	}

	public String getSaml() {
		return saml;
	}

	public void setSaml(String saml) {
		this.saml = saml;
	}

	public String getSamlType() {
		return samlType;
	}

	public void setSamlType(String samlType) {
		this.samlType = samlType;
	}

	public List getGroups() {
		return groups;
	}

	public void setGroups(List groups) {
		this.groups = groups;
	}

	/**
	 * Returns the list of Roles for this identity (Principal).
	 * @return
	 */
	public List getRoles() {
		return roles;
	}

	/**
	 * Sets the list of roles for this identity (Principal).
	 * @param roles
	 */
	public void setRoles(List roles) {
		this.roles = roles;
	}


	/**
	 * Returns the principal that was used to carry out the authentication.
	 * @return
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * Sets the principal that is to be used for authentication
	 * @param principal
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}	
		
	/**
	 * Returns the list of Principals for this user.
	 * @return
	 */
	public List getPrincipals() {
		return principals;
	}

	/**
	 * Sets the list of Principals for this user.
	 * @param principals
	 */
	public void setPrincipals(List principals) {
		this.principals = principals;
	}

	/**
	 * Returns an SSOToken if one was set
	 * @return
	 */
	public SSOToken getSsoToken() {
		return ssoToken;
	}

	/**
	 * Sets the SSOToken
	 * @param ssoToken
	 */
	public void setSsoToken(SSOToken ssoToken) {
		this.ssoToken = ssoToken;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	
}
