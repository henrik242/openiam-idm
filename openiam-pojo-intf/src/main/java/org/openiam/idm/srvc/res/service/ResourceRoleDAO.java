package org.openiam.idm.srvc.res.service;

import java.util.List;

import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.dto.ResourceRoleId;

public interface ResourceRoleDAO {

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceRoleDAO#remove(org.openiam.idm.srvc.res.dto.ResourceRole)
	 */
	void remove(ResourceRole persistentInstance);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceRoleDAO#update(org.openiam.idm.srvc.res.dto.ResourceRole)
	 */
	ResourceRole update(ResourceRole detachedInstance);

	ResourceRole findById(ResourceRoleId id);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceRoleDAO#findByExample(org.openiam.idm.srvc.res.dto.ResourceRole)
	 */
	List<ResourceRole> findByExample(ResourceRole instance);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceRoleDAO#add(org.openiam.idm.srvc.res.dto.ResourceRole)
	 */
	ResourceRole add(ResourceRole instance);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceRoleDAO#findAllResourceRoles()
	 */
	List<ResourceRole> findAllResourceRoles();
	
	List<ResourceRole> findResourcesForRole(String domainId, String roleId);

    void removeResourceRole(String domainId, String roleId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceRoleDAO#removeAllResourceRoles()
	 */
	void removeAllResourceRoles();

}