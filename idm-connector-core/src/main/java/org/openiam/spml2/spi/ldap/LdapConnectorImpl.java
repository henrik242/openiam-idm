/*
 * Copyright 2009-2011, OpenIAM LLC
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
package org.openiam.spml2.spi.ldap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.ConfigurationException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.ws.AuthenticationResponse;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.base.AbstractSpml2Complete;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.*;
import org.openiam.spml2.msg.password.*;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;
import org.openiam.spml2.util.connect.ConnectionFactory;
import org.openiam.spml2.util.connect.ConnectionManagerConstant;
import org.openiam.spml2.util.connect.ConnectionMgr;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import javax.xml.namespace.QName;
import java.util.*;

/**
 * Updates the OpenIAM repository with data received from external client.
 *
 * @author suneet
 */

@WebService(endpointInterface = "org.openiam.spml2.interf.ConnectorService",
        targetNamespace = "http://www.openiam.org/service/connector",
        portName = "LDAPConnectorServicePort",
        serviceName = "LDAPConnectorService")
public class LdapConnectorImpl extends AbstractSpml2Complete implements ConnectorService {

    private static final Log log = LogFactory.getLog(LdapConnectorImpl.class);
    protected ManagedSystemDataService managedSysService;
    protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
    protected ResourceDataService resourceDataService;
    protected IdmAuditLogDataService auditDataService;
    protected LoginDataService loginManager;
    protected PolicyDAO policyDao;
    protected SecurityDomainDataService secDomainService;
    protected UserDataService userManager;

    protected LdapSuspend ldapSuspend;
    protected LdapPassword ldapPassword;

    static String keystore;

    public AuthenticationResponse login(AuthenticationContext authContext) {

        AuthenticationResponse resp = new AuthenticationResponse();

        Subject sub = new Subject();

        log.debug("login() in LdapConnectorImpl called");

        // current date
        Date curDate = new Date(System.currentTimeMillis());
        PasswordCredential cred = (PasswordCredential) authContext.getCredential();

        String principal = cred.getPrincipal();
        String domainId = cred.getDomainId();
        String password = cred.getPassword();

        User user = authContext.getUser();
        Login lg = authContext.getLogin();
        String managedSysId = authContext.getManagedSysId();
        SecurityDomain securityDomain = this.secDomainService.getSecurityDomain(domainId);


        if (user != null && user.getStatus() != null) {
            log.debug("User Status=" + user.getStatus());
            if (user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {
                if (!pendingInitialStartDateCheck(user, curDate)) {
                    log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID USER STATUS", domainId, null, principal, null, null);
                    resp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
                    return resp;
                }
            }
            if (!user.getStatus().equals(UserStatusEnum.ACTIVE) && !user.getStatus().equals(UserStatusEnum.PENDING_INITIAL_LOGIN)) {
                // invalid status
                log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID USER STATUS", domainId, null, principal, null, null);
                resp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
                return resp;
            }
            // check the secondary status
            log.debug("Secondary status=" + user.getSecondaryStatus());
            int ret = checkSecondaryStatus(user);
            if (ret != 1) {
                resp.setAuthErrorCode(ret);
                return resp;
            }

        }
        // get the id of the user from the openiam repository
        List<Login> principalList = loginManager.getLoginByUser(user.getUserId());
        if (principalList == null) {
            log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", domainId, null, principal, null, null);
            resp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
            return resp;
        }
        Login ldapLogin = null;
        for (Login l : principalList) {
            if (l.getId().getManagedSysId().equalsIgnoreCase(managedSysId)) {
                ldapLogin = l;
            }
        }
        if (ldapLogin == null) {
            log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", domainId, null, principal, null, null);
            resp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
            return resp;

        }
        if (!ldapLogin.getId().getLogin().contains(principal)) {
            log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID LOGIN", domainId, null, principal, null, null);
            resp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_LOGIN);
            return resp;

        }


        // try to login to AD with this user
        LdapContext tempCtx = connect(ldapLogin.getId().getLogin(), password);
        if (tempCtx == null) {
            log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "RESULT_INVALID_PASSWORD", domainId, null, principal, null, null);
            resp.setAuthErrorCode(AuthenticationConstants.RESULT_INVALID_PASSWORD);
            return resp;
        }


        log.debug("Authentication policyid=" + securityDomain.getAuthnPolicyId());
        // get the authentication lock out policy
        Policy plcy = policyDao.findById(securityDomain.getAuthnPolicyId());
        String attrValue = getPolicyAttribute(plcy.getPolicyAttributes(), "FAILED_AUTH_COUNT");

        String tokenType = getPolicyAttribute(plcy.getPolicyAttributes(), "TOKEN_TYPE");
        String tokenLife = getPolicyAttribute(plcy.getPolicyAttributes(), "TOKEN_LIFE");
        String tokenIssuer = getPolicyAttribute(plcy.getPolicyAttributes(), "TOKEN_ISSUER");


