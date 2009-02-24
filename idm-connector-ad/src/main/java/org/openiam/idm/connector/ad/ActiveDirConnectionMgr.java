package org.openiam.idm.connector.ad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.connector.ConnectionMgr;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;


import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;

import java.util.*;

/**
 * Manages connections to active directory
 * @author suneet
 *
 */
public class ActiveDirConnectionMgr implements ConnectionMgr {

	private String keystore;
	LdapContext ctxLdap = null;
	
    static protected final Log log = LogFactory.getLog(ActiveDirConnectionMgr.class);
    
	 static protected ResourceBundle res = ResourceBundle.getBundle("ad-conf");
 
     static protected ResourceBundle resSecstore = ResourceBundle.getBundle("securityconf");

    public ActiveDirConnectionMgr() {
    	keystore = resSecstore.getString("KEYSTORE");
    }


	
	public LdapContext connect(ManagedSys sys) {

		LdapContext ctxLdap = null;
		Hashtable envDC = new Hashtable();
		
		
		String urlAD = res.getString("hostname");
		String baseContainer = res.getString("baseDN");
		
		System.out.println("k=" + keystore);
		
		System.setProperty("javax.net.ssl.trustStore",keystore);

		
		String adminName = res.getString("accountid");
		String password = res.getString("accountpassword");
		String protocol = res.getString("protocol");

		
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,adminName);  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS, password);
		envDC.put(Context.PROVIDER_URL,urlAD);
		envDC.put(Context.REFERRAL,"follow");
		if (protocol.equalsIgnoreCase("ssl")) {
			System.out.println("protocol=" + protocol );
			envDC.put(Context.SECURITY_PROTOCOL,"ssl");
		}
		try {
			ctxLdap = new InitialLdapContext(envDC,null);
			System.out.println("AD Directory context = " + ctxLdap);
			return ctxLdap;
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		
		
		System.out.println("Not Connected to = " + urlAD);

		
		return null;
	}

	
	public void close() {

		if (this.ctxLdap != null) { 
			try {
			ctxLdap.close();
			}catch(NamingException ne ) {
				log.error(ne.getMessage(), ne);
				ne.printStackTrace();
			}
		}
		ctxLdap = null;
		
	}

}
