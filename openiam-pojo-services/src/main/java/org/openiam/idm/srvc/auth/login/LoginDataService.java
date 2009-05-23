package org.openiam.idm.srvc.auth.login;

import javax.jws.WebService;

import org.openiam.idm.srvc.auth.dto.*;

/**
 * Interface to manage the login object. The Login object is largely used for service that use password 
 * based authentication.
 * 
 * @author Suneet Shah
 *
 */
@WebService
public interface LoginDataService {

	/**
	 * This method adds a login to the user specified in the Login object.. <br>
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
	public Login addLogin(Login login);
	public void updateLogin(Login login);
	public void removeLogin(String serviceId, String login);
	public Login getLogin(String serviceId, String login);
	public Login getLoginByManagedSys(String serviceId, String login,String sysId) ;
	public Login[] getLoginByUser(String userId);
	
	void lockLogin(String serviceId, String login);

	void unLockLogin(String serviceId, String login);
	
	Login[] getLoginByDomain(String domain);
	
	
	/*
	 * getLoginByService () - needs pagination - could be a large resultset
	 */
	
}
