package org.openiam.selfsrvc.prov;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Set;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;

// temp
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;




public class RequestDetailController extends SimpleFormController {

	RequestDataService provRequestService;
	
	public RequestDetailController() {
		
	}
	
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		System.out.println("onSubmit called.");
		
		RequestDetailCommand requestDetailCmd =(RequestDetailCommand)command;
		
		String reqId = requestDetailCmd.getRequestId();
		int indx = reqId.indexOf(",");
		if (indx != -1) {
			reqId = reqId.substring(0,indx);
		}
		
		
		System.out.println("Request id = " + reqId);
		
		ProvisionRequest req =  provRequestService.getRequest(reqId);
		
		String btn = ServletRequestUtils.getStringParameter(request, "btn");

		if (btn.equalsIgnoreCase("Approve")) {
			req.setStatus("APPROVED");
		}else {
			req.setStatus("REJECTED");
		}
		req.setRequestDate(new Date(System.currentTimeMillis()));
		
		provRequestService.updateRequest(req);
		

	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("requestDetailCmd", requestDetailCmd);
	
		return mav;

		
	}


	


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		String requestId = ServletRequestUtils.getStringParameter(request, "requestId");
		ProvisionRequest req =  provRequestService.getRequest(requestId);
		Set<RequestUser> reqUserSet = req.getRequestUsers();

		
		RequestDetailCommand reqDetailCommand = new RequestDetailCommand();
		reqDetailCommand.setRequestDate(req.getRequestDate());
		reqDetailCommand.setRequestId(req.getRequestId()); 
		reqDetailCommand.setRequestorId(req.getRequestorId());
		reqDetailCommand.setRequestReason(req.getRequestReason());
		reqDetailCommand.setStatus(req.getStatus());
		reqDetailCommand.setStatusDate(req.getStatusDate()); 
		
		//get the user
		if (reqUserSet != null && reqUserSet.size() > 0) {
			Iterator<RequestUser> it = reqUserSet.iterator();
			while (it.hasNext()) {
				RequestUser rqUser = it.next();
				reqDetailCommand.setFirstName(rqUser.getFirstName());
				reqDetailCommand.setLastName(rqUser.getLastName());
			}
			
			
		}

		
		return reqDetailCommand;
		
		
	}
	


	public RequestDataService getProvRequestService() {
		return provRequestService;
	}

	public void setProvRequestService(RequestDataService provRequestService) {
		this.provRequestService = provRequestService;
	}

}
