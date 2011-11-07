package org.openiam.idm.srvc.res.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceRole associates a role to a resource. This association is used to determine if a role has access
 * to a resource.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceRole", propOrder = {
    "id" ,
     "permit",
     "startDate",
      "endDate"
})
public class ResourceRole implements java.io.Serializable {

	private ResourceRoleId id;
	//private String resourceId;

    protected boolean permit;
    @XmlSchemaType(name = "dateTime")
    protected Date startDate;
    @XmlSchemaType(name = "dateTime")
    protected Date endDate;

	public ResourceRole() {
	}

	public ResourceRole(ResourceRoleId id) {
		this.id = id;
		//this.resourceId = resourceId;
	}

	public ResourceRoleId getId() {
		return this.id;
	}

	public void setId(ResourceRoleId id) {
		this.id = id;
	}

    public boolean getPermit() {
        return permit;
    }

    public void setPermit(boolean permit) {
        this.permit = permit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
