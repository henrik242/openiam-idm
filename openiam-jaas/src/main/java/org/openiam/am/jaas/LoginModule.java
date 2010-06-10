package org.openiam.am.jaas;

import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;


import java.security.Principal;
import java.util.List;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.service.AuthenticationService;



public class LoginModule implements javax.security.auth.spi.LoginModule {
	
	static protected ResourceBundle res = ResourceBundle.getBundle("iam_client_config");
	static String serviceBaseUrl = res.getString("openiam.service_base_url");
	static String secDomain = res.getString("openiam.securityDomain");
	static String managedSysId = res.getString("openiam.managedSysId");
	
    /** <p>LoginModule debug mode is turned off by default.</p> */
    protected boolean debug;

    /** <p>The authentication status.</p> */
    protected boolean success;

    /** <p>The commit status.</p> */
    protected boolean commitSuccess;

    /** <p>The Subject to be authenticated.</p> */
    protected Subject subject;

    /** <p>A CallbackHandler for communicating with the end user (prompting for usernames and passwords, for example).</p> */
    protected CallbackHandler callbackHandler;

    /** <p>State shared with other configured LoginModules.</p> */
    protected Map sharedState;

    /** <p>Options specified in the login Configuration for this particular LoginModule.</p> */
    protected Map options;


    /** The portal user role. */
    protected String portalUserRole;

    /** <p>The user name.</p> */
    protected String username;

