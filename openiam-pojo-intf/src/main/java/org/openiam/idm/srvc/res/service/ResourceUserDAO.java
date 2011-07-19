package org.openiam.idm.srvc.res.service;

import java.util.List;

import org.openiam.idm.srvc.res.dto.ResourceUser;
import org.openiam.idm.srvc.res.dto.ResourceUserId;

public interface ResourceUserDAO {

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#remove(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	void remove(ResourceUser persistentInstance);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#update(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	ResourceUser update(ResourceUser detachedInstance);

	ResourceUser findById(ResourceUserId id);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#findByExample(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	List<ResourceUser> findByExample(ResourceUser instance);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#add(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	ResourceUser add(ResourceUser instance);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#findAllResourceUsers()
	 */
	List<ResourceUser> findAllResourceUsers();
	
	List<ResourceUser> findAllResourceForUsers(String userId);

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#removeAllResourceUsers()
	 */
	void removeAllResourceUsers();
	
	void removeUserFromAllResources(String userId);

}