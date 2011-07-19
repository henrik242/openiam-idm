package org.openiam.idm.srvc.auth.service;


import org.openiam.base.ws.BooleanResponse;
import org.openiam.base.ws.Response;
import org.openiam.exception.AuthenticationException;
import org.openiam.exception.LogoutException;
import org.openiam.idm.srvc.auth.dto.AuthzRequest;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.ws.AuthenticationResponse;
import org.openiam.idm.srvc.auth.ws.AuthzPermissionResponse;
import org.openiam.idm.srvc.auth.ws.AuthzResponse;
import org.openiam.idm.srvc.grp.dto.Group;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * <p>
 * <code>AuthenticateService</code> <font face="arial"> allows users to authenticate using
 * various methods </font>
 * </p>
 */
@WebService
@XmlSeeAlso({
	Group.class
})
public interface AuthorizationService {


	@WebMethod
    AuthzResponse isAuthorized(
            @WebParam(name = "request", targetNamespace = "")
            AuthzRequest request) throws LogoutException;


  	@WebMethod
      AuthzPermissionResponse getPermissions(
            @WebParam(name = "request", targetNamespace = "")
            AuthzRequest request) throws LogoutException;



}
