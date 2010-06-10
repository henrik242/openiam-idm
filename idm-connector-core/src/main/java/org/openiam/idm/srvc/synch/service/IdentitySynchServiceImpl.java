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

import java.util.List;

import org.openiam.idm.srvc.synch.dto.SynchConfig;

/**
 * @author suneet
 *
 */
public class IdentitySynchServiceImpl implements IdentitySynchService {
	SynchConfigDAO synchConfigDao;
	SynchConfigDataMappingDAO synchConfigMappingDao;
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.synch.service.IdentitySynchService#getAllConfig()
	 */
	public List<SynchConfig> getAllConfig() {
		// TODO Auto-generated method stub
		return null;
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

}
