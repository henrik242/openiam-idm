package org.openiam.selfsrvc.hire;


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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.ProvisioningConstants;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.email.MailService;
import org.openiam.selfsrvc.util.ConnectionMgr;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;

public class NewHireController extends SimpleFormController {




	UserDataService userMgr;
	LoginDataService loginManager;
	GroupDataService groupManager;
	RoleDataService roleDataService;
	MailService mailService;
	ConnectionMgr ldapConnectionMgr;
	NavigatorDataService navigatorDataService;
	RequestDataService provRequestService;
	
	String defaultDomainId;
	String menuGroup;
	
	static protected ResourceBundle res = ResourceBundle.getBundle("ldapconf");
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
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		NewHireCommand newHireCommand = new NewHireCommand();
		
		HttpSession session = request.getSession();		
		
		Menu[] menuAry=  navigatorDataService.menuGroup(menuGroup, (String)session.getAttribute("defaultLang"));
		

		if (menuAry == null)
			return newHireCommand;
		
		newHireCommand.setManagedSystems(menuAry);
		
		return newHireCommand;
		
		
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		
		NewHireCommand newHireCmd =(NewHireCommand)command;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get objects from the command object
		
		// - User
		User usr = getUser(newHireCmd);
		usr.setCreatedBy(userId);
		usr.setCreateDate(new Date(System.currentTimeMillis()));
				
		Supervisor sup = this.getSupervisor(newHireCmd, usr);
		LoginId loginId = new LoginId( defaultDomainId,  newHireCmd.getUserPrincipalName(), "0");
		Login login = getLogin(newHireCmd, usr, loginId);
			
		
		// persist objects to DB
		try {
			userMgr.addUserWithDependent(usr, false);
			userMgr.addSupervisor(sup);
			loginManager.addLogin(login);
	        groupManager.addUserToGroup("END_USER_GRP",usr.getUserId());
	        
	        // add to group
	        if (newHireCmd.getGroup() != null && !newHireCmd.getGroup().isEmpty()) {
	        	groupManager.addUserToGroup(newHireCmd.getGroup(), usr.getUserId());
	        }
	
	        // add to role
	        if (newHireCmd.getRole() != null && !newHireCmd.getRole().isEmpty()) {
	        	log.info("Add role: domain=" + defaultDomainId + " role=" + newHireCmd.getRole() + " " + usr.getUserId());
	        	roleDataService.addUserToRole(defaultDomainId, newHireCmd.getRole(), usr.getUserId());
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
        
		}catch(Exception e) {
			e.printStackTrace();
		}
        
        createRequest(userId, usr);
        
        notifyManager(usr);
		
 		

		
		// ADD TO LDAP - SEND TO CONNECTOR
		String uid = usr.getLastName() + "." + usr.getFirstName();
		
		String[] oc = { "top", "person", "organizationalPerson", "inetOrgPerson" };
		String[] organizationalUnits = { "Auditor Controller" };
		Map<String, Object> attributes = new HashMap<String, Object>();			

		attributes.put("cn", usr.getFirstName() + " " + usr.getLastName());
		attributes.put("sn", usr.getLastName());
		attributes.put("description", "LDAP User");
		attributes.put("displayName", usr.getLastName() + "," + usr.getFirstName() );
		attributes.put("givenName",usr.getLastName() + "," + usr.getFirstName());
		attributes.put("mail", loginId.getLogin());
		attributes.put("title","Manager");
		attributes.put("uid",usr.getLastName() + "." + usr.getFirstName());
		
		byte[] bytePassword = "passwd00".getBytes("UTF-16LE");
		
		attributes.put("userPassword",bytePassword);
		attributes.put("telephoneNumber","999.999.9999");
		addLdap(uid, oc, organizationalUnits, attributes, "passwd00");
	 
	
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("newHireCmd",newHireCmd);
		
		
		return mav;
	}

	private void createRequest(String userId, User usr) {
	       ProvisionRequest req = new ProvisionRequest();
			req.setRequestorId(userId);
			req.setStatus("NEW");
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
		
		String managerId = usr.getManagerId();
		User manager = userMgr.getUserWithDependent(managerId, true);
		EmailAddress email = manager.getEmailByName("EMAIL1");
		
		mailService.send(null, "suneet_shah@openiam.com", "User Successfully Created", 
				"A request for User: " + usr.getFirstName() + " " + usr.getLastName() + 
				" has been created through the new hire facility. Please log into the OpenIAM Selfservice application to review the request." +
				" http://localhost:8080/selfservice");
	}



