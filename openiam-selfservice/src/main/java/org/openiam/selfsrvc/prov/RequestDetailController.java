package org.openiam.selfsrvc.prov;


import com.thoughtworks.xstream.XStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.auth.ws.LoginResponse;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.grp.ws.GroupResponse;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.msg.dto.NotificationParam;
import org.openiam.idm.srvc.msg.dto.NotificationRequest;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.ws.RequestWebService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleResponse;
import org.openiam.idm.srvc.service.dto.RequestApprover;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.provision.service.ProvisionService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.org.dto.Organization;

import org.openiam.idm.srvc.org.service.OrganizationDataService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

// temp


public class RequestDetailController extends SimpleFormController {

    protected RequestWebService provRequestService;
    protected UserDataWebService userManager;
    protected RoleDataWebService roleDataService;
    protected GroupDataWebService groupManager;
    protected ProvisionService provisionService;
    protected MailService mailService;
    protected LoginDataWebService loginManager;
    protected ResourceDataService resourceDataService;
    protected ManagedSystemDataService managedSysService;
    protected OrganizationDataService orgManager;


    private static final Log log = LogFactory.getLog(RequestDetailController.class);

    public RequestDetailController() {

    }


    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");

        String requestId = ServletRequestUtils.getStringParameter(request, "requestId");
        ProvisionRequest req = provRequestService.getRequest(requestId).getRequest();
        Set<RequestUser> reqUserSet = req.getRequestUsers();

        System.out.println("RequestDetails - FormBackingObject");

        RequestDetailCommand reqDetailCommand = new RequestDetailCommand();
        reqDetailCommand.setRequest(req);

        String userAsXML = req.getRequestXML();


        ProvisionUser pUser = null;

        if (userAsXML != null) {
            pUser = this.fromXML(userAsXML);
            System.out.println("User deserialized successfully = " + pUser);

            reqDetailCommand.setUserDetail(pUser);
            String companyId = pUser.getCompanyId();
            if (companyId != null && companyId.length() > 0) {
                Organization org = orgManager.getOrganization(companyId);
                if (org != null) {
                 reqDetailCommand.setOrgName(org.getOrganizationName());
                }
            }



            if (pUser != null) {
                List<Group> groupList = pUser.getMemberOfGroups();
                List<Role> roleList = pUser.getMemberOfRoles();

                log.info("Role list=" + roleList);

                if (groupList != null) {
                    reqDetailCommand.setGroupList(buildGroupList(groupList));
                }
                if (roleList != null) {
                    reqDetailCommand.setRoleList(buildRoleList(roleList));
                }
            }
        }



        if (userId != null) {
            // load the user object for the requestor

            if (req.getRequestorId().equalsIgnoreCase("ANONYMOUS")) {
                reqDetailCommand.setRequestor(pUser);

            }else {
                if (req.getRequestorId() != null && req.getRequestorId().length() > 0) {

                    UserResponse userResp = userManager.getUserWithDependent(req.getRequestorId(), false);
                    User user = userResp.getUser();
                    reqDetailCommand.setRequestor(user);
                }
            }



        }


