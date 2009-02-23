package org.openiam.webadmin.conn.mngsys;

import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Command object for the ManagedSystemList 
 * @author suneet
 *
 */
public class ManagedSysListCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 513253291470702152L;
	
	private SecurityDomain[] domainAry;
	private String domainId;
   
    
    
    public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public ManagedSysListCommand() {
    	
    }

	public SecurityDomain[] getDomainAry() {
		return domainAry;
	}



	public void setDomainAry(SecurityDomain[] domainAry) {
		this.domainAry = domainAry;
	}
	
	

}
