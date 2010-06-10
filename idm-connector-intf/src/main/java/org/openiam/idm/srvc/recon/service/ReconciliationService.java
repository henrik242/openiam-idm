package org.openiam.idm.srvc.recon.service;

import org.openiam.idm.srvc.recon.dto.*;
import java.util.List;

/**
 * Interface for  <code>ReconciliationService</code>. All reconciliation activities are managed through 
 * this service. 
 */
public interface ReconciliationService {

	List<ReconciliationConfig> getAllConfig();
	

}