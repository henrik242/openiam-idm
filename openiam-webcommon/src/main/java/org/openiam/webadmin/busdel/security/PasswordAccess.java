package org.openiam.webadmin.busdel.security;

import java.rmi.RemoteException;
import java.util.*;

import org.openiam.webadmin.busdel.base.NavigationAccess;
import diamelle.security.policy.*;
import diamelle.security.pwd.*;

public class PasswordAccess extends NavigationAccess{
    PolicyManagerHome policyMgrHome = null;
    PolicyManager policyMgr = null;
    PasswordManagerHome passwordMgrHome = null;
    PasswordManager passwordMgr = null;

    private String attrId;

    public PasswordAccess() {
    	try {
    		policyMgrHome = (PolicyManagerHome)getHome("PolicyManager");
    		policyMgr  = policyMgrHome.create();

    		passwordMgrHome = (PasswordManagerHome)getHome("PasswordManager");
    		passwordMgr  = passwordMgrHome.create();

    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public boolean validatePassword(String service, String login, String password)  throws RemoteException{
    	return passwordMgr.validatePassword(service, login, password);
    }
    
     
    
	
}