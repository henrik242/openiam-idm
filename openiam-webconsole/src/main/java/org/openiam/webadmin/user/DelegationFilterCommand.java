package org.openiam.webadmin.user;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Command object for the EditUserController
 * @author suneet
 *
 */
public class DelegationFilterCommand implements Serializable {

	 
	protected List<Login> principalList;
	protected String perId; // personId


	public String getPerId() {
		return perId;
	}


	public void setPerId(String perId) {
		this.perId = perId;
	}


	public List<Login> getPrincipalList() {
		return principalList;
	}


	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}
	

	



}
