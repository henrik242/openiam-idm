// send a notification that a users account has been locked because of too many failed login attempts
// runs every minute

import groovyx.net.ws.WSClient
import java.util.Date
import org.openiam.idm.srvc.msg.dto.NotificationRequest
@Grab(group='org.codehaus.groovy.modules', module='groovyws', version='0.5.1')


def getProxy(wsdl, classLoader) {
  new WSClient(wsdl, classLoader)
}
proxy = getProxy("http://localhost:8080/idm-ws/idmsrvc/EmailWebService?wsdl", this.class.classLoader)
proxy.initialize()


def loginManager = context.getBean("loginWS")

System.out.println("Manager  =" + loginManager)


loginList = loginManager.getLockedUserSince(lastExecTime).PrincipalList
if (loginList != null ) {
	for ( lg in loginList) {
	  System.out.println("userId=" + lg.userId)

	  NotificationRequest request = new NotificationRequest(lg.userId, "ACCOUNT_LOCKED")
		proxy.sendNotification(request)

	}
}

output=1