package org.openiam.idm.srvc.auth.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.SysConfiguration;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.pswd.dto.PasswordHistory;
import org.openiam.idm.srvc.pswd.service.PasswordHistoryDAO;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDAO;
import org.openiam.util.encrypt.*;

import java.util.*;

import javax.jws.WebService;

@WebService(endpointInterface = "org.openiam.idm.srvc.auth.login.LoginDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/auth/service", 
		serviceName = "LoginWebService")
public class LoginDataServiceImpl implements LoginDataService {

	protected LoginDAO loginDao;
	protected LoginAttributeDAO loginAttrDao;
	protected SecurityDomainDataService secDomainService; 
	protected UserDAO userDao;
	protected PolicyDAO policyDao;
	protected PasswordService passwordManager;
	protected PasswordHistoryDAO passwordHistoryDao;
	protected SysConfiguration sysConfiguration;
		
	protected Cryptor cryptor;

	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	boolean encrypt = true;	// default encryption setting
	private static final Log log = LogFactory.getLog(LoginDataServiceImpl.class);
	
	public Login addLogin(Login login) {
		if (login == null)
			throw new NullPointerException("Login is null");
		      
        if (login.getCreateDate() == null)
        	login.setCreateDate(new Date(System.currentTimeMillis()));
	
		return loginDao.add(login);

	}

	public Login getLogin(String secDomainId, String login) throws AuthenticationException {
		if (secDomainId == null)
			throw new NullPointerException("service is null");
		
		if (login == null)
			throw new NullPointerException("Login is null");
		
		SecurityDomain secDomain = secDomainService.getSecurityDomain(secDomainId);
		if (secDomain == null) {
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_DOMAIN);
		}
		
		LoginId id = new LoginId(secDomainId, login, secDomain.getAuthSysId());
		
		Login lg = loginDao.findById(id);

