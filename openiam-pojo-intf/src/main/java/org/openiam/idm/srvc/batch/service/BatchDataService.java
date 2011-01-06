package org.openiam.idm.srvc.batch.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.idm.srvc.batch.dto.BatchTask;

/**
 * Service for accessing and managing batch oriented tasks. 
 * @author suneet shah
 *
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/batch/service",	name = "BatchDataWebService")
public interface BatchDataService {

	/**
	 * Returns a list of all batch tasks in the system
	 * @param cat
	 */
	@WebMethod
	public List<BatchTask> getAllTasks();

	/**
	 * Returns a list of all batch tasks in the system by their frequency - DAILY, MINUTE, HOUR
	 * @param cat
	 */
	public List<BatchTask> getAllTasksByFrequency(
			@WebParam(name = "frequency", targetNamespace = "")
			String frequency);
	
	@WebMethod
	public void updateTask(
			@WebParam(name="task", targetNamespace="")
			BatchTask task);
	
	@WebMethod
	public BatchTask getBatchTask(
			@WebParam(name = "taskId", targetNamespace = "")
			String taskId);
	@WebMethod
	BatchTask getTaskByName(
			@WebParam(name="taskName", targetNamespace="")
			String taskName);
	
	public void removeBatchTask(
			@WebParam(name = "taskID", targetNamespace = "")
			String taskId);
	public BatchTask addBatchTask(
			@WebParam(name = "task", targetNamespace = "")
			BatchTask task);
	public BatchTask upateBatchTask(
			@WebParam(name = "task", targetNamespace = "")
			BatchTask task);
	
}
