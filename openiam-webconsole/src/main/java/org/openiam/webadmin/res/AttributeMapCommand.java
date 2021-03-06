/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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
package org.openiam.webadmin.res;

import org.openiam.idm.srvc.mngsys.dto.AttributeMap;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Command object for ManagedSystemConnection
 *
 * @author suneet
 */
public class AttributeMapCommand implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4939438179419596312L;
    /**
     *
     */
    List<AttributeMap> attrMapList;
    String resourceName;
    String resId;
    String managedSysId;

    public AttributeMapCommand() {

    }


    public String formatDate(Date dt) {
        if (dt == null) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        return fmt.format(dt);
    }


    public List<AttributeMap> getAttrMapList() {
        return attrMapList;
    }


    public void setAttrMapList(List<AttributeMap> attrMapList) {
        this.attrMapList = attrMapList;
    }


    public String getResourceName() {
        return resourceName;
    }


    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }


    public String getResId() {
        return resId;
    }


    public void setResId(String resId) {
        this.resId = resId;
    }


    public String getManagedSysId() {
        return managedSysId;
    }


    public void setManagedSysId(String managedSysId) {
        this.managedSysId = managedSysId;
    }


}
