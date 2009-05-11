package org.openiam.idm.srvc.loc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openiam.idm.srvc.loc.dto.Location;


/**
 * <code>LocationDataService</code> provides a service to manage the location
 * lists in OpenIAM. Locations are predefined addresses, such as the various addresses that a company may have.
 * User can then be assigned to these locations instead of creating a new address entry.   
 * 
 * @author Suneet Shah
 * @version 2.0
 */
	
public interface LocationDataService {

	/**
	 * Adds a new Location to the list
	 * @param lg
	 */
	public void addLocation(Location lg);
	/**
	 * Updates an existing Location in the list
	 * @param lg
	 */
	public void updateLocation(Location lg);
	/**
	 * Removes a Locations from the list of Locations
	 * @param langCd
	 */
	public void removeLocation(String langCd);
	/**
	 * Returns an array of all Locations
	 * @return
	 */
	public Location[] allLocations();
	/**
	 * Returns the Location specified by the Location
	 * @param LocationCd
	 * @return
	 */
	public Location getLocation(String LocationCd);
	


}