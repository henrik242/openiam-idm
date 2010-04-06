package org.openiam.idm.srvc.auth.login;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.dto.*;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;

/**
 * Interface to manage the principal object. The principal object is largely used for service that use password 
 * based authentication.
 * 
 * @author Suneet Shah
 *
 */

@WebService
public interface LoginDataService {

	/**
	 * This method adds a principal to the user specified in the Login object.. <br>
	 * For example:
	 * <p>
	 * <code>
	 *  Login lv = new Login();<br>
	 *  lv.setLogin(login);<br>
	 *  lv.setPassword(password);<br>
	 *  lv.setService(service);<br>
	 *  lv.setNewUser(true);<br>
	 *  loginDataService.addLogin(lv);<br>
	 * </code>
	 * 
	 * 
	 * @param loginValue
	 * 
	 */
	public Login addLogin(Login principal);
	public void updateLogin(Login principal);
	public void removeLogin(String domainId, String principal, String managedSysId);
	public Login getLogin(String domainId, String principal) throws AuthenticationException ;
	public Login getLoginByManagedSys(String domainId, String principal,String sysId) ;
	
	public User getUserByLogin(String domainId, String principal, String sysId); 
	
	/**
	 * Returns the primary identity for this user
	 * @param userId
	 * @return
	 */
	public Login getPrimaryIdentity(String userId);
	
	/**
	 * Returns a decrypted password.
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @return
	 */
	public String getPassword(String domainId, String principal, String sysId );
	
	/**
	 * determines if the new passowrd is equal to the current password that is associated with this principal
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @param newPassword
	 * @return
	 */
	public boolean isPasswordEq(String domainId, String principal, String sysId, String newPassword);
	
	/**
	 * Checks to see if a login exists for a user - domain - managed system combination
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @return
	 */
	public boolean loginExists(String domainId, String principal, String sysId);
	
	/**
	 * Sets the password for a principal. The password needs to be encrypted externally. this allow for flexiblity in 
	 * supporting alternate approaches to encryption.
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @param password
	 * @return
	 */
	public boolean setPassword(String domainId, String principal, String sysId, String password );
	
	/**
	 * Sets a new password for the identity and updates the support attributes such as locked account flag.
	 * @param domainId
	 * @param principal
	 * @param sysId
	 * @param password
	 * @return
	 */
	public boolean resetPassword(String domainId, String principal, String sysId, String password );
	

	/**
	 * Encrypts the password string.
	 * @param password
	 * @return
	 */
	public String encryptPassword(String password );
	public String decryptPassword(String password);
	
	public List<Login> getLoginByUser(String userId);
	
	void lockLogin(String domainId, String principal, String sysId);

	void unLockLogin(String domainId, String principal, String sysId);


	/**
	 * Unlocks all accounts that are in the specified status. Valid status codes include LOCKED AND ADMIN_LOCKED.
	 * @param status
	 */
	public void bulkUnLock(UserStatusEnum status);
	
	int bulkResetPasswordChangeCount();
	
	List<Login> getLoginByDomain(String domain);
	
	/**
	 * List containing an array of User and Login objects
	 * @param managedSysId
	 * @param department
	 * @param div
	 * @return
	 */
	public List getLoginByDept(String managedSysId, String department, String div);
	
	public List<Login> getLockedUserSince(Date lastExecTime);
	
	/**
	 * Return the list of users that have not logged in certain number of days.
	 * @param startDays
	 * @param endDays
	 * @return
	 */
	public List<Login> getInactiveUsers(int startDays, int endDays);
	
	public List<Login> getUserNearPswdExpiration(int expDays);
	

	
}
