package org.openiam.idm.srvc.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openiam.idm.srvc.continfo.dto.*;
//import org.openiam.idm.srvc.continfo.service.AddressDAO;
//import org.openiam.idm.srvc.continfo.service.EmailAddressDAO;
//import org.openiam.idm.srvc.continfo.service.PhoneDAO;
import org.openiam.idm.srvc.user.dto.UserAttributeMapAdapter;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.util.db.Search;

/**
 * Service interface that clients will access to gain information about users and related information.
 * @author Suneet Shah
 * @version 2
 */

@WebService(targetNamespace = "urn:idm.openiam.org/srvc/user/service", name = "UserDataService")
public interface UserDataService {


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUser(java.lang.String, boolean)
	 */
	
	public User getUserWithDependent(String id, boolean dependants);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addUser(org.openiam.idm.srvc.user.dto.User)
	 */
	
	public User addUser(User user);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addUser(org.openiam.idm.srvc.user.dto.User, boolean)
	 */
	
	public User addUserWithDependent(User user, boolean dependency);

	public void updateUser(User user);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateUser(org.openiam.idm.srvc.user.dto.User, boolean)
	 */
	public void updateUserWithDependent(User user, boolean dependency);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeUser(java.lang.String)
	 */
	public void removeUser(String id);

	public List<User> findUsersByLastUpdateRange(Date startDate, Date endDate);

	public List<User> findUserByOrganization(String orgId);

	public List<User> findUsersByStatus(String status);

