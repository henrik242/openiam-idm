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
package org.openiam.idm.srvc.synch.srcadapter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.service.MatchObjectRule;
import org.openiam.idm.srvc.synch.service.SourceAdapter;
import org.openiam.idm.srvc.synch.service.TransformScript;
import org.openiam.idm.srvc.synch.service.ValidationScript;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.provision.service.ProvisionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Scan Ldap for any new records, changed users, or delete operations and then synchronizes them back into OpenIAM.
 *
 * @author suneet
 */
public class LdapAdapter implements SourceAdapter {

    protected LineObject rowHeader = new LineObject();
    protected ProvisionUser pUser = new ProvisionUser();
    private String keystore;

    public static ApplicationContext ac;

    protected LoginDataService loginManager;
    protected RoleDataService roleDataService;
    protected AuditHelper auditHelper;
    protected MatchRuleFactory matchRuleFactory;

    static protected ResourceBundle secres = ResourceBundle.getBundle("securityconf");

    LdapContext ctx = null;

    protected UserDataService userMgr;
    ProvisionService provService = null;
    String systemAccount;
    private static final Log log = LogFactory.getLog(LdapAdapter.class);


    public SyncResponse startSynch(SynchConfig config) {
        // rule used to match object from source system to data in IDM
        MatchObjectRule matchRule = null;
       // String changeLog = null;
       // Date mostRecentRecord = null;
        long mostRecentRecord = 0L;
        String lastRecProcessed = null;
        //java.util.Date lastExec = null;
        IdmAuditLog synchUserStartLog = null;
        provService = (ProvisionService) ac.getBean("defaultProvision");

        log.debug("LDAP startSynch CALLED.^^^^^^^^");

        String requestId = UUIDGen.getUUID();

        IdmAuditLog synchStartLog = new IdmAuditLog();
        synchStartLog.setSynchAttributes("SYNCH_USER", config.getSynchConfigId(), "START", "SYSTEM", requestId);
        synchStartLog = auditHelper.logEvent(synchStartLog);

        try {

            if (!connect(config)) {
                SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
                resp.setErrorCode(ResponseCode.FAIL_CONNECTION);
                return resp;
            }

            try {
                matchRule = matchRuleFactory.create(config);
            } catch (ClassNotFoundException cnfe) {
                log.error(cnfe);

                synchStartLog.updateSynchAttributes("FAIL",ResponseCode.CLASS_NOT_FOUND.toString() , cnfe.toString());
                auditHelper.logEvent(synchStartLog);

                SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
                resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
                return resp;
            }
            // get the last execution time
            if (config.getLastRecProcessed() != null) {
			    lastRecProcessed =  config.getLastRecProcessed() ;
		    }

            // get change log field
            if (config.getSynchType().equalsIgnoreCase("INCREMENTAL")) {
                if (lastRecProcessed != null) {
                    // update the search filter so that it has the new time
                    String ldapFilterQuery =  config.getQuery();
                    // replace wildcards with the last exec time

                    config.setQuery(  ldapFilterQuery.replace("?", lastRecProcessed ) );

                    log.debug("Updated ldap filter = " + config.getQuery());
                }
            }


            int ctr = 0;

            NamingEnumeration results = search(config);
            while (results != null && results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = sr.getAttributes();

                pUser = new ProvisionUser();
                LineObject rowObj = new LineObject();

                log.debug("-New Row to Synchronize --" + ctr++);

                if (attrs != null) {
                   // try {
                        for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) {

                            javax.naming.directory.Attribute attr = (javax.naming.directory.Attribute) ae.next();

                            List<String> valueList = new ArrayList<String>();

                            String key = attr.getID();

                            log.debug("attribute id=: " + key);


                            for (NamingEnumeration e = attr.getAll(); e.hasMore();) {
                                Object o = e.next();
                                if (o.toString() != null) {
                                    valueList.add(o.toString());
                                    log.debug("- value:=" + o.toString());
                                }
                            }
                            if (valueList.size() > 0) {
                                org.openiam.idm.srvc.synch.dto.Attribute rowAttr = new org.openiam.idm.srvc.synch.dto.Attribute();
                                rowAttr.populateAttribute(key, valueList);
                                rowObj.put(key, rowAttr);
                            }else {
                               log.debug("- value is null");
                            }
                        }


                }
                // set the lastRecProcessed time
          /*      Attribute atr = rowObj.get("modifyTimestamp");

                if (atr != null && atr.getValue() != null) {
                    lastRecProcessed = atr.getValue();
                }else {
                    // look at the createTime
                    atr = rowObj.get("createTimestamp");
                    if (atr != null && atr.getValue() != null) {
                        lastRecProcessed = atr.getValue();
                    }

                }
            */

                System.out.println("Modify Timestamp =" + rowObj.get("modifyTimestamp") );
                System.out.println("Create Timestamp =" + rowObj.get("createTimestamp") );

                LastRecordTime lrt = getRowTime(rowObj);

                if (mostRecentRecord < lrt.mostRecentRecord) {
                    mostRecentRecord = lrt.mostRecentRecord;
                    lastRecProcessed = lrt.generalizedTime;
                }

                log.debug("STarting validation and transformation..");


                // start the synch process
                // 1) Validate the data
                // 2) Transform it
                // 3) if not delete - then match the object and determine if its a new object or its an udpate
                try {
                    // validate
                    if (config.getValidationRule() != null && config.getValidationRule().length() > 0) {
                        ValidationScript script = SynchScriptFactory.createValidationScript(config.getValidationRule());
                        int retval = script.isValid(rowObj);
                        if (retval == ValidationScript.NOT_VALID) {
                            log.error("Row Object Faied Validation=" + rowObj.toString());
                            // log this object in the exception log

                            continue;
                        }
                        if (retval == ValidationScript.SKIP) {
                            continue;
                        }
                    }

                    // check if the user exists or not
                    Map<String, Attribute> rowAttr = rowObj.getColumnMap();
                    //
                    matchRule = matchRuleFactory.create(config);
                    User usr = matchRule.lookup(config, rowAttr);


                    // transform
                    if (config.getTransformationRule() != null && config.getTransformationRule().length() > 0) {
                        TransformScript transformScript = SynchScriptFactory.createTransformationScript(config.getTransformationRule());

                        // initialize the transform script
                        if (usr != null) {
                            transformScript.setNewUser(false);
                            transformScript.setUser(userMgr.getUserWithDependent(usr.getUserId(), true));
                            transformScript.setPrincipalList(loginManager.getLoginByUser(usr.getUserId()));
                            transformScript.setUserRoleList(roleDataService.getUserRolesAsFlatList(usr.getUserId()));

                        } else {
                            transformScript.setNewUser(true);
                        }

                        int retval = transformScript.execute(rowObj, pUser);

                        log.debug("Transform result=" + retval);

                        pUser.setSessionId(synchStartLog.getSessionId());


                        if (retval == TransformScript.DELETE && usr != null) {
                            log.debug("deleting record - " + usr.getUserId());
                            ProvisionUserResponse userResp = provService.deleteByUserId(new ProvisionUser(usr), UserStatusEnum.DELETED, systemAccount);


                        } else {
                            // call synch
                            if (retval != TransformScript.DELETE) {
                                System.out.println("Provisioning user=" + pUser.getLastName());
                                if (usr != null) {
                                    log.debug("updating existing user...systemId=" + pUser.getUserId());
                                    pUser.setUserId(usr.getUserId());
                                    ProvisionUserResponse userResp = provService.modifyUser(pUser);

                                } else {
                                    log.debug("adding new user...");
                                    pUser.setUserId(null);
                                    ProvisionUserResponse userResp = provService.addUser(pUser);


                                }
                            }
                        }
                    }
                    // show the user object


                } catch (ClassNotFoundException cnfe) {
                    log.error(cnfe);

                    synchStartLog.updateSynchAttributes("FAIL",ResponseCode.CLASS_NOT_FOUND.toString() , cnfe.toString());
                    auditHelper.logEvent(synchStartLog);


                    SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
                    resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
                    resp.setErrorText(cnfe.toString());
                    return resp;
                }  catch (IOException fe ) {

                    log.error(fe);

                    synchStartLog.updateSynchAttributes("FAIL",ResponseCode.FILE_EXCEPTION.toString() , fe.toString());
                    auditHelper.logEvent(synchStartLog);


                    SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
                    resp.setErrorCode(ResponseCode.FILE_EXCEPTION);
                    resp.setErrorText(fe.toString());
                    return resp;


                }
            }


        } catch (NamingException ne) {

            log.error(ne);

            synchStartLog.updateSynchAttributes("FAIL",ResponseCode.DIRECTORY_NAMING_EXCEPTION.toString() , ne.toString());
            auditHelper.logEvent(synchStartLog);

            SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
            resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
            resp.setErrorText(ne.toString());
            return resp;

        }

