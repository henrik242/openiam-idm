package org.openiam.idm.connector;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;
import java.util.*;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;


/**
 * Interface for objects that will manage the connection to the target system.
 * @author suneet
 *
 */
public interface ConnectionMgr {

	public LdapContext connect(ManagedSys sys);
	public void close();
}
