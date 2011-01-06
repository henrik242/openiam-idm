package org.openiam.webadmin.filter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.openiam.selfsrvc.util.*;


/**
 * <p>
 * <code>ACLFilter</code> <font face="arial"> is a Filter which checks 
 * if a user is allowed to access a protected resource.
 * The resource must be the resource table and be associted to a role.
 *
 *
 * </font>
 * </p>
 */

public class ACLFilter implements javax.servlet.Filter {

	private FilterConfig filterConfig = null;
	private String serviceId = null;
	private String appId = null;
	private String rootMenu = null;
	private String extToCheck = null;
	private String authorizedAccessPage = null;
	private String securityTokenType = null;

	ResourceDataService  resDataService = null;
	//AuthenticationService resDataService = null;
	
	//protected LoginAccess loginAccess = new LoginAccess();
	//protected NavigationAccess navAccess = new NavigationAccess();

	private static final Log log = LogFactory.getLog(ACLFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		extToCheck = filterConfig.getInitParameter("extTocheck");
		authorizedAccessPage = filterConfig.getInitParameter("authorizedAccessPage");
		securityTokenType = filterConfig.getInitParameter("securityTokenType");
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(
		ServletRequest servletRequest,
		ServletResponse servletResponse,
		FilterChain chain)
		throws IOException, ServletException {
		
		log.info("ACLFilter:doFilter");

		boolean hasPermission = false;
		String errMsg = null;

		
		ServletContext context = getFilterConfig().getServletContext();

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		// get the application context
		WebApplicationContext webContext = WebApplicationContextUtils.getWebApplicationContext(context);
		resDataService = (ResourceDataService)webContext.getBean("resourceDataService");
		//authService =  (AuthenticationService)webContext.getBean("authenticate");
		
		String url = request.getRequestURI();
		System.out.println("Request URL =" + url);
		
		if (url == null || !url.endsWith(extToCheck)) {
			// do not check the extensions that we dont care about
			chain.doFilter(servletRequest, servletResponse);
		}
		
		String appContext = request.getContextPath();	
		int size = appContext.length();
		String resourceString =  url.substring(size,url.length());
		
		System.out.println("Resource string =" + resourceString);
		
		if (resourceString == null || resourceString.length() == 0) {
			chain.doFilter(servletRequest, servletResponse);
		}

		Resource res = resDataService.getResourceByName(resourceString);
		
		if (res == null) {
			// res is null. Its public resource that is not protected
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
			
		// get the user and token
		// validate the token
		
		

		// if user has been authenticated for this app, then should be in session
		String userId = (String) session.getAttribute("userId");
		// hack to get around the session not being updated problem.
		String paramUserId = servletRequest.getParameter("userId");
		if (paramUserId != null && userId != null) {
			if (!paramUserId.equalsIgnoreCase(userId)) {
				session.invalidate();
				session = request.getSession(true);
				userId = null;
			}
		}
		
		boolean retval = resDataService.isUserAuthorized(userId, res.getResourceId());
		if (!retval) {
			// user does not have access to the requested page. Redirect them to a page that shows a message
			response.sendRedirect(authorizedAccessPage);
			return;
		}
		
		
		chain.doFilter(servletRequest, servletResponse);

/*		if ((userId == null   || userId.length() == 0) && (paramUserId != null) ) {
			userId = servletRequest.getParameter("userId");
			// will be used in the password change action 
			session.setAttribute("userId", userId);
		}
		Boolean approved = (Boolean)session.getAttribute("approved");
		// if a menuList exists in session, then this person has been authenticated
		// on an earlier request.

		String pwdExpFlag = servletRequest.getParameter("pwdexp");
		
		if (pwdExpFlag != null && pwdExpFlag.equals("1")) {
			isPasswordExp = true;
		}
		
		//if (userId != null) {
		if (approved != null ) {
			// other filters if any will follow
			chain.doFilter(servletRequest, servletResponse);

		} else {
			//Check authentication
			//userId = servletRequest.getParameter("userId");

			if (userId != null ) {
				
				// login may not be needed
				String urlEncodedToken = servletRequest.getParameter("token");
				String token = null;

				
				// if the user id and token are passed in on the parameter,
				// then one of the main menu items has been clicked.
				// check to see if these parameters already exist in the session

				try {
					try {
						if (urlEncodedToken != null) {
							// decode token from encoded url
							token = java.net.URLDecoder.decode(urlEncodedToken);
						}
						if (token != null) {
							sub = authService.authenticateByToken(userId, token, AuthenticationConstants.OPENIAM_TOKEN );
							//sub = loginAccess.authenticate(userId, token);
						}

					} catch (Exception e) {
						errMsg = "error.login.badpadding";
						response.sendRedirect(authUrl + "?logError=" + errMsg);
					}

					if (userId != null ) {
							hasPermission = true;
							// user is authenticated for this app
							session.setAttribute("userId", userId);
							// token set in session for other applications
							session.setAttribute("token", token);
							// set appId in session so it is available to application
							session.setAttribute("appId", appId);
							String langCd = null;
							String lang = request.getHeader("Accept-Language");
							if (lang != null) {
								if (lang.length() >= 2)
									langCd = lang.substring(0, 2);
							}

							// Save permissions in session
							// every one in the system needs to have access to self service.
							// To add every on to group / role for service is extra overhead
							// so if you are a valid user in the system, then you are allowed in.
							//List menuList = loginAccess.getPermissions(userId, rootMenu, langCd);
							List<Menu> menuList = navDataService.menuGroup(rootMenu, langCd);
							//List menuList = navAccess.getMenuList(rootMenu, langCd);
							session.setAttribute("permissions", menuList);
							
							session.setAttribute("approved", new Boolean(true));
							// other filters if any will follow
							chain.doFilter(servletRequest, servletResponse);
							return;
					}

				} catch (Exception e) {
					e.printStackTrace();
					errMsg = "error.login.exception";
					response.sendRedirect(authUrl + "?logError=" + errMsg);
					return;
				}

			} else if (isPasswordExp) {
				chain.doFilter(servletRequest, servletResponse);
				return;						
			}
			
			log.info("after auth userId= -" + userId);
			chain.doFilter(servletRequest, servletResponse);
		}
	*/

	}

	// Not in Filter interface, but weblogic is asking for these two methods
	public javax.servlet.FilterConfig getFilterConfig() {
		return filterConfig;
	}

	// in older version instead of init() setFilterConfig is being called
	public void setFilterConfig(javax.servlet.FilterConfig f) {
		this.filterConfig = f;
		try {
			this.init(this.filterConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}