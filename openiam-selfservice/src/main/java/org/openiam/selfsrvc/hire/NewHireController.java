package org.openiam.selfsrvc.hire;


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
import org.openiam.selfsrvc.pswd.UnlockUserValidator;


/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class NewHireController extends AbstractWizardFormController {




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
	
	private static final Log log = LogFactory.getLog(NewHireController.class);

	
	public NewHireController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	
	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		log.debug("Validate page:" + page);
		NewHireValidator validator = (NewHireValidator)getValidator();
		switch (page) {
		case 0: 
			validator.validateNewHireForm(command, errors);
			break;
		case 1:
			//validator.validateUnlockVerifyIdentity(command, errors);
			break;
		}
		
	}
	
	
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException arg3)
			throws Exception {
		
		log.info("In processFinish..");
		
		NewHireCommand newHireCmd =(NewHireCommand)command;
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
	  
	    return new ModelAndView("pub/confirm");   
	        
	}
	
	private List<Login> getPrincipalList(NewHireCommand newHireCmd, User user ) {
		
		List<Login> principalList = new ArrayList<Login>();
		
		LoginId loginId = new LoginId( configuration.getDefaultSecurityDomain(), 
				newHireCmd.getUserPrincipalName(), 
				configuration.getDefaultManagedSysId());
		Login login = getLogin(newHireCmd, user, loginId);
		principalList.add(login);
		
		return principalList;
	}
	private List<Group> getGroupList(NewHireCommand newHireCmd, User user) {
		List<Group> groupList = new ArrayList<Group>();
		String groupId = newHireCmd.getGroup();
		Group g = new Group();
		g.setGrpId(groupId);
		groupList.add(g);
		return groupList;
	}
	private List<Role> getRoleList(NewHireCommand newHireCmd, User user) {
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
	
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) 			throws Exception {
		List<Resource> resourceList = resourceDataService.getAllResources();
		
		NewHireCommand cmd = new NewHireCommand();
		cmd.setResourceList(resourceList);
		
		return cmd;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request, int page) throws Exception {
		switch (page) {
		case 0:
			return loadUserInformation(request);
		case 1:
			return loadAppInformation(request);
		}
		return null;
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
	protected Map loadAppInformation(HttpServletRequest request) {
		Map model = new HashMap();
		
		List<Resource> resourceList = resourceDataService.getAllResources();
		model.put("resourceList", resourceList);
		return model;
		
	}
	
	private void prepareObject(User user, String userId) {
		// need userId to be null so that persistence layer will generate a uid for the user
		user.setUserId(null);
		user.setCreateDate(new Date(System.currentTimeMillis()));
		user.setCreatedBy(userId);
		user.setStatus(UserStatusEnum.PENDING_APPROVAL);
		
	}
	


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



	
	private Login getLogin(NewHireCommand newHireCmd, User usr, LoginId loginId) {
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
