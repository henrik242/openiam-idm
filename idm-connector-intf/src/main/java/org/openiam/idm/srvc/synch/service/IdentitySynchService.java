package org.openiam.idm.srvc.synch.service;


import org.openiam.idm.srvc.synch.dto.SynchConfig;

import java.util.*;

/**
 * Interface for  <code>IdentitySynchService</code>. All synchronization activities are managed through 
 * this service. 
 */
public interface IdentitySynchService {

	List<SynchConfig> getAllConfig();
	

}