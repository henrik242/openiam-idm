// send a notification that a users has been in pending validation for 5 days
// runs every night

System.out.println("pendingUserValidationNotification.groovy");

import groovyx.net.ws.WSClient
import java.util.Calendar
import org.openiam.idm.srvc.user.dto.UserSearch

@Grab(group='org.codehaus.groovy.modules', module='groovyws', version='0.5.1')


def getProxy(wsdl, classLoader) {
  new WSClient(wsdl, classLoader)
}
proxy = getProxy("http://localhost:8080/mpi-ws/MpiNotificationService?wsdl", this.class.classLoader)
proxy.initialize()


def loginManager = context.getBean("loginManager")
def userManager = context.getBean("userManager")
def auditService = context.getBean("auditDataService")

		UserSearch search = new UserSearch();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.add(Calendar.DATE,  -5);
		
		System.out.println("create date=" + cal.getTime() )
		
		search.createDate = cal.getTime()
		search.status = "PENDING_USER_VALIDATION"
		userList = userManager.search(search);

if (userList != null ) {
	for ( user in userList) {	
		
		System.out.println("User id in script=" + user.userId)
		
		//proxy.sendNotification(user.userId, 0, "ACTIVE")
		
	}
}

output=1