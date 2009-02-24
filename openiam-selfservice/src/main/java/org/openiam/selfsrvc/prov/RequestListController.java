package org.openiam.selfsrvc.prov;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;

// temp
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.user.dto.User;



public class RequestListController extends SimpleFormController {

	RequestDataService provRequestService;
	
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("onSubmit called.");
		
		RequestSearchCriteria reqSearch =(RequestSearchCriteria)command;
		reqSearch.setStatus("new");
		
		ProvisionRequest[] reqAry = provRequestService.search(null);
		
		// temp objects
	/*	User usr = new User();
		usr.setFirstName("Suneet");
		usr.setLastName("shah");
		
		List userList = new ArrayList();
		userList.add(usr);
	*/
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("requestSearchCriteria",reqSearch);
		
		mav.addObject("resultList", reqAry);
		
		return mav;
	}

	public RequestListController() {
		super();
		setFormView("/prov/requestlist");
		setSuccessView("/prov/requestlist");
		setCommandClass(RequestSearchCriteria.class);
		setCommandName("requestSearchCriteria");
		
	}

	public RequestDataService getProvRequestService() {
		return provRequestService;
	}

	public void setProvRequestService(RequestDataService provRequestService) {
		this.provRequestService = provRequestService;
	}

}
