package org.openiam.webadmin.metadata;
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


import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Command object for managing MetadataTypes 
 * @author suneet
 *
 */
public class MetadataTypeCommand implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780174689384407535L;
	
	
	private String metadataTypeId;
	private String description;
	private int active = 0;
	private int syncManagedSys = 0;
	String categoryId;
	

	public MetadataTypeCommand() {
    	
    }

	public MetadataTypeCommand(int active, String description,
			String metadataTypeId, int syncManagedSys) {
		super();
		this.active = active;
		this.description = description;
		this.metadataTypeId = metadataTypeId;
		this.syncManagedSys = syncManagedSys;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getMetadataTypeId() {
		return metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getSyncManagedSys() {
		return syncManagedSys;
	}

	public void setSyncManagedSys(int syncManagedSys) {
		this.syncManagedSys = syncManagedSys;
	}




	
	

}
