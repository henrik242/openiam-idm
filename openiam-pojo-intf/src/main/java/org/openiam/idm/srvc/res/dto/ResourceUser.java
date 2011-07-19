package org.openiam.idm.srvc.res.dto;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceUser is the association of resource and a user
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceUser", propOrder = {
    "id",
    "resource"
})
public class ResourceUser implements java.io.Serializable {

	private ResourceUserId id;
	private Resource resource;

	public ResourceUser() {
	}

	public ResourceUser(ResourceUserId id, Resource resource) {
		this.id = id;
		this.resource = resource;
	}
	

	public ResourceUserId getId() {
		return this.id;
	}

	public void setId(ResourceUserId id) {
		this.id = id;
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource value) {
		this.resource = value;
	}


	
	
}
