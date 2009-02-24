package org.openiam.selfsrvc;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import diamelle.security.auth.*;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
//import org.openiam.selfsrvc.util.*;


/**
 * <p>
 * <code>SelfServeAuthFilter</code> <font face="arial"> is a Filter which checks user
 * authentication. If the userId is in session, he/she has been authenticated.
 * If not authenticated, authentication is checked and then userId is set in session
 * If userId is not provided in the request object, control is passed to the
 * the login application and the Filter chain is terminated.
 *
 * Filter definition sample in web.xml
 * <code>
 *
 *   <filter>
 *   <filter-name>AuthFilter</filter-name>
 *   <filter-class>diamelle.app.login.AuthFilter</filter-class>
 *    <init-param>
 *      <param-name>appId</param-name>
 *      <param-value>CMS</param-value>
 *    </init-param>
 *    <init-param>
 *      <param-name>rootPermissions</param-name>
 *      <param-value>ROOT</param-value>
 *    </init-param>
 *  </filter>
 *  <filter-mapping>
 *    <filter-name>AuthFilter</filter-name>
 *    <url-pattern>/editor/*</url-pattern>
 *  </filter-mapping>
 * 
 *
 * </code>
 *
 * </font>
 * </p>
 */

public class SelfServeAuthFilter implements javax.servlet.Filter {

	private FilterConfig filterConfig = null;
	private String serviceId = null;
	private String appId = null;
	private String rootMenu = null;

	protected LoginAccess loginAccess = new LoginAccess();
	protected NavigationAccess navAccess = new NavigationAccess();

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

		// the menuId of the app linked to a role in PERMISSIONS table
		this.appId = filterConfig.getInitParameter("appId");

		// the rootMenu for all the appId (applications)
		this.rootMenu = filterConfig.getInitParameter("rootPermissionMenu");
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(
		ServletRequest servletRequest,
		ServletResponse servletResponse,
		FilterChain chain)
		throws IOException, ServletException {

		boolean hasPermission = false;
		boolean isPasswordExp = false;
		String errMsg = null;
		String authUrl = "authenticate.do";
		
		ServletContext context = getFilterConfig().getServletContext();

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		SSOSubject sub = null;

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

		if ((userId == null   || userId.length() == 0) && (paramUserId != null) ) {
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
							sub = loginAccess.authenticate(userId, token);
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
							List menuList = navAccess.getMenuList(rootMenu, langCd);
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
			
			System.out.println("** in do filter...after auth userId= -" + userId);

			if (userId == null) {

				// Build the queryString
				StringBuffer qb = new StringBuffer();

				// if userId and token are passed into query string we want to send them to AuthenticateAction
				if (request.getQueryString() != null)
					qb.append(request.getQueryString());

				if (appId != null) {	
					if (qb.length() > 0)
						qb.append("&");
					qb.append("appId=" + appId);
				}

				if (errMsg != null) {	
					if (qb.length() > 0)
						qb.append("&");
					qb.append("logError=" + errMsg);
				}

				if (qb.length() > 0)
					authUrl = "authenticate.do?" + qb.toString();
				//else
				//	authUrl = "/login/authenticate.do";
				response.sendRedirect(authUrl);
			}
			}
		//}

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