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
package org.openiam.idm.srvc.synch.ws;

import javax.jws.WebService;

import org.openiam.idm.srvc.synch.service.IdentitySynchService;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;
import org.openiam.idm.srvc.synch.ws.SynchConfigListResponse;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.synch.ws.IdentitySynchWebService", 
		targetNamespace = "http://www.openiam.org/service/synch", 
		portName = "IdentitySynchWebServicePort", 
		serviceName = "IdentitySynchWebService")
public class IdentitySynchWebServiceImpl implements IdentitySynchWebService {

	IdentitySynchService synchService;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.sync.ws.IdentitySynchWebService#getAllConfig()
	 */
	public SynchConfigListResponse getAllConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public IdentitySynchService getSynchService() {
		return synchService;
	}

	public void setSynchService(IdentitySynchService synchService) {
		this.synchService = synchService;
	}

}
