package org.openiam.selfsrvc.reg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;

import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.ProvisioningConstants;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.email.MailService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.service.LocationDataService;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;


/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class SelfRegistrationController extends SimpleFormController {




	UserDataService userMgr;
	LoginDataService loginManager;
	GroupDataService groupManager;
	RoleDataService roleDataService;
	MailService mailService;
	NavigatorDataService navigatorDataService;
	RequestDataService provRequestService;
	OrganizationDataService orgManager;
	LocationDataService locationService;
	
	protected ReferenceDataService refDataService;
	protected PasswordConfiguration configuration;
	protected ResourceDataService resourceDataService;
	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService;
	
	protected ProvisionService provisionService;
	 	
	String defaultDomainId;
	String menuGroup;
	
	private static final Log log = LogFactory.getLog(SelfRegistrationController.class);

	
	public SelfRegistrationController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException arg3)
			throws Exception {
		
		log.info("In processFinish..");
		
		SelfRegistrationCommand newHireCmd =(SelfRegistrationCommand)command;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

		User user = newHireCmd.getUser();
		prepareObject(user, userId);
		
		log.info("User=" + user);
		
/*		userMgr.addUser(user);
		
		log.info("user added..." + user);

		LoginId loginId = new LoginId( configuration.getDefaultSecurityDomain(), 
				newHireCmd.getUserPrincipalName(), 
				configuration.getDefaultManagedSysId());
		Login login = getLogin(newHireCmd, user, loginId);
		
		loginManager.addLogin(login);

		LoginId loginId2 = new LoginId( configuration.getDefaultSecurityDomain(), 
				newHireCmd.getUserPrincipalName(), 
				"100");
		Login login2 = getLogin(newHireCmd, user, loginId2);
		
		loginManager.addLogin(login2);
		
        if (!newHireCmd.getManagedBy().equalsIgnoreCase("-")) {
        	User supervisorUser = userMgr.getUserWithDependent(newHireCmd.getManagedBy(),false);
        	List<Supervisor> superVisorList = userMgr.getSupervisors(userId);
        		// new supervisor
        		Supervisor sup = new Supervisor();
        		sup.setSupervisor(supervisorUser);
        		sup.setEmployee(user);
        		sup.setStatus("ACTIVE");
        		userMgr.addSupervisor(sup);
        }
        this.createRequest(userId, user);
        this.notifyManager(user);
  */      
        //Build the provision user object
        
        // provision the user
/*        Group grp = new Group();
        grp.setGrpId("MY_GROUP");
          
        List<Group> grpList = new ArrayList<Group>();
        grpList.add(grp);
        user.setMemberOfGroups(grpList);
    
        EmailAddress email = new EmailAddress();
        email.setEmailAddress(newHireCmd.getEmail1());
        user.getEmailAddress().add(email);
        
        Phone ph = new Phone();
        ph.setPhoneNbr(newHireCmd.getPersonalNbr());
        ph.setAreaCd(newHireCmd.getPersonalAreaCode());
        
        Address adr = new Address();
        adr.setAddress1("123 some street");
        adr.setCity("Boston");
        adr.setState("MA");
        user.getAddresses().add(adr);
        
        UserAttribute atr = new UserAttribute();
        atr.setName("ATTR1");
        atr.setValue("VALUE 1");
        user.getUserAttributes().put("ATTR1", atr);
        
        user.setUserId("100");
        user.setRequestId("1001");
        Login login = new Login();
        LoginId id = new LoginId("USER_SEC_DOMAIN", "joe.frate", "0");
        login.setId(id);
        login.setUserId("100");
        List<Login> principalList = new ArrayList();
        principalList.add(login);
        user.setPrincipalList(principalList);
  */      
        ProvisionUser pUser = new ProvisionUser(user);
        pUser.setPrincipalList(getPrincipalList(newHireCmd,user));
        
        if (newHireCmd.getGroup() != null && !newHireCmd.getGroup().isEmpty()) {
        	pUser.setMemberOfGroups(getGroupList(newHireCmd, user));
        }
        if (newHireCmd.getRole() != null && !newHireCmd.getRole().isEmpty()) {
        	pUser.setMemberOfRoles(getRoleList(newHireCmd, user));
        }
        
        provisionService.addUser(pUser);
	  
	    return new ModelAndView("pub/confirm");   
	        
	}
	
	private List<Login> getPrincipalList(SelfRegistrationCommand newHireCmd, User user ) {
		
		List<Login> principalList = new ArrayList<Login>();
		
		LoginId loginId = new LoginId( configuration.getDefaultSecurityDomain(), 
				newHireCmd.getUserPrincipalName(), 
				configuration.getDefaultManagedSysId());
		Login login = getLogin(newHireCmd, user, loginId);
		principalList.add(login);
		
		return principalList;
	}
	private List<Group> getGroupList(SelfRegistrationCommand newHireCmd, User user) {
		List<Group> groupList = new ArrayList<Group>();
		String groupId = newHireCmd.getGroup();
		Group g = new Group();
		g.setGrpId(groupId);
		groupList.add(g);
		return groupList;
	}
	private List<Role> getRoleList(SelfRegistrationCommand newHireCmd, User user) {
		List<Role> roleList = new ArrayList<Role>();
		String roleId = newHireCmd.getRole();
		RoleId id = new RoleId(configuration.getDefaultSecurityDomain() , roleId);
		Role r = new Role();
		r.setId(id);
		roleList.add(r);
		
		return roleList;
	}

	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Map model = new HashMap();   
        model.put("message", "Request to reset the password has been canceled");   
        return new ModelAndView("pub/cancel");   
        
	}
	
