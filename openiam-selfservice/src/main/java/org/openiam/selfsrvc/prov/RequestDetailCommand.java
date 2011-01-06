package org.openiam.selfsrvc.prov;

import java.io.Serializable;
import java.util.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestApprover;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;

public class RequestDetailCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5456150837492332419L;

	private String requestId;
	
	protected ProvisionRequest request;
	protected User requestor;
	protected String approverId;
	protected String comment;
	protected List<RequestApprover> requestApproverList;
	protected List<Group> groupList;
	protected List<Role> roleList;
	

	private String submit;
	
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public ProvisionRequest getRequest() {
		return request;
	}
	public void setRequest(ProvisionRequest request) {
		this.request = request;
	}
	public User getRequestor() {
		return requestor;
	}
	public void setRequestor(User requestor) {
		this.requestor = requestor;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<RequestApprover> getRequestApproverList() {
		return requestApproverList;
	}
	public void setRequestApproverList(List<RequestApprover> requestApproverList) {
		this.requestApproverList = requestApproverList;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
