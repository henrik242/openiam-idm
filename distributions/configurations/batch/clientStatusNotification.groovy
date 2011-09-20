// Client, Provider and Vendor Accounts that are "Pending Start Date" must be made "Pending User Validation" on the start date. 
// This must also call the SendNotification Operation of the Identity Service and pass "ACTIVE" as the NotificaitonType. (see below)


System.out.println("clientStatusNotification.groovy");

import groovyx.net.ws.WSClient
import java.util.Calendar
import org.openiam.idm.srvc.user.dto.UserSearch
import org.openiam.idm.srvc.user.dto.UserStatusEnum

@Grab(group='org.codehaus.groovy.modules', module='groovyws', version='0.5.1')


def getProxy(wsdl, classLoader) {
  new WSClient(wsdl, classLoader)
}
proxy = getProxy("http://localhost:8080/mpi-ws/MpiNotificationService?wsdl", this.class.classLoader)
proxy.initialize()


def loginManager = context.getBean("loginManager")
def userManager = context.getBean("userManager")

		UserSearch search = new UserSearch();
	  
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		
		System.out.println("create date=" + cal.getTime() )
		
		search.startDate = cal.getTime()
		search.status = "PENDING_START_DATE"
		userList = userManager.search(search);

    System.out.println("Userlist = " + userList)
    
if (userList != null ) {
	for ( user in userList) {	
	
		classification = user.classification
		
		System.out.println("user classification = " + classification)
		
		if (classification == "CLI" || classification == "VND") {
			u = userManager.getUserWithDependent(user.userId, true)
			u.status = UserStatusEnum.PENDING_USER_VALIDATION
			userManager.updateUserWithDependent(u,true)
		
		   System.out.println("User id in script=" + user.userId)
		
		  proxy.sendNotification(user.userId, 0, "ACTIVE")
		
	}
}
}else {
	System.out.println("No users found")
}

output=1