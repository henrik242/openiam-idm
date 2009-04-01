package org.openiam.webadmin.filter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.openiam.webadmin.busdel.base.LoginAccess;

import diamelle.security.auth.*;

/**
 * <p>
 * <code>AuthFilter</code> <font face="arial"> is a Filter which checks user
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

public class AuthFilter implements javax.servlet.Filter {

	private FilterConfig filterConfig = null;
	private String serviceId = null;
	private String appId = null;
	private String rootMenu = null;

	protected LoginAccess loginAccess = new LoginAccess();

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
		String errMsg = null;
		String authUrl = "/login/authenticate.do";

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

		if (userId != null) {
			// other filters if any will follow
			chain.doFilter(servletRequest, servletResponse);

		} else {

			//Check authentication
			userId = servletRequest.getParameter("userId");

			if (userId != null) {

				// login may not be needed
				String urlEncodedToken = servletRequest.getParameter("token");
				String token = null;
				// if the user id and token are passed in on the parameter,
				// then one of the main menu items has been clicked.
				// check to see if these parameters already exist in the session

				try {
					try {
						if (urlEncodedToken != null)
							token = java.net.URLDecoder.decode(urlEncodedToken);

						System.out.println("token decoded from urlEncodedToken: " + token);
						sub = loginAccess.authenticate(userId, token);

					} catch (Exception e) {
						errMsg = "error.login.badpadding";
						response.sendRedirect(authUrl + "?logError=" + errMsg);
					}

					if (sub != null) {
						if (loginAccess.hasPermission(userId, appId)) {

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
								if (lang.length() > 3)
									langCd = lang.substring(0, 2);
							}
						
							// Save permissions in session
							List menuList =
								loginAccess.getPermissions(userId, rootMenu, langCd);
							
							session.setAttribute("permissions", menuList);
							
							// other filters if any will follow
							chain.doFilter(servletRequest, servletResponse);
							return;
							
						} else {
							errMsg = "error.login.nopermission";
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					errMsg = "error.login.exception";
					response.sendRedirect(authUrl + "?logError=" + errMsg);
				}

			} 

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
					authUrl = "/login/authenticate.do?" + qb.toString();
				//else
				//	authUrl = "/login/authenticate.do";

				response.sendRedirect(authUrl);
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