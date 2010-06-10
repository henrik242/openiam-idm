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

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;


/**
 * @author suneet
 *
 */
public class DefaultCallbackHandler implements CallbackHandler {

	private String userName;
	private String password;
	
	private String token;
	private String tokenType;
	
	
	/* (non-Javadoc)
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	public void handle(Callback[] callbackAry) throws IOException,
			UnsupportedCallbackException {
	
		for (int i = 0; i < callbackAry.length; i++) {
		    if (callbackAry[i] instanceof TextOutputCallback) {
		      
		      System.out.println("TextOutputCallback found");

		    } else if (callbackAry[i] instanceof NameCallback) {
		  
		    	System.out.println("NameCallback found");
		    	
		    	System.out.println("Default name" + ((NameCallback)callbackAry[i]).getDefaultName());
		    	System.out.println("Name" + ((NameCallback)callbackAry[i]).getName());
		    	
		    	((NameCallback)callbackAry[i]).setName(userName);
		    	

		    } else if (callbackAry[i] instanceof PasswordCallback) {
		  
		    	System.out.println("PasswordCallback found");
		    	((PasswordCallback)callbackAry[i]).setPassword(password.toCharArray());
		    	
		    	


		    } else {
		    	System.out.println( callbackAry[i].getClass().getName());
		        throw new UnsupportedCallbackException
		         (callbackAry[i], "Unrecognized Callback");
		    }
		  }

		

	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getTokenType() {
		return tokenType;
	}


	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
