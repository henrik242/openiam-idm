package org.openiam.idm.srvc.orgpolicy.service;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA


import java.util.List;

import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;


/**
 * Home object for domain model class OrgPolicyAcceptance.
 */
public interface OrgPolicyDAO {



	public OrgPolicy add (OrgPolicy transientInstance) ;
	
	public void remove(OrgPolicy persistentInstance) ;

	public OrgPolicy update(OrgPolicy detachedInstance) ;

	public OrgPolicy findById(java.lang.String id) ;
	
	public List<OrgPolicy> findAll() ;

}
