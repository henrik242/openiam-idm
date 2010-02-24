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
package org.openiam.idm.srvc.grp.ws;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;

/**
 * Response object for a web service operation that returns a Map of GroupAttribute objects.
 * @author suneet
 *
 */
@XmlJavaTypeAdapter(org.openiam.idm.srvc.grp.dto.GroupAttributeMapAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupAttrMapResponse", propOrder = {
    "groupAttrMap"
})
public class GroupAttrMapResponse extends Response{

	public GroupAttrMapResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupAttrMapResponse(ResponseStatus s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	Map<String,GroupAttribute> groupAttrMap;

	//@XmlJavaTypeAdapter(org.openiam.idm.srvc.grp.dto.GroupAttributeMapAdapter.class)
	public Map<String, GroupAttribute> getGroupAttrMap() {
		return groupAttrMap;
	}

	//@XmlJavaTypeAdapter(org.openiam.idm.srvc.grp.dto.GroupAttributeMapAdapter.class)
	public void setGroupAttrMap(Map<String, GroupAttribute> groupAttrMap) {
		this.groupAttrMap = groupAttrMap;
	}


	
	
}
