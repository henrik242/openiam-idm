package org.openiam.webadmin.user;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.base.ExtendController;
import org.openiam.idm.srvc.role.dto.UserRole;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;

import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.webadmin.admin.AppConfiguration;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;


/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class NewUserController extends AbstractWizardFormController {

	protected GroupDataWebService groupManager;
	protected RoleDataWebService roleDataService;
	protected NavigatorDataWebService navigatorDataService;
	protected LocationDataWebService locationService;
	
	protected ProvisionService provService;
	protected OrganizationDataService orgManager;

	protected ReferenceDataService refDataService;
	protected ResourceDataService resourceDataService;
	protected SecurityDomainDataService secDomainService;
	protected MetadataWebService metadataService;

	protected AppConfiguration configuration;
	protected String redirectView;
    protected UserStatusEnum newUserStatus;


    protected String scriptEngine;
    protected String extendController;

	
	private static final Log log = LogFactory.getLog(NewUserController.class);

	
	public NewUserController() {
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
		NewUserValidator validator = (NewUserValidator)getValidator();
		switch (page) {
		case 0: 
			validator.validateNewUserType(command, errors);
			break;
		case 1:
			validator.validateNewUserForm(command, errors);
			break;
		}
		
	}
	
	
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException arg3)
			throws Exception {
		
		log.info("In processFinish..");
		
		NewUserCommand newUserCmd =(NewUserCommand)command;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

		User user = newUserCmd.getUser();
		prepareObject(user, userId);
		
		log.info("User=" + user);

        // add the attributes
        List<UserAttribute> attrList =  newUserCmd.getAttributeList();
        if (attrList != null) {
            for ( UserAttribute ua : attrList) {
                ua.setOperation(AttributeOperationEnum.ADD);
                ua.setUserId(null);
                ua.setId(null);
                log.info("Name=" + ua.getName() + "-"+ ua.getValue());
                user.getUserAttributes().put(ua.getName(), ua);
            }
        }
		
        ProvisionUser pUser = new ProvisionUser(user);
        
        log.info("Show in search value=" + pUser.getShowInSearch());
        log.info("Alternate contactid=" + pUser.getAlternateContactId());
         
        // set the supervisor
        if (newUserCmd.getSupervisorId() != null && newUserCmd.getSupervisorId().length() > 0) {
        	User supervisorUser = new User(newUserCmd.getSupervisorId());
        	Supervisor sup = new Supervisor();
    		sup.setSupervisor(supervisorUser);
    		sup.setStatus("ACTIVE");
    		sup.setSupervisor(supervisorUser);
    		pUser.setSupervisor(sup);
        }

           
        
        // add contact information
        pUser.getAddresses().add(getAddress(newUserCmd));
        
        addPhoneToUser(pUser, newUserCmd);
        addEmailToUser(pUser, newUserCmd);
        
      //  pUser.getPhone().add(getPhone(newUserCmd));
      //  pUser.getEmailAddress().add(getEmailAddress(newUserCmd));
        
        // add group
        if (newUserCmd.getGroup() != null && !newUserCmd.getGroup().isEmpty()) {
        	pUser.setMemberOfGroups(getGroupList(newUserCmd, user));
        }
        // add role
        if (newUserCmd.getRole() != null && !newUserCmd.getRole().isEmpty()) {
        	pUser.setMemberOfRoles(getRoleList(newUserCmd, user));
        }
        
        // update the type
        if (user.getMetadataTypeId().equalsIgnoreCase("-")){
        	user.setMetadataTypeId(null);
        }

         try {
            ScriptIntegration se = null;
            se = ScriptFactory.createModule(scriptEngine);
            ExtendController extCmd = (ExtendController)se.instantiateClass(null, extendController);
            Map<String,Object> controllerObj = new HashMap<String,Object>();

            controllerObj.put("user", pUser);

            // provision user
            if (extCmd.pre("ADD", controllerObj, null) == ExtendController.SUCCESS_CONTINUE) {
                ProvisionUserResponse provResp = provService.addUser(pUser);
                if ( provResp.getStatus() == org.openiam.base.ws.ResponseStatus.FAILURE ) {
                    return new ModelAndView("/user/error");
                }
            }
         }catch(Exception e) {
            e.printStackTrace();
         }



        return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));
          
	        
	}




    private Address getAddress(NewUserCommand newUserCmd) {
    	Address adr = new Address();
    	adr.setAddress1(newUserCmd.getUser().getAddress1());
    	adr.setAddress2(newUserCmd.getUser().getAddress2());
    	adr.setAddress3(newUserCmd.getUser().getAddress3());
    	adr.setAddress4(newUserCmd.getUser().getAddress4());
    	adr.setAddress5(newUserCmd.getUser().getAddress5());
    	adr.setBldgNumber(newUserCmd.getUser().getBldgNum());
    	adr.setStreetDirection(newUserCmd.getUser().getStreetDirection());
    	adr.setCity(newUserCmd.getUser().getCity());
    	adr.setState(newUserCmd.getUser().getState());
    	adr.setPostalCd(newUserCmd.getUser().getPostalCd());

    	return adr;
    	
    }

    
	private void addPhoneToUser (ProvisionUser usr, NewUserCommand newHireCmd) {
		Set<Phone> phSet = usr.getPhone();

		Phone deskPhone = new Phone();
		deskPhone.setAreaCd(newHireCmd.getUser().getAreaCd());
		deskPhone.setPhoneNbr(newHireCmd.getUser().getPhoneNbr());
		deskPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		deskPhone.setName("DESK PHONE");
		deskPhone.setParentId(usr.getUserId());

		Phone cellPhone = new Phone();
		cellPhone.setAreaCd(newHireCmd.getCellAreaCode());
		cellPhone.setPhoneNbr(newHireCmd.getCellPhone());
		cellPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		cellPhone.setName("CELL PHONE");
		cellPhone.setParentId(usr.getUserId());

		Phone faxPhone = new Phone();
		faxPhone.setAreaCd(newHireCmd.getFaxAreaCode());
		faxPhone.setPhoneNbr(newHireCmd.getFaxPhone());
		faxPhone.setDescription("FAX");
		faxPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		faxPhone.setName("FAX");
		faxPhone.setParentId(usr.getUserId());

		Phone homePhone = new Phone();
		homePhone.setAreaCd(newHireCmd.getHomePhoneAreaCode());
		homePhone.setPhoneNbr(newHireCmd.getHomePhoneNbr());
		homePhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		homePhone.setName("HOME PHONE");
		homePhone.setParentId(usr.getUserId());
		
		Phone altCellPhone = new Phone();
		altCellPhone.setAreaCd(newHireCmd.getAltCellAreaCode());
		altCellPhone.setPhoneNbr(newHireCmd.getAltCellNbr());
		altCellPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		altCellPhone.setName("ALT CELL PHONE");
		altCellPhone.setParentId(usr.getUserId());
		
		Phone personalPhone = new Phone();
		personalPhone.setAreaCd(newHireCmd.getPersonalAreaCode());
		personalPhone.setPhoneNbr(newHireCmd.getPersonalNbr());
		personalPhone.setDescription("PERSONAL PHONE");
		personalPhone.setParentType(ContactConstants.PARENT_TYPE_USER);
		personalPhone.setName("PERSONAL PHONE");
		personalPhone.setParentId(usr.getUserId());
		
		phSet.add(deskPhone);
		phSet.add(cellPhone);
		phSet.add(faxPhone);
		phSet.add(homePhone);
		phSet.add(altCellPhone);
		phSet.add(personalPhone);

		
		
	}
	
	private void addEmailToUser(ProvisionUser usr, NewUserCommand newHireCmd) {
		Set<EmailAddress> emailAdrSet =  usr.getEmailAddress();
		
		EmailAddress email1 = new EmailAddress();
		email1.setEmailAddress(newHireCmd.getUser().getEmail());
		email1.setParentId(usr.getUserId());
		email1.setName("EMAIL1");
		email1.setParentType(ContactConstants.PARENT_TYPE_USER);
        usr.setEmail(newHireCmd.getUser().getEmail());

		EmailAddress email2 = new EmailAddress();
		email2.setEmailAddress(newHireCmd.getEmail2());
		email2.setParentId(usr.getUserId());
		email2.setName("EMAIL2");
		email2.setParentType(ContactConstants.PARENT_TYPE_USER);

		emailAdrSet.add(email1);
		emailAdrSet.add(email2);

		
	}
	
    
    private EmailAddress getEmailAddress(NewUserCommand newUserCmd) {
    	EmailAddress email = new EmailAddress();
    	email.setName("EMAIL1");
    	email.setEmailAddress(newUserCmd.getUser().getEmail());
    	return email;
    }
    
	
	private List<Group> getGroupList(NewUserCommand newHireCmd, User user) {
		List<Group> groupList = new ArrayList<Group>();
		String groupId = newHireCmd.getGroup();
		Group g = new Group();
		g.setGrpId(groupId);
		groupList.add(g);
		return groupList;
	}
	private List<Role> getRoleList(NewUserCommand newHireCmd, User user) {
		List<Role> roleList = new ArrayList<Role>();
		String cmdRole = newHireCmd.getRole();
		/* parse the role */
		String domainId = null;
		String roleId = null;
		
		StringTokenizer st = new StringTokenizer(cmdRole, "*");
		if (st.hasMoreTokens()) {
			domainId = st.nextToken();
		}
		if (st.hasMoreElements()) {
			roleId = st.nextToken();
		}
		RoleId id = new RoleId(domainId , roleId);
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
		List<Resource> resourceList = resourceDataService.getResourcesByType(this.configuration.getManagedSystemType());
		
		NewUserCommand cmd = new NewUserCommand();
		cmd.setResourceList(resourceList);
		
		return cmd;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request, int page) throws Exception {
		
		log.info("in referenceData");
		
		switch (page) {
		case 0:
			return loadUserTypes(request);
		case 1:
			return loadUserInformation(request);

		}
		return null;
	}

	protected Map loadUserTypes(HttpServletRequest request) {
		log.info("referenceData:loadUserTypes called.");
		
		HttpSession session =  request.getSession();
		
		log.info("User type category =" + configuration.getUserCategoryType());
		
		MetadataType[] typeAry = metadataService.getTypesInCategory(configuration.getUserCategoryType()).getMetadataTypeAry();
		
		
		Map model = new HashMap();
		model.put("metadataTypeAry",typeAry);
	
		return model;
		
	}

	
	protected Map loadUserInformation(HttpServletRequest request) {
		log.info("referenceData:loadUserInformation called.");
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get the organizations
		List<Organization> orgList = orgManager.getOrganizationList(null,"ACTIVE");  //orgManager.getTopLevelOrganizations();
		// get the divisions
		List<Organization> divList = orgManager.allDivisions(null);
		// load the department list
		List<Organization> deptList = orgManager.allDepartments(null);
		

		// get the list of groups that this user belongs to
		List<Group> groupList = groupManager.getAllGroups().getGroupList();	
		// get the list of roles that this user belongs to
		List<Role> roleList = roleDataService.getAllRoles().getRoleList();
		
		
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
		user.setStatus(newUserStatus);
		
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




	public OrganizationDataService getOrgManager() {
		return orgManager;
	}


	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}


	public LocationDataWebService getLocationService() {
		return locationService;
	}


	public void setLocationService(LocationDataWebService locationService) {
		this.locationService = locationService;
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


	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}


	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}


	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}




	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public MetadataWebService getMetadataService() {
		return metadataService;
	}


	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}


	public ProvisionService getProvService() {
		return provService;
	}


	public void setProvService(ProvisionService provService) {
		this.provService = provService;
	}


    public UserStatusEnum getNewUserStatus() {
        return newUserStatus;
    }

    public void setNewUserStatus(UserStatusEnum newUserStatus) {
        this.newUserStatus = newUserStatus;
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
}
