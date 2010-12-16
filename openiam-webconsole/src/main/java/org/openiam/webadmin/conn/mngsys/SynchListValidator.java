package org.openiam.webadmin.conn.mngsys;
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


import java.util.List;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


/**
 * Validation class for the Managed System list.
 * @author suneet
 *
 */
public class SynchListValidator implements Validator {

	

	public boolean supports(Class cls) {
		 return ManagedSysListCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		ManagedSysListCommand listCommand =  (ManagedSysListCommand) cmd;

		if (listCommand.getDomainId() != null &&
			listCommand.getDomainId().equals("-") ) {
			err.rejectValue("domainId", "required");
		}
		

		
		
	}


}
