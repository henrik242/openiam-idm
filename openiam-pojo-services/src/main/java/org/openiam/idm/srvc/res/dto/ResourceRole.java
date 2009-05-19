package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceRole associates a role to a resource. This association is used to determine if a role has access
 * to a resource.
 */
public class ResourceRole implements java.io.Serializable {

	private ResourceRoleId id;
	private Resource resources;

	public ResourceRole() {
	}

	public ResourceRole(ResourceRoleId id, Resource resources) {
		this.id = id;
		this.resources = resources;
	}

	public ResourceRoleId getId() {
		return this.id;
	}

	public void setId(ResourceRoleId id) {
		this.id = id;
	}

	public Resource getResources() {
		return this.resources;
	}

	public void setResources(Resource resources) {
		this.resources = resources;
	}

}