		return lg;
	}

	public Login getLoginByManagedSys(String domainId, String login,String sysId) {
		if (domainId == null)
			throw new NullPointerException("domainId is null");
		if (login == null)
			throw new NullPointerException("Login is null");
		
		log.debug("getLoginByManagedSys Params = domainId=" + domainId + " login=" + login + " AuthSysId=" + sysId);
		
		LoginId id = new LoginId(domainId, login, sysId);
		
		Login lg = loginDao.findById(id);
		
		log.debug("login=" + lg);
		
		

		return lg;
	}
	
	public String getPassword(String domainId, String login, String sysId ) {
		
		Login lg = getLoginByManagedSys(domainId, login, sysId);
		if (lg != null && lg.getPassword() != null) {
		    return cryptor.decrypt(lg.getPassword()) ;
		}
		
		return null;
	}
	
	/**
	 * Checks to see if a login exists for a user - domain - managed system combination
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @return
	 */
	public boolean loginExists(String domainId, String principal, String sysId) {
		Login lg = this.getLoginByManagedSys(domainId, principal, sysId);
		if (lg == null) {
			return false;
		}
		return true;
	}
	
	public List getLoginByDept(String managedSysId, String department, String div) {
		return loginDao.findLoginByDept(managedSysId, department, div);
	}
	
	/**
	 * determines if the new passowrd is equal to the current password that is associated with this principal
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @param newPassword
	 * @return
	 */
	public boolean isPasswordEq(String domainId, String principal, String sysId, String newPassword) {
		if (domainId == null) {
			throw new NullPointerException("domainId is null");
		}
		if (principal == null) {
			throw new NullPointerException("principal is null");
		}
		if (sysId == null) {
			throw new NullPointerException("sysId is null");
		}
		if (newPassword == null) {
			return false;
		}
		String oldPassword = getPassword(domainId, principal, sysId);
		if (oldPassword != null) {
			if (oldPassword.equals(newPassword)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets the password for a login. The password needs to be encrypted externally. this allow for flexiblity in 
	 * supporting alternate approaches to encryption.
	 * @param domainId
	 * @param login
	 * @param sysId
	 * @param password
	 * @return
	 */
	public boolean setPassword(String domainId, String login, String sysId, String password ) {
		Calendar cal = Calendar.getInstance();
		Calendar expCal = Calendar.getInstance();

		//SecurityDomain securityDomain = secDomainService.getSecurityDomain(domainId);
		//Policy plcy = policyDao.findById(securityDomain.getPasswordPolicyId());
		Policy plcy = passwordManager.getPasswordPolicy(domainId, login, sysId);
		
		String pswdExpValue = getPolicyAttribute( plcy.getPolicyAttributes(), "PWD_EXPIRATION");
		String changePswdOnReset = getPolicyAttribute( plcy.getPolicyAttributes(), "CHNG_PSWD_ON_RESET");
		String gracePeriod = getPolicyAttribute( plcy.getPolicyAttributes(),"PWD_EXP_GRACE");
		
		Login lg = getLoginByManagedSys(domainId, login, sysId);
		lg.setPassword(password);
		lg.setPwdChanged( cal.getTime());
		
		// increment the change password count
		if (lg.getPasswordChangeCount() == null) {
			lg.setPasswordChangeCount(new Integer(1));
		}else {
			lg.setPasswordChangeCount(lg.getPasswordChangeCount()+1);
		}
	    // password has been changed - we dont need to force a change password on the next login
	    lg.setResetPassword(0);
		
		
		// calculate when the password will expire
		if (pswdExpValue != null && !pswdExpValue.isEmpty()) {
			cal.add(Calendar.DATE, Integer.parseInt(pswdExpValue) );
			expCal.add(Calendar.DATE, Integer.parseInt(pswdExpValue));
			lg.setPwdExp( expCal.getTime());
			
			// calc the grace period if there is a policy for it
			if (gracePeriod != null && !gracePeriod.isEmpty()) {
				cal.add(Calendar.DATE, Integer.parseInt(gracePeriod) );
				lg.setGracePeriod(cal.getTime());
			}
		}		
		



		
		if (loginDao.update(lg) != null) {
			// add the password to the history table
			PasswordHistory hist = new PasswordHistory(lg.getId().getLogin() , lg.getId().getDomainId(),
					lg.getId().getManagedSysId());
			hist.setPassword(password);
			passwordHistoryDao.add(hist);
			return true;
		}
		return false;
		
	}
	public boolean resetPassword(String domainId, String login, String sysId, String password ) {
		
		//SecurityDomain securityDomain = secDomainService.getSecurityDomain(domainId);
		//Policy plcy = policyDao.findById(securityDomain.getPasswordPolicyId());
		
		Policy plcy = passwordManager.getPasswordPolicy(domainId, login, sysId);
		
		String pswdExpValue = getPolicyAttribute( plcy.getPolicyAttributes(), "PWD_EXPIRATION_ON_RESET");
		//String changePswdOnReset = getPolicyAttribute( plcy.getPolicyAttributes(), "CHNG_PSWD_ON_RESET");
		String gracePeriod = getPolicyAttribute( plcy.getPolicyAttributes(),"PWD_EXP_GRACE");
		
		
		Login lg = getLoginByManagedSys(domainId, login, sysId);
		User user = userDao.findById(lg.getUserId());
		user.setSecondaryStatus(null);
		userDao.update(user);
		
		lg.setPassword(password);		
		// set the other properties of a password based on policy
		Calendar cal = Calendar.getInstance();
		
		// reset the authn related flags
		lg.setAuthFailCount(0);
		lg.setIsLocked(0);

		// reset the password change count
		lg.setPasswordChangeCount(0);
		lg.setResetPassword(1);
		

		lg.setPwdChanged( cal.getTime());
		
		if (pswdExpValue != null && !pswdExpValue.isEmpty()) {
			cal.add(Calendar.DATE, Integer.parseInt(pswdExpValue) );
			lg.setPwdExp( cal.getTime());
		}
		// calc the grace period if there is a policy for it
		if (gracePeriod != null && !gracePeriod.isEmpty()) {
			cal.add(Calendar.DATE, Integer.parseInt(gracePeriod) );
			lg.setGracePeriod(cal.getTime());
		}

		
		
		if (loginDao.update(lg) != null) {
			return true;
		}
		return false;		
	}
	
	public String encryptPassword(String password ) {
		if (password != null) {
			return cryptor.encrypt(password);
		}
		return null;
	}

	public String decryptPassword(String password ) {
		if (password != null) {
			return cryptor.decrypt(password);
		}
		return null;
	}
	
	public List<Login> getLoginByUser(String userId) {
		
		log.info("LoginDataService: getLoginByUser userId=" + userId);
		
		if (userId == null)
			throw new NullPointerException("userId is null");
		List<Login> loginList = loginDao.findUser(userId);
		if (loginList == null || loginList.size() == 0)
			return null;
		return loginList;
		
		
	
		
	}
	
	public List<Login> getLoginByDomain(String domain) {
		if (domain == null)
			throw new NullPointerException("domain is null");
		List<Login> loginList = loginDao.findLoginByDomain(domain);
		if (loginList == null || loginList.size() == 0)
			return null;
		return loginList;

		
		
	}

	public void lockLogin(String domainId, String principal, String sysId) {
		Login lg = getLoginByManagedSys(domainId, principal, sysId);
		// get the user object
		User user = userDao.findById(lg.getUserId());
		
		lg.setIsLocked(1);
		user.setSecondaryStatus(UserStatusEnum.LOCKED);
		
		// update 
		updateLogin(lg);
		userDao.update(user);
		
	}
	
	public void unLockLogin(String domainId, String principal, String sysId) {
		Login lg = getLoginByManagedSys(domainId, principal, sysId);
		// get the user object
		User user = userDao.findById(lg.getUserId());
		
		lg.setIsLocked(0);
		user.setSecondaryStatus(null);
		
		// update 
		updateLogin(lg);
		userDao.update(user);

	}
	

	public void removeLogin(String serviceId, String login, String managedSysId) {
		if (login == null)
			throw new NullPointerException("Login is null");
		
		LoginId id = new LoginId(serviceId, login, managedSysId);
		Login loginDTO = loginDao.findById(id);
		loginDao.remove(loginDTO);
		
		
	}



	public void updateLogin(Login login) {
		if (login == null)
			throw new NullPointerException("Login is null");
		
        
       loginDao.update(login);
	}

	public LoginDAO getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public LoginAttributeDAO getLoginAttrDao() {
		return loginAttrDao;
	}

	public void setLoginAttrDao(LoginAttributeDAO loginAttrDao) {
		this.loginAttrDao = loginAttrDao;
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor crypt) {
		this.cryptor = crypt;
	}

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	private String getPolicyAttribute(Set<PolicyAttribute> attr, String name) {
		assert name != null : "Name parameter is null";
		
		log.debug("Attribute Set size=" + attr.size());
		
		for ( PolicyAttribute policyAtr :attr) {
			if (policyAtr.getName().equalsIgnoreCase(name)) {
				return policyAtr.getValue1();
			}
		}
		return null;
		
	}

	public PolicyDAO getPolicyDao() {
		return policyDao;
	}

	public void setPolicyDao(PolicyDAO policyDao) {
		this.policyDao = policyDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDataService#getUserByLogin(java.lang.String, java.lang.String, java.lang.String)
	 */
	public User getUserByLogin(String domainId, String login, String sysId) {
		Login lg = getLoginByManagedSys(domainId, login, sysId);
		if (lg == null) {
			return null;
		}
		if (lg.getUserId() == null) {
			log.info("UserId in login object is null");
			return null;
		}
		User usr = userDao.findById(lg.getUserId());
		
		//	 assemble the various dependant objects
		org.hibernate.Hibernate.initialize(usr.getPhone());
		org.hibernate.Hibernate.initialize(usr.getEmailAddress());
		org.hibernate.Hibernate.initialize(usr.getAddresses());
		org.hibernate.Hibernate.initialize(usr.getUserAttributes());
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDataService#bulkUnLock(org.openiam.idm.srvc.user.dto.UserStatusEnum)
	 */
	public void bulkUnLock(UserStatusEnum status) {
		log.info("bulk unlock called.");
		if (status == null) {
			throw new NullPointerException("status is null");
		}
		if (status != UserStatusEnum.LOCKED && status != UserStatusEnum.LOCKED_ADMIN) {
			throw new IllegalArgumentException("Invalid status value");
		}
		// since each security domain may have different authn policies, loop through each domain
		SecurityDomain[] secDomainAry = secDomainService.getAllDomainsWithExclude("IDM");
		for (SecurityDomain secDom : secDomainAry) {
			String authnPolicy =  secDom.getAuthnPolicyId();
			if (authnPolicy != null) {
				Policy plcy = policyDao.findById(authnPolicy);
				String autoUnlockTime = getPolicyAttribute( plcy.getPolicyAttributes(), "AUTO_UNLOCK_TIME");
				if (autoUnlockTime != null) {
					loginDao.bulkUnlock(secDom.getDomainId(), status, Integer.parseInt( autoUnlockTime ));
				}
			}
		
		}

	}
	
	public List<Login> getLockedUserSince(Date lastExecTime) {
		return loginDao.findLockedUsers(lastExecTime);
	}

	public List<Login> getInactiveUsers(int startDays, int endDays) {
		List<Login> loginList = loginDao.findInactiveUsers(startDays, endDays);
		log.info("Inactive user list=" + loginList);
		return loginList;
	}
	
	public PasswordService getPasswordManager() {
		return passwordManager;
	}

	public void setPasswordManager(PasswordService passwordManager) {
		this.passwordManager = passwordManager;
	}

	public PasswordHistoryDAO getPasswordHistoryDao() {
		return passwordHistoryDao;
	}

	public void setPasswordHistoryDao(PasswordHistoryDAO passwordHistoryDao) {
		this.passwordHistoryDao = passwordHistoryDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDataService#getUserNearPswdExpiration(int)
	 */
	public List<Login> getUserNearPswdExpiration(int expDays) {
		List<Login> loginList = loginDao.findUserNearPswdExp(expDays);
		return loginList;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDataService#getPrimaryIdentity(java.lang.String)
	 */
	public Login getPrimaryIdentity(String userId) {
		List<Login> loginList = getLoginByUser(userId);
		if (loginList == null) {
			return null;
		}
		
		for ( Login lg : loginList) {
			if (lg.getId().getManagedSysId().equalsIgnoreCase(sysConfiguration.getDefaultManagedSysId())) {
				return lg;
			}
		}
		
		return null;
	}

	public SysConfiguration getSysConfiguration() {
		return sysConfiguration;
	}

	public void setSysConfiguration(SysConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDataService#bulkResetPasswordChangeCount()
	 */
	public int bulkResetPasswordChangeCount() {
		return loginDao.bulkResetPasswordChangeCount();

	}




}
