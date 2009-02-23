/*
* ------------------------------------------------------------------------------
 * Title: GroupAction
 * Author: APS 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * Groups 
* ------------------------------------------------------------------------------
* Copyright (c) 2000-2008 OpenIAM, LLC. All Rights Reserved.
*
* This SOURCE CODE FILE, which has been provided by OpenIAM as part
* of a OpenIAM Software product for use ONLY by licensed users of the product,
* includes CONFIDENTIAL and PROPRIETARY information of OpenIAM.
*
* This code or parts or derivatives of it cannot be used for any commercial
* products without written permission from OpenIAM.
*
* USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
*
* IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD OpenIAM, ITS
* RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
* CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
* DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
* ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
* DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
* ------------------------------------------------------------------------------
* CHANGE CONTROL:
* Last modified by : 
* on : 
* ------------------------------------------------------------------------------
*/

package org.openiam.webadmin.access;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.identity.UserDataServiceAccess;
//import diamelle.security.auth.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;

//import diamelle.common.service.ServiceMgr;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import diamelle.common.status.StatusCodeValue;



//This class allows us to
// 1. View List of Group
// 2. View details of a Groups
// 3. Add new Group
//4. Update details of existing Group
// 5. Deletes a Group


public class GroupAction extends NavigationDispatchAction  {
	

    private SecurityAccess securityAccess = new SecurityAccess();
	
