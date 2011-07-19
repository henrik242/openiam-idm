package org.openiam.idm.srvc.synch.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA


import java.sql.Timestamp;
import java.util.List;

import org.openiam.idm.srvc.synch.dto.SynchConfig;


/**
 * Home object for domain model class SynchConfig.
 * @see org.openiam.idm.srvc.pswd.service.SynchConfig
 * @author Hibernate Tools
 */
public interface  SynchConfigDAO {

	SynchConfig findById(java.lang.String id) ;

	SynchConfig add(SynchConfig instance);

	SynchConfig update(SynchConfig instance);

	void remove(SynchConfig instance);
	
	List<SynchConfig> findAllConfig();
	
	int updateExecTime(String configId, Timestamp execTime);
    int updateLastRecProcessed(String configId,String processTime);
	

}
