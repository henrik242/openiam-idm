package org.openiam.webadmin.res;
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


import org.openiam.idm.srvc.res.dto.ResourceType;

import java.io.Serializable;
import java.util.List;


/**
 * Command object for the ManagedSystemList
 *
 * @author suneet
 */
public class ResourceListCommand implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 513253291470702152L;

    private List<ResourceType> resTypeAry;
    private String resourceTypeId;


    public ResourceListCommand() {

    }


    public String getResourceTypeId() {
        return resourceTypeId;
    }


    public void setResourceTypeId(String resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }


    public List<ResourceType> getResTypeAry() {
        return resTypeAry;
    }

    public void setResTypeAry(List<ResourceType> resTypeAry) {
        this.resTypeAry = resTypeAry;
    }


}
