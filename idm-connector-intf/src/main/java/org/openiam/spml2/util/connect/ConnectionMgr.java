package org.openiam.spml2.util.connect;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

import java.util.*;

/**
 * Interface for objects that will manage the connection to the target system.
 * @author suneet
 *
 */
public interface ConnectionMgr {

	public LdapContext connect(ManagedSys managedSys);
	public void close();
}