/*	protected void validatePage(Object command, Errors errors, int page) {
		log.debug("Validate page:" + page);
		NewHireValidator validator = (NewHireValidator)getValidator();
		switch (page) {
		case 0: 
	//		validator.validateUnlockUser(command, errors);
			break;
		case 1:
	//		validator.validateUnlockVerifyIdentity(command, errors);
			break;
		case 2:
	//		validator.validateUnlockSetNewPassword(command, errors);
			break;
		}
		
	}

*/	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("In processFinish..");
		
		SelfRegistrationCommand newHireCmd =(SelfRegistrationCommand)command;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

		User user = newHireCmd.getUser();
		prepareObject(user, userId);
		
		log.info("User=" + user);

        ProvisionUser pUser = new ProvisionUser(user);
        pUser.setPrincipalList(getPrincipalList(newHireCmd,user));
        
        if (newHireCmd.getGroup() != null && !newHireCmd.getGroup().isEmpty()) {
        	pUser.setMemberOfGroups(getGroupList(newHireCmd, user));
        }
        if (newHireCmd.getRole() != null && !newHireCmd.getRole().isEmpty()) {
        	pUser.setMemberOfRoles(getRoleList(newHireCmd, user));
        }
        
        provisionService.addUser(pUser);
        
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("newHireCmd",newHireCmd);
		
		
		return mav;
		
	}




	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		return loadUserInformation(request);
	}
	
	protected Map loadUserInformation(HttpServletRequest request) {
		log.info("referenceData called.");
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get the organizations
		List<Organization> orgList = orgManager.getTopLevelOrganizations();
		// get the divisions
		List<Organization> divList = orgManager.allDivisions(configuration.getParentOrgId());
		// load the department list
		List<Organization> deptList = orgManager.allDepartments(configuration.getParentOrgId());
		

		// get the list of groups that this user belongs to
		List<Group> groupList = groupManager.getAllGroups();	
		// get the list of roles that this user belongs to
		List<Role> roleList = roleDataService.getRolesInDomain(configuration.getDefaultSecurityDomain());
		
		// get the list of managers
		// update later with rich components to allow for better filtering
		UserSearch search = new UserSearch();
		List<User> managerList = userMgr.search(search);
		
		// get the list of job codes
		List<ReferenceData> jobCodeList = refDataService.getRefByGroup("JOB_CODE", "en");
		
		// get the list of user type codes
		List<ReferenceData> userTypeList = refDataService.getRefByGroup("USER_TYPE", "en");
		
		// get location list and the address for the user
		Location[] locationAry = locationService.allLocations();
		
		Map model = new HashMap();
		model.put("orgList",orgList);
		model.put("divList",divList);
		model.put("deptList",deptList);
		model.put("groupList",groupList);
		model.put("roleList", roleList);
		model.put("managerList",managerList);
		model.put("jobCodeList",jobCodeList);
		model.put("userTypeList", userTypeList);
		model.put("locationAry", locationAry);
		
		return model;
		
	}
	
	private void prepareObject(User user, String userId) {
		// need userId to be null so that persistence layer will generate a uid for the user
		user.setUserId(null);
		user.setCreateDate(new Date(System.currentTimeMillis()));
		user.setCreatedBy(userId);
		user.setStatus(UserStatusEnum.PENDING_APPROVAL);
		
	}
	


	