    //Extracts nav, categoryId, menuId and nextAction parameters from a request object and sets
    //nextAction, categoryId,categoryValue, categories, navBar, menuId, menus, menuBar in session
    //Also extracts List of Groups. 
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			                  HttpServletResponse response) throws IOException, ServletException {  

	    super.setNavigation(mapping,form,request,response);
	    ActionErrors errors = new ActionErrors();
          
	 	WebApplicationContext webContext =  getWebApplicationContext();
		GroupDataService gds = (GroupDataService)webContext.getBean("groupManager");
	    
	    try {
	    	

	    	//List<Group> groupList = gds.getAllGroups();
	    	List<Group> groupList = gds.getAllGroupsWithDependents(true);
	    	
	    	//helps in debug
	    	if (groupList == null) {
	    		System.out.println("No groups found");
	    	}
	    	request.setAttribute("groupList", groupList);
	    
	    } catch(Exception e) {
	    	e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
	    }
	    return mapping.findForward("grouplist");
	}

	public ActionForward searchGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
            HttpServletResponse response) throws IOException, ServletException {

		GroupDataServiceAccess groupDataAcc = null;
		
	    ActionErrors errors = new ActionErrors();
	   // HttpSession session = request.getSession();
	    DynaValidatorForm dynForm = (DynaValidatorForm)form;
	    dynForm.set("mode","List");
	    List filterGroupList = new ArrayList();
	
	 	
		WebApplicationContext webContext =  getWebApplicationContext();
		GroupDataService gds = (GroupDataService)webContext.getBean("groupManager");

			
	    
	    try {    	

	    	List<Group> groupList = gds.getAllGroups();
	    	if (groupList != null) {
		    	String grpFilter = request.getParameter("groupName"); 
		    	if (grpFilter != null ) {
		    		// iterate through the list and 
		    		int size = groupList.size();
		    		for (int i=0; i < size; i++) {
		    			Group gVal = (Group)groupList.get(i);
		    			if (gVal.getGrpName().startsWith(grpFilter)) {
		    				filterGroupList.add(gVal);
		    			}
		    		}
		    		request.setAttribute("groupList", filterGroupList);
		    	}else {
		    		request.setAttribute("groupList", groupList);	
		    	}
	    	}

	    } catch(Exception e) {
	    	e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
	    }
	    return mapping.findForward("grouplist");
		
		
	}
	

	//Sets mode to add, for adding new Group
 	public ActionForward addGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
	                              HttpServletResponse response) throws IOException, ServletException {
				
		
			
			ServletContext servletContext =  getServlet().getServletConfig().getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			//org.openiam.idm.srvc.service.service.ServiceMgr servMgr = 
			//	(org.openiam.idm.srvc.service.service.ServiceMgr)ctx.getBean("serviceManager");
			
			HttpSession session = request.getSession();
			DynaValidatorForm groupForm = (DynaValidatorForm) form;

			//intializing the groupslist.jsp
		    init(mapping, form, request, response);	
		    
			//set mode. if mode is add/edit, groupslist jsp will include group.jsp
		    groupForm.set("mode","add");
		
			//Map services = servMgr.getServicesMap();
			List grpList = this.getGroupList();
			
	    	//request.setAttribute("services", services);
	    	request.setAttribute("refGroupList", grpList);

		    return mapping.findForward("editGroup");
		}



 	//Sets mode to Edit, for updating the Group Details
	public ActionForward editGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
                                   HttpServletResponse response) throws IOException, ServletException {

       GroupDataServiceAccess groupDataAcc = null;
      
 	   WebApplicationContext webContext =  getWebApplicationContext();
	   groupDataAcc = new GroupDataServiceAccess(webContext);
		
		ActionErrors errors = new ActionErrors();

		try {
			String groupId = request.getParameter("groupId");
						
			DynaValidatorForm groupForm = (DynaValidatorForm) form;

			//set mode. if mode is add/edit, groupslist jsp will include group.jsp
			groupForm.set("mode","edit");

			if(groupId != null) {
				//sets values for fields in group.jsp for editing
				Group groupValue = groupDataAcc.getGroup(groupId);
				setGroupValue(form, groupValue);
									
				//intializing the grouplist.jsp
				init(mapping, form, request, response);
				
				List grpList = this.getGroupList();
				request.setAttribute("refGroupList", grpList);
			}
			
		} catch(Exception e){
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}  		
		return mapping.findForward("editGroup");
	}
			
	

	// Saves/Update Policy Details
	public ActionForward saveGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
     	                      	   HttpServletResponse response) throws IOException, ServletException {  

		GroupDataServiceAccess groupDataAcc = null;

		System.out.println("GroupAction - saveGroup...");
		ActionErrors errors = new ActionErrors();  
		//HttpSession session = request.getSession();

		WebApplicationContext webContext =  getWebApplicationContext();
		groupDataAcc = new GroupDataServiceAccess(webContext);
		
		
		DynaValidatorForm grpForm = (DynaValidatorForm) form;
		String mode = (String) grpForm.get("mode");
		
		try {
			//reads the data from the form
			Group groupValue =getGroupValue(form);
			
			System.out.println("Group Id = " + groupValue.getGrpId());
		    	
	    	if(mode.equalsIgnoreCase("add")) {
				//adding the new group
	    		groupDataAcc.addGroup(groupValue);
			} else if(mode.equalsIgnoreCase("edit")) {
				//updating the group
				groupDataAcc.updateGroup(groupValue);
	    	}  		

			//sets mode to view
			grpForm.set("mode","view");
			
			//intializing the grouplist.jsp
			init(mapping,form,request,response);    	    	
	    } catch(Exception e) {
	    	e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
	    }
		return mapping.findForward("grouplist");
	}

	
	//Removes Group/Groups from the List
 	public ActionForward removeGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
  			                      	 HttpServletResponse response) throws IOException, ServletException {  
			  
		System.out.println("GroupAction - removeGroup...");
		ActionErrors errors = new ActionErrors();
		try {
			if (request.getParameterValues("groupId") != null) {

			String[] arrGroupId = request.getParameterValues("groupId");
			for(int i = 0; i < arrGroupId.length; i++)
				// deleting selected groups
				securityAccess.remove(arrGroupId[i]);				
			}
			init(mapping,form,request,response);				
		}  catch(Exception e){
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}  		
		return mapping.findForward("grouplist");
	}
	
	

	//Fetches input data from the form and sets into value class
	private Group getGroupValue(ActionForm form) {
		DynaValidatorForm groupForm = (DynaValidatorForm)form;
	
		Group groupValue = new Group();
		groupValue.setGrpId((String) groupForm.get("grpId"));
		groupValue.setGrpName((String)groupForm.get("groupName"));
		
		if (groupValue.getGrpId() == null || groupValue.getGrpId().length() ==0) {
			groupValue.setGrpId(groupValue.getGrpName());
		}
		
		
		String inherit = (String)groupForm.get("inheritFromParent");
		if ( inherit != null && inherit.equalsIgnoreCase("on"))
		    groupValue.setInheritFromParent(true);
		else
		    groupValue.setInheritFromParent(false);
	
		
		String event = ((String)groupForm.get("provMethodEvent"));
		if (event != null && event.equalsIgnoreCase("on")) {
		    groupValue.setProvisionMethod( "EVENT");
		}
		String process = ((String)groupForm.get("provMethodProcess"));
		if (process != null && process.equalsIgnoreCase("on")) {
		    groupValue.setProvisionMethod( "PROCESS");
		}
		if (event == null && process == null) {
		    groupValue.setProvisionMethod(null);
		}

		groupValue.setProvisionObjName( (String)groupForm.get("provProcessName") );
		groupValue.setParentGrpId((String)groupForm.getString("parentGroup"));
		
  
		return groupValue;
	}
		


	//Takes values from value class object and sets it into form
 	private void setGroupValue(ActionForm form, Group groupValue) throws Exception {
   		DynaValidatorForm groupForm = (DynaValidatorForm) form;
	
	  	groupForm.set("grpId",groupValue.getGrpId());
	  	groupForm.set("groupName",groupValue.getGrpName());         
	  	groupForm.set("provProcessName", groupValue.getProvisionObjName() );
	  	groupForm.set("parentGroup", groupValue.getParentGrpId());

	  	
	  	if (groupValue.getProvisionMethod() != null) {
		  	if  (groupValue.getProvisionMethod().equals("EVENT")){
		  	  groupForm.set("provMethodEvent","on");
		  	}else {
		  	  groupForm.set("provMethodEvent","off");
		  	}
		  	if (groupValue.getProvisionMethod().equals("PROCESS")) {
		  	  groupForm.set("provMethodProcess","on");  
		  	}else {
		  	  groupForm.set("provMethodProcess","off");
		  	}
	  	}
	  	  

 	}
 	
	private List getGroupList()  {
		
   	 WebApplicationContext webContext =  getWebApplicationContext();
  	 GroupDataService gds = (GroupDataService)webContext.getBean("groupManager");
		
   	   List<Group> grpList = gds.getAllGroups();
   	    
		
    	ArrayList newGroupList = new ArrayList();

        if (grpList != null && grpList.size() > 0) {
        	newGroupList.add(new LabelValueBean("",""));
        	for (int i=0; i < grpList.size(); i++) {       		
        		Group val = (Group)grpList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
        	 	newGroupList.add(label);
        	}
        }
        return newGroupList;
    }
}

