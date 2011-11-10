package org.openiam.spml2.spi.ldap;

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

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

//import org.openiam.spml2.msg.ExtensibleAttribute;

/**
 * Implements modify capability for the LdapConnector
 * User: suneetshah
 * Date: 7/30/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class LdapModifyCommand extends LdapAbstractCommand {

     public ModifyResponseType modify(ModifyRequestType reqType) {
  /* FOR LDAP, need to be able to move object's OU - incase of re-org, person changes roles, etc */
        /* Need to be able add and remove users from groups */

        log.debug("LDAP Modify request called..");
        ConnectionMgr conMgr = null;
        LdapContext ldapctx = null;

         List<String> targetMembershipList = new ArrayList<String>();

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


                        List<ExtensibleAttribute> attrList = obj.getAttributes();
                        List<ModificationItem> modItemList = new ArrayList<ModificationItem>();
                        for (ExtensibleAttribute att : attrList) {

                            log.debug("Extensible Attribute: " + att.getName() + " " + att.getDataType() );

                            if ( att.getDataType().equalsIgnoreCase("memberOf")) {
                                buildMembershipList(att,targetMembershipList);
                            }

                            if (att.getOperation() != 0 && att.getName() != null && !att.getDataType().equalsIgnoreCase("memberOf")) {

                                // set an attribute to null
                                if ((att.getValue() == null || att.getValue().contains("null")) && (att.getValueList() == null || att.getValueList().size() == 0)) {
                                    //modItemList.add( new ModificationItem(3, new BasicAttribute(att.getName(), att.getValue())));
                                    log.debug("** set to null - name=" + att.getName() + "-" + att.getValue() + " - operation=" + att.getOperation());

                                    modItemList.add(new ModificationItem(att.getOperation(), new BasicAttribute(att.getName(), null)));
                                } else {
                                   // valid value

                                  //  if (!att.getDataType().equalsIgnoreCase("memberOf")) {
                                    if (!att.getName().equalsIgnoreCase("userPassword")) {
                                   /*     !att.getName().equalsIgnoreCase("userRole") &&
                                        !att.getName().equalsIgnoreCase("userOrg") ) {
                                   */
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

                log.debug("Modify: Extensible Object List =" + extobjectList );

                BasicAttributes basicAttr = getBasicAttributes(extobjectList, matchObj[0].getKeyField(),targetMembershipList);

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

        modifyAccountMembership(targetMembershipList , ldapName, matchObj[0], ldapctx);

        return respType;
    }

    private void modifyAccountMembership(List<String>targetMembershipList, String ldapName, ManagedSystemObjectMatch matchObj,  LdapContext ldapctx ) {

        // get the accounts current membership list
        List<String> currentMembershipList =  getAccountMembership(ldapName, matchObj,   ldapctx);

        log.debug("Current ldap role membership:" + currentMembershipList);
        log.debug("Target ldap role membership:"  + targetMembershipList);

        if (targetMembershipList == null && currentMembershipList != null) {
            // remove all associations
            for (String s : currentMembershipList) {
                    try {
                       ModificationItem mods[] = new ModificationItem[1];
                       mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute("uniqueMember", ldapName));
                       ldapctx.modifyAttributes(s, mods);
                    }catch (NamingException ne ) {
                      log.error(ne);
                    }

            }
        }


        // see if we need to add additional membership entries

        if (targetMembershipList != null) {
            for (String s : targetMembershipList) {
                boolean found = false;
                if (currentMembershipList != null) {
                    for (String cur : currentMembershipList) {
                        if (s.equalsIgnoreCase(cur))  {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found ) {
                    try {
                       ModificationItem mods[] = new ModificationItem[1];
                       mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("uniqueMember", ldapName));
                       ldapctx.modifyAttributes(s, mods);
                    }catch (NamingException ne ) {
                      log.error(ne);
                    }
                }
            }
        }

        // remove membership
        if (currentMembershipList != null) {
            for (String s : currentMembershipList) {
                boolean found = false;
                if (targetMembershipList != null) {
                    for (String cur : targetMembershipList) {
                        if (s.equalsIgnoreCase(cur))  {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found ) {
                    try {
                       ModificationItem mods[] = new ModificationItem[1];
                       mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute("uniqueMember", ldapName));
                       ldapctx.modifyAttributes(s, mods);
                    }catch (NamingException ne ) {
                      log.error(ne);
                    }
                }
            }
        }


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




}


