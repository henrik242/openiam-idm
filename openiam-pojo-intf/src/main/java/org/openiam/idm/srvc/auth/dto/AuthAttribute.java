package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11


import org.openiam.base.AttributeOperationEnum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Login domain object
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthAttribute", propOrder = {
    "name",
    "value"
})

public class AuthAttribute implements java.io.Serializable,  Cloneable{

    protected String name;

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

    protected String value;


    public AuthAttribute() {
    }




}

