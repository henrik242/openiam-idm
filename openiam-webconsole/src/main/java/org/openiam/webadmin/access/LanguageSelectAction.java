/*
* ------------------------------------------------------------------------------
* Title: org.openiam.webadmin.access.LanguageAction
* Author: Diamelle Technologies 
* Overview: Provides methods to manage Language Code information
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
* USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
*
* IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
* RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
* CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
* DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
* ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
* DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
* ------------------------------------------------------------------------------
* CHANGE CONTROL:
* Last modified by : APS
* on : May 21 2005 
* ------------------------------------------------------------------------------
*/



package org.openiam.webadmin.access;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;  

//----------: Struts Packages
import org.apache.struts.action.*;
import org.apache.struts.validator.DynaValidatorForm;

//----------: Diamelle App Packages
import org.openiam.webadmin.busdel.base.*;



public class LanguageSelectAction extends NavigationDispatchAction {
	
  
  
  ServiceAccess serviceAccess;

   
  /** 
   * The default Constructor instantiates all the Access Classes.
   */
  public LanguageSelectAction() {
   try {
   		serviceAccess = new ServiceAccess();
   } catch (Exception e) {
 		e.printStackTrace();
   }
  }
  
  
  /** 
   * Fetches all Language Codes 
   */
  public ActionForward viewLanguages(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response)
	  throws IOException, ServletException {
	
	ActionErrors errors = new ActionErrors();
	try {	
		Map languages = null; // (Map) serviceAccess.getAllLanguages();
		request.setAttribute("languages",languages);   	
    } catch (Exception e) {
		e.printStackTrace();
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
	    return (mapping.findForward("menulist"));
	}  	
 	  	
 	return mapping.findForward("language");
  }
    
 
	public ActionForward setLanguage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		try {

			String langCd = (String)request.getParameter("languageCd");
			if ((langCd == null)||(langCd.length() == 0)){
				langCd = "en";
			}
			session.setAttribute("languageCd", langCd);
			
		} catch (Exception e) {
			e.printStackTrace();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("menulist");
	}
  

}
