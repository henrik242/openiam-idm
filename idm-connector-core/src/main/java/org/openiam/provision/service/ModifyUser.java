package org.openiam.provision.service;

import java.util.ArrayList;
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
import org.openiam.base.id.UUIDGen;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.type.ExtensibleUser;

/**
 * Helper class for the modifyUser operation in the Provisioning Service.
 * @author administrator
 *
 */
public class ModifyUser {
	protected RoleDataService roleDataService;
	protected GroupDataService groupManager;
	protected UserDataService userMgr;
	protected LoginDataService loginManager;
	protected AuditHelper auditHelper;
	
	protected static final Log log = LogFactory.getLog(ModifyUser.class);

	// these instance variables will be used later in the provisioning process when we need to show the difference at the field level
	Map<String, UserAttribute> userAttributes = new HashMap<String, UserAttribute>();
	Set<EmailAddress> emailSet = new HashSet<EmailAddress>();
	Set<Phone> phoneSet = new HashSet<Phone>();
	Set<Address> addressSet = new HashSet<Address>();
	List<Group> groupList = new ArrayList<Group>();
	List<Role> roleList = new ArrayList<Role>();
	List<Login> principalList = new ArrayList<Login>();
	
	
	public String updateUser(ProvisionUser user, User origUser) {
	
		String requestId = UUIDGen.getUUID();
		
		User newUser = user.getUser();
		updateUserObject(origUser, newUser);
		
		// check the email collection
	 	Set<EmailAddress> emailAdrSet = origUser.getEmailAddress();
		for (EmailAddress e : emailAdrSet) {
			log.info("Email Set:" + e.getEmailAddress());
		}
		//
		
		userMgr.updateUserWithDependent(origUser, true);
		
/*		String linkedLogId =  auditHelper.addLog("MODIFY USER", user.getSecurityDomain(),
				null, "IDM SERVICE",
				user.getLastUpdatedBy(), "0",
				"USER", user.getUserId(), "SUCCESS", null,  null, null, requestId);
	*/	

		
		return requestId;
	}



	public void updateUserObject(User origUser, User newUser) {
		
		origUser.updateUser(newUser);
		
		//updatePrimaryUserInfo(origUser, newUser);
		updateUserAttributes(origUser, newUser);
		updateUserEmail(origUser, newUser);
		updatePhone(origUser, newUser);
		updateAddress(origUser, newUser);
	}
	
	private void updateUserAttributes(User origUser, User newUser) {
		Map<String, UserAttribute> origAttributes =  origUser.getUserAttributes();
		if (origAttributes == null) {
			origAttributes = new HashMap<String, UserAttribute>();
		}
		Map<String, UserAttribute> newAttributes = newUser.getUserAttributes();
		// no change to the attributes - leave it alone
		if (newAttributes == null) {
			return;
		}
		
		Iterator<UserAttribute> attrIt =  newAttributes.values().iterator();
		while (attrIt.hasNext()) {
			UserAttribute newAttr = attrIt.next();
			if (newAttr.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("size before remove: " + origAttributes.size());
				origAttributes.remove(newAttr.getName());
				log.info("size after remove: " + origAttributes.size());
			}else {
				UserAttribute origAttr = origAttributes.get(newAttr.getName());
				if (origAttr != null) {
					if (origAttr.equals(newAttr)) {
						// update the attr if something was changed
						origAttr.setValue(newAttr.getValue());
						origAttributes.put(origAttr.getName(),origAttr);
						newAttr.setOperation(AttributeOperationEnum.REPLACE);
					}
				}else {
					// new attribute was not in the original list. add it to the new one.
					origAttributes.put(newAttr.getName(), newAttr);
					newAttr.setOperation(AttributeOperationEnum.ADD);
				}
			}
			userAttributes.put(newAttr.getName(), newAttr);
		}
	
		
	}
	
