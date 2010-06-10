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
package org.openiam.idm.srvc.loc.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Domain object representing a location
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", propOrder = {
    "locationId",
    "name",
    "description",
    "country",
    "bldgNum",
    "streetDirection",
    "address1",
    "address2",
    "address3",
    "city",
    "state",
    "postalCd",
    "organizationId",
    "internalLocationId",
    "active"
})

public class Location implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -1982532513415681152L;
	protected String locationId;
	protected String name;
	protected String description;
	protected String country;
	protected String bldgNum;
	protected String streetDirection;
	protected String address1;
	protected String address2;
	protected String address3;
	protected String city;
	protected String state;
	protected String postalCd;
	protected String organizationId;
	protected Integer active;
	protected String internalLocationId;
	

	
	
	public Location() {
		
	}
	public Location(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBldgNum() {
		return bldgNum;
	}
	public void setBldgNum(String bldgNum) {
		this.bldgNum = bldgNum;
	}
	public String getStreetDirection() {
		return streetDirection;
	}
	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCd() {
		return postalCd;
	}
	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInternalLocationId() {
		return internalLocationId;
	}
	public void setInternalLocationId(String internalLocationId) {
		this.internalLocationId = internalLocationId;
	}
	
}
