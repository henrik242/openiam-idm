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
package org.openiam.webadmin.res;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import java.util.*;

/**
 * Command object for ManagedSystemConnection
 * @author suneet
 *
 */
public class ResourceDetailCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8987849239297542982L;
	Resource resource;
	Set<ResourceProp> resourceProp;
	ManagedSys[] managedSysAry;

	public ResourceDetailCommand() {

	}
	
	
	public String formatDate(Date dt) {
		if (dt == null) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		return fmt.format(dt);
	}


	public Resource getResource() {
		return resource;
	}


	public void setResource(Resource resource) {
		this.resource = resource;
	}


	public Set<ResourceProp> getResourceProp() {
		return resourceProp;
	}


	public void setResourceProp(Set<ResourceProp> resourceProp) {
		this.resourceProp = resourceProp;
	}


	public ManagedSys[] getManagedSysAry() {
		return managedSysAry;
	}


	public void setManagedSysAry(ManagedSys[] managedSysAry) {
		this.managedSysAry = managedSysAry;
	}





	
}
