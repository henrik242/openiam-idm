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

/**
 * 
 */
package org.openiam.spml2.spi.script;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.provision.type.ExtensibleAddress;
import org.openiam.provision.type.ExtensibleEmailAddress;
import org.openiam.provision.type.ExtensibleGroup;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensiblePhone;
import org.openiam.provision.type.ExtensibleRole;
import org.openiam.provision.type.ExtensibleUser;

import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.ListTargetsRequestType;
import org.openiam.spml2.msg.ListTargetsResponseType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;
import org.openiam.spml2.spi.ldap.LdapConnectorImpl;

/**
 * Connector shell that can be used to jumpstart the creation of a connector service.
 * @author suneet
 *
 */

@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "ScriptConnectorServicePort", 
		serviceName="ScriptConnectorService")

public class ScriptConnectorImpl implements ConnectorService {

	private static final Log log = LogFactory.getLog(LdapConnectorImpl.class);
	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected ResourceDataService resourceDataService;
	protected String scriptEngine;
	
	public boolean testConnection(String targetID) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#add(org.openiam.spml2.msg.AddRequestType)
	 */
	public AddResponseType add(AddRequestType reqType) {
		System.out.println("add request called..");
		

		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
		}
		
		bindingMap.put("reqType", reqType);
		String output = (String)se.execute(bindingMap, "connector/" + reqType.getTargetID() + "/add.groovy");
		
		AddResponseType resp = new AddResponseType();
		resp.setRequestID(reqType.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
		

	}
	

	

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#delete(org.openiam.spml2.msg.DeleteRequestType)
	 */
	public ResponseType delete(DeleteRequestType reqType) {
		System.out.println("delete request called..");
		
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
		}
		
		bindingMap.put("reqType", reqType);
		String output = (String)se.execute(bindingMap, "connector/" + reqType.getPsoID().getTargetID() + "/delete.groovy");

		
	
		ResponseType resp = new ResponseType();
		resp.setRequestID(reqType.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#listTargets(org.openiam.spml2.msg.ListTargetsRequestType)
	 */
	public ListTargetsResponseType listTargets(ListTargetsRequestType reqType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#lookup(org.openiam.spml2.msg.LookupRequestType)
	 */
	public LookupResponseType lookup(LookupRequestType reqType) {
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
		}
		
		bindingMap.put("reqType", reqType);
		List output = (List)se.execute(bindingMap, "connector/" + reqType.getPsoID().getTargetID() + "/lookup.groovy");
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#modify(org.openiam.spml2.msg.ModifyRequestType)
	 */
	public ModifyResponseType modify(ModifyRequestType reqType) {
		System.out.println("modify request called..");
		
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
		}
		
		bindingMap.put("reqType", reqType);
		String output = (String)se.execute(bindingMap, "connector/" + reqType.getPsoID().getTargetID() + "/modify.groovy");

		

		ModifyResponseType resp = new ModifyResponseType();
		resp.setRequestID(reqType.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlPassword#expirePassword(org.openiam.spml2.msg.password.ExpirePasswordRequestType)
	 */
	public ResponseType expirePassword(ExpirePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlPassword#resetPassword(org.openiam.spml2.msg.password.ResetPasswordRequestType)
	 */
	public ResetPasswordResponseType resetPassword(
			ResetPasswordRequestType request) {
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
		}
		
		bindingMap.put("reqType", request);
		String output = (String)se.execute(bindingMap, "connector/" + request.getPsoID().getTargetID() + "/resetPassword.groovy");


		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlPassword#setPassword(org.openiam.spml2.msg.password.SetPasswordRequestType)
	 */
	public ResponseType setPassword(SetPasswordRequestType request) {
		System.out.println("setPassword request called..");
		
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
		}
		
		bindingMap.put("reqType", request);
		String output = (String)se.execute(bindingMap, "connector/" + request.getPsoID().getTargetID() + "/setPassword.groovy");
		
	
		ResponseType resp = new ResponseType();
		resp.setRequestID(request.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlPassword#validatePassword(org.openiam.spml2.msg.password.ValidatePasswordRequestType)
	 */
	public ValidatePasswordResponseType validatePassword(
			ValidatePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}
	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}
	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}
	public ManagedSystemObjectMatchDAO getManagedSysObjectMatchDao() {
		return managedSysObjectMatchDao;
	}
	public void setManagedSysObjectMatchDao(
			ManagedSystemObjectMatchDAO managedSysObjectMatchDao) {
		this.managedSysObjectMatchDao = managedSysObjectMatchDao;
	}
	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}
	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}
	public String getScriptEngine() {
		return scriptEngine;
	}
	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}

}
