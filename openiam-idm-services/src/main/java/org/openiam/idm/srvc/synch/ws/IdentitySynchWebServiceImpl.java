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

import java.util.List;

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.msg.ws.SysMessageResponse;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
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

	protected IdentitySynchService synchService;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.sync.ws.IdentitySynchWebService#getAllConfig()
	 */
	public SynchConfigListResponse getAllConfig() {
		SynchConfigListResponse resp = new SynchConfigListResponse(ResponseStatus.SUCCESS);
		List<SynchConfig> configList = synchService.getAllConfig();
		if (configList == null || configList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setConfigList(configList);
		}
		return resp;
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.synch.ws.IdentitySynchWebService#addConfig(org.openiam.idm.srvc.synch.dto.SynchConfig)
	 */
	public SynchConfigResponse addConfig(SynchConfig synchConfig) {
		SynchConfigResponse resp = new SynchConfigResponse(ResponseStatus.SUCCESS);
		SynchConfig config = synchService.addConfig(synchConfig);
		if (config == null || config.getSynchConfigId()==null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setConfig(config);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.synch.ws.IdentitySynchWebService#findById(java.lang.String)
	 */
	public SynchConfigResponse findById(String id) {
		SynchConfigResponse resp = new SynchConfigResponse(ResponseStatus.SUCCESS);
		SynchConfig config = synchService.findById(id);
		if (config == null || config.getSynchConfigId()==null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setConfig(config);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.synch.ws.IdentitySynchWebService#removeConfig(java.lang.String)
	 */
	public Response removeConfig(String configId) {
		SysMessageResponse resp = new SysMessageResponse(ResponseStatus.SUCCESS);
		synchService.removeConfig(configId);
		return resp;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.synch.ws.IdentitySynchWebService#updateConfig(org.openiam.idm.srvc.synch.dto.SynchConfig)
	 */
	public SynchConfigResponse updateConfig(SynchConfig synchConfig) {
		SynchConfigResponse resp = new SynchConfigResponse(ResponseStatus.SUCCESS);
		SynchConfig config = synchService.updateConfig(synchConfig);
		if (config == null || config.getSynchConfigId()==null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setConfig(config);
		}
		return resp;
	}

	public IdentitySynchService getSynchService() {
		return synchService;
	}

	public void setSynchService(IdentitySynchService synchService) {
		this.synchService = synchService;
	}



	public SyncResponse startSynchronization(SynchConfig config) {
		return synchService.startSynchronization(config);
	}
	
}
