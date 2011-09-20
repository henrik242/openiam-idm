package org.openiam.webadmin.filter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.service.NavigatorDataService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
//import org.openiam.selfsrvc.util.*;


/**
 * <p>
 * <code>SelfServeAuthFilter</code> <font face="arial"> is a Filter which checks user
 * authentication. If the userId is in session, he/she has been authenticated.
 * If not authenticated, authentication is checked and then userId is set in session
 * If userId is not provided in the request object, control is passed to the
 * the login application and the Filter chain is terminated.
 *
 *
 * </font>
 * </p>
 */

public class SelfServeAuthFilter implements javax.servlet.Filter {

	private FilterConfig filterConfig = null;
	private String serviceId = null;

    private String expirePage = null;
	private String excludePath = null;

	AuthenticationService authService = null;
    LoginDataWebService loginDataWebService = null;
	
	private static final Log log = LogFactory.getLog(SelfServeAuthFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

        		// the expire page is the url of the page to display if the session has expired.
		this.expirePage = filterConfig.getInitParameter("expirePage");
		excludePath = filterConfig.getInitParameter("excludePath");

	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(
		ServletRequest servletRequest,
		ServletResponse servletResponse,
		FilterChain chain)
		throws IOException, ServletException {

        boolean loginPage = false;

		
		log.info("SelfServeAuthFilter:doFilter");

		
		ServletContext context = getFilterConfig().getServletContext();

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

        if (request.getMethod().equalsIgnoreCase("POST")) {
            log.info("Post operation - pass through request");
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        String url = request.getRequestURI();
        log.info("* Requested url=" + url);

        if ( url == null || url.equals("/") || url.endsWith("index.do") || url.endsWith("login.selfserve")
                || isExcludeObject(url) || isPublicUrl(url) || url.endsWith("logout.do") || url.endsWith(".jsp") ) {
			log.info("Pass through request for object");
            chain.doFilter(servletRequest, servletResponse);
            return;
		}
        log.info("Validating url: " + url);

        // validate the token. If the token is not valid then redirect to the login page
        // invalidate the session
		
		// get the application context
		WebApplicationContext webContext = WebApplicationContextUtils.getWebApplicationContext(context);
		authService =  (AuthenticationService)webContext.getBean("authServiceClient");
        loginDataWebService =  (LoginDataWebService)webContext.getBean("loginServiceClient");

        String token = servletRequest.getParameter("tk");
        String userId = servletRequest.getParameter("userId");
        String principal = servletRequest.getParameter("lg");

        String sessionUserId = (String)session.getAttribute("userId");


        if (token == null || token.length() == 0) {
            // token is missing
            log.info("token is null");
            response.sendRedirect(request.getContextPath() + expirePage);
            return;

        }

        if (principal == null || principal.length() == 0) {
            // token is missing
            log.info("principal is null");
            response.sendRedirect(request.getContextPath() + expirePage);
            return;

        }
        // get the user in the token and make sure that user in the token is the same as the one in the session
        if (sessionUserId != null && sessionUserId.length() > 0) {
            log.info("Validating token");
            try {
            String decString = (String)loginDataWebService.decryptPassword(token).getResponseValue();

            StringTokenizer tokenizer = new StringTokenizer(decString,":");
                if (tokenizer.hasMoreTokens()) {
                    String decUserId =  tokenizer.nextToken();
                    if (decUserId == null || !decUserId.equals(sessionUserId)) {
                        log.info("Token validation failed...");
                        session.invalidate();
                        response.sendRedirect(request.getContextPath() + expirePage);
                        return;
                    }
                }
            }catch(Exception e){
                 log.info("Token validation created exception failed" );
                 log.error(e);
                 session.invalidate();
                 response.sendRedirect(request.getContextPath() + expirePage);
                 return;
            }
        }


       // token is valid, but renew it for this request
        Response resp = authService.renewToken(principal,token,AuthenticationConstants.OPENIAM_TOKEN);
        if (resp.getStatus() == ResponseStatus.FAILURE) {
            log.info("Token renewal failed:" + userId + " - " + token );
            session.invalidate();
            response.sendRedirect(request.getContextPath() + expirePage);
            return;

        }
        log.info("Token renewed");

        SSOToken ssoToken = (SSOToken)resp.getResponseValue();
        String newToken = ssoToken.getToken();
        log.info("New token: "  + userId + " - " + newToken);
        session.setAttribute("token",newToken);

		chain.doFilter(servletRequest, servletResponse);


	}

    public boolean isExcludeObject(String url) {
        if (url.endsWith(".jpg")  || url.endsWith(".css") || url.endsWith(".gif")) {
            return true;
        }
        return false;
    }

    public boolean isPublicUrl(String url) {
        if (url.contains(excludePath)) {
            return true;
        }
        return false;
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