	public Map<String, UserAttribute> getUserAttributes() {
		return userAttributes;
	}
	
  
	private void updateUserEmail(User origUser, User newUser) {
		Set<EmailAddress> origEmailSet = origUser.getEmailAddress();
		Set<EmailAddress> newEmailSet = newUser.getEmailAddress();

		if (origEmailSet == null && newEmailSet != null) {
			log.info("New email list is not null");
			origEmailSet = new HashSet<EmailAddress>();
			origEmailSet.addAll(newEmailSet);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (EmailAddress em  : newEmailSet) {
				em.setOperation(AttributeOperationEnum.ADD);
				this.emailSet.add(em);
			}
			return;
		}

		if ( (origEmailSet != null && origEmailSet.size() > 0 ) && (newEmailSet == null || newEmailSet.size() == 0 )) {
			log.info("orig email list is not null and nothing was passed in for the newEmailSet - ie no change");
			for (EmailAddress em  : origEmailSet) {
				em.setOperation(AttributeOperationEnum.NO_CHANGE);
				this.emailSet.add(em);
			}
			return;
		}

		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		for (EmailAddress em : newEmailSet) {
			if (em.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing email :" + em.getEmailAddress() );
				// get the email object from the original set of emails so that we can remove it
				EmailAddress e = getEmailAddress(em.getEmailId(), origEmailSet);
				if (e != null) {
					origEmailSet.remove(e);
				}
				emailSet.add(em);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.info("evaluate email address");
				EmailAddress origEmail =  getEmailAddress(em.getEmailId(), origEmailSet);
				if (origEmail == null) {
					em.setOperation(AttributeOperationEnum.ADD);
					origEmailSet.add(em);
					emailSet.add(em);
				}else {
					if (em.equals(origEmail)) {
						// not changed
						em.setOperation(AttributeOperationEnum.NO_CHANGE);
						emailSet.add(em);
					}else {
						// object changed
						origEmail.updateEmailAddress(em);
						origEmailSet.add(origEmail);
						origEmail.setOperation(AttributeOperationEnum.REPLACE);
						emailSet.add(origEmail);
					}
				}
			}
		}
		// if a value is in original list and not in the new list - then add it on 
		for (EmailAddress e : origEmailSet) {
			EmailAddress newEmail =  getEmailAddress(e.getEmailId(), newEmailSet);
			if (newEmail == null) {
				e.setOperation(AttributeOperationEnum.NO_CHANGE);
				emailSet.add(e);
			}
		}
		
	}
	

	private EmailAddress getEmailAddress(String id, Set<EmailAddress> emailSet) {
		Iterator<EmailAddress> emailIt = emailSet.iterator();
		while (emailIt.hasNext()) {
			EmailAddress email = emailIt.next();
			if (email.getEmailId() != null) {
				if (email.getEmailId().equals(id) && (id != null && id.length() > 0)) {
					log.info("Match >> email.getEmailId = " + email.getEmailId() + " - " + id);
					return email;
				}
			}
		}
		return null;
		
	}
	private Phone getPhone(String id, Set<Phone> phoneSet) {
		Iterator<Phone> phoneIt = phoneSet.iterator();
		while (phoneIt.hasNext()) {
			Phone phone = phoneIt.next();
			if (phone.getPhoneId() != null) {
				if (phone.getPhoneId().equals(id) && (id != null && id.length() > 0)) {
					log.info("Match >> phone.getPhoneId = " + phone.getPhoneId() + " - " + id);
					return phone;
				}
			}
		}
		return null;
		
	}

	private Address getAddress(String id, Set<Address> addressSet) {
		Iterator<Address> addressIt = addressSet.iterator();
		while (addressIt.hasNext()) {
			Address adr = addressIt.next();
			if (adr.getAddressId() != null  ) {
				if (adr.getAddressId().equals(id) && (id != null && id.length() > 0)) {
					log.info("Match >> adr.getAdrId = " + adr.getAddressId() + " - " + id);
					return adr;
				}
			}
		}
		return null;
		
	}
	
