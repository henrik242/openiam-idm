/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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
package org.openiam.webadmin.conn.mngsys;

import java.io.Serializable;
import java.util.Date;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.SysAttributeMapping;

/**
 * 
 * @author suneet
 *
 */
public class SysAttributeMapCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3139908525784150897L;

	private SysAttributeMapping[] mapAry = null;
	

	private String managedSysId;
	/* User, Group, Role */
	private String objectGroupType;

	public SysAttributeMapCommand() {
		
	}
	
	public String getManagedSysId() {
		return managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}
	
	public SysAttributeMapping[] getMapAry() {
		return mapAry;
	}

	public void setMapAry(SysAttributeMapping[] mapAry) {
		this.mapAry = mapAry;
	}

	public String getObjectGroupType() {
		return objectGroupType;
	}

	public void setObjectGroupType(String objectGroupType) {
		this.objectGroupType = objectGroupType;
	}
	
}
