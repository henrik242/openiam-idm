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


//import diamelle.ebc.user.*;
import diamelle.common.status.*;
import diamelle.common.view.DataView;
import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;


import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

public class OrgSearchAction extends NavigationDispatchAction  {

	MetadataAccess metaAccess = null;
	
    public ActionForward view ( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
			throws IOException, ServletException {
    	
   	 WebApplicationContext webContext =  getWebApplicationContext();

   	 HttpSession session = request.getSession();
     List typeList = null;     	
	 if (typeList == null) {
        typeList = getTypeList();
        session.setAttribute("orgTypeList", typeList);        		
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
        	OrganizationDataService orgMgr = (OrganizationDataService)webContext.getBean("orgManager");
        	
        	String orgName = (String)((DynaValidatorForm)form).get("organizationName");
        	String orgType = (String)((DynaValidatorForm)form).get("orgType");
        	
        	if (orgType != null && orgType.length()==0) {
        		orgType = null;
        	}
        	if (orgName != null && orgName.length()==0) {
        		orgName = null;
        	}        	
    		List orgList = orgMgr.search(orgName, orgType);
        	

	        if (orgList != null) {
	             request.setAttribute("orgList", orgList );
	             DataView vw = new DataView(orgList,200);
	 		  	 session.setAttribute("searchResult", vw);
			  	 request.setAttribute("pageCount", new Integer(vw.getPageCount()));
			  	 request.setAttribute("resultSize", new Integer(vw.size()));
			  	 request.setAttribute("searchResult", vw.next());
			  	 request.setAttribute("currentPage",new Integer(0));
			}

    	 
         } catch(Exception e) {
            e.printStackTrace();
        }
        return (mapping.findForward("success"));
    }
    
    private List getTypeList() throws RemoteException {
    	ArrayList newCodeList = new ArrayList();
    	metaAccess = new MetadataAccess();
    	Map typeMap = metaAccess.getMetadataTypes("ORG_TYPE");
      
        if (typeMap != null && typeMap.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	Set set = typeMap.keySet();
        	Iterator it = set.iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();
        		String value = (String)typeMap.get(key);
        		LabelValueBean label = new LabelValueBean(value,key);
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


    

 
}

