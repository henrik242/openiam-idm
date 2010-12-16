package org.openiam.webadmin.admin.loc;

import java.io.Serializable;

import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.menu.dto.Menu;

/**
 * Command object for the ConnectorDefinitionController
 * @author suneet
 *
 */
public class LocationCommand implements Serializable {

    
    protected Location location = new Location();
    
    public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360587821829865173L;

	public LocationCommand() {
    	
    }

	


	

}
