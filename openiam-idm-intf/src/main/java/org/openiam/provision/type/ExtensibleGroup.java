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
import org.openiam.idm.srvc.grp.dto.Group;

/**
 * Group object that is passed to the connector service when provisioning a group.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleGroup")
public class ExtensibleGroup extends ExtensibleObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423217166073136606L;
	protected Group group;
	
	public ExtensibleGroup() {
		
	}
	
	public ExtensibleGroup(Group grp) {
		group = grp;
		if (grp.getOperation() == AttributeOperationEnum.DELETE) {
			operation = grp.getOperation().getValue();
		}else {
			operation = 0;
		}
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}

    @Override
    public String toString() {
        return "ExtensibleGroup{" +
                "group=" + group +
                '}';
    }
}
