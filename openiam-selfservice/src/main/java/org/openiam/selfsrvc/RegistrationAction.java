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

package org.openiam.selfsrvc;

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


public class RegistrationAction extends NavigationDispatchAction  {
	UserAccess userAccess = null;
	AuthenticatorAccess authAccess = null; 
	AuditLogAccess logAccess = null;
	TokenAccess tknAccess = null;
//	OrganizationAccess orgAccess = null;
//	ServiceAccess serviceAccess = null;
	MetadataAccess metaAccess = null;
	IdQuestionAccess questAccess = new IdQuestionAccess();
	PolicyAccess policyAccess = new PolicyAccess();

	
	public RegistrationAction() {
		try {
		userAccess = new UserAccess();
		authAccess = new AuthenticatorAccess();
		logAccess = new AuditLogAccess();
		tknAccess = new TokenAccess();
//		orgAccess = new OrganizationAccess();
//		serviceAccess = new ServiceAccess();
		metaAccess = new MetadataAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ActionForward view ( ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse res ) throws IOException, ServletException {


        return mapping.findForward("view");
    }
	
	public ActionForward showNewUserForm(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse res) throws  IOException, ServletException {
		
		Map metadataMap = null;
		
       try {          
           HttpSession session = request.getSession();
           DynaValidatorForm userForm = (DynaValidatorForm)form;
           userForm.set("personId", "" );
           String userTypeId = (String)userForm.get("typeId");
           if (userTypeId != null && userTypeId.length()>0) {
           	metadataMap = metaAccess.getMetadataByType(userTypeId);
           	request.setAttribute("metadata", metadataMap);
           }
           request.setAttribute("typeId", userTypeId);
           
        } catch(Exception e) {
            e.printStackTrace();
        }
       return (mapping.findForward("success"));
	
	}
	


	
   /** 
     * Creates a new User or Saves a Users details if its an existing user.
     */
     public ActionForward saveUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
        UserData userData = new UserData();
        Address adr = AddressFactory.create();
        Email email = EmailFactory.create();
        Phone cellPhone = PhoneFactory.create();
        Phone workPhone = PhoneFactory.create();
        Phone faxPhone = PhoneFactory.create();
        String personId = null;
      //  String submit = null;
        String logMsg = null;
        Component comp = ComponentFactory.create();
        
        

        try {
           ActionErrors errors = null;
           
           HttpSession session = request.getSession();          
           DynaValidatorForm userForm = (DynaValidatorForm)form;

           // is actually the personId
           personId = (String) userForm.get("personId");
           if (personId != null)
            request.setAttribute("personId", personId);


 		  userData = getUserDetails(userData, userForm);
          formToAddress(adr,userForm);
          formToEmail(email,userForm);
          formToPhone(workPhone,userForm,"WORK");
          formToPhone(cellPhone,userForm,"CELL");
          formToPhone(faxPhone,userForm,"FAX");

           if (personId != null && personId.length() > 0){
     		   userData.setId(personId);
     		   
     		   if (userForm.get("submit").equals("Unlock User")) {
     		   		//userData.setStatusId("");
     		   }
     		   if (userForm.get("submit").equals("Blacklist User")){
     		   		userData.setStatusId("BLACK LISTED");
     		   		userForm.set("userStatus","BLACK LISTED");
     		   		logMsg = "User id=" + personId + " BLACK LISTED";
     		   }
     		   if (userForm.get("submit").equals("Delete User")){
 		   		userData.setStatusId("DELETED");
 		   		userForm.set("userStatus","DELETED");
 		   		logMsg = "User id=" + personId + " DELETED";
     		   }
     		   
     		  // update an existing user record
               userAccess.saveUser(userData);
               // LOG the event
               if (logMsg == null) {
               	logMsg = "User id=" + personId + " UPDATED";
               }
               AuditLogAccess.logEvent(logMsg, request.getRemoteHost(), 
               		(String)session.getAttribute("userId"),
               		(String)session.getAttribute("login"),"IDM");
               
               adr.setParentId(personId);
               userAccess.saveAddress(personId,adr);
               
               email.setParentId(personId);
               userAccess.saveEmail(personId,email);
               
               workPhone.setParentId(personId);
               cellPhone.setParentId(personId);
               faxPhone.setParentId(personId);
               userAccess.savePhone(personId,workPhone);
               userAccess.savePhone(personId,cellPhone);
               userAccess.savePhone(personId,faxPhone);

	              // get the attributes
               updateAttributes(request,personId, userAccess, userData.getTypeId());
               
                
           } else {
           	  // new user
           	  // validate login and password fields
           	  String login = (String)userForm.get("login");
           	  String password = (String)userForm.get("password");
           	  String confPassword = (String)userForm.get("confirmpassword");
           	  
           	  // validation cannot be done in the form as there 2 different sets 
           	  // of fields in the view and edit
      	  	  errors = validateLogin(errors,login,password, confPassword);    	  	  
      	  	  errors = isDuplicate(errors, login, (String) userForm.get("service"));

      	  	  if (errors != null) {     	  	 
      	  		  saveErrors(request, errors);
      	  	  }
      	  	                    
              if (errors == null) {
              	  String createdId = (String)session.getAttribute("userId");

               	  userData.setCreatedBy(createdId);
              	  userData.setCreateDate(new Timestamp(System.currentTimeMillis()));
	              
              	  userAccess.createUser(userData);
	                                             
				  request.setAttribute("personId", userData.getId());

	              // new - sas- 12-13-2003
	              personId = userData.getId();
	              adr.setParentId(personId);
	              userAccess.saveAddress(personId,adr);
	               
	              email.setParentId(personId);
	              userAccess.saveEmail(personId,email);
	               
	              workPhone.setParentId(personId);
	              cellPhone.setParentId(personId);
	              faxPhone.setParentId(personId);
	              userAccess.savePhone(personId,workPhone);
	              userAccess.savePhone(personId,cellPhone);
	              userAccess.savePhone(personId,faxPhone);

             	  LoginValue lv = new LoginValue();
	              lv.setLogin( login);
	              if (password != null )
	              	lv.setPassword( password);
	              lv.setService( (String) userForm.get("service"));
	              lv.setUserId(userData.getId());
	              lv.setResetPassword(true);
	              //lv.setNewUser(true);
	              lv.setChangePassword(new Timestamp(System.currentTimeMillis()));
	              authAccess.addLogin(lv);
	              
	              // password is set in the login bean. need to get it out so that we
	              // can show the user.
	              lv = authAccess.getLogin((String) userForm.get("service"),login);

	              String msg = "Password has been auto set to: " + lv.getPassword();
	              request.setAttribute("msg",msg);	              
	              // save the default group
	              String group = (String)userForm.get("group");
	              userAccess.addUserGroup(userData.getId(),group);
         
	              // get the attributes
	              addAttributes(request,personId, userAccess);
           
	              // log the event
	              AuditLogAccess.logEvent("User id=" + userData.getId() + " created",
                  		request.getRemoteHost(),createdId,  
                   		(String)session.getAttribute("login"),"IDM");

	     
              }
           }
           this.editUser(request, userForm, "IDENTITIES");
	              
        } catch(Exception e) {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
            saveErrors(request, errors);
            e.printStackTrace();
        }
        return (mapping.findForward("success"));
    }
     
     private void  addAttributes(HttpServletRequest request, String personId, UserAccess userAccess ) throws RemoteException {
     	Component comp = null;
        java.util.Enumeration<String> en =  request.getParameterNames();
        if (en != null) {
     	   while (en.hasMoreElements()) {
     		   String fieldName = (String)en.nextElement();
     		   // look for our special marker '*'
     		   if (fieldName.startsWith("*")) {
     			   String value = request.getParameter(fieldName);
     			   comp = ComponentFactory.create();
     			   int size = fieldName.length();
     			   String elementName = fieldName.substring(1,size);
     			   int indx = elementName.indexOf("-");
     			   String name = elementName.substring(0,indx);
     			   String metadataId = elementName.substring(indx+1,--size);
     			   comp.setName(name);
     			   comp.setParentId(personId);
     			   comp.setValue(value);
     			   comp.setMetadataId(metadataId);
     			   userAccess.saveAttribute(comp, personId);
     		   }
     	   }
        }     	
     }
     
     private void updateAttributes (HttpServletRequest request, String personId, 
     		UserAccess userAccess,String typeId ) throws RemoteException {
     	// for each item in metadata, check if we have a value.
     	// if we do, then update it. if we don't add it.
     	Map metadataMap =  metaAccess.getMetadataByType(typeId);
     	Map userAttr = userAccess.getAllAttributes(personId);
     	
     	Collection col = metadataMap.values();
     	Iterator it = col.iterator();
     	while (it.hasNext()) {
     		MetadataElementValue elementVal = (MetadataElementValue)it.next();
     	 	String webParamName =  "*" + elementVal.getAttributeName() + "-" + elementVal.getMetadataId();
     	 	String webParamValue = request.getParameter(webParamName);
     	 	Component comp = getComponent(userAttr, elementVal.getAttributeName());
     	 	if (comp != null ) {
     	 		// update
     	 		if (webParamValue == null) {
     	 			comp.setValue("");
     	 		}else {
     	 			comp.setValue(webParamValue); 
     	 		}
     	 	}else {
     	 		// new
      	 	   if (comp == null) {
     	 	   		comp = ComponentFactory.create();
     	 	   }
     	 	   if (elementVal != null) {
	  			   comp.setName(elementVal.getAttributeName());
	 			   comp.setParentId(personId);
	 			   if (webParamValue == null) {
	 			   	comp.setValue("");
	 			   }else {
	 			   	comp.setValue(webParamValue);
	 			   }
	 			   comp.setMetadataId(elementVal.getMetadataId());
     	 	   }
      	 	}
		   userAccess.saveAttribute(comp, personId);   	 	
     	}
     	
     }
     private Component getComponent(Map userAttr, String attrName) {
     	if (userAttr == null || userAttr.isEmpty()) {
          	return null;
     	}
          	
		Collection col = userAttr.values();
		Iterator it = col.iterator();
		while (it.hasNext()) {
			Component cmp = (Component)it.next();
			if (cmp.getName().equals(attrName)) {
				return cmp;
			}
		}
		return null;
     }
     
     private ActionErrors isDuplicate(ActionErrors errors,String login, String serviceId) {
     	try {

     	LoginValue val = authAccess.getLogin(serviceId, login);

     	if (val != null) {
  	  		if (errors == null) {
  	  			errors = new ActionErrors();
  	  		}
  	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));

     	}
     	}catch(Exception e) {
     		e.printStackTrace();
     	}
     	return errors; 	
     }

     private  ActionErrors validateLogin(ActionErrors errors, String login, 
     							String password, String confPassword) {
 
	  	
     	 if (login == null || login.length() ==0) {
  	  		if (errors == null) {
  	  			errors = new ActionErrors();
  	  		}
  	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));

  	 	}
      	if (password != null ) {
	     	if (!password.equals(confPassword)) {
	  	  		if (errors == null) {
	  	  			errors = new ActionErrors();
	  	  		}
	  	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));

	  	 	}
     	}
     	return errors;

     }
 
     /**
      * Forwards it to the input page while validations are done
      * according to the add or edit function 
      */
     public ActionForward userValidate ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {

        try {

           String personId = request.getParameter("personId");
           DynaValidatorForm userForm = (DynaValidatorForm) form;
           
           if (personId != null && personId.length() > 0) {
               userForm.set("personId", personId);
               Object phoneList = getTabDetail("PHONE", personId);
               request.setAttribute("detailView", phoneList);
               request.setAttribute("personId",personId);
               request.setAttribute("tabOptions", setActiveTab("IDENTITIES"));
           }
        } catch(Exception e) {
             e.printStackTrace();
        }
        return (mapping.findForward("success"));
    }


    /**
     * Deletes a User and all its dependencies
     */
    public ActionForward deleteUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {

        try {
           HttpSession session = request.getSession();
           String personId = request.getParameter("personId");
           UserAccess userAccess = new UserAccess();
           
           UserData ud = userAccess.getUser(personId);
           if (ud != null) {
           	ud.setStatusId("DELETED");
           	userAccess.saveUser(ud);
            AuditLogAccess.logEvent("User id=" + personId + " has been deleted.",
              		request.getRemoteHost(),(String)session.getAttribute("userId"),  
               		(String)session.getAttribute("login"),"IDM");
           }
 
        } catch(Exception e) {
            e.printStackTrace();
        }
    	//return (mapping.findForward("success"));
        return (mapping.findForward("search"));
    }




    /**
     * Retrieves a list of Login information for a User
     */
    public ActionForward identities ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
    	String mode = null;
    	List statusList = null;
    	HttpSession session = request.getSession();
        try {

           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";
           
           Object identities = getTabDetail("IDENTITIES", personId);
           request.setAttribute("detailView", identities);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Identities");

          	statusList = (List)session.getAttribute("statusList");
        	
        	if (statusList == null) {
	            statusList = getUserStatusList();
	            session.setAttribute("statusList", statusList);        		
        	}
           
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));

        return (mapping.findForward("success"));
    }

    /**
     * Retrieves a list of Attributes for a User
     */
    public ActionForward attributes ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
       	String mode = null;
        try {
           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";
           
           Object attributes = getTabDetail("ATTRIBUTE", personId);
           request.setAttribute("detailView", attributes);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Attributes");

        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));

        return (mapping.findForward("success"));
    }


    
    /**
     * Retrieves a list of Emails for a User
     */
