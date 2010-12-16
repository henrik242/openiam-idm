package org.openiam.webadmin.admin.orgpolicy;

import java.io.Serializable;

import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;

/**
 * Command object for the ConnectorDefinitionController
 * @author suneet
 *
 */
public class OrgPolicyCommand implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8302277051573483007L;
	protected OrgPolicy orgPolicy = new OrgPolicy();
    
	public OrgPolicyCommand() {
    	
    }

	public OrgPolicy getOrgPolicy() {
		return orgPolicy;
	}

	public void setOrgPolicy(OrgPolicy orgPolicy) {
		this.orgPolicy = orgPolicy;
	}

	


	

}
