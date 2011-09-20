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
package org.openiam.provision.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.provision.dto.Application;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.type.ExtensibleAddress;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleEmailAddress;
import org.openiam.provision.type.ExtensibleGroup;
import org.openiam.provision.type.ExtensiblePhone;
import org.openiam.provision.type.ExtensibleRole;
import org.openiam.provision.type.ExtensibleUser;

/**
 * User to extract the full list of attributes in a User object that is used for 
 * provisioning.
 * @author suneet
 *
 */
public class UserAttributeHelper {
	
	protected static final Log log = LogFactory.getLog(UserAttributeHelper.class);
	protected static LoginDataService loginManager;
	
	public static ExtensibleUser newUser(ProvisionUser pUser) throws IllegalArgumentException, 
		IllegalAccessException, 
		InvocationTargetException {
		
		SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
		
		ExtensibleUser extUser = new ExtensibleUser();
		
		if (pUser == null) {
			throw new NullPointerException("Provision user is null");
		}
		// use reflection to the list of attributes that are part of user
		User user = pUser.getUser();
		
		
		Class userClass =  user.getClass();
		Method[] userMethod =  userClass.getDeclaredMethods();
		if (userMethod != null && userMethod.length > 0) {
			for (Method m  : userMethod) {
				String methName = m.getName();
				String returnType = m.getReturnType().getName();
				//log.info("Return type:=" + returnType ) ;
				//log.info("MethodName=" + methName);
				String value = null;
				if (methName.startsWith("get")) {
					if (returnType.contains("String")){
						value = (String) m.invoke(pUser, null);
					}
					if (returnType.contains("Date")) {
						Date temp = (Date)m.invoke(pUser, null);
						if (temp != null) {
							value = df.format(temp);
							//value = temp.toString();
						}
					}
					if (returnType.contains("UserStatusEnum")) {
						UserStatusEnum temp = (UserStatusEnum)m.invoke(pUser, null);
						if (temp != null) {
							
							value = temp.getValue();
						}
					}
					// build the method name - to show the object that the value came from
					// get rid of the "get" in the name
					methName = "USER." + methName.substring(3);
					extUser.getAttributes().add(new ExtensibleAttribute(methName, value, 1, "String"));
				}

			}
		}

		// get the user attributes
		Map<String, UserAttribute> userAttr = (Map<String,UserAttribute>)pUser.getUserAttributes();
		Collection<UserAttribute> attrCol =  userAttr.values();
		for (UserAttribute attr : attrCol) {
			String name = extUser.getName();
			name = "USER_ATTRIBUTE." + attr.getName();
			extUser.getAttributes().add(new ExtensibleAttribute(name, attr.getValue(), attr.getMetadataElementId()));
		}
		// primary identity
		List<Login> principalList = pUser.getPrincipalList();
		if (principalList != null && principalList.size() > 0) {
			for (Login lg  : principalList) {
				try {
					if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY.PRINCIPAL.", lg.getId().getLogin()));
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD.PRINCIPAL", lg.getPassword()));
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_STATUS.PRINCIPAL", lg.getStatus()));
					}else {
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY." + lg.getId().getManagedSysId(), lg.getId().getLogin()));
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD."+lg.getId().getManagedSysId(), lg.getPassword()));
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_STATUS."+lg.getId().getManagedSysId(), lg.getStatus()));

					}
				}catch(Exception e) {
					log.error(e);
					return null;
				}
			}

		}
		/*	Login lg = getPrimaryPrincipal(principalList);
		if (lg != null) {
			extUser.getAttributes().add(new ExtensibleAttribute("PRINCIPAL.IDENTITY", lg.getId().getLogin()));
			extUser.getAttributes().add(new ExtensibleAttribute("PRINCIPAL.IDENTITY_PSWD", lg.getPassword()));
		}
	*/
		
