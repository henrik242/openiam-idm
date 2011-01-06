package org.openiam.webadmin.rpt.audit;



import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.dto.AuditConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Generates the audit report
 * @author suneet
 *
 */
public class GenAuditReportController extends MultiActionController {


	private IdmAuditLogWebDataService auditService;
	


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
	
		 SearchAudit search = new SearchAudit();
		 String principal  = (String)request.getSession().getAttribute("rpt_principal");
		 if (principal != null && principal.length() > 0) {
			 search.setLoginId(principal);
		 }
		 String action = (String)request.getSession().getAttribute("rpt_action");
		 if (action != null && action.length() > 0) {
			 String[] actionList = new String[] { action };
			 search.setActionList(actionList);
		 }		 
	

		Date startDate = (Date)request.getSession().getAttribute("rpt_startDate");
		 if (startDate != null ) {
			 search.setStartDate(startDate);
		 }	
		Date endDate = (Date)request.getSession().getAttribute("rpt_endDate");
		if (endDate != null) {
			search.setEndDate(endDate);
		}
		
	  
		Collection auditData = auditService.search(search).getLogList();
		  
		  
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
		
		Collection auditData = auditService.search(search).getLogList();
			  
		  
		Map model = new HashMap();
		if (format != null && format.length() > 0) {
			model.put("format", format);
		}else {
			model.put("format", "pdf");
		}
	    model.put("dataSource",auditData);
	    
	    return new ModelAndView("passwordMultiFormatReport", model); 

	}

	public IdmAuditLogWebDataService getAuditService() {
		return auditService;
	}

	public void setAuditService(IdmAuditLogWebDataService auditService) {
		this.auditService = auditService;
	}

}