	private Group getGroup(String grpId, List<Group> origGroupList) {
		for (Group g : origGroupList ) {
			if (g.getGrpId().equalsIgnoreCase(grpId)) {
				return g;
			}
		}
		return null;
	}

	private Role getRole(RoleId roleId, List<Role> roleList) {
		for (Role rl : roleList ) {
			if (rl.getId().equals(roleId)) {
				return rl;
			}
		}
		return null;
	}

	private Login getPrincipal(LoginId loginId, List<Login> loginList) {
		for (Login lg : loginList ) {
			if (lg.getId().getManagedSysId().equals(loginId.getManagedSysId())) {
				return lg;
			}
		}
		return null;
	}
	public Login getPrimaryIdentity(String managedSysId) {
		if (	principalList == null || 
				principalList.size() == 0) {
			return null;
		}
		for (Login l  : principalList) {
			if (l.getId().getManagedSysId().equalsIgnoreCase(managedSysId)) {
				return l;
			}
		}
		return null;
 	}
	
	
	private void updatePhone(User origUser, User newUser) {
		Set<Phone> origPhoneSet = origUser.getPhone();
		Set<Phone> newPhoneSet = newUser.getPhone();

		if (origPhoneSet == null && newPhoneSet != null) {
			log.info("New email list is not null");
			origPhoneSet = new HashSet<Phone>();
			origPhoneSet.addAll(newPhoneSet);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (Phone ph : newPhoneSet) {
				ph.setOperation(AttributeOperationEnum.ADD);
				phoneSet.add(ph);
			}
			return;
		}

		if ( (origPhoneSet != null && origPhoneSet.size() > 0 ) && (newPhoneSet == null || newPhoneSet.size() == 0 )) {
			log.info("orig phone list is not null and nothing was passed in for the newPhoneSet - ie no change");
			for (Phone ph  : origPhoneSet) {
				ph.setOperation(AttributeOperationEnum.NO_CHANGE);
				this.phoneSet.add(ph);
			}
			return;
		}

		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		for (Phone ph : newPhoneSet) {
			if (ph.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing phone :" + ph.getPhoneId() );
				// get the email object from the original set of emails so that we can remove it
				Phone e = getPhone(ph.getPhoneId(), origPhoneSet);
				if (e != null) {
					origPhoneSet.remove(e);
				}
				phoneSet.add(ph);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.info("evaluate phone");
				Phone origPhone =  getPhone(ph.getPhoneId(), origPhoneSet);
				if (origPhone == null) {
					ph.setOperation(AttributeOperationEnum.ADD);
					origPhoneSet.add(ph);
					phoneSet.add(ph);
				}else {
					if (ph.equals(origPhone)) {
						// not changed
						ph.setOperation(AttributeOperationEnum.NO_CHANGE);
						phoneSet.add(ph);
					}else {
						// object changed
						origPhone.updatePhone(ph);
						origPhoneSet.add(origPhone);
						origPhone.setOperation(AttributeOperationEnum.REPLACE);
						phoneSet.add(origPhone);
					}
				}
			}
		}
		// if a value is in original list and not in the new list - then add it on 
		for (Phone ph : origPhoneSet) {
			Phone newPhone =  getPhone(ph.getPhoneId(), newPhoneSet);
			if (newPhone == null) {
				ph.setOperation(AttributeOperationEnum.NO_CHANGE);
				phoneSet.add(ph);
			}
		}
		
	}
	
