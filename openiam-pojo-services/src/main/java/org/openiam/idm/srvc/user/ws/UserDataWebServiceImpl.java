/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.user.ws;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.continfo.ws.AddressListResponse;
import org.openiam.idm.srvc.continfo.ws.AddressMapResponse;
import org.openiam.idm.srvc.continfo.ws.AddressResponse;
import org.openiam.idm.srvc.continfo.ws.EmailAddressListResponse;
import org.openiam.idm.srvc.continfo.ws.EmailAddressMapResponse;
import org.openiam.idm.srvc.continfo.ws.EmailAddressResponse;
import org.openiam.idm.srvc.continfo.ws.PhoneListResponse;
import org.openiam.idm.srvc.continfo.ws.PhoneMapResponse;
import org.openiam.idm.srvc.continfo.ws.PhoneResponse;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.service.UserDataService;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.user.ws.UserDataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/user/service", 
		serviceName = "UserDataWebService",
		portName = "UserDataWebServicePort")

public class UserDataWebServiceImpl implements UserDataWebService {

	UserDataService userManager;
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public AddressResponse addAddress(Address val) {
		AddressResponse resp = new AddressResponse(ResponseStatus.SUCCESS);
		userManager.addAddress(val);
		if (val.getAddressId() == null || val.getAddressId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAddress(val);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addAddressSet(java.util.Set)
	 */
	public Response addAddressSet(Set<Address> adrList) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.addAddressSet(adrList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public UserAttributeResponse addAttribute(UserAttribute attribute) {
		UserAttributeResponse resp = new UserAttributeResponse(ResponseStatus.SUCCESS);
		userManager.addAttribute(attribute);
		if (attribute.getId() == null || attribute.getId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAttribute(attribute);;
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public EmailAddressResponse addEmailAddress(EmailAddress val) {
		EmailAddressResponse resp = new EmailAddressResponse(ResponseStatus.SUCCESS);
		userManager.addEmailAddress(val);
		if (val.getEmailId() == null || val.getEmailId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setEmailAddress(val);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addEmailAddressSet(java.util.Set)
	 */
	public Response addEmailAddressSet(Set<EmailAddress> adrList) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.addEmailAddressSet(adrList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public UserNoteResponse addNote(UserNote note) {
		UserNoteResponse resp = new UserNoteResponse(ResponseStatus.SUCCESS);
		userManager.addNote(note);
		if (note.getUserNoteId() == null || note.getUserNoteId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserNote(note);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addPhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public PhoneResponse addPhone(Phone val) {
		PhoneResponse resp = new PhoneResponse(ResponseStatus.SUCCESS);
		userManager.addPhone(val);
		if (val.getPhoneId() == null || val.getPhoneId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPhone(val);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addPhoneSet(java.util.Set)
	 */
	public Response addPhoneSet(Set<Phone> phoneList) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.addPhoneSet(phoneList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addSupervisor(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
	public SupervisorResponse addSupervisor(Supervisor supervisor) {
		SupervisorResponse resp = new SupervisorResponse(ResponseStatus.SUCCESS);
		userManager.addSupervisor(supervisor);
		if (supervisor.getOrgStructureId() == null || supervisor.getOrgStructureId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSupervisor(supervisor);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addUser(org.openiam.idm.srvc.user.dto.User)
	 */
	public UserResponse addUser(User user) {
		UserResponse resp = new UserResponse(ResponseStatus.SUCCESS);
		userManager.addUser(user);
		if (user.getUserId() == null || user.getUserId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUser(user);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#addUserWithDependent(org.openiam.idm.srvc.user.dto.User, boolean)
	 */
	public UserResponse addUserWithDependent(User user, boolean dependency) {
		UserResponse resp = new UserResponse(ResponseStatus.SUCCESS);
		userManager.addUserWithDependent(user, dependency);
		if (user.getUserId() == null || user.getUserId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUser(user);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#findUserByOrganization(java.lang.String)
	 */
	public UserListResponse findUserByOrganization(String orgId) {
		UserListResponse resp = new UserListResponse(ResponseStatus.SUCCESS);
		List<User> userList = userManager.findUserByOrganization(orgId);
		if (userList == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserList(userList);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#findUsersByLastUpdateRange(java.util.Date, java.util.Date)
	 */
	public UserListResponse findUsersByLastUpdateRange(Date startDate,
			Date endDate) {
		UserListResponse resp = new UserListResponse(ResponseStatus.SUCCESS);
		List<User> userList = userManager.findUsersByLastUpdateRange(startDate, endDate);
		if (userList == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserList(userList);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#findUsersByStatus(java.lang.String)
	 */
	public UserListResponse findUsersByStatus(String status) {
		UserListResponse resp = new UserListResponse(ResponseStatus.SUCCESS);
		List<User> userList = userManager.findUsersByStatus(status);
		if (userList == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserList(userList);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getAddressById(java.lang.String)
	 */
	public AddressResponse getAddressById(String addressId) {
		AddressResponse resp = new AddressResponse(ResponseStatus.SUCCESS);
		Address adr = userManager.getAddressById(addressId);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAddress(adr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getAddressByName(java.lang.String, java.lang.String)
	 */
	public AddressResponse getAddressByName(String userId, String addressName) {
		AddressResponse resp = new AddressResponse(ResponseStatus.SUCCESS);
		Address adr = userManager.getAddressByName(userId, addressName);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAddress(adr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getAddressList(java.lang.String)
	 */
	public AddressListResponse getAddressList(String userId) {
		AddressListResponse resp = new AddressListResponse(ResponseStatus.SUCCESS);
		List<Address> adrList = userManager.getAddressList(userId);
		if (adrList == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAddressList(adrList);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getAddressMap(java.lang.String)
	 */
	public AddressMapResponse getAddressMap(String userId) {
		AddressMapResponse resp = new AddressMapResponse(ResponseStatus.SUCCESS);
		Map<String, Address> adrMap = userManager.getAddressMap(userId);
		if (adrMap == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAddressMap(adrMap);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getAllNotes(java.lang.String)
	 */
	public UserNoteListResponse getAllNotes(String userId) {
		UserNoteListResponse resp = new UserNoteListResponse(ResponseStatus.SUCCESS);
		List<UserNote> note = userManager.getAllNotes(userId);
		if (note == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserNoteList(note);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getAttribute(java.lang.String)
	 */
	public UserAttributeResponse getAttribute(String attrId) {
		UserAttributeResponse resp = new UserAttributeResponse(ResponseStatus.SUCCESS);
		UserAttribute userAttr = userManager.getAttribute(attrId);
		if (userAttr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAttribute(userAttr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getDefaultAddress(java.lang.String)
	 */
	public AddressResponse getDefaultAddress(String userId) {
		AddressResponse resp = new AddressResponse(ResponseStatus.SUCCESS);
		Address adr = userManager.getDefaultAddress(userId);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAddress(adr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getDefaultEmailAddress(java.lang.String)
	 */
	public EmailAddressResponse getDefaultEmailAddress(String userId) {
		EmailAddressResponse resp = new EmailAddressResponse(ResponseStatus.SUCCESS);
		EmailAddress adr = userManager.getDefaultEmailAddress(userId);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setEmailAddress(adr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getDefaultPhone(java.lang.String)
	 */
	public PhoneResponse getDefaultPhone(String userId) {
		PhoneResponse resp = new PhoneResponse(ResponseStatus.SUCCESS);
		Phone ph = userManager.getDefaultPhone(userId);
		if (ph == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPhone(ph);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getEmailAddressById(java.lang.String)
	 */
	public EmailAddressResponse getEmailAddressById(String addressId) {
		EmailAddressResponse resp = new EmailAddressResponse(ResponseStatus.SUCCESS);
		EmailAddress adr = userManager.getEmailAddressById(addressId);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setEmailAddress(adr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getEmailAddressByName(java.lang.String, java.lang.String)
	 */
	public EmailAddressResponse getEmailAddressByName(String userId,
			String addressName) {
		EmailAddressResponse resp = new EmailAddressResponse(ResponseStatus.SUCCESS);
		EmailAddress adr = userManager.getEmailAddressByName(userId, addressName);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setEmailAddress(adr);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getEmailAddressList(java.lang.String)
	 */
	public EmailAddressListResponse getEmailAddressList(String userId) {
		EmailAddressListResponse resp = new EmailAddressListResponse(ResponseStatus.SUCCESS);
		List<EmailAddress> adr = userManager.getEmailAddressList(userId);
		if (adr == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setEmailAddressList(adr); 
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getEmailAddressMap(java.lang.String)
	 */
	public EmailAddressMapResponse getEmailAddressMap(String userId) {
		EmailAddressMapResponse resp = new EmailAddressMapResponse(ResponseStatus.SUCCESS);
		Map<String, EmailAddress> adrMap = userManager.getEmailAddressMap(userId);
		if (adrMap == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setEmailMap(adrMap);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getEmployees(java.lang.String)
	 */
	public SupervisorListResponse getEmployees(String supervisorId) {
		SupervisorListResponse resp = new SupervisorListResponse(ResponseStatus.SUCCESS);
		List<Supervisor> sup = userManager.getEmployees(supervisorId);
		if (sup == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSupervisorList(sup);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getNote(java.lang.String)
	 */
	public UserNoteResponse getNote(String noteId) {
		UserNoteResponse resp = new UserNoteResponse(ResponseStatus.SUCCESS);
		UserNote note = userManager.getNote(noteId);
		if (note.getUserNoteId() == null || note.getUserNoteId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserNote(note);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getPhoneById(java.lang.String)
	 */
	public PhoneResponse getPhoneById(String addressId) {
		PhoneResponse resp = new PhoneResponse(ResponseStatus.SUCCESS);
		Phone ph = userManager.getPhoneById(addressId);
		if (ph == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPhone(ph);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getPhoneByName(java.lang.String, java.lang.String)
	 */
	public PhoneResponse getPhoneByName(String userId, String addressName) {
		PhoneResponse resp = new PhoneResponse(ResponseStatus.SUCCESS);
		Phone ph = userManager.getPhoneByName(userId, addressName);
		if (ph == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPhone(ph);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getPhoneList(java.lang.String)
	 */
	public PhoneListResponse getPhoneList(String userId) {
		PhoneListResponse resp = new PhoneListResponse(ResponseStatus.SUCCESS);
		List<Phone> ph = userManager.getPhoneList(userId);
		if (ph == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPhoneList(ph);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getPhoneMap(java.lang.String)
	 */
	public PhoneMapResponse getPhoneMap(String userId) {
		PhoneMapResponse resp = new PhoneMapResponse(ResponseStatus.SUCCESS);
		Map<String, Phone> phoneMap = userManager.getPhoneMap(userId);
		if (phoneMap == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPhoneMap(phoneMap);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getPrimarySupervisor(java.lang.String)
	 */
	public SupervisorResponse getPrimarySupervisor(String employeeId) {
		SupervisorResponse resp = new SupervisorResponse(ResponseStatus.SUCCESS);
		Supervisor sup = userManager.getPrimarySupervisor(employeeId);
		if (sup == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSupervisor(sup);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getSupervisor(java.lang.String)
	 */
	public SupervisorResponse getSupervisor(String supervisorObjId) {
		SupervisorResponse resp = new SupervisorResponse(ResponseStatus.SUCCESS);
		Supervisor sup = userManager.getSupervisor(supervisorObjId);
		if (sup == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSupervisor(sup);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getSupervisors(java.lang.String)
	 */
	public SupervisorListResponse getSupervisors(String employeeId) {
		SupervisorListResponse resp = new SupervisorListResponse(ResponseStatus.SUCCESS);
		List<Supervisor> sup = userManager.getSupervisors(employeeId);
		if (sup == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSupervisorList(sup);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getUserByName(java.lang.String, java.lang.String)
	 */
	public UserResponse getUserByName(String firstName, String lastName) {
		UserResponse resp = new UserResponse(ResponseStatus.SUCCESS);
		User user = userManager.getUserByName(firstName, lastName);
		if (user == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUser(user);
		}
		return resp;
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#getUserWithDependent(java.lang.String, boolean)
	 */
	public UserResponse getUserWithDependent(String id, boolean dependants) {
		UserResponse resp = new UserResponse(ResponseStatus.SUCCESS);
		User user = userManager.getUserWithDependent(id, dependants);
		if (user == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUser(user);
		}
		return resp;
	}
	
	@WebMethod
	public UserResponse getUserByPrincipal(
			String securityDomain, 
			String principal, 
			String managedSysId, 
			boolean dependants) {
		UserResponse resp = new UserResponse(ResponseStatus.SUCCESS);
		User user = userManager.getUserByPrincipal(securityDomain, principal, managedSysId, dependants);
		if (user == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUser(user);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public Response removeAddress(Address val) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAddress(val);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAllAddresses(java.lang.String)
	 */
	public Response removeAllAddresses(String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAllAddresses(userId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAllAttributes(java.lang.String)
	 */
	public Response removeAllAttributes(String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAllAttributes(userId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAllEmailAddresses(java.lang.String)
	 */
	public Response removeAllEmailAddresses(String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAllEmailAddresses(userId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAllNotes(java.lang.String)
	 */
	public Response removeAllNotes(String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAllNotes(userId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAllPhones(java.lang.String)
	 */
	public Response removeAllPhones(String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAllPhones(userId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public Response removeAttribute(UserAttribute attr) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeAttribute(attr);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public Response removeEmailAddress(EmailAddress val) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeEmailAddress(val);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public Response removeNote(UserNote note) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeNote(note);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public Response removePhone(Phone val) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removePhone(val);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeSupervisor(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
	public Response removeSupervisor(Supervisor supervisor) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeSupervisor(supervisor);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#removeUser(java.lang.String)
	 */
	public Response removeUser(String id) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.removeUser(id);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#search(org.openiam.idm.srvc.user.dto.UserSearch)
	 */
	public UserListResponse search(UserSearch search) {
		UserListResponse resp = new UserListResponse(ResponseStatus.SUCCESS);
		List<User> userList = userManager.search(search);
		if (userList == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserList(userList);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public Response updateAddress(Address val) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateAddress(val);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public Response updateAttribute(UserAttribute attribute) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateAttribute(attribute);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public Response updateEmailAddress(EmailAddress val) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateEmailAddress(val);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public Response updateNote(UserNote note) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateNote(note);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updatePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public Response updatePhone(Phone val) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updatePhone(val);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateSupervisor(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
	public Response updateSupervisor(Supervisor supervisor) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateSupervisor(supervisor);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateUser(org.openiam.idm.srvc.user.dto.User)
	 */
	public Response updateUser(User user) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateUser(user);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.user.ws.UserDataWebService#updateUserWithDependent(org.openiam.idm.srvc.user.dto.User, boolean)
	 */
	public Response updateUserWithDependent(User user, boolean dependency) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		userManager.updateUserWithDependent(user, dependency);
		return resp;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}

}
