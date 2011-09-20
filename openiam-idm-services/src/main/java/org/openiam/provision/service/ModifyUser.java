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
import org.openiam.exception.EncryptionException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.dto.UserRole;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.spml2.msg.ExtensibleAttribute;

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
	List<Role> deleteRoleList = new ArrayList<Role>();
	List<Login> principalList = new ArrayList<Login>();
	
	public ModifyUser() {
        init();
		
	}
	
	public void init() {
        log.debug("Modify User initialized");
		userAttributes = new HashMap<String, UserAttribute>();
		emailSet = new HashSet<EmailAddress>();
		phoneSet = new HashSet<Phone>();
		addressSet = new HashSet<Address>();
		groupList = new ArrayList<Group>();
		roleList = new ArrayList<Role>();
		deleteRoleList = new ArrayList<Role>();
		principalList = new ArrayList<Login>();		
	}
	
	public String updateUser(ProvisionUser user, User origUser) {
	
		String requestId = UUIDGen.getUUID();
		
		User newUser = user.getUser();
		updateUserObject(origUser, newUser);
			
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
		
		updateUserEmail(origUser, newUser);
		updatePhone(origUser, newUser);
		updateAddress(origUser, newUser);
	}
	

	public Map<String, UserAttribute> getUserAttributes() {
		return userAttributes;
	}
	
  
	private void updateUserEmail(User origUser, User newUser) {
		Set<EmailAddress> origEmailSet = origUser.getEmailAddress();
		Set<EmailAddress> newEmailSet = newUser.getEmailAddress();

		if (origEmailSet == null && newEmailSet != null) {
			log.debug("New email list is not null");
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
			log.debug("orig email list is not null and nothing was passed in for the newEmailSet - ie no change");
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
				log.debug("removing email :" + em.getEmailAddress() );
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
				log.debug("evaluate email address");
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
					log.debug("Match >> email.getEmailId = " + email.getEmailId() + " - " + id);
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
					log.debug("Match >> phone.getPhoneId = " + phone.getPhoneId() + " - " + id);
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
					log.debug("Match >> adr.getAdrId = " + adr.getAddressId() + " - " + id);
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
			System.out.println("GetRole: " + roleId + "--" + rl.getId());
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
			log.debug("New email list is not null");
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
			log.debug("orig phone list is not null and nothing was passed in for the newPhoneSet - ie no change");
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
				log.debug("removing phone :" + ph.getPhoneId() );
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
				log.debug("evaluate phone");
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
			log.debug("New email list is not null");
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
			log.debug("orig Address list is not null and nothing was passed in for the newAddressSet - ie no change");
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
				log.debug("removing Address :" + ph.getAddressId() );
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
				log.debug("evaluate Address");
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

		log.debug("updating group associations..");
		log.debug("origGroupList =" + origGroupList);
		log.debug("newGroupList=" + newGroupList);

		if ( (origGroupList == null || origGroupList.size() == 0 )  && 
				(newGroupList == null || newGroupList.size() == 0 )) {
			return;
		}
		
		if ( (origGroupList == null || origGroupList.size() == 0 )  && 
			  (newGroupList != null || newGroupList.size() > 0 )) {
			
			log.debug("New group list is not null");
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
			log.debug("orig group list is not null and nothing was passed in for the newGroupList - ie no change");
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
				log.debug("removing Group :" + g.getGrpId() );
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
				log.debug("evaluate Group");
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
	
	public void updatePrincipalList(String userId, List<Login> origLoginList,  
			List<Login> newLoginList,
			List<Resource> deleteResourceList) {

		log.debug("** updating Principals in modify User.");
		log.debug("- origPrincpalList =" + origLoginList);
		log.debug("- newPrincipalList=" + newLoginList);
		
		if ( (origLoginList == null || origLoginList.size() == 0 )  && 
				  (newLoginList == null || newLoginList.size() == 0 )) {
			return;
		}
		
		if ( (origLoginList == null || origLoginList.size() == 0 )  && 
			  (newLoginList != null || newLoginList.size() > 0 )) {
			
			log.debug("New Principal list is not null");
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
			log.debug("orig Principal list is not null and nothing was passed in for the newPrincipal list - ie no change");
			for (Login l  : origLoginList) {
				l.setOperation(AttributeOperationEnum.NO_CHANGE);
				if (notInDeleteResourceList(l,deleteResourceList)) {
					l.setStatus("ACTIVE");
					l.setAuthFailCount(0);
					l.setIsLocked(0);
					l.setPasswordChangeCount(0);
                    // reset the password from the primary identity
                    // get the primary identity for this user
                    Login primaryIdentity = loginManager.getPrimaryIdentity(l.getUserId());
                    if (primaryIdentity != null) {
                        log.debug("Identity password reset to: " + primaryIdentity.getPassword());
                        l.setPassword( primaryIdentity.getPassword() );
                    }

					loginManager.updateLogin(l);
				}
                principalList.add(l);
			}
			return;
		}

		// if in new login, but not in old, then add it with operation 1
		// else add with operation 2
		for (Login l : newLoginList) {
			if (l.getOperation() == AttributeOperationEnum.DELETE) {
				log.debug("removing Login :" + l.getId() );
				// get the email object from the original set of emails so that we can remove it
				Login lg = getPrincipal(l.getId(), origLoginList);
				log.debug("Login to remove = " + lg);
				if (lg != null) {
					lg.setStatus("INACTIVE");
					loginManager.updateLogin(lg);
				}
				principalList.add(l);
			}else {
				// check if this login is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				log.debug("evaluate Login");
				Login origLogin =  getPrincipal(l.getId(), origLoginList);
				log.debug("OrigLogin found=" + origLogin);
				if (origLogin == null) {
					l.setOperation(AttributeOperationEnum.ADD);
					l.setUserId(userId);
					principalList.add(l);
					loginManager.addLogin(l);

				}else {
					if (l.getId().equals(origLogin.getId())) {
						// not changed
						log.debug("Identities are equal - No Change");
						log.debug("OrigLogin status=" + origLogin.getStatus());
						
						// if the request contains a password, then set the password
						// as part of the modify request
						
						if (l.getPassword() != null && !l.getPassword().equals(origLogin.getPassword())) {
							// update the password

                            log.debug("Password change detected during synch process");

							Login newLg = (Login)origLogin.clone();
							try {
								newLg.setPassword(loginManager.encryptPassword(l.getPassword()));
							}catch(EncryptionException e) {
								log.error(e);
								e.printStackTrace();
							}
							loginManager.changeIdentityName(newLg.getId().getLogin(), newLg.getPassword(),
									newLg.getUserId(), newLg.getId().getManagedSysId(), newLg.getId().getDomainId());
							principalList.add(newLg);	
						} else {
                            log.debug("Updating Identity in IDM repository");
                            if (l.getOperation() == AttributeOperationEnum.REPLACE) {
                                // user set the replace flag
                                loginManager.updateLogin(l);
                                principalList.add(l);
                            } else {

                                log.debug("Flagged identity with NO_CHANGE attribute");

							    l.setOperation(AttributeOperationEnum.NO_CHANGE);
							    principalList.add(l);
                            }
							
						}
						

					}else {
						log.debug("Identity changed - RENAME");
	
						
						// clone the object
						Login newLg = (Login)origLogin.clone();
						// add it back with the changed identity
						newLg.setOperation(AttributeOperationEnum.REPLACE);
						newLg.getId().setLogin(l.getId().getLogin());
						
						//encrypt the password and save it
						String newPassword = l.getPassword();
						if (newPassword == null) {
							newLg.setPassword(null);
						}else {
							try {
								newLg.setPassword(loginManager.encryptPassword(newPassword));
							}catch(EncryptionException e) {
								log.error(e);
								e.printStackTrace();
							}
						}
						loginManager.changeIdentityName(newLg.getId().getLogin(), newLg.getPassword(),
								newLg.getUserId(), newLg.getId().getManagedSysId(), newLg.getId().getDomainId());
						//loginManager.addLogin(newLg);

						
						// we cannot send the encrypted password to the connector
						// set the password back
						newLg.setPassword(newPassword);
						// used the match up the 
						newLg.setOrigPrincipalName(origLogin.getId().getLogin());
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

	private boolean notInDeleteResourceList(Login l, List<Resource> deleteResourceList) {
		if (deleteResourceList == null) {
			return true;
		}
		for ( Resource r : deleteResourceList) {
			if (l.getId().getManagedSysId().equalsIgnoreCase(r.getManagedSysId())) {
				return false;
			}
		}
		return true;
	}

	
	public void updateRoleAssociation(String userId, List<Role> origRoleList,  List<Role> newRoleList, List<IdmAuditLog> logList) {

		log.debug("updating role associations..");
		log.debug("origRoleList =" + origRoleList);
		log.debug("newRoleList=" + newRoleList);
		
		// initialize the role lists
		roleList = new ArrayList<Role>();
		deleteRoleList = new ArrayList<Role>();
		
		List<UserRole> currentUserRole = roleDataService.getUserRolesForUser(userId);
        User user = userMgr.getUserWithDependent(userId,false);

		
		if ( (origRoleList == null || origRoleList.size() == 0 )  && 
				  (newRoleList == null || newRoleList.size() == 0 )) {
			return;
		}
		
		// scneario where the original role list is empty but new roles are passed in on the request
		if ( (origRoleList == null || origRoleList.size() == 0 )  && 
			  (newRoleList != null || newRoleList.size() > 0 )) {
			
			log.debug("New Role list is not null");
			origRoleList = new ArrayList<Role>();
			origRoleList.addAll(newRoleList);
			// update the instance variable so that it can passed to the connector with the right operation code
			for (Role rl : newRoleList) {
				rl.setOperation(AttributeOperationEnum.ADD);
				roleList.add(rl);
				
				UserRole ur = new UserRole(userId, rl.getId().getServiceId(), 
						rl.getId().getRoleId());

				if ( rl.getStartDate() != null) {
					ur.setStartDate(rl.getStartDate());
				}
				if ( rl.getEndDate() != null ) {
					ur.setEndDate(rl.getEndDate());
				}
				roleDataService.assocUserToRole(ur);

                logList.add( auditHelper.createLogObject("ADD ROLE", user.getSecurityDomain(), null,
                        "IDM SERVICE", user.getCreatedBy(), "0", "USER", user.getUserId(),
                        null, "SUCCESS", null, "USER_STATUS",
                        user.getStatus().toString(),
                        null, null, null, null, ur.getRoleId()));

				//roleDataService.addUserToRole(rl.getId().getServiceId(), rl.getId().getRoleId(), userId);
			}
			return;
		}

		// roles were originally assigned to this user, but this request does not have any roles.
		// need to ensure that old roles are marked with the no-change operation code.
		if ( (origRoleList != null && origRoleList.size() > 0 ) && (newRoleList == null || newRoleList.size() == 0 )) {
			log.debug("orig Role list is not null and nothing was passed in for the newRoleList - ie no change");
			for (Role r  : origRoleList) {
				r.setOperation(AttributeOperationEnum.NO_CHANGE);
				roleList.add(r);
			}
			return;
		}

		// if in new roleList, but not in old, then add it with operation 1
		// else add with operation 2
		for (Role r : newRoleList) {
			if (r.getOperation() == AttributeOperationEnum.DELETE) {
				log.debug("removing Role :" + r.getId() );
				// get the email object from the original set of emails so that we can remove it
				Role rl = getRole(r.getId(), origRoleList);
				if (rl != null) {
					roleDataService.removeUserFromRole(rl.getId().getServiceId(), rl.getId().getRoleId(), userId);

                    logList.add( auditHelper.createLogObject("REMOVE ROLE", user.getSecurityDomain(), null,
                            "IDM SERVICE", user.getCreatedBy(), "0", "USER", user.getUserId(),
                            null, "SUCCESS", null, "USER_STATUS",
                            user.getStatus().toString(),
                            null, null, null, null, rl.getId().getRoleId()));

				}
				log.debug("Adding role to deleteRoleList =" + rl);
				this.deleteRoleList.add(rl);
				//roleList.add(r);
			}else {
				// check if this address is in the current list
				// if it is - see if it has changed
				// if it is not - add it.
				System.out.println("evaluate Role" + r.getId());
				
				Role origRole =  getRole(r.getId(), origRoleList);
				
				System.out.println("OrigRole found=" + origRole);
				
				if (origRole == null) {
					r.setOperation(AttributeOperationEnum.ADD);
					roleList.add(r);
					
					UserRole ur = new UserRole(userId, r.getId().getServiceId(), 
							r.getId().getRoleId());

					if ( r.getStartDate() != null) {
						ur.setStartDate(r.getStartDate());
					}
					if ( r.getEndDate() != null ) {
						ur.setEndDate(r.getEndDate());
					}
					roleDataService.assocUserToRole(ur);

                    logList.add( auditHelper.createLogObject("ADD ROLE", user.getSecurityDomain(), null,
                        "IDM SERVICE", user.getCreatedBy(), "0", "USER", user.getUserId(),
                        null, "SUCCESS", null, "USER_STATUS",
                        user.getStatus().toString(),
                        null, null, null, null, ur.getRoleId()));
					
					//roleDataService.addUserToRole(r.getId().getServiceId(), r.getId().getRoleId(), userId);
				}else {
					// get the user role object 
					log.debug("checking if no_change or replace");
					//if (r.equals(origRole)) {
					//UserRole uRole = userRoleAttrEq(r, currentUserRole);
					if (r.getId().equals(origRole.getId()) && userRoleAttrEq(r, currentUserRole)  ) {
						// not changed
						log.debug("- no_change ");
						r.setOperation(AttributeOperationEnum.NO_CHANGE);
						roleList.add(r);
					}else {
						log.debug("- Attr not eq - replace");
						r.setOperation(AttributeOperationEnum.REPLACE);
						roleList.add(r);
						
						// object changed
						//UserRole ur = new UserRole(userId, r.getId().getServiceId(), 
						//		r.getId().getRoleId());
						UserRole ur = getUserRole(r, currentUserRole);
						if ( r.getStartDate() != null) {
							ur.setStartDate(r.getStartDate());
						}
						if ( r.getEndDate() != null ) {
							ur.setEndDate(r.getEndDate());
						}
						if ( r.getStatus() != null ) {
							ur.setStatus(r.getStatus());
						}
						roleDataService.updateUserRoleAssoc(ur);
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
	private UserRole getUserRole(Role r, List<UserRole> currentUserRole) {
		//boolean retval = true;
		
		UserRole ur = null;
		// get the user role object
		for (UserRole u : currentUserRole) {
			if (r.getId().getRoleId().equalsIgnoreCase(u.getRoleId()) && 
				r.getId().getServiceId().equalsIgnoreCase(u.getServiceId())) {
				return u;
			}
		}
		return null;

	}
	
	private boolean userRoleAttrEq(Role r, List<UserRole> currentUserRole) {
		//boolean retval = true;
		
		UserRole ur = null;
		// get the user role object
		for (UserRole u : currentUserRole) {
			if (r.getId().getRoleId().equalsIgnoreCase(u.getRoleId()) && 
				r.getId().getServiceId().equalsIgnoreCase(u.getServiceId())) {
				ur = u;
			}
		}
		if (ur == null) {
			return false;
		}
		if (r.getStatus() != null) {
			if ( !r.getStatus().equalsIgnoreCase(ur.getStatus()) ) {
				return false;
			}
		}
		if (r.getStartDate() != null) {
			if ( !r.getStartDate().equals(ur.getStartDate()) ){
				return false;
			}
		}
		if (r.getEndDate() != null) {
			if ( !r.getEndDate().equals(ur.getEndDate()) ){
				return false;
			}
		}
		return true;
	}

	

	public void updateSupervisor(User user, Supervisor supervisor) {
		
		if (supervisor == null) {
			return;
		}
		// check the current supervisor - if different - remove it and add the new one.
		List<Supervisor> supervisorList = userMgr.getSupervisors(user.getUserId());
		for (Supervisor s : supervisorList) {
			log.debug("looking to match supervisor ids = " + s.getSupervisor().getUserId() + " " + supervisor.getSupervisor().getUserId());
			if (s.getSupervisor().getUserId().equalsIgnoreCase(supervisor.getSupervisor().getUserId())) {
				return;
			}
			userMgr.removeSupervisor(s);
		}
		log.debug("adding supervisor: " + supervisor.getSupervisor().getUserId());
		supervisor.setEmployee(user);
		userMgr.addSupervisor(supervisor);

	}
	
	// 
	/**
	 * Update the list of attributes with the correct operation values so that they can be 
	 * passed to the connector
	 */
	public ExtensibleUser updateAttributeList(org.openiam.provision.type.ExtensibleUser extUser, Map<String,String> currentValueMap ) {
		if (extUser == null) {
			return null;
		}
		log.debug("Updating operations on attributes being passed to connectors");
		log.debug("Current attributeMap = " + currentValueMap);
		
		List<org.openiam.provision.type.ExtensibleAttribute> extAttrList = extUser.getAttributes();
		if (extAttrList == null) {
			
			log.debug("Extended user attributes is null");
			
			return null;
		}
		for (org.openiam.provision.type.ExtensibleAttribute attr  : extAttrList) {
			String nm = attr.getName();
			if (currentValueMap == null) {
				attr.setOperation(1);
			}else {
				String curVal = currentValueMap.get(nm);
				if (curVal == null) {
                    // temp hack
                    if (nm.equalsIgnoreCase("objectclass")) {
                        attr.setOperation(2);
                    }else {
                        log.debug("- Op = 1 - AttrName = " +nm );

                        attr.setOperation(1);
                    }
				}else {
					if (curVal.equalsIgnoreCase(attr.getValue())) {
						log.debug("- Op = 0 - AttrName = " +nm );
						
						attr.setOperation(0);
					}else {
						
						log.debug("- Op = 2 - AttrName = " +nm );
						
						attr.setOperation(2);
					}
				}
			}
		}
		return extUser;
		
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

	public List<Role> getActiveRoleList() {
		log.debug("Determining activeRole List. RoleList =" + roleList);
		List<Role> rList = new ArrayList<Role>();
		// create a list of roles that are not in the deleted list
		for ( Role r : roleList) {
			boolean found =false;
			log.debug("- Role=" + r);
			if (deleteRoleList != null && !deleteRoleList.isEmpty()) {
				for ( Role delRl : deleteRoleList) {
					if (!found && r.getId().getRoleId().equalsIgnoreCase(delRl.getId().getRoleId())) {
						found = true;
					}
				}
			}
			if (!found) {
				log.debug("- Adding Role=" + r);
				rList.add(r);
			}
		}
		return rList;
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



	public List<Role> getDeleteRoleList() {
		return deleteRoleList;
	}



	public void setDeleteRoleList(List<Role> deleteRoleList) {
		this.deleteRoleList = deleteRoleList;
	}
}
