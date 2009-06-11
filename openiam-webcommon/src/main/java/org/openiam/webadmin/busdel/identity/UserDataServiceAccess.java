package org.openiam.webadmin.busdel.identity;

import org.springframework.web.context.*;

import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;

public class UserDataServiceAccess {
	
	private UserDataService userMgr;
	


  public UserDataServiceAccess()  {

  }

	public UserDataServiceAccess(WebApplicationContext  webContext) {
		userMgr = (UserDataService)webContext.getBean("userManager");
	}
  
  

 public void createUser(User ud)  {
	 userMgr.addUser(ud);
 }

 }