/**
 * ------------------------------------------------------------------------------ 
 * Title: ResourceTypeAction
 * Author: APS 04-09-2004
 * Overview: gemonique starting action
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
 * aaa - APS   
 * ------------------------------------------------------------------------------           
 */

package org.openiam.webadmin.access;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import diamelle.security.resource.*;
import org.openiam.webadmin.busdel.security.ACLAccess;
import org.apache.struts.validator.DynaValidatorForm;

public class ResourceTypeAction extends NavigationDispatchAction  {
  private SecurityAccess security = new SecurityAccess();
  private ACLAccess ac = new ACLAccess();

  
  
  /**	
   * Display list of Resource Types 
   * @param mapping - ActionMapping
   * @param form - ActionForm
   * @param request - HttpServletRequest
   * @param response - HttpServletResponse
   * return ActionForward object
   */
  	
  	public ActionForward viewResourceTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
                                        HttpServletResponse response) throws IOException, ServletException {
  		
  		System.out.println("ResourceTypeAction - viewResourceTypes...");
  		HttpSession session =  request.getSession();
  		ActionErrors errors = new ActionErrors();
  		try {
  			//intializing the policylist.jsp
  			init(mapping,form,request,response);
  			
  			//removing mode from session
  			session.removeAttribute("mode");
  		} catch(Exception e){
  			e.printStackTrace();
  			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
  		}  		
  		return mapping.findForward("resourcetypes");
  	}

  public ActionForward init(ActionMapping mapping, ActionForm form, 
      HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {  

    System.out.println("ResourceTypeAction - init...");
    ActionErrors err = new ActionErrors();  

    // Extracts nav, categoryId, menuId and nextAction parameters from a request object and sets
    // nextAction, categoryId,categoryValue, categories, navBar, menuId, menus, menuBar in session
    // categoryId left unchanged if null or showlist=1
    // nav=resetMenu, resetCat removes those attributes only, reset removes both
    super.setNavigation(mapping,form,request,response);

    Locale locale = getLocale(request);
    //MessageResources messages = getResources();
    HttpSession session = request.getSession();
    String langCd = locale.getLanguage();

    // String categoryId = (String)session.getAttribute("categoryId");

    try {
      
      // do something
      List resourceTypes = ac.getAllResourceTypes();
      request.setAttribute("resourceTypes", resourceTypes);
     
    } catch(Exception e) {
      e.printStackTrace();
      err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
    }
   
    // if errors, return to welcome page via index.jsp
    if (!err.isEmpty()) {
      saveErrors(request, err);
      // request.setAttribute("nextAction", "xxx.do?method=yyy");
      return mapping.findForward("index");
    }

    // category.jsp will need this to provide link to action class
    // request.setAttribute("nextAction", "xxx.do?method=yyy");

    return mapping.findForward("resourcetypes");
  }


  /**
   * Sets mode to Add, for adding new Resource
   * @param mapping - ActionMapping
   * @param form - ActionForm
   * @param request - HttpServletRequest
   * @param response - HttpServletResponse
   * return ActionForward object
   */

  	public ActionForward addResourceTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
                                   HttpServletResponse response) throws IOException, ServletException {
  		
  		System.out.println("ResourceAction - addResourceType.....");
  	  	    
  	    //set mode. if mode is add/edit, resourceList jsp will include resource.jsp
      	DynaValidatorForm resourceTypeForm = (DynaValidatorForm) form;
      	resourceTypeForm.set("mode","add");
  	    
      
      	//intializing the resourcelist.jsp
      	init(mapping, form, request, response);		    
      	return mapping.findForward("resourcetypes");
  	}


  /**
   * Sets mode to Edit, for updating the Resource Details
   * @param mapping - ActionMapping
   * @param form - ActionForm
   * @param request - HttpServletRequest
   * @param response - HttpServletResponse
   * return ActionForward object
   */
  	
  	public ActionForward editResourceTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
                                    HttpServletResponse response) throws IOException, ServletException {

  		System.out.println("ResourceAction - editResourceType...");
  		ActionErrors errors = new ActionErrors();
  		DynaValidatorForm resourceTypeForm = (DynaValidatorForm) form;
  				
  		try {
  			String resourceTypeId = request.getParameter("resourceTypeId");
  						
  			//set mode. if mode is add/edit, resourceList jsp will include resource.jsp			
  	    	resourceTypeForm.set("mode","edit");
  			
  			if(resourceTypeId != null) {
  				
  				ResourceTypeValue resourceTypeValue = ac.getResourceType(resourceTypeId);
  				setResourceTypes(form, resourceTypeValue);
  				
  				//intializing the resourcelist.jsp
  				init(mapping, form, request, response);
  			}
  		} catch(Exception e){
  			e.printStackTrace();
  			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
  		}  		
  		return mapping.findForward("resourcetypes");
  	}
  	
  	
  /**
   * Saves/Update Resource Details
   * @param mapping - ActionMapping
   * @param form - ActionForm
   * @param request - HttpServletRequest
   * @param response - HttpServletResponse
   * return ActionForward object
   */

  	public ActionForward saveResourceTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
  			                      HttpServletResponse response) throws IOException, ServletException {  
    
  		System.out.println("ResourceAction - saveResourceType...");
  	    ActionErrors errors = new ActionErrors();  
  	    HttpSession session = request.getSession();
  	    DynaValidatorForm resourceTypeForm = (DynaValidatorForm) form;
  	    
  	    //String serviceId = (String) session.getAttribute("serviceId");                      
  	    String mode = (String) resourceTypeForm.get("mode");
  	 
  	    ResourceTypeValue resourceTypeValue = getResourceTypes(form);
  	    
  	    try {    		    	 	    	

			if (mode.equalsIgnoreCase("add")) { 		        	
  		    		try {
						ac.addResourceType(resourceTypeValue);  
						resourceTypeForm.set("mode","view");
					} catch (Exception e) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.addRecord"));

					}
  		    		
  	    	} else if (mode.equalsIgnoreCase("edit")) {
  		    		//updating the resource type
  		    		ac.saveResourceType(getResourceTypes(form));
  		    		
  		    		//do not want to see role.jsp in resourcetypes.jsp
  	  		        resourceTypeForm.set("mode","view");
  		    }  		

			init(mapping,form,request,response);
			
  	    } catch(Exception e) {
  	    	e.printStackTrace();
  	    	errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.operation"));
  	    }
  	   
  	    // if errors, return to welcome page via index.jsp
  	    if (!errors.isEmpty()) {
  	    	saveErrors(request, errors);
  	    	//request.setAttribute("nextAction", "xxx.do?method=yyy");
  	    	return mapping.findForward("resourcetypes");
  	    }
  	
  	    // category.jsp will need this to provide link to action class
  	    // request.setAttribute("nextAction", "xxx.do?method=yyy");
  	    return mapping.findForward("resourcetypes");
  	}
  	
  	
  /**
   * Removes selected Resource/Resources from the list of Resource 
   * @param mapping - ActionMapping
   * @param form - ActionForm
   * @param request - HttpServletRequest
   * @param response - HttpServletResponse
   * return ActionForward object
   */

  	public ActionForward removeResourceTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request,		
                                      HttpServletResponse response) throws IOException, ServletException {
  	    
  		System.out.println("ResourceAction - removeResourceType...");     
  		ActionErrors errors = new ActionErrors();
  		String[] resourceTypeId = request.getParameterValues("resourceTypeId");
  		
  		try {
  			if (request.getParameterValues("resourceTypeId") != null) {
  				// deleting a resource from the service
  				for(int i = 0; i < resourceTypeId.length; i++){
  						ac.removeResourceType(resourceTypeId[i]);
  				   }
  			}
  			
  			init(mapping,form,request,response);				
  		}  catch(Exception e){
  			e.printStackTrace();
  			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
  		}  		
  		return mapping.findForward("resourcetypes");
  	}
  	
  	
  /**
   * Fetches input data from the form and sets into value class
   * @param form - ActionForm
   * return void
   */
  	
  	private ResourceTypeValue getResourceTypes(ActionForm form) {
  		DynaValidatorForm resourceTypeForm = (DynaValidatorForm)form;
  
  		ResourceTypeValue resourceTypeValue = new ResourceTypeValue();
  		resourceTypeValue.setResourceTypeId((String)resourceTypeForm.get("resourceTypeId"));
  		resourceTypeValue.setDescription((String)resourceTypeForm.get("description"));
  		
  		return resourceTypeValue;
  	}
  	

  /**
   * Takes values from value class object and sets it into form
   * @param form - ActionForm
   * @param form - RoleValue
   * return void
   */	
  	private void setResourceTypes(ActionForm form, ResourceTypeValue resourceTypeValue) throws Exception {
     		DynaValidatorForm resourceTypeForm = (DynaValidatorForm) form;
  	 		resourceTypeForm.set("resourceTypeId",resourceTypeValue.getResourceTypeId());
  	     	resourceTypeForm.set("description",resourceTypeValue.getDescription());         
  	}


}