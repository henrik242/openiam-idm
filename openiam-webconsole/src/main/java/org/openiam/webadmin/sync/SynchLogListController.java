package org.openiam.webadmin.sync;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.service.ReferenceDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.ContactConstants;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;

import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.ws.LocationDataWebService;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.webadmin.admin.AppConfiguration;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;


/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class SynchLogListController extends SimpleFormController {


	protected String redirectView; 	

	protected IdentitySynchWebService synchConfig;
	private static final Log log = LogFactory.getLog(SynchLogListController.class);
	protected NavigatorDataWebService navigationDataService;

	
	public SynchLogListController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	


	
	@Override
	protected Object formBackingObject(HttpServletRequest request) 			throws Exception {
		
		SynchLogListCommand cmd = new SynchLogListCommand();

		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		String configId = request.getParameter("objId");
		if (configId != null && configId.length() > 0) {
			request.setAttribute("objId",configId);
		}
		
		String menuGrp = request.getParameter("menugrp");
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		
		
		return cmd;
	}




	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		// TODO Auto-generated method stub
		SynchLogListCommand cmd = (SynchLogListCommand)command;
		SynchConfig config =  cmd.getSyncConfig();
	
		String btn = request.getParameter("btn");
		String configId = config.getSynchConfigId();

		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			
			synchConfig.removeConfig(configId);			
	        ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Configuration has been successfully deleted.");
	        return mav;

		}
		if (btn != null && btn.equalsIgnoreCase("Sync Now")) {
			
			config.setSynchAdapter("RDBMS");
			synchConfig.startSynchronization(config);			
	        
	        return new ModelAndView(new RedirectView(redirectView, true));

		}
		
		if (config.getSynchConfigId() == null || 
				(config.getSynchConfigId() != null && config.getSynchConfigId().length() == 0)) {
			config.setSynchConfigId(null);
			synchConfig.addConfig(config);
		}else {
			synchConfig.updateConfig(config);
		}
		
	
		return new ModelAndView(new RedirectView(redirectView, true));


        
	}


	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public IdentitySynchWebService getSynchConfig() {
		return synchConfig;
	}


	public void setSynchConfig(IdentitySynchWebService synchConfig) {
		this.synchConfig = synchConfig;
	}




	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}



	
	




}
