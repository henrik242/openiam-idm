package org.openiam.idm.srvc.res.dto;

import org.openiam.base.BaseObject;

import javax.xml.bind.annotation.XmlSchemaType;
import java.sql.Timestamp;

//import org.openiam.idm.srvc.user.dto.User;

/**
 * Created by IntelliJ IDEA.
 * User: arun
 * Date: 8/4/11
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserPrivilege extends BaseObject {
    private String userPrivilegeId;
    private String userId;
    private String resourceId;
    private PrivilegeDef privilege;
    private boolean permit;
    @XmlSchemaType(name = "dateTime")
    private Timestamp startDate;
    @XmlSchemaType(name = "dateTime")
    private Timestamp endDate;

    public String getUserPrivilegeId() {
        return userPrivilegeId;
    }

    public void setUserPrivilegeId(String userPrivilegeId) {
        this.userPrivilegeId = userPrivilegeId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public PrivilegeDef getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeDef privilege) {
        this.privilege = privilege;
    }


    public boolean getPermit() {
        return permit;
    }

    public void setPermit(boolean permit) {
        this.permit = permit;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }


    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrivilege that = (UserPrivilege) o;

        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        //if (permit != null ? !permit.equals(that.permit) : that.permit != null) return false;
        //if (privilegeId != null ? !privilegeId.equals(that.privilegeId) : that.privilegeId != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userPrivilegeId != null ? !userPrivilegeId.equals(that.userPrivilegeId) : that.userPrivilegeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userPrivilegeId != null ? userPrivilegeId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        //result = 31 * result + (privilegeId != null ? privilegeId.hashCode() : 0);
        //result = 31 * result + (permit != null ? permit.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
