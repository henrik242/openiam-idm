package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceProp enables the extension of a resource by associated properties (name value pairs) to them.
 */
public class ResourceProp implements java.io.Serializable {

	private String resourcePropId;
	private Resources resources;
	private String metadataId;
	private String propValue;

	public ResourceProp() {
	}

	public ResourceProp(String resourcePropId) {
		this.resourcePropId = resourcePropId;
	}

	public ResourceProp(String resourcePropId, Resources resources,
			String metadataId, String propValue) {
		this.resourcePropId = resourcePropId;
		this.resources = resources;
		this.metadataId = metadataId;
		this.propValue = propValue;
	}

	public String getResourcePropId() {
		return this.resourcePropId;
	}

	public void setResourcePropId(String resourcePropId) {
		this.resourcePropId = resourcePropId;
	}

	public Resources getResources() {
		return this.resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public String getMetadataId() {
		return this.metadataId;
	}

	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
	}

	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}
