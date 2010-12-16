/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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

/**
 * 
 */
package org.openiam.provision.service;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;

/**
 * Wraps around the connector interface and manages the calls to the varous operations for the 
 * connectors for provisioning.
 * @author suneet
 *
 */
public class ConnectorAdapter {

	protected static final Log log = LogFactory.getLog(ConnectorAdapter.class);
	
	protected ConnectorDataService connectorService;
	

	
	public void addRequest( ManagedSys managedSys,  AddRequestType addReqType) {
				
		if ( managedSys == null) {
			return;
		}
		log.info("ConnectorAdapter:addRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
		log.info("Connector found for " + connector.getConnectorId() ); 
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0)) {
					
			ConnectorService port = getService(connector);
            port.add(addReqType);

		}
					
			
	}

	public void modifyRequest( ManagedSys managedSys,  ModifyRequestType modReqType) {
	try {	
		if ( managedSys == null) {
			return;
		}
		log.info("ConnectorAdapter:modifyRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
		log.info("Connector found for " + connector.getConnectorId() ); 
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0)) {
					
			ConnectorService port = getService(connector);
            port.modify(modReqType);

		}
	}catch(Exception e) {
		e.printStackTrace();
	}
					
			
	}

	public LookupResponseType lookupRequest(ManagedSys managedSys, LookupRequestType req ) {
		if ( managedSys == null) {
			return null;
		}
		log.info("ConnectorAdapter:lookupRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
		log.info("Connector found for " + connector.getConnectorId() ); 
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0)) {
					
			ConnectorService port = getService(connector);
            return port.lookup(req);

		}		
		return null;
	}
	
	public void deleteRequest( ManagedSys managedSys,  DeleteRequestType delReqType) {
		
		if ( managedSys == null) {
			return;
		}
		log.info("ConnectorAdapter:deleteRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
		log.info("Connector found for " + connector.getConnectorId() ); 
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
					
			ConnectorService port = getService(connector);
            port.delete(delReqType);

		}
					
			
	}
	
	public void setPasswordRequest( ManagedSys managedSys,  SetPasswordRequestType request) {
		
		if ( managedSys == null) {
			return;
		}
		log.info("ConnectorAdapter:setPasswordRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
		log.info("Connector found for " + connector.getConnectorId() ); 
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
					
			ConnectorService port = getService(connector);
            port.setPassword(request);

		}
					
			
	}
	
	public void resetPasswordRequest( ManagedSys managedSys,  ResetPasswordRequestType request) {
		
		if ( managedSys == null) {
			return;
		}
		log.info("ConnectorAdapter:addRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
		log.info("Connector found for " + connector.getConnectorId() ); 
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
					
			ConnectorService port = getService(connector);
            port.resetPassword(request);

		}
					
			
	}
	
	

	
	private ConnectorService getService(ProvisionConnector connector ) {
		try {
			
			QName SERVICE_NAME = new QName(connector.getServiceUrl());
			QName PORT_NAME = new QName(connector.getServiceNameSpace(),connector.getServicePort());
			
			Service service = Service.create(SERVICE_NAME);
			service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, connector.getServiceUrl());
			
			ConnectorService port = service.getPort(new QName(connector.getServiceNameSpace(),
					connector.getServicePort()), 
					ConnectorService.class);
			return port;
			
			/*		

		System.out.println(" Port = " + port );

		Client client =  ClientProxy.getClient(port);
			//Conduit conduit =  client.getConduit();
		System.out.println("Conduit type=" + client.getConduit());
		
		//HTTPConduit http = (HTTPConduit) client.getConduit();
		//MuleUniversalConduit muleConduit = client.getConduit();
	*/		

		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		/*  ------------- working code ------------ */
	/*	Service service = Service.create(QName.valueOf(connector.getServiceUrl()));
		service.addPort(new QName(connector.getServiceNameSpace(),
				connector.getServicePort()),
				SOAPBinding.SOAP11HTTP_BINDING, 
				connector.getServiceUrl());
	
		ConnectorService port = service.getPort(new QName(connector.getServiceNameSpace(),
				connector.getServicePort()), 
				ConnectorService.class);
		return port;
	*/
		
	/*	Client client =  ClientProxy.getClient(port);
		MuleUniversalConduit conduit = client.getConduit();
		
		//HTTPConduit http = (HTTPConduit) client.getConduit().;

		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

		httpClientPolicy.setConnectionTimeout(36000);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setReceiveTimeout(32000);

		http.setClient(httpClientPolicy);
	*/	

		
	}

	
	
	public ConnectorDataService getConnectorService() {
		return connectorService;
	}

	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
	}

	


}
