//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package org.openiam.selfsrvc.pswd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.*;

import diamelle.security.auth.*;
import diamelle.ebc.user.*;
import org.openiam.webadmin.busdel.base.*;
import org.openiam.webadmin.busdel.security.*;
import org.openiam.webadmin.busdel.identity.*;
//import org.openiam.selfsrvc.util.*;


/** 
 * MyEclipse Struts
 * Creation date: 05-12-2005
 * 
 * XDoclet definition:
 * @struts:action path="/forgotPassword" name="forgotPasswordForm" input="/pub/forgotPassword.jsp" scope="request" validate="true"
 * @struts:action-forward name="confirm" path="confirmpwd.jsp"
 */
public class ForgotPasswordAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	
	private AuthenticatorAccess authAccess = new AuthenticatorAccess();
	private UserAccess userAccess;
	
	public ForgotPasswordAction() {
		try {
			userAccess = new UserAccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// --------------------------------------------------------- Methods
	public ActionForward view(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("view");
		
	}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getPassword(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		DynaActionForm dynForm = (DynaActionForm) form;
		ActionErrors err = new ActionErrors();
		
		// no clue why this is coming out reversed
		// what should be login is service
		String login = (String)dynForm.get("login");
		String service = (String)dynForm.get("service");
		
	try {	
		
		validate(err, service,login);
		if (!err.isEmpty()) {
			saveErrors(request, err);
			return (mapping.findForward("view"));
		}

		// reverse this when authAccess is corrected
		LoginValue loginValue = authAccess.getLogin(service, login);
		if (loginValue == null) {
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.no.login"));
			//err.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.no.login"));
				saveErrors(request, err);
				return (mapping.findForward("view"));
		}
		
		UserData ud = userAccess.getUser(loginValue.getUserId());
		String toEmail = ud.getEmail();
		if ((toEmail == null)||(toEmail.length()==0)) {
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.no.email"));
			//err.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.no.email"));
			saveErrors(request, err);			
			return (mapping.findForward("view"));				
		}

		// get the user record
		String fromEmail = "aruns@diamelle.com";
/*		String msgSubject = "Account information";
		String msgBody = "Login: " + login + "\n" +
		"Service: " + service + "\n" +
		"Password: " + loginValue.getPassword() + "\n" +
		"\n" +
		"EMCOR Facilities Services Inc";
		
		// send password or show error
		
		MailValue msg = new MailValue();		
		msg.setTo(toEmail);
		msg.setFrom(fromEmail);
		msg.setSubject(msgSubject);
		msg.setBody(msgBody);

		MailSender.sendMail(msg);  // static method
*/		
	} catch (Exception e) {
		e.printStackTrace();
		err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.mail"));
		//err.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.mail"));
	}

	if (!err.isEmpty()) {
		saveErrors(request, err);
		return (mapping.findForward("view"));
	}
	
	return mapping.findForward("confirm");

	}
	
	public void validate(ActionErrors err,String login, String service) throws Exception {
		
		if (login == null || login.length() ==0) {
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.login"));
//			err.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.login"));
		}
		if (service == null || service.length() ==0) {
			err.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.service"));
			//err.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.service"));
		}	
		

		return;	
	}

}