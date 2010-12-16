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
package org.openiam.idm.srvc.batch.service;

import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.batch.dto.BatchTask;

/**
 * Implementation for BatchDataService that will allow you to access and manage batch tasks.
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.batch.service.BatchDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/batch/service", 
		portName = "BatchDataWebServicePort", 
		serviceName = "BatchDataWebService")
public class BatchDataServiceImpl implements BatchDataService {

	BatchConfigDAO  batchDao;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchDataService#getAllTasks()
	 */
	public List<BatchTask> getAllTasks() {
		return batchDao.findAllBatchTasks();
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchDataService#getAllTasksByFrequency(java.lang.String)
	 */
	public List<BatchTask> getAllTasksByFrequency(String frequency) {
		if (frequency == null) {
			throw new IllegalArgumentException("Frequency is null");
		}
		return batchDao.findBatchTasksByFrequency(frequency);
	}
	
	public void updateTask(BatchTask task) {
		if (task == null) {
			throw new IllegalArgumentException("task is null");
		}
		batchDao.update(task);
	}

	public BatchConfigDAO getBatchDao() {
		return batchDao;
	}

	public void setBatchDao(BatchConfigDAO batchDao) {
		this.batchDao = batchDao;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchDataService#addBatchTask(org.openiam.idm.srvc.batch.dto.BatchTask)
	 */
	public BatchTask addBatchTask(BatchTask task) {
		batchDao.add(task);
		
		return task;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchDataService#getBatchTask(java.lang.String)
	 */
	public BatchTask getBatchTask(String taskId) {
		
		return batchDao.findById(taskId);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchDataService#removeBatchTask(java.lang.String)
	 */
	public void removeBatchTask(String taskId) {
		BatchTask task = batchDao.findById(taskId);
		batchDao.remove(task);
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchDataService#upateBatchTask(org.openiam.idm.srvc.batch.dto.BatchTask)
	 */
	public BatchTask upateBatchTask(BatchTask task) {
		batchDao.update(task);
		return task;
	}

	public BatchTask getTaskByName(String taskName) {
		return batchDao.findByName(taskName);
	}

}
