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

import javax.jws.WebParam;
import javax.jws.WebService;

import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.recon.dto.ReconciliationResponse;
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
public class ReconciliationWebServiceImpl implements ReconciliationWebService, MuleContextAware {

	protected ReconciliationService reconService;
    protected MuleContext muleContext;

    public ReconciliationConfigResponse addConfig( ReconciliationConfig config) {
        ReconciliationConfigResponse response = new ReconciliationConfigResponse(ResponseStatus.SUCCESS);
        ReconciliationConfig cfg = reconService.addConfig(config);
        if (cfg == null || cfg.getReconConfigId() == null) {
            response.setStatus(ResponseStatus.FAILURE);
        }else {
            response.setConfig(cfg);
        }
        return response;


    }

    public ReconciliationConfigResponse updateConfig( ReconciliationConfig config) {
        ReconciliationConfigResponse response = new ReconciliationConfigResponse(ResponseStatus.SUCCESS);
        ReconciliationConfig cfg = reconService.updateConfig(config);
        if (cfg == null || cfg.getReconConfigId() == null) {
            response.setStatus(ResponseStatus.FAILURE);
        }else {
            response.setConfig(cfg);
        }
        return response;

    }

    public Response removeConfigByResourceId( String resourceId) {
        Response response = new Response(ResponseStatus.SUCCESS);
        reconService.removeConfigByResourceId(resourceId);
        return response;


    }

    public ReconciliationConfigResponse getConfigByResource( String resourceId) {
        ReconciliationConfigResponse response = new ReconciliationConfigResponse(ResponseStatus.SUCCESS);
        ReconciliationConfig cfg = reconService.getConfigByResource(resourceId);
        if (cfg == null || cfg.getReconConfigId() == null) {
            response.setStatus(ResponseStatus.FAILURE);
        }else {
            response.setConfig(cfg);
        }
        return response;

    }

    public Response removeConfig(String configId) {
        Response response = new Response(ResponseStatus.SUCCESS);
        reconService.removeConfig(configId);
        return response;
    }

    public ReconciliationService getReconService() {
		return reconService;
	}


	public void setReconService(ReconciliationService reconService) {
		this.reconService = reconService;
	}

    public void setMuleContext(MuleContext ctx) {

		muleContext = ctx;

	}

    public ReconciliationResponse startReconciliation( ReconciliationConfig config) {
       reconService.setMuleContext(muleContext);
       return reconService.startReconciliation(config);

    }

    public ReconciliationConfigResponse getConfigById( String configId) {
        ReconciliationConfigResponse response = new ReconciliationConfigResponse(ResponseStatus.SUCCESS);
                ReconciliationConfig cfg = reconService.getConfigById(configId);
                if (cfg == null || cfg.getReconConfigId() == null) {
                    response.setStatus(ResponseStatus.FAILURE);
                }else {
                    response.setConfig(cfg);
                }
                return response;

    }
}

