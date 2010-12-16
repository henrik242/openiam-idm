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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.exception.ConfigurationException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditHelper;
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
import org.openiam.idm.srvc.mngsys.service.ManagedSysDAO;
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

import com.google.gdata.client.appsforyourdomain.UserService;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.Name;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.util.ServiceException;

/**
 * Updates the OpenIAM repository with data received from external client.
 * @author suneet
 *
 */

@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "ShellConnectorServicePort", 
		serviceName="ShellConnectorService")
public class ShellConnectorImpl extends AbstractSpml2Complete implements ConnectorService  {

	private static final Log log = LogFactory.getLog(ShellConnectorImpl.class);
	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected ResourceDataService resourceDataService;
	protected IdmAuditLogDataService auditDataService;
	protected LoginDataService loginManager;
	protected PolicyDAO policyDao;
	protected SecurityDomainDataService secDomainService; 
	protected UserDataService userManager;
	
	static String keystore;
	
	public AuthenticationResponse login(AuthenticationContext authContext) {
		
		return null;
		
	}
	
	public LdapContext connect(String userName, String password) {

		return null;
	}


	
	
	public boolean testConnection(String targetID) {

	 	return true;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#add(org.openiam.spml2.msg.AddRequestType)
	 */
	public AddResponseType add(AddRequestType reqType) {
		log.debug("add request called..");
		
	//	powershell.exe -command "& C:\appserver\apache-tomcat-6.0.26\powershell\create.ps1 displayName principalName firstName middleInit lastName password"
		
		String userName = null;
		
		String requestID = reqType.getRequestID();
		/* ContainerID - May specify the container in which this object should be created
		 *      ie. ou=Development, org=Example */
		PSOIdentifierType containerID = reqType.getContainerID();
		System.out.println("ContainerId =" + containerID );
	
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();
		userName = psoID.getID();
		
		System.out.println("PSOId=" + psoID.getID());

		
		/* targetID -  */
		String targetID = reqType.getTargetID();
				
		// Data sent with request - Data must be present in the request per the spec
		ExtensibleType data = reqType.getData(); 
		Map<QName,String> otherData  = reqType.getOtherAttributes();
		
		/* Indicates what type of data we should return from this operations */
		ReturnDataType returnData =  reqType.getReturnData();
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);  
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
		
		
		List<ExtensibleObject> requestAttributeList =  reqType.getData().getAny();
	

		
		String password = null;
		String givenName = null;
		String lastName = null;
		String displayName = null;
		String init = null;
		String cn = null;
		String principalName = null;
		String sAMAccountName = null;
		String middleInit = null;
		String email = null;
		String nickname = null;
		String title = null;
		String getExchange = null;
		String userState = null;
		
		String host;
		String hostlogin;
		String hostpassword;
		
		host = managedSys.getHostUrl();
		hostlogin = managedSys.getUserId();
		hostpassword = managedSys.getDecryptPassword();
		
		for( ExtensibleObject obj : requestAttributeList ) {
			List<ExtensibleAttribute> attrList =  obj.getAttributes();
			
			for (ExtensibleAttribute att: attrList) {
				
				System.out.println("Attr Name=" + att.getName() +  " " + att.getValue());
				
				String name = att.getName();
				String value = att.getValue();
				
				if (name.equalsIgnoreCase("password")) {
					password = value;
					
				}
				if (name.equalsIgnoreCase("firstName")) {
					givenName = value;
					
				}
				if (name.equalsIgnoreCase("lastName")) {
					lastName = value;
				}
				if (name.equalsIgnoreCase("displayName")) {
					displayName = value;
					
				}
				if (name.equalsIgnoreCase("initials")) {
					init = value;
				}
				if (name.equalsIgnoreCase("cn")) {
					cn = value;
				}
				if (name.equalsIgnoreCase("principalName")) {
					principalName = value;
				}			
				if (name.equalsIgnoreCase("sAMAccountName")) {
					sAMAccountName = value;
				}				
				if (name.equalsIgnoreCase("middleInit")) {
					middleInit = value;
				}				
				if (name.equalsIgnoreCase("email")) {
					email = value;
				}	
				if (name.equalsIgnoreCase("nickname")) {
					nickname = value;
				}
				if (name.equalsIgnoreCase("getExchange")) {
					getExchange = value;
				}
				if (name.equalsIgnoreCase("title")) {
					title = value;
				}
				if (name.equalsIgnoreCase("userState")) {
					userState = value;
				}
			}
		}
		
		/*
		 $adHost = 			$args[0]
$user=				$args[1]
$userpswd=			$args[2]
$cn=				$args[3]
$principalName = 	$args[4]
$samAccountName = 	$args[5]
$pswd = 			$args[6]
$givenname =		$args[7]
$sn = 				$args[8]
$middleInit	=		$args[9]
$displayName =		$args[10]
$ou =				$args[11]

		 */
		
		//	powershell.exe -command "& C:\appserver\apache-tomcat-6.0.26\powershell\create.ps1 displayName principalName firstName middleInit lastName password"

		StringBuffer strBuf = new StringBuffer();

		strBuf.append("cmd /c powershell.exe -command \"& C:\\powershell\\ad\\Add-UserActiveDir.ps1 ");
		strBuf.append("'"+ host +"' ");
		strBuf.append("'"+ hostlogin +"' ");
		strBuf.append("'"+ hostpassword +"' ");
		strBuf.append("'"+ userName +"' ");
		strBuf.append("'"+ principalName +"' ");
		strBuf.append("'"+ sAMAccountName +"' ");
		strBuf.append("'"+ password +"' ");
		strBuf.append("'"+ givenName +"' ");
		strBuf.append("'"+ lastName +"' ");
		strBuf.append("'"+ middleInit + "' " );
		strBuf.append("'"+ displayName + "' " );
		strBuf.append("'"+ userState + "' " );
		strBuf.append("'"+ email + "' " );
		strBuf.append("'"+ nickname + "' " );
		strBuf.append("'"+ getExchange + "' " );
		strBuf.append("'"+ title + "' \" " );
		
		//System.out.println("Command line string= " + strBuf.toString());
		String[] cmdarray = {"cmd",strBuf.toString()};
		try {
			//Runtime.getRuntime().exec(cmdarray); //exec(strBuf.toString());
			Process p = Runtime.getRuntime().exec(strBuf.toString());
			System.out.println("Process =" + p);
			OutputStream stream =  p.getOutputStream();
			//System.out.println( "stream=" + stream.toString() );
			
		}catch(Exception e) {
			e.printStackTrace();
		}


		
		
		AddResponseType response = new AddResponseType();
		response.setStatus(StatusCodeType.SUCCESS);
	
		return response;
	}

