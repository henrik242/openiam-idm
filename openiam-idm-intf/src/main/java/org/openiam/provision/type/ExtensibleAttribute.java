/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.provision.type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.relaxng.datatype.Datatype;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The content carried by the most SPML requests includes an Extensible type to all
 * for flexibility in carrying the data. ExtensibleAttribute provides an
 * attribute model to capture a wide variety of data.
 * 
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleAttribute", propOrder = {
    "name",
    "value",
    "metadataElementId",
    "operation",
    "multivalued",
    "valueList",
    "dataType",
    "objectType"

})
public class ExtensibleAttribute  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8402148961330001942L;
	protected String name;
	protected String value;
    protected String metadataElementId;
    protected int operation;
    protected boolean multivalued = false;
    protected List<String> valueList;
    protected String dataType;
    protected String objectType;

    protected static final Log log = LogFactory.getLog(ExtensibleAttribute.class);

	
	public ExtensibleAttribute() {
		
	}
	public ExtensibleAttribute(String name, String value) {
		this.name = name;
		this.value = value;
		operation = ModificationAttribute.add;
	}
	public ExtensibleAttribute(String name, String value, String metadataElementId) {
		this.name = name;
		this.value = value;
		this.metadataElementId = metadataElementId;
		operation = ModificationAttribute.add;
	}
	
	public ExtensibleAttribute(String name, String value, int operation, String dataType) {
		super();
		this.name = name;
		this.operation = operation;
		this.value = value;
        this.dataType = dataType;
	}

     public ExtensibleAttribute(String name, List<String> val, int operation, String dataType) {
	    super();
		this.name = name;
		this.operation = operation;
		this.valueList = val;
        multivalued = true;
        this.dataType = dataType;

        log.debug("Extensible attribute created: multivalue");

	}

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
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}


    public boolean isMultivalued() {
        return multivalued;
    }

    public void setMultivalued(boolean multivalued) {
        this.multivalued = multivalued;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public String toString() {
        return "ExtensibleAttribute{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", metadataElementId='" + metadataElementId + '\'' +
                ", operation=" + operation +
                ", multivalued=" + multivalued +
                ", valueList=" + valueList +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
