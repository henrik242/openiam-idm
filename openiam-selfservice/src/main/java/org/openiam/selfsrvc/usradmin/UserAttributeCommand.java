package org.openiam.selfsrvc.usradmin;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.user.dto.UserAttribute;



/**
 * Command object for the UserAttributeController
 * @author suneet
 *
 */
public class UserAttributeCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1889032830441911649L;
	 

	protected List<UserAttribute> attributeList;
	protected String perId; // personId




	public String getPerId() {
		return perId;
	}


	public void setPerId(String perId) {
		this.perId = perId;
	}


	public List<UserAttribute> getAttributeList() {
		return attributeList;
	}


	public void setAttributeList(List<UserAttribute> attributeList) {
		this.attributeList = attributeList;
	}
	

	



}