/*	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		log.debug("onSubmit called.");
		
		NewHireCommand newHireCmd =(NewHireCommand)command;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get objects from the command object
		try {
			
			// - User
			User usr = getUser(newHireCmd);
			usr.setCreatedBy(userId);
			usr.setCreateDate(new Date(System.currentTimeMillis()));	
			//usr.setUserId(null);
			userMgr.addUserWithDependent(usr, false);
		
			Supervisor sup = this.getSupervisor(newHireCmd, usr);
			
			LoginId loginId = new LoginId( defaultDomainId,  newHireCmd.getPrincipalName(), "0");
			Login login = getLogin(newHireCmd, usr, loginId);
			
	
			userMgr.addSupervisor(sup);
			loginManager.addLogin(login);
	        
	        // add to group
	        if (newHireCmd.getGroup() != null && !newHireCmd.getGroup().isEmpty()) {
	        	groupManager.addUserToGroup(newHireCmd.getGroup(), usr.getUserId());
	        }
	
	        // add to role

	    
	        
			// set the attributes	
			userAttributes(newHireCmd, usr);	
			// add the phone numbers
			getPhone(newHireCmd, usr);
			// add the email 
			getEmail(newHireCmd, usr);
			// add the addresses
			this.getAddress(newHireCmd, usr);
			
	        userMgr.updateUserWithDependent(usr, true);
	                 
	        // add the applications
        
	        createRequest(userId, usr);
  
	        
	         AddRequestType addReqType = new AddRequestType();
            PSOIdentifierType idType = new PSOIdentifierType( newHireCmd.getPrincipalName(),null, "target");
            addReqType.setPsoID(idType);
            addReqType.setRequestID(String.valueOf( System.currentTimeMillis()));
            addReqType.setTargetID("101");
            
            ExtensibleUser extUser = new ExtensibleUser();
            extUser.setName("My user");
            extUser.getAttributes().add( new ExtensibleAttribute("cn", newHireCmd.getPrincipalName() )); 
            extUser.getAttributes().add( new ExtensibleAttribute("sAMAccountName",newHireCmd.getPrincipalName()));
            extUser.getAttributes().add( new ExtensibleAttribute("sn",usr.getLastName()));
            extUser.getAttributes().add( new ExtensibleAttribute("name", usr.getLastName() + "," + usr.getFirstName() ));
            extUser.getAttributes().add( new ExtensibleAttribute("description","OpenIAM generated User"));
         
      
            addReqType.getData().getAny().add(extUser);
            
            activeDirConnector.add(addReqType);

	        
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
        
        
				
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("newHireCmd",newHireCmd);
		
		
		return mav;
	}

	*/
	private void createRequest(String userId, User usr) {
	       ProvisionRequest req = new ProvisionRequest();
			req.setRequestorId(userId);
			req.setStatus("PENDING_APPROVAL");
			req.setStatusDate(new Date(System.currentTimeMillis()));
			req.setRequestDate(new Date(System.currentTimeMillis()));
			req.setRequestType(ProvisioningConstants.NEW_HIRE);

			
			
			provRequestService.addRequest(req); 
			
			// add a user to the request - this is the person in the New Hire
			Set<RequestUser> reqUserSet =  req.getRequestUsers();
			
			RequestUser reqUser = new RequestUser();
			reqUser.setUserId(usr.getUserId());
			reqUser.setFirstName(usr.getFirstName());
			reqUser.setLastName(usr.getLastName());
			reqUser.setProvRequestId(req.getRequestId());
			reqUserSet.add(reqUser);
			
			provRequestService.updateRequest(req);
		
	}
	
	private void notifyManager(User usr) {
		
	//	String managerId = usr.getManagerId();
	//	User manager = userMgr.getUserWithDependent(managerId, true);
	//	EmailAddress email = manager.getEmailByName("EMAIL1");
		
		mailService.send(null, "suneet_shah@openiam.com", "User Successfully Created", 
				"A request for User: " + usr.getFirstName() + " " + usr.getLastName() + 
				" has been created through the new hire facility. Please log into the OpenIAM Selfservice application to review the request." +
				" http://localhost:8080/selfservice");
	}