    protected String password;


    
    /**
     * <p>The default login module constructor.</p>
     */
    public LoginModule()
    {
    	
    	System.out.println("LoginModule: default constructor called.");
    	
    }

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
    {
    	System.out.println("LoginModule:initialize() called.");
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

    }
    
    



    
    /**
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    public boolean abort() throws LoginException
    {
        // Clean out state
        success = false;
        commitSuccess = false;
        username = null;
      //  if (callbackHandler instanceof PassiveCallbackHandler)
       // {
       //     ((PassiveCallbackHandler) callbackHandler).clearPassword();
       // }
        logout();
        return true;
    }


    
    /**
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    public boolean commit() throws LoginException
    {
    	return true;
    	
    	
    }

    public boolean login() throws LoginException {

    	System.out.println("LoginModule: login() method called.");
    	
    	// prompt for a user name and password
    	if (callbackHandler == null)
    	    throw new LoginException("Error: no CallbackHandler available " +
    			"to garner authentication information from the user");

    	Callback[] callbacks = new Callback[2];
    	callbacks[0] = new NameCallback("user name:");
    	callbacks[1] = new PasswordCallback("password:", false);
     
    	try {
    	    callbackHandler.handle(callbacks);
    	    username = ((NameCallback)callbacks[0]).getName();
    	    char[] tmpPassword = ((PasswordCallback)callbacks[1]).getPassword();
    	    if (tmpPassword == null) {
    		// treat a NULL password as an empty password
    		tmpPassword = new char[0];
    	    }
    	    password = new String(tmpPassword);
    	    ((PasswordCallback)callbacks[1]).clearPassword();
     
    	} catch (java.io.IOException ioe) {
    	    throw new LoginException(ioe.toString());
    	} catch (UnsupportedCallbackException uce) {
    	    throw new LoginException("Error: " + uce.getCallback().toString() +
    		" not available to garner authentication information " +
    		"from the user");
    	}

    	try {
			AuthenticationService authenticate = ServiceLookupHelper.getAuthenticationService();
			
			org.openiam.idm.srvc.auth.dto.Subject sub = authenticate.passwordAuth("USR_SEC_DOMAIN", username, password);
			
			System.out.println("validation: subject=" + sub);
			
			return true;

			
		}catch(AuthenticationException ae) {
			ae.printStackTrace();
			int errCode = ae.getErrorCode();
			switch (errCode) {
			case AuthenticationConstants.RESULT_INVALID_DOMAIN:
				throw new FailedLoginException("RESULT_INVALID_DOMAIN");
			case AuthenticationConstants.RESULT_INVALID_LOGIN:
				throw new FailedLoginException("RESULT_INVALID_LOGIN");
			case AuthenticationConstants.RESULT_INVALID_PASSWORD:
				throw new FailedLoginException("RESULT_INVALID_PASSWORD");
			case AuthenticationConstants.RESULT_INVALID_USER_STATUS:
				throw new FailedLoginException("RESULT_INVALID_USER_STATUS");
			case AuthenticationConstants.RESULT_LOGIN_DISABLED:
				throw new FailedLoginException("RESULT_LOGIN_DISABLED");
			case AuthenticationConstants.RESULT_LOGIN_LOCKED:
				throw new FailedLoginException("RESULT_LOGIN_LOCKED");
			case AuthenticationConstants.RESULT_PASSWORD_EXPIRED:
				throw new FailedLoginException("RESULT_PASSWORD_EXPIRED");
			case AuthenticationConstants.RESULT_SERVICE_NOT_FOUND:
			default:
				throw new FailedLoginException("INVALID");
			}
		}
    	
    	// verify the username/password
 /*   	boolean usernameCorrect = false;
    	boolean passwordCorrect = false;
    	
    	System.out.println("UserName found=" + username);
    	
    	if (username.equals("testUser"))
    	    usernameCorrect = true;
    	boolean succeeded;
		if (usernameCorrect ) {

    	    // authentication succeeded!!!
    	    passwordCorrect = true;
    	    if (debug)
    		System.out.println("\t\t[SampleLoginModule] " +
    				"authentication succeeded");
    	    succeeded = true;
    	    return true;
    	} else {

    	    // authentication failed -- clean out state
    	    if (debug)
    		System.out.println("\t\t[SampleLoginModule] " +
    				"authentication failed");
    	    succeeded = false;
    	    username = null;
    	    password = null;
    	    if (!usernameCorrect) {
    		throw new FailedLoginException("User Name Incorrect");
    	    } else {
    		throw new FailedLoginException("Password Incorrect");
    	    }
    	}
    	
   */
        }

    
    
    /**
     * @see javax.security.auth.spi.LoginModule#login()
     */
 /*   public boolean login() throws LoginException
    {
    	System.out.println("LoginModule: login() called.");
        if (callbackHandler == null)
        {
            throw new LoginException("Error: no CallbackHandler available " + "to garner authentication information from the user");
        }
        try
        {
        	System.out.println("Login:login called.");
        	
            // Setup default callback handlers.
            Callback[] callbacks = new Callback[] { new NameCallback("Username: "), 
            		new PasswordCallback("Password: ", false)};

            System.out.println("Login:login called.1");
            callbackHandler.handle(callbacks);

            username = ((NameCallback) callbacks[0]).getName();
            String password = new String(((PasswordCallback) callbacks[1]).getPassword());

            System.out.println("Login:login called.2");
            ((PasswordCallback) callbacks[1]).clearPassword();

            System.out.println("Login:login called.3 -pre-refreshProxy");
            
     //       refreshProxy();            
            
            
            //success = ums.authenticate(this.username, password);
            
            System.out.println("After authenticate success="+success);
            callbacks[0] = null;
            callbacks[1] = null;


            return (success);
        }

        catch (Exception ex)
        {
        	System.out.println("LogiModule:Login() Caught  exception");
        	ex.printStackTrace();
        	
            success = false;
            throw new LoginException(ex.getMessage());
        }
    }
*/
    /**
     * <p> This method is called if the LoginContext's
     * overall authentication succeeded
     * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own authentication attempt
     * succeeded (checked by retrieving the private state saved by the
     * <code>login</code> method), then this method associates a
     * <code>SamplePrincipal</code>
     * with the <code>Subject</code> located in the
     * <code>LoginModule</code>.  If this LoginModule's own
     * authentication attempted failed, then this method removes
     * any state that was originally saved.
     *
     * <p>
     *
     * @exception LoginException if the commit fails.
     *
     * @return true if this LoginModule's own login and commit
     *		attempts succeeded, or false otherwise.
     */
 /*   public boolean commit() throws LoginException {
	if (succeeded == false) {
	    return false;
	} else {
	    // add a Principal (authenticated identity)
	    // to the Subject

	    // assume the user we authenticated is the SamplePrincipal
	    userPrincipal = new SamplePrincipal(username);
	    if (!subject.getPrincipals().contains(userPrincipal))
		subject.getPrincipals().add(userPrincipal);

	    if (debug) {
		System.out.println("\t\t[SampleLoginModule] " +
				"added SamplePrincipal to Subject");
	    }

	    // in any case, clean out state
	    username = null;
	    for (int i = 0; i < password.length; i++)
		password[i] = ' ';
	    password = null;

	    commitSucceeded = true;
	    return true;
	}
    }
*/

    /**
     * <p> This method is called if the LoginContext's
     * overall authentication failed.
     * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
     * did not succeed).
     *
     * <p> If this LoginModule's own authentication attempt
     * succeeded (checked by retrieving the private state saved by the
     * <code>login</code> and <code>commit</code> methods),
     * then this method cleans up any state that was originally saved.
     *
     * <p>
     *
     * @exception LoginException if the abort fails.
     *
     * @return false if this LoginModule's own login and/or commit attempts
     *		failed, and true otherwise.
     */
 /*   public boolean abort() throws LoginException {
	if (succeeded == false) {
	    return false;
	} else if (succeeded == true && commitSucceeded == false) {
	    // login succeeded but overall authentication failed
	    succeeded = false;
	    username = null;
	    if (password != null) {
		for (int i = 0; i < password.length; i++)
		    password[i] = ' ';
		password = null;
	    }
	    userPrincipal = null;
	} else {
	    // overall authentication succeeded and commit succeeded,
	    // but someone else's commit failed
	    logout();
	}
	return true;
    }

*/
    /**
     * Logout the user.
     *
     * <p> This method removes the <code>SamplePrincipal</code>
     * that was added by the <code>commit</code> method.
     *
     * <p>
     *
     * @exception LoginException if the logout fails.
     *
     * @return true in all cases since this <code>LoginModule</code>
     *          should not be ignored.
     */
 /*   public boolean logout() throws LoginException {

	subject.getPrincipals().remove(userPrincipal);
	succeeded = false;
	succeeded = commitSucceeded;
	username = null;
	if (password != null) {
	    for (int i = 0; i < password.length; i++)
		password[i] = ' ';
	    password = null;
	}
	userPrincipal = null;
	return true;
    }
*/
    
    /**
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    public boolean logout() throws LoginException
    {
        // TODO Can we set subject to null?
        subject.getPrincipals().clear();
        subject.getPrivateCredentials().clear();
        subject.getPublicCredentials().clear();
        success = false;
        commitSuccess = false;

        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
     */


    
 /*   protected Principal getUserPrincipal(User user)
    {
    	System.out.println("LoginModule:getUserPrincipal() called.");
        return SecurityHelper.getPrincipal(user.getSubject(),UserPrincipal.class);
    }
    
    protected List getUserRoles(User user)
    {
    	System.out.println("LoginModule:getUserRoles() called.");
        return SecurityHelper.getPrincipals(user.getSubject(),RolePrincipal.class);
    }
  */  
    /**
     * Default setup of the logged on Subject Principals for Tomcat
     * @param subject
     * @param user
     */
 /*   protected void commitPrincipals(Subject subject, User user)
    {
    	
    	System.out.println("LoginModule: commitPrincipals() called.");
        // add user specific portal user name and roles
        subject.getPrincipals().add(getUserPrincipal(user));
        subject.getPrincipals().addAll(getUserRoles(user));

        // add portal user role: used in web.xml authorization to
        // detect authenticated portal users
        subject.getPrincipals().add(new RolePrincipalImpl(portalUserRole));        
    }
*/

}
