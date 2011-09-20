// Sends a notification to users whose passwords are going to expire
// runs every night

System.out.println("passwordExpirationNotification.groovy");

import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.srvc.msg.dto.NotificationParam

import java.util.Date
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.msg.dto.NotificationRequest
import org.openiam.idm.groovy.helper.ServiceHelper;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.base.id.UUIDGen;


def MailService mailSrv = ServiceHelper.emailService();
def loginManager = ServiceHelper.loginService()

//def userManager = context.getBean("userWS")
//def mailService = context.getBean("mailService")
//def loginManager = context.getBean("loginWS")


currentDate = new Date(System.currentTimeMillis())

loginList = loginManager.getUserNearPswdExpiration(14).principalList


if (loginList != null ) {
	for ( lg in loginList) {
	  
	  expDate = lg.pwdExp
	  
	  
	  diffInMilleseconds = expDate.getTime() - currentDate.getTime();
	  diffInSeconds = diffInMilleseconds/1000;
	  diffInMinutes = diffInSeconds/60;
	  diffInHours = diffInMinutes/60;
	  diffInDays = diffInHours/24;

	  NotificationRequest req = new NotificationRequest()
	  NotificationParam param = new NotificationParam()
	  param.name = "daysToExpiration"
	  param.value = (int)diffInDays
	  
	  req.notificationType = "PASSWORD_NEAR_EXPIRATION"
	  req.userId = lg.userId
	  req.linkedRequestId = parentRequestId
	  req.requestId =  UUIDGen.getUUID()
	  
	  req.paramList.add(param)
	  
	  mailSrv.sendNotification(req)
			
		
	}
}

output=1

