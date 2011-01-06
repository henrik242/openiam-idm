package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * ResourceType allows you to classify the resource.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceType", propOrder = {
    "resourceTypeId",
    "description",
    "metadataTypeId",
    "provisionResource",
    "processName"
})
public class ResourceType implements java.io.Serializable {

	private String resourceTypeId;
	private String description;
	private String metadataTypeId;
	private Integer provisionResource;
	private String processName;

	public ResourceType() {
	}

	public ResourceType(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public ResourceType(String resourceTypeId, String description,
			String metadataTypeId, Integer provisionResource,
			String processName, Set<Resource> resourceses) {
		this.resourceTypeId = resourceTypeId;
		this.description = description;
		this.metadataTypeId = metadataTypeId;
		this.provisionResource = provisionResource;
		this.processName = processName;

	}

	public String getResourceTypeId() {
		return this.resourceTypeId;
	}

	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMetadataTypeId() {
		return this.metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}

	public Integer getProvisionResource() {
		return this.provisionResource;
	}

	public void setProvisionResource(Integer provisionResource) {
		this.provisionResource = provisionResource;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}



}
