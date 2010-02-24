package org.openiam.idm.srvc.user.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.openiam.idm.srvc.meta.dto.*;
import org.openiam.idm.srvc.org.dto.OrganizationAttributeMapAdapter;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.grp.dto.Group;

/**
 * User domain object.  This object is used to transfer data between the service layer
 * and the client layer.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "addresses",
    "birthdate",
    "companyId",
    "companyOwnerId",
    "createDate",
    "createdBy",
    "deptCd",
    "deptName",
    "emailAddresses",
    "employeeId",
    "employeeType",
    //"expirationDate",
    "firstName",
    "jobCode",
    "lastName",
    "lastUpdate",
    "lastUpdatedBy",
    "locationCd",
    "locationName",
    "managerId",
    "metadataTypeId",
    "classification",
    "middleInit",
    "phones",
    "prefix",
    "sex",
    "status",
    "secondaryStatus",
    "suffix",
    "title",
    "userAttributes",
    "userId",
    "userTypeInd",
    "userNotes",
    "division",
    "costCenter",
    "startDate",
    "lastDate",
    "mailCode",
	"nickname",
	"maidenName",
	"passwordTheme",
	"country",            
    "bldgNum", 
    "streetDirection",
    "suite",
	"address1",
	"address2",
	"address3",            
	"address4",            
	"address5",     
	"address6",  
	"address7",
	"city",
	"state",
	"postalCd",
	"email",
	"showInSearch",
	"areaCd",       
	"countryCd",
	"phoneNbr",
	"phoneExt",
	"principalList",
	"supervisor",
	"alternateContactId",
	"securityDomain"
})

	public class User extends org.openiam.base.BaseObject implements java.io.Serializable {

	// Fields    
	
	//protected AddressMap addresses; see below
    @XmlSchemaType(name = "dateTime")
    protected Date birthdate;
    protected String companyId;
    protected String companyOwnerId;
    @XmlSchemaType(name = "dateTime")
    protected Date createDate;
    protected String createdBy;
    protected String deptCd;
    protected String deptName;
    protected String employeeId;
    protected String employeeType;

    protected String firstName;
    protected String jobCode;
    protected String lastName;
    @XmlSchemaType(name = "dateTime")
    protected Date lastUpdate;
    protected String lastUpdatedBy;
    protected String locationCd;
    protected String locationName;
    protected String managerId;
    protected String metadataTypeId;
    protected String classification;
    protected String middleInit;
    protected String prefix;
    protected String sex;
    protected UserStatusEnum status;
    protected UserStatusEnum secondaryStatus;
    protected String suffix;
    protected String title;
    protected String userId;
    protected String userTypeInd;
    protected String division;
    protected String mailCode;
    
	protected String costCenter;
    @XmlSchemaType(name = "dateTime")
	protected Date startDate;
    @XmlSchemaType(name = "dateTime")
	protected Date lastDate;
    
	protected String nickname;
	protected String maidenName;
	protected String passwordTheme;

    protected String country;            
    protected String bldgNum; 
    protected String streetDirection;
    protected String suite;
	protected String address1;
	protected String address2;
	protected String address3;            
	protected String address4;            
	protected String address5;     
	protected String address6;  
	protected String address7;
	protected String city;
	protected String state;
	protected String postalCd;
	protected String email;
	protected String areaCd;       
	protected String countryCd;
	protected String phoneNbr;
	protected String phoneExt;
	protected Integer showInSearch;
	
	protected List<Login> principalList;
	protected Supervisor supervisor;
	protected String alternateContactId;
	//@XmlElement(name="securityDomain",	namespace = "urn:idm.openiam.org/srvc/user/dto") 
	protected String securityDomain;

    

	//private Set userGrps = new HashSet(0);
    


	@XmlJavaTypeAdapter(UserNoteSetAdapter.class)
	protected Set<UserNote> userNotes = new HashSet(0);
	//private Set<Group> groups = new HashSet<Group>(0);


	@XmlJavaTypeAdapter(UserAttributeMapAdapter.class)
	protected Map<String, UserAttribute> userAttributes = new HashMap<String, UserAttribute>(0);

	protected Set<Address> addresses = new HashSet<Address>(0);

	protected Set<Phone> phones = new HashSet<Phone>(0);
	
	protected Set<EmailAddress> emailAddresses = new HashSet<EmailAddress>(0);

	
	
	//private Set userAttributes = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userId) {
		
		this.userId = userId;
	}


	
	// Property accessors
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInit() {
		return this.middleInit;
	}

	public void setMiddleInit(String middleInit) {
		this.middleInit = middleInit;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeptCd() {
		return this.deptCd;
	}

	public void setDeptCd(String dept) {
		this.deptCd = dept;
	}





	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getUserTypeInd() {
		return this.userTypeInd;
	}

	public void setUserTypeInd(String userTypeInd) {
		this.userTypeInd = userTypeInd;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeType() {
		return this.employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getLocationCd() {
		return this.locationCd;
	}

	public void setLocationCd(String locationId) {
		this.locationCd = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyOwnerId() {
		return this.companyOwnerId;
	}

	public void setCompanyOwnerId(String companyOwnerId) {
		this.companyOwnerId = companyOwnerId;
	}



	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}


	public Set getUserNotes() {
		return this.userNotes;
	}

	public void setUserNotes(Set<UserNote> userNotes) {
		this.userNotes = userNotes;
	}
	
	/**
	 * Updates the underlying collection with the UserNote object that is being passed in.
	 * The note is added if its does note exist and updated if its does exist.
	 * @param note
	 */
	public void saveNote(UserNote note) {
		userNotes.add(note);
	}
	
	/**
	 * Removes the note object from the underlying collection.
	 * @param note
	 */
	public void removeNote(UserNote note) {
		userNotes.remove(note);
	}
	
	/**
	 * Returns the note object for the specified noteId.
	 * @param noteId
	 * @return
	 */
	public UserNote getUserNote(String noteId) {
		UserNote nt = null;
		
		Iterator<UserNote> it = this.userNotes.iterator();
		while (it.hasNext()) {
			nt = it.next();
			if (nt.getUserNoteId().equals(noteId))
				return nt;
		}
		
		return nt;
	}
	


	public Map<String,UserAttribute> getUserAttributes() {
		return this.userAttributes;
	}

	public void setUserAttributes(Map<String,UserAttribute> userAttributeses) {
		this.userAttributes = userAttributeses;
	}

	
	/**
	 * Updates the underlying collection with the UserAttribute object that is being passed in.
	 * The attribute is added if its does not exist and updated if its does exist.
	 * @param attr
	 */
	public void saveAttribute(UserAttribute attr) {
		userAttributes.put(attr.getName(),attr); 
	}
	
	/**
	 * Removes the attribute object from the underlying collection.
	 * @param attr
	 */
	public void removeAttributes(UserAttribute attr) {
		userAttributes.remove(attr.getName());
	}
	
	/**
	 * Returns the attribute object that is specified by the NAME parameter. 
	 * @param name - The attribute map is keyed on the NAME property.
	 * @return
	 */
	public UserAttribute getAttribute(String name) {
	
		return userAttributes.get(name);

	}

	/**
	 * Returns a Set of addresses. Map is keyed on the Address.description value. This
	 * value should indicate the type of address; HOME, SHIPPING, BILLING, etc.
	 * @return
	 */
	public Set<Address> getAddresses() {
		return addresses;
	}

	/**
	 * Sets a Set of addresses with a user. Map is keyed on the Address.description value.
	 * @param addressMap
	 */
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Address getAddressByName(String name) {
		Iterator<Address> addressIt = addresses.iterator();
	 	while (addressIt.hasNext()) {
	 		Address adr = addressIt.next();
	 		if (adr.getName() != null && adr.getName().equalsIgnoreCase(name)) {
	 			return adr;
	 		}
	 	}	
	 	return null;
	}
	
	public Set<EmailAddress> getEmailAddress() {
		return emailAddresses;
	}

	public void setEmailAddress(Set<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	
	public EmailAddress getEmailByName(String name) {
		Iterator<EmailAddress> emailIt = emailAddresses.iterator();
	 	while (emailIt.hasNext()) {
	 		EmailAddress em = emailIt.next();
	 		if (em.getName() != null && em.getName().equalsIgnoreCase(name)) {
	 			return em;
	 		}
	 	}	
	 	return null;
	}

	public Set<Phone> getPhone() {
		return phones;
	}

	public void setPhone(Set<Phone> phones) {
		this.phones = phones;
	}
	
	public Phone getPhoneByName(String name) {
		Iterator<Phone> phoneIt = phones.iterator();
	 	while (phoneIt.hasNext()) {
	 		Phone ph = phoneIt.next();
	 		if (ph.getName() != null && ph.getName().equalsIgnoreCase(name)) {
	 			return ph;
	 		}
	 	}	
	 	return null;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBldgNum() {
		return bldgNum;
	}

	public void setBldgNum(String bldgNum) {
		this.bldgNum = bldgNum;
	}

	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
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

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAddress5() {
		return address5;
	}

	public void setAddress5(String address5) {
		this.address5 = address5;
	}

	public String getAddress6() {
		return address6;
	}

	public void setAddress6(String address6) {
		this.address6 = address6;
	}

	public String getAddress7() {
		return address7;
	}

	public void setAddress7(String address7) {
		this.address7 = address7;
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

	public String getPostalCd() {
		return postalCd;
	}

	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getPhoneExt() {
		return phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	/*
	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
	*/

	/*public Set<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	public UserStatusEnum getSecondaryStatus() {
		return secondaryStatus;
	}

	public void setSecondaryStatus(UserStatusEnum secondaryStatus) {
		this.secondaryStatus = secondaryStatus;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getMetadataTypeId() {
		return metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public String getPasswordTheme() {
		return passwordTheme;
	}

	public void setPasswordTheme(String passwordTheme) {
		this.passwordTheme = passwordTheme;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public Integer getShowInSearch() {
		return showInSearch;
	}

	public void setShowInSearch(Integer showInSearch) {
		this.showInSearch = showInSearch;
	}

	public List<Login> getPrincipalList() {
		return principalList;
	}

	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public String getAlternateContactId() {
		return alternateContactId;
	}

	public void setAlternateContactId(String alternateContactId) {
		this.alternateContactId = alternateContactId;
	}

	public String getSecurityDomain() {
		return securityDomain;
	}

	public void setSecurityDomain(String securityDomain) {
		this.securityDomain = securityDomain;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}


	
}
