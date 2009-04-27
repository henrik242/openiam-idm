/**
 * ------------------------------------------------------------------------------
 * Title: UserSearchAction
 * Author: LD 07-20-2004
 * Overview:Handles searching user/s based on the search criteria.
 * ------------------------------------------------------------------------------
 * Copyright (c) 2000-2004 Diamelle Inc. All Rights Reserved.
 *
 * This SOURCE CODE FILE, which has been provided by Diamelle Technologies as part
 * of a Diamelle Software product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of Diamelle Technologies.
 *
 * This code or parts or derivatives of it cannot be used for any commercial
 * products without written permission from Diamelle Technologies.
 *
 * USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS
 * OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
 * RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
 * CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
 * DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
 * ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
 * DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR
 * DERIVED FROM THIS SOURCE CODE FILE.
 * ------------------------------------------------------------------------------
 * CHANGE CONTROL:
 *
 * ------------------------------------------------------------------------------
 */

package org.openiam.selfsrvc.profile;

import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.meta.service.MetadataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.selfsrvc.AppConfiguration;
import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.Search;
import org.openiam.util.db.SearchImpl;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.identity.*;
import org.openiam.webadmin.busdel.security.AuditLogAccess;
import org.openiam.webadmin.busdel.security.AuthenticatorAccess;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import diamelle.ebc.user.*;
import diamelle.util.Log;
import diamelle.common.org.OrganizationValue;
import diamelle.common.service.Service;
import diamelle.common.service.ServiceMgr;
import diamelle.common.status.*;
import diamelle.common.view.DataView;

import java.io.*;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.actions.*;

public class DirectorySearchAction extends NavigationDispatchAction  {

	
	AppConfiguration appConfiguration;
	MetadataService metadataSrvc = null;
	UserDataService userManager = null;
	OrganizationDataService orgManager = null;
	String searchOrganizationList = null;
	
	


	
	public DirectorySearchAction() {
	}
	
