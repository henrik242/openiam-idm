package org.openiam.webadmin.busdel.base;

import java.util.List;

import diamelle.security.auth.*;
import diamelle.security.crypt.PBECryptor;

/**
 * <p>
 * <code>LoginAccess</code><font face="arial">
 * Business delegate for Authenticator and SecurityManager EJBs
 * </font>
 * </p>
 */

public class LoginAccess extends NavigationAccess {

	Authenticator auth = null;
	diamelle.security.auth.SecurityManager security = null;
	static private final String ENCRYPTION_KEY = "diamelle";

	public LoginAccess() {

		try {
			// getting home and remote object of bean.
			System.out.println("Authenticator = " + getHome("Authenticator") );
			System.out.println("Authenticator = " + this.getHome("diamelle.security.Authenticator", Authenticator.class));
			diamelle.security.auth.AuthenticatorHome aHome = (diamelle.security.auth.AuthenticatorHome) getHome("Authenticator");
			auth = aHome.create();
			SecurityManagerHome securityHome =
				(SecurityManagerHome) getHome("SecurityManager");
			security = securityHome.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the SSOSubject Object from authenticate() method
	 * @param service, login, password, 
	 * @returns SSOSubject Object
	 */
	public SSOSubject authenticate(String serviceId, String login, String password)
		throws InvalidLoginIdException, InvalidPasswordException, Exception {
		LoginValue lv = new LoginValue();
		lv.setService(serviceId);
		lv.setLogin(login);
		lv.setPassword(password);
		return auth.authenticate(lv);
	}
	

	/**
	 * Get the SSOSubject Object from authenticate() method
	 * @param lv - LoginValue
	 * @returns SSOSubject Object
	 */
	public SSOSubject authenticate(LoginValue lv)	throws diamelle.security.auth.AuthenticationException, java.rmi.RemoteException {
		return auth.authenticate(lv);
	}

	/**
	 * Get the SSOSubject Object from authenticate() method
	 * @param String userId
	 * @param String token
	 * @returns SSOSubject
	 */
	public SSOSubject authenticate(String userId, String token)
		throws Exception {

		// using clear text as token as encrypted tokens do not always match
		// decrypt(token) Sometimes BadPaddingException occurs
		// return auth.authenticate(userId, decrypt(token));
		return auth.authenticate(userId, token);
	}
	/**
	 * @returns true if the user has rights for that application
	 */
	public boolean hasPermission(String userId, String appId) throws Exception {
		return security.hasPermission(userId, appId);
	}

	public List getPermissions(String userId, String menuId, String langCd)
		throws Exception {
		return security.getPermissions(userId, menuId, langCd);
	}

	/**
	 * Logs out the user
	 */
	public void logout(String userId) throws java.rmi.RemoteException {
		auth.logout(userId);
	}

	public String encrypt(String clearText) {

		String token = null;
		try {
			PBECryptor cryptor = new PBECryptor(ENCRYPTION_KEY);
			token = cryptor.encode(clearText);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		BlowfishCryptor b = new BlowfishCryptor();
		String token = b.encode("257Watch");
		System.out.println("BlowfishToken: " + token);
		
		String cleartext = b.decode(token.getBytes());
		System.out.println("test - cleartext from decoding token: " + cleartext);
		*/

		return token;
	}

	public String decrypt(String token) throws Exception {

		String clearText = null;
		PBECryptor cryptor = new PBECryptor(ENCRYPTION_KEY);
		clearText = cryptor.decode(token);
		System.out.println("clearText: " + clearText);

		return clearText;
	}
}