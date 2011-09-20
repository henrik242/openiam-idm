import org.springframework.context.support.ClassPathXmlApplicationContext

println "userId in script=" + user.userId

if (user.classification == "INT") {
  output="org.openiam.idm.srvc.auth.spi.ActiveDirectoryLoginModule"
}else{
  output="org.openiam.idm.srvc.auth.spi.DefaultLoginModule"
}

