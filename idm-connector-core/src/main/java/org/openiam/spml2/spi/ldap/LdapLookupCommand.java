package org.openiam.spml2.spi.ldap;

import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.msg.*;
import org.openiam.spml2.spi.jdbc.AppTableAbstractCommand;
import org.openiam.spml2.util.connect.ConnectionFactory;
import org.openiam.spml2.util.connect.ConnectionManagerConstant;
import org.openiam.spml2.util.connect.ConnectionMgr;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import java.sql.*;
import java.text.ParseException;
import java.util.List;

/**
 * Implements lookup furnctionality for the ldapconnector.
 * User: suneetshah
 */
public class LdapLookupCommand extends LdapAbstractCommand {

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






}


