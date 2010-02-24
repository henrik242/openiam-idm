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
package org.openiam.base;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines values to indicate how an attribute is being modified.
 * @author suneet
 *
 */

@XmlType(name = "AttributeOperationEnum")
@XmlEnum
public enum AttributeOperationEnum {

    @XmlEnumValue("add")
    ADD(1),
    @XmlEnumValue("replace")
    REPLACE(2),
    @XmlEnumValue("delete")
    DELETE(3);
    private int value;
   
    AttributeOperationEnum(int val) {
    	value = val;
    }
    public int getValue() {
    	return value;
    }
    public void setValue(int val) {
    	value = val;
    }  
}

    