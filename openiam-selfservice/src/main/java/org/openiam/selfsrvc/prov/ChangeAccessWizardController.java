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
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.ProvisioningConstants;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.service.LocationDataService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.provision.service.ProvisionService;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.ExtensibleAttribute;
import org.openiam.spml2.msg.PSOIdentifierType;

/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class ChangeAccessWizardController extends AbstractWizardFormController {


	protected UserDataWebService userMgr;
	protected ResourceDataService resourceDataService;
	protected PasswordConfiguration configuration;
	protected String resourceName;
	
	private RoleDataWebService roleDataService;
	private GroupDataWebService groupManager;
	protected ProvisionService provisionService;
	protected OrganizationDataService orgManager;
	
	 	
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
		User usr = userMgr.getUserWithDependent(userId, false).getUser();
		command.setFirstName(usr.getFirstName());
		command.setLastName( usr.getLastName() );
		command.setPhoneAreaCd(usr.getAreaCd());
		command.setPhoneNbr(usr.getPhoneNbr());		
		command.setTitle(usr.getTitle());
		command.setEmail(usr.getEmail());
		command.setDepartment(usr.getDeptCd());
		command.setRequestorUserId(usr.getUserId());
		// get the list of roles

		List<Resource> resourceList = resourceDataService.getResourcesByType(this.configuration.getManagedSystemType());
		command.setResourceList(resourceList);
		
		return command;
		
		
	}



	protected Map referenceData(HttpServletRequest request, int page) throws Exception {
		switch (page) {
		case 0:
			return null;
		case 1:
			return null;
		case 2:
			return loadAppInformation(request);

		}
		return null;
	}
	
	protected Map loadAppInformation(HttpServletRequest request) {
		
		log.info("loadAppInformation called.");
		
		Map model = new HashMap();
		
		List<Resource> resourceList = resourceDataService.getResourcesByType(this.configuration.getManagedSystemType());
		model.put("resourceList", resourceList);
		
		List<Role> roleList = roleDataService.getAllRoles().getRoleList();
		model.put("roleList", roleList);		
		 
		List<Group> groupList = groupManager.getAllGroups().getGroupList();
		model.put("groupList", groupList);
		
		List<Organization> deptList = orgManager.allDepartments(null);
		model.put("deptList",deptList);
		
		return model;
		
	}


	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}


	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}


	public PasswordConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}


	public GroupDataWebService getGroupManager() {
		return groupManager;
	}


	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}


	public ProvisionService getProvisionService() {
		return provisionService;
	}


	public void setProvisionService(ProvisionService provisionService) {
		this.provisionService = provisionService;
	}


	public OrganizationDataService getOrgManager() {
		return orgManager;
	}


	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}
	
	

	





}
