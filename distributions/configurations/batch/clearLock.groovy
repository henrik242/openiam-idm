//  Accounts that are in any status (besides "Terminated") must be terminated on the termination date.


System.out.println("clearLock.groovy");

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
		
		search.lastDate = cal.getTime()
		userList = userManager.search(search);

    System.out.println("Userlist = " + userList)
    
if (userList != null ) {
	for ( user in userList) {	
	
		classification = user.classification
		
		System.out.println("user classification = " + classification)
		
		if (user.status != "TERMINATE") {
			u = userManager.getUserWithDependent(user.userId, true)
			u.status = UserStatusEnum.TERMINATE
			userManager.updateUserWithDependent(u,true)
  		 System.out.println("User id in script=" + user.userId)
		}
	}	 
}

output=1