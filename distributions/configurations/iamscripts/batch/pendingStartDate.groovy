//  Internal Accounts that are "Pending Start Date" must be made:
//  - "Pending Initial Login" on the start date if the user has not logged in within the past 90 days.
//  -  "Active" on the start date if the user has logged in within the past 90 days.


System.out.println("clientStatusNotification.groovy");

import groovyx.net.ws.WSClient
import java.util.Calendar
import java.util.Date
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
currentDate = new Date(System.currentTimeMillis())


		UserSearch search = new UserSearch();
	  
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()))
		
		System.out.println("create date=" + cal.getTime() )
		
		search.startDate = cal.getTime()
		search.status = "PENDING_START_DATE"
		userList = userManager.search(search)

    System.out.println("Userlist = " + userList)
    
if (userList != null ) {
	for ( user in userList) {	
	
		lastLogin = ""
	
	if (user.classification == "INT" ) {
		  loginList = loginManager.getLoginByUser(user.userId)
		  // get the last login date for the primary identity
		  if ( loginList != null ) {
		  	for (lg in loginList) {
		  		if (lg.id.managedSysId == "0") {
		  			lastLogin = lg.lastLogin
		  		}
		  	}
			}
			if (lastLogin != null) {
				// determine the number of days
				diffInMilleseconds = currentDate.getTime() - lastLogin.getTime() 
			  diffInSeconds = diffInMilleseconds/1000;
			  diffInMinutes = diffInSeconds/60;
		   	diffInHours = diffInMinutes/60;
		  	diffInDays = diffInHours/24;
	
		    System.out.println("** diff in days=" + (int)diffInDays)
				
				if (diffInDays > 90) {
					u = userManager.getUserWithDependent(user.userId, true)
					u.status = UserStatusEnum.PENDING_INITIAL_LOGIN
					userManager.updateUserWithDependent(u,true)				
				}else {
					u = userManager.getUserWithDependent(user.userId, true)
					u.status = UserStatusEnum.ACTIVE
					u.secondaryStatus = null
					userManager.updateUserWithDependent(u,true)
				}
			}else {
					u = userManager.getUserWithDependent(user.userId, true)
					u.status = UserStatusEnum.PENDING_INITIAL_LOGIN
					userManager.updateUserWithDependent(u,true)			
			}
		
	}

		
	}	 
}

output=1