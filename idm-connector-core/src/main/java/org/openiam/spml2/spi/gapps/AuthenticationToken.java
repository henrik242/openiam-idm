package org.openiam.spml2.spi.gapps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthParameters.OAuthType;
import com.google.gdata.client.appsforyourdomain.UserService;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import com.google.gdata.client.appsforyourdomain.AppsForYourDomainQuery;
import com.google.gdata.client.appsforyourdomain.AppsGroupsService;
import com.google.gdata.client.appsforyourdomain.EmailListRecipientService;
import com.google.gdata.client.appsforyourdomain.EmailListService;
import com.google.gdata.client.appsforyourdomain.NicknameService;
import com.google.gdata.client.appsforyourdomain.UserService;
import com.google.gdata.data.Link;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainErrorCode;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.EmailList;
import com.google.gdata.data.appsforyourdomain.Login;
import com.google.gdata.data.appsforyourdomain.Name;
import com.google.gdata.data.appsforyourdomain.Nickname;
import com.google.gdata.data.appsforyourdomain.Quota;
import com.google.gdata.data.appsforyourdomain.generic.GenericEntry;
import com.google.gdata.data.appsforyourdomain.generic.GenericFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListRecipientEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListRecipientFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.NicknameEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.NicknameFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
import com.google.gdata.data.extensions.Who;
import com.google.gdata.util.ServiceException;

/**
 * Generates an Authentication token that is used to when calling the google API for provisioning
 * @author suneet
 *
 */
public class AuthenticationToken {

	/*
	 * Create User Account  	POST https://apps-apis.google.com/a/feeds/domain/user/2.0
Retrieve User Account 	GET https://apps-apis.google.com/a/feeds/domain/user/2.0/userName
Retrieve All Users in Domain 	GET https://apps-apis.google.com/a/feeds/domain/user/2.0
Update User Account 	PUT https://apps-apis.google.com/a/feeds/domain/user/2.0/userName
Renaming User Account 	PUT https://apps-apis.google.com/a/feeds/domain/user/2.0/(old)userName
Delete User Account 	DELETE https://apps-apis.google.com/a/feeds/domain/user/2.0/userName
	 */
	private static String hashfunction = "SHA-1";
	
	  private static final String APPS_FEEDS_URL_BASE =
	      "https://apps-apis.google.com/a/feeds/";

	  protected static final String SERVICE_VERSION = "2.0";

	  protected static final String domainUrlBase = APPS_FEEDS_URL_BASE + "openiam.com" + "/user/2.0";
	  
	static public void deleteUser() {
		System.out.println("Deleting user");

    	UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");
    	try {
			userService.setUserCredentials("suneet_shah@openiam.com", "scorpio32");
	        URL deleteUrl = new URL(domainUrlBase + "/test3" );
	        userService.delete(deleteUrl);
			
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppsForYourDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	
	static public void setPassword(String name) {
		UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");
		
    	try {
    		
	    	UserEntry entry = new UserEntry();
	    	
	        Name n = new Name();
	        n.setGivenName("bb");
	        n.setFamilyName("tst1");
	        entry.addExtension(n);
	    	

	        
    		
			userService.setUserCredentials("suneet_shah@openiam.com", "scorpio32");
	        URL updateUrl = new URL(domainUrlBase + "/" + name );
	        userService.update(updateUrl, entry);
			
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppsForYourDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	}

	static public void changeUser(String name) {
		UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");
		
    	try {
    		
	    	UserEntry entry = new UserEntry();
	        Login login = new Login();
	        login.setPassword("openiam14");
	        entry.addExtension(login);
	        
    		
			userService.setUserCredentials("suneet_shah@openiam.com", "scorpio32");
	        URL updateUrl = new URL(domainUrlBase + "/" + name );
	        userService.update(updateUrl, entry);
			
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppsForYourDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	}
	
	static public void  createUser() {

	    try {
	    	// nickname
	    	// password
	    	// quota
	    	// group membership
	    	
	    	UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");
	    	userService.setUserCredentials("suneet_shah@openiam.com", "scorpio32");
	    	
	    	UserEntry entry = new UserEntry();
	        Login login = new Login();
	        login.setUserName("test4");
	        login.setPassword("openiam12");
	       // login.setHashFunctionName(hashfunction);
	       /* if (passwordHashFunction != null) {
	          login.setHashFunctionName(passwordHashFunction);
	        }
	       */
	        entry.addExtension(login);

	        Name name = new Name();
	        name.setGivenName("bob");
	        name.setFamilyName("test1");
	        entry.addExtension(name);

	    /*    if (quotaLimitInMb != null) {
	          Quota quota = new Quota();
	          quota.setLimit(quotaLimitInMb);
	          entry.addExtension(quota);
	        }
	    */

	        System.out.println("Domain Url = " + domainUrlBase);
	        URL insertUrl = new URL(domainUrlBase );
	        System.out.println( "add user = " + userService.insert(insertUrl, entry) );
	    	
	

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	static public void main(String[] arg) {
		System.out.println("Test...");
		//AuthenticationToken.createUser();
		AuthenticationToken.setPassword("test4");
		//AuthenticationToken.changeUser("test4");
		//AuthenticationToken.deleteUser();
	}
}