        log.debug("LDAP SYNCHRONIZATION COMPLETE^^^^^^^^");

        SyncResponse resp = new SyncResponse(ResponseStatus.SUCCESS);
        //resp.setLastRecordTime(mostRecentRecord);
        resp.setLastRecProcessed(lastRecProcessed);
        return resp;

    }

    private LastRecordTime getRowTime(LineObject rowObj)  {
        Attribute atr = rowObj.get("modifyTimestamp");

       if (atr != null && atr.getValue() != null) {
             return getTime(atr);
       }
       atr = rowObj.get("createTimestamp");

       if (atr != null && atr.getValue() != null) {
             return getTime(atr);
       }
       return new LastRecordTime();


    }
    private LastRecordTime getTime(Attribute atr) {
        LastRecordTime lrt = new LastRecordTime();

        String s = atr.getValue();
        int i = s.indexOf("Z");
        if (i == -1) {
            i = s.indexOf("-");
        }
        if (i > 0) {
            lrt.mostRecentRecord =  Long.parseLong( s.substring(0,i) );
            lrt.generalizedTime = atr.getValue();
            return lrt;

        }
        lrt.mostRecentRecord =  Long.parseLong( s );
        lrt.generalizedTime = atr.getValue();
        return lrt;


    }

    private NamingEnumeration search(SynchConfig config) throws NamingException {

       // String attrIds[] = {"1.1", "+", "*"};

        String attrIds[] = {"1.1", "+", "*", "accountUnlockTime", "aci", "aclRights", "aclRightsInfo", "altServer", "attributeTypes", "changeHasReplFixupOp", "changeIsReplFixupOp", "copiedFrom", "copyingFrom", "createTimestamp", "creatorsName", "deletedEntryAttrs", "dITContentRules", "dITStructureRules", "dncomp", "ds-pluginDigest", "ds-pluginSignature", "ds6ruv", "dsKeyedPassword", "entrydn", "entryid", "hasSubordinates", "idmpasswd", "isMemberOf", "ldapSchemas", "ldapSyntaxes", "matchingRules", "matchingRuleUse", "modDNEnabledSuffixes", "modifiersName", "modifyTimestamp", "nameForms", "namingContexts", "nsAccountLock", "nsBackendSuffix", "nscpEntryDN", "nsds5ReplConflict", "nsIdleTimeout", "nsLookThroughLimit", "nsRole", "nsRoleDN", "nsSchemaCSN", "nsSizeLimit", "nsTimeLimit", "nsUniqueId", "numSubordinates", "objectClasses", "parentid", "passwordAllowChangeTime", "passwordExpirationTime", "passwordExpWarned", "passwordHistory", "passwordPolicySubentry", "passwordRetryCount", "pwdAccountLockedTime", "pwdChangedTime", "pwdFailureTime", "pwdGraceUseTime", "pwdHistory", "pwdLastAuthTime", "pwdPolicySubentry", "pwdReset", "replicaIdentifier", "replicationCSN", "retryCountResetTime", "subschemaSubentry", "supportedControl", "supportedExtension", "supportedLDAPVersion", "supportedSASLMechanisms", "supportedSSLCiphers", "targetUniqueId", "vendorName", "vendorVersion"};

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(attrIds);

        String searchFilter = config.getQuery();

        return ctx.search(config.getBaseDn(), searchFilter, searchCtls);


    }



    private boolean connect(SynchConfig config) throws NamingException {

        Hashtable<String, String> envDC = new Hashtable();
        keystore = secres.getString("KEYSTORE");
        System.setProperty("javax.net.ssl.trustStore", keystore);

        String hostUrl = config.getSrcHost(); //   managedSys.getHostUrl();
        log.debug("Directory host url:" + hostUrl);


        envDC.put(Context.PROVIDER_URL, hostUrl);
        envDC.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        envDC.put(Context.SECURITY_AUTHENTICATION, "simple"); // simple
        envDC.put(Context.SECURITY_PRINCIPAL, config.getSrcLoginId());  //"administrator@diamelle.local"
        envDC.put(Context.SECURITY_CREDENTIALS, config.getSrcPassword());

        if (hostUrl.contains("ldaps")) {

            envDC.put(Context.SECURITY_PROTOCOL, "SSL");
        }


        ctx = new InitialLdapContext(envDC, null);
        if (ctx != null) {
            return true;
        }

        return false;


    }

    private void closeConnection() {
        try {
            if (ctx != null) {
                ctx.close();
            }

        } catch (NamingException ne) {
            log.error(ne.getMessage(), ne);
            ne.printStackTrace();
        }

    }


    public MatchRuleFactory getMatchRuleFactory() {
        return matchRuleFactory;
    }


    public void setMatchRuleFactory(MatchRuleFactory matchRuleFactory) {
        this.matchRuleFactory = matchRuleFactory;
    }


    public String getSystemAccount() {
        return systemAccount;
    }


    public void setSystemAccount(String systemAccount) {
        this.systemAccount = systemAccount;
    }


    public LoginDataService getLoginManager() {
        return loginManager;
    }


    public void setLoginManager(LoginDataService loginManager) {
        this.loginManager = loginManager;
    }


    public RoleDataService getRoleDataService() {
        return roleDataService;
    }


    public void setRoleDataService(RoleDataService roleDataService) {
        this.roleDataService = roleDataService;
    }


    public UserDataService getUserMgr() {
        return userMgr;
    }


    public void setUserMgr(UserDataService userMgr) {
        this.userMgr = userMgr;
    }


    public AuditHelper getAuditHelper() {
        return auditHelper;
    }


    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public void setMuleContext(MuleContext ctx) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    private class LastRecordTime {
        long mostRecentRecord;
        String generalizedTime;

    }



}
