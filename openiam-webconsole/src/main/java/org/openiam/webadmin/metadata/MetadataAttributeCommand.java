package org.openiam.webadmin.metadata;
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


import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Command object for the ManagedSystemList 
 * @author suneet
 *
 */
public class MetadataAttributeCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5730162333111454215L;
	
	private MetadataElement[] elementAry;
	
	private MetadataElement element = new MetadataElement();
	

	public MetadataAttributeCommand() {
    	
    }
	
	public MetadataAttributeCommand(MetadataElement[] elementAry) {
		super();
		this.elementAry = elementAry;
	}


	
	


	public MetadataElement[] getElementAry() {
		return elementAry;
	}


	public void setElementAry(MetadataElement[] elementAry) {
		this.elementAry = elementAry;
	}

	public MetadataElement getElement() {
		return element;
	}

	public void setElement(MetadataElement element) {
		this.element = element;
	}





	

}
