package org.openiam.webadmin.rpt.audit;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;


public class AuditReportController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(AuditReportController.class);
	private IdmAuditLogWebDataService auditService;
	


	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	
	public AuditReportController() {
		super();
	}


	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		AuditReportCommand auditCommand = (AuditReportCommand)command;

/*		request.getSession().removeAttribute("rpt_principal");
		request.getSession().removeAttribute("rpt_action");
		request.getSession().removeAttribute("rpt_startDate");
		request.getSession().removeAttribute("rpt_endDate");

		
		request.getSession().setAttribute("rpt_principal",auditCommand.getPrincipal());
		request.getSession().setAttribute("rpt_action",auditCommand.getAction());
		request.getSession().setAttribute("rpt_startDate",auditCommand.getStartDate());
		request.getSession().setAttribute("rpt_endDate",auditCommand.getEndDate());
*/
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String format = request.getParameter("format");
	
		 SearchAudit search = new SearchAudit();
		 String principal  = auditCommand.getPrincipal();
		 if (principal != null && principal.length() > 0) {
			 search.setLoginId(principal);
		 }
		 String action = auditCommand.getAction();
		 if (action != null && action.length() > 0) {
			 String[] actionList = new String[] { action };
			 search.setActionList(actionList);
		 }		 
	

		Date startDate = auditCommand.getStartDate();
		 if (startDate != null ) {
			 search.setStartDate(startDate);
		 }	
		Date endDate = auditCommand.getEndDate();
		if (endDate != null) {
			search.setEndDate(endDate);
		}
		
	  
		Collection auditData = auditService.search(search).getLogList();
		  
		  
	   if (auditData != null){
		   request.getSession().setAttribute("rptResult", auditData);
		  System.out.println("Audit Data size: " + auditData.size());
	   }

	   return new ModelAndView(new RedirectView(getSuccessView(), true));
	   

	}
		



	public IdmAuditLogWebDataService getAuditService() {
		return auditService;
	}

	public void setAuditService(IdmAuditLogWebDataService auditService) {
		this.auditService = auditService;
	}




	

}