	private void updateAddress(User origUser, User newUser) {
		Set<Address> origAddressSet = origUser.getAddresses();
		Set<Address> newAddressSet = newUser.getAddresses();

		if (origAddressSet == null && newAddressSet != null) {
			log.info("New email list is not null");
			origAddressSet = new HashSet<Address>();
			origAddressSet.addAll(newAddressSet);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (Address ph : newAddressSet) {
				ph.setOperation(AttributeOperationEnum.ADD);
				addressSet.add(ph);
			}
			return;
		}

		if ( (origAddressSet != null && origAddressSet.size() > 0 ) && (newAddressSet == null || newAddressSet.size() == 0 )) {
			log.info("orig Address list is not null and nothing was passed in for the newAddressSet - ie no change");
			for (Address ph  : origAddressSet) {
				ph.setOperation(AttributeOperationEnum.NO_CHANGE);
				addressSet.add(ph);
			}
			return;
		}

		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		for (Address ph : newAddressSet) {
			if (ph.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing Address :" + ph.getAddressId() );
				// get the email object from the original set of emails so that we can remove it
				Address e = getAddress(ph.getAddressId(), origAddressSet);
				if (e != null) {
					origAddressSet.remove(e);
				}
				addressSet.add(ph);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.info("evaluate Address");
				Address origAddress =  getAddress(ph.getAddressId(), origAddressSet);
				if (origAddress == null) {
					ph.setOperation(AttributeOperationEnum.ADD);
					origAddressSet.add(ph);
					addressSet.add(ph);
				}else {
					if (ph.equals(origAddress)) {
						// not changed
						ph.setOperation(AttributeOperationEnum.NO_CHANGE);
						addressSet.add(ph);
					}else {
						// object changed
						origAddress.updateAddress(ph);
						origAddressSet.add(origAddress);
						origAddress.setOperation(AttributeOperationEnum.REPLACE);
						addressSet.add(origAddress);
					}
				}
			}
		}
		// if a value is in original list and not in the new list - then add it on 
		for (Address ph : origAddressSet) {
			Address newAddress =  getAddress(ph.getAddressId(), newAddressSet);
			if (newAddress == null) {
				ph.setOperation(AttributeOperationEnum.NO_CHANGE);
				addressSet.add(ph);
			}
		}
		
	}
	
	
	public void updateGroupAssociation(String userId, List<Group> origGroupList,  List<Group> newGroupList) {

		log.info("updating group associations..");
		log.info("origGroupList =" + origGroupList);
		log.info("newGroupList=" + newGroupList);

		if ( (origGroupList == null || origGroupList.size() == 0 )  && 
				(newGroupList == null || newGroupList.size() == 0 )) {
			return;
		}
		
		if ( (origGroupList == null || origGroupList.size() == 0 )  && 
			  (newGroupList != null || newGroupList.size() > 0 )) {
			
			log.info("New group list is not null");
			origGroupList = new ArrayList<Group>();
			origGroupList.addAll(newGroupList);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (Group g : newGroupList) {
				g.setOperation(AttributeOperationEnum.ADD);
				groupList.add(g);
				this.groupManager.addUserToGroup(g.getGrpId(), userId);
			}
			return;
		}

		if ( (origGroupList != null && origGroupList.size() > 0 ) && (newGroupList == null || newGroupList.size() == 0 )) {
			log.info("orig group list is not null and nothing was passed in for the newGroupList - ie no change");
			for (Group g  : origGroupList) {
				g.setOperation(AttributeOperationEnum.NO_CHANGE);
				groupList.add(g);
			}
			return;
		}

		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		for (Group g : newGroupList) {
			if (g.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing Group :" + g.getGrpId() );
				// get the email object from the original set of emails so that we can remove it
				Group grp = getGroup(g.getGrpId(), origGroupList);
				if (grp != null) {
					this.groupManager.removeUserFromGroup(grp.getGrpId(), userId);
				}
				groupList.add(grp);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.info("evaluate Group");
				Group origGroup =  getGroup(g.getGrpId(), origGroupList);
				if (origGroup == null) {
					g.setOperation(AttributeOperationEnum.ADD);
					groupList.add(g);
					groupManager.addUserToGroup(g.getGrpId(), userId);
				}else {
					if (g.getGrpId().equals(origGroup.getGrpId())) {
						// not changed
						g.setOperation(AttributeOperationEnum.NO_CHANGE);
						groupList.add(g);
					}
				}
			}
		}
		// if a value is in original list and not in the new list - then add it on 
		for (Group g : origGroupList) {
			Group newGroup =  getGroup(g.getGrpId(), newGroupList);
			if (newGroup == null) {
				g.setOperation(AttributeOperationEnum.NO_CHANGE);
				groupList.add(g);
			}
		}
		
	}
	
