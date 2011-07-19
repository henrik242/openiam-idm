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
package org.openiam.idm.srvc.recon.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.recon.dto.ReconciliationResponse;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.LookupUserResponse;
import  org.openiam.provision.service.ProvisionService;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;

/**
 * @author suneet
 *
 */
public class ReconciliationServiceImpl implements ReconciliationService {

	protected ReconciliationSituationDAO reconSituationDao;
	protected ReconciliationResultDAO reconResultDao;
	protected ReconciliationConfigDAO reconConfigDao;
	protected ReconciliationResultDAO reconResultDetailDao;
    protected MuleContext muleContext;
    protected LoginDataService loginManager;
    protected  ProvisionService provisionService;
    protected ResourceDataService resourceDataService;


    private static final Log log = LogFactory.getLog(ReconciliationServiceImpl.class);

    public ReconciliationConfig addConfig(ReconciliationConfig config) {
        if (config == null) {
			throw new IllegalArgumentException("config parameter is null");
		}
		return reconConfigDao.add(config);


    }

    public ReconciliationConfig updateConfig(ReconciliationConfig config) {
        if (config == null) {
                    throw new IllegalArgumentException("config parameter is null");
        }
        return reconConfigDao.update(config);


    }

    public void removeConfigByResourceId(String resourceId) {
        if (resourceId == null) {
                    throw new IllegalArgumentException("resourceId parameter is null");
        }
        reconConfigDao.removeByResourceId(resourceId);

    }

    public void removeConfig(String configId) {
        if (configId == null) {
                    throw new IllegalArgumentException("configId parameter is null");
        }
        ReconciliationConfig config =  reconConfigDao.findById(configId);
        reconConfigDao.remove(config);

    }

    public ReconciliationConfig getConfigByResource(String resourceId) {
    if (resourceId == null) {
                    throw new IllegalArgumentException("resourceId parameter is null");
     }
     return reconConfigDao.findByResourceId(resourceId);

    }

    public ReconciliationSituationDAO getReconSituationDao() {
		return reconSituationDao;
	}


	public void setReconSituationDao(ReconciliationSituationDAO reconSituationDao) {
		this.reconSituationDao = reconSituationDao;
	}


	public ReconciliationResultDAO getReconResultDao() {
		return reconResultDao;
	}


	public void setReconResultDao(ReconciliationResultDAO reconResultDao) {
		this.reconResultDao = reconResultDao;
	}


	public ReconciliationConfigDAO getReconConfigDao() {
		return reconConfigDao;
	}


	public void setReconConfigDao(ReconciliationConfigDAO reconConfigDao) {
		this.reconConfigDao = reconConfigDao;
	}


	public ReconciliationResultDAO getReconResultDetailDao() {
		return reconResultDetailDao;
	}


	public void setReconResultDetailDao(ReconciliationResultDAO reconResultDetailDao) {
		this.reconResultDetailDao = reconResultDetailDao;
	}

    public ReconciliationConfig getConfigById(String configId) {
       if (configId == null) {
                    throw new IllegalArgumentException("configId parameter is null");
        }
        return reconConfigDao.findById(configId);

    }

    public void setMuleContext(MuleContext ctx) {

       muleContext = ctx;
    }

    public ReconciliationResponse startReconciliation(ReconciliationConfig config) {
        try {
            log.debug("Reconciliation started for configId=" + config.getReconConfigId() + " - resource=" + config.getResourceId() );

            Resource res = resourceDataService.getResource(config.getResourceId());
            String managedSysId =  res.getManagedSysId();

            log.debug("ManagedSysId = " + managedSysId);
            log.debug("Getting identities for managedSys");

            List<Login> principalList =  loginManager.getAllLoginByManagedSys(managedSysId);
            if (principalList == null || principalList.isEmpty()) {
                log.debug("No identities found for managedSysId in IDM repository");
                ReconciliationResponse resp = new ReconciliationResponse(ResponseStatus.SUCCESS);
                return resp;
            }
            for ( Login l  : principalList ) {
                String principal = l.getId().getLogin();
                log.debug("looking up identity in resource: " + principal);

                LookupUserResponse lookupResp =  provisionService.getTargetSystemUser(principal, managedSysId);

                log.debug("Lookup status for " + principal + " =" +  lookupResp.getStatus());

                if (lookupResp.getStatus() == ResponseStatus.FAILURE) {
                    // delete iam identity
                    log.debug("Delete  user :" + l.getUserId());

                    ProvisionUser pUser = new ProvisionUser();
                    pUser.setUserId(l.getUserId());
                    pUser.setNotifyTargetSystems(false);
                    provisionService.deleteByUserId( pUser, UserStatusEnum.DELETED,"3000");
                }


            }


        /*}catch( ClassNotFoundException cnfe) {
			log.error(cnfe);
			ReconciliationResponse resp = new ReconciliationResponse(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
			resp.setErrorText(cnfe.getMessage());
			return resp;
			*/
		}catch(Exception e) {
			log.error(e);
			ReconciliationResponse resp = new ReconciliationResponse(ResponseStatus.FAILURE);
			resp.setErrorText(e.getMessage());
			return resp;
		}
        ReconciliationResponse resp = new ReconciliationResponse(ResponseStatus.SUCCESS);
        return resp;

    }

    public LoginDataService getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginDataService loginManager) {
        this.loginManager = loginManager;
    }

    public ProvisionService getProvisionService() {
        return provisionService;
    }

    public void setProvisionService(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }
}
