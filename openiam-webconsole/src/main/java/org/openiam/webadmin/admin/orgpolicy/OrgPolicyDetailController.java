package org.openiam.webadmin.admin.orgpolicy;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.ws.OrgPolicyWebService;


public class OrgPolicyDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(OrgPolicyDetailController.class);
	private OrgPolicyWebService orgPolicyService; 
 	protected String redirectView;
	
	
	public OrgPolicyDetailController() {
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


		OrgPolicyCommand orgPolicyCmd = new OrgPolicyCommand();

		String orgPolicyId = request.getParameter("orgPolicyId");
		if (orgPolicyId == null) {
			return orgPolicyCmd;
		}
		
		OrgPolicy orgPolicy = orgPolicyService.getPolicyMessageById(orgPolicyId).getAcceptanceMsg();
		
		orgPolicyCmd.setOrgPolicy(orgPolicy);
	
		return orgPolicyCmd;

}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("OrgPolicyDetailController:onSubmit called.");
		
		OrgPolicyCommand orgPolicyCmd = (OrgPolicyCommand)command;
		OrgPolicy orgPolicy = orgPolicyCmd.getOrgPolicy();

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			orgPolicyService.removePolicyMessage(orgPolicy.getOrgPolicyId());
			ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Location has been successfully deleted.");
	        return mav;
		}else {	
			if (orgPolicy.getOrgPolicyId() == null || orgPolicy.getOrgPolicyId().length() == 0) {
				orgPolicy.setOrgPolicyId(null);
				orgPolicyService.addPolicyMessage(orgPolicy);
			}else {
				orgPolicyService.updatePolicyMessage(orgPolicy);
			}
		}
		
		ModelAndView mav = new ModelAndView(new RedirectView(redirectView, true));
		mav.addObject("orgPolicyCmd", orgPolicyCmd);
		
		return mav;
	}


	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public OrgPolicyWebService getOrgPolicyService() {
		return orgPolicyService;
	}

	public void setOrgPolicyService(OrgPolicyWebService orgPolicyService) {
		this.orgPolicyService = orgPolicyService;
	}



	



	

}
