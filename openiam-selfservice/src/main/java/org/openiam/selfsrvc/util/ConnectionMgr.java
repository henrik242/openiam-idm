package org.openiam.selfsrvc.util;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;
import java.util.*;

/**
 * Interface for objects that will manage the connection to the target system.
 * @author suneet
 *
 */
public interface ConnectionMgr {

	public LdapContext connect(ConnectionParam param);
	public void close();
}
