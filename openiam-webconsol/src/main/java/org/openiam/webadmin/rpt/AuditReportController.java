package org.openiam.webadmin.rpt;



import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.dto.AuditConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AuditReportController extends MultiActionController {


	private IdmAuditLogDataService auditService;
	

	public IdmAuditLogDataService getAuditService() {
		return auditService;
	}


	public void setAuditService(IdmAuditLogDataService auditService) {
		this.auditService = auditService;
	}

	/**
	 * Method to obtain audit data based on user supplied parameters and then pass it on the reporting engine.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView auditReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String format = request.getParameter("format");
		String startDt = request.getParameter("startdt");
		String endDt = request.getParameter("enddt");

		String userId = request.getParameter("userid");
		String loginId = request.getParameter("loginid");
	
		 SearchAudit search = new SearchAudit();
		  
		if (startDt != null && startDt.length() > 0) {
			search.setStartDate( new java.sql.Date( df.parse(startDt).getTime() ) ); 
		}
		if (endDt != null && endDt.length() > 0) {
			search.setEndDate( new java.sql.Date ( df.parse(endDt).getTime() ));
		}
		if (userId != null && userId.length() > 0) {
			search.setUserId(userId);
		}
		if (loginId != null && loginId.length() > 0) {
			search.setLoginId(loginId);
		}		  
		  
		Collection auditData = auditService.search(search);
		  
		  
		  if (auditData != null){
			  System.out.println("Audit Data size: " + auditData.size());
		  }

		Map model = new HashMap();
		if (format != null && format.length() > 0) {
			model.put("format", format);
		}else {
			model.put("format", "pdf");
		}
	    model.put("dataSource",auditData);
	    return new ModelAndView("auditMultiFormatReport", model); 

	}

	/**
	 * Method to obtain data for the password change report and pass it on the reporting engine.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView passwordChangeReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

 
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		
		String format = request.getParameter("format");
		String startDt = request.getParameter("startdt");
		String endDt = request.getParameter("enddt");
		  
		  
		String[] actionAry = new String[2];
		actionAry[0] = AuditConstants.ACTION_PASSWORD_CHANGE;
		actionAry[1] = AuditConstants.ACTION_PASSWORD_RESET;

		// set search parameter
		SearchAudit search = new SearchAudit();
		search.setActionList(actionAry);
		
		if (startDt != null && startDt.length() > 0) {
			search.setStartDate( new java.sql.Date ( df.parse(startDt).getTime() ) ); 
		}
		if (endDt != null && endDt.length() > 0) {
			search.setEndDate( new java.sql.Date( df.parse(endDt).getTime()));
		}
		
		Collection auditData = auditService.search(search);
			  
		  
		Map model = new HashMap();
		if (format != null && format.length() > 0) {
			model.put("format", format);
		}else {
			model.put("format", "pdf");
		}
	    model.put("dataSource",auditData);
	    
	    return new ModelAndView("passwordMultiFormatReport", model); 

	}

}
