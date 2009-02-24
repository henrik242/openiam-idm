package org.openiam.spml2.spi.ldap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.connector.ConnectionMgr;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;

import java.util.*;

/**
 * Manages connections to LDAP
 * @author Suneet Shah
 *
 */
public class LdapConnectionMgr implements ConnectionMgr {

	private String keystore;
	LdapContext ctxLdap = null;
	
	 //static protected ResourceBundle res = ResourceBundle.getBundle("ldapconf");
	 static protected ResourceBundle secres = ResourceBundle.getBundle("securityconf");
	
    private static final Log log = LogFactory.getLog(LdapConnectionMgr.class);

    public LdapConnectionMgr() {
    	keystore = secres.getString("KEYSTORE");
    }
    


	public LdapContext connect(ManagedSys sys) {

		Hashtable envDC = new Hashtable();
	
		keystore = secres.getString("KEYSTORE");
		System.setProperty("javax.net.ssl.trustStore",keystore);
		
		String urlLdap = sys.getHostUrl() + ":" + sys.getPort();
		String adminName = sys.getUserId();
		String password = sys.getPswd();
			
		System.out.println("**accountid = " + adminName);

		

		envDC.put(Context.PROVIDER_URL,urlLdap);
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); 
		envDC.put(Context.SECURITY_PRINCIPAL,adminName);  
		envDC.put(Context.SECURITY_CREDENTIALS,password);
		
		
		envDC.put(Context.SECURITY_PROTOCOL,sys.getCommProtocol());
		try {
			System.out.println("Pre-context ....");
			ctxLdap = new InitialLdapContext(envDC,null);
			System.out.println("Directory context = " + ctxLdap);
			return ctxLdap;
		}catch(NamingException ne) {
			log.error(ne.getMessage(), ne);
			ne.printStackTrace();
		}

		
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
