package org.openiam.selfsrvc.usradmin;

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
 * Command object for the UserUserController
 * @author suneet
 *
 */
public class UserRoleCommand implements Serializable {
	 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1193834815491224478L;
	protected List<Role> roleList;
	protected String perId; // personId
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}


	



}
