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
package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;

/**
 * @author suneet
 *
 */
public interface ResourceApproverDAO {

	public abstract void setSessionFactory(SessionFactory session);

	public abstract void add(ApproverAssociation transientInstance);

	public abstract void remove(ApproverAssociation persistentInstance);

	public abstract ApproverAssociation update(ApproverAssociation detachedInstance);

	public abstract ApproverAssociation findById(java.lang.String id);
	
	List<ApproverAssociation> findApproversByResource(String managedSysId);
	List<ApproverAssociation> findApproversByAction(String managedSysId, String action, int level);

}