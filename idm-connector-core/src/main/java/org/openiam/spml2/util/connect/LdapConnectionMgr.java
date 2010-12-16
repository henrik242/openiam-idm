package org.openiam.spml2.util.connect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	static protected ResourceBundle secres = ResourceBundle.getBundle("securityconf");
	
    private static final Log log = LogFactory.getLog(LdapConnectionMgr.class);

    public LdapConnectionMgr() {
    	keystore = secres.getString("KEYSTORE");
    }
    


	public LdapContext connect(ManagedSys managedSys) {

		//LdapContext ctxLdap = null;
		Hashtable<String, String> envDC = new Hashtable();
	
		keystore = secres.getString("KEYSTORE");
		System.setProperty("javax.net.ssl.trustStore",keystore);

		String hostUrl = managedSys.getHostUrl();
		if (managedSys.getPort() > 0 ) {
			hostUrl = hostUrl + ":" + String.valueOf(managedSys.getPort());
		}
		
		log.debug("Directory host url:" + hostUrl);
		//log.info(" directory login = " + managedSys.getUserId() );
		//log.info(" directory login passowrd= " + managedSys.getDecryptPassword() );
		
		envDC.put(Context.PROVIDER_URL,hostUrl);
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");		
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,managedSys.getUserId());  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS,managedSys.getDecryptPassword());	
		if (managedSys.getCommProtocol() != null && managedSys.getCommProtocol().equalsIgnoreCase("SSL")) {
			envDC.put(Context.SECURITY_PROTOCOL, managedSys.getCommProtocol());
		}
		
		try {
			return (new InitialLdapContext(envDC,null));
		}catch(NamingException ne) {
			log.error(ne.getMessage(), ne);
			ne.printStackTrace();
			return null;
		}

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
