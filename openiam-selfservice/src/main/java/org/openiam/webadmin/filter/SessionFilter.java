package org.openiam.webadmin.filter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import diamelle.security.auth.*;

/**
 * <p>
 * <code>SessionFilter</code> <font face="arial"> is a Filter that session for a users session expiring. 
 * If it has expired, then it gracefully displays a session expiration message.
 *
 * </font>
 * </p>
 */

public class SessionFilter implements javax.servlet.Filter {

	private FilterConfig filterConfig = null;
	private String expirePage = null;


	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

		// the expire page is the url of the page to display if the session has expired.
		this.expirePage = filterConfig.getInitParameter("expirePage");

	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(
		ServletRequest servletRequest,
		ServletResponse servletResponse,
		FilterChain chain)
		throws IOException, ServletException {

System.out.println("SessionFilter: doFilter() called");

		ServletContext context = getFilterConfig().getServletContext();

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		if (session == null) {
			System.out.println("SessionFilter: doFilter(): session is null. Redirect to login");
			response.sendRedirect(expirePage);
			return;
		}
		chain.doFilter(servletRequest, servletResponse);
		return;
		


	}

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