package org.openiam.idm.srvc.synch.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SynchConfig", 
		propOrder = { "synchConfigId",
		"name",
		"status", 
		"synchSrc",
		"fileName",
		"managedSysId",
		"loadMatchOnly",
		"updateAttribute",
		"synchFrequency",
		"deleteRule",
		"processRule",
		"rejectRule",
		"transformationRule",
		"matchFieldName",
		"matchManagedSysId",
		"matchSrcFieldName"})
public class SynchConfig implements java.io.Serializable {

	private String synchConfigId;
	private String name;
	private String status;
	private String synchSrc;
	private String fileName;
	private String managedSysId;
	private Integer loadMatchOnly;
	private Integer updateAttribute;
	private String synchFrequency;
	private String deleteRule;
	private String processRule;
	private String rejectRule;
	private String transformationRule;
	private String matchFieldName;
	private String matchManagedSysId;
	private String matchSrcFieldName;

	public SynchConfig() {
	}

	public SynchConfig(String synchConfigId) {
		this.synchConfigId = synchConfigId;
	}

	public SynchConfig(String synchConfigId, String name, String status,
			String synchSrc, String fileName, String managedSysId,
			Integer loadMatchOnly, Integer updateAttribute,
			String synchFrequency, String deleteRule, String processRule,
			String rejectRule, String transformationRule,
			String matchFieldName, String matchSrcFieldName) {
		this.synchConfigId = synchConfigId;
		this.name = name;
		this.status = status;
		this.synchSrc = synchSrc;
		this.fileName = fileName;
		this.managedSysId = managedSysId;
		this.loadMatchOnly = loadMatchOnly;
		this.updateAttribute = updateAttribute;
		this.synchFrequency = synchFrequency;
		this.deleteRule = deleteRule;
		this.processRule = processRule;
		this.rejectRule = rejectRule;
		this.transformationRule = transformationRule;
		this.matchFieldName = matchFieldName;
		this.matchSrcFieldName = matchSrcFieldName;
	}

	public String getSynchConfigId() {
		return this.synchConfigId;
	}

	public void setSynchConfigId(String synchConfigId) {
		this.synchConfigId = synchConfigId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSynchSrc() {
		return this.synchSrc;
	}

	public void setSynchSrc(String synchSrc) {
		this.synchSrc = synchSrc;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getManagedSysId() {
		return this.managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public Integer getLoadMatchOnly() {
		return this.loadMatchOnly;
	}

	public void setLoadMatchOnly(Integer loadMatchOnly) {
		this.loadMatchOnly = loadMatchOnly;
	}

	public Integer getUpdateAttribute() {
		return this.updateAttribute;
	}

	public void setUpdateAttribute(Integer updateAttribute) {
		this.updateAttribute = updateAttribute;
	}

	public String getSynchFrequency() {
		return this.synchFrequency;
	}

	public void setSynchFrequency(String synchFrequency) {
		this.synchFrequency = synchFrequency;
	}

	public String getDeleteRule() {
		return this.deleteRule;
	}

	public void setDeleteRule(String deleteRule) {
		this.deleteRule = deleteRule;
	}

	public String getProcessRule() {
		return this.processRule;
	}

	public void setProcessRule(String processRule) {
		this.processRule = processRule;
	}

	public String getRejectRule() {
		return this.rejectRule;
	}

	public void setRejectRule(String rejectRule) {
		this.rejectRule = rejectRule;
	}

	public String getTransformationRule() {
		return this.transformationRule;
	}

	public void setTransformationRule(String transformationRule) {
		this.transformationRule = transformationRule;
	}

	public String getMatchFieldName() {
		return this.matchFieldName;
	}

	public void setMatchFieldName(String matchFieldName) {
		this.matchFieldName = matchFieldName;
	}

	public String getMatchSrcFieldName() {
		return this.matchSrcFieldName;
	}

	public void setMatchSrcFieldName(String matchSrcFieldName) {
		this.matchSrcFieldName = matchSrcFieldName;
	}

	public String getMatchManagedSysId() {
		return matchManagedSysId;
	}

	public void setMatchManagedSysId(String matchManagedSysId) {
		this.matchManagedSysId = matchManagedSysId;
	}

}
