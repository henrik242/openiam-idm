package org.openiam.idm.srvc.recon.dto;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReconciliationResult", 
		propOrder = { "reconResultId",
		"reconConfigId",
		"executionStartDate", 
		"executeionEndDate",
		"mode"})
public class ReconciliationResult implements java.io.Serializable {

	private String reconResultId;
	private String reconConfigId;
	private Date executionStartDate;
	private Date executionEndDate;
	private String mode;

	public ReconciliationResult() {
	}

	public ReconciliationResult(String reconResultId) {
		this.reconResultId = reconResultId;
	}

	public ReconciliationResult(String reconResultId, String reconConfigId,
			Date executionStartDate, Date executionEndDate, String mode) {
		this.reconResultId = reconResultId;
		this.reconConfigId = reconConfigId;
		this.executionStartDate = executionStartDate;
		this.executionEndDate = executionEndDate;
		this.mode = mode;
	}

	public String getReconResultId() {
		return this.reconResultId;
	}

	public void setReconResultId(String reconResultId) {
		this.reconResultId = reconResultId;
	}

	public String getReconConfigId() {
		return this.reconConfigId;
	}

	public void setReconConfigId(String reconConfigId) {
		this.reconConfigId = reconConfigId;
	}

	public Date getExecutionStartDate() {
		return this.executionStartDate;
	}

	public void setExecutionStartDate(Date executionStartDate) {
		this.executionStartDate = executionStartDate;
	}

	public Date getExecutionEndDate() {
		return this.executionEndDate;
	}

	public void setExecutionEndDate(Date executionEndDate) {
		this.executionEndDate = executionEndDate;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
