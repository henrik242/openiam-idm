package org.openiam.idm.srvc.orgpolicy.service;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA


import java.util.List;

import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;


/**
 * Home object for domain model class OrgPolicyLog.
 */
public interface OrgPolicyUserLogDAO {



	public OrgPolicyUserLog add (OrgPolicyUserLog transientInstance) ;
	
	public void remove(OrgPolicyUserLog persistentInstance) ;

	public OrgPolicyUserLog update(OrgPolicyUserLog detachedInstance) ;

	public OrgPolicyUserLog findById(java.lang.String id) ;
	
	public List<OrgPolicyUserLog> findLogForUser(String userId);

}
