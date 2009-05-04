package org.openiam.webadmin.conn.mngsys;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */


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
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Controller to manage connectivity information for a managed system
 * @author suneet
 *
 */
public class ManagedSysConnectionController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ManagedSysConnectionController.class);



	private ManagedSystemDataService managedSysService; 
	private SecurityDomainDataService secDomainService;
	private ConnectorDataService connectorService;
	
	


	public ManagedSysConnectionController() {
		super();
	}

	
	

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String connectorId = request.getParameter("connectorId");
		String menuGroup = request.getParameter("menuGroup");
		
		// used by the UI for to show the side menu
		request.setAttribute("menuGroup", menuGroup);
		request.setAttribute("connectorId", connectorId);
		
		ManagedSys sys = managedSysService.getManagedSys(connectorId);
		
		if (sys == null) {
			return new ManagedSysConnectionCommand();
		}else {
			return new ManagedSysConnectionCommand(
					sys.getCommProtocol(), sys.getConnectorId(),
					sys.getDescription(), sys.getDomainId(), sys.getEndDate(), sys.getHostUrl(),
					sys.getManagedSysId(), sys.getName(), sys.getPort(), sys.getPswd(),
					sys.getStartDate(), sys.getStatus(), sys.getUserId()); 
		}
	}





	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		SecurityDomain[] domainAry = secDomainService.getAllSecurityDomains();
		ProvisionConnector[] connectorAry = (ProvisionConnector[])connectorService.getAllConnectors();
		
		
		Map model = new HashMap();
		model.put("secDomainAry", domainAry);
		model.put("connectors", connectorAry);
		
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





	public ConnectorDataService getConnectorService() {
		return connectorService;
	}




	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
	}


	

}
