package org.openiam.spml2.spi.ldap;


import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.SysConfiguration;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.pswd.service.PasswordGenerator;
import org.openiam.spml2.msg.ErrorCode;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.suspend.ActiveRequestType;
import org.openiam.spml2.msg.suspend.ActiveResponseType;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;
import org.openiam.spml2.util.connect.ConnectionFactory;
import org.openiam.spml2.util.connect.ConnectionManagerConstant;
import org.openiam.spml2.util.connect.ConnectionMgr;

/**
 * Implements the suspend functionality for the ldap connector
 * @author suneet
 *
 */
public class LdapSuspend {
	
	private static final Log log = LogFactory.getLog(LdapSuspend.class);

	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected LoginDataService loginManager;
	protected SysConfiguration sysConfiguration;
	
	
	public ResponseType suspend(SuspendRequestType request) {
		log.debug("suspend request called..");
		// ldap does not have suspend/disable capability.
		// work around is to scramble the password
		
		String requestID = request.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = request.getPsoID();
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
	 	
	 	
	 	String ldapName = psoID.getID();
	 	
	 	try {
	 		log.debug("Scrambling password...");
	 		String scrambledPswd =	PasswordGenerator.generatePassword(10);
	 		
	 		ModificationItem[] mods = new ModificationItem[1];
	 		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", scrambledPswd));
	 		ldapctx.modifyAttributes(ldapName, mods);
	
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

	public ResponseType resume(ResumeRequestType request) {
		log.debug("resume request called..");
		// ldap does not have suspend/disable capability.
		// To resume, replace the scrambled password with the one that is stored in the IDM system
		
		String requestID = request.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = request.getPsoID();
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
	 	
	 	
	 	String ldapName = psoID.getID();
	 	
	 	try {
	 		log.debug("restoring password...");
	 		// get the current password for the user.
	 		Login login = loginManager.getLoginByManagedSys(sysConfiguration.getDefaultSecurityDomain(),
	 				ldapName, targetID);
	 		String encPassword = login.getPassword();
	 		String decPassword = loginManager.decryptPassword(encPassword);
	 		 		
	 		ModificationItem[] mods = new ModificationItem[1];
	 		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", decPassword));
	 		ldapctx.modifyAttributes(ldapName, mods);
	
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

	public ActiveResponseType active(ActiveRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	public ManagedSystemObjectMatchDAO getManagedSysObjectMatchDao() {
		return managedSysObjectMatchDao;
	}

	public void setManagedSysObjectMatchDao(
			ManagedSystemObjectMatchDAO managedSysObjectMatchDao) {
		this.managedSysObjectMatchDao = managedSysObjectMatchDao;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

	public SysConfiguration getSysConfiguration() {
		return sysConfiguration;
	}

	public void setSysConfiguration(SysConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}
}
