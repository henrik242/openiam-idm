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
package org.openiam.idm.srvc.pswd.rule;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.service.PasswordHistoryDAO;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.util.encrypt.Cryptor;

/**
 * All password validation rules must extend AbstractPasswordRule
 * @author suneet
 *
 */
public abstract class AbstractPasswordRule {

	protected Policy policy;
	protected String password;
	protected String principal;
	protected String managedSysId;
	protected User user;
	protected Login lg;
	protected PasswordHistoryDAO passwordHistoryDao;
	protected Cryptor cryptor;

	public abstract PasswordValidationCode isValid(); 
	
	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getManagedSysId() {
		return managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Login getLg() {
		return lg;
	}

	public void setLg(Login lg) {
		this.lg = lg;
	}

	public PasswordHistoryDAO getPasswordHistoryDao() {
		return passwordHistoryDao;
	}

	public void setPasswordHistoryDao(PasswordHistoryDAO passwordHistoryDao) {
		this.passwordHistoryDao = passwordHistoryDao;
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}


}
