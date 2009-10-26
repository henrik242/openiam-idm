package org.openiam.selfsrvc.prov;

import java.io.Serializable;
import java.util.*;

import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.user.dto.User;

public class RequestDetailCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5456150837492332419L;

	private String requestId;
	
	protected ProvisionRequest request;
	protected User requestor;
	

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

}
