//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.webadmin.user;

import java.io.*;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import org.openiam.webadmin.busdel.identity.*;
import org.openiam.webadmin.busdel.security.AuditLogAccess;
import org.openiam.webadmin.busdel.base.*;

//import org.openiam.idm.connector.ldap.*;
//import org.openiam.idm.connector.*;

import diamelle.common.user.UserNoteValue;
import diamelle.ebc.user.*;
import diamelle.security.log.AccessLogValue;
import diamelle.util.Log;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.service.UserMgr;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.web.context.WebApplicationContext;

/** 
 * MyEclipse Struts
 * Creation date: 11-22-2004
 * 
 * XDoclet definition:
 * @struts:action path="/approveUser" name="approveUserForm" input="/approveUser.jsp" scope="request"
 * @struts:action-forward name="success" path="confirm" contextRelative="true"
 * @struts:action-forward name="fail" path="approveUser"
 */
public class ApproveUserAction extends NavigationDispatchAction {

	// --------------------------------------------------------- Instance Variables

	//AuditLogAccess logAccess = null;
	//UserAccess userAccess = null;
	//protected JMSSender sender;
	
	//private EmailManager emailManager;
	
	// --------------------------------------------------------- Methods

	public ApproveUserAction() {
		try {
			//userAccess = new UserAccess();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void init() {
		
	}
	
	public ActionForward viewUserList( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse res ) 
		throws IOException, ServletException {
		
		List filteredList = new ArrayList();
		
		WebApplicationContext webContext =  getWebApplicationContext();
		UserDataService userMgr = (UserDataService)webContext.getBean("userManager");
		

		try {
	        //HttpSession session = request.getSession();          
	        //UserAccess userAccess = new UserAccess();
	        //String userId = (String)session.getAttribute("userId");
	        
	        // create the search object
	       // UserSearch search = new UserSearch();
	        //search.setStatus("PENDING");
	        //List userList = userAccess.findUser(search);
	        
	        List userList = userMgr.findUsersByStatus("PENDING");
	        
	      //  System.out.println("view userlist = " + userList);
	      //  System.out.println("view userlist size= " + userList.size());
	        
	        request.setAttribute("userList", userList);
        
		}catch(Exception e) {
			e.printStackTrace();
		}
        return mapping.findForward("success");        

	
	}

	public ActionForward approveUser( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
	throws IOException, ServletException {
	
	HttpSession session = request.getSession();
	try {
        UserAccess userAccess = new UserAccess();
        DynaValidatorForm dynForm = (DynaValidatorForm) form;
        //String userId = (String)dynForm.get("uId");
        String[] personId = request.getParameterValues("personId");
        
        String task = (String)dynForm.get("submit");
 
		WebApplicationContext webContext =  getWebApplicationContext();
        IdmAuditLogDataService auditService = 
 	    		 (IdmAuditLogDataService)webContext.getBean("auditDataService");

        
       
        int len = personId.length;
        // if no records are selected by the user
        if (personId == null || personId.length == 0) {
        	request.setAttribute("OPERATION", task);
            request.setAttribute("COUNT",new Integer(0));  
            return mapping.findForward("success"); 
        }
        
        
        if (task.equalsIgnoreCase("Reject")) {
        	session.setAttribute("personId", personId);
        	return mapping.findForward("reject");
        	//session.setAttribute("selectedPersons", personId);
        	//return mapping.findForward("reject"); 
        }else {
        	// approved
        	for (int i=0; i<len; i++) {
        		String selectedPersonId = personId[i];
        		UserData ud = userAccess.getUser(selectedPersonId);
        		ud.setStatusId("APPROVED");
        		userAccess.saveUser(ud);
        		// log the approval
        	//	logEvent("User id=" + selectedPersonId + " has been approved",
        	//			request.getRemoteHost(), (String)session.getAttribute("userId"),"IDM");

      			String logMsg = "User id=" + selectedPersonId + " has been approved";
                  			             
      			IdmAuditLog log = new IdmAuditLog();
      			log.setActionId("APPROVAL");
      			log.setActionStatus("SUCCESS");
      			log.setActionDatetime(new Date(System.currentTimeMillis()));
      			log.setHost(request.getRemoteHost());
      			log.setLogHash("00");
      			log.setObjectName(Class.forName("org.openiam.idm.srvc.user.dto.User").getName());
      			log.setObjectTypeId(selectedPersonId);
      			log.setReason("USER_APPROVAL");
      			log.setReasonDetail(logMsg);
      			log.setReqUrl(request.getRequestURI());
      			log.setServiceId("IDM");
      			log.setUserId((String)session.getAttribute("userId"));
      			auditService.addLog(log);
        	
         		
        		//this.sender.sendMessage(ud);
        	//	System.out.println("Sending email message...");
        	//	org.openiam.idm.srvc.user.dto.User user = new org.openiam.idm.srvc.user.dto.User();
        	//	user.setFirstName(ud.getFirstName());
        	//	Email email = userAccess.getEmailByName(ud.getId(),"DEFAULT");
        	//	this.emailManager.sendMail(user, email.getEmailAddress(), true);
        		
        		// temp code to write user into ldap
        	//	Spml2Service ldap = (Spml2Service)getWebApplicationContext().getBean("ldapService");
        	//	ldap.add(ud.getFirstName()+ud.getLastName(),
        	//			ud.getFirstName() + " " + ud.getLastName(),
        	//			ud.getLastName(), ud.getFirstName(), 
        	//			ud.getFirstName()+"_"+ud.getLastName()+".openiam.org");
       		
        		
        	}
        }
        request.setAttribute("OPERATION", task);
        request.setAttribute("COUNT",new Integer(len));
                   
	}catch(Exception e) {
		e.printStackTrace();
	}
    return mapping.findForward("confirm");        


}
	public ActionForward rejectUser( ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse res ) 
	throws IOException, ServletException {

		UserNote noteValue = null;
		String logMsg = null;

		WebApplicationContext webContext =  getWebApplicationContext();
        IdmAuditLogDataService auditService = 
 	    		 (IdmAuditLogDataService)webContext.getBean("auditDataService");
		UserDataService userMgr = (UserDataService)webContext.getBean("userManager");        

		
		HttpSession session = request.getSession();
		String[] personId = (String[])session.getAttribute("personId");
		int len = personId.length;

		DynaActionForm userNotesForm = (DynaActionForm) form;

		String noteType = (String)userNotesForm.get("noteType");
		String userId = (String)session.getAttribute("userId");
		String login = (String)session.getAttribute("login");
		
		try {
	       	for (int i=0; i<len; i++) {
        		String selectedPersonId = personId[i];
        		User ud = userMgr.getUserWithDependent(selectedPersonId,false);
        		ud.setStatus("REJECTED");
        		userMgr.updateUser(ud);
        		// save the reason for the rejection with the user
        		noteValue = new UserNote();
        		noteValue.setCreatedBy(userId);
        		noteValue.setCreateDate(new Timestamp(System.currentTimeMillis()));
        		noteValue.setDescription((String)userNotesForm.get("description"));
        		noteValue.setNoteType(noteType);
        		noteValue.setUserId(selectedPersonId);
        		userMgr.addNote(noteValue);
                
        		//AuditLogAccess.logEvent("User id=" + selectedPersonId + " has been REJECTED.", 
                //		request.getRemoteHost(),userId, login,"IDM"); 
                
                
     			logMsg = "User id=" + selectedPersonId + " has been rejected";
		             
      			IdmAuditLog log = new IdmAuditLog();
      			log.setActionId("REJECT");
      			log.setActionStatus("SUCCESS");
      			log.setActionDatetime(new Date(System.currentTimeMillis()));
      			log.setHost(request.getRemoteHost());
      			log.setLogHash("00");
      			log.setObjectName(Class.forName("org.openiam.idm.srvc.user.dto.User").getName());
      			log.setObjectTypeId(selectedPersonId);
      			log.setReason("USER_REJECT");
      			log.setReasonDetail(logMsg);
      			log.setReqUrl(request.getRequestURI());
      			log.setServiceId("IDM");
      			log.setUserId((String)session.getAttribute("userId"));
      			auditService.addLog(log);

        		
        		//System.out.println("Sending email message...");
        		org.openiam.idm.srvc.user.dto.User user = new org.openiam.idm.srvc.user.dto.User();
        		user.setFirstName(ud.getFirstName());
        	//	Email email = userAccess.getEmailByName(ud.getId(),"DEFAULT");
        	//	this.emailManager.sendMail(user, email.getEmailAddress(), false);
       		
        	}
		}catch(ClassNotFoundException ce) {
			Log.error(ce.getMessage(),ce);
			ce.printStackTrace();	
		}
        request.setAttribute("OPERATION", "REJECTED");
        request.setAttribute("COUNT",new Integer(len));
        session.removeAttribute("personId");
		return mapping.findForward("confirm"); 

		
	}
	




}