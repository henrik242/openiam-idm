package org.openiam.idm.srvc.secdomain.service;

import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Interface to manager the SecurityDomain that clients will access to gain information about SecurityDomain.
 * @author Suneet Shah
 *
 */

public interface SecurityDomainDataService {

	/**
	 * Returns the <code>SecurityDomain</code> object specified by the the domainId
	 * @param id - domainId
	 * @return
	 */
	public abstract SecurityDomain getSecurityDomain(String domainId);

	/**
	 * Adds a new security domain to the system. 
	 * @param val - SecurityDomain Object
	 */

	public abstract void addSecurityDomain(SecurityDomain secDom);

	/**
	 * Updates an existing security domain object.
	 * @param serv - Service Object
	 */
	public abstract void updateSecurityDomain(SecurityDomain secDom);

	/**
	 * Removes an existing security domain.
	 * 
	 * @param id - domainId id
	 */
	public abstract void removeSecurityDomain(String id);

	/**
	 * Removes an existing service.
	 * 
	 * @param id - Service id
	 */
	public abstract void removeSecurityDomain(SecurityDomain secDom);

	/**
	 * Returns an array of security domain objects in the system
	 * @return
	 */
	public abstract SecurityDomain[] getAllSecurityDomains();

}