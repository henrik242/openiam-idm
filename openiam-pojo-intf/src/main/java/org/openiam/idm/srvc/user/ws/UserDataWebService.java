package org.openiam.idm.srvc.user.ws;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.continfo.ws.AddressListResponse;
import org.openiam.idm.srvc.continfo.ws.AddressMapResponse;
import org.openiam.idm.srvc.continfo.ws.AddressResponse;
import org.openiam.idm.srvc.continfo.ws.EmailAddressListResponse;
import org.openiam.idm.srvc.continfo.ws.EmailAddressMapResponse;
import org.openiam.idm.srvc.continfo.ws.EmailAddressResponse;
import org.openiam.idm.srvc.continfo.ws.PhoneListResponse;
import org.openiam.idm.srvc.continfo.ws.PhoneMapResponse;
import org.openiam.idm.srvc.continfo.ws.PhoneResponse;
//import org.openiam.idm.srvc.continfo.service.AddressDAO;
//import org.openiam.idm.srvc.continfo.service.EmailAddressDAO;
//import org.openiam.idm.srvc.continfo.service.PhoneDAO;
import org.openiam.idm.srvc.user.dto.UserAttributeMapAdapter;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.dto.UserSearch;
/**
 * WebService interface that clients will access to gain information about users and related information.
 * @author Suneet Shah
 * @version 2
 */

@WebService(targetNamespace = "urn:idm.openiam.org/srvc/user/service", name = "UserDataService")
public interface UserDataWebService {


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUser(java.lang.String, boolean)
	 */
	@WebMethod
	public UserResponse getUserWithDependent(
			 @WebParam(name = "id", targetNamespace = "")
			String id, 
			 @WebParam(name = "dependants", targetNamespace = "")
			boolean dependants);
	
	@WebMethod
	public UserResponse getUserByPrincipal(
			@WebParam(name="securityDomain", targetNamespace="")
			String securityDomain, 
			@WebParam(name="principal", targetNamespace="")
			String principal, 
			@WebParam(name="managedSysId", targetNamespace="")
			String managedSysId, 
			@WebParam(name="dependants", targetNamespace="")
			boolean dependants);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addUser(org.openiam.idm.srvc.user.dto.User)
	 */
	@WebMethod
	public UserResponse addUser(
			@WebParam(name = "user", targetNamespace = "")
			User user);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addUser(org.openiam.idm.srvc.user.dto.User, boolean)
	 */
	
	@WebMethod
	public UserResponse addUserWithDependent(
			@WebParam(name = "user", targetNamespace = "")
			User user, 
			@WebParam(name = "dependency", targetNamespace = "")
			boolean dependency);

	@WebMethod
	public Response updateUser(User user);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateUser(org.openiam.idm.srvc.user.dto.User, boolean)
	 */
	@WebMethod
	public Response updateUserWithDependent(
			@WebParam(name = "user", targetNamespace = "")
			User user, 
			@WebParam(name = "dependency", targetNamespace = "")
			boolean dependency);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeUser(java.lang.String)
	 */
	@WebMethod
	public Response removeUser(
			@WebParam(name = "id", targetNamespace = "")
			String id);
	
	
	@WebMethod
	public UserResponse getUserByName(
			@WebParam(name = "firstName", targetNamespace = "")
			String firstName, 
			@WebParam(name = "lastName", targetNamespace = "")
			String lastName);

	@WebMethod
	public UserListResponse findUsersByLastUpdateRange(
			@WebParam(name = "startDate", targetNamespace = "")
			Date startDate, 
			@WebParam(name = "endDate", targetNamespace = "")
			Date endDate);

	@WebMethod
	public UserListResponse findUserByOrganization(
			@WebParam(name = "orgId", targetNamespace = "")
			String orgId);

	@WebMethod
	public UserListResponse findUsersByStatus(
			@WebParam(name = "status", targetNamespace = "")
			String status);

