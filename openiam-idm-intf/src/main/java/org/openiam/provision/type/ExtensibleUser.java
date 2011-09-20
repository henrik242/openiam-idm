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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * User object that is passed to the connector service when provisioning a user.
 * @author suneet
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleUser", propOrder = {
    "address",
    "phone",
    "email",
    "group",
    "role"
})
public class ExtensibleUser extends ExtensibleObject {

	protected List<ExtensibleAddress> address = new ArrayList<ExtensibleAddress>();
	protected List<ExtensiblePhone> phone = new ArrayList<ExtensiblePhone>();
	protected List<ExtensibleEmailAddress> email = new ArrayList<ExtensibleEmailAddress>();
	protected List<ExtensibleGroup> group = new ArrayList<ExtensibleGroup>();
	protected List<ExtensibleRole> role = new ArrayList<ExtensibleRole>();


	

	public List<ExtensibleGroup> getGroup() {
		return group;
	}
	public void setGroup(List<ExtensibleGroup> group) {
		this.group = group;
	}
	public List<ExtensibleRole> getRole() {
		return role;
	}
	public void setRole(List<ExtensibleRole> role) {
		this.role = role;
	}
	public List<ExtensibleAddress> getAddress() {
		return address;
	}
	public void setAddress(List<ExtensibleAddress> address) {
		this.address = address;
	}
	public List<ExtensiblePhone> getPhone() {
		return phone;
	}
	public void setPhone(List<ExtensiblePhone> phone) {
		this.phone = phone;
	}
	public List<ExtensibleEmailAddress> getEmail() {
		return email;
	}
	public void setEmail(List<ExtensibleEmailAddress> email) {
		this.email = email;
	}

    @Override
    public String toString() {
        return "ExtensibleUser{" +
                "address=" + address +
                ", phone=" + phone +
                ", email=" + email +
                ", group=" + group +
                ", role=" + role +
                '}';
    }
}
