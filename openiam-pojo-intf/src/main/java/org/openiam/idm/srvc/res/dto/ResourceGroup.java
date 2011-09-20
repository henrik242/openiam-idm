package org.openiam.idm.srvc.res.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceUser is the association of resource and a user
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceGroup", propOrder = {
    "resGroupId",
    "resourceId",
    "groupId",
    "startDate",
    "endDate"
})
public class ResourceGroup implements java.io.Serializable {

    String resGroupId;
    String resourceId;
    String groupId;
    Date startDate;
    Date endDate;


	public ResourceGroup() {
	}

    public ResourceGroup(String resourceId, String groupId) {
        this.resourceId = resourceId;
        this.groupId = groupId;
    }

    public String getResGroupId() {
        return resGroupId;
    }

    public void setResGroupId(String resGroupId) {
        this.resGroupId = resGroupId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceGroup that = (ResourceGroup) o;

        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (resGroupId != null ? !resGroupId.equals(that.resGroupId) : that.resGroupId != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resGroupId != null ? resGroupId.hashCode() : 0;
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
