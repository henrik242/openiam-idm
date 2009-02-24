package org.openiam.spml2.spi.ldap;

import java.io.UnsupportedEncodingException;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.openiam.spml2.interf.SpmlComplete;

import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ListTargetsRequestType;
import org.openiam.spml2.msg.ListTargetsResponseType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.PSOIdentifierType;

import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;


import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.connector.ConnectionMgr;


@WebService(endpointInterface = "org.openiam.spml2.interf.SpmlComplete", 
		targetNamespace = "urn:idm.openiam.org/spml2/service", 
		serviceName = "LdapSpmlService")
public class LDAPSpmlCore implements SpmlComplete {
	
	ManagedSystemDataService managedSysService;
	ConnectionMgr connection;

	public LDAPSpmlCore() {
		
	}
	/**
	 * The add operation enables a requestor to create a new object on a target
	 * @param reqType
	 * @return
	 */
	public AddResponseType add(AddRequestType reqType) {
		// 
		// TODO  If a request contains an invalid "targetID", the provider’s response SHOULD specify “error=’noSuchIdentifier’”.
		// Any <containerID> MUST identify an object that exists on the target. (
		// For non-DSML - Another profile may specify that an instance of {PSOIdentifierType} MAY omit "ID".
		//
		// A provider SHOULD expose an immutable value (such as a globally unique identifier or “GUID”)
		// A <addRequest> MAY specify “executionMode”.
		
		// An <addRequest> SHOULD specify “targetID”.
		// MAY contain a <psoID>. (A requestor supplies <psoID> in order to specify an identifier for the new object.
		// 
		// An <addRequest> MAY contain a <containerID>. (A requestor supplies <containerID> in order to specify an existing object under which the new object should be bound.)

		//Data. An <addRequest> MUST contain a <data> element that supplies initial content for the new
		// object. A <data> element MUST contain only elements and attributes defined by the target
		// schema as valid for the schema entity of which the object to be added is an instance.
		
		//A requestor that wants the provider to return nothing of the added object
		// MUST specify “returnData=’nothing’”.
		// A requestor that wants the provider to return only the identifier of the added object
		//  MUST specify “returnData=’identifier’”.
		
		System.out.println("Add() called.....");
		
		String baseDN = null; // in  a complex environment, this can be calculated through the scripts.
		
		PSOIdentifierType psoId = reqType.getPsoID();
		String objectId = psoId.getID();
		
		
		String targetId = reqType.getTargetID();
		if (targetId == null || targetId.length() == 0) {
			// return an error code
		}
		System.out.println("targetId=" + targetId);
		
		// step 1 - take the targetId and get the managed system object
		ManagedSys sys = managedSysService.getManagedSys(targetId);
	
		// step 2 - create a connection object from the managed system
		LdapContext context = connection.connect(sys);
		
		// step 3 - get the data from the request
		BasicAttributes basicAttr = null;
		
		// step 4 - do the attribute mapping
		ExtensibleType extData = reqType.getData();
		extData.getAny();
		
		// step 5 - write to ldap
		try {
			
			String ldapName = "uid=" + objectId + "," + baseDN;
			Context result = context.createSubcontext(ldapName, basicAttr);
			context.close();	
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		// return a response
		return null;
	}

	public ResponseType delete(DeleteRequestType reqType) {
		return null;
	}

	public LookupResponseType lookup(LookupRequestType reqType) {
		return null;
	}

	public ModifyResponseType modify(ModifyRequestType reqType) {

		return null;
	}

	public ListTargetsResponseType listTargets(ListTargetsRequestType reqType) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * The setPassword operation enables a requestor to specify a new password for an object
	 * @param request
	 * @return
	 */
	public ResponseType setPassword(SetPasswordRequestType request) {
		String sn = null, firstName = null, email = null, dn = null;
		
		String objectId = request.getPsoID().getID();
		String targetId = request.getPsoID().getTargetID();
		String password = request.getPassword();
		
		if (targetId == null || targetId.length() == 0) {
			// return an error code
		}
		
		// step 1 - take the targetId and get the managed system object
		ManagedSys sys = managedSysService.getManagedSys(targetId);
		ManagedSystemObjectMatch matchParam = sys.getObjectMatchDetailsByType("USER");
	
		// step 2 - create a connection object from the managed system
		LdapContext context = connection.connect(sys);
		
		// step 3 - find the object
		NamingEnumeration answer = search(context, matchParam, objectId);
		
		// step 4 - change the password
		//initialize counter to total the results
		int totalResults = 0;


		try {
		if  (answer.hasMoreElements()) {
    		SearchResult sr = (SearchResult)answer.next();

			// Print out some of the attributes, catch the exception if the attributes have no values
			Attributes attrs = sr.getAttributes();
			if (attrs != null) {
				try {
					if (attrs.get("sn") != null ) {
						sn = (String)attrs.get("sn").get();
					}
					if (attrs.get("givenName") != null ) {
						firstName = (String)attrs.get("givenName").get();
					}
					if (attrs.get("mail") != null ) {
						email = (String)attrs.get("mail").get();
					}
					if (attrs.get("distinguishedName") != null ) {
						dn = (String)attrs.get("distinguishedName").get();
					}
					System.out.println("dn=" + dn);
				} 
				catch (NullPointerException e)	{
					System.out.println("Errors listing attributes: " + e);
				}
			}
			ModificationItem[] mods = new ModificationItem[1];
			byte[] bytePassword = password.getBytes("UTF-16LE");
 
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", password));
 
			// Perform the update
			context.modifyAttributes(dn, mods);
		}
		}catch (NamingException e) {
			System.out.println("Problem resetting password: " + e);
		}catch (UnsupportedEncodingException e) {
			System.out.println("Problem encoding password: " + e);
		}
		
		return null;
	}
	
	/**
	 * The expirePassword operation marks as invalid the current password for an object
	 * @param request
	 * @return
	 */
	public ResponseType expirePassword(ExpirePasswordRequestType request) {
		return null;
	}

	/**
	 * The resetPassword operation enables a requestor to change (to an unspecified value) the
 	 * password for an object and to obtain that newly generated password value.
	 * @param request
	 * @return
	 */
	public ResetPasswordResponseType resetPassword(ResetPasswordRequestType request) {
		return null;
	}
	
	/**
	 * The validatePassword operation enables a requestor to determine whether a specified value would
	 *  be valid as the password for a specified object.
	 * @param request
	 * @return
	 */
	public ValidatePasswordResponseType validatePassword(ValidatePasswordRequestType request) {
		return null;
	}

	
	
	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}
	public ConnectionMgr getConnection() {
		return connection;
	}
	public void setConnection(ConnectionMgr connection) {
		this.connection = connection;
	}
	
	private NamingEnumeration search(LdapContext ctx ,ManagedSystemObjectMatch matchParam, String filterValue) {
		SearchControls searchCtls = new SearchControls();
		
		//Specify the attributes to returned
		String returnedAtts[]={"sn","givenName","mail", "distinguishedName"};
		searchCtls.setReturningAttributes(returnedAtts);
		
		//Specify the search scope
		try {
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			//String searchFilter = "(&(objectClass=user)(" + filterAttr + "=" + filterValue + "))";
			String searchFilter = matchParam.getSearchFilter();
			String baseDN = matchParam.getBaseDn();
			return ctx.search(baseDN, searchFilter, searchCtls);
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		return null;
		
	}
	
	

}
