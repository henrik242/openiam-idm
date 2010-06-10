/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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
package org.openiam.am.jaas;

import javax.security.auth.login.*;


/**
 * Test the login context
 * @author suneet
 *
 */
public class TestLoginContext {
	LoginContext lc = null;

	public void login() {
	      try {
	          lc = new LoginContext("Sample", 
	                          new DefaultCallbackHandler());
	          lc.login();
	      } catch (LoginException le) {
	          System.err.println("Cannot create LoginContext. "
	              + le.getMessage());
	          System.exit(-1);
	      } catch (SecurityException se) {
	          System.err.println("Cannot create LoginContext. "
	              + se.getMessage());
	          System.exit(-1);
	      } 		
	}
	
	public static void main(String[] args) {

		System.out.println("Test login context");
		TestLoginContext ctx = new TestLoginContext();
		ctx.login();
	
	}	
}
