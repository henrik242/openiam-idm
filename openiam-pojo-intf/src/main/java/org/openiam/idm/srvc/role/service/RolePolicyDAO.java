package org.openiam.idm.srvc.role.service;

import java.util.List;

import org.openiam.idm.srvc.role.dto.RolePolicy;

/**
 * Data access interface for domain model class RolePolicy.
 * @see org.openiam.idm.srvc.role.dto.RolePolicy
 */
public interface RolePolicyDAO {

	public void add(RolePolicy transientInstance);
	
	public void remove(RolePolicy persistentInstance);

	 public RolePolicy findById( java.lang.String id);
	
	public RolePolicy update(RolePolicy detachedInstance);

	
	public List<RolePolicy> findRolePolicies(String serviceId, String roleId);

}