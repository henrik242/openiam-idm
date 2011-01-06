package org.openiam.idm.srvc.synch.service;

import java.util.List;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;


public abstract class AbstractTransformScript implements TransformScript {

	protected User user;
	protected List<Login> principalList;
	protected List<Role> userRoleList;
	protected boolean isNewUser = false;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Login> getPrincipalList() {
		return principalList;
	}
	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}
	public List<Role> getUserRoleList() {
		return userRoleList;
	}
	public void setUserRoleList(List<Role> userRoleList) {
		this.userRoleList = userRoleList;
	}
	public boolean isNewUser() {
		return isNewUser;
	}
	public void setNewUser(boolean isNewUser) {
		this.isNewUser = isNewUser;
	}
	
}
