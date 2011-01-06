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
package org.openiam.idm.srvc.loc.ws;

import java.util.List;

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginResponse;
import org.openiam.idm.srvc.lang.dto.Language;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.dto.LocationSearch;
import org.openiam.idm.srvc.loc.service.LocationDataService;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.loc.ws.LocationDataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/loc/service", 
		serviceName = "LocationDataWebService")
public class LocationDataWebServiceImpl implements LocationDataWebService {

	protected LocationDataService locationDS;

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.ws.LocationDataWebService#addLocation(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public Response addLocation(Location lg) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		locationDS.addLocation(lg);
		return resp;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.ws.LocationDataWebService#allLocations()
	 */
	public LocationArrayResponse allLocations() {
		LocationArrayResponse resp = new LocationArrayResponse(ResponseStatus.SUCCESS);
		Location[] locAry = locationDS.allLocations();
		if (locAry == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setLocationAry(locAry);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.ws.LocationDataWebService#getLocation(java.lang.String)
	 */
	public LocationResponse getLocation(String LocationCd) {
		LocationResponse resp = new LocationResponse(ResponseStatus.SUCCESS);
		Location loc = locationDS.getLocation(LocationCd);
		if (loc == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setLocation(loc);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.ws.LocationDataWebService#removeLocation(java.lang.String)
	 */
	public Response removeLocation(String locCd) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		locationDS.removeLocation(locCd);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.ws.LocationDataWebService#updateLocation(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public Response updateLocation(Location loc) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		locationDS.updateLocation(loc);
		return resp;
	}

	public LocationDataService getLocationDS() {
		return locationDS;
	}

	public void setLocationDS(LocationDataService locationDS) {
		this.locationDS = locationDS;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.ws.LocationDataWebService#searchLocation(org.openiam.idm.srvc.loc.dto.LocationSearch)
	 */
	public LocationArrayResponse searchLocation(LocationSearch locationSearch) {
		LocationArrayResponse resp = new LocationArrayResponse(ResponseStatus.SUCCESS);
		Location[] locAry = locationDS.searchLocation(locationSearch);
		if (locAry == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setLocationAry(locAry);
		}
		return resp;
	}
	

}
