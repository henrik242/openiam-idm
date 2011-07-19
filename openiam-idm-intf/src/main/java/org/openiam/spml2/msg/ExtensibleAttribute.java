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
package org.openiam.spml2.msg;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.provision.type.ModificationAttribute;
import java.util.List;
import java.util.ArrayList;

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
    "operation",
    "multivalued",
    "valueList",
    "dataType"
})
@XmlSeeAlso({
    ArrayList.class
})
public class ExtensibleAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3737506396547959336L;
	String name;
	String value;
	int operation;
    boolean multivalued = false;
    List<String> valueList;
    DataType dataType;

    protected static final Log log = LogFactory.getLog(ExtensibleAttribute.class);
	
	public ExtensibleAttribute() {
		
	}
	public ExtensibleAttribute(String name, String value) {
		this.name = name;
		this.value = value;
		operation = ModificationAttribute.add;
	}
	
	public ExtensibleAttribute(String name, String value, int operation) {
		super();
		this.name = name;
		this.operation = operation;
		this.value = value;

        log.debug("Extensible attribute created: singlevalue");
	}

    public ExtensibleAttribute(String name, List<String> val, int operation) {
		super();
		this.name = name;
		this.operation = operation;
		this.valueList = val;
        multivalued = true;

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

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
