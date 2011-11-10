package org.openiam.selfsrvc.pswd;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.base.ExtendController;
import org.openiam.script.ScriptIntegration;
import org.openiam.selfsrvc.helper.ScriptEngineUtil;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.service.ProvisionService;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class PasswordChangeController extends CancellableFormController {


	protected UserDataWebService userMgr;
	protected LoginDataWebService loginManager;
	protected PasswordConfiguration configuration;
	protected ProvisionService provisionService;
    protected String extendController;
	String defaultDomainId;
	String menuGroup;
	
	private static final Log log = LogFactory.getLog(PasswordChangeController.class);

	
	public PasswordChangeController() {
		super();
	}
	



	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		String rMenu = request.getParameter("hideRMenu");
		String header = request.getParameter("hideHeader");
		String cd = request.getParameter("cd"); 
		
		
		if (cd != null) {
			if (cd.equalsIgnoreCase("pswdreset")) {
				request.setAttribute("msg", "Your account has been reset. Please change your password.");
			}
			if (cd.equalsIgnoreCase("pswdexp")) {
				request.setAttribute("msg", "Your password has expired. Please change your password.");
			}
		}
		
		if (rMenu != null && rMenu.length() > 0) {		
			request.setAttribute("hideRMenu","1");
		}
		if (header != null && header.length() > 0) {
			request.setAttribute("hideHeader","1");
		}
		
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
		
        // custom processing to determine which screen to show
        String token =  (String)request.getSession().getAttribute("token");
        String queryString = "&userId=" + userId + "&lg="+ principal + "&tk=" + token;

        PasswordSync passwordSync = new PasswordSync("CHANGE PASSWORD", managedSysId, password,
					principal, userId, secDomainId, "SELFSERVICE", false );

        String login = (String)request.getSession().getAttribute("login");
        String domain = (String)request.getSession().getAttribute("domain");

        passwordSync.setRequestClientIP(request.getRemoteHost());
        passwordSync.setRequestorLogin(login);
        passwordSync.setRequestorDomain(domain);



        ScriptIntegration scriptEngine = ScriptEngineUtil.getScriptEngine();

        System.out.println("ExtenededController value = " + extendController);

        if (extendController != null) {
            ExtendController extCmd = (ExtendController)scriptEngine.instantiateClass(null, extendController);

            // build the object to send to the script
            Map<String,Object> controllerObj = new HashMap<String,Object>();
            controllerObj.put("userId", userId);
            controllerObj.put("passwordSync", passwordSync);

            int retval = extCmd.pre("CHANGE_PASSWORD",controllerObj, pswdChangeCmd);
            System.out.println("Script return value=" + retval);

            if (retval == ExtendController.SUCCESS_STOP) {
                String reDirectUrl = (String)controllerObj.get("REDIRECT_URL");
                if ( reDirectUrl != null && reDirectUrl.length() > 0) {
                    log.info("ReDirectURL from script=" + reDirectUrl);
                    return new ModelAndView(new RedirectView(reDirectUrl + queryString, true));
                }
                ModelAndView mav = new ModelAndView(getSuccessView());
		        mav.addObject("pswdChangeCmd",pswdChangeCmd);
		        return mav;

            }

        }

			
		// sync the password
		System.out.println("-Sync password start");
		

			
		provisionService.setPassword(passwordSync);
			
		log.info("-Sync password complete");
				
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("pswdChangeCmd",pswdChangeCmd);
		
		
		return mav;
	}

    @Override
    protected ModelAndView onCancel(Object command) throws Exception {
       // return super.onCancel(command);    //To change body of overridden methods use File | Settings | File Templates.
        return new ModelAndView(new RedirectView(this.getCancelView(),true));
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


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}

    public String getExtendController() {
        return extendController;
    }

    public void setExtendController(String extendController) {
        this.extendController = extendController;
    }
}
