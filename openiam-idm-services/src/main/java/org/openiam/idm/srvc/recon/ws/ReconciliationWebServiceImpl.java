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
package org.openiam.idm.srvc.recon.ws;

import javax.jws.WebService;

import org.openiam.idm.srvc.recon.service.ReconciliationService;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;
import org.openiam.idm.srvc.synch.ws.SynchConfigListResponse;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.recon.ws.ReconciliationWebService", 
		targetNamespace = "http://www.openiam.org/service/recon", 
		portName = "ReconciliationWebServicePort", 
		serviceName = "ReconciliationWebService")
public class ReconciliationWebServiceImpl implements ReconciliationWebService {

	ReconciliationService reconService;
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.recon.ws.ReconciliationWebService#getAllConfig()
	 */
	public ReconciliationConfigListResponse getAllConfig() {
		// TODO Auto-generated method stub
		return null;
	}


	public ReconciliationService getReconService() {
		return reconService;
	}


	public void setReconService(ReconciliationService reconService) {
		this.reconService = reconService;
	}

}
