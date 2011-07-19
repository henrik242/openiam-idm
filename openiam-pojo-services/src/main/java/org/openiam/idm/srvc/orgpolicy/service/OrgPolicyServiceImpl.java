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
package org.openiam.idm.srvc.orgpolicy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;

/**
 * @author suneet
 *
 */
public class OrgPolicyServiceImpl implements OrgPolicyService {

	OrgPolicyDAO acceptanceDao;
	OrgPolicyUserLogDAO userLogDao; 
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.service.SysMessageDeliveryService#addMessage(org.openiam.idm.srvc.msg.dto.SysMessageDelivery)
	 */
	public OrgPolicy addPolicyMessage(OrgPolicy policy) {
		if (policy == null) {
			throw new NullPointerException("policy is null");
		}
		
		return acceptanceDao.add(policy);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.service.SysMessageDeliveryService#getActiveMessagesForUser(java.lang.String)
	 */
	public List<OrgPolicy> getActiveOrgPoliciesForUser(String userId) {
		List<OrgPolicy> orgPolicyPendingList = new ArrayList<OrgPolicy>();
		if (userId == null) {
			throw new NullPointerException("userId is null");
		}
		List<OrgPolicy>  orgPolicyList = acceptanceDao.findAll();
		List<OrgPolicyUserLog> userLogList = userLogDao.findLogForUser(userId);
		
		// determine what the user has not processes
		for (OrgPolicy op : orgPolicyList) {
			if (!userSignedOff(op, userLogList)) {
				orgPolicyPendingList.add(op);
			}
		}
		return orgPolicyPendingList;
	}
	
	private boolean userSignedOff( OrgPolicy op , List<OrgPolicyUserLog> userLogList) {
		for ( OrgPolicyUserLog log : userLogList) {
			if (log.getOrgPolicyId().equalsIgnoreCase(op.getOrgPolicyId())) {
				return true;
			}			
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.service.SysMessageDeliveryService#getMessageById(java.lang.String)
	 */
	public OrgPolicy getPolicyMessageById(String id) {
		if (id == null) {
			throw new NullPointerException("id is null");
		}
		
		return acceptanceDao.findById(id);	
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.service.SysMessageDeliveryService#removeMessage(org.openiam.idm.srvc.msg.dto.SysMessageDelivery)
	 */
	public void removePolicyMessage(String orgPolicyId) {
		if (orgPolicyId == null) {
			throw new NullPointerException("policyId is null");
		}
		OrgPolicy policy = new OrgPolicy();
		policy.setOrgPolicyId(orgPolicyId);
		
		acceptanceDao.remove(policy);		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.service.SysMessageDeliveryService#updateMessage(org.openiam.idm.srvc.msg.dto.SysMessageDelivery)
	 */
	public OrgPolicy updatePolicyMessage(OrgPolicy msg) {
		if (msg == null) {
			throw new NullPointerException("msg is null");
		}
		
		return acceptanceDao.update(msg);
	}

	public OrgPolicyDAO getAcceptanceDAO() {
		return acceptanceDao;
	}

	public void setAcceptanceDAO(OrgPolicyDAO acceptanceDAO) {
		this.acceptanceDao = acceptanceDAO;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.orgpolicy.service.OrgPolicyAcceptanceService#getAllPolicyMessages()
	 */
	public List<OrgPolicy> getAllPolicyMessages() {
		return acceptanceDao.findAll();
	}


	public List<OrgPolicyUserLog> getLogEntryForUser(String userId) {
		if (userId == null) {
			throw new NullPointerException("userId is null");
		}
		return userLogDao.findLogForUser(userId);

	}
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.orgpolicy.service.OrgPolicyService#logUserResponse(java.lang.String, java.lang.String)
	 */
	public void logUserResponse(String orgPolicyId, String userId, String response) {
		OrgPolicyUserLog log = new OrgPolicyUserLog();
		log.setOrgPolicyId(orgPolicyId);
		log.setUserId(userId);
		log.setResponse(response);
		log.setTimeStamp(new Date(System.currentTimeMillis()));
		userLogDao.add(log);
		
	}
	
	
	public OrgPolicyDAO getAcceptanceDao() {
		return acceptanceDao;
	}

	public void setAcceptanceDao(OrgPolicyDAO acceptanceDao) {
		this.acceptanceDao = acceptanceDao;
	}

	public OrgPolicyUserLogDAO getUserLogDao() {
		return userLogDao;
	}

	public void setUserLogDao(OrgPolicyUserLogDAO userLogDao) {
		this.userLogDao = userLogDao;
	}







}
