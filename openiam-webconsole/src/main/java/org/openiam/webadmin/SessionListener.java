/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.webadmin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Listener to handle session expiration and gracefully logout.
 * @author suneet
 *
 */
public class SessionListener implements HttpSessionListener, ServletContextListener {


	private ServletContext context = null;
	private IdmAuditLogDataService auditDataService = null;
	
	 public void contextDestroyed(ServletContextEvent contextEvent) {
			
			
	}

	public void contextInitialized(ServletContextEvent event) {
			// TODO Auto-generated method stub
		 this.context = event.getServletContext();
		
	}
		
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("session destroy called.");
		HttpSession session = event.getSession();
		System.out.println("session : userId=" + session.getAttribute("userId"));
		session.invalidate(); // removes everything
		
		// get the spring context
		WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());
		auditDataService = (IdmAuditLogDataService)webCtx.getBean("auditDataService");
		
		System.out.println("Audit service=" + auditDataService);
				
		// log audit event to indicate session expiration
		// clean out the authstate is possible based on session.

	}

}
