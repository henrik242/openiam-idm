package org.openiam.idm.srvc.loc.ws;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.dto.LocationSearch;


/**
 * <code>LocationDataService</code> provides a service to manage the location
 * lists in OpenIAM. Locations are predefined addresses, such as the various addresses that a company may have.
 * User can then be assigned to these locations instead of creating a new address entry.   
 * 
 * @author Suneet Shah
 * @version 2.0
 */
	

@WebService(targetNamespace = "urn:idm.openiam.org/srvc/loc/service", name = "LocationDataService")		
public interface LocationDataWebService {

	/**
	 * Adds a new Location to the list
	 * @param lg
	 */
	@WebMethod
	public Response addLocation(
			@WebParam(name = "loc", targetNamespace = "")
			Location loc);
	/**
	 * Updates an existing Location in the list
	 * @param lg
	 */
	@WebMethod
	public Response updateLocation(
			@WebParam(name = "loc", targetNamespace = "")
			Location loc);
	/**
	 * Removes a Locations from the list of Locations
	 * @param locCd
	 */
	@WebMethod
	public Response removeLocation(
			@WebParam(name = "loc", targetNamespace = "")
			String loc);
	/**
	 * Returns an array of all Locations
	 * @return
	 */
	@WebMethod
	public LocationArrayResponse allLocations();
	/**
	 * Returns the Location specified by the Location
	 * @param LocationCd
	 * @return
	 */
	@WebMethod
	public LocationResponse getLocation(
			@WebParam(name = "loc", targetNamespace = "")
			String loc);

	@WebMethod
	public LocationArrayResponse searchLocation(
			@WebParam(name = "locationSearch", targetNamespace = "")
			LocationSearch locationSearch);


}