        Map tokenParam = new HashMap();
        tokenParam.put("TOKEN_TYPE", tokenType);
        tokenParam.put("TOKEN_LIFE", tokenLife);
        tokenParam.put("TOKEN_ISSUER", tokenIssuer);
        tokenParam.put("PRINCIPAL", principal);

        // update the login and user records to show this authentication
        lg.setLastAuthAttempt(new Date(System.currentTimeMillis()));
        lg.setLastLogin(new Date(System.currentTimeMillis()));
        lg.setAuthFailCount(0);
        lg.setFirstTimeLogin(0);
        log.debug("Good Authn: Login object updated.");
        loginManager.updateLogin(lg);

        // check the user status
        if (user.getStatus() != null) {
            if (user.getStatus().equals(UserStatusEnum.PENDING_INITIAL_LOGIN) ||
                    // after the start date
                    user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {

                user.setStatus(UserStatusEnum.ACTIVE);
                userManager.updateUser(user);
            }
        }

        // Successful login
        sub.setUserId(lg.getUserId());
        sub.setPrincipal(principal);
        sub.setSsoToken(token(lg.getUserId(), tokenParam));
        sub.setDomainId(domainId);
        setResultCode(lg, sub, curDate);

        // send message into to audit log

        log("AUTHENTICATION", "AUTHENTICATION", "SUCCESS", null, domainId, user.getUserId(), principal, null, null);

        resp.setSubject(sub);
        return resp;


    }

      public LdapContext connect(String userName, String password) {

        //LdapContext ctxLdap = null;
        Hashtable<String, String> envDC = new Hashtable();

        //keystore = secres.getString("KEYSTORE");
        System.setProperty("javax.net.ssl.trustStore", keystore);

        log.debug("Connecting to ldap using principal=" + userName);

        //envDC.put(Context.PROVIDER_URL,host);
        envDC.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        envDC.put(Context.SECURITY_AUTHENTICATION, "simple"); // simple
        envDC.put(Context.SECURITY_PRINCIPAL, userName);  //"administrator@diamelle.local"
        envDC.put(Context.SECURITY_CREDENTIALS, password);
        //	if (protocol != null && protocol.equalsIgnoreCase("SSL")) {
        //		envDC.put(Context.SECURITY_PROTOCOL, protocol);
        //	}

        try {
            return (new InitialLdapContext(envDC, null));
        } catch (NamingException ne) {
            log.error(ne.getMessage());

        }
        return null;
    }



    public ResponseType testConnection(ManagedSys managedSys) {
        ResponseType response = new ResponseType();
        response.setStatus(StatusCodeType.SUCCESS);

        ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);

          try {

            LdapContext ldapctx = conMgr.connect(managedSys);
                } catch (NamingException ne) {
            log.error(ne);
            // return a response object - even if it fails so that it can be logged.
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.DIRECTORY_ERROR);
            response.addErrorMessage(ne.toString());

        } finally {
            /* close the connection to the directory */
            try {
                if (conMgr != null) {
                    conMgr.close();
                }
            } catch (NamingException n) {
                log.error(n);
            }

        }

        return response;
    }

    /* (non-Javadoc)
      * @see org.openiam.spml2.interf.SpmlCore#add(org.openiam.spml2.msg.AddRequestType)
      */
    public AddResponseType add(AddRequestType reqType) {
        AddResponseType response = new AddResponseType();
        response.setStatus(StatusCodeType.SUCCESS);


        log.debug("add request called..");

        String requestID = reqType.getRequestID();
        /* ContainerID - May specify the container in which this object should be created
           *      ie. ou=Development, org=Example */
        PSOIdentifierType containerID = reqType.getContainerID();
        /* PSO - Provisioning Service Object -
           *     -  ID must uniquely specify an object on the target or in the target's namespace
           *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
        PSOIdentifierType psoID = reqType.getPsoID();
        /* targetID -  */
        String targetID = reqType.getTargetID();

        // Data sent with request - Data must be present in the request per the spec
        ExtensibleType data = reqType.getData();
        Map<QName, String> otherData = reqType.getOtherAttributes();

        /* Indicates what type of data we should return from this operations */
        ReturnDataType returnData = reqType.getReturnData();


        /* A) Use the targetID to look up the connection information under managed systems */
        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
        ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
        try {

            LdapContext ldapctx = conMgr.connect(managedSys);

            log.debug("Ldapcontext = " + ldapctx);

            ManagedSystemObjectMatch matchObj = null;
            List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
            if (matchObjList != null && matchObjList.size() > 0) {
                matchObj = matchObjList.get(0);
            }

            log.debug("matchObj = " + matchObj);

            if (matchObj == null) {
                throw new ConfigurationException("LDAP configuration is missing configuration information");
            }

            log.debug("baseDN=" + matchObj.getBaseDn());
            log.debug("ID field=" + matchObj.getKeyField());

            // get the baseDN
            String baseDN = matchObj.getBaseDn();


            // get the field that is to be used as the UniqueIdentifier
            //String ldapName = matchObj.getKeyField() +"=" + psoID.getID() + "," + baseDN;
            String ldapName = psoID.getID();

            // check if the identity exists in ldap first before creating the identity
            if (identityExists(ldapName, ldapctx)) {
                log.debug(ldapName + "exists. Returning success from the connector");
                return response;
            }
            //



            BasicAttributes basicAttr = getBasicAttributes(reqType.getData().getAny(), matchObj.getKeyField());


            Context result = ldapctx.createSubcontext(ldapName, basicAttr);

            // add the user to a role
            // temp hack

            //String role = getRole(reqType.getData().getAny());
            List<String> roleStrList = getRole(reqType.getData().getAny());
            if (roleStrList != null && roleStrList.size() > 0) {
                for (String r : roleStrList) {
                    r = r.toLowerCase();
                    String roleDN = "cn=" + r + ",ou=roles,dc=gtawestdir,dc=com";

                       ModificationItem mods[] = new ModificationItem[1];
                       mods[0]= new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("uniqueMember", ldapName));
                       ldapctx.modifyAttributes(roleDN,mods);
                }
            }

            String org = getOrg(reqType.getData().getAny());
            String orgDN = "cn=" + org + ",ou=affiliations,dc=gtawestdir,dc=com";
            if (org != null && org.length() > 0) {
               ModificationItem mods[] = new ModificationItem[1];
               mods[0]= new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("uniqueMember", ldapName));
               ldapctx.modifyAttributes(orgDN,mods);
            }

            /*

            InitialDirContext ctx = new InitialDirContext(env);

			//Create a LDAP add attribute for the member attribute
			ModificationItem mods[] = new ModificationItem[1];
			mods[0]= new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("member", userName));

			//update the group
			ctx.modifyAttributes(groupName,mods);

			ctx.close();


            */




        } catch (NamingException ne) {
            log.error(ne);
            // return a response object - even if it fails so that it can be logged.
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.DIRECTORY_ERROR);
            response.addErrorMessage(ne.toString());

        } finally {
            /* close the connection to the directory */
            try {
                if (conMgr != null) {
                    conMgr.close();
                }
            } catch (NamingException n) {
                log.error(n);
            }

        }


        return response;
    }

    private List<String> getRole(List<ExtensibleObject> requestAttribute) {
         for (ExtensibleObject obj : requestAttribute) {
            List<ExtensibleAttribute> attrList = obj.getAttributes();
            for (ExtensibleAttribute att : attrList) {
                if (att.getName().equalsIgnoreCase("userRole")) {
                    return  att.getValueList();
                }
            }
         }
        return null;

    }

