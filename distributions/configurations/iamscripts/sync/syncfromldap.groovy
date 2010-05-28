//sync from ldap.groovy

System.out.println("syncfromldap.groovy called.");

import groovyx.net.ws.WSClient

@Grab(group='org.codehaus.groovy.modules', module='groovyws', version='0.5.1')


def getProxy(wsdl, classLoader) {
  new WSClient(wsdl, classLoader)
}

proxy = getProxy("http://localhost:8080/sync-ldap-connector/SyncFromLdapService?wsdl", this.class.classLoader)
proxy.initialize()

proxy.executeSync("104", lastExecTime)

output=1