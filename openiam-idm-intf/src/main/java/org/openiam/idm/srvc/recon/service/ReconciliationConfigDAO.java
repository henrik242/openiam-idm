package org.openiam.idm.srvc.recon.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import org.openiam.idm.srvc.recon.dto.*;

/**
 * Home object for domain model class ReconiliationConfig.
 * @author Hibernate Tools
 */
public interface ReconciliationConfigDAO {



	public ReconciliationConfig add(ReconciliationConfig transientInstance) ;

	public void remove(ReconciliationConfig persistentInstance) ;

	public ReconciliationConfig update(ReconciliationConfig detachedInstance);

	public ReconciliationConfig findById(java.lang.String id);

    public ReconciliationConfig findByResourceId(java.lang.String resourceId);

    public void removeByResourceId(java.lang.String resourceId);



}
