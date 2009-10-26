package org.openiam.selfsrvc.pswd;


import java.util.Date;
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
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.service.ProvisionService;

/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class PasswordChangeController extends SimpleFormController {


	protected UserDataService userMgr;
	protected LoginDataService loginManager;
	protected PasswordConfiguration configuration;
	protected ProvisionService provisionService;
		
	String defaultDomainId;
	String menuGroup;
	
	private static final Log log = LogFactory.getLog(PasswordChangeController.class);

	
	public PasswordChangeController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	

	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		PasswordChangeCommand pswdChangeCmd = new PasswordChangeCommand();	
				
		HttpSession session =  request.getSession();
		String principal = (String)session.getAttribute("login");
		
		pswdChangeCmd.setPrincipal(principal);
		
		return pswdChangeCmd;
	}

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		log.info("onSubmit called.");
		
		String userId = (String)request.getSession().getAttribute("userId");
		
		PasswordChangeCommand pswdChangeCmd =(PasswordChangeCommand)command;
		
		// get objects from the command object
		String principal = pswdChangeCmd.getPrincipal();
		String password = pswdChangeCmd.getPassword();
		
		// update the password in the openiam repository of the primary id
		String managedSysId = configuration.getDefaultManagedSysId();
		String secDomainId = configuration.getDefaultSecurityDomain();
		
		// validate password against policy
			
		// sync the password
		log.info("-Sync password start");
		
		PasswordSync passwordSync = new PasswordSync("CHANGE PASSWORD", managedSysId, password, 
					principal, userId, secDomainId, "SELFSERVICE", false );
			
		provisionService.setPassword(passwordSync);
			
		log.info("-Sync password complete");
				
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("pswdChangeCmd",pswdChangeCmd);
		
		
		return mav;
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


	public PasswordConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}


	public ProvisionService getProvisionService() {
		return provisionService;
	}


	public void setProvisionService(ProvisionService provisionService) {
		this.provisionService = provisionService;
	}


}
