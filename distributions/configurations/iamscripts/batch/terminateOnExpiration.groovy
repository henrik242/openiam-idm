//  Accounts that are in any status (besides "Terminated") must be terminated on the termination date.


System.out.println("terminateOnExpiration.groovy");

import groovyx.net.ws.WSClient
import java.util.Calendar
import org.openiam.idm.srvc.user.dto.UserSearch
import org.openiam.idm.srvc.user.dto.UserStatusEnum
import org.openiam.provision.dto.ProvisionUser
import org.openiam.idm.srvc.msg.dto.NotificationRequest


def userManager = context.getBean("userWS")
def mailService = context.getBean("mailService")
def loginManager = context.getBean("loginWS")
def provision = context.getBean("defaultProvision")

		UserSearch search = new UserSearch();
	  
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		
		System.out.println("create date=" + cal.getTime() )
		
		search.lastDate = cal.getTime()
		userList = userManager.search(search).userList;

    System.out.println("Userlist = " + userList)
    
if (userList != null ) {
	for ( user in userList) {	
	
	System.out.println("User status=" + user.status)

	System.out.println("User id=" + user.userId)
			
		if (user.status == UserStatusEnum.TERMINATE ) {
			System.out.println("status is terminate")
		}else {
			u = userManager.getUserWithDependent(user.userId, true).user
			
			ProvisionUser pUser = new ProvisionUser();
			pUser.userId = user.userId
			pUser.status = UserStatusEnum.TERMINATE
			pUser.secondaryStatus = null
			pUser.lastUpdatedBy = "3000"
			pUser.lastDate = search.lastDate
			provision.modifyUser(pUser)
			
		
	    NotificationRequest req = new NotificationRequest()
		  req.notificationType = "ACCOUNT_TERMINATED"
	  	req.userId = user.userId
			mailService.sendNotification(req)
			
		}
	}	 
}

output=1