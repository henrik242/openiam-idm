package org.openiam.idm.connector.ldap;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.naming.ldap.LdapContext;
import javax.naming.directory.*;
import javax.naming.ldap.*;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttributes;

import org.openiam.idm.connector.*;

public class LdapSpml2Service implements Spml2Service{

	ConnectionMgr connection;

    static protected ResourceBundle res = ResourceBundle.getBundle("ldapconf");

	
	public ConnectionMgr getConnection() {
		return connection;
	}

	public void setConnection(ConnectionMgr connection) {
		this.connection = connection;
	}

	public void add(String loginId, String cn, String sn, String givenName, String eMail,String password,
			String title, String state, String street,
			String postalCode, 
			String ou, String displayName, String[] objectClass, String description ) {
		
		Context result = null;
		
		


		// TODO Auto-generated method stub		
		LdapContext ctx = connection.connect();
		
		String baseDN = res.getString("baseDN");
		String userIdAttribute = res.getString("userIdAttribute");
		
		//String userName = userIdAttribute + "=" + userId + "," + baseDN;	
		//String baseContainer = "ou=People,dc=openiam,dc=org";
		
		//String ldapName = userIdAttribute + "=" + cn + "," + baseDN;
		//String name = "CN=" + userName + "," + baseContainer;
		String ldapName = "uid=" + loginId + "," + baseDN;
		
		BasicAttributes basicAttr  = getAttributes( cn, sn, givenName, eMail, loginId, password,
				title, state, street, postalCode,
				ou, displayName, objectClass, description);
		try {
			result = ctx.createSubcontext(ldapName, basicAttr);
			ctx.close();		
			
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	
	}
	
	private BasicAttributes getAttributes(String cn, String sn, String givenName, String eMail, 
				String uid, String password,
				String title, String state, String street,
				String postalCode,
				String ou, String displayName, String[] objectClass, String description) {
		
		Attribute oc = new BasicAttribute("objectclass");
	   // oc.add("inetOrgPerson");
       // oc.add("organizationalPerson");
       // oc.add("person");
	   // oc.add("top");

		if (objectClass == null) {
			   oc.add("inetOrgPerson");
		       oc.add("organizationalPerson");
		       oc.add("person");
			   oc.add("top");			
		}else {
			int size = objectClass.length;
			for (int i=0; i<size; i++) {
				oc.add(objectClass[i]);
			}
		
		}
		
		Attribute ouSet = new BasicAttribute("ou");
        //ouSet.add("People");
	    ouSet.add(ou);
		
		BasicAttributes attrs = new BasicAttributes();
		// associate the object class
		attrs.put(oc);
		attrs.put(ouSet);

		
		attrs.put("cn",cn);
		attrs.put("sn",sn);
		attrs.put("givenname",givenName);
		//attrs.put("mail",eMail);
		System.out.println("LoginId = " + uid);
		attrs.put("uid", uid);
		attrs.put("userPassword",password);
		attrs.put("displayname", displayName);
		attrs.put("description",description);
		
		
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
	

	public void delete(String uid) {
		try {
			LdapContext ctx = connection.connect();
			
			String baseDN = res.getString("baseDN");
			String userIdAttribute = res.getString("userIdAttribute");
			//String ldapName = userIdAttribute + "=" + cn + "," + baseDN;
	
			String ldapName = "uid=" + uid + "," + baseDN;
			
			System.out.println("In delete: ldapName=" + ldapName);
			
			ctx.destroySubcontext(ldapName);
			
		
			connection.close();
	 
		}catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	public void modify(String loginId, String cn, String sn, String givenName, String eMail,String password,
			String title, String state, String street,
			String postalCode) {

		try {
			LdapContext ctx = connection.connect();
			
			String baseDN = res.getString("baseDN");
			String userIdAttribute = res.getString("userIdAttribute");
			//String ldapName = userIdAttribute + "=" + cn + "," + baseDN;
			String ldapName = "uid=" + loginId + "," + baseDN;
			
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
			ctx.modifyAttributes(ldapName, mods);
			
			connection.close();
	 
		}catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
