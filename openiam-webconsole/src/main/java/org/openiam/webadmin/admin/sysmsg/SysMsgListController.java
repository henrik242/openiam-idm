package org.openiam.webadmin.admin.sysmsg;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.msg.ws.SysMessageWebService;



public class SysMsgListController extends AbstractController {


	private static final Log log = LogFactory.getLog(SysMsgListController.class);
	private String successView;
	private SysMessageWebService sysMessageService; 

	
	
	public SysMsgListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		List<SysMessage> sysMessageList = sysMessageService.getAllMessages().getSysMessageList();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("msgList", sysMessageList);
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public SysMessageWebService getSysMessageService() {
		return sysMessageService;
	}

	public void setSysMessageService(SysMessageWebService sysMessageService) {
		this.sysMessageService = sysMessageService;
	}
	

}