/*	private User getUser(NewHireCommand newHireCmd) {
		User usr = new User();
		usr.setFirstName(newHireCmd.getFirstName());
		usr.setLastName(newHireCmd.getLastName() );
		usr.setMiddleInit(newHireCmd.getMiddleName());
		usr.setManagerId( newHireCmd.getManagedBy() );
		usr.setDeptCd( newHireCmd.getDepartment() );
		usr.setTitle( newHireCmd.getTitle() );
		usr.setStatus( UserStatusEnum.PENDING_APPROVAL );
		usr.setCompanyId( newHireCmd.getCompanyName() );
		usr.setDeptCd( newHireCmd.getDepartment() );
		usr.setNickname( newHireCmd.getNickname() );
		usr.setMaidenName( newHireCmd.getMaiden() ) ;
		usr.setDivision( newHireCmd.getDivision() );
		usr.setJobCode( newHireCmd.getJobCode() );
		usr.setSex( newHireCmd.getGender() );
		usr.setMetadataTypeId("InetOrgPerson");
		
			
		return usr;
	}
	private Supervisor getSupervisor(NewHireCommand newHireCmd, User usr) {
		Supervisor sup = new Supervisor();
		sup.setEmployee(usr);
		sup.setSupervisor(new User(newHireCmd.getManagedBy()));
		sup.setStartDate(new Date(System.currentTimeMillis()));
		sup.setIsPrimarySuper(new Integer(1));
		sup.setStatus("ACTIVE");
		return sup;
	}	

	private void userAttributes(NewHireCommand newHireCmd, User usr ) {
		Map<String, UserAttribute> attrMap = usr.getUserAttributes(); 
		
		UserAttribute userAttribute = new UserAttribute();
	
		userAttribute.setName("FULL_PART");
		userAttribute.setValue(newHireCmd.getFullPart());

		attrMap.put(userAttribute.getName(),userAttribute);
		  
	}
*/	
	private Login getLogin(SelfRegistrationCommand newHireCmd, User usr, LoginId loginId) {
		Login lg = new Login();
		lg.setUserId(usr.getUserId());

 		lg.setId(loginId);
 		lg.setAuthFailCount(0);
 		lg.setIsLocked(0);
 		lg.setIsDefault(0);
 		lg.setCreateDate(new Date(System.currentTimeMillis()));
 		lg.setCreatedBy(usr.getUserId());

 		// get the password policy to figure out the initial password
 /*		SecurityDomain secDomain = secDomainService.getSecurityDomain(configuration.getDefaultSecurityDomain());
 		Policy policy = policyDataService.getPolicy(secDomain.getPasswordPolicyId());
 		PolicyAttribute initialPassword =  policy.getAttribute("INITIAL_PSWD_VALUE");
 		log.debug("Intial password =" + initialPassword.getValue1());
 */		
 		String encPassword = loginManager.encryptPassword("passwd00");
 		
 		lg.setPassword(encPassword);

 		return lg;
	}
	
