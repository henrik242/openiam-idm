package org.openiam.webadmin.sync;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogListResponse;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService;



public class SynchSessionEventController extends AbstractController {


	private static final Log log = LogFactory.getLog(SynchSessionEventController.class);
	private String successView;
	protected IdmAuditLogWebDataService auditService;

	
	
	public SynchSessionEventController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String synchSessionId = request.getParameter("sessionId");
		
		
		ModelAndView mav = new ModelAndView(getSuccessView());		

		
		SearchAudit search = new SearchAudit();
		search.setSessionId(synchSessionId);
		
		IdmAuditLogListResponse logListResp = auditService.search(search);
		if (logListResp != null && logListResp.getStatus() != ResponseStatus.FAILURE) {
			List<IdmAuditLog> logList = logListResp.getLogList();
			mav.addObject("auditLog", logList);
		}
			
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}


	public IdmAuditLogWebDataService getAuditService() {
		return auditService;
	}

	public void setAuditService(IdmAuditLogWebDataService auditService) {
		this.auditService = auditService;
	}

	

}
