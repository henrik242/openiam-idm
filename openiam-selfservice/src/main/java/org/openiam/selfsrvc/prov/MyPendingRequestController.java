package org.openiam.selfsrvc.prov;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller to retrieve user information. The information is presented through a readonly view.
 * @author suneet
 *
 */
public class MyPendingRequestController extends AbstractController {

	private static final Log log = LogFactory.getLog(MyPendingRequestController.class);
	private String view;

    protected RequestWebService provRequestService;
	protected UserDataWebService userMgr;


	public MyPendingRequestController() {
		super();
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

        SearchRequest search = buildSearch( (String)req.getSession().getAttribute("userId"));
		List<ProvisionRequest> reqList = provRequestService.search(search).getReqList();

	    
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("reqList", reqList);

		
		return mav;
		
	}

    private SearchRequest buildSearch( String userId) {
		SearchRequest search = new SearchRequest();
		search.setStatus("PENDING");
		search.setApproverId(userId);

		return search;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}


    public RequestWebService getProvRequestService() {
        return provRequestService;
    }

    public void setProvRequestService(RequestWebService provRequestService) {
        this.provRequestService = provRequestService;
    }

    public UserDataWebService getUserMgr() {
        return userMgr;
    }

    public void setUserMgr(UserDataWebService userMgr) {
        this.userMgr = userMgr;
    }
}
