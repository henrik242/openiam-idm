package org.openiam.webadmin.conn.def;


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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;



public class ConnectorDefinitionListController extends AbstractController {


	private static final Log log = LogFactory.getLog(ConnectorDefinitionListController.class);
	private String successView;
	private ConnectorDataService connectorService; 

	
	
	public ConnectorDefinitionListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub

		ProvisionConnector[] connectoryAry = connectorService.getAllConnectors();

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("connectorAry", connectoryAry);
		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ConnectorDataService getConnectorService() {
		return connectorService;
	}

	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
	}

	

}
