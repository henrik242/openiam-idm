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


import org.openiam.idm.srvc.meta.service.MetadataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.selfsrvc.AppConfiguration;
import org.springframework.web.struts.DispatchActionSupport;



import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

public class DirectorySearchAction extends DispatchActionSupport  {

	
	AppConfiguration appConfiguration;
	MetadataService metadataSrvc = null;
	UserDataWebService userManager = null;
	OrganizationDataService orgManager = null;
	String searchOrganizationList = null;
	
	


	
	public DirectorySearchAction() {
	}
	
    public ActionForward view ( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
			throws IOException, ServletException {
    	
    	HttpSession session = request.getSession();
    	List statusList = (List)session.getAttribute("statusList");
       	
       
       	List<Organization> orgAry = orgManager.allDepartments(appConfiguration.getParentOrgId());     	
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
    		if (search.isEmpty()) {
   		  	 	request.setAttribute("msg", "Please enter search criteria ");
   		  	 	return (mapping.findForward("success_search"));
    		}
        	
    		List<User> userList = userManager.search(search).getUserList();
        	
        
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
    
        

	private List getOrgLabels(List<Organization> orgAry)  {
		
		List<LabelValueBean> labelList = new ArrayList();
		
        if (orgAry != null && orgAry.size() > 0) {
        	labelList.add(new LabelValueBean("-Please Select-",""));
        	for (int i=0; i < orgAry.size(); i++ ) {
        		Organization org = (Organization)orgAry.get(i);
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



	public OrganizationDataService getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}

	public UserDataWebService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataWebService userManager) {
		this.userManager = userManager;
	}



    
    
}

