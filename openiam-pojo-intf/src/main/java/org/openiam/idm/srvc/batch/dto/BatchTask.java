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
    "frequency",
    "frequencyUnitOfMeasure",
    "lastExecTime",
    "taskUrl"
})
public class BatchTask implements java.io.Serializable {

	private String taskId;
	private String taskName;
	private Integer frequency;
	private String frequencyUnitOfMeasure;
    @XmlSchemaType(name = "dateTime")
	private Date lastExecTime;
    private String taskUrl;

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

}
