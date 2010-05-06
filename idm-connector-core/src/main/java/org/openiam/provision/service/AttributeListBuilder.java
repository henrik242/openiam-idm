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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.script.ScriptIntegration;

/**
 * Builds a list of attributes that are to be sent to the connectors.
 * This list can be generated from the groovy scripts that contain rules for provisioning or by sending
 * in the complete User object.
 * @author suneet
 *
 */
public class AttributeListBuilder {
	
	protected static final Log log = LogFactory.getLog(AttributeListBuilder.class);

	public ExtensibleUser buildFromRules(ProvisionUser pUser, 
			List<AttributeMap> attrMap, ScriptIntegration se,
			String managedSysId, String domainId,
			Map<String, Object> bindingMap,
			String createdBy) {
		
		ExtensibleUser extUser = new ExtensibleUser();

		//List<Login> principalList = new ArrayList<Login>();
 		//List<AttributeMap> policyAttrMap = resourceDataService.getAttributeMapForResource(sysConfiguration.getDefaultManagedSysId());
 		
	
 		if (attrMap != null) {
 			
 			log.info("- attrMap IS NOT null");
 			
 			Login identity = new Login();
 			LoginId loginId = new LoginId();
 			EmailAddress emailAddress = new EmailAddress();
 			
 			// init values
 			loginId.setDomainId(domainId);
 			loginId.setManagedSysId(managedSysId);
 		//	primaryIdentity.setUserId(user.getUser().getUserId());
 			
			for (  AttributeMap attr : attrMap ) {
				Policy policy = attr.getAttributePolicy();
				String url = policy.getRuleSrcUrl();
				if (url != null) {
					String output = (String)se.execute(bindingMap, url);
					if (output != null) {
						String objectType = attr.getMapForObjectType();
						if (objectType != null) {
							if (objectType.equalsIgnoreCase("PRINCIPAL")) {
								loginId.setLogin(output);	
							}
							if (objectType.equalsIgnoreCase("USER")) {
								extUser.getAttributes().add(new ExtensibleAttribute(attr.getAttributeName(), output, 1));
							}
						}
					}
				}
			}
			identity.setId(loginId);
			identity.setAuthFailCount(0);
			identity.setCreateDate(new Date(System.currentTimeMillis()));
			identity.setCreatedBy(createdBy);
			identity.setIsLocked(0);
			identity.setFirstTimeLogin(1);
			identity.setStatus("ACTIVE");
			pUser.getPrincipalList().add(identity);
			
 			
 		}else {
 			log.info("- attMap IS null");
 		}

 		
		
		return extUser;
		
		
	}
	

	public ExtensibleUser buildModifyFromRules(ProvisionUser pUser,
			Login currentIdentity,
			List<AttributeMap> attrMap, ScriptIntegration se,
			String managedSysId, String domainId,
			Map<String, Object> bindingMap,
			String createdBy) {
		
		ExtensibleUser extUser = new ExtensibleUser();
		
	
 		if (attrMap != null) {
 			
 			log.info("- attrMap IS NOT null");
 			
 			//Login identity = new Login();
 			//LoginId loginId = new LoginId();
 			//EmailAddress emailAddress = new EmailAddress();
 			
 			// init values
 			//loginId.setDomainId(domainId);
 			//loginId.setManagedSysId(managedSysId);
 		//	primaryIdentity.setUserId(user.getUser().getUserId());
 			
			for (  AttributeMap attr : attrMap ) {
				Policy policy = attr.getAttributePolicy();
				String url = policy.getRuleSrcUrl();
				if (url != null) {
					String output = (String)se.execute(bindingMap, url);
					if (output != null) {
						String objectType = attr.getMapForObjectType();
						if (objectType != null) {
							//if (objectType.equalsIgnoreCase("PRINCIPAL")) {
							//	loginId.setLogin(output);	
							//}
							if (objectType.equalsIgnoreCase("USER")) {
								extUser.getAttributes().add(new ExtensibleAttribute(attr.getAttributeName(), output, 1));
							}
						}
					}
				}
			}
		/*	identity.setId(loginId);
			identity.setAuthFailCount(0);
			identity.setCreateDate(new Date(System.currentTimeMillis()));
			identity.setCreatedBy(createdBy);
			identity.setIsLocked(0);
			identity.setFirstTimeLogin(1);
			identity.setStatus("ACTIVE");
		*/
			
			if (pUser.getPrincipalList() == null) {
				List<Login> principalList = new ArrayList<Login>();
				principalList.add(currentIdentity);
				pUser.setPrincipalList(principalList);
			}else {
				pUser.getPrincipalList().add(currentIdentity);
			}
 			
 		}else {
 			log.info("- attMap IS null");
 		}

 		
		
		return extUser;
		
		
	}

}
