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
package org.openiam.provision.type;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;


/**
 * Base object whose descendants will be used in Add, Update and Delete requests
 * @author suneet
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleObject", propOrder = {
    "objectId",
    "name",
    "operation",
    "attributes"
})
@XmlSeeAlso({
    ArrayList.class
})
public class ExtensibleObject implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6512595735853659295L;
	protected String objectId;
	protected String name;
	protected int operation;
	
	protected List<ExtensibleAttribute> attributes = new ArrayList<ExtensibleAttribute>();

	public ExtensibleObject() {
		operation = 0;
	}
	
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public List<ExtensibleAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ExtensibleAttribute> attributes) {
		this.attributes = attributes;
	}
	
}
