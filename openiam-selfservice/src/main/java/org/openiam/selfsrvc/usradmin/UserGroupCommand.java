package org.openiam.selfsrvc.usradmin;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.grp.dto.Group;

/**
 * Command object for the UserGroupController
 * @author suneet
 *
 */
public class UserGroupCommand implements Serializable {
	 


	/**
	 * 
	 */
	private static final long serialVersionUID = 1023382173600128732L;

	List<Group> groupList;
	protected String perId; // personId
	
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}




}
