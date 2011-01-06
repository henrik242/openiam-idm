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
package org.openiam.idm.srvc.auth.sso;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;


/**
 * Factory to create the LoginModule
 * @author suneet
 *
 */
public class SSOTokenFactory {

	private static final Log log = LogFactory.getLog(SSOTokenFactory.class);
	
	public static SSOTokenModule createModule(String tokenType)  {
		
		if (tokenType.equals(AuthenticationConstants.SAML1_TOKEN)) {
			return new SAML1TokenModule();
		}
		if (tokenType.equals(AuthenticationConstants.SAML2_TOKEN)) {
			return new SAML2TokenModule();
		}else {
			return new DefaultTokenModule();
		}
		
		
		
	}


}
