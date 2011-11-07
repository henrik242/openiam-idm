package org.openiam.idm.srvc.audit.service;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import java.util.*;

/**
 * Interface for  <code>IdmAuditLogDataService</code>. All audit logging activities 
 * persisted through this service.
 */
public interface IdmAuditLogDataService {

	/**
	 * Creates a new audit log entry. The returned object contains the 
	 * @param log
	 * @return
	 */
	public IdmAuditLog addLog(IdmAuditLog log);
	public List<IdmAuditLog>  getCompleteLog();
	public List<IdmAuditLog>  getPasswordChangeLog();
	/**
	 * Returns a collection of audit log entries based on the search parameters.
	 * @param search
	 * @return
	 */
	public List<IdmAuditLog>  search(SearchAudit search);

    public void updateLog(IdmAuditLog log);


}