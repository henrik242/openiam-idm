package org.openiam.webadmin.conn.mngsys;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

public class ManagedSysListController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ManagedSysListController.class);



	private ManagedSystemDataService managedSysService; 
	private SecurityDomainDataService secDomainService;

	


	public ManagedSysListController() {
		super();
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		SecurityDomain[] domainAry = secDomainService.getAllSecurityDomains();
		
		Map model = new HashMap();
		model.put("secDomainAry", domainAry);
		
		return model;
	}
	

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		ManagedSysListCommand sysListCommand = (ManagedSysListCommand)command;
		ManagedSys[] managedSysAry = managedSysService.getManagedSysByDomain( sysListCommand.getDomainId() );
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("managedSysListCmd", sysListCommand);
		mav.addObject("managedSysAry", managedSysAry);
		if (managedSysAry != null) {
			mav.addObject("searchResults", managedSysAry.length);
		}else {
			mav.addObject("searchResults", 0);
		}
		
		return mav;

		
	}



	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	
	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}


	

}
