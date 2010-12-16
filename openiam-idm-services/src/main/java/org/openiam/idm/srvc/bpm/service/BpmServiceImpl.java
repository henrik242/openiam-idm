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
package org.openiam.idm.srvc.bpm.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.jws.WebService;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.ow2.bonita.facade.WebAPI;
import org.ow2.bonita.facade.internal.InternalWebAPI;
import org.ow2.bonita.util.AccessorUtil;
import org.ow2.bonita.util.SimpleCallbackHandler;

import org.bonitasoft.console.security.server.api.ICredentialsEncryptionAPI;
import org.bonitasoft.console.security.server.api.impl.CredentialsEncryptionAPIImpl;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.bpm.service.BpmService", 
		targetNamespace = "http://www.openiam.org/service/bpm", 
		portName = "BpmWebServicePort", 
		serviceName = "BpmWebService")
public class BpmServiceImpl implements BpmService {

	
	private static final Log log = LogFactory.getLog(BpmServiceImpl.class);
	
	


	public Response getBpmSecurityToken(String principal, String password) {

		String encryptedString = null;
		
		// log into the bonita engine

	
		try {
			CallbackHandler handler = new SimpleCallbackHandler("admin", "bpm");
			LoginContext loginContext = new LoginContext("BonitaStore", handler);
			loginContext.login();
		}catch(LoginException le) {
			log.error(le);
			Response resp = new Response(ResponseStatus.FAILURE);
			return resp;				
		}
		
		ICredentialsEncryptionAPI encryptApi = CredentialsEncryptionAPIImpl.getInstance();
		try {
			encryptedString = encryptApi.encryptCredential(principal + ICredentialsEncryptionAPI.USER_CREDENTIALS_SEPARATOR + password );
		}catch(IOException io) {
			log.error(io);
			Response resp = new Response(ResponseStatus.FAILURE);
			return resp;
		}catch(GeneralSecurityException gse) {
			log.error(gse);
			Response resp = new Response(ResponseStatus.FAILURE);
			return resp;			
		}
		
		WebAPI webApi =  AccessorUtil.getWebAPI();
		String token =  webApi.generateTemporaryToken(encryptedString);
		
		Response resp = new Response(ResponseStatus.SUCCESS);
		resp.setResponseValue(token);
		return resp;
		

	}

	
//	- call the web security API to encrypt your credentials (ICredentialsEncryptionAPI.encryptCredential(username + ICredentialsEncryptionAPI.USER_CREDENTIALS_SEPARATOR + password))
//	- call Bonita Runtime API (WebAPI.generateTemporaryToken(yourEncryptedCredentials)). This operation will return a string representing a unique token to login to Bonita application.
//	- then you have to add this parameter to the URL when accessing bonita applications (identityKey=yourToken).



}
