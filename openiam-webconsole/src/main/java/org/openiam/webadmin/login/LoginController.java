package org.openiam.webadmin.login;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(LoginController.class);
	protected String rootMenu;
	
	protected NavigatorDataWebService navigationDataService;
	
	public LoginController() {
		super();
	}

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.debug("onSubmit called.");
		
		System.out.println("onSubmit called.");
		
		LoginCommand loginCmd = (LoginCommand)command;
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", loginCmd.getSubject().getUserId());
		session.setAttribute("token", loginCmd.getSubject().getSsoToken().getToken());
		session.setAttribute("login", loginCmd.getSubject().getPrincipal());
		session.setAttribute("domainId", loginCmd.getDomainId());
		
		// get the menus that the user has permissions too
		List<Menu> menuList = navigationDataService.menuGroupByUser(rootMenu, loginCmd.getSubject().getUserId(), "en").getMenuList();
		session.setAttribute("permissions", menuList);
		
		
		// load the objects that are needed in the primary application
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("loginCmd", command);
		mav.addObject("subject", loginCmd.getSubject());
		return mav;
	}



	public String getRootMenu() {
		return rootMenu;
	}


	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		String expire = request.getParameter("expire");
		if (expire != null && expire.equalsIgnoreCase("1")) {
			request.setAttribute("message", "Session has expired. Please login in again.");
		}
		return super.referenceData(request);
	}

	

}
