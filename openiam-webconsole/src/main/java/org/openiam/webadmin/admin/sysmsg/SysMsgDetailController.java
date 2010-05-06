package org.openiam.webadmin.admin.sysmsg;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.validator.DynaValidatorForm;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditLogUtil;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.msg.ws.SysMessageWebService;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.ws.OrgPolicyWebService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.service.UserDataService;


public class SysMsgDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(SysMsgDetailController.class);
	private SysMessageWebService sysMessageService; 
 	protected String redirectView;
	
 	protected MailService mailService;
 	protected String emailAddress;
	private UserDataService userMgr;
	protected LoginDataWebService loginManager;
	
	protected AuditLogUtil auditUtil;
	
	public SysMsgDetailController() {
		super();
	}
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {


		SysMsgCommand msgCmd = new SysMsgCommand();

		String msgId = request.getParameter("msgId");
		if (msgId == null) {
			return msgCmd;
		}
		
		SysMessage sysMessage = sysMessageService.getMessageById(msgId).getSysMessage();
		
		msgCmd.setMsg(sysMessage);
	
		return msgCmd;

}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("OrgPolicyDetailController:onSubmit called.");
		
		SysMsgCommand msgCmd = (SysMsgCommand)command;
		SysMessage sysMessage = msgCmd.getMsg();

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			sysMessageService.removeMessage(sysMessage.getMsgId());
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Location has been successfully deleted.");
	        return mav;
		}else if (btn != null && btn.equalsIgnoreCase("Send Msg")) {
			sendMsg(msgCmd);
		}else {	
			if (sysMessage.getMsgId() == null || sysMessage.getMsgId().length()  == 0) {
				sysMessage.setMsgId(null); 
				sysMessageService.addMessage(sysMessage);
			}else {
				sysMessageService.updateMessage(sysMessage);
			}
		}
		
		ModelAndView mav = new ModelAndView(new RedirectView(redirectView, true));
		mav.addObject("sysMsgCmd", msgCmd);
		
		return mav;
	}

	private void sendMsg(SysMsgCommand cmd) {
		System.out.println("send msg called");
		
		// get the list of users
    	UserSearch search = createSearch(cmd);     
		if (search.isEmpty()) {
			log.info("Search is object is empty");
		  	 return;
		}   	
		List<User> userList = userMgr.search(search);
		if (userList != null) {
			for (User user : userList) {
				List<Login> principalList = loginManager.getLoginByUser(user.getUserId()).getPrincipalList();
				Login lg = getPrimaryLogin(principalList);
				// build the message
				String subject = null;
				if (lg != null) {
				System.out.println("Message sent to :" + lg.getId().getLogin());
				// send the message
					subject = cmd.getMsg().getMsgSubject() + " " + lg.getId().getLogin() + " with default Password of: OCidisup";
				}
					if (user.getEmail() != null && user.getEmail().length() > 5) {
						mailService.send(cmd.getMsg().getMsgFrom(), user.getEmail(),
								subject, 
								cmd.getMsg().getMsg());
					//	IdmAuditLog log = new IdmAuditLog( "EMAIL", "SEND", "SUCCESS", null,null,lg.getUserId(),  null, lg.getId().getLogin(), null);
					//	auditUtil.log(log);
					}

			}
		}
		
		
	}
	
	private Login getPrimaryLogin(List<Login> principalList) {
		if (principalList == null) {
			return null;
		}
		for (Login lg : principalList) {
			if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
				return lg;
			}
		}
		return null;
	}
	
    private UserSearch createSearch(SysMsgCommand cmd) {
        UserSearch search = new UserSearch();
        
        if (cmd.getMsg().getTargetAudienceType() != null &&
        	cmd.getMsg().getTargetAudienceType().equalsIgnoreCase("DEPT") ) {
        	
        	log.info("Group search parameter being set to: " + cmd.getMsg().getTargetAudience());
        	
        	search.setDeptCd(cmd.getMsg().getTargetAudience());
        }
        

        return search;
     }
    

	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public SysMessageWebService getSysMessageService() {
		return sysMessageService;
	}

	public void setSysMessageService(SysMessageWebService sysMessageService) {
		this.sysMessageService = sysMessageService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}

	public AuditLogUtil getAuditUtil() {
		return auditUtil;
	}

	public void setAuditUtil(AuditLogUtil auditUtil) {
		this.auditUtil = auditUtil;
	}




	



	

}
