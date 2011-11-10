package org.openiam.webadmin.user;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.service.ProvisionService;
import org.openiam.webadmin.admin.AppConfiguration;


public class ResetUserPasswordController extends CancellableFormController {


	protected UserDataWebService userMgr;	
	protected String redirectView;
	protected AppConfiguration configuration;
	protected LoginDataWebService loginManager;
	protected NavigatorDataWebService navigationDataService;
	protected ProvisionService provRequestService;
	
	private static final Log log = LogFactory.getLog(ResetUserPasswordController.class);

	
	public ResetUserPasswordController() {
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


	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		
		
		ResetUserPasswordCommand cmd = new ResetUserPasswordCommand();
		
		String personId = request.getParameter("personId");
		String status = request.getParameter("status");
		
		String userId = (String)request.getSession().getAttribute("userId");
		String principal = (String)request.getSession().getAttribute("login");		
		cmd.setPrincipal(principal);
		
		List<Login> principalList = loginManager.getLoginByUser(personId).getPrincipalList(); 
		for ( Login lg : principalList ) {
			if ( lg.getId().getManagedSysId().equalsIgnoreCase( configuration.getDefaultManagedSysId() )) {
				cmd.setDomainId(lg.getId().getDomainId());
				cmd.setPrincipal(lg.getId().getLogin());
			}
		}
		
		String menuGrp = request.getParameter("menugrp");
		// get the level 3 menu
		
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		request.setAttribute("personId", personId);
		
		User usr = userMgr.getUserWithDependent(personId, false).getUser();
		
		cmd.setFirstName(usr.getFirstName());
		cmd.setLastName(usr.getLastName());
		cmd.setUserStatus(status);
		cmd.setUserId(personId);
		
	

		return cmd;
		
	}	
	

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {

        // using a map to ensure that we have a unique list of domains
        Map<String,String> domainMap = new HashMap<String,String>();

        HttpSession session = request.getSession();
		
	
		ResetUserPasswordCommand cmd =(ResetUserPasswordCommand)command;

        String personId = cmd.getUserId();
        if (personId == null) {
            return new ModelAndView(new RedirectView(redirectView, true));
        }


        // need to sync the passwords across all domains that this user belongs to

        List<Login> principalList = loginManager.getLoginByUser(personId).getPrincipalList();
        for ( Login l : principalList) {
           domainMap.put( l.getId().getDomainId(), null );

        }


		// get objects from the command object
		String password = cmd.getPassword();
		
		// update the password in the openiam repository of the primary id
		String managedSysId = configuration.getDefaultManagedSysId();
		//String secDomainId = configuration.getDefaultSecurityDomain();

        Set<String> domainKeys = domainMap.keySet();
       for ( String domain  : domainKeys) {
            PasswordSync pswdSync = new PasswordSync();
            pswdSync.setAction("RESET PASSWORD");
            pswdSync.setManagedSystemId(managedSysId);
            pswdSync.setPassword(password);
            pswdSync.setPrincipal(cmd.getPrincipal());
            pswdSync.setSecurityDomain(domain);

           String login = (String)session.getAttribute("login");

           pswdSync.setRequestClientIP(request.getRemoteHost());
           pswdSync.setRequestorLogin(login);
           pswdSync.setRequestorDomain(domain);

            provRequestService.resetPassword(pswdSync);
        }
		
		
				
		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));
		

	}


	



	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public AppConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(AppConfiguration configuration) {
		this.configuration = configuration;
	}




	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	public ProvisionService getProvRequestService() {
		return provRequestService;
	}


	public void setProvRequestService(ProvisionService provRequestService) {
		this.provRequestService = provRequestService;
	}



	
}
