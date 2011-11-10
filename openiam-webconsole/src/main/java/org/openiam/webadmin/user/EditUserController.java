package org.openiam.webadmin.user;


import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.base.ExtendController;
import org.openiam.idm.srvc.msg.dto.NotificationRequest;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.webadmin.util.AuditHelper;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.continfo.ws.AddressResponse;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;

import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.ws.SupervisorListResponse;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import org.openiam.provision.service.AsynchUserProvisionService;
import org.openiam.webadmin.admin.AppConfiguration;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.idm.srvc.msg.dto.NotificationParam;


public class EditUserController extends CancellableFormController {


	protected UserDataWebService userMgr;
	protected LoginDataWebService loginManager;
	protected GroupDataWebService groupManager;
	protected RoleDataWebService roleDataService;
	protected ManagedSystemDataService managedSysService;
	protected OrganizationDataService orgManager;
	protected LocationDataWebService locationDataService;
	
	protected ReferenceDataService refDataService;
	protected AppConfiguration configuration;
	protected NavigatorDataWebService navigationDataService;
	protected String redirectView;
	protected ProvisionService provRequestService;
    protected AsynchUserProvisionService asynchProvService;
    protected Boolean emailUserIdentity;
    protected MailService notificationService;

    protected String scriptEngine;
    protected String extendController;
    protected AuditHelper auditHelper;
	
	
	private static final Log log = LogFactory.getLog(EditUserController.class);

	
	public EditUserController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
		
	}

        @Override
      protected ModelAndView onCancel(Object command) throws Exception {
          return new ModelAndView(new RedirectView(getCancelView(),true));
      }


	
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		String personId = request.getParameter("personId");
		String menuGrp = request.getParameter("menugrp");
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		if (userId != null) {
			List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
			request.setAttribute("menuL3", level3MenuList);	
			request.setAttribute("personId", personId);
		}

		
		
		Map<Object, Object> dataMap = new HashMap<Object, Object>();
		
		// get the organizations
		dataMap.put("orgList", orgManager.getOrganizationList(null,"ACTIVE")); // orgManager.getTopLevelOrganizations() );
		// get the divisions
		dataMap.put("divList", orgManager.allDivisions(null));
		// load the department list
		dataMap.put("deptList",orgManager.allDepartments(null));

		// get the list of job codes
		List<ReferenceData> jobCodeList = refDataService.getRefByGroup("JOB_CODE", "en");
		dataMap.put("jobCodeList",jobCodeList);
		
		// get the list of user type codes
		List<ReferenceData> userTypeList = refDataService.getRefByGroup("USER_TYPE", "en");
		dataMap.put("userTypeList",userTypeList);

		List<ReferenceData> userStatusList = refDataService.getRefByGroup("USER", "en");
		dataMap.put("userStatusList",userStatusList);
		
		// load the location list
		Location[] locationAry = locationDataService.allLocations().getLocationAry();
		dataMap.put("locationAry",locationAry);
		
		log.info("referencedata call complete");
	
		return dataMap;
		

	}
	


	@Override
	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		Address addr = null;
		EmailAddress email1 = null, email2 = null, email3 = null;
		Phone deskPhone = null, cellPhone = null, faxPhone = null, homePhone = null, altCellPhone = null, personalPhone = null  ; 
		
		log.info("formBackingObject method called.");
		
		EditUserCommand editUserCmd = new EditUserCommand();
		
		String personId = request.getParameter("personId");
		String menuGrp = request.getParameter("menugrp");

		log.info("PersonId=" + personId);
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		String domainId = (String)request.getSession().getAttribute("domainid");
		String login = (String)request.getSession().getAttribute("login");
		
		// get the level 3 menu
		
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		request.setAttribute("personId", personId);

		
		UserResponse resp = userMgr.getUserWithDependent(personId, true);
		if (resp.getStatus() == ResponseStatus.FAILURE) {
			// user was not found show an error page
			ModelAndView mav = new ModelAndView("/user/usererror");
			mav.addObject("userId", personId);			
			return mav;
		}
		User usr =resp.getUser();
		
		log.info("User jobcode=" + usr.getJobCode());
		log.info("User classification:" + usr.getClassification());
		log.info("User Employment Type:" + usr.getEmployeeType());
		log.info("User show in search:" + usr.getShowInSearch());
		
		editUserCmd.setUser(usr);
		
		// get supervisor information
		SupervisorListResponse supervisorResp = userMgr.getSupervisors(personId);
		if (supervisorResp.getStatus() == ResponseStatus.SUCCESS) {
			List<Supervisor> supVisorList = supervisorResp.getSupervisorList();
			if (supVisorList != null && !supVisorList.isEmpty()) {
				Supervisor supervisor = supVisorList.get(0);
				editUserCmd.setSupervisorId(supervisor.getSupervisor().getUserId());
				editUserCmd.setSupervisorName(supervisor.getSupervisor().getFirstName() + " " + supervisor.getSupervisor().getLastName());
			}
		}
		// get the alternate contact name:
		if (usr.getAlternateContactId() != null && usr.getAlternateContactId().length() > 0 ) {
			UserResponse altUserResp = userMgr.getUserWithDependent(usr.getAlternateContactId(), false);
			if (altUserResp != null && altUserResp.getStatus() == ResponseStatus.SUCCESS) {
				User altUser = altUserResp.getUser();
				editUserCmd.setAlternateContactName(altUser.getFirstName() + 
						" " + altUser.getLastName());
			}
		}
		

		AddressResponse adrResp = userMgr.getAddressByName(personId, "DEFAULT ADR");
		addr = adrResp.getAddress();
		
		 
		email1 =  userMgr.getEmailAddressByName(personId, "EMAIL1").getEmailAddress();
		email2 =  userMgr.getEmailAddressByName(personId, "EMAIL2").getEmailAddress();
		email3 =  userMgr.getEmailAddressByName(personId, "EMAIL3").getEmailAddress();
		

		deskPhone = userMgr.getPhoneByName(personId, "DESK PHONE").getPhone();
		cellPhone = userMgr.getPhoneByName(personId, "CELL PHONE").getPhone();
		faxPhone = userMgr.getPhoneByName(personId, "FAX").getPhone();
		homePhone = userMgr.getPhoneByName(personId, "HOME PHONE").getPhone();
		altCellPhone = userMgr.getPhoneByName(personId, "ALT CELL PHONE").getPhone();
		personalPhone = userMgr.getPhoneByName(personId, "PERSONAL PHONE").getPhone();

		
		setAddressCommand(usr, addr, editUserCmd);
		setEmailCommand(usr,email1, email2, email3, editUserCmd);
		setPhoneCommand(usr,deskPhone, cellPhone, faxPhone, homePhone, altCellPhone, personalPhone, editUserCmd);

        // log that a person has viewed this record




		return editUserCmd;
		
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		String scriptEngine = "org.openiam.script.GroovyScriptEngineIntegration";
		
		System.out.println("EditUserController: onSubmit called");
		
		EditUserCommand cmd =(EditUserCommand)command;
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");



        // integratin with scripting to allow customization of the admin console

        ScriptIntegration se = null;
		se = ScriptFactory.createModule(scriptEngine);
		ExtendController extCmd = (ExtendController)se.instantiateClass(null, extendController);
        Map<String,Object> controllerObj = new HashMap<String,Object>();


		
		User usr = cmd.getUser();
		log.info("User=" + usr);
		
		ProvisionUser pUser = new ProvisionUser(usr);
        controllerObj.put("user", pUser);
		
		// check what type of button was picked.
		// based on that take action
		log.info("Btn clicked=" + request.getParameter("saveBtn"));

		String btnName =  request.getParameter("saveBtn");
		if (btnName.equalsIgnoreCase("DISABLE")) {

            if (extCmd.pre("DISABLE", controllerObj, null) == ExtendController.SUCCESS_CONTINUE) {
			    provRequestService.disableUser(usr.getUserId(), true, userId);
            }

            return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));
		}
		if (btnName.equalsIgnoreCase("ENABLE")) {
            if (extCmd.pre("ENABLE", controllerObj, null) == ExtendController.SUCCESS_CONTINUE) {
			    provRequestService.disableUser(usr.getUserId(), false, userId);
            }
			return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));			
		}

        String login = (String)session.getAttribute("login");
        String domain = (String)session.getAttribute("domainId");
        pUser.setRequestClientIP(request.getRemoteHost());
        pUser.setRequestorLogin(login);
        pUser.setRequestorDomain(domain);

		
		if (btnName.equalsIgnoreCase("ACTIVE")) {
			pUser.setStatus(UserStatusEnum.ACTIVE);
 		}

		if (btnName.equalsIgnoreCase("DELETE")) {
			pUser.setStatus(UserStatusEnum.DELETED);
			// get the primary identity
			Login lg = loginManager.getPrimaryIdentity(usr.getUserId()).getPrincipal();
			
			// add scripting here

			if (extCmd.pre("DELETE", controllerObj, null) == ExtendController.SUCCESS_CONTINUE) {
			
			    provRequestService.deleteByUserId(pUser, UserStatusEnum.DELETED, userId);
            }
			
		
			// add post scripting here
			
			 return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));
		}
		


		getEmail(cmd, pUser);
		getAddress(cmd, pUser);
		getPhone(cmd, pUser);
	

	
       if (cmd.getSupervisorId() != null && cmd.getSupervisorId().length() > 0) {
        	User supervisorUser = new User(cmd.getSupervisorId());
        	Supervisor sup = new Supervisor();
    		sup.setSupervisor(supervisorUser);
    		sup.setStatus("ACTIVE");
    		sup.setSupervisor(supervisorUser);
    		pUser.setSupervisor(sup);
        }


       if (extCmd.pre("MODIFY", controllerObj, null) == ExtendController.SUCCESS_CONTINUE) {
            asynchProvService.modifyUser(pUser);
       }
		

        return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));

	}


    /**
     * Send an email the user with their login and password
     * @param user
     */
    private void sendUserIdentityEmail(User user) {

        // get this users identity
        Login l = loginManager.getPrimaryIdentity(user.getUserId()).getPrincipal();
        String identity = l.getId().getLogin();
        String password = (String)loginManager.decryptPassword(l.getPassword()).getResponseValue();


        NotificationRequest request = new NotificationRequest();
        request.setUserId(user.getUserId());
        request.setNotificationType("NEW_USER_EMAIL");

        request.getParamList().add(new NotificationParam("IDENTITY", identity));
        request.getParamList().add(new NotificationParam("PSWD", password));


        notificationService.sendNotification(request);


    }

	
	
	private void setAddressCommand(User usr, Address adr, EditUserCommand profileCmd) {
		if (adr != null) {
			profileCmd.setBldgNbr(adr.getBldgNumber());
			profileCmd.setAddress1(adr.getAddress1());
			profileCmd.setCity(adr.getCity());
			profileCmd.setState(adr.getState());
			profileCmd.setPostalCode(adr.getPostalCd());
			profileCmd.setAddressId(adr.getAddressId());
			profileCmd.setAddress2(adr.getAddress2());
		}
	}

	

	private void setPhoneCommand(User usr, Phone deskPhone, Phone cellPhone, Phone faxPhone, Phone homePhone, 
			Phone altCellPhone, Phone personalPhone, EditUserCommand profile) {

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
	private void setEmailCommand(User usr, EmailAddress email1, EmailAddress email2, EmailAddress email3, EditUserCommand profile) {
	
		if (email1 != null) {
			log.info("Email1 = " + email1);
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

	private EmailAddress buildEmail(String emailId, String email, String name) {
		EmailAddress em = new EmailAddress();
		em.setEmailAddress(email);
		if (emailId != null && emailId.length() > 0) {
			em.setEmailId(emailId);
		}
		em.setParentType(ContactConstants.PARENT_TYPE_USER);
		em.setName(name);
		return em;
	}
	
	private void getEmail(EditUserCommand profileCommand, ProvisionUser pUser) {
		
		String email = profileCommand.getEmail1();
		String emailId = profileCommand.getEmail1Id();
		if (email != null && email.length() > 0) {
			EmailAddress em = buildEmail(emailId, email,"EMAIL1");
			log.info("EmailId 1 = " + em.getEmailId());
			pUser.getEmailAddress().add(em);
            pUser.setEmail(email);
		}
		email = profileCommand.getEmail2();
		emailId = profileCommand.getEmail2Id();
		if (email != null && email.length() > 0) {
			EmailAddress em = buildEmail(emailId, email, "EMAIL2");
			log.info("EmailId 2 = " + em.getEmailId());
			pUser.getEmailAddress().add(em);
		}
		email = profileCommand.getEmail3();
		emailId = profileCommand.getEmail3Id();
		if (email != null && email.length() > 0) {
			EmailAddress em = buildEmail(emailId, email, "EMAIL3");
			pUser.getEmailAddress().add(em);
		}		
		
			
	}

	private void getAddress(EditUserCommand profileCommand, ProvisionUser pUser) {
		log.info("getAddress called.");
		
		Address adr = new Address();
		if (profileCommand.getAddressId() != null && profileCommand.getAddressId().length() > 0) {
			log.info("addressId=" + profileCommand.getAddressId());
			adr.setAddressId(profileCommand.getAddressId());
		}
		adr.setAddress1(profileCommand.getUser().getAddress1());
		adr.setAddress2(profileCommand.getUser().getAddress2());
		adr.setBldgNumber(profileCommand.getUser().getBldgNum());
		adr.setCity(profileCommand.getUser().getCity());
		adr.setCountry(profileCommand.getUser().getCountry());
		adr.setState(profileCommand.getUser().getState());
		adr.setStreetDirection(profileCommand.getUser().getStreetDirection());
		adr.setName("DEFAULT ADR");
		adr.setParentId(pUser.getUser().getUserId());
		adr.setParentType(ContactConstants.PARENT_TYPE_USER);
		adr.setPostalCd(profileCommand.getUser().getPostalCd());
		pUser.getAddresses().add(adr);

	
			
	}
	
	
	
	private Phone buildPhone(EditUserCommand profileCmd, ProvisionUser usr, String name,
			String areaCode, String phone) {
		Phone ph = new Phone();
		
		ph.setAreaCd(areaCode);
		ph.setPhoneNbr(phone);
		ph.setDescription(name);
		ph.setParentType(ContactConstants.PARENT_TYPE_USER);
		ph.setName(name);
		ph.setParentId(usr.getUserId());
		
		return ph;
	}
	
	private void getPhone(EditUserCommand profileCmd, ProvisionUser usr) {
	//	Set<Phone> phSet = usr.getPhone();
		
 	// add obbject


		Phone ph = buildPhone(profileCmd, usr, "DESK PHONE", profileCmd.getWorkAreaCode(), profileCmd.getWorkPhone());
		if (profileCmd.getWorkPhoneId() != null && profileCmd.getWorkPhoneId().length() > 0 ) {
			ph.setPhoneId(profileCmd.getWorkPhoneId());
		}
		usr.getPhone().add(ph);

		ph = buildPhone(profileCmd, usr, "CELL PHONE", profileCmd.getCellAreaCode(), profileCmd.getCellPhone());
		log.info("CELL PHONE: " + profileCmd.getCellPhoneId());
		if (profileCmd.getCellPhoneId() != null && profileCmd.getCellPhoneId().length() > 0 ) {
			ph.setPhoneId(profileCmd.getCellPhoneId());
		}
		usr.getPhone().add(ph);

		ph = buildPhone(profileCmd, usr, "FAX", profileCmd.getFaxAreaCode(), profileCmd.getFaxPhone() );
		if (profileCmd.getFaxPhoneId() != null && profileCmd.getFaxPhoneId().length() > 0 ) {
			ph.setPhoneId(profileCmd.getFaxPhoneId());
		}
		usr.getPhone().add(ph);

		ph = buildPhone(profileCmd, usr, "HOME PHONE", profileCmd.getHomePhoneAreaCode(), profileCmd.getHomePhoneNbr() );
		if (profileCmd.getHomePhoneIdr() != null && profileCmd.getHomePhoneIdr().length() > 0 ) {
			ph.setPhoneId(profileCmd.getHomePhoneIdr());
		}
		usr.getPhone().add(ph);
		
		ph = buildPhone(profileCmd, usr, "ALT CELL PHONE", profileCmd.getAltCellAreaCode(), profileCmd.getAltCellNbr() );
		if (profileCmd.getAltCellNbrId() != null && profileCmd.getAltCellNbrId().length() > 0 ) {
			ph.setPhoneId(profileCmd.getAltCellNbrId());
		}
		usr.getPhone().add(ph);

		ph = buildPhone(profileCmd, usr, "PERSONAL PHONE", profileCmd.getPersonalAreaCode(), profileCmd.getPersonalNbr() );
		if (profileCmd.getPersonalNbrId() != null && profileCmd.getPersonalNbrId().length() > 0 ) {
			ph.setPhoneId(profileCmd.getPersonalNbrId());
		}
		usr.getPhone().add(ph);
			
		
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



	


	public ReferenceDataService getRefDataService() {
		return refDataService;
	}


	public void setRefDataService(ReferenceDataService refDataService) {
		this.refDataService = refDataService;
	}


	public AppConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(AppConfiguration configuration) {
		this.configuration = configuration;
	}



	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	public GroupDataWebService getGroupManager() {
		return groupManager;
	}


	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}


	public LocationDataWebService getLocationDataService() {
		return locationDataService;
	}


	public void setLocationDataService(LocationDataWebService locationDataService) {
		this.locationDataService = locationDataService;
	}


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}


	public ProvisionService getProvRequestService() {
		return provRequestService;
	}


	public void setProvRequestService(ProvisionService provRequestService) {
		this.provRequestService = provRequestService;
	}


    public Boolean getEmailUserIdentity() {
        return emailUserIdentity;
    }

    public void setEmailUserIdentity(Boolean emailUserIdentity) {
        this.emailUserIdentity = emailUserIdentity;
    }

    public MailService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(MailService notificationService) {
        this.notificationService = notificationService;
    }

    public String getScriptEngine() {
        return scriptEngine;
    }

    public void setScriptEngine(String scriptEngine) {
        this.scriptEngine = scriptEngine;
    }

    public String getExtendController() {
        return extendController;
    }

    public void setExtendController(String extendController) {
        this.extendController = extendController;
    }

    public AsynchUserProvisionService getAsynchProvService() {
        return asynchProvService;
    }

    public void setAsynchProvService(AsynchUserProvisionService asynchProvService) {
        this.asynchProvService = asynchProvService;
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }
}