	/* (non-Javadoc)
	 * @see org.openiam.spml2.interf.SpmlCore#delete(org.openiam.spml2.msg.DeleteRequestType)
	 */
	public ResponseType delete(DeleteRequestType reqType) {
	System.out.println("Delete called..");
		
		String userName = null;
		
		String requestID = reqType.getRequestID();

		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();
		userName = psoID.getID();
		/* targetID -  */
		String targetID = psoID.getTargetID();
	
		/* ContainerID - May specify the container in which this object should be created
		 *      ie. ou=Development, org=Example */
		PSOIdentifierType containerID = psoID.getContainerID();
			
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);  


		String host;
		String hostlogin;
		String hostpassword;
		
		host = managedSys.getHostUrl();
		hostlogin = managedSys.getUserId();
		hostpassword = managedSys.getDecryptPassword();
		
		//powershell.exe -command "& C:\appserver\apache-tomcat-6.0.26\powershell\delete.ps1 principalName"


		StringBuffer strBuf = new StringBuffer();

		
		strBuf.append("cmd /c powershell.exe -command \"& C:\\powershell\\ad\\Del-AdUser.ps1 ");
		strBuf.append("'"+ host +"' ");
		strBuf.append("'"+ userName +"' \" ");

		System.out.println("**Command line string= " + strBuf.toString());

