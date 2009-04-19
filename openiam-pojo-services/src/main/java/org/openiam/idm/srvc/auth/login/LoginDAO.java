package org.openiam.idm.srvc.auth.login;

import java.util.List;

import org.openiam.idm.srvc.auth.dto.Login;

/**
 * Data access interface for domain model class Login.
 * @author Suneet Shah
 *
 */
public interface LoginDAO {

	Login add(Login transientInstance);

	void remove(Login persistentInstance);

	Login update(Login detachedInstance);

	Login findById(org.openiam.idm.srvc.auth.dto.LoginId id);

	List<Login> findByExample(Login instance);
	
	List<Login> findUser(String userId);
	
	List<Login> findLoginByDomain(String domain);
	
}