package org.openiam.idm.srvc.res.dto;

import org.openiam.base.BaseObject;

/**
 * Created by IntelliJ IDEA.
 * User: arun
 * Date: 8/4/11
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrivilegeDef extends BaseObject {
    private String privilegeId;
    private String action;
    private String description;

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivilegeDef that = (PrivilegeDef) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (privilegeId != null ? !privilegeId.equals(that.privilegeId) : that.privilegeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = privilegeId != null ? privilegeId.hashCode() : 0;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