	public void updatePrincipalList(String userId, List<Login> origLoginList,  List<Login> newLoginList) {
		log.info("updating Principals in modify User.");
		log.info("origPrincpalList =" + origLoginList);
		log.info("newPrincipalList=" + newLoginList);
		
		if ( (origLoginList == null || origLoginList.size() == 0 )  && 
				  (newLoginList == null || newLoginList.size() == 0 )) {
			return;
		}
		
		if ( (origLoginList == null || origLoginList.size() == 0 )  && 
			  (newLoginList != null || newLoginList.size() > 0 )) {
			
			log.info("New Principal list is not null");
			origLoginList = new ArrayList<Login>();
			origLoginList.addAll(newLoginList);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (Login lg : newLoginList) {
				lg.setOperation(AttributeOperationEnum.ADD);
				lg.setUserId(userId);
				principalList.add(lg);
				loginManager.addLogin(lg);
			}
			return;
		}

		if ( (origLoginList != null && origLoginList.size() > 0 ) && (newLoginList == null || newLoginList.size() == 0 )) {
			log.info("orig Principal list is not null and nothing was passed in for the newPrincipal list - ie no change");
			for (Login l  : origLoginList) {
				l.setOperation(AttributeOperationEnum.NO_CHANGE);
				principalList.add(l);
			}
			return;
		}

		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		for (Login l : newLoginList) {
			if (l.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing Login :" + l.getId() );
				// get the email object from the original set of emails so that we can remove it
				Login lg = getPrincipal(l.getId(), origLoginList);
				log.info("Login to remove = " + lg);
				if (lg != null) {
					lg.setStatus("INACTIVE");
					loginManager.updateLogin(lg);
				}
				principalList.add(l);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.info("evaluate Login");
				Login origLogin =  getPrincipal(l.getId(), origLoginList);
				log.info("OrigLogin found=" + origLogin);
				if (origLogin == null) {
					l.setOperation(AttributeOperationEnum.ADD);
					l.setUserId(userId);
					principalList.add(l);
					loginManager.addLogin(l);

				}else {
					if (l.getId().equals(origLogin.getId())) {
						// not changed
						l.setOperation(AttributeOperationEnum.NO_CHANGE);
						principalList.add(l);
					}else {
						log.info("Identity changed");
						// remove the current login
						loginManager.removeLogin(origLogin.getId().getDomainId(), 
								origLogin.getId().getLogin(), origLogin.getId().getManagedSysId());
						// clone the object
						Login newLg = (Login)origLogin.clone();
						// add it back with the changed identity
						newLg.setOperation(AttributeOperationEnum.REPLACE);
						newLg.getId().setLogin(l.getId().getLogin());
						loginManager.addLogin(newLg);
						principalList.add(newLg);						
					}
				}
			}
		}
		// if a value is in original list and not in the new list - then add it on 
		for (Login lg : origLoginList) {
			Login newLogin =  getPrincipal(lg.getId(), newLoginList);
			if (newLogin == null) {
				lg.setOperation(AttributeOperationEnum.NO_CHANGE);
				principalList.add(lg);
			}
		}
		
	}


	
	public void updateRoleAssociation(String userId, List<Role> origRoleList,  List<Role> newRoleList) {

		log.info("updating role associations..");
		log.info("origRoleList =" + origRoleList);
		log.info("newRoleList=" + newRoleList);
		
		if ( (origRoleList == null || origRoleList.size() == 0 )  && 
				  (newRoleList == null || newRoleList.size() == 0 )) {
			return;
		}
		
		if ( (origRoleList == null || origRoleList.size() == 0 )  && 
			  (newRoleList != null || newRoleList.size() > 0 )) {
			
			log.info("New Role list is not null");
			origRoleList = new ArrayList<Role>();
			origRoleList.addAll(newRoleList);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (Role rl : newRoleList) {
				rl.setOperation(AttributeOperationEnum.ADD);
				roleList.add(rl);
				roleDataService.addUserToRole(rl.getId().getServiceId(), rl.getId().getRoleId(), userId);
			}
			return;
		}

		if ( (origRoleList != null && origRoleList.size() > 0 ) && (newRoleList == null || newRoleList.size() == 0 )) {
			log.info("orig Role list is not null and nothing was passed in for the newRoleList - ie no change");
			for (Role r  : origRoleList) {
				r.setOperation(AttributeOperationEnum.NO_CHANGE);
				roleList.add(r);
			}
			return;
		}

		// if in new address, but not in old, then add it with operation 1
		// else add with operation 2
		for (Role r : newRoleList) {
			if (r.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing Role :" + r.getId() );
				// get the email object from the original set of emails so that we can remove it
				Role rl = getRole(r.getId(), origRoleList);
				log.info("role to remove = " + rl);
				if (rl != null) {
					roleDataService.removeUserFromRole(rl.getId().getServiceId(), rl.getId().getRoleId(), userId);
				}
				roleList.add(r);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.info("evaluate Role");
				Role origRole =  getRole(r.getId(), origRoleList);
				log.info("OrigRole found=" + origRole);
				if (origRole == null) {
					r.setOperation(AttributeOperationEnum.ADD);
					roleList.add(r);
					roleDataService.addUserToRole(r.getId().getServiceId(), r.getId().getRoleId(), userId);
				}else {
					if (r.getId().equals(origRole.getId())) {
						// not changed
						r.setOperation(AttributeOperationEnum.NO_CHANGE);
						roleList.add(r);
					}
				}
			}
		}
		// if a value is in original list and not in the new list - then add it on 
		for (Role rl : origRoleList) {
			Role newRole =  getRole(rl.getId(), newRoleList);
			if (newRole == null) {
				rl.setOperation(AttributeOperationEnum.NO_CHANGE);
				roleList.add(rl);
			}
		}
		
	}

	

