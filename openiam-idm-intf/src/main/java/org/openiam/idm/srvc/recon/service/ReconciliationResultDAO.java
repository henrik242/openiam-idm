package org.openiam.idm.srvc.recon.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import org.openiam.idm.srvc.recon.dto.*;


public interface ReconciliationResultDAO {



	public void add(ReconciliationResult transientInstance) ;
	public void remove(ReconciliationResult persistentInstance); 
	public ReconciliationResult update (ReconciliationResult detachedInstance) ;
	public ReconciliationResult findById(java.lang.String id) ;


}
