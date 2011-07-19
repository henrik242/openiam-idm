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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.config.ConfigurationBuilder;
import org.mule.api.context.MuleContextBuilder;
import org.mule.api.context.MuleContextFactory;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.openiam.connector.type.LookupRequest;
import org.openiam.connector.type.LookupResponse;
import org.openiam.connector.type.PasswordRequest;
import org.openiam.connector.type.UserRequest;
import org.openiam.connector.type.UserResponse;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ErrorCode;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.mule.module.client.MuleClient;
import org.openiam.connector.type.ResponseType;


/**
 * Wraps around the connector interface and manages the calls to the varous operations for the 
 * connectors for provisioning.
 * @author suneet
 *
 */
public class RemoteConnectorAdapter {

	protected static final Log log = LogFactory.getLog(RemoteConnectorAdapter.class);
	
	protected ConnectorDataService connectorService;
	

	
	public UserResponse addRequest( ManagedSys managedSys,  UserRequest addReqType, ProvisionConnector connector, MuleContext muleContext) {
		UserResponse resp = new UserResponse();
		try {
			
			
			if ( managedSys == null) {
				resp.setStatus(StatusCodeType.FAILURE);
				resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
				return resp;
			}
			log.debug("ConnectorAdapter:addRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
						
			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0)) {
				
				MuleMessage msg  = getService(connector, addReqType, connector.getServiceUrl(), "add",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof UserResponse) {
						return (UserResponse)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}
			}

		}catch(Exception e) {
			log.error(e);
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
					
			
	}

	public UserResponse modifyRequest( ManagedSys managedSys,  UserRequest request, ProvisionConnector connector,MuleContext muleContext) {
		UserResponse resp = new UserResponse();
		try {	
			if ( managedSys == null) {
				resp.setStatus(StatusCodeType.FAILURE);
				resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
				return resp;
			}
			log.debug("ConnectorAdapter:modifyRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
						
			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0)) {
						
				//ConnectorService port = getService(connector);
	            //port.modify(modReqType);
				MuleMessage msg  = getService(connector, request, connector.getServiceUrl(), "modify",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof UserResponse) {
						return (UserResponse)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}
	
			}
		}catch(Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
					
			
	}

	public LookupResponse lookupRequest(ManagedSys managedSys, LookupRequest req, ProvisionConnector connector,MuleContext muleContext ) {
		
		LookupResponse resp = new LookupResponse();
		
		if ( managedSys == null) {
			resp.setStatus(StatusCodeType.FAILURE);
			resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
			return resp;
		}
		log.debug("ConnectorAdapter:lookupRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
					
		try {

			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0)) {

				MuleMessage msg  = getService(connector, req, connector.getServiceUrl(), "lookup",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof LookupResponse) {
						return (LookupResponse)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}
					
			}		
		}catch(Exception e) {
			log.error(e);
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
		
	}
	
