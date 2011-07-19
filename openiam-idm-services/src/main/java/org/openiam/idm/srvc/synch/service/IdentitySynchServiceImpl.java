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
package org.openiam.idm.srvc.synch.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.srcadapter.AdapterFactory;

/**
 * @author suneet
 *
 */
public class IdentitySynchServiceImpl implements IdentitySynchService {
    protected SynchConfigDAO synchConfigDao;
	protected SynchConfigDataMappingDAO synchConfigMappingDao;
	protected AdapterFactory adaptorFactory;
    protected MuleContext muleContext;


	
	private static final Log log = LogFactory.getLog(IdentitySynchServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.synch.service.IdentitySynchService#getAllConfig()
	 */
	public List<SynchConfig> getAllConfig() {
		List<SynchConfig> configList = synchConfigDao.findAllConfig();
		if ( configList != null && !configList.isEmpty()) {
			return configList;
		}
		return null;
	}

	
	public SynchConfig findById(java.lang.String id)  {
		if (id == null) {
			throw new IllegalArgumentException("id parameter is null");
		}
		
		return synchConfigDao.findById(id);
	}

	public SynchConfig addConfig(SynchConfig synchConfig) {
		if (synchConfig == null) {
			throw new IllegalArgumentException("synchConfig parameter is null");
		}
		return synchConfigDao.add(synchConfig);
		
	}

	public SynchConfig updateConfig(SynchConfig synchConfig) {
		if (synchConfig == null) {
			throw new IllegalArgumentException("synchConfig parameter is null");
		}
		return synchConfigDao.update(synchConfig);
				
	}

	public void removeConfig(String configId ) {
		if (configId == null) {
			throw new IllegalArgumentException("id parameter is null");
		}
		SynchConfig config = synchConfigDao.findById(configId);
		synchConfigDao.remove(config);
		
	}

	public SyncResponse startSynchronization(SynchConfig config) {
		log.debug("-startSynchronization CALLED.^^^^^^^^");
		try {
			SourceAdapter adapt = adaptorFactory.create(config);
            adapt.setMuleContext(muleContext);

			long newLastExecTime = System.currentTimeMillis();

			SyncResponse resp = adapt.startSynch(config);
			
			log.debug("SyncReponse updateTime value=" + resp.getLastRecordTime());
			
			if (resp.getLastRecordTime() == null) {
			
				synchConfigDao.updateExecTime(config.getSynchConfigId(), new Timestamp( newLastExecTime ));
			}else {
				synchConfigDao.updateExecTime(config.getSynchConfigId(), new Timestamp( resp.getLastRecordTime().getTime() ));
			}

            if (resp.getLastRecProcessed() != null) {

				synchConfigDao.updateLastRecProcessed(config.getSynchConfigId(),resp.getLastRecProcessed() );
			}


		log.debug("-startSynchronization COMPLETE.^^^^^^^^");
			
			return resp;
		}catch( ClassNotFoundException cnfe) {
			log.error(cnfe);
			SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
			resp.setErrorText(cnfe.getMessage());
			return resp;
		}catch(Exception e) {
			log.error(e);
			SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
			resp.setErrorText(e.getMessage());
			return resp;			
		}

	}

     public void setMuleContext(MuleContext ctx) {

        muleContext = ctx;
     }

	public SynchConfigDAO getSynchConfigDao() {
	    return synchConfigDao;
	}


	public void setSynchConfigDao(SynchConfigDAO synchConfigDao) {
		this.synchConfigDao = synchConfigDao;
	}


	public SynchConfigDataMappingDAO getSynchConfigMappingDao() {
		return synchConfigMappingDao;
	}


	public void setSynchConfigMappingDao(
			SynchConfigDataMappingDAO synchConfigMappingDao) {
		this.synchConfigMappingDao = synchConfigMappingDao;
	}


	public AdapterFactory getAdaptorFactory() {
		return adaptorFactory;
	}


	public void setAdaptorFactory(AdapterFactory adaptorFactory) {
		this.adaptorFactory = adaptorFactory;
	}




}
