package org.openiam.idm.srvc.synch.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SynchConfigDataMapping", 
		propOrder = { "mappingId",
		"synchConfigId",
		"idmFieldName", 
		"srcFieldName"})
public class SynchConfigDataMapping implements java.io.Serializable {

	private String mappingId;
	private String synchConfigId;
	private String idmFieldName;
	private String srcFieldName;

	public SynchConfigDataMapping() {
	}

	public SynchConfigDataMapping(String mappingId) {
		this.mappingId = mappingId;
	}

	public SynchConfigDataMapping(String mappingId, String synchConfigId,
			String idmFieldName, String srcFieldName) {
		this.mappingId = mappingId;
		this.synchConfigId = synchConfigId;
		this.idmFieldName = idmFieldName;
		this.srcFieldName = srcFieldName;
	}

	public String getMappingId() {
		return this.mappingId;
	}

	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}

	public String getSynchConfigId() {
		return this.synchConfigId;
	}

	public void setSynchConfigId(String synchConfigId) {
		this.synchConfigId = synchConfigId;
	}

	public String getIdmFieldName() {
		return this.idmFieldName;
	}

	public void setIdmFieldName(String idmFieldName) {
		this.idmFieldName = idmFieldName;
	}

	public String getSrcFieldName() {
		return this.srcFieldName;
	}

	public void setSrcFieldName(String srcFieldName) {
		this.srcFieldName = srcFieldName;
	}

}
