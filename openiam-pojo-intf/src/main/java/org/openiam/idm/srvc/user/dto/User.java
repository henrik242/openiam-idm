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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.base.BaseConstants;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.role.dto.Role;

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
	"delAdmin",
	"areaCd",       
	"countryCd",
	"phoneNbr",
	"phoneExt",
	"principalList",
	"supervisor",
	"alternateContactId",
	"securityDomain",
	"userOwnerId",
	"datePasswordChanged",
	"dateChallengeRespChanged"
})
@XmlSeeAlso({
    Login.class,
    Supervisor.class,
    UserNote.class,
    Phone.class,
    Address.class,
    EmailAddress.class,
    UserAttribute.class    
})
	public class User extends org.openiam.base.BaseObject  {

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
	protected Integer delAdmin;
	
	protected List<Login> principalList;
	protected Supervisor supervisor;
	protected String alternateContactId;
	//@XmlElement(name="securityDomain",	namespace = "urn:idm.openiam.org/srvc/user/dto") 
	protected String securityDomain;

	protected String userOwnerId;
    @XmlSchemaType(name = "dateTime")
	protected Date datePasswordChanged;
    @XmlSchemaType(name = "dateTime")
	protected Date dateChallengeRespChanged;
	    


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
		this.userAttributes = userAttributeses ;
		
		//this.userAttributes = userAttributeses;
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
		this.addresses =  addresses ;
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
		this.phones =  phones ;
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
	
	public Phone getPhoneById(String id) {
		Iterator<Phone> phoneIt = phones.iterator();
	 	while (phoneIt.hasNext()) {
	 		Phone ph = phoneIt.next();
	 		if (ph.getName() != null && ph.getName().equalsIgnoreCase(id)) {
	 			return ph;
	 		}
	 	}	
	 	return null;		
	}
	public Address getAddressById(String id) {
		Iterator<Address> addressIt = addresses.iterator();
	 	while (addressIt.hasNext()) {
	 		Address adr = addressIt.next();
	 		if (adr.getName() != null && adr.getName().equalsIgnoreCase(id)) {
	 			return adr;
	 		}
	 	}	
	 	return null;		
	}
	
	public EmailAddress getEmailAddressById(String id) {
		Iterator<EmailAddress> emailIt = emailAddresses.iterator();
	 	while (emailIt.hasNext()) {
	 		EmailAddress em = emailIt.next();
	 		if (em.getName() != null && em.getName().equalsIgnoreCase(id)) {
	 			return em;
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

	public void updateUser(User newUser) {
		if (newUser.getAddress1() != null) {
			if (newUser.getAddress1().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address1 = null;
			}else {
				this.address1 = newUser.getAddress1 ();
			}
		}
		if (newUser.getAddress2() != null ) {
			if (newUser.getAddress2().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address2 = null;
			}else {
				this.address2 = newUser.getAddress2 ();
			}
		}
		if (newUser.getAddress3() != null) {
			if (newUser.getAddress3().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address3 = null;
			}else {
				this.address3 = newUser.getAddress3 ();
			}
		}
		if (newUser.getAddress4() != null) {
			if (newUser.getAddress4().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address4 = null;
			}else {
				this.address4 = newUser.getAddress4 ();
			}
		}
		if (newUser.getAddress5() != null) {
			if (newUser.getAddress5().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address5 = null;
			}else {
				this.address5 = newUser.getAddress5 ();
			}
		}
		if (newUser.getAddress6() != null) {
			if (newUser.getAddress6().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address6 = null;
			}else {
				this.address6 = newUser.getAddress6 ();
			}
		}
		if (newUser.getAddress7() != null) {
			if (newUser.getAddress7().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.address7 = null;
			}else {
				this.address7 = newUser.getAddress7 ();
			}
		}
		if (newUser.getAreaCd() != null) {
			if (newUser.getAreaCd().equalsIgnoreCase(BaseConstants.NULL_STRING)) {
				this.areaCd  = null;
			}else {
				this.areaCd = newUser.getAreaCd ();
			}
		}
		if (newUser.getBirthdate() != null) {
			if (newUser.getBirthdate().equals(BaseConstants.NULL_DATE)) {
				this.birthdate = null;
			}else {
				this.birthdate = newUser.getBirthdate ();
			}
		}
		if (newUser.getBldgNum() != null) {
						if (newUser.getBldgNum().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
							this.bldgNum = null;
						}else {
							this.bldgNum = newUser.getBldgNum ();
						}
		}
		if (newUser.getCity() != null) {
						if (newUser.getCity().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
							this.city = null;
						}else {
							this.city = newUser.getCity ();
						}
		}
		if (newUser.getClassification() != null) {
			if (newUser.getClassification().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
				this.classification = null;
			} else {
				this.classification = newUser.getClassification ();
			}
		}
		if (newUser.getCompanyId() != null) {
			if (newUser.getCompanyId().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
				this.companyId = null;
			}else {
				this.companyId = newUser.getCompanyId ();
			}
		}
		if (newUser.getCostCenter() != null) {
			if (newUser.getCostCenter().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
				this.costCenter = null;
			}else {
				this.costCenter = newUser.getCostCenter ();
			}
		}
		if (newUser.getCountry() != null) {
			if (newUser.getCountry().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
				this.country = null;
			}else {
				this.country = newUser.getCountry ();
			}
		}
		if (newUser.getCountryCd() != null) {
			if (newUser.getCountryCd().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
				this.countryCd = null;
			}else {
				this.countryCd = newUser.getCountryCd ();
			}
		}
		if (newUser.getDeptCd() != null) {
			if (newUser.getDeptCd().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
				this.deptCd = null;
			}else {
				this.deptCd = newUser.getDeptCd ();
			}
		}
		if (newUser.getDeptName() != null) {
				if (newUser.getDeptName().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.deptName = null;
				}else {
					this.deptName = newUser.getDeptName ();
				}
		}
		if (newUser.getDivision() != null) {
				if (newUser.getDivision().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.division = null;
				}else {
					this.division = newUser.getDivision ();
				}
		}
		if (newUser.getEmail() != null) {
				if (newUser.getEmail().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.email = null;
				}else {
					this.email = newUser.getEmail ();
				}
		}
		
		if (newUser.getEmployeeId() != null) {
				if (newUser.getEmployeeId().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.employeeId = null;
				}else {
					this.employeeId = newUser.getEmployeeId ();
				}
		}
		if (newUser.getEmployeeType() != null) {
				if (newUser.getEmployeeType().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.employeeType = null;
				}else {
					this.employeeType = newUser.getEmployeeType ();
				}
		}
		if (newUser.getFirstName() != null) {
				if (newUser.getFirstName().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.firstName = null;
				}else {
					this.firstName = newUser.getFirstName ();
				}
		}
		if (newUser.getJobCode() != null) {
				if (newUser.getJobCode().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.jobCode = null; 
				}else {
					this.jobCode = newUser.getJobCode ();
				}
		}
		if (newUser.getLastName() != null) {
				if (newUser.getLastName().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.lastName = null;
				}else {
					this.lastName = newUser.getLastName ();
				}
		}
		if (newUser.getLastDate() != null) {
				if (newUser.getLastDate().equals(BaseConstants.NULL_DATE)) { 
					this.lastDate = null;
				}else {
					this.lastDate = newUser.getLastDate ();
				}
		}
		if (newUser.getLocationCd() != null) {
				if (newUser.getLocationCd().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.locationCd =null;
				}else {
					this.locationCd = newUser.getLocationCd ();
				}
		}
		if (newUser.getLocationName() != null) {
				if (newUser.getLocationName().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.locationName = null;
				}else {
					this.locationName = newUser.getLocationName ();
				}
		}
		if (newUser.getMaidenName() != null) {
				if (newUser.getMaidenName().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.maidenName = null;
				}else {
					this.maidenName = newUser.getMaidenName ();
				}
		}
		if (newUser.getMailCode() != null) {
				if (newUser.getMailCode().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.mailCode = newUser.getMailCode ();
				}else {
					this.mailCode = null;
				}
		}
		if (newUser.getMetadataTypeId() != null) {
				if (newUser.getMetadataTypeId().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.metadataTypeId = null;
				}else {
					this.metadataTypeId = newUser.getMetadataTypeId ();
				}
		}
		if (newUser.getMiddleInit() != null) {
				if (newUser.getMiddleInit().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.middleInit = null;
				}else {
					this.middleInit = newUser.getMiddleInit ();
				}
		}
		if (newUser.getNickname() != null) {
				if (newUser.getNickname().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.nickname = null;
				}else {
					this.nickname = newUser.getNickname ();
				}
		}
		if (newUser.getPasswordTheme() != null) {
				if (newUser.getPasswordTheme().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.passwordTheme = null;
				}else {
					this.passwordTheme = newUser.getPasswordTheme ();
				}
		}
		if (newUser.getPhoneExt() != null) {
				if (newUser.getPhoneExt().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.phoneExt = null;
				}else {
					this.phoneExt = newUser.getPhoneExt ();
				}
		}
		if (newUser.getPhoneNbr() != null) {
				if (newUser.getPhoneNbr().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.phoneNbr = null;
				}else {
					this.phoneNbr = newUser.getPhoneNbr ();
				}
		}
		if (newUser.getPostalCd() != null) {
				if (newUser.getPostalCd().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.postalCd = null;
				}else {
					this.postalCd = newUser.getPostalCd ();
				}
		}
		if (newUser.getPrefix() != null) {
				if (newUser.getPrefix().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.prefix = null;
				}else {
					this.prefix = newUser.getPrefix ();
				}
		}
		if (newUser.getSecondaryStatus() != null) {
				this.secondaryStatus = newUser.getSecondaryStatus ();
		}
		if (newUser.getSex() != null) {
				if (newUser.getSex().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.sex = null;
				}else {
					this.sex = newUser.getSex ();
				}
		}
		if (newUser.getStartDate() != null) {
				if (newUser.getStartDate().equals(BaseConstants.NULL_DATE)) { 
					this.startDate = null;
				}else {
					this.startDate = newUser.getStartDate ();
				}
		}
		
		if (newUser.getStatus() != null) {
				 this.status = newUser.getStatus ();
		}
		if (newUser.getStreetDirection() != null) {
				if (newUser.getStreetDirection().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.streetDirection = null;
				}else {
					this.streetDirection = newUser.getStreetDirection ();
				}
		}
		if (newUser.getState() != null) {
						if (newUser.getState().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
							this.state = null;
						}else {
							this.state = newUser.getState ();
						}
		}
		if (newUser.getSuffix() != null) {
						if (newUser.getSuffix().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
							this.suffix = null;
						}else {
							this.suffix = newUser.getSuffix ();
						}
		}
		if (newUser.getShowInSearch() != null) {
				if (newUser.getShowInSearch().equals(BaseConstants.NULL_INTEGER)) { 
					this.showInSearch = 0;
				}else {
					this.showInSearch = newUser.getShowInSearch();
				}
		}
		if (newUser.getTitle() != null) {
				if (newUser.getTitle().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.title = null;
				}else {
					this.title = newUser.getTitle ();
				}
		}
		if (newUser.getUserTypeInd() != null) {
				if (newUser.getUserTypeInd().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.userTypeInd = null;
				}else {
					this.userTypeInd = newUser.getUserTypeInd ();
				}
		}
		if (newUser.getManagerId() != null) {
				if (newUser.getManagerId().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.managerId = null;
				}else {
					this.managerId = newUser.getManagerId ();
				}
		}
		if (newUser.getAlternateContactId() != null) {
				if (newUser.getAlternateContactId().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.alternateContactId = null;
				}else {
					this.alternateContactId = newUser.getAlternateContactId ();
				}
		}
		if (newUser.getDelAdmin() != null) {
				if (newUser.getDelAdmin().equals(BaseConstants.NULL_INTEGER)) { 
					this.delAdmin  = 0;
				}else {
					this.delAdmin  = newUser.getDelAdmin();;
				}
		}
		if (newUser.getUserOwnerId() != null) {
				if (newUser.getUserOwnerId().equalsIgnoreCase(BaseConstants.NULL_STRING)) { 
					this.userOwnerId = null;
				}else {
					this.userOwnerId = newUser.getUserOwnerId();
				}
		}
		if (newUser.getDateChallengeRespChanged() != null) {
				if (newUser.getDateChallengeRespChanged().equals(BaseConstants.NULL_DATE)) { 
					this.dateChallengeRespChanged = null;
				}else {
					this.dateChallengeRespChanged = newUser.getDateChallengeRespChanged();
				}
		}
		if (newUser.getDatePasswordChanged() != null) {
				if (newUser.getDatePasswordChanged().equals(BaseConstants.NULL_DATE)) { 
					this.datePasswordChanged = null;
				}else {
					this.datePasswordChanged = newUser.getDatePasswordChanged();
				}
		}
		
		// check the attributes
		if (newUser.getUserAttributes() != null) {
			updateAttributes(newUser.getUserAttributes());
		}
	}
	
	private void updateAttributes(Map<String, UserAttribute> attrMap) {
		Set<String> keySet = attrMap.keySet();
		for ( String s :keySet) {
			 UserAttribute origAttr = userAttributes.get(s);
			 UserAttribute newAttr = attrMap.get(s);
			 if (newAttr.getOperation() == AttributeOperationEnum.NO_CHANGE) {
				 continue;
			 }else if (newAttr.getOperation() == AttributeOperationEnum.ADD) {
				 userAttributes.put(newAttr.getName(), newAttr);
			 }else if (newAttr.getOperation() == AttributeOperationEnum.DELETE) {
				 userAttributes.remove(origAttr.getName());
			 }else if (newAttr.getOperation() == AttributeOperationEnum.REPLACE) {
				 origAttr.setOperation(AttributeOperationEnum.REPLACE);
				 origAttr.setValue(newAttr.getValue());
				 userAttributes.put(origAttr.getName(), origAttr);				 
			 }
			 
		}
	}

	public Integer getDelAdmin() {
		return delAdmin;
	}

	public void setDelAdmin(Integer delAdmin) {
		this.delAdmin = delAdmin;
	}

	public String getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(String userOwnerId) {
		this.userOwnerId = userOwnerId;
	}

	public Date getDatePasswordChanged() {
		return datePasswordChanged;
	}

	public void setDatePasswordChanged(Date datePasswordChanged) {
		this.datePasswordChanged = datePasswordChanged;
	}

	public Date getDateChallengeRespChanged() {
		return dateChallengeRespChanged;
	}

	public void setDateChallengeRespChanged(Date dateChallengeRespChanged) {
		this.dateChallengeRespChanged = dateChallengeRespChanged;
	}
	

	
}
