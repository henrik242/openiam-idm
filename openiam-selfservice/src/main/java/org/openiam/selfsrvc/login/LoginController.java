package org.openiam.selfsrvc.login;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.selfsrvc.AppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(LoginController.class);
	private String rootMenu;
	
	protected NavigatorDataService navigationDataService;
	protected SecurityDomainDataService secDomainService;
	protected AppConfiguration appConfiguration;
	 
	 String publicLeftMenuGroup;
	 String publicRightMenuGroup1;
	 String publicRightMenuGroup2;
	 String leftMenuGroup;
	 String rightMenuGroup1;
	 String rightMenuGroup2;
	 
	 protected UserDataService userMgr;
	 protected GroupDataService groupManager;
	 protected RoleDataService roleDataService;
	 protected ChallengeResponseService challengeResponse;
	 
	 
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
			List<Menu> menus = navigationDataService.menuGroupByUser(rootMenu, userId, appConfiguration.getDefaultLang());
			session.setAttribute("topLevelMenus", menus);
			
			session.setAttribute("publicLeftMenuGroup",
					navigationDataService.menuGroup(publicLeftMenuGroup, appConfiguration.getDefaultLang()));
			session.setAttribute("publicRightMenuGroup1",
					navigationDataService.menuGroup(publicRightMenuGroup1, appConfiguration.getDefaultLang()));
			session.setAttribute("publicRightMenuGroup2",
					navigationDataService.menuGroup(publicRightMenuGroup2, appConfiguration.getDefaultLang()));
			
		}
		
		return dataMap;
	
	}


	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.debug("onSubmit called.");
		
		System.out.println("onSubmit called.");
		
		LoginCommand loginCmd = (LoginCommand)command;
		String userId = loginCmd.getSubject().getUserId();
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("token", loginCmd.getSubject().getSsoToken().getToken());
		session.setAttribute("login", loginCmd.getSubject().getPrincipal());
		
		// get the menus that the user has permissions too
		List<Menu> menuList = navigationDataService.menuGroupByUser(rootMenu, loginCmd.getSubject().getUserId(), "en");
		session.setAttribute("permissions", menuList);
	
		// user has been authentication - show the private menus
		session.setAttribute("privateLeftMenuGroup",
				navigationDataService.menuGroupSelectedByUser(leftMenuGroup, userId, appConfiguration.getDefaultLang()));
		session.setAttribute("privateRightMenuGroup1",
				navigationDataService.menuGroupSelectedByUser(rightMenuGroup1,userId, appConfiguration.getDefaultLang()));
		session.setAttribute("privateRightMenuGroup2",
				navigationDataService.menuGroupSelectedByUser(rightMenuGroup2,userId, appConfiguration.getDefaultLang()));
		
		// load information to put on to the welcome screen
		User usr = userMgr.getUserWithDependent(userId, false);
		List<Group> groupList = groupManager.getUserInGroups(userId);
		List<Role> roleList =  roleDataService.getUserRoles(userId);
		boolean answerStatus = challengeResponse.userAnserExists(userId);
		
		// load the objects that are needed in the primary application
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("loginCmd", command);
		mav.addObject("subject", loginCmd.getSubject());
		mav.addObject("user", usr);
		mav.addObject("groupList",groupList);
		mav.addObject("roleList", roleList);
		mav.addObject("challenge", answerStatus);
		return mav;
	}

	public NavigatorDataService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(NavigatorDataService navigationDataService) {
		this.navigationDataService = navigationDataService;
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


	public UserDataService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}


	public GroupDataService getGroupManager() {
		return groupManager;
	}


	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}


	public RoleDataService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}


	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}


	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
	}

	

}
