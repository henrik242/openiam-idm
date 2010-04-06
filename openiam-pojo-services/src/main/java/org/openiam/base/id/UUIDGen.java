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
package org.openiam.base.id;

import java.util.UUID;

/**
 * Generates a UUID
 * @author suneet
 *
 */
public class UUIDGen {

	public static String getUUID() {
		
		String uuidStr = UUID.randomUUID().toString();
		return  uuidStr.replaceAll("-", "");
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("2rig uuid=" + UUIDGen.getUUID());
	}
}
