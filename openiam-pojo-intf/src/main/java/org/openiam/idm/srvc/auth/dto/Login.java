package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.exception.AuthenticationException;

/**
 * Login domain object
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Login", propOrder = {
    "id",
    "userId",
    "password",
    "pwdEquivalentToken", 
    "pwdChanged", 
    "pwdExp", 
    "firstTimeLogin", 
    "resetPassword",
    "isLocked", 
    "status", 
    "gracePeriod", 
    "createDate", 
    "createdBy", 
    "currentLoginHost", 
    "authFailCount", 
    "lastAuthAttempt", 
    "canonicalName", 
	"lastLogin", 
	"isDefault", 
    "selected", 
    "loginAttributes",
    "passwordChangeCount",
    "operation",
    "origPrincipalName"
})
@XmlSeeAlso({
    Subject.class,
    SSOToken.class
})
public class Login  implements java.io.Serializable,  Cloneable{





	/**
	 * 
	 */
	private static final long serialVersionUID = -1972779170001619759L;

	protected AttributeOperationEnum operation;
	
     protected LoginId id;
     protected String userId;
     protected String password;
     protected String pwdEquivalentToken;
     @XmlSchemaType(name = "dateTime")
     protected Date pwdChanged;
     @XmlSchemaType(name = "dateTime")
     protected Date pwdExp;
     protected int firstTimeLogin;
     protected int resetPassword;
     protected int isLocked;
     protected String status;
     @XmlSchemaType(name = "dateTime")
     protected Date gracePeriod;
     @XmlSchemaType(name = "dateTime")
     protected Date createDate;
     protected String createdBy;
     protected String currentLoginHost;
     protected Integer authFailCount = new Integer(0);
     @XmlSchemaType(name = "dateTime")
     protected Date lastAuthAttempt;
     protected String canonicalName;
     @XmlSchemaType(name = "dateTime")
 	 protected Date lastLogin;
	 protected Integer isDefault = new Integer(0);
	 protected Integer passwordChangeCount = new Integer(0);
	 protected boolean selected;
     protected Set<LoginAttribute> loginAttributes = new HashSet<LoginAttribute>(0);
     protected String origPrincipalName;


    public Login() {
    }

	
    public Login(LoginId id, int resetPwd, int isLocked) {
        this.id = id;
        this.firstTimeLogin = resetPwd;
        this.isLocked = isLocked;
    }
    public Login(LoginId id, String userId, String password, String pwdEquivalentToken, Date pwdChanged, Date pwdExp, int resetPwd, int isLocked, String status, Date gracePeriod, Date createDate, String createdBy, String currentLoginHost, Integer authFailCount, Date lastAuthAttempt, Set<LoginAttribute> loginAttributes) {
       this.id = id;
       this.userId = userId;
       this.password = password;
       this.pwdEquivalentToken = pwdEquivalentToken;
       this.pwdChanged = pwdChanged;
       this.pwdExp = pwdExp;
       this.firstTimeLogin = resetPwd;
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
   
	@Override
	public Object clone()  {
		Login l = new Login();
		LoginId lgId = new LoginId(id.getDomainId(), id.getLogin(), id.getManagedSysId());
		l.setId(lgId);
		
		l.setAuthFailCount(authFailCount);
		l.setCanonicalName(canonicalName);
		l.setCreateDate(createDate);
		l.setCreatedBy(createdBy);
		l.setCurrentLoginHost(currentLoginHost);
		l.setFirstTimeLogin(firstTimeLogin);
		l.setGracePeriod(gracePeriod);
		l.setIsDefault(isDefault);
		l.setLastAuthAttempt(lastAuthAttempt);
		l.setLastLogin(lastLogin);
		l.setLoginAttributes(loginAttributes);
		l.setOperation(operation);
		l.setPassword(password);
		l.setPasswordChangeCount(passwordChangeCount);
		l.setPwdChanged(pwdChanged);
		l.setPwdExp(pwdExp);
		l.setResetPassword(resetPassword);
		l.setSelected(selected);
		l.setStatus(status);
		l.setUserId(userId);
		return l;
		
	}
    
    
    public LoginId getId() {
        return this.id;
    }
    
    public void setId(LoginId id) {
        this.id = id;
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
    	if (gracePeriod == null) {
    		// there is no grace period
    		return this.pwdExp;
    	}
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

	public String toString() {
		String str = "Login id=" + id + 
					 " status=" + status + 
					 " createdBy=" + createdBy + 
					 " canonicalName=" + canonicalName;
		return str;

	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getFirstTimeLogin() {
		return firstTimeLogin;
	}


	public void setFirstTimeLogin(int firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}


	public AttributeOperationEnum getOperation() {
		return operation;
	}


	public void setOperation(AttributeOperationEnum operation) {
		this.operation = operation;
	}


	/**
	 * Tracks how many times the password has been changed.
	 * @return
	 */
	public Integer getPasswordChangeCount() {
		return passwordChangeCount;
	}


	public void setPasswordChangeCount(Integer passwordChangeCount) {
		this.passwordChangeCount = passwordChangeCount;
	}

	/**
	 * Indicates that the password has been reset
	 * @return
	 */

	public int getResetPassword() {
		return resetPassword;
	}


	public void setResetPassword(int resetPassword) {
		this.resetPassword = resetPassword;
	}


	public String getOrigPrincipalName() {
		return origPrincipalName;
	}


	public void setOrigPrincipalName(String origPrincipalName) {
		this.origPrincipalName = origPrincipalName;
	}



}

