package org.openiam.idm.srvc.user.service;

import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.util.db.Search;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.service.AddressDAO;
import org.openiam.idm.srvc.continfo.service.EmailAddressDAO;
import org.openiam.idm.srvc.continfo.service.PhoneDAO;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;

import java.util.*;

import javax.jws.WebService;

/**
 * Service interface that clients will access to gain information about users
 * and related information.
 * 
 * @author Suneet Shah
 * @version 2
 */

// Note: as per spec serviceName goes in impl class and name goes in interface
@WebService(endpointInterface = "org.openiam.idm.srvc.user.service.UserDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/user/service", 
		serviceName = "UserDataWebService")
public class UserMgr implements UserDataService {

	private UserDAO userDao;
	private UserAttributeDAO userAttributeDao;
	private UserNoteDAO userNoteDao;
	private AddressDAO addressDao;
	private EmailAddressDAO emailAddressDao;
	private PhoneDAO phoneDao;
	private SupervisorDAO supervisorDao;

	// protected UserMsgProducer userMsgProducer;

	/*
	 * public UserMgr() {
	 *  }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUser(java.lang.String)
	 */
	public User getUser(String id) {
		return userDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUser(java.lang.String,
	 *      boolean)
	 */
	public User getUserWithDependent(String id, boolean dependants) {

		/*
		 * Map<String, UserAttribute> attrMap = new HashMap<String,
		 * UserAttribute>();
		 */
		// Set<UserAttribute> attrSet = new HashSet<UserAttribute>();
		User usr = userDao.findById(id);
		if (usr == null) {
			return null;
		}

		if (!dependants)
			return usr;
		
		
		
		//	 assemble the various dependant objects
		org.hibernate.Hibernate.initialize(usr);
		
		//usr.setAddresses(addressDao.findByParent(id, ContactConstants.PARENT_TYPE_USER));
		//usr.setPhones(phoneDao.findByParent(id, ContactConstants.PARENT_TYPE_USER));
		//usr.setEmailAddresses(emailAddressDao.findByParent(id, ContactConstants.PARENT_TYPE_USER));
		
	
		return usr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addUser(org.openiam.idm.srvc.user.dto.User)
	 */
	public User addUser(User user) {
		if (user == null)
			throw new NullPointerException("user object is null");

		if (user.getCreateDate() == null) {
			user.setCreateDate(new Date(System.currentTimeMillis()));
		}
		if (user.getLastUpdate() == null) {
			user.setLastUpdate(new Date(System.currentTimeMillis()));
		}

		validateEmailAddress(user.getUserId(), user.getEmailAddress());
		
		userDao.add(user);
		// this.userMsgProducer.sendMessage(user,"ADD");

		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addUser(org.openiam.idm.srvc.user.dto.User,
	 *      boolean)
	 */
	public User addUserWithDependent(User user, boolean dependency) {
		if (user == null)
			throw new NullPointerException("user object is null");

		if (user.getCreateDate() == null) {
			user.setCreateDate(new Date(System.currentTimeMillis()));
		}
		if (user.getLastUpdate() == null) {
			user.setLastUpdate(new Date(System.currentTimeMillis()));
		}
		
		// if there are dependants, then make user that the parentId has been set
		
		validateEmailAddress(user.getUserId(), user.getEmailAddress());

		userDao.add(user);

		if (!dependency)
			return user;
			
	  // address
	/*  Map<String, Address> adrMap = user.getAddresses();
	  if (adrMap != null && adrMap.size() > 0 ) {
		  this.addressDao.saveAddressMap(user.getUserId(), 
				  	ContactConstants.PARENT_TYPE_USER , adrMap);
	  
	 }
	 */
	  //email
	//  Map<String, EmailAddress> emailMap = user.getEmailAddresses();
	//  if (emailMap != null && emailMap.size() > 0 ) {
	//  this.emailAddressDao.saveEmailAddressMap(user.getUserId(), 
	//			  	ContactConstants.PARENT_TYPE_USER , emailMap);
	//  }
	  
	  // phone
	/*  Map<String, Phone> phoneMap = user.getPhones();
	  if (phoneMap != null && phoneMap.size() > 0 ) {
		  this.phoneDao.savePhoneMap(user.getUserId(), 
				  	ContactConstants.PARENT_TYPE_USER , phoneMap);
	  }
	  */
	  
	//  this.userMsgProducer.sendMessage(user,"ADD");
			

		return user;

	}
	
	private void validateEmailAddress(String userId, Set<EmailAddress> emailSet) {
		
		if (emailSet == null || emailSet.isEmpty())
			return;
		
		Iterator<EmailAddress> it = emailSet.iterator();
		
		while (it.hasNext()) {
			EmailAddress emailAdr = it.next();
			if (emailAdr.getParentId() == null) {
				emailAdr.setParentId(userId);
				emailAdr.setParentType(ContactConstants.PARENT_TYPE_USER);
			}
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateUser(org.openiam.idm.srvc.user.dto.User)
	 */
	public void updateUser(User user) {
		if (user == null)
			throw new NullPointerException("user object is null");
		if (user.getUserId() == null)
			throw new NullPointerException("user id is null");

		user.setLastUpdate(new Date(System.currentTimeMillis()));

		userDao.update(user);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateUser(org.openiam.idm.srvc.user.dto.User,
	 *      boolean)
	 */
	public void updateUserWithDependent(User user, boolean dependency) {
		if (user == null)
			throw new NullPointerException("user object is null");
		if (user.getUserId() == null)
			throw new NullPointerException("user id is null");

		user.setLastUpdate(new Date(System.currentTimeMillis()));
		
		validateEmailAddress(user.getUserId(), user.getEmailAddress());

		userDao.update(user);

		if (!dependency)
			return;
			
	// address
	 /* Map<Address> adrMap = user.getAddresses();
	  if (adrMap != null && adrMap.size() > 0 ) {
		  this.addressDao.saveAddressMap(user.getUserId(), 
				  	ContactConstants.PARENT_TYPE_USER , adrMap);
	  }
	  */
	  //email
/*	  Map<String, EmailAddress> emailMap = user.getEmailAddresses();
	  if (emailMap != null && emailMap.size() > 0 ) {
		  this.emailAddressDao.saveEmailAddressMap(user.getUserId(), 
				  	ContactConstants.PARENT_TYPE_USER , emailMap);
	  }
*/	  
	  // phone
/*
		Map<String, Phone> phoneMap = user.getPhones();
	  if (phoneMap != null && phoneMap.size() > 0 ) {
		  this.phoneDao.savePhoneMap(user.getUserId(), 
				  	ContactConstants.PARENT_TYPE_USER , phoneMap);
	  }
*/
	  //this.userMsgProducer.sendMessage(user,"UPDATE");
	  
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeUser(java.lang.String)
	 */
	public void removeUser(String id) {
		if (id == null)
			throw new NullPointerException("user id is null");

		User user = new User(id);

		// removes all the dependant objects.
		removeAllAttributes(id);
		removeAllPhones(id);
		removeAllAddresses(id);
		removeAllNotes(id);
		removeAllEmailAddresses(id);

		userDao.remove(user);

		// / this.userMsgProducer.sendMessage(user.getUserId(),"DELETE");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#findUsersByLastUpdateRange(java.util.Date,
	 *      java.util.Date)
	 */
	public List findUsersByLastUpdateRange(Date startDate, Date endDate) {

		return userDao.findByLastUpdateRange(startDate, endDate);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#findUserByOrganization(java.lang.String)
	 */
	public List<User> findUserByOrganization(String orgId) {
		return userDao.findByOrganization(orgId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#findUsersByStatus(java.lang.String)
	 */
	public List findUsersByStatus(String status) {
		return userDao.findByStatus(status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#search(org.openiam.util.db.Search)
	 */
	
	public List<User> search(Search search) {
		return userDao.search(search);
	}

	/* -------- Methods for Attributes ---------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public UserAttribute addAttribute(UserAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		// if (attribute.getId() == null) {
		// throw new NullPointerException("Attribute id is null");
		// }
		if (attribute.getUserId() == null) {
			throw new NullPointerException(
					"User has not been associated with this attribute.");
		}

		userAttributeDao.add(attribute);

		// this.userMsgProducer.sendMessage(attribute.getUserId(),"ADD");

		return attribute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public void updateAttribute(UserAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getUserId() == null) {
			throw new NullPointerException(
					"User has not been associated with this attribute.");
		}

		userAttributeDao.update(attribute);
		// this.userMsgProducer.sendMessage(attribute.getUserId(),"UPDATE");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAllAttributes(java.lang.String)
	 */
	public Map<String, UserAttribute> getAllAttributes(String userId) {
		Map<String, UserAttribute> attrMap = new HashMap<String, UserAttribute>();

		if (userId == null) {
			throw new NullPointerException("userId is null");
		}

		User usr = userDao.findById(userId);

		if (usr == null)
			return null;

		List<UserAttribute> attrList = userAttributeDao
				.findUserAttributes(userId);

		if (attrList == null || attrList.size() == 0)
			return null;

		// migrate to a Map for the User object
		if (attrList != null && !attrList.isEmpty()) {
			int size = attrList.size();
			for (int i = 0; i < size; i++) {
				UserAttribute attr = attrList.get(i);
				attrMap.put(attr.getName(), attr);
			}
		}

		return attrMap;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAttribute(java.lang.String)
	 */
	public UserAttribute getAttribute(String attrId) {
		if (attrId == null) {
			throw new NullPointerException("attrId is null");
		}
		return userAttributeDao.findById(attrId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAttribute(org.openiam.idm.srvc.user.dto.UserAttribute)
	 */
	public void removeAttribute(UserAttribute attr) {
		if (attr == null) {
			throw new NullPointerException("attr is null");
		}
		if (attr.getId() == null) {
			throw new NullPointerException("attrId is null");
		}

		userAttributeDao.remove(attr);

		// this.userMsgProducer.sendMessage(attr.getUserId(),"DELETE");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllAttributes(java.lang.String)
	 */
	public void removeAllAttributes(String userId) {
		if (userId == null) {
			throw new NullPointerException("userId is null");
		}
		userAttributeDao.deleteUserAttributes(userId);

		// this.userMsgProducer.sendMessage(userId,"DELETE");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUserAsMap(java.lang.String)
	 */
	public Map<String, UserAttribute> getUserAsMap(String userId) {
		User usr = getUser(userId);
		if (usr == null) {
			return null;
		}

		Map<String, UserAttribute> attrMap = getAllAttributes(userId);
		if (attrMap == null) {
			attrMap = new HashMap<String, UserAttribute>();
		}
		// assign the predefined properties

		attrMap.put("USER_ID", new UserAttribute(null, usr.getUserId(), null,
				"USER_ID", userId));
		attrMap.put("FIRST_NAME", new UserAttribute(null, usr.getUserId(),
				null, "FIRST_NAME", usr.getFirstName()));
		attrMap.put("LAST_NAME", new UserAttribute(null, usr.getUserId(), null,
				"LAST_NAME", usr.getLastName()));
		attrMap.put("MIDDLE_INIT", new UserAttribute(null, usr.getUserId(),
				null, "MIDDLE_INIT", String.valueOf(usr.getMiddleInit())));
		attrMap.put("TITLE", new UserAttribute(null, usr.getUserId(), null,
				"TITLE", usr.getTitle()));
		attrMap.put("DEPT", new UserAttribute(null, usr.getUserId(), null,
				"DEPT", usr.getDeptCd()));
		attrMap.put("STATUS", new UserAttribute(null, usr.getUserId(), null,
				"STATUS", usr.getStatus()));
		if (usr.getBirthdate() != null) {
			attrMap.put("BIRTHDATE", new UserAttribute(null, usr.getUserId(),
					null, "BIRTHDATE", usr.getBirthdate().toString()));
		} else {
			attrMap.put("BIRTHDATE", new UserAttribute(null, usr.getUserId(),
					null, "BIRTHDATE", null));
		}
		attrMap.put("SEX", new UserAttribute(null, usr.getUserId(), null,
				"SEX", String.valueOf(usr.getSex())));
		if (usr.getCreateDate() != null) {
			attrMap.put("CREATE_DATE", new UserAttribute(null, usr.getUserId(),
					null, "CREATE_DATE", usr.getCreateDate().toString()));
		} else {
			attrMap.put("CREATE_DATE", new UserAttribute(null, usr.getUserId(),
					null, "CREATE_DATE", null));

		}
		attrMap.put("CREATED_BY", new UserAttribute(null, usr.getUserId(),
				null, "CREATED_BY", usr.getCreatedBy()));
		if (usr.getLastUpdate() != null) {
			attrMap.put("LAST_UPDATE", new UserAttribute(null, usr.getUserId(),
					null, "LAST_UPDATE", usr.getLastUpdate().toString()));
		} else {
			attrMap.put("LAST_UPDATE", new UserAttribute(null, usr.getUserId(),
					null, "LAST_UPDATE", null));

		}
		attrMap.put("LAST_UPDATEDBY", new UserAttribute(null, usr.getUserId(),
				null, "LAST_UPDATEDBY", usr.getLastUpdatedBy()));
		attrMap.put("PREFIX", new UserAttribute(null, usr.getUserId(), null,
				"PREFIX", usr.getPrefix()));
		attrMap.put("SUFFIX", new UserAttribute(null, usr.getUserId(), null,
				"SUFFIX", usr.getSuffix()));
		attrMap.put("USER_TYPE_IND", new UserAttribute(null, usr.getUserId(),
				null, "USER_TYPE_IND", usr.getUserTypeInd()));
		attrMap.put("EMPLOYEE_ID", new UserAttribute(null, usr.getUserId(),
				null, "EMPLOYEE_ID", usr.getEmployeeId()));
		attrMap.put("EMPLOYEE_TYPE", new UserAttribute(null, usr.getUserId(),
				null, "EMPLOYEE_TYPE", usr.getEmployeeType()));
		attrMap.put("LOCATION_ID", new UserAttribute(null, usr.getUserId(),
				null, "LOCATION_ID", usr.getLocationCd()));
		attrMap.put("ORGANIZATION_ID", new UserAttribute(null, usr.getUserId(),
				null, "ORGANIZATION_ID", usr.getCompanyId()));
		attrMap.put("COMPANY_OWNER_ID", new UserAttribute(null,
				usr.getUserId(), null, "COMPANY_OWNER_ID", usr
						.getCompanyOwnerId()));

		attrMap.put("MANAGER_ID", new UserAttribute(null, usr.getUserId(),
				null, "MANAGER_ID", usr.getManagerId()));
		attrMap.put("JOB_CODE", new UserAttribute(null, usr.getUserId(), null,
				"JOB_CODE", usr.getJobCode()));

		return attrMap;
	}

	/* -------- Methods for UserNotes ---------- */
	/*
	 * Use these methods when you dont want to go through the user object
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public UserNote addNote(UserNote note) {
		if (note == null)
			throw new NullPointerException("Note cannot be null");

		if (note.getUserId() == null) {
			throw new NullPointerException(
					"User is not associated with this note.");
		}
		userNoteDao.persist(note);
		return note;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public void updateNote(UserNote note) {
		if (note == null)
			throw new NullPointerException("Note cannot be null");
		if (note.getUserNoteId() == null) {
			throw new NullPointerException("noteId is null");
		}
		if (note.getUserId() == null) {
			throw new NullPointerException(
					"User is not associated with this note.");
		}

		userNoteDao.merge(note);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAllNotes(java.lang.String)
	 */
	public List<UserNote> getAllNotes(String userId) {
		List<UserNote> noteList = new ArrayList<UserNote>();

		if (userId == null) {
			throw new NullPointerException("userId is null");
		}
		noteList = userNoteDao.findUserNotes(userId);
		if (noteList == null || noteList.isEmpty())
			return null;
		return noteList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getNote(java.lang.String)
	 */
	public UserNote getNote(java.lang.String noteId) {
		if (noteId == null) {
			throw new NullPointerException("attrId is null");
		}
		return userNoteDao.findById(noteId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeNote(org.openiam.idm.srvc.user.dto.UserNote)
	 */
	public void removeNote(UserNote note) {
		if (note == null) {
			throw new NullPointerException("note is null");
		}
		if (note.getUserNoteId() == null) {
			throw new NullPointerException("noteId is null");
		}

		userNoteDao.delete(note);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllNotes(java.lang.String)
	 */
	public void removeAllNotes(String userId) {
		if (userId == null) {
			throw new NullPointerException("userId is null");
		}
		userNoteDao.deleteUserNotes(userId);

	}

	/* ----------- Address Methods ------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public void addAddress(Address val) {
		if (val == null)
			throw new NullPointerException("val is null");

		if (val.getParentId() == null)
			throw new NullPointerException(
					"parentId for the address is not defined.");

		val.setParentType(ContactConstants.PARENT_TYPE_USER);

		this.addressDao.add(val);

	}

	public void addAddressSet(Set<Address> adrSet) {
		if (adrSet == null || adrSet.size() == 0)
			return;
		Iterator<Address> it = adrSet.iterator();
		while (it.hasNext()) {
			Address adr = it.next();
			addAddress(adr);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public void updateAddress(Address val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getAddressId() == null)
			throw new NullPointerException("AddressId is null");
		if (val.getParentId() == null)
			throw new NullPointerException(
					"parentId for the address is not defined.");
		if (val.getParentType() == null) {
			throw new NullPointerException(
					"parentType for the address is not defined.");
		}

		addressDao.update(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAddress(org.openiam.idm.srvc.continfo.dto.Address)
	 */
	public void removeAddress(Address val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getAddressId() == null)
			throw new NullPointerException("AddressId is null");

		addressDao.remove(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllAddresses(java.lang.String)
	 */
	public void removeAllAddresses(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		addressDao.removeByParent(userId, ContactConstants.PARENT_TYPE_USER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressById(java.lang.String)
	 */
	public Address getAddressById(String addressId) {
		if (addressId == null)
			throw new NullPointerException("addressId is null");
		return addressDao.findById(addressId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressByName(java.lang.String,
	 *      java.lang.String)
	 */
	public Address getAddressByName(String userId, String addressName) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		if (addressName == null)
			throw new NullPointerException("userId is null");

		return addressDao.findByName(addressName, userId,
				ContactConstants.PARENT_TYPE_USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultAddress(java.lang.String)
	 */
	public Address getDefaultAddress(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return addressDao
				.findDefault(userId, ContactConstants.PARENT_TYPE_USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressList(java.lang.String)
	 */
	public List<Address> getAddressList(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return addressDao.findByParentAsList(userId,
				ContactConstants.PARENT_TYPE_USER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressMap(java.lang.String)
	 */
	
	public Map<String, Address> getAddressMap(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return addressDao.findByParent(userId,
				ContactConstants.PARENT_TYPE_USER);

	}

	/* ----------- Phone Methods ------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addPhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public void addPhone(Phone val) {
		if (val == null)
			throw new NullPointerException("val is null");

		if (val.getParentId() == null)
			throw new NullPointerException(
					"parentId for the address is not defined.");

		val.setParentType(ContactConstants.PARENT_TYPE_USER);

		this.phoneDao.add(val);

	}
	
	public void addPhoneSet(Set<Phone> phoneSet) {
		if (phoneSet == null || phoneSet.size() == 0)
			return;
		
		Iterator<Phone> it = phoneSet.iterator();
		while (it.hasNext()) {
			Phone ph = it.next();
			addPhone(ph);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updatePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public void updatePhone(Phone val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getPhoneId() == null)
			throw new NullPointerException("PhoneId is null");
		if (val.getParentId() == null)
			throw new NullPointerException(
					"parentId for the address is not defined.");
		if (val.getParentType() == null) {
			throw new NullPointerException(
					"parentType for the address is not defined.");
		}

		phoneDao.update(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removePhone(org.openiam.idm.srvc.continfo.dto.Phone)
	 */
	public void removePhone(Phone val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getPhoneId() == null)
			throw new NullPointerException("PhoneId is null");

		phoneDao.remove(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllPhones(java.lang.String)
	 */
	public void removeAllPhones(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		phoneDao.removeByParent(userId, ContactConstants.PARENT_TYPE_USER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneById(java.lang.String)
	 */
	public Phone getPhoneById(String addressId) {
		if (addressId == null)
			throw new NullPointerException("addressId is null");
		return phoneDao.findById(addressId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneByName(java.lang.String,
	 *      java.lang.String)
	 */
	public Phone getPhoneByName(String userId, String addressName) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		if (addressName == null)
			throw new NullPointerException("userId is null");

		return phoneDao.findByName(addressName, userId,
				ContactConstants.PARENT_TYPE_USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultPhone(java.lang.String)
	 */
	public Phone getDefaultPhone(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return phoneDao.findDefault(userId, ContactConstants.PARENT_TYPE_USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneList(java.lang.String)
	 */
	public List<Phone> getPhoneList(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return phoneDao.findByParentAsList(userId,
				ContactConstants.PARENT_TYPE_USER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneMap(java.lang.String)
	 */
	public Map<String, Phone> getPhoneMap(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return phoneDao.findByParent(userId, ContactConstants.PARENT_TYPE_USER);

	}

	/* ----------- E-mail Methods ------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public void addEmailAddress(EmailAddress val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getParentId() == null)
			throw new NullPointerException(
					"parentId for the address is not defined.");

		val.setParentType(ContactConstants.PARENT_TYPE_USER);

		this.emailAddressDao.add(val);

	}

	public void addEmailAddressSet(Set<EmailAddress> adrSet) {
		if (adrSet == null || adrSet.size() == 0)
			return;
		
		Iterator<EmailAddress> it = adrSet.iterator();
		while (it.hasNext()) {
			EmailAddress adr = it.next();
			addEmailAddress(adr);
		}
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public void updateEmailAddress(EmailAddress val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getEmailId() == null)
			throw new NullPointerException("EmailAddressId is null");
		if (val.getParentId() == null)
			throw new NullPointerException(
					"parentId for the address is not defined.");
		if (val.getParentType() == null) {
			throw new NullPointerException(
					"parentType for the address is not defined.");
		}

		emailAddressDao.update(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeEmailAddress(org.openiam.idm.srvc.continfo.dto.EmailAddress)
	 */
	public void removeEmailAddress(EmailAddress val) {
		if (val == null)
			throw new NullPointerException("val is null");
		if (val.getEmailId() == null)
			throw new NullPointerException("EmailAddressId is null");

		emailAddressDao.remove(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeAllEmailAddresses(java.lang.String)
	 */
	public void removeAllEmailAddresses(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		emailAddressDao.removeByParent(userId,
				ContactConstants.PARENT_TYPE_USER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressById(java.lang.String)
	 */
	public EmailAddress getEmailAddressById(String addressId) {
		if (addressId == null)
			throw new NullPointerException("addressId is null");
		return emailAddressDao.findById(addressId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressByName(java.lang.String,
	 *      java.lang.String)
	 */
	public EmailAddress getEmailAddressByName(String userId, String addressName) {
		if (userId == null)
			throw new NullPointerException("userId is null");
		if (addressName == null)
			throw new NullPointerException("userId is null");

		return emailAddressDao.findByName(addressName, userId,
				ContactConstants.PARENT_TYPE_USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getDefaultEmailAddress(java.lang.String)
	 */
	public EmailAddress getDefaultEmailAddress(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return emailAddressDao.findDefault(userId,
				ContactConstants.PARENT_TYPE_USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressList(java.lang.String)
	 */
	public List<EmailAddress> getEmailAddressList(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return emailAddressDao.findByParentAsList(userId,
				ContactConstants.PARENT_TYPE_USER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressMap(java.lang.String)
	 */
	public Map<String, EmailAddress> getEmailAddressMap(String userId) {
		if (userId == null)
			throw new NullPointerException("userId is null");

		return emailAddressDao.findByParent(userId,
				ContactConstants.PARENT_TYPE_USER);

	}

	/* ----------- Supervisor Methods ------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#addSupervisor(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
	public Supervisor addSupervisor(Supervisor supervisor) {
		if (supervisor == null)
			throw new NullPointerException("supervisor is null");
		this.supervisorDao.add(supervisor);
		return supervisor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#updateSupervisor(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
	public void updateSupervisor(Supervisor supervisor) {
		if (supervisor == null)
			throw new NullPointerException("supervisor is null");
		this.supervisorDao.update(supervisor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#removeSupervisor(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
	public void removeSupervisor(Supervisor supervisor) {
		if (supervisor == null)
			throw new NullPointerException("supervisor is null");
		this.supervisorDao.remove(supervisor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getSupervisor(java.lang.String)
	 */
	public Supervisor getSupervisor(String supervisorObjId) {
		if (supervisorObjId == null)
			throw new NullPointerException("supervisorObjId is null");
		return supervisorDao.findById(supervisorObjId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getSupervisors(java.lang.String)
	 */
	public List<Supervisor> getSupervisors(String employeeId) {
		if (employeeId == null)
			throw new NullPointerException("employeeId is null");
		return supervisorDao.findSupervisors(employeeId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmployees(java.lang.String)
	 */
	public List<Supervisor> getEmployees(String supervisorId) {
		if (supervisorId == null)
			throw new NullPointerException("employeeId is null");
		return supervisorDao.findEmployees(supervisorId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPrimarySupervisor(java.lang.String)
	 */
	public Supervisor getPrimarySupervisor(String employeeId) {
		if (employeeId == null)
			throw new NullPointerException("employeeId is null");
		return supervisorDao.findPrimarySupervisor(employeeId);
	}

	/* ----------- DAO Setting methods needed by the Springframework ------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUserDao()
	 */
	public UserDAO getUserDao() {
		return userDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setUserDao(org.openiam.idm.srvc.user.service.UserDAO)
	 */
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUserAttributeDao()
	 */
	public UserAttributeDAO getUserAttributeDao() {
		return userAttributeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setUserAttributeDao(org.openiam.idm.srvc.user.service.UserAttributeDAO)
	 */
	public void setUserAttributeDao(UserAttributeDAO userAttributeDao) {
		this.userAttributeDao = userAttributeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getUserNoteDao()
	 */
	public UserNoteDAO getUserNoteDao() {
		return userNoteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setUserNoteDao(org.openiam.idm.srvc.user.service.UserNoteDAO)
	 */
	public void setUserNoteDao(UserNoteDAO userNoteDao) {
		this.userNoteDao = userNoteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getAddressDao()
	 */
	public AddressDAO getAddressDao() {
		return addressDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setAddressDao(org.openiam.idm.srvc.continfo.service.AddressDAO)
	 */
	public void setAddressDao(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getEmailAddressDao()
	 */
	public EmailAddressDAO getEmailAddressDao() {
		return emailAddressDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setEmailAddressDao(org.openiam.idm.srvc.continfo.service.EmailAddressDAO)
	 */
	public void setEmailAddressDao(EmailAddressDAO emailAddressDao) {
		this.emailAddressDao = emailAddressDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getPhoneDao()
	 */
	public PhoneDAO getPhoneDao() {
		return phoneDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setPhoneDao(org.openiam.idm.srvc.continfo.service.PhoneDAO)
	 */
	public void setPhoneDao(PhoneDAO phoneDao) {
		this.phoneDao = phoneDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#getSupervisorDao()
	 */
	public SupervisorDAO getSupervisorDao() {
		return supervisorDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.user.service.UserDataService#setSupervisorDao(org.openiam.idm.srvc.user.service.SupervisorDAO)
	 */
	public void setSupervisorDao(SupervisorDAO supervisorDao) {
		this.supervisorDao = supervisorDao;
	}

}