	private User getUser(NewHireCommand newHireCmd) {
		User usr = new User();
		usr.setFirstName(newHireCmd.getFirstName());
		usr.setLastName(newHireCmd.getLastName() );
		usr.setMiddleInit(newHireCmd.getMiddleName());
		usr.setManagerId( newHireCmd.getManagedBy() );
		usr.setDeptCd( newHireCmd.getDepartment() );
		usr.setTitle( newHireCmd.getTitle() );
		usr.setStatus( "APPROVED" );
		usr.setCompanyId( newHireCmd.getCompanyName() );
		usr.setDeptCd( newHireCmd.getDepartment() );
		usr.setNickname( newHireCmd.getNickname() );
		usr.setMaidenName( newHireCmd.getMaiden() ) ;
		usr.setDivision( newHireCmd.getDivision() );
		usr.setJobCode( newHireCmd.getJobCode() );
		usr.setSex( newHireCmd.getGender() );
		usr.setMetadataTypeId("InetOrgPerson");
		usr.setPasswordTheme(newHireCmd.getPasswordTheme());
			
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
		UserAttribute userAttribute2 = new UserAttribute();
		UserAttribute userAttribute3 = new UserAttribute();
		
		UserAttribute userAttribute4 = new UserAttribute();
		UserAttribute userAttribute5 = new UserAttribute();
	
		userAttribute.setName("FULL_PART");
		userAttribute.setValue(newHireCmd.getFullPart());

		userAttribute2.setName("LANG_SPOKEN");
		userAttribute2.setValue(newHireCmd.getLanguagesSpoken());
		  
		userAttribute3.setName("VOL_INTERPRETER");
		userAttribute3.setValue(newHireCmd.getVolunteerInterpreter());
		
		userAttribute4.setName("APPS_UID");
		userAttribute4.setValue(usr.getLastName() + "." + usr.getFirstName()+ "01");

		userAttribute5.setName("APPS_PASSWORD");
		userAttribute5.setValue("passwd00");

		

		attrMap.put(userAttribute.getName(),userAttribute);
		attrMap.put(userAttribute2.getName(),userAttribute2);
		attrMap.put(userAttribute3.getName(),userAttribute3);
		attrMap.put(userAttribute4.getName(),userAttribute4);
		attrMap.put(userAttribute5.getName(),userAttribute5);
		  
	}
	
	private Login getLogin(NewHireCommand newHireCmd, User usr, LoginId loginId) {
		Login lg = new Login();

		lg.setUser(usr);

 		lg.setId(loginId);
 		lg.setAuthFailCount(0);
 		lg.setIsLocked(0);
 		lg.setPassword("passwd00");
 		lg.setIsDefault(0);
 		lg.setCreateDate(new Date(System.currentTimeMillis()));
 		lg.setCreatedBy(usr.getUserId());
 		return lg;
	}
	
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
	
		EmailAddress email3 = new EmailAddress();
		email3.setEmailAddress(newHireCmd.getEmail3());
		email3.setParentId(usr.getUserId());
		email3.setName("EMAIL3");
		email3.setParentType(ContactConstants.PARENT_TYPE_USER);
	
		emailAdrSet.add(email1);
		emailAdrSet.add(email2);
		emailAdrSet.add(email3);	
		
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
	
	// temp ldap hack
	public void addLdap(String uid, String[] objectClass, String[] organizationalUnits, 
 			Map<String,Object> attributes, String pw) {

		Hashtable envDC = new Hashtable();
		String urlLdap = res.getString("hostname");
		String adminName = res.getString("accountid");
		String password = res.getString("accountpassword");
		
		System.out.println("**accountid = " + adminName);

		
	//	ocesam1.ocfl.net
		
		envDC.put(Context.PROVIDER_URL,urlLdap);
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,adminName);  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS,password);


 	
 		String ldapName = getLdapName(uid);

 		BasicAttributes basicAttr = getAttributes(uid, objectClass, organizationalUnits, attributes);
 		try {
 			System.out.println("Creating ldap entry: " + ldapName);
 			
 			LdapContext ctxLdap = new InitialLdapContext(envDC,null);
			System.out.println("Directory context = " + ctxLdap);
 			
 			Context result = ctxLdap.createSubcontext(ldapName, basicAttr);
 			
 			System.out.println("Directory subcontext called = " + result);
 			
 			ctxLdap.close();
 			

 		} catch (NamingException ne) {
 			ne.printStackTrace();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 	}     
 	
	public String getLdapName(String uid) {

		String baseDN = res.getString("baseDN");
		String userIdAttribute = res.getString("userIdAttribute");
		String ldapName = userIdAttribute + "=" + uid + "," + baseDN;

		System.out.println("ldapName=" + ldapName);

		return ldapName;
	}

	private BasicAttributes getAttributes(String uid, String[] objectClass, String[] organizationalUnits, 
			Map<String,Object> attributes) {

		Attribute oc = new BasicAttribute("objectclass");

		if (objectClass == null) {
			oc.add("inetOrgPerson");
			oc.add("organizationalPerson");
			oc.add("person");
			oc.add("top");
		} else {
			int size = objectClass.length;
			for (int i = 0; i < size; i++) {
				oc.add(objectClass[i]);
			}
		}

		BasicAttributes attrs = new BasicAttributes();
		// associate the object class
		attrs.put(oc);

		Attribute ou = new BasicAttribute("ou");
		int size = organizationalUnits.length;
		for (int i = 0; i < size; i++) {
			ou.add(organizationalUnits[i]); 
		}
		attrs.put(ou);

		Iterator iter = attributes.entrySet().iterator();
		for (int i = 0; i < attributes.size(); i++)
		{
		  Map.Entry entry = (Map.Entry) iter.next();
		  String key = (String) entry.getKey();			  
		  Object value = entry.getValue();
		  // skip non string values
		  if (value instanceof String) {
			  attrs.put(key, (String)value);
		  } 
		}
		
		return attrs;
	}


	public ConnectionMgr getLdapConnectionMgr() {
		return ldapConnectionMgr;
	}


	public void setLdapConnectionMgr(ConnectionMgr ldapConnectionMgr) {
		this.ldapConnectionMgr = ldapConnectionMgr;
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

}
