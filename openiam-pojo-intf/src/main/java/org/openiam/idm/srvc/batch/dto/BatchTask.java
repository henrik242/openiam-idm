package org.openiam.idm.srvc.batch.dto;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * BatchConfig represents configuration information for a batch task.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchConfig", propOrder = {
    "taskId",
    "taskName",
    "enabled",
    "frequency",
    "frequencyUnitOfMeasure",
    "lastExecTime",
    "taskUrl",
    "status",
    "param1",
    "param2",
    "param3",
    "param4",
    "executionOrder"
})
public class BatchTask implements java.io.Serializable {

	private String taskId;
	private String taskName;
	private Integer frequency;
	private Integer enabled;
	private String frequencyUnitOfMeasure;
    @XmlSchemaType(name = "dateTime")
	private Date lastExecTime;
    private String taskUrl;
    private String status;
    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private Integer executionOrder = new Integer(1);
    
	public BatchTask() {
	}

	public BatchTask(String taskId) {
		this.taskId = taskId;
	}

	public BatchTask(String taskId, String taskName, Integer frequency,
			String frequencyUnitOfMeasure, Date lastExecTime) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.frequency = frequency;
		this.frequencyUnitOfMeasure = frequencyUnitOfMeasure;
		this.lastExecTime = lastExecTime;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyUnitOfMeasure() {
		return this.frequencyUnitOfMeasure;
	}

	public void setFrequencyUnitOfMeasure(String frequencyUnitOfMeasure) {
		this.frequencyUnitOfMeasure = frequencyUnitOfMeasure;
	}

	public Date getLastExecTime() {
		return this.lastExecTime;
	}

	public void setLastExecTime(Date lastExecTime) {
		this.lastExecTime = lastExecTime;
	}

	public String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public Integer getExecutionOrder() {
		return executionOrder;
	}

	public void setExecutionOrder(Integer executionOrder) {
		this.executionOrder = executionOrder;
	}

}
