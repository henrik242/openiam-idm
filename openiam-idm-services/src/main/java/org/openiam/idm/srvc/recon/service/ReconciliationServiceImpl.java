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

import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.synch.dto.SynchConfig;

/**
 * @author suneet
 *
 */
public class ReconciliationServiceImpl implements ReconciliationService {

	protected ReconciliationSituationDAO reconSituationDao;
	protected ReconciliationResultDAO reconResultDao;
	protected ReconciliationConfigDAO reconConfigDao;
	protected ReconciliationResultDAO reconResultDetailDao;
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.recon.service.ReconciliationService#getAllConfig()
	 */
	public List<ReconciliationConfig> getAllConfig() {
		// TODO Auto-generated method stub
		return null;
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



}
