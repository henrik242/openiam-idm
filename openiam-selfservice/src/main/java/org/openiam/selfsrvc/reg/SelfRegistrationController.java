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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.user.dto.User;

import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.ProvisioningConstants;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;

import com.thoughtworks.xstream.XStream;


/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class SelfRegistrationController extends SimpleFormController {




	UserDataWebService userMgr;
	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	protected LoginDataWebService loginManager;
	protected GroupDataWebService groupManager;
	protected RoleDataWebService roleDataService;
	protected MailService mailService;
	protected NavigatorDataWebService navigatorDataService;
	protected RequestWebService provRequestService;
	protected OrganizationDataService orgManager;
	protected LocationDataWebService locationService;
	
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
  
        // create the request 
        log.info("User created. New User Id: " + user.getUserId());
        
        
        /* serialize the object into xml */
        String userAsXML = toXML(pUser);
        log.info("pUser serialized to XML:" + userAsXML);
               
        ProvisionRequest pReq = createRequest(userId, user, userAsXML, pUser);
        
        //
        // send a notification to the approver
        
        //
        
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("newHireCmd",newHireCmd);
		
		
		return mav;
		
	}


	private String toXML(ProvisionUser pUser) {
		XStream xstream = new XStream();
		return xstream.toXML(pUser);
		
	}
	
	private ProvisionRequest createRequest(String userId, User usr, String asXML, ProvisionUser pUser) {

		
        ProvisionRequest req = new ProvisionRequest();
		req.setRequestorId(userId);
		req.setStatus("PENDING_APPROVAL");
		req.setStatusDate(new Date(System.currentTimeMillis()));
		req.setRequestDate(new Date(System.currentTimeMillis()));
		req.setRequestType(ProvisioningConstants.NEW_HIRE);
		req.setRequestReason(ProvisioningConstants.NEW_HIRE);
		req.setRequestXML(asXML);
		//req.setManagedResourceId(managedResource.getManagedSysId());
		
		// add a user to the request - this is the person in the New Hire
		Set<RequestUser> reqUserSet =  req.getRequestUsers();
		
		RequestUser reqUser = new RequestUser();
		reqUser.setFirstName(usr.getFirstName());
		reqUser.setLastName(usr.getLastName());
		reqUserSet.add(reqUser);
		
		provRequestService.initiateRequest(req, pUser.getSupervisor()); 
		return req;
			

		
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		String rMenu = request.getParameter("hideRMenu");
		String header = request.getParameter("hideHeader");
		
		if (rMenu != null && rMenu.length() > 0) {
			request.setAttribute("hideRMenu","1");
		}
		if (header != null && header.length() > 0) {
			request.setAttribute("hideHeader","1");
		}
		
		return loadUserInformation(request);
	}
	
	protected Map loadUserInformation(HttpServletRequest request) {
		log.info("referenceData called.");
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get the organizations
		List<Organization> orgList = orgManager.getOrganizationList(null,"ACTIVE"); 
		// get the divisions
		List<Organization> divList = orgManager.allDivisions(configuration.getParentOrgId());
		// load the department list
		List<Organization> deptList = orgManager.allDepartments(configuration.getParentOrgId());
		

		// get the list of groups that this user belongs to
		List<Group> groupList = groupManager.getAllGroups().getGroupList();	
		// get the list of roles that this user belongs to
		List<Role> roleList = roleDataService.getRolesInDomain(configuration.getDefaultSecurityDomain()).getRoleList();
		
		// get the list of managers
		// update later with rich components to allow for better filtering
	//	UserSearch search = new UserSearch();
	//	List<User> managerList = userMgr.search(search);
		
		// get the list of job codes
		List<ReferenceData> jobCodeList = refDataService.getRefByGroup("JOB_CODE", "en");
		
		// get the list of user type codes
		List<ReferenceData> userTypeList = refDataService.getRefByGroup("USER_TYPE", "en");
		
		// get location list and the address for the user
		Location[] locationAry = locationService.allLocations().getLocationAry();
		
		Map model = new HashMap();
		model.put("orgList",orgList);
		model.put("divList",divList);
		model.put("deptList",deptList);
		model.put("groupList",groupList);
		model.put("roleList", roleList);
		//model.put("managerList",managerList);
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
			reqUser.setRequestId(req.getRequestId());
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
 		String encPassword = (String)loginManager.encryptPassword("passwd00").getResponseValue();
 		
 		lg.setPassword(encPassword);

 		return lg;
	}
	

	public MailService getMailService() {
		return mailService;
	}


	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	



	public String getDefaultDomainId() {
		return defaultDomainId;
	}


	public void setDefaultDomainId(String defaultDomainId) {
		this.defaultDomainId = defaultDomainId;
	}




	public String getMenuGroup() {
		return menuGroup;
	}


	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}



	public OrganizationDataService getOrgManager() {
		return orgManager;
	}


	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
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


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	public GroupDataWebService getGroupManager() {
		return groupManager;
	}


	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}


	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}


	public NavigatorDataWebService getNavigatorDataService() {
		return navigatorDataService;
	}


	public void setNavigatorDataService(NavigatorDataWebService navigatorDataService) {
		this.navigatorDataService = navigatorDataService;
	}


	public RequestWebService getProvRequestService() {
		return provRequestService;
	}


	public void setProvRequestService(RequestWebService provRequestService) {
		this.provRequestService = provRequestService;
	}


	public LocationDataWebService getLocationService() {
		return locationService;
	}


	public void setLocationService(LocationDataWebService locationService) {
		this.locationService = locationService;
	}




}