        return reqDetailCommand;


    }

    private ProvisionUser fromXML(String userAsXML) {
        XStream xstream = new XStream();
        ProvisionUser user = (ProvisionUser) xstream.fromXML(userAsXML);
        return user;

    }

    private List<Group> buildGroupList(List<Group> groupLis) {
        List<Group> grpList = new ArrayList<Group>();
        for (Group g : grpList) {
            GroupResponse resp = groupManager.getGroup(g.getGrpId());
            if (resp.getStatus() == ResponseStatus.SUCCESS) {
                grpList.add(resp.getGroup());
            }
        }
        return grpList;

    }

    private List<Role> buildRoleList(List<Role> roleList) {
        List<Role> rlList = new ArrayList<Role>();
        for (Role r : roleList) {

            log.info("User role membership: " + r);

            RoleResponse resp = roleDataService.getRole(r.getId().getServiceId(), r.getId().getRoleId());
            if (resp.getStatus() == ResponseStatus.SUCCESS) {
                rlList.add(resp.getRole());
            }
        }
        return rlList;

    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        System.out.println("onSubmit called.");
        String status = null;
        Date curDate = new Date(System.currentTimeMillis());

        RequestDetailCommand requestDetailCmd = (RequestDetailCommand) command;

        String reqId = requestDetailCmd.getRequestId();
        int indx = reqId.indexOf(",");
        if (indx != -1) {
            reqId = reqId.substring(0, indx);
        }

        String userId = (String) request.getSession().getAttribute("userId");


        System.out.println("Request id = " + reqId);

        ProvisionRequest req = provRequestService.getRequest(reqId).getRequest();




        String btn = ServletRequestUtils.getStringParameter(request, "btn");

        if (btn.equalsIgnoreCase("Approve")) {
            status = "APPROVED";
        } else if (btn.equalsIgnoreCase("Claim")) {
            status = "CLAIMED";
        } else {
            status = "REJECTED";
        }
        req.setStatus(status);
        req.setStatusDate(curDate);

        System.out.println("Status=" + status);
        System.out.println("RequestId = " + req.getRequestId());

        // update the action of this approver.
        Set<org.openiam.idm.srvc.prov.request.dto.RequestApprover> requestApprovers = req.getRequestApprovers();
        for (org.openiam.idm.srvc.prov.request.dto.RequestApprover ra  : requestApprovers ) {
               if (ra.getApproverId().equalsIgnoreCase(userId)) {
                   System.out.println(" Updating approver status component of request to " + status);
                   ra.setAction(status);
                   ra.setActionDate(curDate);
                   ra.setComment(requestDetailCmd.comment);
                }

        }



        provRequestService.updateRequest(req);


        String reqAsXML = req.getRequestXML();
        ProvisionUser pUser = this.fromXML(reqAsXML);

         String login = (String)request.getSession().getAttribute("login");
        String domain = (String)request.getSession().getAttribute("domain");
        pUser.setRequestClientIP(request.getRemoteHost());
        pUser.setRequestorLogin(login);
        pUser.setRequestorDomain(domain);

        if (btn.equalsIgnoreCase("Approve")) {
            approve(req, pUser, userId);

        } else {
            // request was rejected
            reject(req, pUser, userId);

        }


        ModelAndView mav = new ModelAndView(getSuccessView());
        mav.addObject("requestDetailCmd", requestDetailCmd);

        return mav;


    }

    private void reject(ProvisionRequest req, ProvisionUser pUser, String approverId) {
        String requestType = req.getRequestType();


        List<ApproverAssociation> apList = managedSysService.getApproverByRequestType(requestType, 1);
        //String notifyUserId = ap.getNotifyUserOnReject();

        for (ApproverAssociation ap : apList) {
            String typeOfUserToNotify = ap.getRejectNotificationUserType();
            if (typeOfUserToNotify == null || typeOfUserToNotify.length() == 0) {
                typeOfUserToNotify = "USER";
            }
            String notifyUserId = null;
            if (typeOfUserToNotify.equalsIgnoreCase("USER")) {
                notifyUserId = ap.getNotifyUserOnReject();
            } else {
                if (typeOfUserToNotify.equalsIgnoreCase("SUPERVISOR")) {
                    Supervisor supVisor = pUser.getSupervisor();
                    if (supVisor != null) {
                        notifyUserId = supVisor.getSupervisor().getUserId();
                    } else {
                        notifyUserId = null;
                    }

                } else {
                    // target user
                    if (pUser.getEmailAddress() != null) {
                        notifyUserId = pUser.getUserId();
                    } else {
                        notifyUserId = null;
                    }

                }
            }


            notifyRequestorReject(req, approverId, notifyUserId);
        }
    }

    private void approve(ProvisionRequest req, ProvisionUser pUser, String approverId) {
        pUser.getUser().setStatus(UserStatusEnum.ACTIVE);
        pUser.getUser().setUserId(null);
        pUser.setStatus(UserStatusEnum.ACTIVE);
        ProvisionUserResponse resp = provisionService.addUser(pUser);

        User newUser = resp.getUser();

        log.info("New User userId = " + newUser.getUserId());

        String requestType = req.getRequestType();

        List<ApproverAssociation> apList = managedSysService.getApproverByRequestType(requestType, 1);

        for (ApproverAssociation ap : apList) {
            String typeOfUserToNotify = ap.getApproveNotificationUserType();
            if (typeOfUserToNotify == null || typeOfUserToNotify.length() == 0) {
                typeOfUserToNotify = "USER";
            }
            String notifyUserId = null;
            if (typeOfUserToNotify.equalsIgnoreCase("USER")) {
                notifyUserId = ap.getNotifyUserOnApprove();
            } else {
                if (typeOfUserToNotify.equalsIgnoreCase("SUPERVISOR")) {
                    Supervisor supVisor = pUser.getSupervisor();
                    if (supVisor != null) {
                        notifyUserId = supVisor.getSupervisor().getUserId();
                    } else {
                        notifyUserId = null;
                    }

                } else {
                    // target user
                    if (pUser.getEmailAddress() != null) {
                        notifyUserId = newUser.getUserId();
                    } else {
                        notifyUserId = null;
                    }

                }
            }

            if (notifyUserId != null) {
                notifyRequestorApproval(req, approverId, newUser, notifyUserId);
            } else {
                log.info("Unable to determine userId to notify");
            }
        }

    }

    /* Methods for sending out notification */

    private void notifyRequestorApproval(ProvisionRequest req, String approverUserId, User newUser, String notifyUserId) {

        // requestor information
        String userId = req.getRequestorId();
        String identity = null;
        String password = null;


        User approver = this.userManager.getUserWithDependent(approverUserId, false).getUser();

        // get the target user
        String targetUserName = null;
        Set<RequestUser> reqUserSet = req.getRequestUsers();
        if (reqUserSet != null && !reqUserSet.isEmpty()) {
            Iterator<RequestUser> userIt = reqUserSet.iterator();
            if (userIt.hasNext()) {
                RequestUser targetUser = userIt.next();
                targetUserName = targetUser.getFirstName() + " " + targetUser.getLastName();
            }
        }

        LoginResponse lgResponse = loginManager.getPrimaryIdentity(newUser.getUserId());
        if (lgResponse.getStatus() == ResponseStatus.SUCCESS) {
            Login l = lgResponse.getPrincipal();
            identity = l.getId().getLogin();
            password = (String) loginManager.decryptPassword(l.getPassword()).getResponseValue();
        }


        NotificationRequest request = new NotificationRequest();
        // send a message to this user
        request.setUserId(notifyUserId);
        request.setNotificationType("REQUEST_APPROVED");

        request.getParamList().add(new NotificationParam("REQUEST_ID", req.getRequestId()));

        request.getParamList().add(new NotificationParam("REQUEST_REASON", req.getRequestReason()));
        request.getParamList().add(new NotificationParam("REQUESTOR", approver.getFirstName() + " " + approver.getLastName()));
        request.getParamList().add(new NotificationParam("TARGET_USER", targetUserName));
        request.getParamList().add(new NotificationParam("IDENTITY", identity));
        request.getParamList().add(new NotificationParam("PSWD", password));


        mailService.sendNotification(request);
    }

    private void notifyRequestorReject(ProvisionRequest req, String approverUserId, String notifyUserId) {
        String userId = req.getRequestorId();

        User approver = userManager.getUserWithDependent(approverUserId, false).getUser();

        // get the target user
        String targetUserName = null;
        Set<RequestUser> reqUserSet = req.getRequestUsers();
        if (reqUserSet != null && !reqUserSet.isEmpty()) {
            Iterator<RequestUser> userIt = reqUserSet.iterator();
            if (userIt.hasNext()) {
                RequestUser targetUser = userIt.next();
                targetUserName = targetUser.getFirstName() + " " + targetUser.getLastName();

            }

        }

        NotificationRequest request = new NotificationRequest();
        request.setUserId(notifyUserId);
        request.setNotificationType("REQUEST_REJECTED");

        request.getParamList().add(new NotificationParam("REQUEST_ID", req.getRequestId()));

        request.getParamList().add(new NotificationParam("REQUEST_REASON", req.getRequestReason()));
        request.getParamList().add(new NotificationParam("REQUESTOR", approver.getFirstName() + " " + approver.getLastName()));
        request.getParamList().add(new NotificationParam("TARGET_USER", targetUserName));


        mailService.sendNotification(request);


    }


    public UserDataWebService getUserManager() {
        return userManager;
    }


    public void setUserManager(UserDataWebService userManager) {
        this.userManager = userManager;
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


    public ProvisionService getProvisionService() {
        return provisionService;
    }


    public void setProvisionService(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }


    public RequestWebService getProvRequestService() {
        return provRequestService;
    }


    public void setProvRequestService(RequestWebService provRequestService) {
        this.provRequestService = provRequestService;
    }


    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public LoginDataWebService getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginDataWebService loginManager) {
        this.loginManager = loginManager;
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public OrganizationDataService getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(OrganizationDataService orgManager) {
        this.orgManager = orgManager;
    }
}