/*    public ActionForward email ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
       	String mode = null;

        try {
           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";
           
           Object emails = getTabDetail("EMAIL", personId);
           request.setAttribute("detailView", emails);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Email");


        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));
        return (mapping.findForward("success"));
    }
*/

    /**
     * Retrieves a list of Groups and the Groups to which a User belongs
     */
    public ActionForward userGroup ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
       	String mode = null;

        try {

           UserAccess userAccess = new UserAccess();
           SecurityAccess securityAccess = new SecurityAccess();

           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";
           
           Object groupList = securityAccess.getAllGroups();
           request.setAttribute("detailView", groupList);
           request.setAttribute("groupList", groupList);

           List userList = userAccess.getUserGroups(personId);

           request.setAttribute("userList",userList);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Group");


        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));
        return (mapping.findForward("success"));
    }

  /*  public ActionForward log ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
       	String mode = null;

        try {

           UserAccess userAccess = new UserAccess();
           SecurityAccess securityAccess = new SecurityAccess();

           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";
           
           Object groupList = securityAccess.getAllGroups();
           request.setAttribute("detailView", groupList);

           List userList = userAccess.getUserGroups(personId);
           request.setAttribute("userList",userList);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Log");


        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));
        return (mapping.findForward("success"));
    }
*/
    public ActionForward survey ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
       	String mode = null;
 		Context ctx = null;
		String serviceId = null;
		String login = null;
		String strQuestionCount = null;
		String questionSrc = null;
		String strQuestionList = null;
		List questionDropdownList = null;
		
       	try {
     	   HttpSession session = request.getSession();

           UserAccess userAccess = new UserAccess();
           SecurityAccess securityAccess = new SecurityAccess();

           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null) {
           	mode = "VIEW";
           }
           
          // boolean selfServFound = false;
   		   List loginList = authAccess.getAllLogins(personId);
		   if (loginList != null && loginList.size() > 0) {
				int size = loginList.size();
				for (int i=0; i < size; i++) {
					LoginValue val = (LoginValue)loginList.get(i);
					//if (val.getService().equals("SELFSERVICE")) {
					serviceId = val.getService();
					login = val.getLogin();		
					//	selfServFound = true;
					//}
				}
			}

		   if (login != null) {
				List policyAttr = policyAccess.getPolicyForLogin(PolicyConstants.POLICY_TYPE_PASSWORD,serviceId, login);
				// get the parameters
				int size = policyAttr.size();
				for (int i=0; i < size; i++) {
					PolicyAttrValue attrValue = (PolicyAttrValue)policyAttr.get(i);
					if (attrValue.getName().equals("QUEST_COUNT")) {
						strQuestionCount = attrValue.getValue1();	
						request.setAttribute("questCount",  strQuestionCount);
					}
					if (attrValue.getName().equals("QUEST_SRC")) {
						questionSrc = attrValue.getValue1();			
						request.setAttribute("questionSrc",  questionSrc);
					}			
					if (attrValue.getName().equals("QUEST_LIST")) {
						strQuestionList = attrValue.getValue1();		
					}
				} 
				List questionList = null;
				List answerList = questAccess.getUserAnswers(personId);
				if (answerList == null || answerList.isEmpty()) {					
					request.setAttribute("mode","new"); 
				}else {
					request.setAttribute("mode","edit");
				}
				questionList = getQuestions(questionSrc, strQuestionList);
				request.setAttribute("answerList", answerList);
				request.setAttribute("questionDropdown", getQuestionDropDownList(questionList));
				request.setAttribute("personId", personId);
		   }
		   
           
           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Q and A");


        } catch(Exception e) {
            e.printStackTrace();
        }
        if (login == null) {
        	request.setAttribute("identityMissing","Please create an identity first.");
        }
        
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));

        return (mapping.findForward("success"));
    }
    
	private List getQuestions(String questionSrc, String questionList) throws RemoteException {
		// get the answers for this user
		// if none exist, then get the list of questions from the policy
		// if the questions are defined in the policy then get the list of questions
		// if they are user selected then get the list of questions in the question bank.
		if (questionSrc.equalsIgnoreCase("USER")) {
			// user needs to pick the questions.
			return this.questAccess.getAllQuestions();
		}else {
			// policy based definition of which questions to use.
			return this.questAccess.getSelectedQuestions(questionList);
		}		
	}
	
	private List getQuestionDropDownList(List answerList) throws RemoteException {
    	ArrayList dropdownList = new ArrayList();
         if (answerList != null && answerList.size() > 0) {
         	dropdownList.add(new LabelValueBean("",""));
        	for (int i=0; i < answerList.size(); i++) {       		
        		QuestionValue  val = (QuestionValue)answerList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getQuestionText(),val.getQuestionId());
        	 	dropdownList.add(label);
        	}
        }
        return dropdownList;
    }

    /**
     * Retrieves a list of Phone details of the User
     */
    public ActionForward phone ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
        String mode = null;
        // selectUserId is the selectedUserId that we are working on.
        // not the userId of the person that is logged in.       
        try {
           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";

           if (personId == null || personId.length() == 0)
           	personId = (String) request.getAttribute("personId");

           Object phoneList = getTabDetail("PHONE", personId);
           request.setAttribute("detailView", phoneList);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           editUser(request, userForm, "Phone");

        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
    		return (mapping.findForward("success_view"));
        return (mapping.findForward("success"));
    }

    /**
     * Retrieves a list of Addresses of the User
     */
    public ActionForward address ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
       // HttpSession session = request.getSession();
        String mode = null;
        
        try {
           String personId = request.getParameter("personId");
           mode= request.getParameter("mode");
           if (mode == null)
           	mode = "VIEW";

           Object addressList = getTabDetail("ADDRESS", personId);
           request.setAttribute("detailView", addressList);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Address");

        } catch(Exception e) {
            e.printStackTrace();
        }
        if (mode.equals("VIEW"))
        		return (mapping.findForward("success_view"));
        return (mapping.findForward("success"));
    }
    

    public ActionForward history ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
        //HttpSession session = request.getSession();
        String mode = null;
        
        try {
           String personId = request.getParameter("personId");
           if (personId == null) {
           	// coming here from the notes popup
           	HttpSession session = request.getSession();
           	personId = (String)session.getAttribute("personId");
           }

           Object historyList = getTabDetail("HISTORY", personId);
           request.setAttribute("detailView", historyList);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "History");

        } catch(Exception e) {
            e.printStackTrace();
        }
        //set the mode to handle the view / edit
        mode= request.getParameter("mode");
        if (mode == null)
           	mode = "VIEW";
        if (mode.equals("VIEW"))
        	return (mapping.findForward("success_view"));
        
        return (mapping.findForward("success"));
    }

    public ActionForward token ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) throws IOException, ServletException {
        //HttpSession session = request.getSession();
        String mode = null;
       
        HttpSession session = request.getSession();
   
        
        try {
           String personId = request.getParameter("personId");
           if (personId == null)
           	personId = (String)session.getAttribute("personId");
            // check the status of this user
           UserData ud = userAccess.getUser(personId);
           request.setAttribute("status", ud.getStatusId());

           //UserAccess userAccess = new UserAccess();
           TokenRequestValue userToken = (TokenRequestValue)getTabDetail("TOKEN", personId);
           if ( userToken != null && 
           		userToken.getTokenSerialNbr() != null) {
           		TokenValue tokenValue = this.tknAccess.getToken( userToken.getTokenSerialNbr() );
           		request.setAttribute("tokenDetail", tokenValue);
           }
          
           request.setAttribute("token", userToken);

           DynaValidatorForm userForm = (DynaValidatorForm) form;
           this.editUser(request, userForm, "Token");

        } catch(Exception e) {
            e.printStackTrace();
        }
        //set the mode to handle the view / edit
        mode= request.getParameter("mode");
        if (mode == null)
           	mode = "VIEW";
        if (mode.equals("VIEW"))
        	return (mapping.findForward("success_view"));
        
        return (mapping.findForward("success"));
    }


    /** Private methods **/

    /**
     *  Retrieves the information of the user
     */
    private void editUser (HttpServletRequest request, DynaValidatorForm userForm, String activeTab){
        HttpSession session = request.getSession();

        try {
           UserAccess userAccess = new UserAccess();
           
           String personId = request.getParameter("personId");
           if (personId == null) {
           	personId = (String)session.getAttribute("personId");
           }
           if (personId == null || personId.length() == 0)
           	personId = (String) request.getAttribute("personId");
           // for use in popups where we can not easily pass a parameter
           session.setAttribute("personId",personId);

           UserData userData = userAccess.getUser(personId);
           if (userData != null) {
              this.populateFormBean(userData, userForm);
              request.setAttribute("personData", userData);
              // get the phone, address and email
              Address adr = userAccess.getAddressByName(personId,"DEFAULT");
              Email email = userAccess.getEmailByName(personId,"DEFAULT");
              Phone workPhone = userAccess.getPhoneByName(personId,"WORK");
              Phone cellPhone = userAccess.getPhoneByName(personId,"CELL");
              Phone faxPhone = userAccess.getPhoneByName(personId,"FAX");
              addressToForm(userForm,adr);
              phoneToForm(userForm,workPhone, "WORK");
              phoneToForm(userForm,cellPhone, "CELL");
              phoneToForm(userForm,faxPhone, "FAX");
              emailToForm(userForm,email);
              // get the metadata for this user
              if (userData.getTypeId() != null) {
               	Map metadataMap = metaAccess.getMetadataByType(userData.getTypeId());
              	request.setAttribute("metadata", metadataMap);
              	// get the attributes for the metadata
              	Map attrMap =  userAccess.getAllAttributes(personId);
              	request.setAttribute("userAttr", attrMap);
              }
              // get the attributes for the metadata
                           
           }
           
           request.setAttribute("personId",personId);
           Object identities = getTabDetail(activeTab, personId);
           request.setAttribute("detailView", identities);

           request.setAttribute("tabOptions", setActiveTab(activeTab));

        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Retrieves the data from Form and sets in the UserData object
     */
    private UserData getUserDetails(UserData userData, DynaValidatorForm form) {
         CalendarUtil calUtil = new CalendarUtil();
         userData.setFirstName((String) form.get("firstName"));
         userData.setMiddleInit((String) form.get("middleName"));
         userData.setLastName((String) form.get("lastName"));

         //userData.setEMail((String) form.get("email"));
         userData.setUserInd((String) form.get("userInd"));
         userData.setDept((String) form.get("dept"));
         userData.setSex((String) form.get("sex"));
         userData.setTitle((String) form.get("title"));
         userData.setTaxCode((String) form.get("tax"));
         //userData.setCreateDate(new Timestamp(System.currentTimeMillis()));
         userData.setLstUpdated(new Timestamp(System.currentTimeMillis()));
         //userData.setCreatedBy(");
         
         String typeId = (String) form.get("typeId");         
         if (typeId != null && typeId.length() > 0)
            userData.setTypeId(typeId);
         
         String birthdate = (String)form.get("birthday");
         if (birthdate != null && birthdate.length() > 0)
            userData.setBirthdate(calUtil.getSqlDate(birthdate, "MM-dd-yyyy"));   
         
         String companyId = (String)form.get("companyId");
         if (companyId != null && companyId.length() > 0) {
            userData.setCompanyId((String) form.get("companyId"));           
         }
         
         String active = (String) form.get("active");        
         if (active.equalsIgnoreCase("on"))
            userData.setActive(true);
         else
            userData.setActive(false);
         
         userData.setStatusId((String) form.get("status"));  
         return userData;
    }

    private Address formToAddress(Address adr, DynaValidatorForm form) {
    	adr.setAddress1((String) form.get("address1"));
    	adr.setAddress2((String)form.get("address2"));
    	adr.setCity((String)form.get("city"));
    	adr.setDefault(true);
    	adr.setDescription("DEFAULT");
    	adr.setPostalCode((String)form.get("zip"));
    	adr.setState((String)form.get("state"));
    	adr.setId((String)form.get("addressId"));
    	adr.setCountry((String)form.get("country"));
    	return adr;
    	
    }
    private Email formToEmail(Email email, DynaValidatorForm form) {
    	email.setId((String)form.get("emailId"));
    	email.setEmailAddress((String)form.get("email"));
    	email.setDescription("DEFAULT");
    	email.setDefault(true);
    	return email;
    	
    }
    private Phone formToPhone(Phone ph, DynaValidatorForm form, String phoneType) {
    	if (phoneType.equals("WORK")) {
    		ph.setAreaCode((String)form.get("phone_areacd"));
    		ph.setDescription("WORK");
    		ph.setPhoneNumber((String)form.get("phone_nbr"));
    		ph.setId((String)form.get("workPhoneId"));
    	}
    	if (phoneType.equals("CELL")) {
    		ph.setAreaCode((String)form.get("cell_areacd"));
    		ph.setDescription("CELL");
    		ph.setPhoneNumber((String)form.get("cell_nbr"));
    		ph.setId((String)form.get("cellPhoneId"));
    		
    	}
    	if (phoneType.equals("FAX")) {
    		ph.setAreaCode((String)form.get("fax_areacd"));
    		ph.setDescription("FAX");
    		ph.setPhoneNumber((String)form.get("fax_nbr"));
    		ph.setId((String)form.get("faxPhoneId"));
   		
    	}
    	return ph;
    	
    }
   
    private void addressToForm(DynaValidatorForm form, Address adr) {
    	if (adr != null) {
       	form.set("address1",adr.getAddress1());
    	form.set("address2",adr.getAddress2());
    	form.set("city", adr.getCity());
    	form.set("zip",adr.getPostalCode());
    	form.set("state",adr.getState());
    	form.set("addressId",adr.getId());
    	form.set("country", adr.getCountry());
    	}
   	
    }
    private void phoneToForm(DynaValidatorForm form, Phone ph, String phoneType) {
       	if (ph == null)
       		return;
    	if (phoneType.equals("WORK")) {
    		form.set("phone_areacd",ph.getAreaCode());
    		form.set("phone_nbr",ph.getPhoneNumber());
    		form.set("workPhoneId",ph.getId());
    	}
    	if (phoneType.equals("CELL")) {
    		form.set("cell_areacd",ph.getAreaCode());
    		form.set("cell_nbr",ph.getPhoneNumber());
    		form.set("cellPhoneId",ph.getId());
    		
    	}
    	if (phoneType.equals("FAX")) {
    		form.set("fax_areacd",ph.getAreaCode());
    		form.set("fax_nbr",ph.getPhoneNumber());
    		form.set("faxPhoneId",ph.getId());
   		
    	}
    	
    }
    private void emailToForm(DynaValidatorForm form,Email email) {
    if (email != null) {
       	form.set("emailId",email.getId());
    	form.set("email",email.getEmailAddress());
    } 	
    }

    /**
     * Retrieves information from UserData and sets it in Form
     */
    private void populateFormBean(UserData ud, DynaValidatorForm uform) {
        CalendarUtil calUtil = new CalendarUtil();

      uform.set("personId", ud.getId());

      uform.set("firstName", ud.getFirstName());
      uform.set("middleName", ud.getMiddleInit());
      uform.set("lastName", ud.getLastName());

      uform.set("email", ud.getEmail());
      uform.set("userInd", ud.getUserInd());

      uform.set("typeId", ud.getTypeId());
      uform.set("dept", ud.getDept());
      uform.set("companyId", ud.getCompanyId());
      if (ud.getCompanyId() != null) { 
     // 	try {
	       // OrganizationValue val = orgAccess.getOrganization(ud.getCompanyId());
	       // if (val != null) {
	       // 	uform.set("companyName", val.getOrganizationName());
	        //}
      	//}catch(RemoteException e) {
      	//	Log.error(e.getMessage(),e);
      //	}

      }
      
      uform.set("sex", ud.getSex());
      Timestamp createTime = ud.getCreateDate();
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

      if (ud.isActive())
         uform.set("active", "on");
      
      //if (ud.getStatusId() != null )
      //   uform.set("status", ud.);
      uform.set("status",ud.getStatusId());   
      uform.set("tax", ud.getTaxCode());
      uform.set("title", ud.getTitle());
      
    }


  
    /**
     * Sets the tabs
     */
    private List initTabOptions() {

      List l = new ArrayList();

      TabOption option = new TabOption("Group", false, "idman/user.do?method=userGroup", "usergrplist.jsp");
      l.add(option);

      option = new TabOption("Identities", false, "idman/user.do?method=identities", "viewidentities.jsp");
      l.add(option);

      option = new TabOption("History", false, "idman/user.do?method=history", "viewusernotes.jsp");
      l.add(option);
      
  //    option = new TabOption("Token", false, "user.do?method=token", "viewusertoken.jsp");
  //    l.add(option);
      
  //    option = new TabOption("Q and A", false, "user.do?method=survey", "viewsurvey.jsp");
  //    l.add(option);     
      
      return l;
    }

   /**
     * Sets the active tab, regenerating list each time
     */
    public List setActiveTab(String tab) {
       TabOption option = null;
       List optList = initTabOptions();
       int size = optList.size();

       for (int i=0; i<size; i++) {
         option = (TabOption) optList.get(i);


         if (option.getTitle().equalsIgnoreCase(tab))
           option.setActive(true);
         else
           option.setActive(false);
       }
       return optList;
    }
    public Object getTabDetail(String detailView,String personId) throws RemoteException {
        try {

        if (detailView.equalsIgnoreCase("PHONE")) {
          return userAccess.getAllPhoneNbrs(personId);
        }
        if (detailView.equalsIgnoreCase("ADDRESS")) {
          return userAccess.getAllAddresses(personId);
        }
        if (detailView.equalsIgnoreCase("EMAIL")) {
          return userAccess.getAllEmails(personId);
        }
        if (detailView.equalsIgnoreCase("IDENTITIES")) {
        	return authAccess.getAllLogins(personId);
        	//return authAccess.getPrincipals(personId);
        }
        if (detailView.equalsIgnoreCase("HISTORY")) {
        	return userAccess.getUserNotes(personId);
        }
        if (detailView.equalsIgnoreCase("TOKEN")) {
        	return tknAccess.getUserRequest(personId);
        }

        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
      }

    /* Load Static data */
    
    private Vector codeListToVector(List codes) {
	    Vector vct = new Vector();
		LabelValueBean emptybean = new LabelValueBean("<Select a value>", "-1");
		vct.add(emptybean);

		if (codes != null) {
		  String codeKey;
		  String codeDesc;
		  Iterator codeIter = codes.iterator();
		
	      while(codeIter.hasNext()) {
			StatusCodeValue codeValue = (StatusCodeValue) codeIter.next();
			codeKey = codeValue.getStatusCd();
			codeDesc = codeValue.getDescription(); 
			LabelValueBean bean = new LabelValueBean(codeDesc,codeKey);
			vct.add(bean);
		  }
		} 
		
		return vct;
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
	
	private List getCompanyList() {
	   	ArrayList newCodeList = new ArrayList();
//		try {
		//	OrganizationSearch search = new OrganizationSearch();
	/*		List companyList = orgAccess.searchOrganization(search);
	        if (companyList != null && companyList.size() > 0) {
        	newCodeList.add(new LabelValueBean("",""));
        	for (int i=0; i< companyList.size(); i++) {       		
        		CompanyData val = (CompanyData)companyList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getCompanyName(),val.getCompanyId());
        	 	newCodeList.add(label);
        	}
        }
	*/
	//	}catch(RemoteException re) {
	//		re.printStackTrace();
	//	}
	 //   return newCodeList;	
	return null;
	}

	
	private List getAllGroups() throws RemoteException {
    	ArrayList newGroupList = new ArrayList();
        SecurityAccess secAccess = new SecurityAccess();
        List groupList = secAccess.getAllGroups();
        
        if (groupList != null && groupList.size() > 0) {
        	newGroupList.add(new LabelValueBean("",""));
        	for (int i=0; i< groupList.size(); i++) {       		
        		GroupValue val = (GroupValue)groupList.get(i);
        	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
        	 	newGroupList.add(label);
        	}
        }
        return newGroupList;
    }

	private List getAllServices(ServletContext servletCtx) throws RemoteException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletCtx);
		ServiceMgr servMgr = (ServiceMgr)ctx.getBean("serviceManager");
		
    	ArrayList newServiceList = new ArrayList();
    	List serviceList = servMgr.getAllServices();
      
        if (serviceList != null && serviceList.size() > 0) {
        	newServiceList.add(new LabelValueBean("",""));
        	for (int i=0; i < serviceList.size(); i++ ) {
        		Service val = (Service)serviceList.get(i);
          	 	LabelValueBean label = new LabelValueBean(val.getServiceName(),val.getServiceId());
           	 	newServiceList.add(label);
        	}        		
        }
        return newServiceList;
    }	
	
	private void loadStaticData(HttpSession session, ServletContext servletCtx) throws RemoteException {
        if (session.getAttribute("services") == null) {
           	// we dont have the data for the drop downs.
           	session.setAttribute("services", getAllServices(servletCtx));
           	
           	// get the list of groups - so that we can have a default group
           	// for a newly created user
           	List groupList = this.getAllGroups();
           	session.setAttribute("groupList", groupList);
 
           	// show the status codes if they are not already populated    	
            List statusList = getUserStatusList();
	        session.setAttribute("statusList", statusList);
	        
	        // get the list of companies
	        List companyList = getCompanyList();
	        session.setAttribute("companyList", companyList);
        } 		
	}

}

