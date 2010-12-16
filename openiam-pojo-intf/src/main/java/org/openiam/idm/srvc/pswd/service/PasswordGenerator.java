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
package org.openiam.idm.srvc.pswd.service;

import java.util.Random;
import java.util.ResourceBundle;

/**
 * Generates a random string that is used to create a password.
 * @author suneet
 *
 */
public class PasswordGenerator {
	
	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	
	private static final String charset = res.getString("PSWD_GEN_CHARSET");
	

	 public static String generatePassword(int length) {
	        Random rand = new Random(System.currentTimeMillis());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < length; i++) {
	            int pos = rand.nextInt(charset.length());
	            sb.append(charset.charAt(pos));
	        }
	        return sb.toString();
	    }

}
