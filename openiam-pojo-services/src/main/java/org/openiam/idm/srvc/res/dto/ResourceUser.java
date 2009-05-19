package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceUser is the association of resource and a user
 */
public class ResourceUser implements java.io.Serializable {

	private ResourceUserId id;
	private Resource resources;

	public ResourceUser() {
	}

	public ResourceUser(ResourceUserId id, Resource resources) {
		this.id = id;
		this.resources = resources;
	}

	public ResourceUserId getId() {
		return this.id;
	}

	public void setId(ResourceUserId id) {
		this.id = id;
	}

	public Resource getResources() {
		return this.resources;
	}

	public void setResources(Resource resources) {
		this.resources = resources;
	}

}
