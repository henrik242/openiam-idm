// send a notification that a users account has been locked because of too many failed login attempts
// runs every minute

import org.openiam.idm.srvc.msg.dto.NotificationRequest

System.out.println("accountlockedNotify called.")

def mailService = context.getBean("mailService")

def loginManager = context.getBean("loginWS")

principalList = loginManager.getLockedUserSince(lastExecTime).principalList

if (principalList != null ) {
	for ( lg in principalList) {

	  NotificationRequest req = new NotificationRequest()
	  req.notificationType = "ACCOUNT_LOCKED"
	  req.userId = lg.userId
		mailService.sendNotification(req)

	}
}


output=1
