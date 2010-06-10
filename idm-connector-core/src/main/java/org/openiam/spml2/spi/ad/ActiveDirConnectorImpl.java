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
package org.openiam.spml2.spi.ad;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.ConfigurationException;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ManagedSysDAO;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.spml2.base.AbstractSpml2Complete;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ModificationAttribute;
import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.ListTargetsRequestType;
import org.openiam.spml2.msg.ListTargetsResponseType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.ModificationModeType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.ReturnDataType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;
import org.openiam.spml2.util.connect.ConnectionFactory;
import org.openiam.spml2.util.connect.ConnectionManagerConstant;
import org.openiam.spml2.util.connect.ConnectionMgr;

/**
 * Updates the OpenIAM repository with data received from external client.
 * @author suneet
 *
 */

@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "ActiveDirectoryConServicePort", 
		serviceName="ActiveDirectoryConnectorService")
public class ActiveDirConnectorImpl extends AbstractSpml2Complete implements ConnectorService  {

	private static final Log log = LogFactory.getLog(ActiveDirConnectorImpl.class);
	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected ResourceDataService resourceDataService;
	
	
	public boolean testConnection(String targetID) {
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);
		
		log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);	
	 	if (ldapctx == null)
	 		 return false;
	 	return true;
	}
	
	/*public NamingEnumeration search(LdapContext ctx , String searchValue, String baseDN) {
		SearchControls searchCtls = new SearchControls();
		
		//Specify the attributes to returned
		String returnedAtts[]={"cn","sn","givenName","mail", "distinguishedName"};
		searchCtls.setReturningAttributes(returnedAtts);
		
		//Specify the search scope
		try {
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(&(objectClass=user)(" + filterAttr + "=" + filterValue + "))";
			
			System.out.println("Search Filter=" + searchFilter);
			System.out.println("BaseDN=" + baseDN);
			
			return ctx.search(baseDN, searchFilter, searchCtls);
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		return null;
		
	}
	*/
	
	
	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#add(org.openiam.spml2.msg.AddRequestType)
	 */
	public AddResponseType add(AddRequestType reqType) {
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
		Map<QName,String> otherData  = reqType.getOtherAttributes();
		
		/* Indicates what type of data we should return from this operations */
		ReturnDataType returnData =  reqType.getReturnData();
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID);
		
		log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.debug("Ldapcontext = " + ldapctx);
	 	
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
	 	
	 	log.debug("matchObj = " + matchObj);
	 	
	 	if (matchObj == null) {
	 		throw new ConfigurationException("AD configuration is missing configuration information");
	 	}
	 	
	 	log.debug("baseDN=" + matchObj.getBaseDn());
	 	log.debug("ID field=" + matchObj.getKeyField());
	 	
	 	// get the baseDN
	 	String baseDN = matchObj.getBaseDn();
	 	
	 	// get the field that is to be used as the UniqueIdentifier
	 	 	String ldapName = psoID.getID();
	 		 	
	 	BasicAttributes basicAttr = getBasicAttributes(reqType.getData().getAny(),matchObj.getKeyField());

	 	
	 	// create the record in ldap
	 	try {
	 		Context result = ldapctx.createSubcontext(ldapName, basicAttr);		
	 	}catch(NamingException ne) {
	 		// return a response object - even if it fails so that it can be logged.
	 		ne.printStackTrace();
	 	}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
	 	}
	 	
		AddResponseType response = new AddResponseType();
		response.setStatus(StatusCodeType.SUCCESS);
	
		return response;	 	
		

	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#delete(org.openiam.spml2.msg.DeleteRequestType)
	 */
	public ResponseType delete(DeleteRequestType reqType) {
		log.debug("delete request called..");
		
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
		
		log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.debug("Ldapcontext = " + ldapctx);
	 	
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
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
	 	String ldapName = matchObj.getKeyField() +"=" + psoID.getID() + "," + baseDN;
	 	
	 	
	 	// create the record in ldap

	 	try {
	 		ldapctx.destroySubcontext(ldapName);	
	 	}catch(NamingException ne) {
	 		// return a response object - even if it fails so that it can be logged.
	 		ne.printStackTrace();
	 	}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
	 	}
	 	
	 	ResponseType respType = new ResponseType();
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#modify(org.openiam.spml2.msg.ModifyRequestType)
	 */
	public ModifyResponseType modify(ModifyRequestType reqType) {
		/* FOR LDAP, need to be able to move object's OU - incase of re-org, person changes roles, etc */
		/* Need to be able add and remove users from groups */ 
		
		// TODO Auto-generated method stub
		
		log.debug("Modify request called..");
		
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
		log.debug("Modificationlist size= " + modificationList.size() );

	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);
		
		log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.debug("Ldapcontext = " + ldapctx);
	 	
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
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
	 	String ldapName = matchObj.getKeyField() +"=" + psoID.getID() + "," + baseDN;
	 	
	 	// create the record in ldap

	 	try {
	 		for (ModificationType modType  : modificationList ) {
	 		
	 			ModificationItem[] mods = getModificationAttributes(modType,matchObj.getKeyField());
	 			
	 			log.debug("modification items=" + mods);
	 			
	 			ldapctx.modifyAttributes(ldapName, mods);
	 		
	 		}
	
	 	}catch(NamingException ne) {
	 		log.error(ne.getMessage(), ne);
	 		// return a response object - even if it fails so that it can be logged.
	 	}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
	 	}
	 	
	 	ModifyResponseType respType = new ModifyResponseType();
	 	return respType;

	}
	
	private ModificationItem[] getModificationAttributes(ModificationType modType, String idField) {
		
		List<Object> atrList = null;//  = modType.getAny();
		
		 ExtensibleType extType = modType.getData();
		 int operation = 0;
		 		 
		 log.debug("Modification atrList size=" + atrList.size());
		 
		 if (modType.getModificationMode() == ModificationModeType.ADD ) {
			 operation = ModificationAttribute.add;
		 }else if (modType.getModificationMode() == ModificationModeType.REPLACE ) {
			 operation = ModificationAttribute.replace;
		 }else {
			 operation = ModificationAttribute.delete;
		 }
		 
		 int size = atrList.size();
		 ModificationItem[] modItemAry = new ModificationItem[size];
		 for( int i =0; i < size; i++ ) {
				ExtensibleAttribute extAtr = (ExtensibleAttribute)atrList.get(i);
				log.debug("Ext Attr name=" + extAtr.getName() + " value=" + extAtr.getValue());
				if (extAtr.getName() != idField ) {
					modItemAry[i] = new ModificationItem(operation, new BasicAttribute(extAtr.getName(), extAtr.getValue()));
				}
		 }
		 return modItemAry;
		 
	}
	
	private BasicAttributes getBasicAttributes(List<ExtensibleObject> requestAttribute, String idField) {
		BasicAttributes attrs = new BasicAttributes();
		
		// add the object class
		Attribute oc = new BasicAttribute("objectclass");
		// need to make this dynamic
		oc.add("user");
	    oc.add("organizationalPerson");
	    oc.add("person");
		oc.add("top");
		
		// add the structural classes
		attrs.put(oc);
		//attrs.put(ouSet);
			
		// add the identifier
		
		// add the attributes
		for( ExtensibleObject obj : requestAttribute ) {
			List<ExtensibleAttribute> attrList =  obj.getAttributes();
			for (ExtensibleAttribute att: attrList) {
				if (att.getName() != idField ) {
					attrs.put(att.getName(), att.getValue());
				}
			}
		}
		attrs.put("userAccountControl",Integer.toString(
				ActiveDirectoryEnum.UF_NORMAL_ACCOUNT.getValue() + 
				ActiveDirectoryEnum.UF_PASSWD_NOTREQD.getValue() + 
				ActiveDirectoryEnum.UF_PASSWORD_EXPIRED.getValue() + 
				ActiveDirectoryEnum.UF_ACCOUNTDISABLE.getValue() ));
		
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
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);
		
		log.debug("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.debug("Ldapcontext = " + ldapctx);
	 	
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
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
	 	String ldapName = matchObj.getKeyField() +"=" + psoID.getID() + "," + baseDN;
	 	
	 	// create the record in ldap

	 	StartTlsResponse tls = null;
	 	
	 	try {
	 		// start TLS - need secure communication
	 		tls = (StartTlsResponse)ldapctx.extendedOperation(new StartTlsRequest());
			tls.negotiate();
			
	 		ModificationItem[] mods = new ModificationItem[3];
	 		
			//Replace the "unicdodePwd" attribute with a new value
			//Password must be both Unicode and a quoted string
			//String password = "\"" + "password123" + "\"";
			String newQuotedPassword = "\"" + reqType.getPassword() + "\"";
			byte[] bytePassword = newQuotedPassword.getBytes("UTF-16LE");
 
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", bytePassword));
			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, 
					new BasicAttribute("userAccountControl",Integer.toString(ActiveDirectoryEnum.UF_NORMAL_ACCOUNT.getValue() +
																			 ActiveDirectoryEnum.UF_PASSWORD_EXPIRED.getValue())));
			mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("pwdLastSet",Integer.toString(0)));

	 		
	 		
	 		//mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", reqType.getPassword()));
	 		ldapctx.modifyAttributes(ldapName, mods);
	 		tls.close();
	 		
	 	}catch(NamingException ne) {
	 		log.error(ne.getMessage(), ne);
	 		ne.printStackTrace();
	 		// return a response object - even if it fails so that it can be logged.
	 	}catch (UnsupportedEncodingException e) {
	 		log.error(e.getMessage(), e);
			e.printStackTrace();
		}catch(IOException io) {
			log.error(io.getMessage(), io);
			io.printStackTrace();
		}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
	 	}
	 	
	 	ResponseType respType = new ResponseType();
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

}
