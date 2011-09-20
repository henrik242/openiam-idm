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
package org.openiam.idm.srvc.auth.dto;

/**
 * Class used to specify which Login module to use for authentication the type of login module that we have.
 * @author suneet
 *
 */
public class LoginModuleSelector {
	
	static public int MODULE_TYPE_LOGIN_MODULE = 1;
	static public int MODULE_TYPE_CONNECTOR = 2;
	
	
	protected int moduleType = MODULE_TYPE_LOGIN_MODULE;	
	protected String moduleName;
	
	public LoginModuleSelector() {
		
	}
	public LoginModuleSelector(String moduleName, int moduleType) {
		super();
		this.moduleName = moduleName;
		this.moduleType = moduleType;
	}
	
	public int getModuleType() {
		return moduleType;
	}
	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	
}
