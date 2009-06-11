package org.openiam.webadmin.busdel.identity;

import org.openiam.webadmin.busdel.base.*;
import diamelle.common.org.*;
import diamelle.common.continfo.*;

import java.rmi.*;
import java.util.*;

public class OrganizationAccess extends DiamelleBaseAccess{
  private OrganizationManager org = null;

  public OrganizationAccess() {
    super();
    try {
    	OrganizationManagerHome home =  (OrganizationManagerHome)getHome("OrganizationManager");
    	org = home.create(); 
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns a single User
   * @param userId
   * @return
   */
  public OrganizationValue getOrganization(String organizationId) throws RemoteException{  
	  System.out.println("Org id = " + organizationId);
    return org.getOrganization(organizationId);
  }

 public void removeOrganization(String cmpId) throws RemoteException{  
   org.removeOrganization(cmpId);
 }
  

 public void saveOrganization(OrganizationValue cd) throws RemoteException {
   org.saveOrganization(cd);
 }

  
  public void addOrganization(OrganizationValue cd) throws RemoteException {

	 if (cd.getOrganizationId() == null || cd.getOrganizationId().length() == 0) {
		 cd.setOrganizationId(getNextId("COMPANY_ID"));
		 org.addOrganization(cd);		
	 }else {
		 org.saveOrganization(cd);
	 }	
  }
  
  
  public List searchOrganization(OrganizationSearch companySearch) throws java.rmi.RemoteException {
    List companyList = (List) org.searchOrganization(companySearch);
    return companyList;
}
  


}
