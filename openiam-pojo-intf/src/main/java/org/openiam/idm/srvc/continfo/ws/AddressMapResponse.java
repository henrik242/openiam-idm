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
package org.openiam.idm.srvc.continfo.ws;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.role.dto.Role;

/**
 * Response object for a web service operation that returns a role.
 * @author suneet
 *
 */
@XmlJavaTypeAdapter(org.openiam.idm.srvc.continfo.dto.AddressMapAdapter.class) 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressMapResponse", propOrder = {
    "addressMap"
})
public class AddressMapResponse extends Response{

	Map<String, org.openiam.idm.srvc.continfo.dto.Address> addressMap;

	public AddressMapResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressMapResponse(ResponseStatus s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public Map<String, org.openiam.idm.srvc.continfo.dto.Address> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(
			Map<String, org.openiam.idm.srvc.continfo.dto.Address> addressMap) {
		this.addressMap = addressMap;
	}




	
	
}