		// groups
		List<Group> groupList = pUser.getMemberOfGroups();
		if (groupList != null) {
			for (Group grp : groupList) {
				ExtensibleGroup extGroup = new ExtensibleGroup(grp);
				log.info("Group being added to extGroup=" + grp);
				extUser.getGroup().add(extGroup);			
			}
		}
		// roles
		List<Role> roleList = pUser.getMemberOfRoles();
		if (roleList != null) {
			for (Role rl : roleList) {
				ExtensibleRole extRole = new ExtensibleRole(rl);
				log.info("Role being added to extRole=" + rl);
				extUser.getRole().add(extRole);			
			}
		}	
		// address
		Set<Address> addressList =  pUser.getAddresses();
		if (addressList != null) {
			Iterator<Address> adrIt =  addressList.iterator();
			while (adrIt.hasNext()) {
				Address adr = adrIt.next();
				ExtensibleAddress extAddress = new ExtensibleAddress(adr);
				extUser.getAddress().add(extAddress);
			}
		}
		
		// email
		Set<EmailAddress> emailAddressList = pUser.getEmailAddress();
		if (emailAddressList != null) {
			Iterator<EmailAddress> emailIt = emailAddressList.iterator();
			while (emailIt.hasNext()) {
				EmailAddress email = emailIt.next();
				ExtensibleEmailAddress extEmail = new ExtensibleEmailAddress(email);
				extUser.getEmail().add(extEmail);
			}
		}
		
