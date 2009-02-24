package org.openiam.idm.connector;

import javax.jws.WebService;

/** 
 * Interface defining the service
 * @author suneet
 *
 */

@WebService
public interface Spml2Service {

	public void add(String userName, String cn, String sn, String givenName, String eMail, String password,
			String title, String state, String street,
			String postalCode);
	public void modify(String userName, String cn, String sn, String givenName, String eMail, String password,
			String title, String state, String street,
			String postalCode);
	public void delete(String userName);
	
}