    public ActionForward view ( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
			throws IOException, ServletException {
    	
    	HttpSession session = request.getSession();
    	List statusList = (List)session.getAttribute("statusList");
       	
       
       	Organization[] orgAry = orgManager.allDepartments(null);     	
    	List labelList = getOrgLabels(orgAry);  	
    	session.setAttribute("orgList", labelList);
    	
    	return (mapping.findForward("view"));
    }
    /**
     * Retrieves a list of Users based on the search criteria
     */
    public ActionForward search ( ActionMapping mapping, ActionForm form, 
    			HttpServletRequest request, HttpServletResponse res ) 
    			throws IOException, ServletException {
        try {
        	List statusList = null; 
        	
        	
        	HttpSession session = request.getSession();

        	UserSearch search = createSearch((DynaValidatorForm)form);     
    		List<User> userList = userManager.search(search);
        	
        
          if (userList != null) {
             request.setAttribute("userList", userList );

		  	 request.setAttribute("resultSize", userList.size());
		  	 request.setAttribute("maxResultSize", appConfiguration.getMaxResultSetSize());

		  }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return (mapping.findForward("success_search"));
    }
    
	


    private UserSearch createSearch(DynaValidatorForm form) {
        UserSearch search = new UserSearch();
     
       

        // lastname
        if (form.get("lastName")!= null && ((String) form.get("lastName")).length()>0) {
        	search.setLastName(form.get("lastName")+"%");
    	}
        if (form.get("firstName")!= null && ((String) form.get("firstName")).length()>0) {
    		search.setFirstName(form.get("firstName")+"%");
    	}
      
        if (form.get("dept")!= null && ((String) form.get("dept")).length()>0) {
    		search.setDeptCd((String)form.get("dept"));
    	}
        if (form.get("phone_nbr")!= null && ((String) form.get("phone_nbr")).length()>0) {
        	search.setPhoneNbr((String)form.get("phone_nbr"));
    	}
        if (form.get("phone_areaCd")!= null && ((String) form.get("phone_areaCd")).length()>0) {
        	search.setPhoneAreaCd((String)form.get("phone_areaCd"));
    	}

        return search;
     }
    
    public ActionForward detail ( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
			throws IOException, ServletException {

    
    HttpSession session = request.getSession();
    String personId = null;
    DynaValidatorForm userForm = (DynaValidatorForm) form;


 
    try {
    	
		WebApplicationContext webCtx = getWebApplicationContext();
		metadataSrvc = (MetadataService)webCtx.getBean("metadataService");
		
        personId = request.getParameter("personId");

       User userData = userManager.getUserWithDependent(personId,true);
       
                
       if (userData != null) {
          this.populateFormBean(userData, userForm);
          request.setAttribute("personData", userData);
          // get the phone, address and email
          Address adr = userManager.getAddressByName(personId, "PRIMARY");
          Phone workPhone = userManager.getPhoneByName(personId, "WORK");
          Phone cellPhone = userManager.getPhoneByName(personId, "CELL");
          Phone faxPhone = userManager.getPhoneByName(personId, "FAX");
          EmailAddress email = userManager.getEmailAddressByName(personId, "PRIMARY");

          addressToForm(userForm,adr);
          phoneToForm(userForm,workPhone, "WORK");
          phoneToForm(userForm,cellPhone, "CELL");
          phoneToForm(userForm,faxPhone, "FAX");
          emailToForm(userForm,email);                         
       
          // GET DIRECT REPORTS
              
          request.setAttribute("directReports",  userManager.getEmployees(personId) );
               
          // GET SUPERVISOR
         
          request.setAttribute("supervisor",userManager.getPrimarySupervisor(personId) );
       
       }
       
       request.setAttribute("personId",personId);

    } catch(Exception e) {
        e.printStackTrace();
    }
 	return (mapping.findForward("detail"));

    }
    
    /* Helper methods for the directory detail view */
    
    
    /**
     * Retrieves information from UserData and sets it in Form
     */
    private void populateFormBean(User ud, DynaValidatorForm uform) {
        CalendarUtil calUtil = new CalendarUtil();

      uform.set("personId", ud.getUserId());

      uform.set("firstName", ud.getFirstName());
      uform.set("middleName", ud.getMiddleInit());
      uform.set("lastName", ud.getLastName());

      if (ud.getMetadataTypeId() != null) {
    	  uform.set("typeId", ud.getMetadataTypeId());
      }
      uform.set("dept", ud.getDeptCd());
      uform.set("deptName", ud.getDeptName() );

      uform.set("location", ud.getLocationCd());
      uform.set("locationName", ud.getLocationName() );
      
      uform.set("costCenter", ud.getCostCenter() );
      uform.set("mailCode", ud.getDivision() );
      uform.set("employeeType", ud.getEmployeeType() );
      uform.set("division", ud.getMailCode() );
      uform.set("jobCode", ud.getJobCode() );
      
      
      uform.set("companyId", ud.getCompanyId());
      if ( ud.getCompanyId() != null) { 

       		WebApplicationContext webContext =  getWebApplicationContext();
      		Organization org = orgManager.getOrganization(ud.getCompanyId());
      		if (org != null) {
      			 uform.set("companyName", org.getOrganizationName());
      		}

      }
      
      uform.set("sex", ud.getSex());
      Date createTime = ud.getCreateDate();
      if (createTime != null)
      	uform.set("createDate", createTime.toString());
      uform.set("createdBy", ud.getCreatedBy());

      if (ud.getBirthdate() != null) {
      	try {
      	String dt = CalendarUtil.getDateString(ud.getBirthdate(),"MM-dd-yyyy");
      	uform.set("birthday", dt);
      	}catch(ParseException pe) {
      		Log.error("Parse Exception", pe);
      	}
      }
      uform.set("status",ud.getStatus());   
      uform.set("title", ud.getTitle());
      
    }

    private void addressToForm(DynaValidatorForm form, Address adr) {
    	if (adr != null) {
       	form.set("address1",adr.getAddress1());
    	form.set("address2",adr.getAddress2());
    	form.set("city", adr.getCity());
    	form.set("zip",adr.getPostalCd());
    	form.set("state",adr.getState());
    	form.set("addressId",adr.getAddressId());
    	form.set("country", adr.getCountry());
    	}
   	
    }
    private void phoneToForm(DynaValidatorForm form, Phone ph, String phoneType) {
       	if (ph == null)
       		return;
    	if (phoneType.equals("WORK")) {
    		form.set("phone_areacd",ph.getAreaCd());
    		form.set("phone_nbr",ph.getPhoneNbr());
    		form.set("workPhoneId",ph.getPhoneId());
    	}
    	if (phoneType.equals("CELL")) {
    		form.set("cell_areacd",ph.getAreaCd());
    		form.set("cell_nbr",ph.getPhoneNbr());
    		form.set("cellPhoneId",ph.getPhoneId());
    		
    	}
    	if (phoneType.equals("FAX")) {
    		form.set("fax_areacd",ph.getAreaCd());
    		form.set("fax_nbr",ph.getPhoneNbr());
    		form.set("faxPhoneId",ph.getPhoneId());
   		
    	}
    	
    }
    private void emailToForm(DynaValidatorForm form,EmailAddress email) {
	    if (email != null) {
	       	form.set("emailId",email.getEmailId());
	    	form.set("email",email.getEmailAddress());
	    } 	
    }

	private List getOrgLabels(Organization[] orgAry)  {
		
		List<LabelValueBean> labelList = new ArrayList();
		
        if (orgAry != null && orgAry.length > 0) {
        	labelList.add(new LabelValueBean("-Please Select-",""));
        	for (int i=0; i < orgAry.length; i++ ) {
        		Organization org = (Organization)orgAry[i];
          	 	LabelValueBean label = new LabelValueBean(org.getOrganizationName(), org.getOrgId());
          	 	labelList.add(label);
        	}        		
        }
        return labelList;
    }



	
	public String getSearchOrganizationList() {
		return searchOrganizationList;
	}

	public void setSearchOrganizationList(String searchOrganizationList) {
		this.searchOrganizationList = searchOrganizationList;
	}

	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}

	public void setAppConfiguration(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}

	public OrganizationDataService getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}



    
    
}

