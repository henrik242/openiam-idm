/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

package org.openiam.webadmin.user;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

//import org.openiam.idm.connector.Spml2Service;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
import org.openiam.webadmin.metadata.MetadataTypeController;
import org.springframework.web.context.WebApplicationContext;

import diamelle.security.auth.LoginValue;


import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	UserDataService userMgr; 

	private static final Log log = LogFactory.getLog(MetadataTypeController.class);


	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	public UserNotesAction() {

	}
	
	public ActionForward newNote(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {


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
		UserNote noteValue = new UserNote();
		String logMsg = null;
		
		String noteType = (String)userNotesForm.get("noteType");
		String personId = (String)userNotesForm.get("personId");
		String userId = (String)session.getAttribute("userId");
		User ud = userMgr.getUserWithDependent(personId, false);
		
		if (noteType.equals("BL")) {
			// BLACK LISTED
			ud.setStatus("BLACK LISTED");
			logMsg = "User id=" + personId + " BLACK LISTED";
		}
		if (noteType.equals("DL")) {
			// BLACK LISTED
			ud.setStatus("DELETED");
			logMsg = "User id=" + personId + " DELETED";
		}
		if (noteType.equals("UB")) {
			// BLACK LISTED
			ud.setStatus("APPROVED");
			logMsg = "User id=" + personId + " UN-BLACKLISTED. STATUS SET TO APPROVED.";
		}			
		// copy form data into the noteValue object		
		noteValue.setCreatedBy(userId);
		noteValue.setCreateDate(new Timestamp(System.currentTimeMillis()));
		noteValue.setDescription((String)userNotesForm.get("description"));
		noteValue.setUserNoteId(noteType);
		noteValue.setUserId(personId);
		// save the changes
		userMgr.updateUser(ud);
		userMgr.addNote(noteValue);
	
	
		String mode = request.getParameter("mode");
		userNotesForm.set("noteType", mode);
		userNotesForm.set("description",noteValue.getDescription());
		
		// used as a flag to close the popup. Its a bit of hack to get around 
		// javascript quirks
		request.setAttribute("saved","1");
		log.info("Saved note - findfwd= " + mapping.findForward("saved").getPath());
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

	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}
}