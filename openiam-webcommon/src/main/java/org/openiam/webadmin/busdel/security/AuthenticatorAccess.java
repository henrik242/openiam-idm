package org.openiam.webadmin.busdel.security;

import org.openiam.webadmin.busdel.base.NavigationAccess;
import diamelle.security.auth.*;
import java.util.*;

public class AuthenticatorAccess extends NavigationAccess {

   Authenticator auth = null;

   public AuthenticatorAccess() {
     super();
     try {
       AuthenticatorHome aHome = (AuthenticatorHome)getHome("Authenticator");
       auth = aHome.create();
     } catch(Exception e) {
       e.printStackTrace();
     }
   }

   // takes the login and service and authenticates the userid and returns the userId
   public String getSSOSubject(LoginValue loginValue) throws Exception {
          //return auth.authenticate(loginValue);
          SSOSubject sub = auth.authenticate(loginValue);
          return sub.getUserId();
   }

  public Set getPrincipals(String userId) throws Exception {
         return auth.getPrincipals(userId);
  }

   public void addLogin(LoginValue loginValue) throws Exception {
         auth.addLogin(loginValue);
  }

  public void removeLogin(String serviceId, String loginId) throws Exception {
        auth.removeLogin(serviceId, loginId);
  }

  public LoginValue getLogin(String login, String serviceId) throws Exception {
        return auth.getLogin(login,serviceId);
  }

  public void updateLogin(LoginValue lv) throws Exception {
       auth.updateLogin(lv);
  }
  public String autoResetPassword(String service, String login) throws Exception {
  	return auth.autoResetPassword(service, login);
  }
  public List getAllLogins(String userId) throws Exception {
  	return auth.getAllLogins(userId);
  }
  public List getLoginsByService(String serviceId) throws Exception {
  	return auth.getLoginsByService(serviceId);
  }
}