package org.openiam.idm.srvc.orgpolicy.ws;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;

/**
 * Interface for the SysMessageDelivery service. The message Delivery service is allows you to create and define messages and have them
 * delivered to the audience an application such as the selfservice app..
 * @see org.openiam.idm.srvc.msg.dto.SysMessage
 * @author Suneet shah
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/orgpolicy/service", name = "OrgPolicyAcceptanceWebService")
public interface OrgPolicyWebService {

	@WebMethod
	public OrgPolicyResponse addPolicyMessage (
			@WebParam(name = "msg", targetNamespace = "")
			OrgPolicy msg) ;
	@WebMethod	
	public Response removePolicyMessage(
			@WebParam(name = "msgId", targetNamespace = "")
			String msgId) ;

	@WebMethod
	public OrgPolicyResponse updatePolicyMessage(
			@WebParam(name = "msg", targetNamespace = "")
			OrgPolicy msg) ;

	@WebMethod
    public OrgPolicyResponse getPolicyMessageById(
    		@WebParam(name = "id", targetNamespace = "")
    		java.lang.String id) ;

	@WebMethod
	public OrgPolicyListResponse getActiveOrgPoliciesForUser(
			@WebParam(name = "userId", targetNamespace = "")
			java.lang.String userId) ;
	
	@WebMethod
	public OrgPolicyListResponse getAllPolicyMessages();

	OrgPolicyUserLogListResponse getLogEntryForUser(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);
	
	Response logUserResponse(
			@WebParam(name = "orgPolicyId", targetNamespace = "")
			String orgPolicyId, 
			@WebParam(name = "userId", targetNamespace = "")
			String userId,
			@WebParam(name = "response", targetNamespace = "")
			String response);
}
