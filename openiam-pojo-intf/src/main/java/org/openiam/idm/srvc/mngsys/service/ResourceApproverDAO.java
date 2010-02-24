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
import org.openiam.idm.srvc.mngsys.dto.ResourceApprover;

/**
 * @author suneet
 *
 */
public interface ResourceApproverDAO {

	public abstract void setSessionFactory(SessionFactory session);

	public abstract void add(ResourceApprover transientInstance);

	public abstract void remove(ResourceApprover persistentInstance);

	public abstract ResourceApprover update(ResourceApprover detachedInstance);

	public abstract ResourceApprover findById(java.lang.String id);
	
	List<ResourceApprover> findApproversByResource(String managedSysId);
	List<ResourceApprover> findApproversByAction(String managedSysId, String action, int level);

}