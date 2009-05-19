package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceProp enables the extension of a resource by associated properties (name value pairs) to them.
 */
public class ResourceProp implements java.io.Serializable {

	private String resourcePropId;
	private String resourceId;
	private String metadataId;
	private String name;
	private String value;

	public ResourceProp() {
	}

	public ResourceProp(String metadataId, String name, String resourceId,
			String resourcePropId, String value) {
		super();
		this.metadataId = metadataId;
		this.name = name;
		this.resourceId = resourceId;
		this.resourcePropId = resourcePropId;
		this.value = value;
	}

	
	public ResourceProp(String resourcePropId) {
		this.resourcePropId = resourcePropId;
	}



	public ResourceProp(String name, String resourceId, String resourcePropId,
			String value) {
		super();
		this.name = name;
		this.resourceId = resourceId;
		this.resourcePropId = resourcePropId;
		this.value = value;
	}

	public String getResourcePropId() {
		return this.resourcePropId;
	}

	public void setResourcePropId(String resourcePropId) {
		this.resourcePropId = resourcePropId;
	}



	public String getMetadataId() {
		return this.metadataId;
	}

	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}




}
