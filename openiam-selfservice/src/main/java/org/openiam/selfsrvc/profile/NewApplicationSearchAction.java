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

import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.Search;
import org.openiam.util.db.SearchImpl;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.identity.*;
import org.springframework.web.context.WebApplicationContext;

import diamelle.ebc.user.*;
import diamelle.common.status.*;
import diamelle.common.view.DataView;

import java.io.*;

import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.actions.*;

public class NewApplicationSearchAction extends NavigationDispatchAction  {

    public ActionForward view ( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
			throws IOException, ServletException {
    	
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
        	
           	WebApplicationContext webContext =  getWebApplicationContext();
        	UserDataService userMgr = (UserDataService)webContext.getBean("userManager");

        	
        	HttpSession session = request.getSession();

        	Search search = createSearch((DynaValidatorForm)form);     
    		List userList = userMgr.search(search);
        	
            //UserSearch search = createSearch((DynaValidatorForm)form);       	
        	//UserAccess userAccess = new UserAccess();
        	
        	//search.setLastName(request.getParameter("lastName"));
        	
        	//search.setCompanyId(null);
        	
          //List userList = userAccess.findUser(search);
          
          System.out.println("User list size in search=" + userList.size());
          
          if (userList != null) {
             request.setAttribute("userList", userList );
             DataView vw = new DataView(userList,25);
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

    private Search createSearch(DynaValidatorForm form) {
        Search search = new SearchImpl();
        QueryCriteria qc = new QueryCriteria();

        
		//qc.like(UserSearchField.LastName, "s%");
		//search.addSearchCriteria(qc);

		qc.like(UserSearchField.FirstName, "%");
		search.addSearchCriteria(qc);
		
        // firstname
       // if (form.get("firstName")!= null && ((String) form.get("firstName")).length()>0) {
    ///		qc.like(UserSearchField.FirstName, form.get("firstName"));
    //		search.addSearchCriteria(qc);
    //	}
        // lastname
        if (form.get("lastName")!= null && ((String) form.get("lastName")).length()>0) {
    		qc.like(UserSearchField.LastName, form.get("lastName"));
    		search.addSearchCriteria(qc);
    	}


        return search;
     }
    
    /**
     * Retrieves search criteria from Form and sets in the UserSearch object
     */
 /*   private UserSearch createSearch(DynaValidatorForm form) {
      UserSearch search = new UserSearch();

      // firstname
      if (form.get("firstName")!= null && ((String) form.get("firstName")).length()>0) {
        search.setFirstName((String) form.get("firstName"));
        search.addSearchOperator("firstName", (String) form.get("operationFN"));
      }
      // lastname
      if (form.get("lastName")!= null && ((String) form.get("lastName")).length()>0) {
        search.setLastName((String) form.get("lastName"));
       // search.addSearchOperator("lastName", "LIKE");
      }

      return search;
   }

*/
}