	@WebMethod
	public UserListResponse search(
			@WebParam(name = "search", targetNamespace = "")
			UserSearch search);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	@WebMethod
	public UserAttributeResponse addAttribute(
			@WebParam(name = "attribute", targetNamespace = "")
			UserAttribute attribute);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	@WebMethod
	public Response updateAttribute(
			@WebParam(name = "attribute", targetNamespace = "")
			UserAttribute attribute);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAttribute(java.lang.String)
	 */
	@WebMethod
	public UserAttributeResponse getAttribute(
			@WebParam(name = "attrId", targetNamespace = "")
			String attrId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	@WebMethod
	public Response removeAttribute(
			@WebParam(name = "attr", targetNamespace = "")
			UserAttribute attr);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllAttributes(java.lang.String)
	 */
	@WebMethod
	public Response removeAllAttributes(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	@WebMethod
	public UserNoteResponse addNote(
			@WebParam(name = "note", targetNamespace = "")
			UserNote note);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	@WebMethod
	public Response updateNote(
			@WebParam(name = "note", targetNamespace = "")
			UserNote note);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAllNotes(java.lang.String)
	 */
	@WebMethod
	public UserNoteListResponse getAllNotes(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getNote(java.lang.String)
	 */
	@WebMethod
	public UserNoteResponse getNote(
			@WebParam(name = "noteId", targetNamespace = "")
			java.lang.String noteId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	@WebMethod
	public Response removeNote(
			@WebParam(name = "note", targetNamespace = "")
			UserNote note);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllNotes(java.lang.String)
	 */
	@WebMethod
	public Response removeAllNotes(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* ----------- Address Methods ------- */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	@WebMethod
	public Response addAddress(
			@WebParam(name = "address", targetNamespace = "")
			Address address);
	
	@WebMethod
	public Response addAddressSet(
			@WebParam(name = "addressSet", targetNamespace = "")
			Set<Address> addressSet);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	@WebMethod
	public Response updateAddress(
			@WebParam(name = "address", targetNamespace = "")
			Address address);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	@WebMethod
	public Response removeAddress(
			@WebParam(name = "address", targetNamespace = "")
			Address address);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllAddresses(java.lang.String)
	 */
	@WebMethod
	public Response removeAllAddresses(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressById(java.lang.String)
	 */
	@WebMethod
	public AddressResponse getAddressById(
			@WebParam(name = "addressId", targetNamespace = "")
			String addressId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressByName(java.lang.String, java.lang.String)
	 */
	@WebMethod
	public AddressResponse getAddressByName(
			@WebParam(name = "userId", targetNamespace = "")
			String userId, 
			@WebParam(name = "addressName", targetNamespace = "")
			String addressName);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultAddress(java.lang.String)
	 */
	@WebMethod
	public AddressResponse getDefaultAddress(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressList(java.lang.String)
	 */
	@WebMethod
	public AddressListResponse getAddressList(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addPhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	@WebMethod
	public Response addPhone(
			@WebParam(name = "phone", targetNamespace = "")
			Phone phone);
	@WebMethod
	public Response addPhoneSet(Set<Phone> phoneList);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updatePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	@WebMethod
	public Response updatePhone(
			@WebParam(name = "phone", targetNamespace = "")
			Phone phone);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	@WebMethod
	public Response removePhone(
			@WebParam(name = "phone", targetNamespace = "")
			Phone phone);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllPhones(java.lang.String)
	 */
	@WebMethod
	public Response removeAllPhones(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneById(java.lang.String)
	 */
	@WebMethod
	public PhoneResponse getPhoneById(
			@WebParam(name = "addressId", targetNamespace = "")
			String addressId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneByName(java.lang.String, java.lang.String)
	 */
	@WebMethod
	public PhoneResponse getPhoneByName(
			@WebParam(name = "userId", targetNamespace = "")
			String userId, 
			@WebParam(name = "addressName", targetNamespace = "")
			String addressName);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultPhone(java.lang.String)
	 */
	@WebMethod
	public PhoneResponse getDefaultPhone(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneList(java.lang.String)
	 */
	@WebMethod
	public PhoneListResponse getPhoneList(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	@WebMethod
	public Response addEmailAddress(
			@WebParam(name = "email", targetNamespace = "")
			EmailAddress email);
	@WebMethod
	public Response addEmailAddressSet(
			@WebParam(name = "emailAddressSet", targetNamespace = "")
			Set<EmailAddress> emailAddressSet);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	@WebMethod
	public Response updateEmailAddress(
			@WebParam(name = "email", targetNamespace = "")
			EmailAddress email);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	@WebMethod
	public Response removeEmailAddress(
			@WebParam(name = "email", targetNamespace = "")
			EmailAddress email);
	

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllEmailAddresses(java.lang.String)
	 */
	@WebMethod
	public Response removeAllEmailAddresses(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressById(java.lang.String)
	 */
	@WebMethod
	public EmailAddressResponse getEmailAddressById(
			@WebParam(name = "addressId", targetNamespace = "")
			String addressId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressByName(java.lang.String, java.lang.String)
	 */
	@WebMethod
	public EmailAddressResponse getEmailAddressByName(
			@WebParam(name = "userId", targetNamespace = "")
			String userId,
			@WebParam(name = "addressName", targetNamespace = "")
			String addressName);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultEmailAddress(java.lang.String)
	 */
	@WebMethod
	public EmailAddressResponse getDefaultEmailAddress(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressList(java.lang.String)
	 */
	@WebMethod
	public EmailAddressListResponse getEmailAddressList(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);

	@WebMethod
	public SupervisorResponse addSupervisor(
			@WebParam(name = "supervisor", targetNamespace = "")
			Supervisor supervisor);
	
	@WebMethod
	public Response updateSupervisor(
			@WebParam(name = "supervisor", targetNamespace = "")
			Supervisor supervisor);

	@WebMethod
	public Response removeSupervisor(
			@WebParam(name = "supervisor", targetNamespace = "")
			Supervisor supervisor);

	@WebMethod
	public SupervisorResponse getSupervisor(
			@WebParam(name = "supervisorObjId", targetNamespace = "")
			String supervisorObjId);

	/**
	 * Returns a List of supervisor objects that represents the supervisors for this employee or user.
	 * @param employeeId
	 * @return
	 */
	@WebMethod
	public SupervisorListResponse getSupervisors(
			@WebParam(name = "employeeId", targetNamespace = "")
			String employeeId);

	/**
	 * Returns a list of Supervisor objects that represents the employees or users for this supervisor
	 * @param supervisorId
	 * @return
	 */
	@WebMethod
	public SupervisorListResponse getEmployees(
			@WebParam(name = "supervisorId", targetNamespace = "")
			String supervisorId);

	/**
	 * Returns the primary supervisor for this employee. Null if no primary is defined.
	 * @param employeeId
	 * @return
	 */
	@WebMethod
	public SupervisorResponse getPrimarySupervisor(
			@WebParam(name = "employeeId", targetNamespace = "")
			String employeeId);
	
	// methods with schema conflicts
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUser(java.lang.String)
	 */
	
	//@TODO public User getUser(String id);		

	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneMap(java.lang.String)
	 */
	@WebMethod
	public PhoneMapResponse getPhoneMap(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);
    
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressMap(java.lang.String)
	 */
	@WebMethod
	public EmailAddressMapResponse getEmailAddressMap(
			@WebParam(name = "userId", targetNamespace = "")
	        java.lang.String userId);
    
    
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressMap(java.lang.String)
	 */

	@WebMethod
	public AddressMapResponse getAddressMap(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);
	
	

}