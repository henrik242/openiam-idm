package org.openiam.webadmin.admin.domain;

import java.io.Serializable;

import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Command object for the ConnectorDefinitionController
 * @author suneet
 *
 */
public class SecurityDomainCommand implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8741198791323527695L;
	protected SecurityDomain domain = new SecurityDomain();
    

	public SecurityDomainCommand() {
    	
    }

	public SecurityDomain getDomain() {
		return domain;
	}

	public void setDomain(SecurityDomain domain) {
		this.domain = domain;
	}

	


	

}
