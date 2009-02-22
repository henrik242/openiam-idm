package org.openiam.idm.srvc.user.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.openiam.idm.srvc.meta.dto.*;
import org.openiam.idm.srvc.org.dto.OrganizationAttributeMapAdapter;
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
    "middleInit",
    "phones",
    "prefix",
    "sex",
    "status",
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
	"passwordTheme"
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
    //protected EmailAddressMap emailAddresses; see below
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
    //protected MetadataType metadataType;
    protected String metadataTypeId;
    protected String middleInit;
    //protected PhoneMap phones;
    protected String prefix;
    protected String sex;
    protected String status;
    protected String suffix;
    protected String title;
    //protected UserAttributeMap userAttributes; 	
    protected String userId;
    protected String userTypeInd;
    protected String division;
    protected String mailCode;
    
	private String costCenter;
    @XmlSchemaType(name = "dateTime")
	private Date startDate;
    @XmlSchemaType(name = "dateTime")
	private Date lastDate;
    
	private String nickname;
	private String maidenName;
	private String passwordTheme;

   
    

	//private Set userGrps = new HashSet(0);
    


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

	@XmlJavaTypeAdapter(UserNoteSetAdapter.class)
	protected Set<UserNote> userNotes = new HashSet(0);
	//private Set<Group> groups = new HashSet<Group>(0);


	@XmlJavaTypeAdapter(UserAttributeMapAdapter.class)
	protected Map<String, UserAttribute> userAttributes = new HashMap<String, UserAttribute>(0);

	@XmlJavaTypeAdapter(AddressMapAdapter.class)
	//protected Map<String, Address> addresses = new HashMap<String,Address>(0);
	protected Set<Address> addresses = new HashSet<Address>(0);

	@XmlJavaTypeAdapter(PhoneMapAdapter.class)
	//protected Map<String, Phone> phones = new HashMap<String,Phone>(0);
	protected Set<Phone> phones = new HashSet<Phone>(0);
	
	@XmlJavaTypeAdapter(EmailAddressMapAdapter.class)
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<EmailAddress> getEmailAddress() {
		return emailAddresses;
	}

	public void setEmailAddress(Set<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public Set<Phone> getPhone() {
		return phones;
	}

	public void setPhone(Set<Phone> phones) {
		this.phones = phones;
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



}
