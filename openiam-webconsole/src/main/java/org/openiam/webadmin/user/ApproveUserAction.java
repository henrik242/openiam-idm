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

	UserDataService userMgr; 
	
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
	

		try {

	        
	        List userList = userMgr.findUsersByStatus("PENDING");
      
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
        DynaValidatorForm dynForm = (DynaValidatorForm) form;
        String[] personId = request.getParameterValues("personId");
        
        String task = (String)dynForm.get("submit");
 
        
       
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

        }else {
        	// approved
        	for (int i=0; i<len; i++) {
        		String selectedPersonId = personId[i];
        		User usr = userMgr.getUserWithDependent(selectedPersonId, false);
        		usr.setStatus("APPROVED");
        		userMgr.updateUser(usr);

        		String logMsg = "User id=" + selectedPersonId + " has been approved";
   		
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
 

		
		HttpSession session = request.getSession();
		String[] personId = (String[])session.getAttribute("personId");
		int len = personId.length;

		DynaActionForm userNotesForm = (DynaActionForm) form;

		String noteType = (String)userNotesForm.get("noteType");
		String userId = (String)session.getAttribute("userId");
		String login = (String)session.getAttribute("login");
		

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
            
 			logMsg = "User id=" + selectedPersonId + " has been rejected";
  		
    		//System.out.println("Sending email message...");
    		org.openiam.idm.srvc.user.dto.User user = new org.openiam.idm.srvc.user.dto.User();
    		user.setFirstName(ud.getFirstName());

    	}

        request.setAttribute("OPERATION", "REJECTED");
        request.setAttribute("COUNT",new Integer(len));
        session.removeAttribute("personId");
		return mapping.findForward("confirm"); 

		
	}

	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}
	




}