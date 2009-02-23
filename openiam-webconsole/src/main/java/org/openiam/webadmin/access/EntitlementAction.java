/**
 * ------------------------------------------------------------------------------ 
 * Title: EntitlementAction
 * Author: APS 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * Entitlements. 
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

package org.openiam.webadmin.access;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.openiam.webadmin.busdel.base.*;

import diamelle.security.auth.*;
import org.openiam.webadmin.busdel.security.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.DynaValidatorForm;


 
//This class allows us to
// 1. View List of Entitlements
// 2. View details of an Entitlement
// 3. Add new Entitlement
// 4. Update details of existing Entitlement
//5. Deletes a Enttilement


public class EntitlementAction extends NavigationDispatchAction  {
	
	private SecurityAccess securityAccess = new SecurityAccess();
    

	//Extracts nav, categoryId, menuId and nextAction parameters from a request object and sets
	// nextAction, categoryId,categoryValue, categories, navBar, menuId, menus, menuBar in session
	//Also extracts List of Entitlements.
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			                  HttpServletResponse response) throws IOException, ServletException {  

		System.out.println("EntitlementAction - init...");

		// Extracts nav, categoryId, menuId and nextAction parameters from a request object and sets
	    // nextAction, categoryId,categoryValue, categories, navBar, menuId, menus, menuBar in session
	    // categoryId left unchanged if null or showlist=1
	    // nav=resetMenu, resetCat removes those attributes only, reset removes both
	    super.setNavigation(mapping,form,request,response);
	    ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    try {
	    	//retrieves list of all entitlements
	    	List entitlementList =  securityAccess.getAllEntitlements();
				    	
	    	//helps in debug
	    	if(entitlementList == null)
	    		System.out.println("No Entitlements found");
	    	
	    	request.setAttribute("entitlementList",entitlementList);

	    } catch(Exception e) {
	    	e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
	    }
	    return mapping.findForward("entitlementlist");
	}

	//Sets mode to add, for adding new Entitlement
	public ActionForward addEntitlement(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
	                                    HttpServletResponse response) throws IOException, ServletException {
				
		System.out.println("EntitlementAction - addEntitlement...");
		DynaValidatorForm entitlementForm = (DynaValidatorForm) form;

		//set mode. if mode is add/edit, entitlements jsp will include entitlement.jsp
		entitlementForm.set("mode","add");
				    
		//intializing the entitlements.jsp
		init(mapping, form, request, response);		    
		
		return mapping.findForward("entitlementlist");
	}


	//Sets fields in entitlement.jsp for updating the Entitlement Details and also sets mode to edit	
	public ActionForward editEntitlement(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
	                               HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("EntitlementAction - editEntitlement...");
		ActionErrors errors = new ActionErrors();
		DynaValidatorForm entitlementForm = (DynaValidatorForm) form;
		
		try {
			String entitlementId = request.getParameter("entitlementId");				
		
			//set mode. if mode is add/edit, entitlements jsp will include entitlement.jsp
			entitlementForm.set("mode","edit");
			
			if(entitlementId != null) {
				//setting values in entitlement.jsp 
				EntitlementValue entitlementValue = securityAccess.editEntitlement(entitlementId);
				setEntitlementValue(form,entitlementValue);
						
				//intializing the entitlementslist.jsp
				init(mapping, form, request, response);
			}
		} catch(Exception e){
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}  		
			return mapping.findForward("entitlementlist");
	}
			
	


	//Saves/Update Entitlement Details
	public ActionForward saveEntitlement(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			                      	     HttpServletResponse response) throws IOException, ServletException {  
		  
		System.out.println("EntitlementAction - saveEntitlement...");
		ActionErrors errors = new ActionErrors();  
		HttpSession session = request.getSession();
		                              
		DynaValidatorForm entitlementForm = (DynaValidatorForm) form;
		String mode = (String) entitlementForm.get("mode");
		
		try {
			EntitlementValue entitlementValue = getEntitlementValue(form);
			
			if(mode.equalsIgnoreCase("add")) { 

				// if the user had added a EntitlementId in the Form
	            if (entitlementValue.getEntitlementId() != null && entitlementValue.getEntitlementId().length()>0) {

	            	// checking if such a entitlementId is there in the database
	            	EntitlementValue ev = securityAccess.editEntitlement(entitlementValue.getEntitlementId());

	            	if (ev == null) {
	            		// if the entitlement does not exist add the entitlement
	            		securityAccess.addEntitlement(entitlementValue);
	            		entitlementForm.set("mode","view");
	            	} else {
	            		// if the entitlement exists throw an error
	            		entitlementForm.set("mode","add");
						errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.exception.entitlementexists"));
	            	}
	            } else {
					securityAccess.addEntitlement(entitlementValue);
					entitlementForm.set("mode","view"); 
	            }
			} else if(mode.equalsIgnoreCase("edit")) {
				//updating the entitlement
				securityAccess.updateEntitlement(entitlementValue);
				entitlementForm.set("mode","view");
			}	
			
			//intializing the entitlements.jsp
			init(mapping, form, request, response); 
		} catch(Exception e) {
		    	e.printStackTrace();
		    	errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		 // if errors, return to welcome page via index.jsp
	    if (!errors.isEmpty()) {
	    	saveErrors(request, errors);
	    	return mapping.findForward("entitlementlist");
	    }
		return mapping.findForward("entitlementlist");
	}

	

	//Removes Entitlement/Entitlements from the List
	public ActionForward removeEntitlement(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
  			                      	       HttpServletResponse response) throws IOException, ServletException {  
			  
		System.out.println("EntitlementAction - removeEntitlement...");
		ActionErrors errors = new ActionErrors();
		try {
			if (request.getParameterValues("entitlementId") != null) {

			String[] arrEntitlementId = request.getParameterValues("entitlementId");
			for(int i = 0; i < arrEntitlementId.length; i++)	
				// deleting selected entitlements
				securityAccess.deleteEntitlement(arrEntitlementId[i]);
			}
			init(mapping,form,request,response);				
		}  catch(Exception e){
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}  		
		return mapping.findForward("entitlementlist");
	}
	
	

	//Fetches input data from the form and sets into value class
 	private EntitlementValue getEntitlementValue(ActionForm form) {
		DynaValidatorForm entitlementForm = (DynaValidatorForm)form;
	
		EntitlementValue entitlementValue = new EntitlementValue();
		
		entitlementValue.setEntitlementId((String) entitlementForm.get("entitlementId"));
		entitlementValue.setEntitlementName((String) entitlementForm.get("entitlementName"));
		entitlementValue.setEntitlementValue((String) entitlementForm.get("entitlementValue"));
		
		return entitlementValue;
	}
		


 	//Takes values from value class object and sets it into form
 	private void setEntitlementValue(ActionForm form, EntitlementValue entitlementValue) throws Exception {
   		DynaValidatorForm entitlementForm = (DynaValidatorForm) form;
   		
   		entitlementForm.set("entitlementId",entitlementValue.getEntitlementId());
   		entitlementForm.set("entitlementName",entitlementValue.getEntitlementName());
   		entitlementForm.set("entitlementValue",entitlementValue.getEntitlementValue()); 		         
	}
}
