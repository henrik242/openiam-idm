package org.openiam.idm.srvc.auth.spi;

/**
 * The PostLogin interface needs to be implemented by login modules or services 
 *  that want to do post authentication processing.<br>
 *  The interface will be invoked by the Authentication service on the following events:
 *  <ul>
 *  	<li> Successful authentication 
 *  	<li> Failed authentication 
 *  	<li> During logout.
 *  </ul>
 * @author Suneet Shah
 * @version 2
 */
public interface PostLogin {

	/**
	 * Post processing on successful authentication
	 */
	public void onSuccess();
	/**
	 * Post processing on failed authentication
	 */
	public void onFailure();
	/**
	 *  Post processing on Logout.
	 */
	public void onLogout();
}
