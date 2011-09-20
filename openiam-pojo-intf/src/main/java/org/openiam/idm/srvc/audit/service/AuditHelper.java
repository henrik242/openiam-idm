package org.openiam.idm.srvc.audit.service;

import java.util.List;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;


public interface AuditHelper {
    IdmAuditLog logEvent(IdmAuditLog log);

    void logEventListEvent(List<IdmAuditLog> logList);

    IdmAuditLog addLog(String action, String domainId, String principal,
                       String srcSystem, String userId, String targetSystem, String objectType,
                       String objectId, String objectName,
                       String actionStatus, String linkedLogId, String attrName, String attrValue,
                       String requestId, String reason, String sessionId,
                       String reasonDetail);

    IdmAuditLogDataService getAuditDataService();

    void setAuditDataService(IdmAuditLogDataService auditDataService);

    IdmAuditLog createLogObject(String action,String domainId, String principal,
			String srcSystem, String userId, String targetSystem, String objectType,
			String objectId, String objectName,
			String actionStatus, String linkedLogId, String attrName, String attrValue,
			String requestId, String reason, String sessionId,
            String reasonDetail,
            String resourceName) ;

    public void persistLogList(List<IdmAuditLog> logList, String requestId, String sessionId);

}
