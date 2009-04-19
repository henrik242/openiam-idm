package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.service.dto.*;

/**
 * Login domain object
 */
public class Login  implements java.io.Serializable {


     private LoginId id;
     private User user;
     private Service service;
     private String password;
     private String pwdEquivalentToken;
     private Date pwdChanged;
     private Date pwdExp;
     private int resetPwd;
     private int isLocked;
     private String status;
     private Date gracePeriod;
     private Date createDate;
     private String createdBy;
     private String currentLoginHost;
     private Integer authFailCount;
     private Date lastAuthAttempt;
     private String canonicalName;
 	private Date lastLogin;
	private Integer isDefault;
	private boolean selected;
     private Set<LoginAttribute> loginAttributes = new HashSet<LoginAttribute>(0);


    public Login() {
    }

	
    public Login(LoginId id, Service service, int resetPwd, int isLocked) {
        this.id = id;
        this.service = service;
        this.resetPwd = resetPwd;
        this.isLocked = isLocked;
    }
    public Login(LoginId id, User users, Service service, String password, String pwdEquivalentToken, Date pwdChanged, Date pwdExp, int resetPwd, int isLocked, String status, Date gracePeriod, Date createDate, String createdBy, String currentLoginHost, Integer authFailCount, Date lastAuthAttempt, Set<LoginAttribute> loginAttributes) {
       this.id = id;
       this.user = users;
       this.service = service;
       this.password = password;
       this.pwdEquivalentToken = pwdEquivalentToken;
       this.pwdChanged = pwdChanged;
       this.pwdExp = pwdExp;
       this.resetPwd = resetPwd;
       this.isLocked = isLocked;
       this.status = status;
       this.gracePeriod = gracePeriod;
       this.createDate = createDate;
       this.createdBy = createdBy;
       this.currentLoginHost = currentLoginHost;
       this.authFailCount = authFailCount;
       this.lastAuthAttempt = lastAuthAttempt;
       this.loginAttributes = loginAttributes;

    }
   
    public LoginId getId() {
        return this.id;
    }
    
    public void setId(LoginId id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPwdEquivalentToken() {
        return this.pwdEquivalentToken;
    }
    
    public void setPwdEquivalentToken(String pwdEquivalentToken) {
        this.pwdEquivalentToken = pwdEquivalentToken;
    }
    public Date getPwdChanged() {
        return this.pwdChanged;
    }
    
    public void setPwdChanged(Date pwdChanged) {
        this.pwdChanged = pwdChanged;
    }
    public Date getPwdExp() {
        return this.pwdExp;
    }
    
    public void setPwdExp(Date pwdExp) {
        this.pwdExp = pwdExp;
    }
    public int getResetPwd() {
        return this.resetPwd;
    }
    
    public void setResetPwd(int resetPwd) {
        this.resetPwd = resetPwd;
    }
    public int getIsLocked() {
        return this.isLocked;
    }
    
    public void setIsLocked(int isLocked) {
        this.isLocked = isLocked;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getGracePeriod() {
        return this.gracePeriod;
    }
    
    public void setGracePeriod(Date gracePeriod) {
        this.gracePeriod = gracePeriod;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getCurrentLoginHost() {
        return this.currentLoginHost;
    }
    
    public void setCurrentLoginHost(String currentLoginHost) {
        this.currentLoginHost = currentLoginHost;
    }
    public Integer getAuthFailCount() {
        return this.authFailCount;
    }
    
    public void setAuthFailCount(Integer authFailCount) {
        this.authFailCount = authFailCount;
    }
    public Date getLastAuthAttempt() {
        return this.lastAuthAttempt;
    }
    
    public void setLastAuthAttempt(Date lastAuthAttempt) {
        this.lastAuthAttempt = lastAuthAttempt;
    }
    public Set<LoginAttribute> getLoginAttributes() {
        return this.loginAttributes;
    }
    
    public void setLoginAttributes(Set<LoginAttribute> loginAttributes) {
        this.loginAttributes = loginAttributes;
    }


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public Integer getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}


	public String getCanonicalName() {
		return canonicalName;
	}


	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}




}


