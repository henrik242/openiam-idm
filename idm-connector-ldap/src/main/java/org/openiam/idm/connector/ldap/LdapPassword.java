package org.openiam.idm.connector.ldap;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import org.openiam.idm.connector.Password;
import org.openiam.idm.connector.ConnectionMgr;
import javax.naming.ldap.LdapContext;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;

public class LdapPassword implements Password {

	ConnectionMgr connection;
	
	 static protected ResourceBundle res = ResourceBundle.getBundle("ldapconf");

	 
	public void expirePassword() {
		// TODO Auto-generated method stub

	}

	public void resetPassword() {
		// TODO Auto-generated method stub

	}

	public void setPassword(String userId,String password) {
		try {
		System.out.println("*** in setPassword");	
			LdapContext ctx = connection.connect();
			
			String baseDN = res.getString("baseDN");
			System.out.println("*** baseDN=" + baseDN);
			String userIdAttribute = res.getString("userIdAttribute");
			System.out.println("*** baseUserId=" + userIdAttribute);
			
			//String userName = userIdAttribute + "=" + userId + "," + baseDN;
			String userName = "uid=" + userId + "," + baseDN;
			//String userName = "uid=openiamtest1,ou=people,dc=iss-esu_accounts,dc=net";
			//String userName = "testopeniam";
			//set password is a ldap modfy operation
			ModificationItem[] mods = new ModificationItem[1];
 
			//Replace the "unicdodePwd" attribute with a new value
			//Password must be both Unicode and a quoted string
			//String password = "\"" + "password123" + "\"";
			byte[] bytePassword = password.getBytes("UTF-16LE");
 
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", password));
 
			// Perform the update
			ctx.modifyAttributes(userName, mods);
		
			System.out.println("Reset Password for: " + userName);	
			
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
