package org.openiam.idm.srvc.audit.service;


import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.audit.dto.*;
import org.openiam.idm.srvc.audit.export.AuditEventHandlerFactory;
import org.openiam.idm.srvc.audit.export.ExportAuditEvent;
import org.openiam.util.encrypt.HashDigest;




/**
 * Implementation class for <code>IdmAuditLogDataService</code>. All audit logging activities 
 * persisted through this service.
 */		
public class IdmAuditLogDataServiceImpl implements IdmAuditLogDataService {

	IdmAuditLogDAO auditDao;
	HashDigest hash;

     private static final Log sysLog = LogFactory.getLog(IdmAuditLogDataServiceImpl.class);


	public IdmAuditLogDataServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.audit.service.IdmAuditLogDataService#addLog(org.openiam.idm.srvc.audit.dto.IdmAuditLog)
	 */
	public IdmAuditLog addLog(IdmAuditLog log) {
		// create a hash that can be used validate the logs integrity
		String str = log.getActionId() + 
					 log.getActionStatus() + 
					 log.getDomainId() + 
					 log.getPrincipal() + 
					 log.getUserId() + 
					 log.getObjectTypeId() + 
					 log.getObjectId() + 
					 log.getLinkedLogId() +
					 log.getRequestId() +
					 log.getActionDatetime();
		log.setLogHash( hash.HexEncodedHash(str) );
		auditDao.add(log);

        exportEvent(log);

		return log;
	}

    protected void exportEvent(IdmAuditLog log) {
        ExportAuditEvent eventHandler =  AuditEventHandlerFactory.createInstance();
        if (eventHandler == null) {
            return;
        }

        try {
            eventHandler.event(log);
        }catch (Exception e) {
           sysLog.error(e.toString());
        }

    }

	public List<IdmAuditLog>  getCompleteLog() {
		return auditDao.findAll();
	}
	
	public List<IdmAuditLog>  getPasswordChangeLog() {
		return auditDao.findPasswordEvents();
	}
	
	/**
	 * Returns a collection of audit log entries based on the search parameters.
	 * @param search
	 * @return
	 */
	public List<IdmAuditLog>  search(SearchAudit search) {
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

	public HashDigest getHash() {
		return hash;
	}

	public void setHash(HashDigest hash) {
		this.hash = hash;
	}
	
}
