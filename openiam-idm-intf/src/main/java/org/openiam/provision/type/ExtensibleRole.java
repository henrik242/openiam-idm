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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.role.dto.Role;

/**
 * Role object that is passed to the connector service when provisioning a role.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleRole")
public class ExtensibleRole extends ExtensibleObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6938373330651437873L;
	
	protected Role role;
	
	public ExtensibleRole() {
		
	}
	
	public ExtensibleRole(Role rl) {
		role = rl;
		if (rl.getOperation() == AttributeOperationEnum.DELETE) {
			operation = rl.getOperation().getValue();
		}else {
			operation = 0;
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	
}
