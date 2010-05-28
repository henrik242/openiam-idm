// send a notification that a users account has been locked because of too many failed login attempts
// runs every minute

import groovyx.net.ws.WSClient
@Grab(group='org.codehaus.groovy.modules', module='groovyws', version='0.5.1')


def getProxy(wsdl, classLoader) {
  new WSClient(wsdl, classLoader)
}
proxy = getProxy("http://localhost:8080/mpi-ws/MpiNotificationService?wsdl", this.class.classLoader)
proxy.initialize()


def loginManager = context.getBean("loginManager")
def userManager = context.getBean("userManager")


loginList = loginManager.getLockedUserSince(lastExecTime)
if (loginList != null ) {
	for ( lg in loginList) {
	  System.out.println("userId=" + lg.userId)


		//proxy.send("suneet@openiam.com","suneet_shah@openiam.com", "test", "finally it works -2222")
		proxy.sendNotification(lg.userId,0,"LOCK" )
		
	}
}

output=1