package org.openiam.idm.connector.ad;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.jws.WebService;
import javax.naming.ldap.LdapContext;
import javax.naming.directory.*;
import javax.naming.ldap.*;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttributes;

import org.openiam.idm.connector.*;


@WebService(endpointInterface = "org.openiam.idm.connector.Spml2Service", 
		targetNamespace = "urn:idm.openiam.org/connector/activedir/", 
		serviceName = "ActiveDirConnectorService")
public class ADSpml2Service implements Spml2Service{

	ConnectionMgr connection;
	
	int UF_ACCOUNTDISABLE = 0x0002;
	int UF_PASSWD_NOTREQD = 0x0020;
	int UF_PASSWD_CANT_CHANGE = 0x0040;
	int UF_NORMAL_ACCOUNT = 0x0200;
	int UF_DONT_EXPIRE_PASSWD = 0x10000;
	int UF_PASSWORD_EXPIRED = 0x800000;


	 static protected ResourceBundle res = ResourceBundle.getBundle("ad-conf");
	 
	
	public ConnectionMgr getConnection() {
		return connection;
	}

	public void setConnection(ConnectionMgr connection) {
		this.connection = connection;
	}

	public void add(String loginId, String cn, String sn, String givenName, String eMail,String password,
			String title, String state, String street,
			String postalCode) {
		Context result = null;
		

		// TODO Auto-generated method stub		
		LdapContext ctx = connection.connect(null);
		
		String baseDN = res.getString("baseDN");
		String userIdAttribute = res.getString("userIdAttribute");
		
		String name = "CN=" + loginId + "," + baseDN;
		
		BasicAttributes basicAttr  = getAttributes( cn, sn, givenName, eMail, loginId, password,
				title, state, street, postalCode);
		try {
			result = ctx.createSubcontext(name, basicAttr);
			
			System.out.println("User created...");
	
			ModificationItem[] mods = new ModificationItem[2];

			System.out.println("P=" + password);
			
			String newQuotedPassword = "\"" + password + "\"";
			byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");

			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword));
			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userAccountControl",Integer.toString(UF_NORMAL_ACCOUNT + UF_PASSWORD_EXPIRED)));


			System.out.println("Name=" + name);
			ctx.modifyAttributes(name, mods);

			ctx.close();		
			
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	
	}
	
	private BasicAttributes getAttributes(String cn, String sn, String givenName, String eMail, 
				String loginId, String password,
				String title, String state, String street,
				String postalCode) {
		

		BasicAttributes attrs = new BasicAttributes();

		attrs.put("objectClass","user");
		attrs.put("sAMAccountName",loginId);
		attrs.put("cn",loginId);

		attrs.put("givenName",givenName);
		attrs.put("sn",sn);
		attrs.put("displayName",cn);
    	attrs.put("mail",loginId+"@lmco.com");
    	
    	attrs.put("userAccountControl",
    				Integer.toString(UF_NORMAL_ACCOUNT + UF_PASSWD_NOTREQD + 
    							UF_PASSWORD_EXPIRED+ UF_ACCOUNTDISABLE));
    	 
		
		
		if (title != null && title.length() > 0) {
			attrs.put("title",title);
		}
		if (state != null && state.length() > 0) {
			attrs.put("st",state);
		}
		if (street != null && street.length() > 0) {
			attrs.put("street",street);
		}
		if (postalCode != null && postalCode.length() > 0) {
			attrs.put("postalCode", postalCode);
		}
		
		return attrs;


	}
	


	

	public void delete(String loginId) {
		Context result = null;
		

		// TODO Auto-generated method stub		
		LdapContext ctx = connection.connect(null);
		
		String baseDN = res.getString("baseDN");
		String userIdAttribute = res.getString("userIdAttribute");
		
		String name = "CN=" + loginId + "," + baseDN;
		
		try {
	
			ModificationItem[] mods = new ModificationItem[1];

			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userAccountControl",Integer.toString( UF_ACCOUNTDISABLE)));


			ctx.modifyAttributes(name, mods);

			ctx.close();		
			
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}

	public void modify(String loginId, String cn, String sn, String givenName, String eMail,String password,
			String title, String state, String street,
			String postalCode) {

		try {
			LdapContext ctx = connection.connect(null);
			
			String baseDN = res.getString("baseDN");
			String userIdAttribute = res.getString("userIdAttribute");
			
			String name = "CN=" + loginId + "," + baseDN;

			
			ModificationItem[] mods = new ModificationItem[4];

			if (title != null) {
				mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title", title));
			}else {
				mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title", " "));
			}
			if (state != null) {
	 			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("st", state));
			}else {
	 			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("st", " "));
			}
			if (street != null) {
				mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("street", street));
			}else {
				mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("street", " "));
			}
			if (postalCode != null) {	 		
				mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("postalCode", postalCode));
			}else {
				mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("postalCode", " "));				
			}



	 		
			// Perform the update
			ctx.modifyAttributes(name, mods);
			
			connection.close();
	 
		}catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
