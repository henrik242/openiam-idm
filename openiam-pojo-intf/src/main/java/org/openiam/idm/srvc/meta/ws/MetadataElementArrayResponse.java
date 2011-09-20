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
package org.openiam.idm.srvc.meta.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Response object for a web service operation that returns a list of group objects
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetadataElementArrayResponse", propOrder = {
    "metadataElementAry"
})
public class MetadataElementArrayResponse extends Response{

	MetadataElement[] metadataElementAry;
	
	public MetadataElementArrayResponse() {
		super();
	}

	public MetadataElementArrayResponse(ResponseStatus s) {
		super(s);
	}

	public MetadataElement[] getMetadataElementAry() {
		return metadataElementAry;
	}

	public void setMetadataElementAry(MetadataElement[] metadataElementAry) {
		this.metadataElementAry = metadataElementAry;
	}







	
	
}
