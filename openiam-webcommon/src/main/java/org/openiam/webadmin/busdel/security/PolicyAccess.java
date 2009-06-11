package org.openiam.webadmin.busdel.security;

import java.rmi.RemoteException;
import java.util.*;

import org.openiam.webadmin.busdel.base.NavigationAccess;
import diamelle.security.policy.*;

public class PolicyAccess extends NavigationAccess{
    PolicyManagerHome policyMgrHome = null;
    PolicyManager policyMgr = null;
    private String attrId;

    public PolicyAccess() {
    	try {
    		policyMgrHome = (PolicyManagerHome)getHome("PolicyManager");
    		policyMgr  = policyMgrHome.create();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public List getPolicyByType(String type)  throws RemoteException{
    	return policyMgr.getPolicyByType(type);
    }
    
    public PolicyValue getPolicy(String policyId)  throws RemoteException{
    	return policyMgr.getPolicy(policyId);
    }
    public List getPolicyAttributes(String policyId) throws RemoteException {
    	return policyMgr.getPolicyAttrList(policyId);    	
    }
    public List getPolicyMembers(String policyId) throws RemoteException {
    	return policyMgr.getPolicyMemberList(policyId);    	
    }    

 	
 	public void savePolicy(PolicyValue val) throws RemoteException {
 		policyMgr.savePolicy(val);
 	}
 	public String addPolicy(PolicyValue val) throws RemoteException {
 		if ((val.getPolicyId() == null)||(val.getPolicyId().length() == 0))
 			val.setPolicyId(this.getNextId("POLICY_ID"));
 		policyMgr.addPolicy(val);
 		return val.getPolicyId();
 	}
 	public void removePolicy(String policyId) throws RemoteException {
 		policyMgr.removePolicy(policyId);
 	}
 	public void savePolicyAttributes(PolicyAttrValue val) throws RemoteException {
 		policyMgr.savePolicyAttr(val);
 	}
 	public String addPolicyAttribute(PolicyAttrValue val) throws RemoteException {
 		if ((val.getPolicyAttrId() == null)||(val.getPolicyAttrId().length() == 0)) {
 			String id = this.getNextId("POLICY_ATTR_ID");
 			val.setPolicyAttrId(id);
 		}	
 		policyMgr.addPolicyAttr(val);
 		return val.getPolicyAttrId();
 	}

 	public void savePolicyMember(PolicyMemberValue val) throws RemoteException {
 		policyMgr.savePolicyMember(val);
 	}
 	public String addPolicyMember(PolicyMemberValue val) throws RemoteException {
 		if ((val.getPolicyMemberId() == null)||(val.getPolicyMemberId().length() == 0)) {
 			String id = this.getNextId("POLICY_MEMBER_ID");
 			val.setPolicyMemberId(id);
 		}	 		
 		policyMgr.addPolicyMember(val);
 		return val.getPolicyMemberId();
 	}
 	
 	public void removePolicyMember(String policyMemberId) throws RemoteException{
 		policyMgr.removePolicyMember(policyMemberId);
 	}
 	
 	// Policy definitions ---------------------------------------
 	
	public PolicyDefValue getPolicyDefinition(String policyDefId)throws RemoteException {
		return policyMgr.getPolicyDefinition(policyDefId);
	}
	/**
	 * Get policy definition parameters 
	 * 
	 * @param policyDefId
	 * @return Collection of PolicyDefParamValue
	 * @throws RemoteException
	 */
	public Collection getPolicyDefParams(String policyDefId)throws RemoteException {
		Collection c = policyMgr.getPolicyDefParams(policyDefId);
		return c;
	}
	
	public void addPolicyDefinition(PolicyDefValue val) throws RemoteException {
		policyMgr.addPolicyDefinition(val);
	}
	
	public void addPolicyDefParam(PolicyDefParamValue val) throws RemoteException {
		policyMgr.addPolicyDefParam(val);
	}
	
	public void savePolicyDefinition(PolicyDefValue val)throws RemoteException {
		policyMgr.savePolicyDefinition(val);
	}
	
	public void savePolicyDefParam(PolicyDefParamValue val)throws RemoteException {
		policyMgr.savePolicyDefParam(val);
	}
	
    public List getPolicyForLogin(int policyDefType, String service, String login) throws RemoteException {
    	return policyMgr.getPolicyForLogin(policyDefType, service, login);
    }
    
    
	
}