		// phone
		Set<Phone> phoneList = pUser.getPhone();
		if (phoneList != null) {
			Iterator<Phone> phoneIt = phoneList.iterator();
			while (phoneIt.hasNext()) {
				Phone phone = phoneIt.next();
				ExtensiblePhone extPhone = new ExtensiblePhone(phone);
				extUser.getPhone().add(extPhone);
			}
		}
		
		
		return extUser;
	}
	
	private static Login getPrimaryPrincipal(List<Login> principalList) {
		if (principalList == null) {
			return null;
		}
		for (Login lg  : principalList) {
			try {
				if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
					return lg;
				}
			}catch(Exception e) {
				log.error(e);
				return null;
			}
		}
		return null;
		
	}

	public static ExtensibleUser modifyUser(User origUser2, List<Role> curRoleList, 
			List<Group> curGroupList, ProvisionUser pUser) throws IllegalArgumentException, 
	IllegalAccessException, 
	InvocationTargetException {
	
	SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	
	ExtensibleUser extUser = new ExtensibleUser();
	
	if (pUser == null) {
		throw new NullPointerException("Provision user is null");
	}
	// use reflection to the list of attributes that are part of user
	User user = pUser.getUser();
	
	determineUserChanges(extUser, user, origUser2);
	


	// get the user attributes
	Map<String, UserAttribute> userAttr = (Map<String,UserAttribute>)pUser.getUserAttributes();
	Collection<UserAttribute> attrCol =  userAttr.values();
	for (UserAttribute attr : attrCol) {
		String name = extUser.getName();
		name = "USER_ATTRIBUTE." + attr.getName();
		ExtensibleAttribute extAttr = new ExtensibleAttribute(name, attr.getValue(), attr.getMetadataElementId());
		if (attr.getOperation() == (AttributeOperationEnum.DELETE)) {
			extAttr.setOperation(AttributeOperationEnum.DELETE.getValue());
		}else {
			// find the attribute
			log.info("Attribute name=" +  attr.getName());
		 	UserAttribute tempAttr =  origUser2.getAttribute( attr.getName());
		 	if (tempAttr != null) {
		 		//log.info("Attribute found=" + name + " - val=" + tempAttr.getValue());
		 		//log.info("Attribute orgi=" + attr.getValue());
		 		// match found. 
		 		if (attr.getValue() == null && tempAttr.getValue() != null) {
		 			extAttr.setOperation(AttributeOperationEnum.REPLACE.getValue());
		 		}else if (attr.getValue() != null && tempAttr.getValue() == null) {
		 			extAttr.setOperation(AttributeOperationEnum.REPLACE.getValue());
		 		}else if (!attr.getValue().equalsIgnoreCase(tempAttr.getValue())) {
		 			log.info("attr object values are not equal ");
		 			extAttr.setOperation(AttributeOperationEnum.REPLACE.getValue());
		 		}else {
		 			extAttr.setOperation(0);
		 		}
		 	}else {
		 		// not found 
		 		extAttr.setOperation(AttributeOperationEnum.ADD.getValue());
		 	}
		}
		extUser.getAttributes().add(extAttr);
	}
	// check if we have attributes in the original that we dont have here
	Map<String, UserAttribute> origUserAttr = (Map<String,UserAttribute>)origUser2.getUserAttributes();
	Collection<UserAttribute> origUserAttrCol =  origUserAttr.values();
	User newUser = pUser.getUser();
	for (UserAttribute attr : origUserAttrCol) {
		UserAttribute tempAttr2= newUser.getAttribute(attr.getName());
		if (tempAttr2 == null) {
			// not found add to the 
			ExtensibleAttribute extAttr = new ExtensibleAttribute("USER_ATTRIBUTE." + attr.getName(), attr.getValue(),
					attr.getMetadataElementId());
			extAttr.setOperation(0);
			extUser.getAttributes().add(extAttr);

		}
			
	}

	// primary identity
	List<Login> principalList = pUser.getPrincipalList();
	if (principalList != null && principalList.size() > 0) {
		for (Login lg  : principalList) {
			try {
				if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
					extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY.PRINCIPAL.", lg.getId().getLogin(),0, "String"));
					String p = lg.getPassword();
					if (p != null && p.length()> 12) {
						p = loginManager.decryptPassword(p);
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD.PRINCIPAL",   p ,0, "String"));
					}else {
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD.PRINCIPAL", p ,0, "String"));
					}
					extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_STATUS.PRINCIPAL", lg.getStatus()));
				}else {
					extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY." + lg.getId().getManagedSysId(), lg.getId().getLogin(),0, "String"));
					String p = lg.getPassword();
					if (p != null && p.length()> 12) {
						p = loginManager.decryptPassword(p);
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD."+lg.getId().getManagedSysId(), p,0, "String"));
					}else {
						extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD."+lg.getId().getManagedSysId(), p,0, "String"));
					}
					//extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_PSWD."+lg.getId().getManagedSysId(), lg.getPassword(),0));
					extUser.getAttributes().add(new ExtensibleAttribute("IDENTITY_STATUS."+lg.getId().getManagedSysId(), lg.getStatus()));
				}
			}catch(Exception e) {
				log.error(e);
				return null;
			}
		}

	}
	/*	Login lg = getPrimaryPrincipal(principalList);
	if (lg != null) {
		extUser.getAttributes().add(new ExtensibleAttribute("PRINCIPAL.IDENTITY", lg.getId().getLogin()));
		extUser.getAttributes().add(new ExtensibleAttribute("PRINCIPAL.IDENTITY_PSWD", lg.getPassword()));
	}
*/
	
	// groups
	List<Group> groupList = pUser.getMemberOfGroups();
	if (groupList != null) {
		for (Group grp : groupList) {
			ExtensibleGroup extGroup = new ExtensibleGroup(grp);
			log.info("Group being added to extGroup=" + grp);
			// Check the current list. if not found, then the operation is an add
			if (grp.getOperation() != AttributeOperationEnum.DELETE) {
				if (curGroupList != null) {
					boolean found = false;
					for (Group cGroup : curGroupList) {
						if (cGroup.getGrpId().equals(grp.getGrpId())) {
							found = true;
						}
					}
					if (!found) {
						extGroup.setOperation(1);
					}
				}else {
					extGroup.setOperation(1);
				}
			}
			extUser.getGroup().add(extGroup);		
		}
	}
	// add the groups there were in the original but not in the current
	if (curGroupList != null) {
		for (Group g: curGroupList) {
			// check if its in the new list. If not, add it on
			if (groupList != null) {
				boolean found = false;
				for (Group newGroup :groupList) {
					if (g.getGrpId().equalsIgnoreCase(newGroup.getGrpId())) {
						found = true;
					}
				}
				if (!found) {
					ExtensibleGroup extGroup = new ExtensibleGroup(g);
					extGroup.setOperation(0);
					extUser.getGroup().add(extGroup);					
				}
			}else {
				ExtensibleGroup extGroup = new ExtensibleGroup(g);
				extGroup.setOperation(0);
				extUser.getGroup().add(extGroup);
			}
		}
	}
	
	
	
	// roles
	List<Role> roleList = pUser.getMemberOfRoles();
	if (roleList != null) {
		for (Role rl : roleList) {
			ExtensibleRole extRole = new ExtensibleRole(rl);
			log.info("Role being added to extRole=" + rl);
			if (rl.getOperation() != AttributeOperationEnum.DELETE) {
				if (curRoleList != null) {
					boolean found = false;
					for (Role cRl : curRoleList) {
						if (cRl.getId().getRoleId().equals(rl.getId().getRoleId())) {
							found = true;
						}
					}
					if (!found) {
						extRole.setOperation(1);
					}
				}else {
					extRole.setOperation(1);
				}
			}
			
			extUser.getRole().add(extRole);			
		}
	}	
	// add the roles that were in teh orginal, but not in the current
	if (curRoleList != null) {
		for (Role rl: curRoleList) {
			// check if its in the new list. If not, add it on
			if (roleList != null) {
				boolean found = false;
				for (Role newRole : roleList) {
					if (rl.getId().getRoleId().equalsIgnoreCase(newRole.getId().getRoleId())) {
						found = true;
					}
				}
				if (!found) {
					ExtensibleRole extRole = new ExtensibleRole(rl);
					extRole.setOperation(0);
					extUser.getRole().add(extRole);					
				}
			}else {
				ExtensibleRole extRole = new ExtensibleRole(rl);
				extRole.setOperation(0);
				extUser.getRole().add(extRole);	
			}
		}
	}	
	
	
	// address
	log.info("Processing address list");
	Set<Address> addressList = getFullAddressList(pUser.getAddresses(), origUser2.getAddresses() );
	//Set<Address> addressList =  pUser.getAddresses();
	if (addressList != null) {
		Iterator<Address> adrIt =  addressList.iterator();
		while (adrIt.hasNext()) {
			Address adr = adrIt.next();
			ExtensibleAddress extAddress = new ExtensibleAddress(adr);
			extUser.getAddress().add(extAddress);
		}
	}
	
	// email
	log.info("Processing emailAddress list");
	Set<EmailAddress> emailAddressList = getFullEmailList(pUser.getEmailAddress(),
			origUser2.getEmailAddress() );
	//Set<EmailAddress> emailAddressList = pUser.getEmailAddress();
	if (emailAddressList != null) {
		Iterator<EmailAddress> emailIt = emailAddressList.iterator();
		while (emailIt.hasNext()) {
			EmailAddress email = emailIt.next();
			ExtensibleEmailAddress extEmail = new ExtensibleEmailAddress(email);
			extUser.getEmail().add(extEmail);
		}
	}
	
	// phone
	log.info("Processing phoneList");
	Set<Phone> phoneList = getFullPhoneList(pUser.getPhone(),
			origUser2.getPhone() );
	//Set<Phone> phoneList = pUser.getPhone();
	if (phoneList != null) {
		Iterator<Phone> phoneIt = phoneList.iterator();
		while (phoneIt.hasNext()) {
			Phone phone = phoneIt.next();
			ExtensiblePhone extPhone = new ExtensiblePhone(phone);
			extUser.getPhone().add(extPhone);
		}
	}
	
	
	return extUser;
}

	static private Set<Address> getFullAddressList(Set<Address> newAddressList, Set<Address>origAddressList) {
		Set<Address> addressSet = new HashSet<Address>();
		
		log.info("getFullAddress list called.");
		log.info("origAddress list=" + origAddressList);
		log.info("newAddress list=" + newAddressList);

		
		if (origAddressList == null && (newAddressList != null && newAddressList.size()>0 )) {
			addressSet.addAll(newAddressList);
			return addressSet;
		}
		if ( (origAddressList != null && origAddressList.size() > 0 ) && (newAddressList == null || newAddressList.size() == 0 )) {
			log.info("origAddress list size =" + origAddressList.size());
			addressSet.addAll(origAddressList);
			return addressSet;
		}
		
		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		Iterator<Address> it = newAddressList.iterator();
		while (it.hasNext()) {
			Address addr = it.next();
			// check the old list
			Iterator<Address> origIt = origAddressList.iterator();
			boolean found = false;
			while (origIt.hasNext()) {
				Address origAdr = origIt.next();
				if (origAdr.getAddressId().equals(addr.getAddressId())) {
					found = true;
				}
			}
			if (!found) {
				addr.setOperation(AttributeOperationEnum.ADD);
				addressSet.add(addr);
			}else {
				if (addr.getOperation() == AttributeOperationEnum.DELETE) {
					addressSet.add(addr);
				}else {
					addr.setOperation(AttributeOperationEnum.REPLACE);
					addressSet.add(addr);
				}
			}
		}
		
		// if the old address is not in the list, the add it
		Iterator<Address> curIt = origAddressList.iterator();
		if (curIt.hasNext()) {
			Address adr = curIt.next();
			addressSet.add(adr);
		}
		return addressSet;
	}

	
	static private Set<Phone> getFullPhoneList(Set<Phone> newPhoneList, Set<Phone>origPhoneList) {
		Set<Phone> phoneSet = new HashSet<Phone>();
		
		if (origPhoneList == null && newPhoneList != null) {
			phoneSet.addAll(newPhoneList);
			return phoneSet;
		}
		
		if ( (origPhoneList != null && origPhoneList.size() > 0 ) && (newPhoneList == null || newPhoneList.size() ==0 )) {
			phoneSet.addAll(origPhoneList);
			return phoneSet;
		}
		
		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		Iterator<Phone> it = newPhoneList.iterator();
		while (it.hasNext()) {
			Phone addr = it.next();
			// check the old list
			Iterator<Phone> origIt = origPhoneList.iterator();
			boolean found = false;
			while (origIt.hasNext()) {
				Phone origAdr = origIt.next();
				if (origAdr.getPhoneId().equals(addr.getPhoneId())) {
					found = true;
				}
			}
			if (!found) {
				addr.setOperation(AttributeOperationEnum.ADD);
				phoneSet.add(addr);
			}else {
				if (addr.getOperation() == AttributeOperationEnum.DELETE) {
					phoneSet.add(addr);
				}else {
					addr.setOperation(AttributeOperationEnum.REPLACE);
					phoneSet.add(addr);
				}
			}
		}
		
		// if the old address is not in the list, the add it
		Iterator<Phone> curIt = origPhoneList.iterator();
		if (curIt.hasNext()) {
			Phone adr = curIt.next();
			phoneSet.add(adr);
		}
		return phoneSet;
	}

	static private Set<EmailAddress> getFullEmailList(Set<EmailAddress> newPhoneList, Set<EmailAddress>origEmailList) {
		Set<EmailAddress> emailSet = new HashSet<EmailAddress>();
		
		
		log.info("orig email list=" + origEmailList);
		
		if (origEmailList == null && newPhoneList != null) {
			log.info("New email list is not null");
			emailSet.addAll(newPhoneList);
			return emailSet;
		}

		if ( (origEmailList != null && origEmailList.size() > 0 ) && (newPhoneList == null || newPhoneList.size() == 0 )) {
			log.info("orig email list is not null");
			emailSet.addAll(origEmailList);
			return emailSet;
		}
		
		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		Iterator<EmailAddress> it = newPhoneList.iterator();
		while (it.hasNext()) {
			EmailAddress addr = it.next();
			// check the old list
			Iterator<EmailAddress> origIt = origEmailList.iterator();
			boolean found = false;
			while (origIt.hasNext()) {
				EmailAddress origAdr = origIt.next();
				if (origAdr.getEmailId().equals(addr.getEmailId())) {
					found = true;
				}
			}
			if (!found) {
				addr.setOperation(AttributeOperationEnum.ADD);
				emailSet.add(addr);
			}else {
				if (addr.getOperation() == AttributeOperationEnum.DELETE) {
					emailSet.add(addr);
				}else {
					addr.setOperation(AttributeOperationEnum.REPLACE);
					emailSet.add(addr);
				}
			}
		}
		
		// if the old address is not in the list, the add it
		Iterator<EmailAddress> curIt = origEmailList.iterator();
		if (curIt.hasNext()) {
			EmailAddress adr = curIt.next();
			emailSet.add(adr);
		}
		return emailSet;
	}
	
	static public User cloneUser(User user) {
		User newUser = new User();
	
		newUser.setBirthdate(user.getBirthdate());
		newUser.setCompanyId( user.getCompanyId() );
		newUser.setCompanyOwnerId(user.getCompanyOwnerId());
		newUser.setCreateDate(user.getCreateDate());
		newUser.setCreatedBy(user.getCreatedBy());
		newUser.setDeptCd( user.getDeptCd() );
		newUser.setDeptName( user.getDeptName() );
		newUser.setEmployeeId(user.getEmployeeId());
		newUser.setEmployeeType(user.getEmployeeType());

		newUser.setFirstName(user.getFirstName());
		newUser.setJobCode(user.getJobCode());
		newUser.setLastName(user.getLastName());
	    newUser.setLastUpdate(user.getLastUpdate());
	    newUser.setLastUpdatedBy( user.getLastUpdatedBy() );
	    newUser.setLocationCd(user.getLocationCd());
	    newUser.setLocationName(user.getLocationName());
	    newUser.setManagerId( user.getManagerId() );
	    newUser.setMetadataTypeId(user.getMetadataTypeId());
	    newUser.setClassification(user.getClassification());
	    newUser.setMiddleInit(user.getMiddleInit());
	    newUser.setPrefix(user.getPrefix());
	    newUser.setSex( user.getSex() );
	    newUser.setStatus(user.getStatus());
	    newUser.setSecondaryStatus(user.getSecondaryStatus());
	    newUser.setSuffix(user.getSuffix());
	    newUser.setTitle(user.getTitle());
	    newUser.setUserId( user.getUserId() );
	    newUser.setUserTypeInd(user.getUserTypeInd());
	    newUser.setDivision( user.getDivision() );
	    newUser.setMailCode(user.getMailCode()); 
		newUser.setCostCenter(user.getCostCenter());
		newUser.setStartDate(user.getStartDate());
		newUser.setLastDate( user.getLastDate());		    
		newUser.setNickname(user.getNickname());
		newUser.setMaidenName(user.getMaidenName());
		newUser.setPasswordTheme(user.getPasswordTheme());
	    newUser.setCountry( user.getCountry());            
	    newUser.setBldgNum( user.getBldgNum()); 
	    newUser.setStreetDirection( user.getStreetDirection());
		newUser.setAddress1( user.getAddress1());
		newUser.setAddress2( user.getAddress2() );
		newUser.setAddress3( user.getAddress3() );            
		newUser.setAddress4( user.getAddress4() );            
		newUser.setAddress5(user.getAddress5());     
		newUser.setAddress6( user.getAddress6() );  
		newUser.setAddress7(user.getAddress7() );
		newUser.setCity( user.getCity() );
		newUser.setState( user.getState() );
		newUser.setPostalCd( user.getPostalCd());
		newUser.setEmail( user.getEmail() );
		newUser.setAreaCd( user.getAreaCd() );       
		newUser.setCountryCd( user.getCountryCd());
		newUser.setPhoneNbr( user.getPhoneNbr() );
		newUser.setPhoneExt( user.getPhoneExt() );
		newUser.setShowInSearch(user.getShowInSearch());
		newUser.setAlternateContactId( user.getAlternateContactId());

		
		// 
		Map<String, UserAttribute> userAttributes = new HashMap<String, UserAttribute>(0);
		userAttributes.putAll(user.getUserAttributes());
		newUser.setUserAttributes( userAttributes);
		
		newUser.setPhone(user.getPhone() );
		
		newUser.setAddresses( user.getAddresses() );
		
		newUser.setEmailAddress(user.getEmailAddress());
		
		return newUser;
	
	}
	
	static private void determineUserChanges(ExtensibleUser extUser, User newUser, User origUser) {
		SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
		
		// for each field in the user object
		// - check the null conditions
		// - check if the values are different
		// set the operation
		
		// set up the origUser object for reflection
		Class origUserClass = origUser.getClass();
		origUserClass.getDeclaredMethods();
		
		log.info("** c) Deptcd in Orig=" + origUser.getDeptCd());
		
		
		Class newUserClass =  newUser.getClass();
		Method[] userMethod =  newUserClass.getDeclaredMethods();
		if (userMethod != null && userMethod.length > 0) {
			// loop through the methods 
			for (Method m  : userMethod) {
				String methName = m.getName();
				String returnType = m.getReturnType().getName();
				
			//	log.info("MethodName=" + methName);
			//	log.info("Return type:=" + returnType ) ;

				String value = null;
				String origValue = null;
				int operation = 0;
				try {
				if (methName.startsWith("get")) {
					if (returnType.contains("String")){
						value = (String) m.invoke(newUser, null);
						operation =  getStringOperationValue(origUser ,origUserClass, methName, value );
					}
					if (returnType.contains("Date")) {
						Date temp = (Date)m.invoke(newUser, null);
						long time = 0;
						if (temp != null) {
							time = temp.getTime();
							value = df.format(temp);
							//value = temp.toString();
							operation =  getDateOperationValue(origUser ,origUserClass, methName, time );

						}
					}
					if (returnType.contains("UserStatusEnum")) {
						UserStatusEnum temp = (UserStatusEnum)m.invoke(newUser, null);
						if (temp != null) {
							
							value = temp.getValue();
							operation =  getStatusOperationValue(origUser ,origUserClass, methName, value );
						}
					}
					// get the original value
					
					
					
					// build the method name - to show the object that the value came from
					// get rid of the "get" in the name
					methName = "USER." + methName.substring(3);
					extUser.getAttributes().add(new ExtensibleAttribute(methName, value, operation, "String"));
				}
				}catch(Exception it) {
					it.printStackTrace();
				}

			}
		}

	}

	static private int getStatusOperationValue(User origUser, Class origUserClass, String methName, String value ) {
		int operation = 0;
		try {
			Method method =  origUserClass.getMethod(methName, null);
			UserStatusEnum origValue =  (UserStatusEnum)method.invoke(origUser, null);
			
			//log.info("operation check: method name=" + method + " value=" + value + " " + origValue);
			
			// check what has changed
			if (origValue == null && value != null) {
				return 2;
			}
			if (origValue != null && value == null) {
				return 2;
			}
			if (origValue != null && !origValue.getValue().equalsIgnoreCase(value)) {
				return 2;
			}
			return 0;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return operation;
		
	}

	static private int getDateOperationValue(User origUser, Class origUserClass, String methName, long newTime ) {
		int operation = 0;
		long origTime = 0;
		
		try {
			Method method =  origUserClass.getMethod(methName, null);
			Date origValue =  (Date)method.invoke(origUser, null);
			
			if (origValue != null) {
				origTime = origValue.getTime();
			}
			//log.info("date comparison=" + origTime + " - " + newTime);

			
			if (origTime != newTime) {
				return 2;
			}
			return 0;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return operation;
		
	}
	
	static private int getStringOperationValue(User origUser, Class origUserClass, String methName, String value ) {
		int operation = 0;
		try {
			Method method =  origUserClass.getMethod(methName, null);
			String origValue =  (String)method.invoke(origUser, null);
			
			//.info("operation check: method name=" + method + " value=" + value + " " + origValue);
			
			// check what has changed
			if (origValue == null && value != null) {
				return 2;
			}
			if (origValue != null && value == null) {
				return 2;
			}
			if (origValue != null && !origValue.equalsIgnoreCase(value)) {
				return 2;
			}
			return 0;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return operation;
		
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}
	
}



