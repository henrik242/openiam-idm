package org.openiam.selfsrvc.prov;


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
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
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
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.ProvisioningConstants;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.email.MailService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.service.LocationDataService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.ExtensibleAttribute;
import org.openiam.spml2.msg.ExtensibleUser;
import org.openiam.spml2.msg.PSOIdentifierType;

/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class ChangeAccessWizardController extends AbstractWizardFormController {


	UserDataService userMgr;
	LoginDataService loginManager;
	GroupDataService groupManager;
	RoleDataService roleDataService;
	MailService mailService;
	NavigatorDataService navigatorDataService;
	RequestDataService provRequestService;
	OrganizationDataService orgManager;
	LocationDataService locationService;
	
	ConnectorService activeDirConnector;
	 	
	String defaultDomainId;
	String menuGroup;
	
	private static final Log log = LogFactory.getLog(ChangeAccessWizardController.class);

	
	public ChangeAccessWizardController() {
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
		
	
		
		  Map model = new HashMap();   
	      model.put("message", "Job done!");   
	      return new ModelAndView("pub/confirm");   
	        
	}

	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Map model = new HashMap();   
        model.put("message", "Request to reset the password has been canceled");   
        return new ModelAndView("pub/cancel");   
        
	}
	
	protected void validatePage(Object command, Errors errors, int page) {
		log.debug("Validate page:" + page);
		ChangeAccessValidator validator = (ChangeAccessValidator)getValidator();
		switch (page) {
		case 0: 
		//	validator.validateUnlockUser(command, errors);
			break;
		case 1:
		//	validator.validateUnlockVerifyIdentity(command, errors);
			break;
		case 2:
		//	validator.validateUnlockSetNewPassword(command, errors);
			break;
		}
		
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		ChangeAccessCommand command = new ChangeAccessCommand();
		// get the user information
		HttpSession session = (HttpSession)request.getSession();
		String userId = (String)session.getAttribute("userId");
		User usr = userMgr.getUserWithDependent(userId, false);
		command.setFirstName(usr.getFirstName());
		command.setLastName( usr.getLastName() );
		command.setPhoneAreaCd(usr.getAreaCd());
		command.setPhoneNbr(usr.getPhoneNbr());		
		command.setTitle(usr.getTitle());
		command.setEmail(usr.getEmail());
		command.setDepartment(usr.getDeptCd());
		command.setRequestorUserId(usr.getUserId());
		// get the list of roles
		
		return command;
		
		
	}


	public UserDataService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}

	
	
/*	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		log.debug("referenceData called.");
		
		List<Organization> orgList = orgManager.getTopLevelOrganizations();
		if (orgList == null) {
			log.debug("orgList is null");
		}else {
			log.debug("Organization list size=" + orgList.size());
		}
		// get roles
		List<Role> roleAry = roleDataService.getRolesInDomain(defaultDomainId);
		
		// get groups
		List<Group> groupList = groupManager.getAllGroups();
		
		// get manager
		
		// get status codes
		
		// get full time / part time codes
		
		// get gender codes
		
		// get location list and the address for the user
		Location[] locationAry = locationService.allLocations();
		
		Map model = new HashMap();
		model.put("orgList",orgList);
		model.put("roleAry",roleAry);
		model.put("groupList",groupList);
		model.put("locationAry", locationAry);
		
		
		return model;
	}
*/

	
/*	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		log.debug("onSubmit called.");
		
		ChangeAccessCommand newHireCmd =(ChangeAccessCommand)command;
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

/*	private void createRequest(String userId, User usr) {
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
	
*/
/*	private void notifyManager(User usr) {
		
		String managerId = usr.getManagerId();
		User manager = userMgr.getUserWithDependent(managerId, true);
		EmailAddress email = manager.getEmailByName("EMAIL1");
		
		mailService.send(null, "suneet_shah@openiam.com", "User Successfully Created", 
				"A request for User: " + usr.getFirstName() + " " + usr.getLastName() + 
				" has been created through the new hire facility. Please log into the OpenIAM Selfservice application to review the request." +
				" http://localhost:8080/selfservice");
	}
*/


/*	private User getUser(ChangeAccessCommand newHireCmd) {
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

	private Supervisor getSupervisor(ChangeAccessCommand newHireCmd, User usr) {
		Supervisor sup = new Supervisor();
		sup.setEmployee(usr);
		sup.setSupervisor(new User(newHireCmd.getManagedBy()));
		sup.setStartDate(new Date(System.currentTimeMillis()));
		sup.setIsPrimarySuper(new Integer(1));
		sup.setStatus("ACTIVE");
		return sup;
	}	

	private void userAttributes(ChangeAccessCommand newHireCmd, User usr ) {
		Map<String, UserAttribute> attrMap = usr.getUserAttributes(); 
		
		UserAttribute userAttribute = new UserAttribute();
	
		userAttribute.setName("FULL_PART");
		userAttribute.setValue(newHireCmd.getFullPart());

		attrMap.put(userAttribute.getName(),userAttribute);
		  
	}
	
	private Login getLogin(ChangeAccessCommand newHireCmd, User usr, LoginId loginId) {
		Login lg = new Login();

		lg.setUserId(usr.getUserId());

 		lg.setId(loginId);
 		lg.setAuthFailCount(0);
 		lg.setIsLocked(0);
 		lg.setPassword("passwd00");
 		lg.setIsDefault(0);
 		lg.setCreateDate(new Date(System.currentTimeMillis()));
 		lg.setCreatedBy(usr.getUserId());
 		return lg;
	}
	
	private void getEmail(ChangeAccessCommand newHireCmd, User usr) {
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

	private void getAddress(ChangeAccessCommand newHireCmd, User usr) {
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
	
	private void getPhone(ChangeAccessCommand newHireCmd, User usr) {
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
	


/*	public UserDataService getUserMgr() {
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


	public ConnectorService getActiveDirConnector() {
		return activeDirConnector;
	}


	public void setActiveDirConnector(ConnectorService activeDirConnector) {
		this.activeDirConnector = activeDirConnector;
	}
	
 */

}
