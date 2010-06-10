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
package org.openiam.idm.srvc.policy.dto;

/**
 * Constants used by the policy services and applications
 * @author suneet
 *
 */
public class PolicyConstants {
	static final public String PASSWORD_POLICY = "100";
	static final public String ACCESS_POLICY = "101";
	static final public String AUDIT_POLICY = "102";
	static final public String AUTHENTICATION_POLICY = "103";
	static final public String ATTRIBUTE_POLICY = "104";
	static final public String FORM_POLICY = "105";
	
	static final public int STATUS_ACTIVE = 1;
	static final public int STATUS_INACTIVE = 0;
	
	/* POLICY ASSOCIATION LEVEL */
	static final public String LEVEL_USER_CLASSIFICATION = "CLASSIFICATION";
	static final public String LEVEL_USER_TYPE = "TYPE";
	static final public String LEVEL_RESOURCE = "RESOURCE";
	static final public String LEVEL_ORGANIZATION = "ORGANIZATION";
	static final public String LEVEL_DOMAIN = "SECURITY_DOMAIN";
	static final public String LEVEL_GLOBAL = "GLOBAL";
	
	
}
