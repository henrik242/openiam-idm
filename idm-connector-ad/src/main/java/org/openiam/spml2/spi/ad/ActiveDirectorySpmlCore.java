package org.openiam.spml2.spi.ad;

import javax.jws.WebService;

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
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;


@WebService(endpointInterface = "org.openiam.spml2.interf.SpmlComplete", 
		targetNamespace = "urn:idm.openiam.org/spml2/service", 
		serviceName = "ActiveDirSpmlService")
public class ActiveDirectorySpmlCore implements SpmlComplete {

	public AddResponseType add(AddRequestType reqType) {
		System.out.println("Add() called.....");
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

	public ResponseType expirePassword(ExpirePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResetPasswordResponseType resetPassword(
			ResetPasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseType setPassword(SetPasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ValidatePasswordResponseType validatePassword(
			ValidatePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

}
