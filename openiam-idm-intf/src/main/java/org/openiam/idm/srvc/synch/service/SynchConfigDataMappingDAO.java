package org.openiam.idm.srvc.synch.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.dto.SynchConfigDataMapping;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SynchConfigDataMapping.
 * @see org.openiam.idm.srvc.pswd.service.SynchConfigDataMapping
 * @author Hibernate Tools
 */
public interface SynchConfigDataMappingDAO {

	SynchConfigDataMapping findById(java.lang.String id) ;

	SynchConfigDataMapping add(SynchConfigDataMapping instance);

	SynchConfigDataMapping update(SynchConfigDataMapping instance);

	void remove(SynchConfigDataMapping instance);
	
	//List<SynchConfigDataMapping> findDataMappingByConfigId(String configId);
	
}
