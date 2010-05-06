package org.openiam.idm.srvc.orgpolicy.service;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;

import static org.hibernate.criterion.Example.create;

/**
 * Interface for the OrgPolicyAcceptance service. 
 * The OrgPolicyAcceptanceService allows you to delivery notices around corporate policy
 * that people have to review and accept.  Examples of the policies would include
 * IT Resource Usage policy. 
 * @author Suneet shah
 */
public interface OrgPolicyService {

	public OrgPolicy addPolicyMessage (OrgPolicy transientInstance) ;
	
	public void removePolicyMessage(String policyMessageId) ;

	public OrgPolicy updatePolicyMessage(OrgPolicy detachedInstance) ;

    public OrgPolicy getPolicyMessageById(java.lang.String id) ;

	public List<OrgPolicy> getActiveOrgPoliciesForUser(java.lang.String userId) ;
	
	public List<OrgPolicy> getAllPolicyMessages();
	
	List<OrgPolicyUserLog> getLogEntryForUser(String userId);
	
	void logUserResponse(String orgPolicyId, String userId, String response);
}
