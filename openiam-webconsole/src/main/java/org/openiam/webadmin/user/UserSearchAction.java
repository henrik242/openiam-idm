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

package org.openiam.webadmin.user;

import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.Search;
import org.openiam.util.db.SearchImpl;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.identity.*;
import org.openiam.webadmin.busdel.security.GroupDataServiceAccess;
import org.springframework.web.context.WebApplicationContext;

import diamelle.common.status.*;
import diamelle.common.view.DataView;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.service.UserMgr;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.util.db.Search;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

public class UserSearchAction extends NavigationDispatchAction  {

	
	private RoleDataService roleDataService;
	private GroupDataService groupManager;
	private OrganizationDataService orgManager;
	private String defaultSecurityDoamin;
	
	
    public ActionForward view ( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
			throws IOException, ServletException {
    	
    	// get groups and set it into the request object
      	// WebApplicationContext webContext =  getWebApplicationContext();
    	// GroupDataServiceAccess groupDataAcc = new GroupDataServiceAccess(webContext);
    	 
    	 request.setAttribute("groupList", allGroupListAsLabels()); 
    	 request.setAttribute("roleList", allRoleListAsLabels()); 
    	// request.setAttribute("orgList", groupDataAcc.getAllGroupListAsLabels()); 
    	// request.setAttribute("deptList", groupDataAcc.getAllGroupListAsLabels()); 
    	
    	HttpSession session = request.getSession();
    	List statusList = (List)session.getAttribute("statusList");
    	
    	if (statusList == null) {
            statusList = getUserStatusList();
            session.setAttribute("statusList", statusList);        		
    	}
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
         	
        	WebApplicationContext webContext =  getWebApplicationContext();
        	UserDataService userMgr = (UserDataService)webContext.getBean("userManager");

	       	 request.setAttribute("groupList", allGroupListAsLabels()); 
	    	 request.setAttribute("roleList", allRoleListAsLabels()); 
        	
        //	GroupDataServiceAccess groupDataAcc = new GroupDataServiceAccess(webContext);
        //	List groupList = groupDataAcc.getAllGroupListAsLabels();

        //	System.out.println("Grouplist = " + groupList);
        	  
            
    		//Search search = new SearchImpl();
        	UserSearch search = createSearch((DynaValidatorForm)form);     
    		List userList = userMgr.search(search);
        	

	        if (userList != null) {
	             request.setAttribute("userList", userList );
	             DataView vw = new DataView(userList,200);
	 		  	 session.setAttribute("searchResult", vw);
			  	 request.setAttribute("pageCount", new Integer(vw.getPageCount()));
			  	 request.setAttribute("resultSize", new Integer(vw.size()));
			  	 request.setAttribute("searchResult", vw.next());
			  	 request.setAttribute("currentPage",new Integer(0));
			}
 	 
	        //request.setAttribute("groupList", groupList); 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return (mapping.findForward("success"));
    }
    
    private List getUserStatusList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
        CodeAccess cdAccess = new CodeAccess();
        List codeList = cdAccess.getCodesByService("100","IDM","USER","en");
    
        if (codeList != null && codeList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i<codeList.size(); i++) {       		
        		StatusCodeValue val = (StatusCodeValue)codeList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getDescription(),val.getStatusCd());
        	 	newCodeList.add(label);
        	}
        }
        return newCodeList;
   	
    }
	
    public ActionForward page( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        int pageNbr = 0;
      	DataView dv = (DataView)session.getAttribute("searchResult");
      	String pageNumber = request.getParameter("pagenbr");
      	if (pageNumber != null) {
      		pageNbr = Integer.valueOf(pageNumber).intValue();
      	}
       	request.setAttribute("pageCount", new Integer(dv.getPageCount()));
      	request.setAttribute("resultSize", new Integer(dv.size()));
      	request.setAttribute("searchResult", dv.getPage(pageNbr));
      	request.setAttribute("currentPage",new Integer(pageNbr));
      	
      	
    	 return (mapping.findForward("success"));
      	
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
        if (form.get("companyName")!= null && ((String) form.get("companyName")).length()>0) {
    		search.setOrgId((String)form.get("companyName"));
    	}
        
        if (form.get("dept")!= null && ((String) form.get("dept")).length()>0) {
    		search.setDeptCd((String)form.get("dept"));
    	}
        if (form.get("areaCode")!= null && ((String) form.get("areaCode")).length()>0) {
        	search.setPhoneAreaCd((String)form.get("areaCode"));
    	}
        if (form.get("phoneNumber")!= null && ((String) form.get("phoneNumber")).length()>0) {
        	search.setPhoneNbr((String)form.get("phoneNumber"));
    	}
        if (form.get("role")!= null && ((String) form.get("role")).length()>0) {
        	search.setRoleId((String)form.get("role"));
    	} 
        if (form.get("group")!= null && ((String) form.get("group")).length()>0) {
        	search.setGroupId((String)form.get("group"));
    	} 
        if (form.get("email")!= null && ((String) form.get("email")).length()>0) {
        	search.setEmailAddress((String)form.get("email"));
        }
        return search;
     }
    
	public List allGroupListAsLabels() {
		List<LabelValueBean> newCodeList = new LinkedList();
		List<Group> grpList = groupManager.getAllGroups();
		if (grpList != null && grpList.size() > 0) {
			newCodeList.add(new LabelValueBean("",""));
	    	for (int i = 0; i < grpList.size(); i++) {       		
	    		Group val = grpList.get(i);
	    	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
	    	 	newCodeList.add(label);
	    	}
		}
		return newCodeList;
    }
	public List allRoleListAsLabels() {
		List<LabelValueBean> newCodeList = new LinkedList();
		Role[] roleAry = roleDataService.getRolesInService(defaultSecurityDoamin);
		if (roleAry != null && roleAry.length > 0) {
			newCodeList.add(new LabelValueBean("",""));
	    	for (int i = 0; i < roleAry.length; i++) {       		
	    		Role val = roleAry[i];
	    	 	LabelValueBean label = new LabelValueBean(val.getRoleName(),val.getId().getRoleId());
	    	 	newCodeList.add(label);
	    	}
		}
		return newCodeList;
    }
	
    
	public RoleDataService getRoleDataService() {
		return roleDataService;
	}
	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}
	public GroupDataService getGroupManager() {
		return groupManager;
	}
	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}
	public OrganizationDataService getOrgManager() {
		return orgManager;
	}
	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}
	public String getDefaultSecurityDoamin() {
		return defaultSecurityDoamin;
	}
	public void setDefaultSecurityDoamin(String defaultSecurityDoamin) {
		this.defaultSecurityDoamin = defaultSecurityDoamin;
	}
    



}