private String getOrg(List<ExtensibleObject> requestAttribute) {
         for (ExtensibleObject obj : requestAttribute) {
            List<ExtensibleAttribute> attrList = obj.getAttributes();
            for (ExtensibleAttribute att : attrList) {
                if (att.getName().equalsIgnoreCase("userOrg")) {
                    return  att.getValue();
                }
            }
         }
        return null;

    }



    /* (non-Javadoc)
      * @see org.openiam.spml2.interf.SpmlCore#delete(org.openiam.spml2.msg.DeleteRequestType)
      */
    public ResponseType delete(DeleteRequestType reqType) {
        log.debug("delete request called..");
        ConnectionMgr conMgr = null;

        //String uid = null;
        String ou = null;

        String requestID = reqType.getRequestID();

        /* PSO - Provisioning Service Object -
           *     -  ID must uniquely specify an object on the target or in the target's namespace
           *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
        PSOIdentifierType psoID = reqType.getPsoID();
        /* targetID -  */
        String targetID = psoID.getTargetID();
        /* ContainerID - May specify the container in which this object should be created
           *      ie. ou=Development, org=Example */
        PSOIdentifierType containerID = psoID.getContainerID();


        /* A) Use the targetID to look up the connection information under managed systems */
        ManagedSys managedSys = managedSysService.getManagedSys(targetID);
        try {

            log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
            conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
            LdapContext ldapctx = conMgr.connect(managedSys);

            log.debug("Ldapcontext = " + ldapctx);


            String ldapName = psoID.getID();

            log.debug("ldapname=" + ldapName);


            ldapctx.destroySubcontext(ldapName);
        } catch (NamingException ne) {

            log.error(ne);

            ResponseType respType = new ResponseType();
            respType.setStatus(StatusCodeType.FAILURE);
            respType.setError(ErrorCode.DIRECTORY_ERROR);
            respType.addErrorMessage(ne.toString());
            return respType;

        } finally {
            /* close the connection to the directory */
            try {
                if (conMgr != null) {
                    conMgr.close();
                }

            } catch (NamingException n) {
                log.error(n);
            }

        }

        ResponseType respType = new ResponseType();
        respType.setStatus(StatusCodeType.SUCCESS);
        return respType;

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

        log.debug("LOOKUP operation called.");
        boolean found = false;
        ConnectionMgr conMgr = null;

        LookupResponseType respType = new LookupResponseType();

        if (reqType == null) {
            respType.setStatus(StatusCodeType.FAILURE);
            respType.setError(ErrorCode.MALFORMED_REQUEST);
            return respType;
        }
        PSOIdentifierType psoId = reqType.getPsoID();
        String identity = psoId.getID();
        String rdn = null;
        String objectBaseDN = null;

        int indx = identity.indexOf(",");
        if (indx > 0) {
            rdn = identity.substring(0, identity.indexOf(","));
            objectBaseDN = identity.substring(indx+1);
        } else {
            rdn = identity;
        }

        log.debug("looking up identity: " + identity);

        ManagedSys managedSys = managedSysService.getManagedSys(psoId.getTargetID());
        try {

            conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
            LdapContext ldapctx = conMgr.connect(managedSys);
            ManagedSystemObjectMatch[] matchObj = managedSysService.managedSysObjectParam(psoId.getTargetID(), "USER");
            String resourceId = managedSys.getResourceId();

            log.debug("Resource id = " + resourceId);
            List<AttributeMap> attrMap = managedSysService.getResourceAttributeMaps(resourceId);

            if (attrMap != null) {
                List<String> attrList = getAttributeNameList(attrMap);
                String[] attrAry = new String[attrList.size()];
                attrList.toArray(attrAry);
                log.debug("Attribute array=" + attrAry);

                NamingEnumeration results = lookupSearch(matchObj[0], ldapctx, rdn, attrAry, objectBaseDN);

                log.debug("results=" + results);
                log.debug(" results has more elements=" + results.hasMoreElements());

                PSOType psoType = new PSOType();
                psoType.setPsoID(psoId);
                respType.setPso(psoType);

                ExtensibleObject extObj = new ExtensibleObject();
                extObj.setObjectId(identity);

                while (results != null && results.hasMoreElements()) {
                    SearchResult sr = (SearchResult) results.next();
                    Attributes attrs = sr.getAttributes();
                    if (attrs != null) {
                        found = true;
                        for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) {
                            ExtensibleAttribute extAttr = new ExtensibleAttribute();
                            Attribute attr = (Attribute) ae.next();
                            boolean addToList = false;

                            extAttr.setName(attr.getID());

                            NamingEnumeration e = attr.getAll();

                            while (e.hasMore()) {
                                Object o = e.next();
                                if (o instanceof String) {
                                    extAttr.setValue(o.toString());
                                    addToList = true;
                                }
                            }
                            if (addToList) {
                                extObj.getAttributes().add(extAttr);
                            }
                        }
                    }

                }
                respType.addObject(extObj);


            }

        } catch (NamingException ne) {
            log.error(ne.getMessage());
            respType.setStatus(StatusCodeType.FAILURE);
            respType.setError(ErrorCode.DIRECTORY_ERROR);
            respType.addErrorMessage(ne.toString());

            return respType;
        } catch (Exception e) {
            log.error(e.getMessage());
            respType.setStatus(StatusCodeType.FAILURE);

            respType.setError(ErrorCode.OTHER_ERROR);
            respType.addErrorMessage(e.toString());

            return respType;
        }


        log.debug("LOOKUP successful");

        if (!found) {
            respType.setStatus(StatusCodeType.FAILURE);
        } else {
            respType.setStatus(StatusCodeType.SUCCESS);
        }

        return respType;

    }

    private List<String> getAttributeNameList(List<AttributeMap> attrMap) {
        List<String> strList = new ArrayList<String>();

        if (attrMap == null || attrMap.size() == 0) {
            return null;
        }
        for (AttributeMap a : attrMap) {
            strList.add(a.getAttributeName());
        }

        return strList;
    }

    /* (non-Javadoc)
      * @see org.openiam.spml2.interf.SpmlCore#modify(org.openiam.spml2.msg.ModifyRequestType)
      */
    public ModifyResponseType modify(ModifyRequestType reqType) {
        /* FOR LDAP, need to be able to move object's OU - incase of re-org, person changes roles, etc */
        /* Need to be able add and remove users from groups */

        log.debug("LDAP Modify request called..");
        ConnectionMgr conMgr = null;
        LdapContext ldapctx = null;

        ModifyResponseType respType = new ModifyResponseType();
        respType.setStatus(StatusCodeType.SUCCESS);


        String requestID = reqType.getRequestID();
        /* PSO - Provisioning Service Object -
           *     -  ID must uniquely specify an object on the target or in the target's namespace
           *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
        PSOIdentifierType psoID = reqType.getPsoID();
        /* targetID -  */
        String targetID = psoID.getTargetID();
        /* ContainerID - May specify the container in which this object should be created
           *      ie. ou=Development, org=Example */
        PSOIdentifierType containerID = psoID.getContainerID();


        // modificationType contains a collection of objects for each type of operation
        List<ModificationType> modificationList = reqType.getModification();

         log.debug("ModificationList = " + modificationList);
         log.debug("Modificationlist size= " + modificationList.size());


        /* A) Use the targetID to look up the connection information under managed systems */
        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
        try {

            conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
            ldapctx = conMgr.connect(managedSys);
        }catch (NamingException ne) {
           respType.setStatus(StatusCodeType.FAILURE);
           respType.setError(ErrorCode.DIRECTORY_ERROR);
           respType.addErrorMessage(ne.toString());
           return respType;
        }

        log.debug("Ldapcontext = " + ldapctx);

        String ldapName = psoID.getID();

        ExtensibleAttribute origIdentity = isRename(modificationList);
	 	if (origIdentity != null) {
	 		log.debug("Renaming identity: " + origIdentity.getValue());

            try {
                ldapctx.rename(origIdentity.getValue(), ldapName);
                log.debug("Renaming : " + origIdentity.getValue());

            }catch(NamingException ne) {
               respType.setStatus(StatusCodeType.FAILURE);
               respType.setError(ErrorCode.DIRECTORY_ERROR);
               respType.addErrorMessage(ne.toString());
               return respType;
            }
	 	}


        // determine if this record already exists in ldap
        // move to separate method
        ManagedSystemObjectMatch[] matchObj = managedSysService.managedSysObjectParam(targetID, "USER");

        if (isInDirectory(ldapName, matchObj[0], ldapctx)) {

            log.debug("ldapName found in directory. Update the record..");

            try {
                List<ModificationType> modTypeList = reqType.getModification();
                for (ModificationType mod : modTypeList) {
                    ExtensibleType extType = mod.getData();
                    List<ExtensibleObject> extobjectList = extType.getAny();
                    for (ExtensibleObject obj : extobjectList) {

                        log.debug("Object:" + obj.getName() + " - operation=" + obj.getOperation());

                        List<ExtensibleAttribute> attrList = obj.getAttributes();
                        List<ModificationItem> modItemList = new ArrayList<ModificationItem>();
                        for (ExtensibleAttribute att : attrList) {
                            if (att.getOperation() != 0 && att.getName() != null) {
                                if ((att.getValue() == null || att.getValue().contains("null")) && (att.getValueList() == null || att.getValueList().size() == 0)) {
                                    //modItemList.add( new ModificationItem(3, new BasicAttribute(att.getName(), att.getValue())));
                                    log.debug("** set to null - name=" + att.getName() + "-" + att.getValue() + " - operation=" + att.getOperation());

                                    modItemList.add(new ModificationItem(att.getOperation(), new BasicAttribute(att.getName(), null)));
                                } else {
                                    if (!att.getName().equalsIgnoreCase("userPassword") &&
                                        !att.getName().equalsIgnoreCase("userRole") &&
                                        !att.getName().equalsIgnoreCase("userOrg") ) {
                                        log.debug("** name=" + att.getName() + "-" + att.getValue() + " - operation=" + att.getOperation());
                                        Attribute a = null;
                                        if (att.isMultivalued()) {
                                            List<String> valList = att.getValueList();
                                            if (valList != null && valList.size() > 0) {
                                                int ctr = 0;
                                                for (String s : valList) {
                                                    if (ctr == 0) {
                                                        a = new BasicAttribute(att.getName(), valList.get(ctr));
                                                    } else {
                                                        a.add(valList.get(ctr));
                                                    }
                                                    ctr++;
                                                }

                                            }

                                        } else {
                                            a = new BasicAttribute(att.getName(), att.getValue());

                                        }
                                        modItemList.add(new ModificationItem(att.getOperation(), a));
                                        //modItemList.add( new ModificationItem(att.getOperation(), new BasicAttribute(att.getName(), att.getValue())));
                                    }
                                }
                            }
                        }
                        ModificationItem[] mods = new ModificationItem[modItemList.size()];
                        modItemList.toArray(mods);

                        log.debug("ModifyAttribute array=" + mods);
                        log.debug("ldapName=" + ldapName);
                        ldapctx.modifyAttributes(ldapName, mods);
                    }
                }
            } catch (NamingException ne) {
                log.error(ne.getMessage(), ne);

                respType.setStatus(StatusCodeType.FAILURE);
                respType.setError(ErrorCode.DIRECTORY_ERROR);
                respType.addErrorMessage(ne.toString());
                return respType;


            } finally {
                /* close the connection to the directory */
                   try {
                    if (conMgr != null) {
                        conMgr.close();
                    }

                  } catch (NamingException n) {
                      log.error(n);
                  }

            }
        } else {
            // create the record in ldap
            log.debug("ldapName NOT FOUND in directory. Adding new record to directory..");

            List<ModificationType> modTypeList = reqType.getModification();
            for (ModificationType mod : modTypeList) {

                ExtensibleType extType = mod.getData();
                List<ExtensibleObject> extobjectList = extType.getAny();
                BasicAttributes basicAttr = getBasicAttributes(extobjectList, matchObj[0].getKeyField());

                try {
                    Context result = ldapctx.createSubcontext(ldapName, basicAttr);
                } catch (NamingException ne) {
                    respType.setStatus(StatusCodeType.FAILURE);
                    respType.setError(ErrorCode.DIRECTORY_ERROR);
                    respType.addErrorMessage(ne.toString());
                    return respType;

                } finally {
                    /* close the connection to the directory */
                    try {
                        if (conMgr != null) {
                            conMgr.close();
                        }

                      } catch (NamingException n) {
                          log.error(n);
                      }

                }
            }


        }

        return respType;

    }

    private ExtensibleAttribute isRename(List<ModificationType> modTypeList ) {
	 	for (ModificationType mod: modTypeList) {
	 		ExtensibleType extType =  mod.getData();
	 		List<ExtensibleObject> extobjectList = extType.getAny();
	 		for (ExtensibleObject obj: extobjectList) {

	 			log.debug("ReName Object:" + obj.getName() + " - operation=" + obj.getOperation());

	 			List<ExtensibleAttribute> attrList =  obj.getAttributes();
	 			List<ModificationItem> modItemList = new ArrayList<ModificationItem>();
	 			for (ExtensibleAttribute att: attrList) {
	 				if (att.getOperation() != 0 && att.getName() != null) {
	 					if (att.getName().equalsIgnoreCase("ORIG_IDENTITY")) {
	 						return att;
	 					}
	 				}
	 			}
	 		}
	 	}
	 	return null;
	}

    private boolean identityExists(String ldapName, LdapContext ctx)  {

        try {
            LdapContext lCtx = (LdapContext)ctx.lookup(ldapName);
        }catch(NamingException ne) {
           return false;
        }
        return true;

    }

    private boolean isInDirectory(String ldapName, ManagedSystemObjectMatch matchObj,
                                  LdapContext ldapctx) {
        int indx = ldapName.indexOf(",");
        String rdn = null;
        String objectBaseDN = null;
        if (indx > 0) {
            rdn = ldapName.substring(0, ldapName.indexOf(","));
            objectBaseDN = ldapName.substring(indx+1);
        } else {
            rdn = ldapName;
        }
        log.debug("Lookup rdn = " + rdn);
        log.debug("Search in: " + objectBaseDN);

        String[] attrAry = {"uid", "cn", "fn"};
        NamingEnumeration results = null;
        try {
            //results = search(matchObj, ldapctx, rdn, attrAry);
            results = lookupSearch(matchObj,ldapctx,rdn,attrAry,objectBaseDN);
            if (results != null && results.hasMoreElements()) {
                return true;
            }
            return false;
        } catch (NamingException ne) {
            log.error(ne);
            return false;
        }
    }

    private NamingEnumeration lookupSearch(ManagedSystemObjectMatch matchObj,
                                     LdapContext ctx,
                                     String searchValue, String[] attrAry, String objectBaseDN) throws NamingException {

        String attrIds[] = {"1.1", "+", "*", "accountUnlockTime", "aci", "aclRights", "aclRightsInfo", "altServer", "attributeTypes", "changeHasReplFixupOp", "changeIsReplFixupOp", "copiedFrom", "copyingFrom", "createTimestamp", "creatorsName", "deletedEntryAttrs", "dITContentRules", "dITStructureRules", "dncomp", "ds-pluginDigest", "ds-pluginSignature", "ds6ruv", "dsKeyedPassword", "entrydn", "entryid", "hasSubordinates", "idmpasswd", "isMemberOf", "ldapSchemas", "ldapSyntaxes", "matchingRules", "matchingRuleUse", "modDNEnabledSuffixes", "modifiersName", "modifyTimestamp", "nameForms", "namingContexts", "nsAccountLock", "nsBackendSuffix", "nscpEntryDN", "nsds5ReplConflict", "nsIdleTimeout", "nsLookThroughLimit", "nsRole", "nsRoleDN", "nsSchemaCSN", "nsSizeLimit", "nsTimeLimit", "nsUniqueId", "numSubordinates", "objectClasses", "parentid", "passwordAllowChangeTime", "passwordExpirationTime", "passwordExpWarned", "passwordHistory", "passwordPolicySubentry", "passwordRetryCount", "pwdAccountLockedTime", "pwdChangedTime", "pwdFailureTime", "pwdGraceUseTime", "pwdHistory", "pwdLastAuthTime", "pwdPolicySubentry", "pwdReset", "replicaIdentifier", "replicationCSN", "retryCountResetTime", "subschemaSubentry", "supportedControl", "supportedExtension", "supportedLDAPVersion", "supportedSASLMechanisms", "supportedSSLCiphers", "targetUniqueId", "vendorName", "vendorVersion"};

        SearchControls searchCtls = new SearchControls();
        // searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(attrIds);



        String searchFilter = matchObj.getSearchFilter();
        // replace the place holder in the search filter
        searchFilter = searchFilter.replace("?", searchValue);

        if (objectBaseDN == null) {
            objectBaseDN = matchObj.getSearchBaseDn();
        }


       log.debug("Search Filter=" + searchFilter);
       log.debug("Searching BaseDN=" + objectBaseDN);

        return ctx.search(objectBaseDN, searchFilter, searchCtls);


    }



    private NamingEnumeration search(ManagedSystemObjectMatch matchObj,
                                     LdapContext ctx,
                                     String searchValue, String[] attrAry) throws NamingException {

        String attrIds[] = {"1.1", "+", "*", "accountUnlockTime", "aci", "aclRights", "aclRightsInfo", "altServer", "attributeTypes", "changeHasReplFixupOp", "changeIsReplFixupOp", "copiedFrom", "copyingFrom", "createTimestamp", "creatorsName", "deletedEntryAttrs", "dITContentRules", "dITStructureRules", "dncomp", "ds-pluginDigest", "ds-pluginSignature", "ds6ruv", "dsKeyedPassword", "entrydn", "entryid", "hasSubordinates", "idmpasswd", "isMemberOf", "ldapSchemas", "ldapSyntaxes", "matchingRules", "matchingRuleUse", "modDNEnabledSuffixes", "modifiersName", "modifyTimestamp", "nameForms", "namingContexts", "nsAccountLock", "nsBackendSuffix", "nscpEntryDN", "nsds5ReplConflict", "nsIdleTimeout", "nsLookThroughLimit", "nsRole", "nsRoleDN", "nsSchemaCSN", "nsSizeLimit", "nsTimeLimit", "nsUniqueId", "numSubordinates", "objectClasses", "parentid", "passwordAllowChangeTime", "passwordExpirationTime", "passwordExpWarned", "passwordHistory", "passwordPolicySubentry", "passwordRetryCount", "pwdAccountLockedTime", "pwdChangedTime", "pwdFailureTime", "pwdGraceUseTime", "pwdHistory", "pwdLastAuthTime", "pwdPolicySubentry", "pwdReset", "replicaIdentifier", "replicationCSN", "retryCountResetTime", "subschemaSubentry", "supportedControl", "supportedExtension", "supportedLDAPVersion", "supportedSASLMechanisms", "supportedSSLCiphers", "targetUniqueId", "vendorName", "vendorVersion"};

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(attrIds);


        //SearchControls searchCtls = new SearchControls();
        //searchCtls.setReturningAttributes(attrAry);
        //searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        String searchFilter = matchObj.getSearchFilter();
        // replace the place holder in the search filter
        searchFilter = searchFilter.replace("?", searchValue);


       log.debug("Search Filter=" + searchFilter);
       log.debug("Searching BaseDN=" + matchObj.getSearchBaseDn());

        return ctx.search(matchObj.getSearchBaseDn(), searchFilter, searchCtls);


    }

    private String getOU(List<ExtensibleObject> requestAttribute) {
        for (ExtensibleObject obj : requestAttribute) {
            List<ExtensibleAttribute> attrList = obj.getAttributes();
            for (ExtensibleAttribute att : attrList) {
                if (att.getName().equalsIgnoreCase("ou")) {
                    return att.getValue();
                }
            }
        }
        return null;
    }


    private BasicAttributes getBasicAttributes(List<ExtensibleObject> requestAttribute, String idField) {
        BasicAttributes attrs = new BasicAttributes();

        // add the object class
        Attribute oc = new BasicAttribute("objectclass");
        oc.add("top");

        // add the ou for this record
        Attribute ouSet = new BasicAttribute("ou");
        String ou = getOU(requestAttribute);
        log.debug("GetAttributes() - ou=" + ou);
        if (ou != null && ou.length() > 0) {
            ouSet.add(ou);
        }

        // add the structural classes
        attrs.put(oc);
        attrs.put(ouSet);

        // add the identifier

        // add the attributes
        for (ExtensibleObject obj : requestAttribute) {
            List<ExtensibleAttribute> attrList = obj.getAttributes();
            for (ExtensibleAttribute att : attrList) {

                log.debug("Attr Name=" + att.getName() + " " + att.getValue());

                if ( (att.getName() != idField) &&
                     (!att.getName().equalsIgnoreCase("userRole")) &&
                     (!att.getName().equalsIgnoreCase("userOrg") )   )  {
                    Attribute a = null;
                    if (att.isMultivalued()) {
                        List<String> valList = att.getValueList();
                        if (valList != null && valList.size() > 0) {
                            int ctr = 0;
                            for (String s : valList) {
                                if (ctr == 0) {
                                    a = new BasicAttribute(att.getName(), valList.get(ctr));
                                } else {
                                    a.add(valList.get(ctr));
                                }
                                ctr++;
                            }

                        }

                    } else {
                        a = new BasicAttribute(att.getName(), att.getValue());

                    }
                    attrs.put(a);


                    //new BasicAttribute();

                    //attrs.put(att.getName(), att.getValue());
                }
            }
        }

        return attrs;
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
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
      * @see org.openiam.spml2.interf.SpmlPassword#setPassword(org.openiam.spml2.msg.password.SetPasswordRequestType)
      */
    public ResponseType setPassword(SetPasswordRequestType reqType) {
        log.debug("setPassword request called..");

         ConnectionMgr conMgr  = null;

        String requestID = reqType.getRequestID();
        /* PSO - Provisioning Service Object -
           *     -  ID must uniquely specify an object on the target or in the target's namespace
           *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
        PSOIdentifierType psoID = reqType.getPsoID();
        /* targetID -  */
        String targetID = psoID.getTargetID();
        /* ContainerID - May specify the container in which this object should be created
           *      ie. ou=Development, org=Example */
        PSOIdentifierType containerID = psoID.getContainerID();


        /* A) Use the targetID to look up the connection information under managed systems */
        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        try {
            log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
            conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
            LdapContext ldapctx = conMgr.connect(managedSys);

            log.debug("Ldapcontext = " + ldapctx);


            String ldapName = psoID.getID();

            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", reqType.getPassword()));
            ldapctx.modifyAttributes(ldapName, mods);

            // check if the request contains additional attributes
            List<ExtensibleObject> extObjList =  reqType.getAny();
            if (extObjList != null && extObjList.size() > 0) {
               ExtensibleObject obj =  extObjList.get(0);
               if (obj != null) {
                   List<ExtensibleAttribute> attrList =  obj.getAttributes();
                   if (attrList != null && attrList.size() > 0) {
                        mods = new ModificationItem[attrList.size()];
                        for (ExtensibleAttribute a  : attrList) {
                            mods[0] = new ModificationItem( a.getOperation() , new BasicAttribute(a.getName(), a.getValue()));
                        }
                        ldapctx.modifyAttributes(ldapName, mods);
                   }
               }
            }



        } catch (NamingException ne) {
            log.error(ne.getMessage(), ne);

            ResponseType resp = new ResponseType();
            resp.setStatus(StatusCodeType.FAILURE);
            resp.setError(ErrorCode.NO_SUCH_IDENTIFIER);
            return resp;
        } catch (Exception ne) {
            log.error(ne.getMessage(), ne);

            ResponseType resp = new ResponseType();
            resp.setStatus(StatusCodeType.FAILURE);
            resp.setError(ErrorCode.OTHER_ERROR);
            resp.addErrorMessage(ne.toString());
            return resp;

        } finally {
            /* close the connection to the directory */
            try {
                if (conMgr != null) {
                    conMgr.close();
                }

              } catch (NamingException n) {
                  log.error(n);
              }

        }

        ResponseType respType = new ResponseType();
        respType.setStatus(StatusCodeType.SUCCESS);
        return respType;

    }

    /* (non-Javadoc)
      * @see org.openiam.spml2.interf.SpmlPassword#validatePassword(org.openiam.spml2.msg.password.ValidatePasswordRequestType)
      */
    public ValidatePasswordResponseType validatePassword(
            ValidatePasswordRequestType request) {
        // TODO Auto-generated method stub
        return null;
    }


    public ResponseType suspend(SuspendRequestType request) {
        return ldapSuspend.suspend(request);
    }

    public ResponseType resume(ResumeRequestType request) {
        return ldapSuspend.resume(request);
    }


    public ManagedSystemObjectMatchDAO getManagedSysObjectMatchDao() {
        return managedSysObjectMatchDao;
    }

    public void setManagedSysObjectMatchDao(
            ManagedSystemObjectMatchDAO managedSysObjectMatchDao) {
        this.managedSysObjectMatchDao = managedSysObjectMatchDao;
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }


    /**
     * Logs a message into the audit log.
     *
     * @param objectTypeId
     * @param actionId
     * @param actionStatus
     * @param reason
     * @param domainId
     * @param userId
     * @param principal
     * @param linkedLogId
     * @param clientId
     */
    public void log(String objectTypeId, String actionId, String actionStatus, String reason,
                    String domainId, String userId, String principal,
                    String linkedLogId, String clientId) {
        IdmAuditLog log = new IdmAuditLog(objectTypeId, actionId, actionStatus,
                reason, domainId, userId, principal,
                linkedLogId, clientId);
    }


    public IdmAuditLogDataService getAuditDataService() {
        return auditDataService;
    }


    public void setAuditDataService(IdmAuditLogDataService auditDataService) {
        this.auditDataService = auditDataService;
    }


    public LoginDataService getLoginManager() {
        return loginManager;
    }


    public void setLoginManager(LoginDataService loginManager) {
        this.loginManager = loginManager;
    }

    public PolicyDAO getPolicyDao() {
        return policyDao;
    }

    public void setPolicyDao(PolicyDAO policyDao) {
        this.policyDao = policyDao;
    }

    public SecurityDomainDataService getSecDomainService() {
        return secDomainService;
    }

    public void setSecDomainService(SecurityDomainDataService secDomainService) {
        this.secDomainService = secDomainService;
    }

    public UserDataService getUserManager() {
        return userManager;
    }

    public void setUserManager(UserDataService userManager) {
        this.userManager = userManager;
    }

    public LdapSuspend getLdapSuspend() {
        return ldapSuspend;
    }

    public void setLdapSuspend(LdapSuspend ldapSuspend) {
        this.ldapSuspend = ldapSuspend;
    }

    public LdapPassword getLdapPassword() {
        return ldapPassword;
    }

    public void setLdapPassword(LdapPassword ldapPassword) {
        this.ldapPassword = ldapPassword;
    }


}
