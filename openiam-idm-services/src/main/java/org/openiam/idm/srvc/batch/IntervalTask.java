package org.openiam.idm.srvc.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openiam.base.id.UUIDGen;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.batch.dto.BatchTask;
import org.openiam.idm.srvc.batch.service.BatchDataService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Scheduled task that is called at set intervals by the schedular
 * @author suneet
 *
 */
public class IntervalTask  implements ApplicationContextAware  {

	private static final Log log = LogFactory.getLog(IntervalTask.class);
	protected BatchDataService batchService;
	
	protected LoginDataWebService loginManager;
	protected PolicyDataService policyDataService;
	protected String scriptEngine;
	protected AuditHelper auditHelper;
	
	public static ApplicationContext ac;
	
	public IntervalTask() {
		super();
	}

	public void execute(String frequencyMeasure) 
	{
		System.out.println("A-Task called. Frequency=" + frequencyMeasure);
		
		log.debug("IntervalTask called.");
		log.debug("-- Frequency=" + frequencyMeasure);
	
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
		List<BatchTask> taskList = batchService.getAllTasksByFrequency(frequencyMeasure);
		log.debug("-Tasklist=" + taskList);
		
		if (taskList != null) {
			for (BatchTask task : taskList) {
				String requestId = UUIDGen.getUUID();
				try {
					if (task.getEnabled() != 0) {
						auditHelper.addLog(task.getTaskName(), null,	null,
								"IDM BATCH TASK", null, "0", "BATCH", task.getTaskId(), 
								null,   "TASK STARTED", null,  null, 
								task.getParam1(), requestId, null, null, null);
						
						log.info("Executing task:" + task.getTaskName());
						if (task.getLastExecTime() == null) {
							task.setLastExecTime(new Date(System.currentTimeMillis()));
						}
						bindingMap.put("taskObj", task);
						bindingMap.put("lastExecTime", task.getLastExecTime());
						bindingMap.put("parentRequestId", requestId);
						
						Integer output = (Integer)se.execute(bindingMap, task.getTaskUrl());
						if (output.intValue() == 0 ) {
							 auditHelper.addLog(task.getTaskName(), null,	null,
									"IDM BATCH TASK", null, "0", "BATCH", task.getTaskId(), 
									null,   "FAIL", null,  null, 
									task.getParam1(), requestId, null, null, null);
						}else {
							auditHelper.addLog(task.getTaskName(), null,	null,
									"IDM BATCH TASK", null, "0", "BATCH", task.getTaskId(), 
									null,   "SUCCESS", null,  "USER_STATUS", 
									task.getParam1(), requestId, null, null, null);
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



	public BatchDataService getBatchService() {
		return batchService;
	}

	public void setBatchService(BatchDataService batchService) {
		this.batchService = batchService;
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

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ac = applicationContext;
		
	}
}