/*
	private void getEmail(NewHireCommand newHireCmd, User usr) {
		Set<EmailAddress> emailAdrSet =  usr.getEmailAddress();
		
		EmailAddress email1 = new EmailAddress();
		email1.setEmailAddress(newHireCmd.getEmail1());
		email1.setParentId(usr.getUserId());
		email1.setName("EMAIL1");
		email1.setParentType(ContactConstants.PARENT_TYPE_USER);

		EmailAddress email2 = new EmailAddress();
		email2.setEmailAddress(newHireCmd.getEmail2());
		email2.setParentId(usr.getUserId());
		email2.setName("EMAIL2");
		email2.setParentType(ContactConstants.PARENT_TYPE_USER);
	

		emailAdrSet.add(email1);
		emailAdrSet.add(email2);
		
	}

	private void getAddress(NewHireCommand newHireCmd, User usr) {
		Set<Address> adrSet =  usr.getAddresses();
		
		Address addr = new Address();
		addr.setAddress1(newHireCmd.getAddress1());
		addr.setAddress2(newHireCmd.getAddress2());
		addr.setCity(newHireCmd.getCity());
		addr.setCountry(newHireCmd.getCountry());
		addr.setState(newHireCmd.getState());
		addr.setPostalCd(newHireCmd.getPostalCode());
		addr.setParentId(usr.getUserId());
		addr.setName("DEFAULT ADR");
		addr.setParentType(ContactConstants.PARENT_TYPE_USER);
		adrSet.add(addr);
	
		
	}
	
	private void getPhone(NewHireCommand newHireCmd, User usr) {
		Set<Phone> phSet = usr.getPhone();

		Phone deskPhone = new Phone();
		deskPhone.setAreaCd(newHireCmd.getWorkAreaCode());
		deskPhone.setPhoneNbr(newHireCmd.getWorkPhone());
		deskPhone.setDescription("WORK");
		deskPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		deskPhone.setName("Desk Phone");
		deskPhone.setParentId(usr.getUserId());

		Phone cellPhone = new Phone();
		cellPhone.setAreaCd(newHireCmd.getCellAreaCode());
		cellPhone.setPhoneNbr(newHireCmd.getCellPhone());
		cellPhone.setDescription("CELL");
		cellPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		cellPhone.setName("Cell Phone");
		cellPhone.setParentId(usr.getUserId());

		Phone faxPhone = new Phone();
		faxPhone.setAreaCd(newHireCmd.getFaxAreaCode());
		faxPhone.setPhoneNbr(newHireCmd.getFaxPhone());
		faxPhone.setDescription("FAX");
		faxPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		faxPhone.setName("Fax Phone");
		faxPhone.setParentId(usr.getUserId());

		Phone homePhone = new Phone();
		homePhone.setAreaCd(newHireCmd.getHomePhoneAreaCode());
		homePhone.setPhoneNbr(newHireCmd.getHomePhoneNbr());
		homePhone.setDescription("HOME");
		homePhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		homePhone.setName("Home Phone");
		homePhone.setParentId(usr.getUserId());
		
		Phone altCellPhone = new Phone();
		altCellPhone.setAreaCd(newHireCmd.getAltCellAreaCode());
		altCellPhone.setPhoneNbr(newHireCmd.getAltCellNbr());
		altCellPhone.setDescription("ALT CELL");
		altCellPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		altCellPhone.setName("Alt Cell Phone");
		altCellPhone.setParentId(usr.getUserId());
		
		Phone personalPhone = new Phone();
		personalPhone.setAreaCd(newHireCmd.getPersonalAreaCode());
		personalPhone.setPhoneNbr(newHireCmd.getPersonalNbr());
		personalPhone.setDescription("PERSONAL PHONE");
		personalPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		personalPhone.setName("Personal Phone");
		personalPhone.setParentId(usr.getUserId());
		
		phSet.add(deskPhone);
		phSet.add(cellPhone);
		phSet.add(faxPhone);
		phSet.add(homePhone);
		phSet.add(altCellPhone);
		phSet.add(personalPhone);

		
		
	}
	
	*/

	


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


	public MailService getMailService() {
		return mailService;
	}


	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	


	public RoleDataService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}


	public String getDefaultDomainId() {
		return defaultDomainId;
	}


	public void setDefaultDomainId(String defaultDomainId) {
		this.defaultDomainId = defaultDomainId;
	}


	public NavigatorDataService getNavigatorDataService() {
		return navigatorDataService;
	}


	public void setNavigatorDataService(NavigatorDataService navigatorDataService) {
		this.navigatorDataService = navigatorDataService;
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


	public OrganizationDataService getOrgManager() {
		return orgManager;
	}


	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}


	public LocationDataService getLocationService() {
		return locationService;
	}


	public void setLocationService(LocationDataService locationService) {
		this.locationService = locationService;
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


	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}


	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}


	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}


	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}


	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}


	public ProvisionService getProvisionService() {
		return provisionService;
	}


	public void setProvisionService(ProvisionService provisionService) {
		this.provisionService = provisionService;
	}




}
