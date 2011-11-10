package org.openiam.selfsrvc.prov;


import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;

// temp
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.ws.UserDataWebService;




public class RequestListController extends SimpleFormController {

	protected RequestWebService provRequestService;
	protected UserDataWebService userMgr;


	public RequestListController() {
		super();

	}
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
	    String userId = (String)request.getSession().getAttribute("userId");
		// get the direct reports
	    List<Supervisor> directReports = userMgr.getEmployees(userId).getSupervisorList();
	    
		dataMap.put("directReports", directReports);
		return dataMap;
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		System.out.println("onSubmit called.");
		
		RequestSearchCriteria reqSearch =(RequestSearchCriteria)command;
		System.out.println("Request search:" + reqSearch.getStatus());
		
		SearchRequest search = buildSearch(reqSearch, 
				(String)request.getSession().getAttribute("userId"));	
		List<ProvisionRequest> reqList = provRequestService.search(search).getReqList();
		
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("requestSearchCriteria",reqSearch);
		
		mav.addObject("resultList", reqList);
		
		return mav;
	}



	private SearchRequest buildSearch(RequestSearchCriteria request, String userId) {
		SearchRequest search = new SearchRequest();
		search.setStatus(request.getStatus());
		if (!request.getRequestorId().equalsIgnoreCase("-")) {
			// show requests for the person selected on the list
			search.setApproverId(request.getRequestorId());
			//search.setRequestorId(request.getRequestorId());
		}else {
			// show the requests for the approver that is logged in.
			search.setApproverId(userId);
//			search.setRequestorId(userId);
		}
		if (request.getStartDate() != null) {
			search.setStartDate(request.getStartDate());
		}
		if (request.getEndDate() != null) {
			search.setEndDate(request.getEndDate());
		}		
		return search;
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}

	public RequestWebService getProvRequestService() {
		return provRequestService;
	}

	public void setProvRequestService(RequestWebService provRequestService) {
		this.provRequestService = provRequestService;
	}

}
