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
package org.openiam.idm.srvc.loc.service;

import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.lang.dto.Language;
import org.openiam.idm.srvc.loc.dto.Location;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.loc.service.LocationDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/loc/service", 
		serviceName = "LocationWebService")
public class LocationDataServiceImpl implements LocationDataService {

	LocationDAO locationDao;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDataService#addLocation(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public void addLocation(Location lg) {
		if (lg == null) {
			throw new NullPointerException("lg is null");
		}
		locationDao.add(lg);
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDataService#allLocations()
	 */
	public Location[] allLocations() {
		List<Location> lgList = locationDao.findAllLocations();
		if (lgList == null || lgList.isEmpty())
			return null;
		int size = lgList.size();
		Location[] lgAry = new Location[size];
		lgList.toArray(lgAry);
		return lgAry;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDataService#getLocation(java.lang.String)
	 */
	public Location getLocation(String locationCd) {
		if (locationCd == null) {
			throw new NullPointerException("locationCd is null");
		}
		return locationDao.findById(locationCd);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDataService#removeLocation(java.lang.String)
	 */
	public void removeLocation(String locCd) {
		if (locCd == null) {
			throw new NullPointerException("locCd is null");
		}
		Location lg = new Location(locCd);
		locationDao.remove(lg);
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDataService#updateLocation(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public void updateLocation(Location lg) {
		if (lg == null) {
			throw new NullPointerException("lg is null");
		}
		locationDao.update(lg);
		
	}

	public LocationDAO getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDAO locationDao) {
		this.locationDao = locationDao;
	}

}
