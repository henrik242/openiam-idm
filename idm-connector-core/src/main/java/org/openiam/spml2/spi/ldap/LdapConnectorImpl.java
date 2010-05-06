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
package org.openiam.spml2.spi.ldap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.ConfigurationException;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
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
import org.openiam.spml2.msg.ErrorCode;
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
import org.openiam.spml2.msg.PSOType;
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
		portName = "LDAPConnectorServicePort", 
		serviceName="LDAPConnectorService")
public class LdapConnectorImpl extends AbstractSpml2Complete implements ConnectorService  {

	private static final Log log = LogFactory.getLog(LdapConnectorImpl.class);
	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected ResourceDataService resourceDataService;
	
	
	public boolean testConnection(String targetID) {
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID); 
		
		log.info("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
		
	
	 	LdapContext ldapctx = conMgr.connect(managedSys);	
	 	if (ldapctx == null)
	 		 return false;
	 	return true;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#add(org.openiam.spml2.msg.AddRequestType)
	 */
	public AddResponseType add(AddRequestType reqType) {
		log.info("add request called..");
		
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
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);  
		
		log.info("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.info("Ldapcontext = " + ldapctx);
	 	
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
	 	
	 	log.info("matchObj = " + matchObj);
	 	
	 	if (matchObj == null) {
	 		throw new ConfigurationException("LDAP configuration is missing configuration information");
	 	}
	 	
	 	log.info("baseDN=" + matchObj.getBaseDn());
	 	log.info("ID field=" + matchObj.getKeyField());
	 	
	 	// get the baseDN
	 	String baseDN = matchObj.getBaseDn();

	 	
	 	// get the field that is to be used as the UniqueIdentifier
	 	//String ldapName = matchObj.getKeyField() +"=" + psoID.getID() + "," + baseDN;
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
		log.info("delete request called..");
	 	
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
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);  
		
		log.info("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.info("Ldapcontext = " + ldapctx);
	 	

	 	
	 	String ldapName = psoID.getID();
	 	
	 	log.info("ldapname=" + ldapName);
	 	

	 	try {
	 		ldapctx.destroySubcontext(ldapName);	
	 	}catch(NamingException ne) {
	 		// return a response object - even if it fails so that it can be logged.
	 		ne.printStackTrace();
	 		
	 		log.error(ne);
		 	
	 		ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.UNSUPPORTED_OPERATION);
		 	return respType;
		 	
	 	}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
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
		
		LookupResponseType respType = new LookupResponseType();

		if (reqType == null) {
			respType.setStatus(StatusCodeType.FAILURE);
			respType.setError(ErrorCode.MALFORMED_REQUEST);
			return respType;
		}
		PSOIdentifierType psoId = reqType.getPsoID();
		String identity = psoId.getID();
		String rdn = null;
		
		int indx = identity.indexOf(",");
		if (indx > 0 ) {
			rdn = identity.substring(0, identity.indexOf(",") );
		}else {
			rdn = identity;
		}
		
		log.info("looking up identity: " + identity);
		
		ManagedSys managedSys =  managedSysService.getManagedSys(psoId.getTargetID()); 
		
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
		LdapContext ldapctx = conMgr.connect(managedSys);
		ManagedSystemObjectMatch[] matchObj = managedSysService.managedSysObjectParam(psoId.getTargetID(),"USER");
		String resourceId = managedSys.getResourceId();
		
		log.info("Resource id = " + resourceId);
		List<AttributeMap> attrMap =  resourceDataService.getResourceAttributeMaps(resourceId);
		if (attrMap != null) {
			List<String> attrList =  getAttributeNameList(attrMap);		
			String[] attrAry = new String[attrList.size()]; 
			attrList.toArray(attrAry);
			log.info("Attribute array=" + attrAry);
			
			try {
				NamingEnumeration results = search(matchObj[0], ldapctx, rdn, attrAry);	
				
				log.info("results=" + results);
				log.info(" results has more elements=" + results.hasMoreElements());
	
				PSOType psoType = new PSOType();
				psoType.setPsoID(psoId);
				respType.setPso(psoType);
				
				ExtensibleObject extObj = new ExtensibleObject();
				extObj.setObjectId(identity);
				
				while (results != null && results.hasMoreElements()) {
					SearchResult sr = (SearchResult)results.next();
					Attributes attrs =  sr.getAttributes();
					if (attrs != null ) {
						for (NamingEnumeration ae = attrs.getAll();ae.hasMore();) {
							ExtensibleAttribute extAttr = new ExtensibleAttribute();
							Attribute attr = (Attribute)ae.next();
							boolean addToList = false;
							System.out.println("Attribute: " + attr.getID());
							
							extAttr.setName(attr.getID());
							
							NamingEnumeration e = attr.getAll();
							while ( e.hasMore()) {
								Object o = e.next();
								log.info("element type = " + o.getClass().getName());
								if ( o instanceof String) {
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
					
			
			}catch(NamingException ne) {
				ne.printStackTrace();
				respType.setStatus(StatusCodeType.FAILURE);
				respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
				return respType;
			}
		}
		
		
		
		respType.setStatus(StatusCodeType.SUCCESS);
		return respType;

	}
	
	private List<String>  getAttributeNameList(List<AttributeMap> attrMap) {
		List<String> strList = new ArrayList<String>();
	
		if (attrMap == null || attrMap.size() == 0) {
			return null;
		}
		for ( AttributeMap a : attrMap )  {
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
		
		// TODO Auto-generated method stub
		
		log.info("Modify request called..");
		
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
		
		log.info("ModificationList = " + modificationList);
		log.info("Modificationlist size= " + modificationList.size() );

	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID); 
		
		log.info("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.info("Ldapcontext = " + ldapctx);
	 	
	 	String ldapName = psoID.getID();
	 	

		 
	 	try {
	 	List<ModificationType> modTypeList = reqType.getModification(); 
	 	for (ModificationType mod: modTypeList) {
	 		ExtensibleType extType =  mod.getData();
	 		List<ExtensibleObject> extobjectList = extType.getAny();
	 		for (ExtensibleObject obj: extobjectList) {
	 			log.info("Object:" + obj.getName() + " - operation=" + obj.getOperation());
	 			List<ExtensibleAttribute> attrList =  obj.getAttributes();
	 			ModificationItem[] mods = new ModificationItem[attrList.size()];
	 			int  ctr = 0;
	 			for (ExtensibleAttribute att: attrList) {
	 				 // mods[ctr] = new ModificationItem(att.getOperation(), new BasicAttribute(att.getName(), att.getValue()));
	 				  mods[ctr] = new ModificationItem(2, new BasicAttribute(att.getName(), att.getValue()));
	 				  ctr++;
	 			}
	 			ldapctx.modifyAttributes(ldapName, mods);
	 		}
	 	}
	 	}catch(NamingException ne) {
	 		log.error(ne.getMessage(), ne);
	 		// return a response object - even if it fails so that it can be logged.
	 	}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
	 	}
	 	
	 	// create the record in ldap

	 	
	 	ModifyResponseType respType = new ModifyResponseType();
	 	respType.setStatus(StatusCodeType.SUCCESS);
	 	return respType;

	}
	
	private NamingEnumeration search(ManagedSystemObjectMatch matchObj, 
				LdapContext ctx, 
				String searchValue, String[] attrAry) throws NamingException {
		SearchControls searchCtls = new SearchControls();
		
		//Specify the attributes to returned
		//String returnedAtts[]={"uid","cn","sn", "ou"};
		//searchCtls.setReturningAttributes(returnedAtts);
		searchCtls.setReturningAttributes(attrAry);
		
		
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String searchFilter = matchObj.getSearchFilter();
		// replace the place holder in the search filter
		searchFilter = searchFilter.replace("?", searchValue);

		
		System.out.println("Search Filter=" + searchFilter);
		System.out.println("BaseDN=" + matchObj.getBaseDn());
		
		return ctx.search(matchObj.getSearchBaseDn(), searchFilter, searchCtls);
	

		
	}
	
	private ModificationItem[] getModificationAttributes(ModificationType modType, String idField) {
		
		List<Object> atrList = null;//  = modType.getAny();
		
		 ExtensibleType extType = modType.getData();
		 int operation = 0;
		 		
		 
		 if (modType.getModificationMode() == ModificationModeType.ADD ) {
			 operation = ModificationAttribute.add;
		 }else if (modType.getModificationMode() == ModificationModeType.REPLACE ) {
			 operation = ModificationAttribute.replace;
		 }else {
			 operation = ModificationAttribute.delete;
		 }

		 log.info("Modify operation: looping through attributes.");
		 
		 
		 
		 int size = atrList.size();
		 ModificationItem[] modItemAry = new ModificationItem[size];
		 for( int i =0; i < size; i++ ) {
				ExtensibleAttribute extAtr = (ExtensibleAttribute)atrList.get(i);
				log.info("Ext Attr name=" + extAtr.getName() + " value=" + extAtr.getValue());
				if (extAtr.getName() != idField ) {
					modItemAry[i] = new ModificationItem(operation, new BasicAttribute(extAtr.getName(), extAtr.getValue()));
				}
		 }
		 return modItemAry;
		 
	}
	
	private String getOU(List<ExtensibleObject> requestAttribute) {
		for( ExtensibleObject obj : requestAttribute ) {
			List<ExtensibleAttribute> attrList =  obj.getAttributes();
			for (ExtensibleAttribute att: attrList) {
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
		log.info("GetAttributes() - ou=" + ou);
		if (ou != null && ou.length() > 0) {
			ouSet.add(ou);
		}

		// add the structural classes
		attrs.put(oc);
		attrs.put(ouSet);
			
		// add the identifier
		
		// add the attributes
		for( ExtensibleObject obj : requestAttribute ) {
			List<ExtensibleAttribute> attrList =  obj.getAttributes();
			for (ExtensibleAttribute att: attrList) {
				
				log.info("Attr Name=" + att.getName() +  " " + att.getValue());
				
				if (att.getName() != idField ) {
					attrs.put(att.getName(), att.getValue());
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
		log.info("setPassword request called..");
		
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
		
		log.info("managedSys found for targetID=" + targetID + " " + " Name=" + managedSys.getName());
		ConnectionMgr conMgr = ConnectionFactory.create(ConnectionManagerConstant.LDAP_CONNECTION);
	 	LdapContext ldapctx = conMgr.connect(managedSys);
		
	 	log.info("Ldapcontext = " + ldapctx);
	 	
	 	
	 	String ldapName = psoID.getID();
	 	
	 	try {
	 		ModificationItem[] mods = new ModificationItem[1];
	 		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", reqType.getPassword()));
	 		ldapctx.modifyAttributes(ldapName, mods);
	
	 	}catch(NamingException ne) {
	 		log.error(ne.getMessage(), ne);
	 		
	 		ResponseType resp = new ResponseType();
	 		resp.setStatus(StatusCodeType.FAILURE);
	 		resp.setError(ErrorCode.NO_SUCH_IDENTIFIER);
	 		return resp;
	 	}catch(Exception ne) {
	 		log.error(ne.getMessage(), ne);
	 		
	 		ResponseType resp = new ResponseType();
	 		resp.setStatus(StatusCodeType.FAILURE);
	 		resp.setError(ErrorCode.NO_SUCH_IDENTIFIER);
	 		return resp;
	 		
	 	}finally {
	 		/* close the connection to the directory */
	 		conMgr.close();
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

}
