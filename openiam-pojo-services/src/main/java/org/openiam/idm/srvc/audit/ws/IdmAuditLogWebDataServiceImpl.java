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
package org.openiam.idm.srvc.audit.ws;

import java.util.List;

import javax.jws.WebService;

import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/audit/service",
		portName = "AuditWebServicePort",
		serviceName = "AuditService")
public class IdmAuditLogWebDataServiceImpl implements IdmAuditLogWebDataService {

	IdmAuditLogDataService auditDataService;
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService#addLog(org.openiam.idm.srvc.audit.dto.IdmAuditLog)
	 */
	public IdmAuditLogResponse addLog(IdmAuditLog log) {
		IdmAuditLogResponse resp = new IdmAuditLogResponse(ResponseStatus.SUCCESS);
		auditDataService.addLog(log); 
		if (log.getLogId() != null) {
			resp.setLog(log);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService#getCompleteLog()
	 */
	public IdmAuditLogListResponse getCompleteLog() {
		IdmAuditLogListResponse resp = new IdmAuditLogListResponse(ResponseStatus.SUCCESS);
		List<IdmAuditLog> logList = auditDataService.getCompleteLog(); 
		if (logList != null) {
			resp.setLogList(logList);;
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService#getPasswordChangeLog()
	 */
	public IdmAuditLogListResponse getPasswordChangeLog() {
		IdmAuditLogListResponse resp = new IdmAuditLogListResponse(ResponseStatus.SUCCESS);
		List<IdmAuditLog> logList = auditDataService.getPasswordChangeLog(); 
		if (logList != null) {
			resp.setLogList(logList);;
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService#search(org.openiam.idm.srvc.audit.dto.SearchAudit)
	 */
	public IdmAuditLogListResponse search(SearchAudit search) {
		IdmAuditLogListResponse resp = new IdmAuditLogListResponse(ResponseStatus.SUCCESS);
		List<IdmAuditLog> logList = auditDataService.search(search); 
		if (logList != null) {
			resp.setLogList(logList);;
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		return resp;
	}

	public IdmAuditLogDataService getAuditDataService() {
		return auditDataService;
	}

	public void setAuditDataService(IdmAuditLogDataService auditDataService) {
		this.auditDataService = auditDataService;
	}

}
