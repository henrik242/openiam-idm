package org.openiam.idm.srvc.recon.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReconciliationConfig", 
		propOrder = { "reconConfigId",
		"resourceId",
		"mode", 
		"frequency",
		"status", "attributeLevelCheck",
		"updateChangedAttribute" })
public class ReconciliationConfig implements java.io.Serializable {

	private String reconConfigId;
	private String resourceId;
	private String mode;
	private String frequency;
	private String status;
	private Integer attributeLevelCheck;
	private Integer updateChangedAttribute;

	public ReconciliationConfig() {
	}

	public ReconciliationConfig(String reconConfigId) {
		this.reconConfigId = reconConfigId;
	}

	public ReconciliationConfig(String reconConfigId, String resourceId,
			String mode, String frequency, String status,
			Integer attributeLevelCheck, Integer updateChangedAttribute) {
		this.reconConfigId = reconConfigId;
		this.resourceId = resourceId;
		this.mode = mode;
		this.frequency = frequency;
		this.status = status;
		this.attributeLevelCheck = attributeLevelCheck;
		this.updateChangedAttribute = updateChangedAttribute;
	}

	public String getReconConfigId() {
		return this.reconConfigId;
	}

	public void setReconConfigId(String reconConfigId) {
		this.reconConfigId = reconConfigId;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAttributeLevelCheck() {
		return this.attributeLevelCheck;
	}

	public void setAttributeLevelCheck(Integer attributeLevelCheck) {
		this.attributeLevelCheck = attributeLevelCheck;
	}

	public Integer getUpdateChangedAttribute() {
		return this.updateChangedAttribute;
	}

	public void setUpdateChangedAttribute(Integer updateChangedAttribute) {
		this.updateChangedAttribute = updateChangedAttribute;
	}

}