		try {
			//Runtime.getRuntime().exec(cmdarray); //exec(strBuf.toString());
			Process p = Runtime.getRuntime().exec(strBuf.toString());
			System.out.println("Process =" + p);
			OutputStream stream =  p.getOutputStream();
			System.out.println( "stream=" + stream.toString() );
		}catch(Exception e) {
			e.printStackTrace();
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
		String userName = null;
		String firstName = null;
		String lastName = null;
		String init = null;
		String displayName = null;
		String ou = null;
		String role = null;
		boolean change = false;
		String title = null;
		String userState = null;
		String sAMAccountName = null;
		
		String requestID = reqType.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();		
		userName = psoID.getID();
		
		/* targetID -  */
		String targetID = psoID.getTargetID();	
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID); 
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
	 	
		String host;
		String hostlogin;
		String hostpassword;
		
		host = managedSys.getHostUrl();
		hostlogin = managedSys.getUserId();
		hostpassword = managedSys.getDecryptPassword();
    	
	 	// get the firstName and lastName values
	 	
	 	List<ModificationType> modTypeList = reqType.getModification(); 
	 	for (ModificationType mod: modTypeList) {
	 		ExtensibleType extType =  mod.getData();
	 		List<ExtensibleObject> extobjectList = extType.getAny();
	 		for (ExtensibleObject obj: extobjectList) {
	 			System.out.println("Object:" + obj.getName() + " - operation=" + obj.getOperation());
	 			List<ExtensibleAttribute> attrList =  obj.getAttributes();
	 			List<ModificationItem> modItemList = new ArrayList<ModificationItem>();
	 			for (ExtensibleAttribute att: attrList) {
	 				if (att.getOperation() != 0 && att.getName() != null) {
	 					if (att.getName().equalsIgnoreCase("firstName")) {
	 						firstName = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("lastName")) {
	 						lastName = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("sAMAccountName")) {
	 						sAMAccountName = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("displayName")) {

	 						displayName = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("ou")) {
	 						ou = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("role")) {
	 						role = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("initials")) {
	 						init = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("title")) {
	 						title = att.getValue();
	 						change = true;
	 					}
	 					if (att.getName().equalsIgnoreCase("userState")) {
	 						userState = att.getValue();
	 						change = true;
	 					}

	 				}
	 			}
	 		}
	 	}
	 
	 	
		StringBuffer strBuf = new StringBuffer();

	//	powershell -command "& c:\powershell\ad\Upd-AdUser.ps1 'cn=Raymond Collins,cn=Users,dc=iamdev,dc=local' 'Ray' 'Coly' 'P' 'Coly, Ray'  'Knight' 0"
	//	strBuf.append(" '"+ displayName +"' ");
	//	strBuf.append(" '"+ userName +"' ");
		
		strBuf.append("cmd /c powershell.exe -command \"& C:\\powershell\\ad\\Upd-AdUser.ps1 ");
		//strBuf.append("cmd /c notepad.exe ");user
		strBuf.append(" 'CN="+ userName +",cn=USERS,DC=IAMDEV,DC=LOCAL' ");
		strBuf.append("'"+ firstName +"' ");
		strBuf.append("'"+ lastName +"' ");
		strBuf.append("'"+ init +"' ");
		strBuf.append("'"+ displayName +"' ");
		strBuf.append("'"+ title +"' ");
		strBuf.append(""+ userState +" \"");

		//strBuf.append(" '"+ ou +"' ");
		//strBuf.append(" '"+ title +"' \"");

