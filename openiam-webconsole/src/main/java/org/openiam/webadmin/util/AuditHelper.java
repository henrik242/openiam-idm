/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.webadmin.util;

import java.util.List;
import java.util.UUID;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.ws.AsynchIdmAuditLogWebService;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService;

/**
 * Helper class that other modules can use to simplify the audit logging process.
 * This class will validate the log against the audit policy and then log the event.
 * @author suneet
 *
 */
public class AuditHelper {
	
	IdmAuditLogWebDataService auditDataService;
	AsynchIdmAuditLogWebService asynchAuditDataService;
	/**
	 * Logs the event through the audit log service. Returns the logId.
	 * @param log
	 * @return
	 */
	public String logEvent(IdmAuditLog log) {
		auditDataService.addLog(log);
		return log.getLogId();
		
	}

	public void logEventListEvent(List<IdmAuditLog> logList) {
		if (logList  == null || logList.isEmpty()) {
			return ;
		}
		int ctr = 0;
		String linkedLogId = null;
		
		for ( IdmAuditLog log : logList) {
			if (linkedLogId != null) {
				log.setLinkedLogId(linkedLogId);
				log.setLinkSequence(ctr);
			}
			
			String logId = logEvent(log);
			if (ctr == 0 ) {
				linkedLogId = logId;
			}
			ctr++;
		}
		
	}
	
	/**
	 * Logs the event through the audit log service. Returns the logId.
	 * @param action
	 * @param domainId
	 * @param principal
	 * @param srcSystem
	 * @param userId
	 * @param targetSystem
	 * @param objectType
	 * @param objectId
	 * @param actionStatus
	 * @param linkedLogId
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public String addLog(String action,String domainId, String principal, 
			String srcSystem, String userId, String targetSystem, String objectType,  
			String objectId, String objectName,
			String actionStatus, String linkedLogId, String attrName, String attrValue,
			String requestId, String reason,
            String resourceName,String requestIP) {


		IdmAuditLog log = new IdmAuditLog();
		log.setObjectId(objectId);
		log.setObjectTypeId(objectType);
		log.setActionId(action);
		log.setActionStatus(actionStatus);
		log.setDomainId(domainId);
		log.setUserId(userId);
		log.setPrincipal(principal);
		log.setLinkedLogId(linkedLogId);
		log.setSrcSystemId(srcSystem);
		log.setTargetSystemId(targetSystem);
		log.setCustomAttrname1(attrName);
		log.setCustomAttrvalue1(attrValue);
		log.setRequestId(requestId);
		log.setReason(reason);
        log.setResourceName(resourceName);
        log.setHost(requestIP);
		
		return logEvent(log);
		
	}

    public IdmAuditLog addLog(String action,String requestorDomainId, String requestorPrincipal,
			String srcSystem, String userId, String targetSystem, String objectType,
			String objectId, String objectName,
			String actionStatus, String linkedLogId, String attrName, String attrValue,
			String requestId, String reason, String sessionId,
            String reasonDetail,
            String requestIP, String targetPrincipal, String targetUserDomain) {

		IdmAuditLog log = new IdmAuditLog();
		log.setObjectId(objectId);
		log.setObjectTypeId(objectType);
		log.setActionId(action);
		log.setActionStatus(actionStatus);
		log.setDomainId(requestorDomainId);
		log.setUserId(userId);
		log.setPrincipal(requestorPrincipal);
		log.setLinkedLogId(linkedLogId);
		log.setSrcSystemId(srcSystem);
		log.setTargetSystemId(targetSystem);
		log.setCustomAttrname1(attrName);
		log.setCustomAttrvalue1(attrValue);
		log.setRequestId(requestId);
		log.setReason(reason);
		log.setSessionId(sessionId);
        log.setReasonDetail(reasonDetail);
        log.setHost(requestIP);
        log.setCustomAttrname3("TARGET_IDENTITY");
        log.setCustomAttrvalue3(targetPrincipal);
        log.setCustomAttrname4("TARGET_DOMAIN");
        log.setCustomAttrvalue4(targetUserDomain);

		logEvent(log);
        return log;

	}
public void addLogAsync(String action,String domainId, String principal,
			String srcSystem, String userId, String targetSystem, String objectType,
			String objectId, String objectName,
			String actionStatus, String linkedLogId, String attrName, String attrValue,
			String requestId, String reason) {


       System.out.println("addLogAsync started..");

		IdmAuditLog log = new IdmAuditLog();
		log.setObjectId(objectId);
		log.setObjectTypeId(objectType);
		log.setActionId(action);
		log.setActionStatus(actionStatus);
		log.setDomainId(domainId);
		log.setUserId(userId);
		log.setPrincipal(principal);
		log.setLinkedLogId(linkedLogId);
		log.setSrcSystemId(srcSystem);
		log.setTargetSystemId(targetSystem);
		log.setCustomAttrname1(attrName);
		log.setCustomAttrvalue1(attrValue);

        if (requestId == null || requestId.length() == 0) {
            log.setRequestId(UUID.randomUUID().toString());
        }else {

		    log.setRequestId(requestId);
        }
		log.setReason(reason);

        asynchAuditDataService.createLog(log);


	}


	public IdmAuditLogWebDataService getAuditDataService() {
		return auditDataService;
	}

	public void setAuditDataService(IdmAuditLogWebDataService auditDataService) {
		this.auditDataService = auditDataService;
	}

    public AsynchIdmAuditLogWebService getAsynchAuditDataService() {
        return asynchAuditDataService;
    }

    public void setAsynchAuditDataService(AsynchIdmAuditLogWebService asynchAuditDataService) {
        this.asynchAuditDataService = asynchAuditDataService;
    }
}
