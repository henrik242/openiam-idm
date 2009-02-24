//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.2/xslt/JavaClass.xsl

package org.openiam.selfserv.da.user;

import java.rmi.RemoteException;
import java.sql.Timestamp;

import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;


import diamelle.common.user.UserNoteValue;
import diamelle.ebc.user.*;
import diamelle.util.Log;

import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-28-2004
 * 
 * XDoclet definition:
 * @struts:action path="/userHistory" name="userHistoryForm" input="userHistory.jsp" scope="request" validate="true"
 * @struts:action-forward name="success" path="home.jsp?bodyjsp"
 */
public class UserNotesAction extends NavigationDispatchAction {
	UserAccess userAccess = null;
	AuditLogAccess logAccess = new AuditLogAccess();

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	public UserNotesAction() {
		try {
			userAccess = new UserAccess();
			logAccess = new AuditLogAccess();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ActionForward newNote(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
System.out.println("....New note..");		
		DynaActionForm userNotesForm = (DynaActionForm) form;
		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		String personId = (String)session.getAttribute("personId");
		userNotesForm.set("noteType", mode);		
		userNotesForm.set("personId", personId);
		return mapping.findForward("new");
	}

	public ActionForward saveNote(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("....save note..");		
		
		HttpSession session = request.getSession();
		DynaActionForm userNotesForm = (DynaActionForm) form;
		UserNoteValue noteValue = new UserNoteValue();
		String logMsg = null;
		
		try {
		// update the user status
		String noteType = (String)userNotesForm.get("noteType");
		String personId = (String)userNotesForm.get("personId");
		String userId = (String)session.getAttribute("userId");
		UserData ud = userAccess.getUser(personId);
System.out.println("note type = " + noteType);		
		if (noteType.equals("BL")) {
			// BLACK LISTED
			ud.setStatusId("BLACK LISTED");
			logMsg = "User id=" + personId + " BLACK LISTED";
		}
		if (noteType.equals("DL")) {
			// BLACK LISTED
			ud.setStatusId("DELETED");
			logMsg = "User id=" + personId + " DELETED";
		}
		if (noteType.equals("UB")) {
			// BLACK LISTED
			ud.setStatusId("APPROVED");
			logMsg = "User id=" + personId + " UN-BLACKLISTED. STATUS SET TO APPROVED.";
		}			
		// copy form data into the noteValue object		
		noteValue.setCreatedBy(userId);
		noteValue.setDateCreated(new Timestamp(System.currentTimeMillis()));
		noteValue.setDescription((String)userNotesForm.get("description"));
		noteValue.setNoteTypeId(noteType);
		noteValue.setUserId(personId);
		// save the changes
		userAccess.saveUser(ud);
		userAccess.addUserNote(noteValue);
        AuditLogAccess.logEvent("User id=" + personId + " has been deleted.", 
        		request.getRemoteHost(), 
           		(String)session.getAttribute("userId"),
				(String)session.getAttribute("login"), "IDM");
		
		String mode = request.getParameter("mode");
		userNotesForm.set("noteType", mode);
		userNotesForm.set("description",noteValue.getDescription());
		
		}catch(RemoteException e) {
			Log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		// used as a flag to close the popup. Its a bit of hack to get around 
		// javascript quirks
		request.setAttribute("saved","1");
System.out.println("Saved note - findfwd= " + mapping.findForward("saved").getPath());
		return mapping.findForward("saved"); 
	}
	
	

	   
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		System.out.println("....New note - execute..");		

		String method = request.getParameter("method");
		if (method.equalsIgnoreCase("newNote")) {
			return this.newNote(mapping,form, request,response);
		} else {
			return this.saveNote(mapping,form, request,response);
		}
	}
}