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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.module.client.MuleClient;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.base.BaseObject;
import org.openiam.base.SysConfiguration;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.connector.type.*;
import org.openiam.exception.EncryptionException;
import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDAO;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.service.PasswordGenerator;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.AccountLockEnum;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.dto.ProvisionGroup;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.LookupUserResponse;
import org.openiam.provision.resp.PasswordResponse;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.spml2.msg.*;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;

/**
 * @author suneet
 */
@WebService(endpointInterface = "org.openiam.provision.service.AsynchUserProvisionService",
        targetNamespace = "http://www.openiam.org/service/provision",
        portName = "DefaultProvisionControllerServicePort",
        serviceName = "AsynchUserProvisionService")
public class AsynchUserProvisioningServiceImpl implements MuleContextAware, AsynchUserProvisionService {

    protected static final Log log = LogFactory.getLog(AsynchUserProvisioningServiceImpl.class);

    protected ProvisionService provisionService;
    MuleContext muleContext;

    static protected ResourceBundle res = ResourceBundle.getBundle("datasource");
    static String serviceHost = res.getString("openiam.service_base");
	static String serviceContext = res.getString("openiam.idm.ws.path");

    /* (non-Javadoc)
      * @see org.openiam.provision.service.ProvisionService#addUser(org.openiam.provision.dto.ProvisionUser)
      */
    public void addUser(ProvisionUser user) {
        log.debug("START PROVISIONING - ADD USER CALLED...................");

		try {

			Map<String,String> msgPropMap =  new HashMap<String,String>();
			msgPropMap.put("SERVICE_HOST", serviceHost);
			msgPropMap.put("SERVICE_CONTEXT", serviceContext);


			//Create the client with the context
			MuleClient client = new MuleClient(muleContext);
			client.sendAsync("vm://provisionServiceAddMessage", (ProvisionUser)user, msgPropMap);

		}catch(Exception e) {
			log.debug("EXCEPTION:AsynchIdentitySynchService");
			log.error(e);
			//e.printStackTrace();
		}
		log.debug("END PROVISIONING - ADD USER ---------------------");

    }


    /* (non-Javadoc)
      * @see org.openiam.provision.service.ProvisionService#modifyUser(org.openiam.provision.dto.ProvisionUser)
      */
    public void modifyUser(ProvisionUser user) {
            log.debug("START PROVISIONING - MODIFY USER CALLED...................");

            try {

                Map<String,String> msgPropMap =  new HashMap<String,String>();
                msgPropMap.put("SERVICE_HOST", serviceHost);
                msgPropMap.put("SERVICE_CONTEXT", serviceContext);


                //Create the client with the context
                MuleClient client = new MuleClient(muleContext);
                client.sendAsync("vm://provisionServiceModifyMessage", (ProvisionUser)user, msgPropMap);

            }catch(Exception e) {
                log.debug("EXCEPTION:AsynchIdentitySynchService");
                log.error(e);
                //e.printStackTrace();
            }
            log.debug("END PROVISIONING - MODIFY USER ---------------------");


    }



    public ProvisionService getProvisionService() {
        return provisionService;
    }

    public void setProvisionService(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    public void setMuleContext(MuleContext ctx) {
        log.debug("AsynchUserProvisioningServiceImpl - setMuleContext called.");
        muleContext = ctx;

    }


}
