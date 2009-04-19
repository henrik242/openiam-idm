package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * ResourceType allows you to classify the resource.
 */
public class ResourceType implements java.io.Serializable {

	private String resourceTypeId;
	private String description;
	private String metadataTypeId;
	private Integer provisionResource;
	private String processName;
	private Set<Resources> resourceses = new HashSet<Resources>(0);

	public ResourceType() {
	}

	public ResourceType(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public ResourceType(String resourceTypeId, String description,
			String metadataTypeId, Integer provisionResource,
			String processName, Set<Resources> resourceses) {
		this.resourceTypeId = resourceTypeId;
		this.description = description;
		this.metadataTypeId = metadataTypeId;
		this.provisionResource = provisionResource;
		this.processName = processName;
		this.resourceses = resourceses;
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

	public Set<Resources> getResourceses() {
		return this.resourceses;
	}

	public void setResourceses(Set<Resources> resourceses) {
		this.resourceses = resourceses;
	}

}
