package org.openiam.selfsrvc.prov;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.selfsrvc.usradmin.DelegationFilterHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.role.dto.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private RoleDataWebService roleDataService;


	public MyPendingRequestController() {
		super();
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

        String userId = (String)req.getSession().getAttribute("userId");
        List<String> roleIdList = new ArrayList<String>();
        User usr = userMgr.getUserWithDependent(userId,true).getUser();


        RoleListResponse resp =  roleDataService.getUserRolesAsFlatList(userId);

		if (resp != null && resp.getStatus() == ResponseStatus.SUCCESS) {
            List<Role> roleList =  resp.getRoleList();
            for (Role r : roleList) {
                roleIdList.add(r.getId().getRoleId());

            }

        }

        SearchRequest search = buildSearch(userId, roleIdList, usr);
		List<ProvisionRequest> reqList = provRequestService.search(search).getReqList();

        System.out.println("Requests found=" + reqList);
	    
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("reqList", reqList);

		
		return mav;
		
	}

    private SearchRequest buildSearch( String userId, List<String> roleIdList, User usr) {
		SearchRequest search = new SearchRequest();
		search.setStatus("PENDING");
		search.setApproverId(userId);
        search.setRoleIdList(roleIdList);


        if (usr.getDelAdmin() != null &&  usr.getDelAdmin().intValue() == 1) {
            Map<String, UserAttribute> attrMap = usr.getUserAttributes();
            List<String> deptFilterList = null;
            List<String> orgFilterList = null;
            List<String> divFilterList = null;


            orgFilterList = DelegationFilterHelper.getOrgIdFilterFromString (attrMap);

            System.out.println("Org Filterlist =" + orgFilterList);

            if (orgFilterList != null && orgFilterList.size() > 0) {
                search.setRequestForOrgList(orgFilterList);
            }

         }

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

    public RoleDataWebService getRoleDataService() {
        return roleDataService;
    }

    public void setRoleDataService(RoleDataWebService roleDataService) {
        this.roleDataService = roleDataService;
    }
}