		System.out.println("**Command line string= " + strBuf.toString());
		String[] cmdarray = {"cmd",strBuf.toString()};
		try {
			//Runtime.getRuntime().exec(cmdarray); //exec(strBuf.toString());
			Process p = Runtime.getRuntime().exec(strBuf.toString());
			System.out.println("Process =" + p);
			//OutputStream stream =  p.getOutputStream();
			//System.out.println( "stream=" + stream.toString() );
			
			BufferedReader in = new BufferedReader(  
			                           new InputStreamReader(p.getInputStream()));  
			System.out.println( "stream reader=" +in.toString() );
			in.close();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	 	
	 	// assign to google 
	 		

	 	ModifyResponseType respType = new ModifyResponseType();
	 	respType.setStatus(StatusCodeType.SUCCESS);
	 	return respType; 
	 	

	}
	
	private boolean isInDirectory(String ldapName, ManagedSystemObjectMatch matchObj,
			LdapContext ldapctx ) {
		int indx = ldapName.indexOf(",");
		String rdn = null;
		if (indx > 0 ) {
			rdn = ldapName.substring(0, ldapName.indexOf(",") );
		}else {
			rdn = ldapName;
		}
		String[] attrAry = {"uid", "cn", "fn"};  
		NamingEnumeration results = null;
		try {
			results = search(matchObj, ldapctx, rdn, attrAry);	
			if (results != null && results.hasMoreElements()) {
				return true;
			}
			return false;
		}catch(NamingException ne) {
			log.error(ne);
			return false;
		}
	}
	
	private NamingEnumeration search(ManagedSystemObjectMatch matchObj, 
				LdapContext ctx, 
				String searchValue, String[] attrAry) throws NamingException {
		SearchControls searchCtls = new SearchControls();
		
		searchCtls.setReturningAttributes(attrAry);
		
		
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String searchFilter = matchObj.getSearchFilter();
		// replace the place holder in the search filter
		searchFilter = searchFilter.replace("?", searchValue);

		
		System.out.println("Search Filter=" + searchFilter);
		System.out.println("BaseDN=" + matchObj.getBaseDn());
		
		return ctx.search(matchObj.getSearchBaseDn(), searchFilter, searchCtls);
	

		
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
		log.debug("GetAttributes() - ou=" + ou);
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
				
				log.debug("Attr Name=" + att.getName() +  " " + att.getValue());
				
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
		log.debug("setPassword request called..");
		
		String userName;
		
		String requestID = reqType.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();
		userName = psoID.getID();
		/* targetID -  */
		String targetID = psoID.getTargetID();
		/* ContainerID - May specify the container in which this object should be created
		 *      ie. ou=Development, org=Example */
		PSOIdentifierType containerID = psoID.getContainerID();
			
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID); 
		
		String host;
		String hostlogin;
		String hostpassword;
		
		host = managedSys.getHostUrl();
		hostlogin = managedSys.getUserId();
		hostpassword = managedSys.getDecryptPassword();
		
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("cmd /c powershell.exe -command \"& C:\\powershell\\ad\\SetPassword-UserActiveDir.ps1 ");
		strBuf.append("'"+ host +"' ");
		strBuf.append("'"+ hostlogin +"' ");
		strBuf.append("'"+ hostpassword +"' ");
		strBuf.append("'"+ userName +"' "); 
		strBuf.append("'"+ reqType.getPassword() +"' \" ");
	
		System.out.println("Command line string= " + strBuf.toString());
		String[] cmdarray = {"cmd",strBuf.toString()};
		try {
			//Runtime.getRuntime().exec(cmdarray); //exec(strBuf.toString());
			Process p = Runtime.getRuntime().exec(strBuf.toString());
			System.out.println("Process =" + p);
			OutputStream stream =  p.getOutputStream();
			System.out.println( "stream=" + stream.toString() );
			
		}catch(Exception e) {
			e.printStackTrace();
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




	/**
	 * Logs a message into the audit log.
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
		IdmAuditLog log = new IdmAuditLog( objectTypeId, actionId, actionStatus,
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
}
