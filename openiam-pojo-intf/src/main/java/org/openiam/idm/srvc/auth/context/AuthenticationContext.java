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
package org.openiam.idm.srvc.auth.context;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.user.dto.User;

/**
 * AuthenticationContext enables a higher level of flexibility during the authentication
 * process.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationContext")
public interface AuthenticationContext {

	/**
	 * Returns an object to capture the credentials appropriate for a specific type of 
	 * authentication. The type of authentication is specified by the authnType parameter.
	 * @param authnType
	 */
	public abstract Credential createCredentialObject(String authnType);
	public abstract void setCredential(String authnType, Credential cred);
	public abstract Credential getCredential();
	public abstract String getResourceId();
	public abstract void setResourceId(String resourceId);

	public User getUser();
	public void setUser(User user);
	public Login getLogin() ;
	public void setLogin(Login login);
	public String getManagedSysId();
	public void setManagedSysId(String managedSysId);
	
	public String getDomainId(); 
	public void setDomainId(String domainId) ;

	/**
	 * Returns a map of a authentication parameters that are needed by the login module.
	 * @return
	 */
	public abstract Map<String, Object> getAuthParam();

	/**
	 * Sets the parameters that are to be used by the login module.
	 * @param authParam
	 */
	public abstract void setAuthParam(Map<String, Object> authParam);
	
	/**
	 * Add a parameter to the context
	 * @param key
	 * @param value
	 */
	public void addParam(String key, Object value);
	/**
	 * Retrieve a parameter from the context
	 * @param key
	 * @return
	 */
	public Object getParam(String key);

	/**
	 * Login module that is to be used for this authentication attempt. If a value is defined here, it will override 
	 * the login module that is defined at the Security Domain level.
	 * @return
	 */
	public String getLoginModule();

	public void setLoginModule(String loginModule);
}