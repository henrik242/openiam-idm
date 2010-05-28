// changes the status us users that have been inactive
// runs every night

import org.openiam.idm.srvc.user.dto.UserStatusEnum
import org.openiam.idm.srvc.msg.dto.NotificationRequest

System.out.println("inactiveUser.groovy is being executed.");

def loginManager = context.getBean("loginWS")
def userManager = context.getBean("userWS")
def mailService = context.getBean("mailService")

loginList = loginManager.getInactiveUsers(90,0).principalList
if (loginList != null ) {
	for ( lg in loginList) {
		user = userManager.getUserWithDependent(lg.userId, true).user		

System.out.println("User=" + user)

		if (user.status == null || user.status != UserStatusEnum.INACTIVE) {
			user.status = UserStatusEnum.INACTIVE
			userManager.updateUserWithDependent(user,true)
	    System.out.println("- Users set to inactive");
	    
	    NotificationRequest req = new NotificationRequest()
		  req.notificationType = "ACCOUNT_INACTIVE"
	  	req.userId = lg.userId
			mailService.sendNotification(req)

	    
    }		
	}
}





output=1