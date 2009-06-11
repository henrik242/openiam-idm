/*
 * Created on Jan 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.webadmin.busdel.security;

import java.util.*;
import java.rmi.*;
import javax.naming.*;

import org.openiam.webadmin.busdel.base.NavigationAccess;
import diamelle.security.log.*;
import diamelle.util.Log;

/**
 * @author suneet
 *
  */
public class AuditLogAccess extends NavigationAccess {
	static protected AccessLogManager mgr = null;
	
	public AuditLogAccess() {
	      super();
	      try {
	      	AccessLogManagerHome logHome = (AccessLogManagerHome)getHome("AccessLogManager");
	        mgr = logHome.create();
	      } catch(Exception e) {
	        e.printStackTrace();
	      }		
	}
	public List search(LogSearch search) throws RemoteException {
		return mgr.search(search);		
	}
	public void addLog(AccessLogValue val) throws RemoteException {
		mgr.addLog(val);
	}
	
	/**
	 * Used to log an event for the application
	 * @param msg
	 * @param ip
	 * @param userId
	 * @param loginId
	 * @param serviceId
	 */
	static public void logEvent(String msg, String ip, String userId, 
				String loginId, String serviceId) {
		
		if (mgr == null) {
		      try {
		      	Context ctx = new InitialContext();
		      	AccessLogManagerHome logHome = (AccessLogManagerHome) ctx.lookup("java:comp/env/ejb/AccessLogManager" );
		 		mgr = logHome.create();
		      } catch(Exception e) {
		        e.printStackTrace();
		      }				
		}
		AccessLogValue logVal = new AccessLogValue();
		logVal.setAccessLogId(String.valueOf(System.currentTimeMillis()));
		logVal.setDescription(msg);
		logVal.setHostIp(ip);
		logVal.setLogTime(new java.util.Date(System.currentTimeMillis()));
		logVal.setUserId(userId);
		logVal.setLoginId(loginId);
		logVal.setServiceId(serviceId);		
		try {
			mgr.addLog(logVal);
		}catch(RemoteException re) {
			Log.error(re.getMessage(),re);
		}
	}

}
