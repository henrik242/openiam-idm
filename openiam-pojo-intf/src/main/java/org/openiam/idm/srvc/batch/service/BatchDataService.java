package org.openiam.idm.srvc.batch.service;

import java.util.List;
import java.util.Set;

import org.openiam.idm.srvc.batch.dto.BatchTask;
import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.cat.dto.CategoryLanguage;
import org.openiam.idm.srvc.cat.dto.CategoryLanguageId;
/**
 * Service for accessing and managing batch oriented tasks. 
 * @author suneet shah
 *
 */
public interface BatchDataService {

	/**
	 * Returns a list of all batch tasks in the system
	 * @param cat
	 */
	public List<BatchTask> getAllTasks();

	/**
	 * Returns a list of all batch tasks in the system by their frequency - DAILY, MINUTE, HOUR
	 * @param cat
	 */
	public List<BatchTask> getAllTasksByFrequency(String frequency);
	
	public void updateTask(BatchTask task);
	
}
