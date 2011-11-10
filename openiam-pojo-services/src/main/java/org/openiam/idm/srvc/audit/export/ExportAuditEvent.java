package org.openiam.idm.srvc.audit.export;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;

/**
 * Interface to export Audit Events to external systems
 * User: suneetshah
 * Date: 9/18/11
 * Time: 10:45 PM
 */
public interface ExportAuditEvent {

    void event(IdmAuditLog log);
    boolean isAlive();
}
