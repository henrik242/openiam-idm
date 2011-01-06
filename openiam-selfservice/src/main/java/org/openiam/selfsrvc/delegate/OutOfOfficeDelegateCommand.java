package org.openiam.selfsrvc.delegate;

import java.io.Serializable;
import java.util.Date;

import org.openiam.idm.srvc.menu.dto.Menu;

/**
 * Command object for the ContactAdmin
 * @author suneet
 *
 */
public class OutOfOfficeDelegateCommand implements Serializable {
	 



	private String userId;
	private String userName;
	private Date startDate;
	private Date endDate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	



	

}
