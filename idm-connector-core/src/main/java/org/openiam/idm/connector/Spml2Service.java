package org.openiam.idm.connector;

/** 
 * Interface defining the service
 * @author suneet
 *
 */
public interface Spml2Service {

	/* uid = userName */
	public void add(String uid, String cn, String sn, String givenName, String eMail, String password,
			String title, String state, String street,
			String postalCode,
			String ou, String displayName, String[] objectClass,  String description);
	public void modify(String userName, String cn, String sn, String givenName, String eMail, String password,
			String title, String state, String street,
			String postalCode);
	public void delete(String userName);
	
}
