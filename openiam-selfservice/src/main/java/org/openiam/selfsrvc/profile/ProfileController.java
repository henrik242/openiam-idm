package org.openiam.selfsrvc.profile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.email.MailService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.service.LocationDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;


public class ProfileController extends SimpleFormController {


	UserDataService userMgr;
	LoginDataService loginManager;
	GroupDataService groupManager;
	RoleDataService roleDataService;
	ManagedSystemDataService managedSysService;
	RequestDataService provRequestService;
	OrganizationDataService orgManager;
	LocationDataService locationDataService;
	
	ReferenceDataService refDataService;
	protected PasswordConfiguration configuration;
	
	String menuGroup;
	String parentOrgId;
	
	private static final Log log = LogFactory.getLog(ProfileController.class);

	
	public ProfileController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
		
	}

	
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		log.info("refernceData called.");
		
		Map<Object, Object> dataMap = new HashMap<Object, Object>();
		dataMap.put("groupList", groupManager.getAllGroups() );
		
		return dataMap;
		

	}
	


	@Override
	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		
		
		ProfileCommand profileCmd = new ProfileCommand();
		
				
		HttpSession session =  request.getSession();
		String upn = (String)session.getAttribute("login");
		String userId = (String)session.getAttribute("userId");
		
		User usr = userMgr.getUserWithDependent(userId, true);
		List<Supervisor> supVisorList = userMgr.getSupervisors(userId);
		if (supVisorList != null && !supVisorList.isEmpty()) {
			Supervisor supervisor = supVisorList.get(0);
			profileCmd.setManagedBy(supervisor.getSupervisor().getUserId());
		}
		
		Address addr = userMgr.getAddressByName(userId, "DEFAULT ADR");
		
		
		EmailAddress email1 =  userMgr.getEmailAddressByName(userId, "EMAIL1");
		EmailAddress email2 =  userMgr.getEmailAddressByName(userId, "EMAIL2");
		EmailAddress email3 =  userMgr.getEmailAddressByName(userId, "EMAIL3");
		

		Phone deskPhone = userMgr.getPhoneByName(userId, "DESK PHONE");
		Phone cellPhone = userMgr.getPhoneByName(userId, "CELL PHONE");
		Phone faxPhone = userMgr.getPhoneByName(userId, "FAX");
		Phone homePhone = userMgr.getPhoneByName(userId, "HOME PHONE");
		Phone altCellPhone = userMgr.getPhoneByName(userId, "ALT CELL PHONE");
		Phone personalPhone = userMgr.getPhoneByName(userId, "PERSONAL PHONE");

		// get the organizations
		profileCmd.setOrgList( orgManager.getTopLevelOrganizations() );
		// get the divisions
		profileCmd.setDivisionList( orgManager.allDivisions(this.parentOrgId));

		// load the department list
		profileCmd.setDepartmentList(orgManager.allDepartments(this.parentOrgId));
		
		
		// get the list of groups that this user belongs to
		List<Group> userGroupList = groupManager.getUserInGroups(userId);
		profileCmd.setSelectedGroups(userGroupList);
		
		// get the list of roles that this user belongs to
		List<Role> userRoles = roleDataService.getUserRoles(userId);
		profileCmd.setRoleAry(userRoles);
		
		// get the list of managers
		// update later with rich components to allow for better filtering
		UserSearch search = new UserSearch();
		List<User> userList = userMgr.search(search);
		profileCmd.setManagerList(userList);
		
		// get the list of job codes
		List<ReferenceData> jobCodeList = refDataService.getRefByGroup("JOB_CODE", "en");
		profileCmd.setJobCodeList(jobCodeList);
		
		// get the list of user type codes
		List<ReferenceData> userTypeList = refDataService.getRefByGroup("USER_TYPE", "en");
		profileCmd.setUserTypeList(userTypeList);
		
		setUserCommand(usr,profileCmd);
		setAddressCommand(usr, addr, profileCmd);
		setEmailCommand(usr,email1, email2, email3, profileCmd);
		setPhoneCommand(usr,deskPhone, cellPhone, faxPhone, homePhone, altCellPhone, personalPhone, profileCmd);
		
		// load the location list
		Location[] locationAry = locationDataService.allLocations();
		profileCmd.setLocationAry(locationAry);

		return profileCmd;
		
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		
		System.out.println("ProfileController: onSubmit");
		
		ProfileCommand profileCmd =(ProfileCommand)command;
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		User usr = userMgr.getUserWithDependent(userId, true);
		
		System.out.println("User=" + usr);
		
		// - User
		usr = updateUser(usr,profileCmd);
			
		// add the phone numbers
		getPhone(profileCmd, usr);
		// add the email 

		getEmail(profileCmd, usr);
		// add the addresses
		getAddress(profileCmd, usr);
	
        userMgr.updateUserWithDependent(usr, true);
        
        // set the supervisor
        if (!profileCmd.getManagedBy().equalsIgnoreCase("-")) {
        	User supervisorUser = userMgr.getUserWithDependent(profileCmd.getManagedBy(),false);
        	List<Supervisor> superVisorList = userMgr.getSupervisors(userId);
        	if (superVisorList != null && !superVisorList.isEmpty()) {
        		Supervisor supervisor = superVisorList.get(0);
        		// update an existing supervisor record
	            supervisor.setSupervisor(supervisorUser);
	            userMgr.updateSupervisor(supervisor);
        	} else {
        		// new supervisor
        		Supervisor sup = new Supervisor();
        		sup.setSupervisor(supervisorUser);
        		sup.setEmployee(usr);
        		sup.setStatus("ACTIVE");
        		userMgr.addSupervisor(sup);
        	}
        }
          
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("profileCmd",profileCmd);
		
		
		return mav;
	}


	// methods to move data from a domain object to a command object.
	private void setUserCommand(User usr, ProfileCommand profile) {
		
		profile.setUserId(profile.getUserId());
		profile.setFirstName(usr.getFirstName());
		profile.setLastName(usr.getLastName() );
		profile.setMiddleName(usr.getMiddleInit());
		profile.setNickname( usr.getNickname() );
		profile.setMaiden( usr.getMaidenName() ) ;
		profile.setSuffix(usr.getSuffix());
		
		profile.setDepartment( usr.getDeptCd() );
		profile.setDivision( usr.getDivision() );
		
		profile.setTitle( usr.getTitle() );
		profile.setCompanyName( usr.getCompanyId() );

		profile.setJobCode( usr.getJobCode() );
		profile.setGender( usr.getSex() );
		profile.setPasswordTheme(usr.getPasswordTheme());
		profile.setBirthDate(usr.getBirthdate() ); 
		profile.setEmployeeType(usr.getEmployeeType());
		profile.setLocationBldg(usr.getLocationCd());

	}
	
	
	private void setAddressCommand(User usr, Address adr, ProfileCommand profileCmd) {
		if (adr != null) {
			profileCmd.setBldgNbr(adr.getBldgNumber());
			profileCmd.setAddress1(adr.getAddress1());
			profileCmd.setCity(adr.getCity());
			profileCmd.setState(adr.getState());
			profileCmd.setPostalCode(adr.getPostalCd());
		}
	}
	private void setPhoneCommand(User usr, Phone deskPhone, Phone cellPhone, Phone faxPhone, Phone homePhone, 
			Phone altCellPhone, Phone personalPhone, ProfileCommand profile) {

		if (deskPhone != null) {
			profile.setWorkAreaCode(deskPhone.getAreaCd());
			profile.setWorkPhoneId(deskPhone.getPhoneId());
			profile.setWorkPhone(deskPhone.getPhoneNbr());	
		}
		if (cellPhone != null) {
			profile.setCellAreaCode(cellPhone.getAreaCd());
			profile.setCellPhoneId(cellPhone.getPhoneId());
			profile.setCellPhone(cellPhone.getPhoneNbr());
		}
		if (faxPhone != null) {
			profile.setFaxAreaCode(faxPhone.getAreaCd());
			profile.setFaxPhoneId(faxPhone.getPhoneId());
			profile.setFaxPhone(faxPhone.getPhoneNbr());
		}
		if (homePhone != null) {
			profile.setHomePhoneAreaCode(homePhone.getAreaCd());
			profile.setHomePhoneIdr(homePhone.getPhoneId());
			profile.setHomePhoneNbr(homePhone.getPhoneNbr());
		}
		if (altCellPhone != null) {
			profile.setAltCellAreaCode(altCellPhone.getAreaCd());
			profile.setAltCellNbrId(altCellPhone.getPhoneId());
			profile.setAltCellNbr(altCellPhone.getPhoneNbr());
		}
		
		if (personalPhone != null) {
			profile.setPersonalAreaCode(personalPhone.getAreaCd());
			profile.setPersonalNbrId(personalPhone.getPhoneId());
			profile.setPersonalNbr(personalPhone.getPhoneNbr());
		}		

	}
	private void setEmailCommand(User usr, EmailAddress email1, EmailAddress email2, EmailAddress email3, ProfileCommand profile) {
	
		if (email1 != null) {
			profile.setEmail1(email1.getEmailAddress());
			profile.setEmail1Id(email1.getEmailId());
		}
		if (email2 != null) {
			profile.setEmail2(email2.getEmailAddress());
			profile.setEmail2Id(email2.getEmailId());
		}
		if (email3 != null) {
			profile.setEmail3(email3.getEmailAddress());
			profile.setEmail3Id(email3.getEmailId());
		}
	
	}

	private void setSupervisorCommand(User usr, ProfileCommand profile) {
		
	}
	private void setLoginCommand(User usr, String upn,  ProfileCommand profile) {
		try {
		Login lg = loginManager.getLogin("USR_SEC_DOMAIN", upn );
		if (lg != null && lg.getId() != null){
			profile.setUserPrincipalName(lg.getId().getLogin());
			profile.setSecdomain(lg.getId().getManagedSysId());
			profile.setManagedSysId(lg.getId().getManagedSysId());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	// methods to get data from a form to a domain object that we can persist
	
	private User updateUser(User usr, ProfileCommand profileCmd) {
		usr.setFirstName(profileCmd.getFirstName());
		usr.setLastName(profileCmd.getLastName() );
		usr.setMiddleInit(profileCmd.getMiddleName());
		usr.setSuffix(profileCmd.getSuffix());
		usr.setNickname( profileCmd.getNickname() );
		usr.setMaidenName( profileCmd.getMaiden() ) ;
		
		usr.setCompanyId( profileCmd.getCompanyName() );
		usr.setDeptCd( profileCmd.getDepartment() );
		usr.setDivision( profileCmd.getDivision() );
		
		usr.setTitle( profileCmd.getTitle() );
		usr.setJobCode( profileCmd.getJobCode() );
		usr.setSex( profileCmd.getGender() );
		usr.setBirthdate(profileCmd.getBirthDate());
		usr.setEmployeeType(profileCmd.getEmployeeType());
		usr.setLocationCd(profileCmd.getLocationBldg());
		
		// update the phone, email and address info with the user so that it appears in the search
		// denormalized for performance
		usr.setAreaCd(profileCmd.getWorkAreaCode());
		usr.setPhoneNbr(profileCmd.getWorkPhone());
		usr.setEmail(profileCmd.getEmail1());
		usr.setBldgNum(profileCmd.getBldgNbr());
		usr.setAddress1(profileCmd.getAddress1());
		usr.setCity(profileCmd.getCity());
		usr.setState(profileCmd.getState());
		usr.setPostalCd(profileCmd.getPostalCode());

		
		return usr;
	}
	private Supervisor getSupervisor(ProfileCommand newHireCmd, User usr) {
		Supervisor sup = new Supervisor();
		sup.setEmployee(usr);
		sup.setSupervisor(new User(newHireCmd.getManagedBy()));
		sup.setStartDate(new Date(System.currentTimeMillis()));
		sup.setIsPrimarySuper(new Integer(1));
		sup.setStatus("ACTIVE");
		return sup;
	}	

	
	private void getEmail(ProfileCommand profileCommand, User usr) {
		Set<EmailAddress> emailAdrSet =  usr.getEmailAddress();
		
		EmailAddress email1 = null, email2 = null, email3 = null;
		
		Iterator<EmailAddress> emailIterator = emailAdrSet.iterator();
		while (emailIterator.hasNext()) {
			EmailAddress e = emailIterator.next();
			if (e.getName().equalsIgnoreCase("EMAIL1")) {			
				// update
				e.setEmailAddress(profileCommand.getEmail1());
			}
			if (e.getName().equalsIgnoreCase("EMAIL2")) {			
				// update
				e.setEmailAddress(profileCommand.getEmail2());
			}
			if (e.getName().equalsIgnoreCase("EMAIL3")) {			
				// update
				e.setEmailAddress(profileCommand.getEmail3());
			}
		}
		
		if (!emailExists(emailAdrSet, "EMAIL1")) {
			// add email
			email1 = new EmailAddress();
			email1.setEmailAddress(profileCommand.getEmail1());
			email1.setName("EMAIL1");
			email1.setParentId(usr.getUserId());
			email1.setParentType(ContactConstants.PARENT_TYPE_USER);
			emailAdrSet.add(email1);
			
		}
		if (!emailExists(emailAdrSet, "EMAIL2")) {
			// add email
			email2 = new EmailAddress();
			email2.setEmailAddress(profileCommand.getEmail2());
			email2.setName("EMAIL2");
			email2.setParentId(usr.getUserId());
			email2.setParentType(ContactConstants.PARENT_TYPE_USER);
			emailAdrSet.add(email2);
		}		
		if (!emailExists(emailAdrSet, "EMAIL3")) {
			// add email
			email3 = new EmailAddress();
			email3.setEmailAddress(profileCommand.getEmail3());
			email3.setName("EMAIL3");
			email3.setParentId(usr.getUserId());
			email3.setParentType(ContactConstants.PARENT_TYPE_USER);
			emailAdrSet.add(email3);
			
		}	
		
	}

	private void getAddress(ProfileCommand profileCmd, User usr) {
		Set<Address> adrSet =  usr.getAddresses();
		boolean found = false;
		
		Iterator<Address> adrIt = adrSet.iterator();
		while (adrIt.hasNext()) {
			Address adr = adrIt.next();
			if (adr.getName().equalsIgnoreCase("DEFAULT ADR")) {
				found = true;
				adr.setAddress1(profileCmd.getAddress1());
				adr.setAddress2(profileCmd.getAddress2());
				adr.setCity(profileCmd.getCity());
				adr.setCountry(profileCmd.getCountry());
				adr.setState(profileCmd.getState());
				adr.setPostalCd(profileCmd.getPostalCode());
				adr.setBldgNumber(profileCmd.getBldgNbr());
			}
		}
		if (!found) {
			Address addr = new Address();
			addr.setAddress1(profileCmd.getAddress1());
			addr.setAddress2(profileCmd.getAddress2());
			addr.setCity(profileCmd.getCity());
			addr.setCountry(profileCmd.getCountry());
			addr.setState(profileCmd.getState());
			addr.setPostalCd(profileCmd.getPostalCode());
			addr.setBldgNumber(profileCmd.getBldgNbr());
			addr.setParentId(usr.getUserId());
			addr.setName("DEFAULT ADR");
			addr.setParentType(ContactConstants.PARENT_TYPE_USER);
			adrSet.add(addr);
		}
		
	}
	


	private boolean phoneExists(Set<Phone> phSet, String name) {
		Iterator<Phone> phoneIterator = phSet.iterator();
		if (phoneIterator == null) {
			return false;
		}
		Phone p = null;
		while (phoneIterator.hasNext()) {
			p = phoneIterator.next();
			if (p.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;		
	}

	private boolean emailExists(Set<EmailAddress> emailSet, String name) {
		Iterator<EmailAddress> emailIterator = emailSet.iterator();
		if (emailIterator == null) {
			return false;
		}
		EmailAddress em = null;
		while (emailIterator.hasNext()) {
			em = emailIterator.next();
			if (em.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;		
	}
	
	
	private void getPhone(ProfileCommand profileCmd, User usr) {
		Set<Phone> phSet = usr.getPhone();
		
		Phone deskPhone = null, cellPhone = null, faxPhone=null, homePhone = null, altCellPhone = null, personalPhone = null;

		Iterator<Phone> phoneIterator = phSet.iterator();
		while (phoneIterator.hasNext()) {
			Phone p = phoneIterator.next();
			if (p.getName().equalsIgnoreCase("DESK PHONE")) {			
				// update
				p.setAreaCd(profileCmd.getWorkAreaCode());
				p.setPhoneNbr(profileCmd.getWorkPhone());
			}
			if (p.getName().equalsIgnoreCase("CELL PHONE")) {
					// update
					p.setAreaCd(profileCmd.getCellAreaCode());
					p.setPhoneNbr(profileCmd.getCellPhone());
			}
			if (p.getName().equalsIgnoreCase("FAX")) {
					// update
					p.setAreaCd(profileCmd.getFaxAreaCode());
					p.setPhoneNbr(profileCmd.getFaxPhone());
			}
			if (p.getName().equalsIgnoreCase("HOME PHONE")) {
					// update
					p.setAreaCd(profileCmd.getHomePhoneAreaCode());
					p.setPhoneNbr(profileCmd.getHomePhoneNbr());
							}
			if (p.getName().equalsIgnoreCase("ALT CELL PHONE")) {
					// update
					p.setAreaCd(profileCmd.getAltCellAreaCode());
					p.setPhoneNbr(profileCmd.getAltCellNbr());
			}			
			if (p.getName().equalsIgnoreCase("PERSONAL PHONE")) {
					// update
					p.setAreaCd(profileCmd.getPersonalAreaCode());
					p.setPhoneNbr(profileCmd.getPersonalNbr());			
			}
		}
		// add obbject
		if (!phoneExists(phSet, "DESK PHONE")) {
			// add
			deskPhone = new Phone();
			deskPhone.setAreaCd(profileCmd.getWorkAreaCode());
			deskPhone.setPhoneNbr(profileCmd.getWorkPhone());
			deskPhone.setDescription("WORK");
			deskPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			deskPhone.setName("DESK PHONE");
			deskPhone.setParentId(usr.getUserId());
			phSet.add(deskPhone);
		}		
		if (!phoneExists(phSet, "CELL PHONE") ) {
			// add
			cellPhone = new Phone();
			cellPhone.setAreaCd(profileCmd.getCellAreaCode());
			cellPhone.setPhoneNbr(profileCmd.getCellPhone());
			cellPhone.setDescription("CELL");
			cellPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			cellPhone.setName("CELL PHONE");
			cellPhone.setParentId(usr.getUserId());
			phSet.add(cellPhone);
		}

		if (!phoneExists(phSet, "FAX")) {
			// add
			faxPhone = new Phone();
			faxPhone.setAreaCd(profileCmd.getFaxAreaCode());
			faxPhone.setPhoneNbr(profileCmd.getFaxPhone());
			faxPhone.setDescription("FAX");
			faxPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			faxPhone.setName("FAX");
			faxPhone.setParentId(usr.getUserId());
			phSet.add(faxPhone);
		}
		if (!phoneExists(phSet, "HOME PHONE")) {
			// add

			homePhone = new Phone();
			homePhone.setAreaCd(profileCmd.getHomePhoneAreaCode());
			homePhone.setPhoneNbr(profileCmd.getHomePhoneNbr());
			homePhone.setDescription("HOME");
			homePhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			homePhone.setName("HOME PHONE");
			homePhone.setParentId(usr.getUserId());
			phSet.add(homePhone);
		}
		if (!phoneExists(phSet, "ALT CELL PHONE") ) {
			// add
			altCellPhone = new Phone();
			altCellPhone.setAreaCd(profileCmd.getAltCellAreaCode());
			altCellPhone.setPhoneNbr(profileCmd.getAltCellNbr());
			altCellPhone.setDescription("ALT CELL");
			altCellPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			altCellPhone.setName("ALT CELL PHONE");
			altCellPhone.setParentId(usr.getUserId());
			altCellPhone.setPhoneId(profileCmd.getAltCellNbrId());
			phSet.add(altCellPhone);
		}
		if (!phoneExists(phSet, "PERSONAL PHONE") ) {
			// add
			personalPhone = new Phone();
			personalPhone.setAreaCd(profileCmd.getPersonalAreaCode());
			personalPhone.setPhoneNbr(profileCmd.getPersonalNbr());
			personalPhone.setDescription("PERSONAL PHONE");
			personalPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
			personalPhone.setName("PERSONAL PHONE");
			personalPhone.setParentId(usr.getUserId());
			phSet.add(personalPhone);
		}
	
		
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


	public GroupDataService getGroupManager() {
		return groupManager;
	}


	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}



	public RoleDataService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}



	public String getMenuGroup() {
		return menuGroup;
	}


	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}


	public RequestDataService getProvRequestService() {
		return provRequestService;
	}


	public void setProvRequestService(RequestDataService provRequestService) {
		this.provRequestService = provRequestService;
	}


	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}


	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	public OrganizationDataService getOrgManager() {
		return orgManager;
	}


	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}


	public String getParentOrgId() {
		return parentOrgId;
	}


	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}


	public LocationDataService getLocationDataService() {
		return locationDataService;
	}


	public void setLocationDataService(LocationDataService locationDataService) {
		this.locationDataService = locationDataService;
	}


	public ReferenceDataService getRefDataService() {
		return refDataService;
	}


	public void setRefDataService(ReferenceDataService refDataService) {
		this.refDataService = refDataService;
	}


	public PasswordConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}
	
}
