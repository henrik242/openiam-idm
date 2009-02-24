/**
 * ------------------------------------------------------------------------------
 * Title: UserAction
 * Author: LD 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * list of users.
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

package org.openiam.selfsrvc.prov;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;

import diamelle.base.composite.Component;
import diamelle.base.composite.ComponentFactory;
import diamelle.common.meta.MetadataElementValue;
import diamelle.common.service.Service;
import diamelle.common.service.ServiceMgr;
import diamelle.common.status.StatusCodeValue;

import diamelle.common.org.*;
import diamelle.ebc.user.*;
import diamelle.security.auth.*;
import diamelle.security.idquest.QuestionValue;
import diamelle.security.policy.PolicyAttrValue;
import diamelle.security.policy.PolicyConstants;
import diamelle.security.token.*;
import diamelle.util.Log;
import java.io.*;

import java.text.ParseException;
import java.util.*;
import java.rmi.RemoteException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class MyTasksAction extends NavigationDispatchAction  {
	UserAccess userAccess = null;
	AuditLogAccess logAccess = null;
	MetadataAccess metaAccess = null;
	IdQuestionAccess questAccess = new IdQuestionAccess();
	PolicyAccess policyAccess = new PolicyAccess();

	
	public MyTasksAction() {
		try {
		userAccess = new UserAccess();
		logAccess = new AuditLogAccess();
		metaAccess = new MetadataAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public ActionForward view(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse res) throws  IOException, ServletException {
		
System.out.println("in MyTasksAction view...");		
       try {          
           HttpSession session = request.getSession();

           
        } catch(Exception e) {
            e.printStackTrace();
        }
       return (mapping.findForward("success"));
	
	}
	


	
 
}