	public List<User> search(UserSearch search);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public UserAttribute addAttribute(UserAttribute attribute);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public void updateAttribute(UserAttribute attribute);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAttribute(java.lang.String)
	 */
	public UserAttribute getAttribute(String attrId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public void removeAttribute(UserAttribute attr);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllAttributes(java.lang.String)
	 */
	public void removeAllAttributes(String userId);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public UserNote addNote(UserNote note);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public void updateNote(UserNote note);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAllNotes(java.lang.String)
	 */
	public List<UserNote> getAllNotes(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getNote(java.lang.String)
	 */
	public UserNote getNote(java.lang.String noteId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public void removeNote(UserNote note);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllNotes(java.lang.String)
	 */
	public void removeAllNotes(String userId);

	/* ----------- Address Methods ------- */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public void addAddress(Address val);
	
	public void addAddressSet(Set<Address> adrList);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public void updateAddress(Address val);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public void removeAddress(Address val);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllAddresses(java.lang.String)
	 */
	public void removeAllAddresses(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressById(java.lang.String)
	 */
	public Address getAddressById(String addressId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressByName(java.lang.String, java.lang.String)
	 */
	public Address getAddressByName(String userId, String addressName);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultAddress(java.lang.String)
	 */
	public Address getDefaultAddress(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressList(java.lang.String)
	 */
	public List<Address> getAddressList(String userId);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addPhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public void addPhone(Phone val);
	
	public void addPhoneSet(Set<Phone> phoneList);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updatePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public void updatePhone(Phone val);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public void removePhone(Phone val);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllPhones(java.lang.String)
	 */
	public void removeAllPhones(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneById(java.lang.String)
	 */
	public Phone getPhoneById(String addressId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneByName(java.lang.String, java.lang.String)
	 */
	public Phone getPhoneByName(String userId, String addressName);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultPhone(java.lang.String)
	 */
	public Phone getDefaultPhone(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneList(java.lang.String)
	 */
	public List<Phone> getPhoneList(String userId);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public void addEmailAddress(EmailAddress val);
	
	public void addEmailAddressSet(Set<EmailAddress> adrList);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public void updateEmailAddress(EmailAddress val);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public void removeEmailAddress(EmailAddress val);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllEmailAddresses(java.lang.String)
	 */
	public void removeAllEmailAddresses(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressById(java.lang.String)
	 */
	public EmailAddress getEmailAddressById(String addressId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressByName(java.lang.String, java.lang.String)
	 */
	public EmailAddress getEmailAddressByName(String userId,
			String addressName);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultEmailAddress(java.lang.String)
	 */
	public EmailAddress getDefaultEmailAddress(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressList(java.lang.String)
	 */
	public List<EmailAddress> getEmailAddressList(String userId);


	public Supervisor addSupervisor(Supervisor supervisor);

	public void updateSupervisor(Supervisor supervisor);

	public void removeSupervisor(Supervisor supervisor);

	public Supervisor getSupervisor(String supervisorObjId);

	/**
	 * Returns a List of supervisor objects that represents the supervisors for this employee or user.
	 * @param employeeId
	 * @return
	 */
	public List<Supervisor> getSupervisors(String employeeId);

	/**
	 * Returns a list of Supervisor objects that represents the employees or users for this supervisor
	 * @param supervisorId
	 * @return
	 */
	public List<Supervisor> getEmployees(String supervisorId);

	/**
	 * Returns the primary supervisor for this employee. Null if no primary is defined.
	 * @param employeeId
	 * @return
	 */
	public Supervisor getPrimarySupervisor(String employeeId);
	
	// methods with schema conflicts
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUser(java.lang.String)
	 */
	
	//@TODO public User getUser(String id);		

	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneMap(java.lang.String)
	 */
    @WebMethod
    @XmlJavaTypeAdapter(org.openiam.idm.srvc.continfo.dto.PhoneMapAdapter.class) 
	public Map<String, org.openiam.idm.srvc.continfo.dto.Phone> getPhoneMap(String userId);
    
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressMap(java.lang.String)
	 */
    @WebMethod
    @XmlJavaTypeAdapter(org.openiam.idm.srvc.continfo.dto.EmailAddressMapAdapter.class)
	public Map<String, org.openiam.idm.srvc.continfo.dto.EmailAddress> getEmailAddressMap(
			@WebParam(name = "userId", targetNamespace = "")
	        java.lang.String userId);
    
    
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressMap(java.lang.String)
	 */
    @WebMethod
    @XmlJavaTypeAdapter(org.openiam.idm.srvc.continfo.dto.AddressMapAdapter.class) 
	public Map<String, org.openiam.idm.srvc.continfo.dto.Address> getAddressMap(String userId);
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUserAsMap(java.lang.String)
	 */
    @WebMethod
    @XmlJavaTypeAdapter(org.openiam.idm.srvc.user.dto.UserAttributeMapAdapter.class) 
	public Map<String, org.openiam.idm.srvc.user.dto.UserAttribute> getUserAsMap(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAllAttributes(java.lang.String)
	 */
	
    @WebMethod
    @XmlJavaTypeAdapter(org.openiam.idm.srvc.user.dto.UserAttributeMapAdapter.class) 
	public Map<String, org.openiam.idm.srvc.user.dto.UserAttribute> getAllAttributes(String userId);
	
	/*
	@WebMethod(exclude=true)
	public UserDAO getUserDao();

	@WebMethod(exclude=true)
	public void setUserDao(UserDAO userDao);

	@WebMethod(exclude=true)
	public UserAttributeDAO getUserAttributeDao();

	@WebMethod(exclude=true)
	public void setUserAttributeDao(UserAttributeDAO userAttributeDao);

	@WebMethod(exclude=true)
	public UserNoteDAO getUserNoteDao();

	@WebMethod(exclude=true)
	public void setUserNoteDao(UserNoteDAO userNoteDao);

	@WebMethod(exclude=true)
	public AddressDAO getAddressDao();

	@WebMethod(exclude=true)
	public void setAddressDao(AddressDAO addressDao);

	@WebMethod(exclude=true)
	public EmailAddressDAO getEmailAddressDao();

	@WebMethod(exclude=true)
	public void setEmailAddressDao(EmailAddressDAO emailAddressDao);

	@WebMethod(exclude=true)
	public PhoneDAO getPhoneDao();

	@WebMethod(exclude=true)
	public void setPhoneDao(PhoneDAO phoneDao);

	@WebMethod(exclude=true)
	public SupervisorDAO getSupervisorDao();

	@WebMethod(exclude=true)
	public void setSupervisorDao(SupervisorDAO supervisorDao);
	*/
}