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
package org.openiam.spml2.spi.ad;

/**
 * Constants that are used by Active Directory
 * @author suneet
 *
 */
public enum ActiveDirectoryEnum {

	
	UF_ACCOUNTDISABLE  (0x0002),
	UF_PASSWD_NOTREQD  (0x0020),
	UF_PASSWD_CANT_CHANGE (0x0040),
	UF_NORMAL_ACCOUNT (0x0200),
	UF_DONT_EXPIRE_PASSWD (0x10000),
	UF_PASSWORD_EXPIRED (0x800000);
	
	private int value;

	ActiveDirectoryEnum(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int val) {
		value = val;
	}
}
