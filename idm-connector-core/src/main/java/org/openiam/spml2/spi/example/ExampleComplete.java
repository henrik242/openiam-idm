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
package org.openiam.spml2.spi.example;

import java.util.List;

import javax.jws.WebService;

import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.provision.type.ExtensibleAddress;
import org.openiam.provision.type.ExtensibleEmailAddress;
import org.openiam.provision.type.ExtensibleGroup;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensiblePhone;
import org.openiam.provision.type.ExtensibleRole;
import org.openiam.provision.type.ExtensibleUser;

import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.ListTargetsRequestType;
import org.openiam.spml2.msg.ListTargetsResponseType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;

/**
 * Connector shell that can be used to jumpstart the creation of a connector service.
 * @author suneet
 *
 */

@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "ExampleConnectorServicePort", 
		serviceName="ExampleConnectorService")

/*@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "AICmsConnectorServicePort", 
		serviceName="AICmsConnectorService")
*/
public class ExampleComplete implements ConnectorService {

	public boolean testConnection(String targetID) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#add(org.openiam.spml2.msg.AddRequestType)
	 */
	public AddResponseType add(AddRequestType reqType) {
		System.out.println("add request called..");
		
		System.out.println("POS Identitfier: " + reqType.getPsoID().getID());
		System.out.println("RequestID: " + reqType.getRequestID());
		System.out.println("TargetId: " + reqType.getTargetID());
		
		System.out.println("Data:" );
		List<ExtensibleObject> objList = reqType.getData().getAny();
		for (ExtensibleObject obj: objList) {
			System.out.println("Object:" + obj.getName() + " - operation=" + obj.getOperation());
			List<ExtensibleAttribute> attrList =  obj.getAttributes();
			for (ExtensibleAttribute att: attrList) {
				System.out.println("-->Attribute:" + att.getName() + " - value=" + att.getValue() + " operation=" + att.getOperation());
			}
			
			ExtensibleUser extUser =  (ExtensibleUser)obj;
		
			// show the groups for this user
			List<ExtensibleGroup> extGroupList =  extUser.getGroup();
			for (ExtensibleGroup g : extGroupList) {
				System.out.println("Group:" + g.getGroup().getGrpId());
			}
			
			// show the roles for this user
			List<ExtensibleRole> extRoleList =  extUser.getRole();
			System.out.println("Roles=" + extRoleList);
			for (ExtensibleRole r : extRoleList) {
				System.out.println("Role:" + r.getRole().getId());
			}
			// show the Addresses
			List<ExtensibleAddress> extAddressList =  (List<ExtensibleAddress>)extUser.getAddress();
			for (ExtensibleAddress adr : extAddressList) {
				System.out.println("Address: " + adr.getAddress().getAddress1());
			}
			
			
			// show the email accounts
			List<ExtensiblePhone> extPhoneList = extUser.getPhone();
			for (ExtensiblePhone phone: extPhoneList) {
				System.out.println("Address: " + phone.getPhone().getPhoneNbr());
			}
			
			// show the phone numbers
			List<ExtensibleEmailAddress> extEmailList = extUser.getEmail();
			for (ExtensibleEmailAddress email: extEmailList) {
				System.out.println("Email address:" + email.getEmailAddress().getEmailAddress());
			}
			
		}

		AddResponseType resp = new AddResponseType();
		resp.setRequestID(reqType.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
		

	}
	

	

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#delete(org.openiam.spml2.msg.DeleteRequestType)
	 */
	public ResponseType delete(DeleteRequestType reqType) {
		System.out.println("delete request called..");
		
		System.out.println("POS Identitfier: " + reqType.getPsoID().getID());
		System.out.println("RequestID: " + reqType.getRequestID());
		System.out.println("Target: " + reqType.getPsoID().getTargetID());
		
	
		ResponseType resp = new ResponseType();
		resp.setRequestID(reqType.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
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
		System.out.println("add request called..");
		
		System.out.println("POS Identitfier: " + reqType.getPsoID().getID());
		System.out.println("RequestID: " + reqType.getRequestID());
		System.out.println("TargetId: " + reqType.getPsoID().getTargetID());
		
		System.out.println("Data:" );
		List<ModificationType> modTypeList = reqType.getModification(); 
		for (ModificationType mod: modTypeList) {
			ExtensibleType extType =  mod.getData();
			List<ExtensibleObject> extobjectList = extType.getAny();
			for (ExtensibleObject obj: extobjectList) {
				System.out.println("Object:" + obj.getName() + " - operation=" + obj.getOperation());
				List<ExtensibleAttribute> attrList =  obj.getAttributes();
				for (ExtensibleAttribute att: attrList) {
					System.out.println("-->Attribute:" + att.getName() + " - value=" + att.getValue() + " operation=" + att.getOperation());
				}
				
				ExtensibleUser extUser =  (ExtensibleUser)obj;
				
				// show the groups for this user
				List<ExtensibleGroup> extGroupList =  extUser.getGroup();
				for (ExtensibleGroup g : extGroupList) {
					System.out.println("Group:" + g.getGroup().getGrpId() + " OPERATION=" + g.getOperation());
				}
				
				// show the roles for this user
				List<ExtensibleRole> extRoleList =  extUser.getRole();
				System.out.println("Roles=" + extRoleList);
				for (ExtensibleRole r : extRoleList) {
					System.out.println("Role:" + r.getRole().getId() + " OPERATION=" + r.getOperation());
				}

				List<ExtensibleAddress> extAddressList =  (List<ExtensibleAddress>)extUser.getAddress();
				for (ExtensibleAddress adr : extAddressList) {
					System.out.println("Address: " + adr.getAddress().getAddress1() + " - " + adr.getName());
				}
				
				
				// show the email accounts
				List<ExtensiblePhone> extPhoneList = extUser.getPhone();
				for (ExtensiblePhone phone: extPhoneList) {
					System.out.println("Phone: " + phone.getPhone().getPhoneNbr() + " - " + phone.getName());
				}
				
				// show the phone numbers
				List<ExtensibleEmailAddress> extEmailList = extUser.getEmail();
				for (ExtensibleEmailAddress email: extEmailList) {
					System.out.println("Email address:" + email.getEmailAddress().getEmailAddress());
				}
				
			}
		}
		ModifyResponseType resp = new ModifyResponseType();
		resp.setRequestID(reqType.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
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
	public ResponseType setPassword(SetPasswordRequestType request) {
		System.out.println("setPassword request called..");
		
		System.out.println("POS Identitfier: " + request.getPsoID().getID());
		System.out.println("RequestID: " + request.getRequestID());
		System.out.println("Password: " + request.getPassword());
		
	
		ResponseType resp = new ResponseType();
		resp.setRequestID(request.getRequestID());
		resp.setStatus(StatusCodeType.SUCCESS);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlPassword#validatePassword(org.openiam.spml2.msg.password.ValidatePasswordRequestType)
	 */
	public ValidatePasswordResponseType validatePassword(
			ValidatePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

}
