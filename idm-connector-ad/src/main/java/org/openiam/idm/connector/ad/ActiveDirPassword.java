package org.openiam.idm.connector.ad;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import org.openiam.idm.connector.Password;
import org.openiam.idm.connector.ConnectionMgr;
import javax.naming.ldap.LdapContext;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;


public class ActiveDirPassword implements Password {

	ConnectionMgr connection;
	
	int UF_ACCOUNTDISABLE = 0x0002;
	int UF_PASSWD_NOTREQD = 0x0020;
	int UF_PASSWD_CANT_CHANGE = 0x0040;
	int UF_NORMAL_ACCOUNT = 0x0200;
	int UF_DONT_EXPIRE_PASSWD = 0x10000;
	int UF_PASSWORD_EXPIRED = 0x800000;


	static protected ResourceBundle res = ResourceBundle.getBundle("ad-conf");
	 
	public void expirePassword() {
		// TODO Auto-generated method stub

	}

	public void resetPassword() {
		// TODO Auto-generated method stub

	}
	
	public NamingEnumeration search(LdapContext ctx ,String filterAttr, String filterValue) {
		SearchControls searchCtls = new SearchControls();
		
		//Specify the attributes to returned
		String returnedAtts[]={"sn","givenName","mail", "distinguishedName"};
		searchCtls.setReturningAttributes(returnedAtts);
		
		//Specify the search scope
		try {
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(&(objectClass=user)(" + filterAttr + "=" + filterValue + "))";
			String baseDN = res.getString("baseDN");
			return ctx.search(baseDN, searchFilter, searchCtls);
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		return null;
		
	}

	public void setPassword(String loginId,String password) {
		String name=null, sn=null, email=null, firstName=null, dn=null;
		
		try {
			LdapContext ctx = connection.connect(null);
			
			// we should be searching either the sAMAccountName or the UserPrincipalName. Both of these values are
			// unique
			NamingEnumeration answer = search(ctx,res.getString("searchAttribute"), loginId);
		
 
			//initialize counter to total the results
			int totalResults = 0;
 

			if  (answer.hasMoreElements()) {
	    		SearchResult sr = (SearchResult)answer.next();

				// Print out some of the attributes, catch the exception if the attributes have no values
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					try {
						if (attrs.get("sn") != null ) {
							sn = (String)attrs.get("sn").get();
						}
						if (attrs.get("givenName") != null ) {
							firstName = (String)attrs.get("givenName").get();
						}
						if (attrs.get("mail") != null ) {
							email = (String)attrs.get("mail").get();
						}
						if (attrs.get("distinguishedName") != null ) {
							dn = (String)attrs.get("distinguishedName").get();
						}
	
					} 
					catch (NullPointerException e)	{
						System.out.println("Errors listing attributes: " + e);
					}
				}

		}

	
			ModificationItem[] mods = new ModificationItem[2];
 
			//Replace the "unicdodePwd" attribute with a new value
			//Password must be both Unicode and a quoted string
			//String password = "\"" + "password123" + "\"";
			String newQuotedPassword = "\"" + password + "\"";
			byte[] bytePassword = newQuotedPassword.getBytes("UTF-16LE");
 
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", bytePassword));
			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userAccountControl",Integer.toString(UF_NORMAL_ACCOUNT + UF_PASSWORD_EXPIRED)));
			 
 
			// Perform the update
			ctx.modifyAttributes(dn, mods);
		
			System.out.println("Reset Password for: " + dn);	
			
			connection.close();
 
		} 
		catch (NamingException e) {
			System.out.println("Problem resetting password: " + e);
		}
		catch (UnsupportedEncodingException e) {
			System.out.println("Problem encoding password: " + e);
		}

	}

	public void validatePassword() {
		// TODO Auto-generated method stub
	}

	public ConnectionMgr getConnection() {
		return connection;
	}

	public void setConnection(ConnectionMgr connection) {
		this.connection = connection;
	}
}
