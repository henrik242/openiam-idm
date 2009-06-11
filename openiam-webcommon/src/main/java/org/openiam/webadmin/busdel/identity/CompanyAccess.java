package org.openiam.webadmin.busdel.identity;

import org.openiam.webadmin.busdel.base.*;
import diamelle.ebc.user.*;

import java.rmi.*;
import java.util.*;

public class CompanyAccess extends DiamelleBaseAccess{
  private CompanyService company = null;

  public CompanyAccess() {
    super();
    try {
      CompanyServiceHome home =  (CompanyServiceHome)getHome("CompanyService");
      company = home.create(); 
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns a single User
   * @param userId
   * @return
   */
  public CompanyData getCompany(String companyId) throws RemoteException{  
    return company.getCompany(companyId);
  }

 public void removeCompany(String cmpId) throws RemoteException{  
   company.removeCompany(cmpId);
 }
  
  public void createCompany(CompanyData cd) throws RemoteException {
   this.addCompany(cd);
   //cmp.addCompany(cd);
 }

 public void saveCompany(CompanyData cd) throws RemoteException {
   company.saveCompany(cd);
 }

 public void addAddress(Address adr) throws RemoteException  {
    company.addAddress(adr);
 }

 public Address getAddress(String compId, String addressName) throws RemoteException {
   return company.getAddressByDescription(compId, addressName);
 }
 public Email getEmail(String compId, String emailName) throws RemoteException {
   return company.getEmailByDescription(compId, emailName);
 }
 public Phone getPhone(String compId, String phoneName) throws RemoteException {
 	return company.getPhoneNbrByDescription(compId, phoneName);
 }

 public Address getAddress(String companyId) throws RemoteException {
   return company.getDefaultAddress(companyId);
  }

  public Email getEmail(String companyId) throws RemoteException {
	 return company.getDefaultEmail(companyId);


  }
  public Phone getPhone(String companyId) throws RemoteException {
	 return company.getDefaultPhoneNbr(companyId);
  } 
 
  public void addCompany(CompanyData cd) throws RemoteException {

	 if (cd.getCompanyId() == null || cd.getCompanyId().length() == 0) {
		 cd.setCompanyId(getNextId("COMPANY_ID"));
		 company.addCompany(cd);		
	 }else {
		 company.saveCompany(cd);
	 }	
  }
  
  public void addPhone(Phone phone) throws RemoteException {
  
  	if (phone.getId() == null || phone.getId().length() == 0)
  		phone.setId(getNextId("PHONE_ID"));
      company.addPhone(phone);
  }

  public void addEmail(Email email) throws RemoteException {
	if (email.getId() == null || email.getId().length() == 0)
		email.setId(getNextId("EMAIL_ID"));
    company.addEmail(email);
  }
  
  public List searchCompany(CompanySearch companySearch) throws java.rmi.RemoteException {
    List companyList = (List) company.searchCompany(companySearch);
    return companyList;
}
  
  public Map getAllPhoneNumbers(String compId) throws RemoteException {
 	return company.getAllPhoneNbrs(compId);
 }
 public Map getAllAddresses(String compId) throws RemoteException {
 	return company.getAllAddresses(compId);
 }
 public Map getAllEmailAddresses(String compId) throws RemoteException {
 	return company.getAllEmails(compId);
 }



}
