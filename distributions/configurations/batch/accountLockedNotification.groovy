// send a notification that a users account has been locked because of too many failed login attempts
// runs every minute


import java.util.Date
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.groovy.helper.ServiceHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;

def MailService mailSrv = ServiceHelper.emailService();
def loginManager = ServiceHelper.loginService()

println("Manager  =" + loginManager)


loginList = loginManager.getLockedUserSince(lastExecTime).principalList
if (loginList != null ) {
	for ( lg in loginList) {
	  println("userId=" + lg.userId)

	  NotificationRequest request = new NotificationRequest(lg.userId, "ACCOUNT_LOCKED")
	  mailSrv.sendNotification(request)

	}
}

output=1