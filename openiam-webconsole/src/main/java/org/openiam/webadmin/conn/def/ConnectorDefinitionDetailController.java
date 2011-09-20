package org.openiam.webadmin.conn.def;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;


public class ConnectorDefinitionDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ConnectorDefinitionDetailController.class);
	private ConnectorDataService connectorService; 
	
	private String defaultNameSpace;
	
	
	
	public ConnectorDefinitionDetailController() {
		super();
	}
	
	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {

		
		String connectorId = request.getParameter("connectorId");
		if (connectorId == null) {
			return (new ConnectorDefinitionDetailCommand());
		}
		
		ProvisionConnector connector = connectorService.getConnector(connectorId);
		
		ConnectorDefinitionDetailCommand connectorDetailCmd = 
			new ConnectorDefinitionDetailCommand(connector.getClassName(),
					connector.getClientCommProtocol(), 
					connector.getMetadataTypeId(),
					connector.getName(),
					connector.getConnectorId(),
					connector.getServiceUrl(),
					connector.getStdComplianceLevel(),
					connector.getServiceNameSpace(),
					connector.getServicePort(),
					connector.getConnectorInterface());
		

		return connectorDetailCmd;


}

	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ConnectorDefinitionDetailCommand connectorDetailCmd = (ConnectorDefinitionDetailCommand)command;
		ProvisionConnector connector = getConnector(connectorDetailCmd);

		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			connectorService.removeConnector(connector.getConnectorId());
		}else {	
			if (connector.getConnectorId() == null || connector.getConnectorId().length() == 0) {
				connectorService.addConnector(connector);
			}else {
				connectorService.updateConnector(connector);
			}
		}
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("connectorDetailCmd", connectorDetailCmd);
		
		return mav;
	}
	
	private ProvisionConnector getConnector(ConnectorDefinitionDetailCommand connectorDetailCmd ) {
		ProvisionConnector con = new ProvisionConnector();
		con.setClassName(connectorDetailCmd.getClassName());
		con.setClientCommProtocol(connectorDetailCmd.getClientCommProtocol());
		if (connectorDetailCmd.getProvConnectorId() != null && connectorDetailCmd.getProvConnectorId().length() > 0 ) {
			con.setConnectorId(connectorDetailCmd.getProvConnectorId());
		}
		con.setMetadataTypeId(connectorDetailCmd.getMetadataTypeId());
		con.setName(connectorDetailCmd.getName());
		con.setServiceUrl(connectorDetailCmd.getServiceUrl());
		con.setStdComplianceLevel(connectorDetailCmd.getStdComplianceLevel());
		if (connectorDetailCmd.getServiceNameSpace() != null && connectorDetailCmd.getServiceNameSpace().length() > 0) {
			con.setServiceNameSpace(connectorDetailCmd.getServiceNameSpace());
		}else {
			con.setServiceNameSpace(defaultNameSpace);
		}
		con.setServicePort(connectorDetailCmd.getServicePort());
		con.setConnectorInterface(connectorDetailCmd.getConnectorInterface());
		return con;
	}


	



	public ConnectorDataService getConnectorService() {
		return connectorService;
	}

	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
	}

	public String getDefaultNameSpace() {
		return defaultNameSpace;
	}

	public void setDefaultNameSpace(String defaultNameSpace) {
		this.defaultNameSpace = defaultNameSpace;
	}

	

}
