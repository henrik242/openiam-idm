package org.openiam.selfsrvc;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.grp.ws.GroupListResponse;
import org.openiam.idm.srvc.loc.service.LocationDataService;

import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.idm.srvc.auth.dto.Login;



public class WelcomePageController extends AbstractController {


	private static final Log log = LogFactory.getLog(WelcomePageController.class);
	private String successView;
	private UserDataWebService userMgr;
	private RoleDataWebService roleDataService;
	private GroupDataWebService groupManager;
    protected LoginDataWebService loginManager;
    protected PasswordConfiguration configuration;
    protected RequestWebService provRequestService;
    protected OrganizationDataService orgManager;

	
	public WelcomePageController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

	
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		
		UserResponse response = userMgr.getUserWithDependent(userId, true);
		if (response.getStatus() == ResponseStatus.FAILURE) {
			ModelAndView mav = new ModelAndView("/error");		
			return mav;			
		}
		User usr = response.getUser();
		GroupListResponse groupResponse = groupManager.getUserInGroups(userId);
		RoleListResponse roleResponse =  roleDataService.getUserRoles(userId);
        Login lg = loginManager.getPrimaryIdentity(userId).getPrincipal();

        String daysToExpStr = null;
        if (lg.getPwdExp() != null) {
            daysToExpStr = String.valueOf( setDaysToPassworExpiration(lg) );
        }else {
            daysToExpStr = "NA";
        }

        // supervisor
        List<Supervisor> supVisorList = userMgr.getSupervisors(userId).getSupervisorList();
        String supervisorName =  null;
		if (supVisorList != null && !supVisorList.isEmpty()) {
			Supervisor supervisor = supVisorList.get(0);
            supervisorName = supervisor.getSupervisor().getFirstName() + " " + supervisor.getSupervisor().getLastName();

		} else {
            supervisorName = "NA";
        }

        // user dept
        String deptCd = usr.getDeptCd();
        String deptName = "NA";
        if (deptCd != null && deptCd.length() >0) {
             deptName = orgManager.getOrganization(deptCd).getOrganizationName();

        }


        SearchRequest search = buildSearch( (String)req.getSession().getAttribute("userId"));
        List<ProvisionRequest> reqList = provRequestService.search(search).getReqList();

			
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("user", usr);
		mav.addObject("groupList",groupResponse.getGroupList());
		mav.addObject("roleList", roleResponse.getRoleList());
        mav.addObject("primaryIdentity", lg);
        mav.addObject("daysToExp", daysToExpStr);
        mav.addObject("supervisor", supervisorName);
        mav.addObject("dept", deptName);

        if (reqList != null && reqList.size() > 0) {
            mav.addObject("pendingReq", reqList.size());
        }
		
		return mav;
	}

    public int  setDaysToPassworExpiration(Login lg) {
        if (lg.getPwdExp() == null) {
            return -1;
        }

        long DAY = 86400000L;

        // lg.getPwdExp is the expiration date/time

        long diffInMilliseconds = lg.getPwdExp().getTime() - System.currentTimeMillis();
        long diffInDays = diffInMilliseconds / DAY;

        // treat anything that is less than a day, as zero
        if (diffInDays < 1) {
            return 0;
        }

        return (int)diffInDays;



    }




	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
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

	public GroupDataWebService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}

    public LoginDataWebService getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginDataWebService loginManager) {
        this.loginManager = loginManager;
    }

    public PasswordConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(PasswordConfiguration configuration) {
        this.configuration = configuration;
    }

    public RequestWebService getProvRequestService() {
        return provRequestService;
    }

    public void setProvRequestService(RequestWebService provRequestService) {
        this.provRequestService = provRequestService;
    }
    private SearchRequest buildSearch( String userId) {
		SearchRequest search = new SearchRequest();
		search.setStatus("PENDING");
		search.setApproverId(userId);

		return search;
	}

    public OrganizationDataService getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(OrganizationDataService orgManager) {
        this.orgManager = orgManager;
    }
}