	public UserResponse deleteRequest( ManagedSys managedSys,  UserRequest request , ProvisionConnector connector,MuleContext muleContext) {
		UserResponse resp = new UserResponse();
		
		if ( managedSys == null) {
			resp.setStatus(StatusCodeType.FAILURE);
			resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
			return resp;
		}
		
		log.debug("ConnectorAdapter:deleteRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
		
		try {
					
			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
					
				MuleMessage msg  = getService(connector, request, connector.getServiceUrl(), "delete",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof UserResponse) {
						return (UserResponse)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}

	
			}
		}catch(Exception e) {
			log.error(e);
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
			
	}
	
	public ResponseType setPasswordRequest( ManagedSys managedSys,  PasswordRequest request , ProvisionConnector connector,MuleContext muleContext) {
		ResponseType resp = new ResponseType();
		
		if ( managedSys == null) {
			resp.setStatus(StatusCodeType.FAILURE);
			resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
			return resp;
		}
		
		log.debug("ConnectorAdapter:setPasswordRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
		try {
			
			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
					
				MuleMessage msg  = getService(connector, request, connector.getServiceUrl(), "setPassword",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof ResponseType) {
						return (ResponseType)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}

	
			}
		}catch(Exception e) {
			log.error(e);
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
					
			
	}
	
	public ResponseType resetPasswordRequest( ManagedSys managedSys,  PasswordRequest request , ProvisionConnector connector,MuleContext muleContext) {
		ResponseType resp = new ResponseType();
		
		if ( managedSys == null) {
			resp.setStatus(StatusCodeType.FAILURE);
			resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
			return resp;
		}
		log.debug("ConnectorAdapter:resetPasswordRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
		
		try {
			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
		
				MuleMessage msg  = getService(connector, request, connector.getServiceUrl(), "resetPassword",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof ResponseType) {
						return (ResponseType)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}

				
				//ConnectorService port = getService(connector);
	            //port.resetPassword(request);
	
			}
		}catch(Exception e) {
			log.error(e);
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
					
			
	}

	public ResponseType suspend( ManagedSys managedSys,  SuspendRequestType request , ProvisionConnector connector,MuleContext muleContext) {
		ResponseType resp = new ResponseType();
		
		if ( managedSys == null) {
			resp.setStatus(StatusCodeType.FAILURE);
			resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
			return resp;
		}
		
		log.debug("ConnectorAdapter:suspendRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
				
		try {
			
			if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
	
				MuleMessage msg  = getService(connector, request, connector.getServiceUrl(), "suspend",muleContext);
				if (msg != null) {
					log.debug("***Payload=" + msg.getPayload() );
					if (msg.getPayload() != null && msg.getPayload() instanceof ResponseType) {
						return (ResponseType)msg.getPayload();
					}
					resp.setStatus(StatusCodeType.SUCCESS);
					return resp;
				}else {
					log.debug("MuleMessage is null..");
				}

				//ConnectorService port = getService(connector);
	            //port.suspend(request);
	
			}
		}catch(Exception e) {
			log.error(e);
		}
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
					
			
	}

	public ResponseType resumeRequest( ManagedSys managedSys,  ResumeRequestType request , ProvisionConnector connector,MuleContext muleContext) {
		ResponseType resp = new ResponseType();
		
		if ( managedSys == null) {
			resp.setStatus(StatusCodeType.FAILURE);
			resp.setError(ErrorCode.INVALID_MANAGED_SYS_ID);
			return resp;
		}
		
		log.debug("ConnectorAdapter:resumeRequest called. Managed sys =" + managedSys.getManagedSysId() ); 
	try {				

		
		if (connector != null && (connector.getServiceUrl() != null && connector.getServiceUrl().length() > 0) ) {
			
			MuleMessage msg  = getService(connector, request, connector.getServiceUrl(), "resume",muleContext);
			if (msg != null) {
				log.debug("***Payload=" + msg.getPayload() );
				if (msg.getPayload() != null && msg.getPayload() instanceof ResponseType) {
					return (ResponseType)msg.getPayload();
				}
				resp.setStatus(StatusCodeType.SUCCESS);
				return resp;
			}else {
				log.debug("MuleMessage is null..");
			}
	
			
			//ConnectorService port = getService(connector);
            //port.resume(request);

		}
	}catch(Exception e) {
		log.error(e);
	}
	resp.setStatus(StatusCodeType.FAILURE);
	return resp;
					
			
	}

	
	private MuleMessage getService(ProvisionConnector connector, Object reqType, String url, String operation,MuleContext muleContext ) {
		try {
			//Create a MuleContextFactory
		/*	MuleContextFactory muleContextFactory = new DefaultMuleContextFactory();

			//create the configuration builder and optionally pass in one or more of these
			ConfigurationBuilder builder =  new SpringXmlConfigurationBuilder("mule-service-client-config.xml");
			//The actual context builder to use
			MuleContextBuilder contextBuilder = new DefaultMuleContextBuilder();

			//Create the context
			MuleContext context = muleContextFactory.createMuleContext(builder, contextBuilder);

			//Start the context
			context.start();

			//Create the client with the context
			MuleClient client = new MuleClient(context);
	    */

            MuleClient client = new MuleClient(muleContext);
		
			//Map<?,?> msgPropMap = Collections.singletonMap("serviceName","LDAPConnectorService");
			Map<String,String> msgPropMap =  new HashMap<String,String>(); 
			msgPropMap.put("serviceName", url);			
						
			MuleMessage msg = null;
			
			if (operation.equalsIgnoreCase("add")) {
			
				msg = client.send("vm://remoteConnectorMessageAdd", (UserRequest)reqType, msgPropMap);
			}
			if (operation.equalsIgnoreCase("modify")) {
				
				msg = client.send("vm://remoteConnectorMessageModify", (UserRequest)reqType, msgPropMap);
			}
			if (operation.equalsIgnoreCase("lookup")) {
				
				msg = client.send("vm://remoteConnectorMessageLookup", (LookupRequest)reqType, msgPropMap);
			}
			if (operation.equalsIgnoreCase("delete")) {
				
				msg = client.send("vm://remoteConnectorMessageDelete", (UserRequest)reqType, msgPropMap);
			}
			if (operation.equalsIgnoreCase("setPassword")) {
				
				msg = client.send("vm://remoteConnectorMessageSetPassword", (PasswordRequest)reqType, msgPropMap);
			}

			if (operation.equalsIgnoreCase("resetPassword")) {
				
				msg = client.send("vm://remoteConnectorMessageResetPassword", (PasswordRequest)reqType, msgPropMap);
			}
			if (operation.equalsIgnoreCase("suspend")) {
				
				msg = client.send("vm://remoteConnectorClientSuspend", (SuspendRequestType)reqType, msgPropMap);
			}
			if (operation.equalsIgnoreCase("resume")) {
				
				msg = client.send("vm://remoteConnectorMessageResume", (ResumeRequestType)reqType, msgPropMap);
			}
			
			return msg;
		

			
		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
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
			
		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	
	public ConnectorDataService getConnectorService() {
		return connectorService;
	}

	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
	}

	


}
