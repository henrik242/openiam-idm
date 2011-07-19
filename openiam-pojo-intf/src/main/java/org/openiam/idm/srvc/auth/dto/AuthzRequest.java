package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11


import org.openiam.base.AttributeOperationEnum;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * Login domain object
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthzRequest", propOrder = {
    "principalName",
    "principalAttributes",
    "resourceName",
    "resourceAttributes",
    "action",
    "requestTime",
    "sessionId",
    "clientIp",
    "requestParameters"

})

public class AuthzRequest implements java.io.Serializable,  Cloneable{

    protected String principalName;
    protected List<AuthAttribute> principalAttributes;
    String resourceName;
    List<AuthAttribute> resourceAttributes;
    String action;
     @XmlSchemaType(name = "dateTime")
    protected Date requestTime;
    protected String sessionId;
    protected String clientIp;
    List<AuthAttribute> requestParameters;


    public AuthzRequest() {
    }


    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public List<AuthAttribute> getPrincipalAttributes() {
        return principalAttributes;
    }

    public void setPrincipalAttributes(List<AuthAttribute> principalAttributes) {
        this.principalAttributes = principalAttributes;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<AuthAttribute> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(List<AuthAttribute> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public List<AuthAttribute> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(List<AuthAttribute> requestParameters) {
        this.requestParameters = requestParameters;
    }
}

