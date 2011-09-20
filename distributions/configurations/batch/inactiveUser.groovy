// changes the status us users that have been inactive
// runs every night

import org.openiam.idm.srvc.user.dto.UserStatusEnum
import org.openiam.idm.srvc.msg.dto.NotificationRequest

System.out.println("inactiveUser.groovy is being executed.");

import java.util.Date
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.groovy.helper.ServiceHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.provision.service.ProvisionService;
import org.openiam.provision.dto.ProvisionUser


def MailService mailService = ServiceHelper.emailService();
def LoginDataWebService loginManager = ServiceHelper.loginService()
def UserDataWebService userManager = ServiceHelper.userService()
def ProvisionService provision = ServiceHelper.povisionService()


loginList = loginManager.getInactiveUsers(90,0).principalList
if (loginList != null ) {
	for ( lg in loginList) {
		user = userManager.getUserWithDependent(lg.userId, true).user		

		println("User id=" + user.userId)

		if (user.status == null || (user.status != UserStatusEnum.INACTIVE &&
			 user.status != UserStatusEnum.TERMINATE && user.status != UserStatusEnum.LEAVE )) {
			
			println("****user status=" + user.status)
			 
			 ProvisionUser pUser = new ProvisionUser(user);
			 pUser.userId = user.userId
			 pUser.status = UserStatusEnum.INACTIVE
			 pUser.secondaryStatus = null
			 pUser.lastUpdatedBy = "3000"
			 provision.modifyUser(pUser)
			 
	    
			 NotificationRequest req = new NotificationRequest()
			 req.notificationType = "ACCOUNT_INACTIVE"
			 req.userId = user.userId
			 mailService.sendNotification(req)	    
		}		
	}
}

output=1