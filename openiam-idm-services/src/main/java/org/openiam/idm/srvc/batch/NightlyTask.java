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
package org.openiam.idm.srvc.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.batch.dto.BatchTask;
import org.openiam.idm.srvc.batch.service.BatchDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Job bean that is called by quartz to kick off the nightly tasks.
 * @author suneet
 *
 */
public class NightlyTask implements ApplicationContextAware {
	
	
	private static final Log log = LogFactory.getLog(NightlyTask.class);
	
	protected LoginDataWebService loginManager;
	protected PolicyDataService policyDataService;
	protected BatchDataService batchService;
	protected String scriptEngine;
	protected AuditHelper auditHelper;

	
	// used to inject the application context into the groovy scripts
	public static ApplicationContext ac;
	

	
	public void execute() {
		log.debug("NightlyBatchJob called.");
		
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		bindingMap.put("context", ac);	
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
			return;

		}
		
		// get the list of domains
		List<BatchTask> taskList = batchService.getAllTasksByFrequency("NIGHTLY");
		log.debug("-Tasklist=" + taskList);
		
		if (taskList != null) {
			for (BatchTask task : taskList) {
				log.debug("Executing task:" + task.getTaskName());
				try {
					if (task.getEnabled() != 0) {
						log.debug("Executing task:" + task.getTaskName());
						if (task.getLastExecTime() == null) {
							task.setLastExecTime(new Date(System.currentTimeMillis()));
						}
				
						bindingMap.put("lastExecTime", task.getLastExecTime());
						
						Integer output = (Integer)se.execute(bindingMap, task.getTaskUrl());
						if (output.intValue() == 0 ) {
							auditHelper.addLog(task.getTaskName(), null,	null,
									"IDM BATCH TASK", null, "0", "DAILY BATCH", task.getTaskId(), 
									null,   "FAIL", null,  null, 
									null, null, null, null, null);
						}else {
							auditHelper.addLog(task.getTaskName(), null,	null,
									"IDM BATCH TASK", null, "0", "DAILY BATCH", task.getTaskId(), 
									null,   "SUCCESS", null,  null, 
									null, null, null, null, null);
						}
					}
				}catch(Exception e) {
					log.error(e);
				}finally {
					if (task.getEnabled() != 0) {
						task.setLastExecTime(new Date(System.currentTimeMillis()));
						batchService.updateTask(task);
					}
				}
				
			}
		}

		
	}

	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException  {
        ac = applicationContext;
	}
	

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}


	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public BatchDataService getBatchService() {
		return batchService;
	}


	public void setBatchService(BatchDataService batchService) {
		this.batchService = batchService;
	}


	public String getScriptEngine() {
		return scriptEngine;
	}


	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}


	public AuditHelper getAuditHelper() {
		return auditHelper;
	}


	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}




}


