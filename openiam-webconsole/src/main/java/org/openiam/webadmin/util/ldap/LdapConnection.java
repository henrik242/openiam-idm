package org.openiam.webadmin.util.ldap;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 8/31/11
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class LdapConnection {

       public static LdapContext connect(ManagedSys sys)  throws NamingException {

		//LdapContext ctxLdap = null;
		Hashtable<String, String> envDC = new Hashtable();

        String hostUrl = sys.getHostUrl();
		if (sys.getPort() > 0 ) {
			hostUrl = hostUrl + ":" + String.valueOf(sys.getPort());
		}

		envDC.put(Context.PROVIDER_URL, hostUrl);
		envDC.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		envDC.put(Context.SECURITY_AUTHENTICATION, "simple" ); // simple
		envDC.put(Context.SECURITY_PRINCIPAL,sys.getUserId());  //"administrator@diamelle.local"
		envDC.put(Context.SECURITY_CREDENTIALS, sys.getDecryptPassword());


		return (new InitialLdapContext(envDC,null));


	}
}
