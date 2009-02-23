//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.7.200/xslt/JavaClass.xsl

package org.openiam.webadmin.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

import org.openiam.webadmin.busdel.security.AuditLogAccess;
import diamelle.security.log.*;

import java.text.*;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 08-14-2004
 * 
 * XDoclet definition:
 * @struts:action path="/searchAuditLog" name="searchAuditLogForm" input="/log/searchAuditLog.jsp" scope="request"
 */
public class AuditLogAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

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
		
		AuditLogAccess logAccess = new AuditLogAccess();
		LogSearch search = new LogSearch();
		DynaActionForm auditLogForm = (DynaActionForm) form;
		
		try {
			formToValue(auditLogForm, search);
			List resultList = logAccess.search(search);
			request.setAttribute("searchResult",resultList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("searchlog");

	}
	
	private void formToValue(DynaActionForm dynForm, LogSearch search) {
		java.sql.Date stDate = null;
		java.sql.Date edDate = null;
		String str = null;
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy"); 
			//DateFormat.getDateInstance();
		try {
			String strDate = (String)dynForm.get("startDate");
			if (strDate != null && strDate.length() > 0) {
				stDate = new java.sql.Date( df.parse((String)dynForm.get("startDate")).getTime());
				search.setStartDate(stDate);
			}
			strDate = (String)dynForm.get("endDate");
			if (strDate != null && strDate.length() > 0) {
				edDate = new java.sql.Date( df.parse((String)dynForm.get("endDate")).getTime());
				search.setEndDate(edDate);
			}

		}catch(ParseException pe) {
			pe.printStackTrace();
		}
		str = (String)dynForm.get("service");
		if (str != null && str.length() > 0)
			search.setService((String)dynForm.get("service"));
		str = (String)dynForm.get("personId");
		if (str != null && str.length() > 0)
			search.setUserId((String)dynForm.get("personId"));
		str = (String)dynForm.get("IPAddress");
		if (str != null && str.length() > 0)
			search.setIpAddress((String)dynForm.get("IPAddress"));
	}

}