package org.openiam.idm.srvc.audit.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openiam.idm.srvc.audit.dto.*;
import org.openiam.exception.data.*;




/**
 * Implementation class for <code>IdmAuditLogDataService</code>. All audit logging activities 
 * persisted through this service.
 */		
public class IdmAuditLogDataServiceImpl implements IdmAuditLogDataService {

	IdmAuditLogDAO auditDao;

	public IdmAuditLogDataServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.audit.service.IdmAuditLogDataService#addLog(org.openiam.idm.srvc.audit.dto.IdmAuditLog)
	 */
	public void addLog(IdmAuditLog log) {
		auditDao.add(log);
	}

	public Collection getCompleteLog() {
		return (Collection) auditDao.findAll();
	}
	
	public Collection getPasswordChangeLog() {
		return auditDao.findPasswordEvents();
	}
	
	/**
	 * Returns a collection of audit log entries based on the search parameters.
	 * @param search
	 * @return
	 */
	public Collection search(SearchAudit search) {
		return auditDao.search(search);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.audit.service.IdmAuditLogDataService#getAuditDao()
	 */
	public IdmAuditLogDAO getAuditDao() {
		return auditDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.audit.service.IdmAuditLogDataService#setAuditDao(org.openiam.idm.srvc.audit.service.IdmAuditLogDAO)
	 */

	public void setAuditDao(IdmAuditLogDAO auditDao) {
		this.auditDao = auditDao;
	}
}