	public void updateSupervisor(User user, Supervisor supervisor) {
		
		if (supervisor == null) {
			return;
		}
		// check the current supervisor - if different - remove it and add the new one.
		List<Supervisor> supervisorList = userMgr.getSupervisors(user.getUserId());
		for (Supervisor s : supervisorList) {
			log.info("looking to match supervisor ids = " + s.getSupervisor().getUserId() + " " + supervisor.getSupervisor().getUserId());
			if (s.getSupervisor().getUserId().equalsIgnoreCase(supervisor.getSupervisor().getUserId())) {
				return;
			}
			userMgr.removeSupervisor(s);
		}
		log.info("adding supervisor: " + supervisor.getSupervisor().getUserId());
		supervisor.setEmployee(user);
		userMgr.addSupervisor(supervisor);

	}
	
	// 
	/**
	 * Update the list of attributes that are to be passed to the connector
	 */
	public void updateAttributeList(ExtensibleUser extUser) {
		
	}


	

	public RoleDataService getRoleDataService() {
		return roleDataService;
	}

	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}

	public GroupDataService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}

	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}



	public AuditHelper getAuditHelper() {
		return auditHelper;
	}



	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}



	public Set<EmailAddress> getEmailSet() {
		return emailSet;
	}



	public void setEmailSet(Set<EmailAddress> emailSet) {
		this.emailSet = emailSet;
	}



	public Set<Phone> getPhoneSet() {
		return phoneSet;
	}



	public void setPhoneSet(Set<Phone> phoneSet) {
		this.phoneSet = phoneSet;
	}



	public Set<Address> getAddressSet() {
		return addressSet;
	}



	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
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



	public List<Login> getPrincipalList() {
		return principalList;
	}



	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}



	public void setUserAttributes(Map<String, UserAttribute> userAttributes) {
		this.userAttributes = userAttributes;
	}
}
