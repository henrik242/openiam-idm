package org.openiam.selfsrvc.login;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.base.ExtendController;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.pswd.ws.PasswordWebService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.script.ScriptIntegration;
import org.openiam.selfsrvc.AppConfiguration;
import org.openiam.selfsrvc.helper.ScriptEngineUtil;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;
import org.openiam.selfsrvc.usradmin.DelegationFilterHelper;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(LoginController.class);
	private String rootMenu;
	
	protected NavigatorDataWebService navigationDataService;
	protected SecurityDomainDataService secDomainService;
	protected AppConfiguration appConfiguration;
	protected PasswordConfiguration configuration;
	protected PasswordWebService passwordService;
    protected String extendController;
	 
	 String publicLeftMenuGroup;
	 String publicRightMenuGroup1;
	 String publicRightMenuGroup2;
	 String publicRightMenuGroup3;
	 String leftMenuGroup;
	 String rightMenuGroup1;
	 String rightMenuGroup2;
	 String rightMenuGroup3;
	 
	 protected UserDataWebService userMgr;
	 protected GroupDataWebService groupManager;
	 protected RoleDataWebService roleDataService;
	 protected ChallengeResponseService challengeResponse;
	 protected LoginDataWebService loginManager;
     protected RequestWebService provRequestService;
     protected OrganizationDataService orgManager;
	 
	 
	public LoginController() {
		super();
	}

	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		Map<Object, Object> dataMap = new HashMap<Object, Object>();

		HttpSession session = request.getSession();
			
		session.setAttribute("welcomePageUrl", request.getContextPath() + appConfiguration.getWelcomePageUrl() );
		session.setAttribute("logoUrl", appConfiguration.getLogoUrl());
		session.setAttribute("title", appConfiguration.getTitle());
		session.setAttribute("defaultLang", appConfiguration.getDefaultLang());
		
		
		
		
		String userId = (String)session.getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			// user has not been authenticated - show the public menus
			List<Menu> menus = navigationDataService.menuGroupByUser(rootMenu, userId, appConfiguration.getDefaultLang()).getMenuList();
			session.setAttribute("topLevelMenus", menus);
			
			session.setAttribute("publicLeftMenuGroup",
					navigationDataService.menuGroup(publicLeftMenuGroup, appConfiguration.getDefaultLang()).getMenuList());
			session.setAttribute("publicRightMenuGroup1",
					navigationDataService.menuGroup(publicRightMenuGroup1, appConfiguration.getDefaultLang()).getMenuList());
			session.setAttribute("publicRightMenuGroup2",
					navigationDataService.menuGroup(publicRightMenuGroup2, appConfiguration.getDefaultLang()).getMenuList());
			session.setAttribute("publicRightMenuGroup3",
					navigationDataService.menuGroup(publicRightMenuGroup3, appConfiguration.getDefaultLang()).getMenuList());
			
		}
		
		return dataMap;
	
	}


	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.debug("onSubmit called.");
		
		System.out.println("onSubmit called.");

        List<String> roleIdList = new ArrayList<String>();
        List<Role> roleList  = null;
		
		LoginCommand loginCmd = (LoginCommand)command;
		String userId = loginCmd.getSubject().getUserId();
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
        if (loginCmd.getSubject().getSsoToken().getToken() != null &&
            !loginCmd.getSubject().getSsoToken().getToken().contains("saml")){
		    session.setAttribute("token", loginCmd.getSubject().getSsoToken().getToken());
        }

		
		// get the menus that the user has permissions too
		List<Menu> menuList = navigationDataService.menuGroupByUser(rootMenu, loginCmd.getSubject().getUserId(), "en").getMenuList();
		session.setAttribute("permissions", menuList);
	
		// user has been authentication - show the private menus
		session.setAttribute("privateLeftMenuGroup",
				navigationDataService.menuGroupSelectedByUser(leftMenuGroup, userId, appConfiguration.getDefaultLang()).getMenuList());
		session.setAttribute("privateRightMenuGroup1",
				navigationDataService.menuGroupSelectedByUser(rightMenuGroup1,userId, appConfiguration.getDefaultLang()).getMenuList());
		session.setAttribute("privateRightMenuGroup2",
				navigationDataService.menuGroupSelectedByUser(rightMenuGroup2,userId, appConfiguration.getDefaultLang()).getMenuList());

		session.setAttribute("privateRightMenuGroup3",
				navigationDataService.menuGroupSelectedByUser(rightMenuGroup3,userId, appConfiguration.getDefaultLang()).getMenuList());
		
		// load information to put on to the welcome screen
		User usr = userMgr.getUserWithDependent(userId, true).getUser();

		List<Group> groupList = groupManager.getUserInGroups(userId).getGroupList();
        RoleListResponse roleResponse =  roleDataService.getUserRolesAsFlatList(userId);

        if (roleResponse != null && roleResponse.getStatus() == ResponseStatus.SUCCESS) {
            roleList =  roleResponse.getRoleList();
            for (Role r : roleList) {
                roleIdList.add(r.getId().getRoleId());

            }

        }

		boolean answerStatus = challengeResponse.userAnserExists(userId);
        Login lg = loginManager.getPrimaryIdentity(userId).getPrincipal();



        String queryString = "&userId=" + userId + "&lg="+loginCmd.getPrincipal() + "&tk=" + loginCmd.getSubject().getSsoToken().getToken();
		
		String principal = loginCmd.getPrincipal();

        session.setAttribute("userObj",usr);
        session.setAttribute("domain", lg.getId().getDomainId());


	    Login principalLg = loginManager.getPrimaryIdentity(loginCmd.getSubject().getUserId()).getPrincipal();
        session.setAttribute("login", principalLg.getId().getLogin());

        log.info("Identity =" + principalLg.getId().getLogin());


		// custom processing to determine which screen to show
        ScriptIntegration scriptEngine = ScriptEngineUtil.getScriptEngine();
        if (extendController != null) {
            ExtendController extCmd = (ExtendController)scriptEngine.instantiateClass(null, extendController);

            // build the object to send to the script
            Map<String,Object> controllerObj = new HashMap<String,Object>();
            controllerObj.put("userId", usr.getUserId());
            controllerObj.put("userObj", usr);

            int retval = extCmd.pre("LOGIN",controllerObj, loginCmd);
            log.info("Script return value=" + retval);

            if (retval == 2) {
                String reDirectUrl = (String)controllerObj.get("REDIRECT_URL");
                log.info("ReDirectURL from script=" + reDirectUrl);
                return new ModelAndView(new RedirectView(reDirectUrl + queryString, true));

            }

        }



		if (loginCmd.getSubject().getResultCode() > 1) {
			if ( loginCmd.getSubject().getResultCode() == AuthenticationConstants.RESULT_SUCCESS_PASSWORD_EXP) {
				 return new ModelAndView(new RedirectView("/passwordChange.selfserve?hideRMenu=1&cd=pswdexp" + queryString, true));
			}
		}




		
		/* If the password was reset and the policy says change the password after a reset, then force a password reset */
		Policy policy = passwordService.getPasswordPolicy(configuration.getDefaultSecurityDomain(), principalLg.getId().getLogin(),
				configuration.getDefaultManagedSysId()).getPolicy();
		PolicyAttribute attr = policy.getAttribute("CHNG_PSWD_ON_RESET");
		boolean changePswdOnReset = false;
		if (attr.getValue1() != null && attr.getValue1().equalsIgnoreCase("1")) {
			if ( principalLg.getResetPassword() == 1) {
				changePswdOnReset = true;
			}
		}
		
		if (changePswdOnReset) {
			// reset the password
			 return new ModelAndView(new RedirectView("/passwordChange.selfserve?hideRMenu=1&cd=pswdreset"+ queryString, true));
		}

         SearchRequest search = buildSearch( userId, roleIdList, usr);
        List<ProvisionRequest> reqList = provRequestService.search(search).getReqList();

      // supervisor
        List<Supervisor> supVisorList = userMgr.getSupervisors(userId).getSupervisorList();
        String supervisorName =  null;
		if (supVisorList != null && !supVisorList.isEmpty()) {
			Supervisor supervisor = supVisorList.get(0);
            supervisorName = supervisor.getSupervisor().getFirstName() + " " + supervisor.getSupervisor().getLastName();

		} else {
            supervisorName = "NA";
        }

        // user dept
        String deptCd = usr.getDeptCd();
        String deptName = "NA";
        if (deptCd != null && deptCd.length() >0) {
             deptName = orgManager.getOrganization(deptCd).getOrganizationName();

        }

		
		// load the objects that are needed in the primary application
		
		ModelAndView mav = new ModelAndView(getSuccessView());

		mav.addObject("loginCmd", command);
		mav.addObject("subject", loginCmd.getSubject());
		mav.addObject("user", usr);
		mav.addObject("groupList",groupList);
		mav.addObject("roleList", roleList);
		mav.addObject("challenge", answerStatus);
        mav.addObject("primaryIdentity", lg);
        mav.addObject("daysToExp", loginCmd.getSubject().getDaysToPwdExp());
        mav.addObject("supervisor", supervisorName);
        mav.addObject("dept", deptName);

        if (reqList != null && reqList.size() > 0) {
            mav.addObject("pendingReq", reqList.size());
        }

		return mav;
	}



  private SearchRequest buildSearch( String userId, List<String> roleIdList, User usr) {
		SearchRequest search = new SearchRequest();
		search.setStatus("PENDING");
		search.setApproverId(userId);

        search.setRoleIdList(roleIdList);


        if (usr.getDelAdmin() != null &&  usr.getDelAdmin().intValue() == 1) {
            Map<String, UserAttribute> attrMap = usr.getUserAttributes();
            List<String> deptFilterList = null;
            List<String> orgFilterList = null;
            List<String> divFilterList = null;


            orgFilterList = DelegationFilterHelper.getOrgIdFilterFromString(attrMap);

            System.out.println("Org Filterlist =" + orgFilterList);

            if (orgFilterList != null && orgFilterList.size() > 0) {
                search.setRequestForOrgList(orgFilterList);
            }

         }

		return search;
	}

	public String getRootMenu() {
		return rootMenu;
	}


	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}


	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}


	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}


	public void setAppConfiguration(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}


	public String getPublicLeftMenuGroup() {
		return publicLeftMenuGroup;
	}


	public void setPublicLeftMenuGroup(String publicLeftMenuGroup) {
		this.publicLeftMenuGroup = publicLeftMenuGroup;
	}


	public String getPublicRightMenuGroup1() {
		return publicRightMenuGroup1;
	}


	public void setPublicRightMenuGroup1(String publicRightMenuGroup1) {
		this.publicRightMenuGroup1 = publicRightMenuGroup1;
	}


	public String getPublicRightMenuGroup2() {
		return publicRightMenuGroup2;
	}


	public void setPublicRightMenuGroup2(String publicRightMenuGroup2) {
		this.publicRightMenuGroup2 = publicRightMenuGroup2;
	}


	public String getLeftMenuGroup() {
		return leftMenuGroup;
	}


	public void setLeftMenuGroup(String leftMenuGroup) {
		this.leftMenuGroup = leftMenuGroup;
	}


	public String getRightMenuGroup1() {
		return rightMenuGroup1;
	}


	public void setRightMenuGroup1(String rightMenuGroup1) {
		this.rightMenuGroup1 = rightMenuGroup1;
	}


	public String getRightMenuGroup2() {
		return rightMenuGroup2;
	}


	public void setRightMenuGroup2(String rightMenuGroup2) {
		this.rightMenuGroup2 = rightMenuGroup2;
	}



	

	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}


	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
	}


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
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


	public PasswordConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}


	public PasswordWebService getPasswordService() {
		return passwordService;
	}


	public void setPasswordService(PasswordWebService passwordService) {
		this.passwordService = passwordService;
	}


	public String getPublicRightMenuGroup3() {
		return publicRightMenuGroup3;
	}


	public void setPublicRightMenuGroup3(String publicRightMenuGroup3) {
		this.publicRightMenuGroup3 = publicRightMenuGroup3;
	}


	public String getRightMenuGroup3() {
		return rightMenuGroup3;
	}


	public void setRightMenuGroup3(String rightMenuGroup3) {
		this.rightMenuGroup3 = rightMenuGroup3;
	}




    public RequestWebService getProvRequestService() {
        return provRequestService;
    }

    public void setProvRequestService(RequestWebService provRequestService) {
        this.provRequestService = provRequestService;
    }

    public OrganizationDataService getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(OrganizationDataService orgManager) {
        this.orgManager = orgManager;
    }

    public String getExtendController() {
        return extendController;
    }

    public void setExtendController(String extendController) {
        this.extendController = extendController;
    }
}
