package org.openiam.webadmin.busdel.base;

import java.rmi.*;
import diamelle.common.status.*;
import java.util.*;

public class CodeAccess extends DiamelleBaseAccess {
	CodeManager codeMgr = null;

	public CodeAccess() {
	  try{
		  CodeManagerHome codeMgrHome = (CodeManagerHome)getHome("diamelle.common.CodeManager", CodeManagerHome.class);
		  codeMgr = codeMgrHome.create();
	  } catch(Exception e){
		    e.printStackTrace();
	  }
  }

  
	public void addStatusCodeValue(StatusCodeValue statusCodeValue) throws RemoteException {
       codeMgr.addCode(statusCodeValue);
      
	}
  
	public void saveCode(StatusCodeValue statusCodeValue) throws java.rmi.RemoteException {
		   codeMgr.saveCode(statusCodeValue);
		   
	}	 
  
	public void removeCode(StatusCodeKey statusCodeKey) throws java.rmi.RemoteException {
			   codeMgr.removeCode(statusCodeKey);
	}
  
	public List getCodesByService(String companyOwnerId,String serviceId,String codeGroup,String languageCd) throws java.rmi.RemoteException {
	  return codeMgr.getCodesByService(companyOwnerId,serviceId ,codeGroup,languageCd);
  }
	 
	public StatusCodeValue getStatusCode(StatusCodeKey statusCodeKey) throws java.rmi.RemoteException {
		  return codeMgr.getStatusCode(statusCodeKey);
  }
  
  public Set getCodeGroupsByService(String companyOwnerId, String serviceId ) throws RemoteException {
  	return codeMgr.getCodeGroupsByService(companyOwnerId, serviceId);
  }
  
  public void addCode(StatusCodeValue val) throws RemoteException {
  	addCode(val);
  }
		    
	  
}


